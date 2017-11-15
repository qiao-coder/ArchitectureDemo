package com.tufei.architecturedemo.mvp.face;

import com.tufei.architecturedemo.base.mvp.IBasePresenter;
import com.tufei.architecturedemo.base.mvp.IBaseView;

/**
 * @author tufei
 * @date 2017/9/11
 */

public interface FaceContract {
    interface View extends IBaseView {
        void showToast(String tip);
    }

    interface Presenter extends IBasePresenter<View> {

        void saveFace();

        void recognizeFace();

        void deleteFace();

        void getAccessToken();
    }
}
