package com.tufei.architecturedemo.core.retrofit.result;


import com.tufei.architecturedemo.constants.NetConstants;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by wjc on 2018/1/2. 网络请求结果处理
 */
@SuppressWarnings("unused")
public abstract class ResultObserver<T> implements Observer<Result<T>> {
    @Override public void onSubscribe(Disposable d) {

    }

    @Override public void onNext(Result<T> result) {
        if (result != null) {
            if (result.getCode() == NetConstants.NET_CODE_SUCCESS) {
                handlerResult(result.getResult());
            } else {
                handlerError(result.getCode(), result.getMsg());
            }
        } else {
            handlerError(NetConstants.NET_CODE_ERROR, NetConstants.EMPTY_RESPONSE_EXCEPTION);
        }
    }

    @Override public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            handlerError(NetConstants.NET_CODE_SOCKET_TIMEOUT, NetConstants.SOCKET_TIMEOUT_EXCEPTION);
        } else if (e instanceof ConnectException) {
            handlerError(NetConstants.NET_CODE_CONNECT, NetConstants.CONNECT_EXCEPTION);
        } else if (e instanceof UnknownHostException) {
            handlerError(NetConstants.NET_CODE_UNKNOWN_HOST, NetConstants.UNKNOWN_HOST_EXCEPTION);
        } else {
            handlerError(NetConstants.NET_CODE_ERROR, e.getMessage());
        }
    }

    @Override public void onComplete() {

    }

    /**
     * 返回正常数据
     */
    public abstract void handlerResult(T t);

    /**
     * 返回业务错误
     */
    public void handlerError(int code, String msg) {
    }

}
