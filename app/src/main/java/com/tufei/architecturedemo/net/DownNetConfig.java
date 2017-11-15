package com.tufei.architecturedemo.net;

import com.google.gson.Gson;
import com.tufei.architecturedemo.constants.NetConstants;
import com.tufei.architecturedemo.net.download.OnDownListener;
import com.tufei.architecturedemo.net.download.ProgressResponseBody;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 下载文件时，如果需要监听进度，必须另外配置
 *
 * @author tufei
 * @date 2017/7/16
 */

public class DownNetConfig {
    /**
     * 要监听下载进度时，无法使用全局的HttpService
     *
     * @param url
     * @param listener
     * @return
     */
    public static HttpService initNetConfig(String url, OnDownListener listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetConstants.BASE_URL)
                .client(createOkHttpClient(url, listener))
                .addConverterFactory(createGsonConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(HttpService.class);
    }

    /**
     * rxbus要根据视频url区分不同的事件，所以不使用全局的Repository
     *
     * @param url
     * @return
     */
    public static HttpService initNetConfigForRxbus(final String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetConstants.BASE_URL)
                .client(createOkHttpClient(url, null))
                .addConverterFactory(createGsonConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(HttpService.class);
    }

    private static OkHttpClient createOkHttpClient(String url, OnDownListener listener) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(chain -> {
                    Response response = chain.proceed(chain.request());
                    return response.newBuilder().body(new ProgressResponseBody(response.body(), url, listener)).build();
                })
                .connectTimeout(NetConstants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(NetConstants.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .build();
        return client;
    }

    private static GsonConverterFactory createGsonConverterFactory() {
        return GsonConverterFactory.create(new Gson());
    }
}
