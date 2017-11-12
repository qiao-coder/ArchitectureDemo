package com.tufei.architecturedemo.net;

/**
 * 后台的网络请求返回的json的实体类封装类
 *
 * @author tufei
 * @date 2017/7/3
 */

public class HttpResult<T> {
    /**
     * 若请求失败，则为 false；
     * 若请求成功，则为 true；
     */
    private boolean success;
    private T data;
    private String errmsg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}