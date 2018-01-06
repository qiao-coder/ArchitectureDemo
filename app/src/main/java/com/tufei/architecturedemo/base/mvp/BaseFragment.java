package com.tufei.architecturedemo.base.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

/**
 * BaseFragment要继承{@link DaggerFragment},而不再是{@link android.support.v4.app.Fragment}
 * <p>
 * 1)如果你要使用的是{@link android.support.v4.app.Fragment}，那就继承{@link DaggerFragment}
 * 2)如果你要使用的是{@link android.app.Fragment}，那就继承{@link dagger.android.DaggerFragment}
 * 3)或者你两者都要用..那就点开上述两个类，把它们的代码都拷贝过来放在你的BaseFragment就是了..
 *
 * @author tufei
 * @date 2017/6/27
 */

public abstract class BaseFragment extends DaggerFragment {
    /**
     * TAG封装在了这里..因为懒..
     */
    protected String TAG = getClass().getSimpleName();
    protected View mRootView;
    Unbinder unbinder;
    private List<IBasePresenter> presenterList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayoutResourceId(), container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        attachPresenter();
        initData();
        return mRootView;
    }

    /**
     * 设置布局文件
     *
     * @return
     */
    @LayoutRes
    protected abstract int setLayoutResourceId();

    /**
     * 初始化视图
     */
    protected abstract void initData();

    /**
     * 主要作用：提醒记得绑定Presenter，哈哈哈...
     * 使用{@link #attach(IBasePresenter)}方法进行绑定
     */
    protected abstract void attachPresenter();

    /**
     * presenter绑定view  在Fragment视图被销毁时{@link #onDestroyView()},会解除绑定
     *
     * @param t
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public <T extends IBasePresenter> void attach(T t) {
        if (!(this instanceof IBaseView)) {
            throw new RuntimeException(this.getClass().getSimpleName() +
                    "didn't implement the interface for View that base on IBaseView!");
        }
        //也考虑了  一个view同时绑定多个presenter的情况  (つд⊂)其实基本用不到
        if (presenterList == null) {
            presenterList = new ArrayList<>();
        }
        presenterList.add(t);
        t.onAttachView((IBaseView) this);
    }

    /**
     * presenter解除绑定的view，用于主动解除绑定(如果你想在Fragment视图未摧毁的时候解除绑定)
     *
     * @param t
     * @param <T>
     */
    public <T extends IBasePresenter> void detach(T t) {
        presenterList.remove(t);
        t.onDetachView();
    }

    /**
     * 界面跳转，不需要添加额外信息的时候(一切为了偷懒)
     *
     * @param clazz 要跳转的界面
     */
    public void startActivity(@NonNull Class<?> clazz) {
        Intent intent = new Intent();
        intent.setClass(getContext(), clazz);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        //执行presenter里的onDetachView方法
        if (presenterList != null && presenterList.size() > 0) {
            Iterator<IBasePresenter> iterator = presenterList.iterator();
            if (iterator.hasNext()) {
                IBasePresenter presenter = iterator.next();
                presenter.onDetachView();
                iterator.remove();
            }
        }
        //销毁顺序与初始化顺序相反，所以，放在最后面
        super.onDestroyView();
    }
}
