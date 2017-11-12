package com.tufei.architecturedemo.base.mvp;

/**
 * @author tufei
 * @date 2017/6/29
 */

public interface IBasePresenter<T extends IBaseView> {

    void onAttachView(T view);

    void onDetachView();
}
