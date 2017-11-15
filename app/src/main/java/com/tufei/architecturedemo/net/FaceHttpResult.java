package com.tufei.architecturedemo.net;

/**
 * 请求百度人脸接口时返回的json的实体类封装
 * @author tufei
 * @date 2017/10/18.
 */

public class FaceHttpResult<T>{

    /**
     * 成功时，error_code为0
     */
    private int error_code;
    /**
     * 成功时，error_msg为null
     */
    private String error_msg;
    private long log_id;
    private int result_num;
    private T result;

    public int getErrorCode() {
        return error_code;
    }

    public void setErrorCode(int error_code) {
        this.error_code = error_code;
    }

    public T getResult() {
        return result;
    }


    public void setResult(T result) {
        this.result = result;
    }

    public String getErrorMsg() {
        return error_msg;
    }

    public void setErrorMsg(String error_msg) {
        this.error_msg = error_msg;
    }

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public int getResult_num() {
        return result_num;
    }

    public void setResult_num(int result_num) {
        this.result_num = result_num;
    }

}
