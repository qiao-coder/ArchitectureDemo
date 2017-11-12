package com.tufei.architecturedemo.base.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tufei.architecturedemo.utils.ActivityCollector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * BaseActivity要继承{@link DaggerAppCompatActivity},而不再是{@link AppCompatActivity}
 * 或者你点开{@link DaggerAppCompatActivity}，把他的不多的代码放到你的BaseActivity里，
 * 继续extends AppCompatActivity(傻吧你？)。
 *
 * @author tufei
 * @date 2017/6/27
 */

public abstract class BaseActivity extends DaggerAppCompatActivity {
    /**
     * TAG封装在了这里..因为懒..
     */
    protected String TAG = getClass().getSimpleName();
    private List<IBasePresenter> presenterList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResourceId());
        //绑定view
        ButterKnife.bind(this);
        ActivityCollector.addActivity(this);
        attachPresenter();
        initData();
    }

    /**
     * 设置布局文件
     *
     * @return
     */
    @LayoutRes
    protected abstract int setLayoutResourceId();

    /**
     * 初始化资源
     */
    protected abstract void initData();

    /**
     * 主要作用：提醒记得绑定Presenter，哈哈哈...
     * 使用{@link #attach(IBasePresenter)}方法进行绑定
     */
    protected abstract void attachPresenter();

    /**
     * presenter绑定view  在activity结束时{@link #onDestroy()},会解除绑定
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
     * presenter解除绑定的view，用于主动解除绑定(如果你想在Activity未摧毁的时候解除绑定)
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
        intent.setClass(this, clazz);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        ActivityCollector.removeActivity(this);
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
        super.onDestroy();
    }

}
