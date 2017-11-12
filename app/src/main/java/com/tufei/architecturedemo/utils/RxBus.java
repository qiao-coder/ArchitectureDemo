package com.tufei.architecturedemo.utils;


import java.util.HashMap;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;

/**
 * TODO:待完善 考虑背压问题
 * 参考博文：
 * <a href="http://www.jianshu.com/p/ccf624368ec9"/>
 * <a href="http://flyou.ren/2017/04/05/%E5%85%B3%E4%BA%8ERxJava%E8%83%8C%E5%8E%8B/?utm_source=tuicool&utm_medium=referral"/>
 * Created by tufei on 2017/7/12.
 */

public class RxBus {
    private static final String TAG = "RxBus";
    private static volatile RxBus mInstance;
    private final FlowableProcessor<Object> mBus;
    private HashMap<String, CompositeDisposable> mCompositeDisposableMap;

    /**
     * Rxjava1中，
     * SerializedSubject<Object, Object> mBus = new SerializedSubject<>(PublishSubject.create());
     * PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
     * Subject同时充当了Observer和Observable的角色，Subject是非线程安全的，要避免该问题，
     * 需要将 Subject转换为一个 SerializedSubject ，上述RxBus类中把线程非安全的PublishSubject包装成线程安全的Subject。
     * <p>
     * Rxjava2中，
     * FlowableProcessor<Object> mBus = PublishProcessor.create().toSerialized();
     * SerializedSubject 已经变为非public类
     * 可以通过bus = PublishSubject.create().toSerialized();的方式获取线程安全 的对象。
     */
    private RxBus() {
        mBus = PublishProcessor.create().toSerialized();
    }

    /**
     * 单例 双重锁
     *
     * @return
     */
    public static RxBus getInstance() {
        if (mInstance == null) {
            synchronized (RxBus.class) {
                if (mInstance == null) {
                    mInstance = new RxBus();
                }
            }
        }
        return mInstance;
    }

    /**
     * 提供了一个新的事件,单一类型
     *
     * @param o 事件数据
     */
    public void post(Object o) {
        mBus.onNext(o);
    }

    /**
     * 根据传递的 type 类型返回特定类型(type)的 被观察者
     *
     * @param type
     * @param <T>
     * @return
     */
    public <T> Flowable<T> tObservable(final Class<T> type) {
        //ofType操作符只发射指定类型的数据，其内部就是filter+cast
        return mBus.ofType(type);
    }

    /**
     * 提供了一个新的事件,根据code进行分发
     *
     * @param flag 事件flag
     * @param o
     */
    public void post(String flag, Object o) {
        mBus.onNext(new Message(flag, o));
    }


    /**
     * 根据传递的code和 type 类型返回特定类型(type)的 被观察者
     *
     * @param flag 事件flag
     * @param type 事件类型
     * @param <T>
     * @return
     */
    public <T> Flowable<T> tObservable(final String flag, final Class<T> type) {
        //ofType操作符只发射指定类型的数据，其内部就是filter+cast
        return mBus.ofType(Message.class)
                .filter(new Predicate<Message>() {
                            @Override
                            public boolean test(@NonNull Message message) throws Exception {
                                return message.getFlag().equals(flag) && type.isInstance(message.getObject());
                            }
                        }
                ).map(new Function<Message, Object>() {
                          @Override
                          public Object apply(@NonNull Message message) throws Exception {
                              return message.getObject();
                          }
                      }
                ).cast(type);
    }

    public <T> Disposable doSubscribe(Class<T> type, Consumer<T> next, Consumer<Throwable> error) {
        return tObservable(type)
                //有时候订阅下载事件，会出现"Could not emit value due to lack of requests"
                .onBackpressureLatest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next, error);
    }

    public <T> Disposable doSubscribe(String flag, Class<T> type, Consumer<T> next, Consumer<Throwable> error) {
        return tObservable(flag, type)
                //有时候订阅下载事件，会出现"Could not emit value due to lack of requests"
                .onBackpressureLatest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next, error);
    }

    public void addDisposable(String key, Disposable disposable) {

        if (mCompositeDisposableMap == null) {
            mCompositeDisposableMap = new HashMap<>();
        }
        if (mCompositeDisposableMap.get(key) != null) {
            mCompositeDisposableMap.get(key).add(disposable);
        } else {
            CompositeDisposable compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(disposable);
            mCompositeDisposableMap.put(key, compositeDisposable);
        }
    }

    public void unSubscribe(String key) {
        if (mCompositeDisposableMap == null) {
            return;
        }
        if (!mCompositeDisposableMap.containsKey(key)) {
            return;
        }
        if (mCompositeDisposableMap.get(key) != null) {
            mCompositeDisposableMap.get(key).clear();
        }
        mCompositeDisposableMap.remove(key);
    }

    class Message {
        private String flag;
        private Object object;

        public Message() {
        }

        public Message(String flag, Object o) {
            this.flag = flag;
            this.object = o;
        }

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }
    }
}
