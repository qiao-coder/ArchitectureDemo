package com.tufei.architecturedemo.mvp.model;

import com.tufei.architecturedemo.net.NetRepository;
import com.tufei.architecturedemo.utils.LogUtil;

import javax.inject.Inject;

/**
 * 登陆登出
 * @author tufei
 * @date 2017/9/12
 */
public class LoginTask {
    private static final String TAG = "LoginTask";
    private NetRepository mNetRepository;

    @Inject
    public LoginTask(NetRepository netRepository) {
        mNetRepository = netRepository;
        LogUtil.d(TAG,"netRepository = "+netRepository);
    }
}
