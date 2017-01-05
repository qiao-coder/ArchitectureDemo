package com.tufei.architecturedemo.core.retrofit.interceptor;

import android.text.TextUtils;
import android.util.ArrayMap;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wjc on 2017/12/29.请求头拦截器
 */

public class HeaderInterceptor implements Interceptor {
    private ArrayMap<String, String> headers;

    public HeaderInterceptor(ArrayMap<String, String> headers) {
        this.headers = headers;
    }

    @Override public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        //如果公共请求头不为空,则构建新的请求
        if (headers != null) {
            for (String key : headers.keySet()) {
                requestBuilder.addHeader(key, headers.get(key));
            }
        }
        Request request = requestBuilder.build();
        Response.Builder responseBuilder = chain.proceed(request).newBuilder();
        if (!TextUtils.isEmpty(request.cacheControl().toString())) {
            responseBuilder
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + request.cacheControl().maxAgeSeconds());
        }
        return responseBuilder.build();
    }
}
