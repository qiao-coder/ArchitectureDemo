package com.tufei.architecturedemo.rxjavatest;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author tufei
 * @date 2018/1/19.
 */

public class RepeatWhen {
    static int i = 0;

    public static void main(String[] args) {

        Observable.range(1, 3)
                .repeatWhen((Function<Observable<Object>, ObservableSource<Object>>) v -> {
                    //不管是下面哪种情况，只输出一次，所以这个方法体永远只会执行一次
                    System.out.println("我执行了");

                    //走onError,错误信息：The handler returned a null ObservableSource
                    //return null;

                    //走onError
                    //throw new Exception();

                    //注意，1 2 3重复发送的次数  跟这里just里面的参数个数有关，为什么会这么奇怪，看源码才能知道
                    //输出:我执行了 1 2 3 onComplete（1个参数，不重复）
                    //return Observable.just(1);
                    //输出:我执行了 1 2 3 1 2 3 onComplete （2个参数，重复一次）
                    //return Observable.just(1, 2);

                    //1 2 3无限重复发送
                    //return v;

                    //使用关键字take,这才是限制重复发送次数的正确姿势
                    //输出：我执行了 1 2 3 1 2 3 onComplete
                    //return v.take(2);

                    //延迟发送   延迟一秒无限循环发送(第一次发送是不会存在延迟的)
                    //return v.delay(1000, TimeUnit.MILLISECONDS);

                    //输出：我执行了 onComplete（？？take(0)的时候，有古怪啊 不是应该起码发送一次吗 这算不算是设计bug）
                    //return v.delay(1000, TimeUnit.MILLISECONDS).take(0);
                    //输出：我执行了 1 2 3 1 2 3 onComplete(重复发送了一次)
                    //return v.delay(1000, TimeUnit.MILLISECONDS).take(1);
                    //输出：我执行了 1 2 3 1 2 3 1 2 3 onComplete(重复发送了两次)
                    return v.delay(1000, TimeUnit.MILLISECONDS).take(2);
                })
                .subscribeOn(Schedulers.io())
                .subscribe(integer -> System.out.println(integer),
                        throwable -> System.out.println(throwable.getMessage()),
                        () -> System.out.println("onComplete"));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
