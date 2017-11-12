package com.tufei.architecturedemo.mvp.splash;

import com.tufei.architecturedemo.base.mvp.IBasePresenter;
import com.tufei.architecturedemo.base.mvp.IBaseView;

/**
 * @author tufei
 * @date 2017/9/11
 */

public interface SplashContract {
    interface View extends IBaseView {
        void showToast(String tip);

        void showMainActivity();

        void showDownProgress(int progress);
    }

    interface Presenter extends IBasePresenter<View> {
        void checkUpdate();
    }
}
