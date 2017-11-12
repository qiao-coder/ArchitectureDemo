package com.tufei.architecturedemo.net;

import com.tufei.architecturedemo.mvp.model.bean.VersionBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

/**
 * @author tufei
 * @date 2017/9/12
 */
interface HttpService {

    /**
     * 获取当前最新的软件版本
     *
     * @return
     */
    @GET("software/upgrade")
    Observable<HttpResult<VersionBean>> getVersion();

    /**
     * 版本更新(下载应该使用Flowable，而不是Observable，避免背压问题)
     *
     * @param path
     * @return
     */
    @Streaming
    @GET("software/upgrade/apk")
    Flowable<ResponseBody> update(@Query("apkPath") String path);
}
