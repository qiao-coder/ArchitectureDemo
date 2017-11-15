package com.tufei.architecturedemo.net;

import com.google.gson.Gson;
import com.tufei.architecturedemo.constants.NetConstants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author tufei
 * @date 2017/9/11
 */

public class RetrofitFactory {

    public static HttpService createRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetConstants.BASE_URL)
                .client(createOkHttpClient())
                .addConverterFactory(createGsonConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofit.create(HttpService.class);
        return  retrofit.create(HttpService.class);
    }

    private static OkHttpClient createOkHttpClient(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Content-Type", "application/json; charset=UTF-8")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .connectTimeout(NetConstants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(NetConstants.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .build();
        return client;
    }

    private static GsonConverterFactory createGsonConverterFactory(){
        return GsonConverterFactory.create(new Gson());
    }
}
