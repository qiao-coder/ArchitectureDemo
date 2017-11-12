package com.tufei.architecturedemo.net;

import com.tufei.architecturedemo.mvp.model.bean.VersionBean;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * 这里的@Singleton不能去掉。不然NetRepository无法保证全局单例。
 * @author tufei
 * @date 2017/6/28
 */
@Singleton
public class NetRepository {
    private HttpService httpService;

    @Inject
    NetRepository(Retrofit retrofit) {
        httpService = retrofit.create(HttpService.class);
    }

    /**
     * 注意，这里的返回值是Observable<HttpResult<VersionTask>>，而不是Observable<VersionTask>
     * @return
     */
    public Observable<HttpResult<VersionBean>> getVersion() {
        return httpService.getVersion();
    }

    public Flowable<ResponseBody> update(String path) {
        return httpService.update(path);
    }
}
