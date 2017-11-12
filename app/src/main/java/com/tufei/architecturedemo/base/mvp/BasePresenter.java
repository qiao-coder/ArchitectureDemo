package com.tufei.architecturedemo.base.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author tufei
 * @date 2017/7/29
 */

public class BasePresenter {
    protected String TAG = getClass().getSimpleName();
    private CompositeDisposable mCompositeDisposable;

    /**
     * 把取消订阅的disposable存储起来
     *
     * @param disposable
     */
    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * 取消所有存储的订阅
     */
    public void clearDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
    }

    public void onDetachView() {
        //取消所有存储的订阅
        clearDisposable();
    }

}
