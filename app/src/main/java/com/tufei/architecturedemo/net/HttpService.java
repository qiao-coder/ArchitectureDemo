package com.tufei.architecturedemo.net;

import com.tufei.architecturedemo.mvp.model.bean.AccessToken;
import com.tufei.architecturedemo.mvp.model.bean.RecognizeResult;
import com.tufei.architecturedemo.mvp.model.bean.VersionBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.FormBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @author tufei
 * @date 2017/9/12
 */
public interface HttpService {

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

    /**
     * 获取使用百度人脸识别时，必须的access_token
     * 当BaseUrl不适用时，用@Url标注,传一个完整的url即可
     *
     * @param accessTokenUrl
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<AccessToken> getAccessToken(@Url String accessTokenUrl, @FieldMap Map<String, String> params);


    /**
     * 保存人脸到百度
     *
     * @return
     * @param body
     */
    @POST("faceset/user/add")
    Observable<FaceHttpResult> saveFace(@Body FormBody body);

    /**
     * 识别人脸
     *
     * @param photoBytes
     * @return
     */
    @POST("identify")
    Observable<FaceHttpResult<List<RecognizeResult>>> recognizeFace(@Body FormBody photoBytes);

    /**
     * 删除人脸
     *
     * @param body
     * @return
     */
    @POST("faceset/user/delete")
    Observable<FaceHttpResult> deleteFace(@Body FormBody body);

}
