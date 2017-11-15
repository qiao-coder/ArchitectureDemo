package com.tufei.architecturedemo.mvp.model;

import com.tufei.architecturedemo.mvp.model.bean.VersionBean;
import com.tufei.architecturedemo.net.DownNetConfig;
import com.tufei.architecturedemo.net.HttpService;
import com.tufei.architecturedemo.net.download.OnDownListener;
import com.tufei.architecturedemo.utils.LogUtil;
import com.tufei.architecturedemo.utils.RxUtil;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 *
 * @author tufei
 * @date 2017/9/11
 */
public class VersionTask {
    private static final String TAG = "VersionTask";
    private HttpService mHttpService;

    @Inject
    public VersionTask(HttpService httpService) {
        LogUtil.d(TAG, "mHttpService = " + httpService);
        mHttpService = httpService;
    }


    public Observable<VersionBean> getVersion() {
        return mHttpService.getVersion()
                .compose(RxUtil.io_main_handleHttpResult());
    }

    public Flowable<ResponseBody> update(String path, OnDownListener listener) {
        HttpService httpService = DownNetConfig.initNetConfig(path, listener);
        return httpService.update(path);
    }
}
