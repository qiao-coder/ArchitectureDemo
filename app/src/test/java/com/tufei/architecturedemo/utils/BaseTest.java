package com.tufei.architecturedemo.utils;

import android.content.Context;

import com.tufei.architecturedemo.net.HttpService;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

/**
 * @author tufei
 * @date 2017/11/15.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, sdk = 23)
public class BaseTest {

    protected HttpService mHttpService;
    protected Context mContext;

    /**
     * 子类千万别有方法跟它同名，免得将其覆盖。这个会第一个调用。
     */
    @Before
    public void setSetSet() {
        //Robolectric日志输出(能输出代码块里面的log)
        Log.out();
        //RxJava异步代码，变同步，用于测试
        RxJava.asyncToSync();
        //全局Context
        mContext = RuntimeEnvironment.application;
        //使用全局配置的Retrofit，主要为了看日志输出
        mHttpService = Dagger.getAppComponent().httpService();
    }
}
