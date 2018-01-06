package com.tufei.architecturedemo.core.retrofit.result;

import com.tufei.architecturedemo.constants.NetConstants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wjc on 2018/1/2.ResultCallBack
 */

public abstract class ResultCallBack<T> implements Callback<Result<T>> {
    @Override public void onResponse(Call<Result<T>> call, Response<Result<T>> response) {
        if (response != null && response.body()!=null) {
            if (response.body().getCode() == NetConstants.NET_CODE_SUCCESS) {
                handlerResult(true, null, response.body().getResult());
            } else {
                handlerResult(false, new Throwable(response.body().getMsg()), null);
            }
        } else {
            handlerResult(false, new Throwable(NetConstants.EMPTY_RESPONSE_EXCEPTION), null);
        }
    }

    @Override public void onFailure(Call<Result<T>> call, Throwable t) {
        handlerResult(false, t, null);
    }

    public abstract void handlerResult(boolean success, Throwable throwable, T t);
}
