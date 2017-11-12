package com.tufei.architecturedemo.mvp.main;

import com.tufei.architecturedemo.base.mvp.IBasePresenter;
import com.tufei.architecturedemo.base.mvp.IBaseView;

/**
 * @author tufei
 * @date 2017/9/12
 */

public interface MainContract {

    interface View extends IBaseView {

    }

    interface Presenter extends IBasePresenter<View> {

    }
}
