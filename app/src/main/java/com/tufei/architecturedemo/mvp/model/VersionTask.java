package com.tufei.architecturedemo.mvp.model;

import com.tufei.architecturedemo.mvp.model.bean.VersionBean;
import com.tufei.architecturedemo.net.DownNetConfig;
import com.tufei.architecturedemo.net.NetRepository;
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
    private NetRepository mNetRepository;

    @Inject
    public VersionTask(NetRepository netRepository) {
        LogUtil.d(TAG, "netRepository = " + netRepository);
        mNetRepository = netRepository;
    }


    public Observable<VersionBean> getVersion() {
        return mNetRepository.getVersion()
                .compose(RxUtil.io_main_handleHttpResult());
    }

    public Flowable<ResponseBody> update(String path, OnDownListener listener) {
        NetRepository repository = DownNetConfig.initNetConfig(path, listener);
        return repository.update(path);
    }
}
