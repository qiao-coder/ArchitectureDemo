package com.tufei.architecturedemo.constants;

/**
 * @author tufei
 * @date 2017/9/11
 */

@SuppressWarnings("unused")
public class NetConstants {
    public static final long HTTP_CONNECT_TIMEOUT = 5000;
    public static final long HTTP_READ_TIMEOUT = 5000;
    public static final String BASE_URL = "http://www.nishabi.com";

    //add some constants
    public static final int NET_CODE_SUCCESS = 0;
    public static final int NET_CODE_ERROR = 1;

    public static final int NET_CODE_CONNECT = 400;
    public static final int NET_CODE_UNKNOWN_HOST = 401;
    public static final int NET_CODE_SOCKET_TIMEOUT = 402;

    public static final String CONNECT_EXCEPTION = "网络连接异常，请检查您的网络状态";
    public static final String SOCKET_TIMEOUT_EXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试";
    public static final String UNKNOWN_HOST_EXCEPTION = "与服务器连接失败";
    public static final String EMPTY_RESPONSE_EXCEPTION = "无效的返回";
}
