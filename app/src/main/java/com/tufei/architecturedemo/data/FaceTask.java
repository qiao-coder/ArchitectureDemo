package com.tufei.architecturedemo.data;

import com.google.gson.Gson;
import com.tufei.architecturedemo.constants.FaceConstants;
import com.tufei.architecturedemo.data.bean.AccessToken;
import com.tufei.architecturedemo.data.bean.RecognizeResult;
import com.tufei.architecturedemo.data.bean.UserBean;
import com.tufei.architecturedemo.net.FaceHttpResult;
import com.tufei.architecturedemo.net.HttpService;
import com.tufei.architecturedemo.utils.Base64Util;
import com.tufei.architecturedemo.utils.RxUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.FormBody;


/**
 * 关于百度人脸识别api说明，详见：
 *
 * @see <a href="https://cloud.baidu.com/doc/FACE/Face-API.html#.E6.8E.A5.E5.8F.A3.E6.8F.8F.E8.BF.B0"/>
 * 或者 @see <a href="https://ai.baidu.com/docs#/Face-API/top"/>
 * 对于百度的接口返回的json，进行了封装,详见{@link FaceHttpResult}。
 * 注意，不是所有百度返回的json都可以使用这个类。
 * 目前已知人脸识别，人脸对比，人脸保存(更新)，人脸删除的接口，可以使用该封装。获取access_token的接口不可以。
 * <p>
 * 百度的post数据的格式是application/x-www-form-urlencoded,不是我们常用的application/json
 */

/**
 * @author tufei
 * @date 2017/11/15.
 */
public class FaceTask {
    private HttpService mHttpService;
    /**
     * 匹配的最小分数(自定义的)
     */
    private static final int minMatchScore = 75;

    @Inject
    public FaceTask(HttpService httpService) {
        mHttpService = httpService;
    }

    /**
     * 获取使用百度人脸识别时，必须的access_token
     *
     * @return
     */
    public Observable<AccessToken> getAccessToken() {
        //阿里巴巴代码规范：【HashMap】初始化时，尽量指定初始值大小
        //如果暂时无法确定集合大小，那么指定默认值（16）即可。
        Map<String, String> params = new HashMap<>(3);
        params.put(FaceConstants.GRANT_TYPE, FaceConstants.GRANT_TYPE_VALUE);
        params.put(FaceConstants.CLIENT_ID, FaceConstants.CLIENT_ID_VALUE);
        params.put(FaceConstants.CLIENT_SECRET, FaceConstants.CLIENT_SECRET_VALUE);
        return mHttpService.getAccessToken(FaceConstants.ACCESS_TOKEN_URL, params)
                .compose(RxUtil.io_main())
                .doOnNext(accessToken -> FaceConstants.ACCESS_TOKEN_VALUE = accessToken.getAccessToken())
                .retry(3);
    }

    /**
     * 保存人脸
     *
     * @param photoBytes
     * @param userBean
     * @return
     */
    public Observable<FaceHttpResult> saveFace(byte[] photoBytes, UserBean userBean) {
        String json = new Gson().toJson(userBean);
        FormBody body = new FormBody.Builder()
                .add(FaceConstants.ACCESS_TOKEN, FaceConstants.ACCESS_TOKEN_VALUE)
                .add(FaceConstants.GROUP_ID, FaceConstants.GROUP_ID_VALUE)
                .add(FaceConstants.UID, userBean.getId())
                .add(FaceConstants.USER_INFO, json)
                //每次add 如果存在此uid 都替换资料
                .add(FaceConstants.ACTION_TYPE, FaceConstants.ACTION_TYPE_REPLACE)
                .add(FaceConstants.IMAGE, Base64Util.encode(photoBytes))
                .build();
        return mHttpService.saveFace(body)
                .compose(RxUtil.io_main_handleFaceNoData());
    }

    /**
     * 识别人脸
     *
     * @param photoBytes
     * @return
     */
    public Observable<UserBean> recognizeFace(byte[] photoBytes) {
        FormBody body = new FormBody.Builder()
                .add(FaceConstants.ACCESS_TOKEN, FaceConstants.ACCESS_TOKEN_VALUE)
                .add(FaceConstants.GROUP_ID, FaceConstants.GROUP_ID_VALUE)
                .add(FaceConstants.IMAGE, Base64Util.encode(photoBytes))
                .add(FaceConstants.USER_TOP_NUM, FaceConstants.USER_TOP_NUM_VALUE)
                .build();
        return mHttpService.recognizeFace(body)
                .compose(RxUtil.io_main_handleFaceHttpResult())
                .flatMap(this::getUser);
    }

    /**
     * 删除人脸
     *
     * @param id
     * @return
     */
    public Observable<FaceHttpResult> deleteFace(String id) {
        FormBody body = new FormBody.Builder()
                .add(FaceConstants.ACCESS_TOKEN, FaceConstants.ACCESS_TOKEN_VALUE)
                .add(FaceConstants.GROUP_ID, FaceConstants.GROUP_ID_VALUE)
                .add(FaceConstants.UID, id)
                .build();
        return mHttpService.deleteFace(body).
                compose(RxUtil.io_main_handleFaceNoData());
    }

    private Observable<UserBean> getUser(List<RecognizeResult> recognizeResults) {
        return Observable.just(recognizeResults.get(0)).
                flatMap(recognizeResult -> {
                    Double score = recognizeResult.getScores().get(0);
                    UserBean user = new Gson().fromJson(recognizeResult.getUser_info(), UserBean.class);
                    if (score > minMatchScore) {
                        return Observable.just(user);
                    } else {
                        return Observable.error(new Exception("人脸匹配度不够"));
                    }
                });
    }
}
