package com.tufei.architecturedemo.data;

import com.tufei.architecturedemo.net.HttpService;
import com.tufei.architecturedemo.utils.LogUtil;

import javax.inject.Inject;

/**
 * 登陆登出
 * @author tufei
 * @date 2017/9/12
 */
public class LoginTask {
    private static final String TAG = "LoginTask";
    private HttpService mHttpService;

    @Inject
    public LoginTask(HttpService httpService) {
        mHttpService = httpService;
        LogUtil.d(TAG,"mHttpService = "+httpService);
    }
}
