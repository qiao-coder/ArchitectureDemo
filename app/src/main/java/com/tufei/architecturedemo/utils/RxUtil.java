package com.tufei.architecturedemo.utils;

import com.tufei.architecturedemo.net.FaceHttpResult;
import com.tufei.architecturedemo.net.HttpResult;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author tufei
 * @date 2017/7/8
 */

public class RxUtil {

    public static <T> SingleTransformer<T, T> io_main() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> SingleTransformer<T, T> all_io() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }

    /**
     * 1)实现了线程切换io->main
     * 2)用于预处理后台返回的json
     * 注意：
     * 如果对{@link HttpResult#data}不关心，即使data为空也无所谓的时候，
     * 只在乎网络请求的结果{@link HttpResult#success}，
     * 那么请用{@link #io_main_handleNoData()},因为RxJava不允许发送null
     * <p>
     * 使用的时候，
     * 如果{@link HttpResult#data}是一串json，这么写：Observable<HttpResult<Bean>>
     * 如果{@link HttpResult#data}是一组json，这么写：Observable<HttpResult<List<Bean>>>
     *
     * @param <T>
     * @return
     */
    public static <T> SingleTransformer<HttpResult<T>, T> io_main_handleHttpResult() {
        return httpResultObservable ->
                httpResultObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .flatMap(httpResult -> {
                            if (httpResult.isSuccess()) {
                                return Single.just(httpResult.getData());
                            } else {
                                return Single.error(new Exception(httpResult.getErrmsg()));
                            }
                        });
    }

    /**
     * 使用的时候，这么写：Observable<HttpResult>
     *
     * @return
     */
    public static ObservableTransformer<HttpResult, HttpResult> io_main_handleNoData() {
        return httpResultObservable ->
                httpResultObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                        flatMap(httpResult -> {
                            if (httpResult.isSuccess()) {
                                return Observable.just(httpResult);
                            } else {
                                return Observable.error(new Exception(httpResult.getErrmsg()));
                            }
                        });
    }


    /**
     * 1)实现了线程切换io->main
     * 2)用于预处理百度人脸相关接口返回的json
     * 注意：
     * 如果对{@link FaceHttpResult#result}不关心，即使result为空也无所谓的时候，
     * 只在乎网络请求的结果{@link FaceHttpResult#error_code}或者{@link FaceHttpResult#error_msg}，
     * 那么请用{@link #io_main_handleFaceNoData()},因为RxJava不允许发送null
     *
     * @param <T>
     * @return
     */
    public static <T> SingleTransformer<FaceHttpResult<T>, T> io_main_handleFaceHttpResult() {
        return httpResultObservable ->
                httpResultObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                        flatMap(httpResult -> {
                            if (httpResult.getErrorCode() != 0 || httpResult.getErrorMsg() != null) {
                                return Single.error(new Exception(httpResult.getErrorMsg()));
                            } else {
                                return Single.just(httpResult.getResult());
                            }
                        });
    }

    /**
     * 使用的时候，这么写：Single<FaceHttpResult>
     *
     * @return
     */
    public static SingleTransformer<FaceHttpResult, FaceHttpResult> io_main_handleFaceNoData() {
        return httpResultObservable ->
                httpResultObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                        flatMap(httpResult -> {
                            if (httpResult.getErrorCode() != 0 || httpResult.getErrorMsg() != null) {
                                return Single.error(new Exception(httpResult.getErrorMsg()));
                            } else {
                                return Single.just(httpResult);
                            }
                        });
    }
}
