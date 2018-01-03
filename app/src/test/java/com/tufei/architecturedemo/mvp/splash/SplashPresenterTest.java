package com.tufei.architecturedemo.mvp.splash;

import com.tufei.architecturedemo.BuildConfig;
import com.tufei.architecturedemo.data.VersionTask;
import com.tufei.architecturedemo.data.bean.VersionBean;
import com.tufei.architecturedemo.utils.AppUtil;
import com.tufei.architecturedemo.utils.Log;
import com.tufei.architecturedemo.utils.RxJava;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author tufei
 * @date 2017/11/17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
//必须写如下代码 让PowerMock 忽略Robolectric的所有注入 如果还要使用https 必须加上"javax.net.ssl.*"，忽略ssl 不然可以省略
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*", "javax.net.ssl.*"})
@PrepareForTest(AppUtil.class)
public class SplashPresenterTest {

    @Mock
    SplashActivity splashActivity;
    @Mock
    VersionTask versionTask;
    private SplashPresenter mSplashPresenter;

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(AppUtil.class);
        //Robolectric日志输出(能输出代码块里面的log)
        Log.out();
        //RxJava异步代码，变同步，用于测试
        RxJava.asyncToSync();
        mSplashPresenter = new SplashPresenter(versionTask, RuntimeEnvironment.application);
        mSplashPresenter.onAttachView(splashActivity);
    }

    @Test
    public void checkUpdate_noUpdate() throws Exception {
        VersionBean versionBean = new VersionBean();
        versionBean.setVersion("1.0");
        versionBean.setPath("www.baidu.com");
        when(versionTask.getVersion()).thenReturn(Observable.just(versionBean));
        when(AppUtil.getVersion(any())).thenReturn("1.0");
        mSplashPresenter.checkUpdate();
        verify(splashActivity).showMainActivity();
    }

    @Test
    public void checkUpdate_update() throws Exception {
        VersionBean versionBean = new VersionBean();
        versionBean.setVersion("1.1");
        versionBean.setPath("www.baidu.com");
        when(versionTask.getVersion()).thenReturn(Observable.just(versionBean));
        when(AppUtil.getVersion(any())).thenReturn("1.0");
        mSplashPresenter.checkUpdate();
        verify(splashActivity).showUpdateTipDialog(versionBean.getPath());
    }

}