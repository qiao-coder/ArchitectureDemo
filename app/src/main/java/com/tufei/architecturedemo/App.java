package com.tufei.architecturedemo;

import com.tufei.architecturedemo.di.AppComponent;
import com.tufei.architecturedemo.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * 我们创建一个定制的{@link App}类，继承自{@link DaggerApplication}。
 * 然后覆盖{@link DaggerApplication#applicationInjector()}方法，告诉Dagger如何为我们创建一个单例的Component。
 * 我们完全不用调用`component.inject(this)`，因为{@link DaggerApplication}将会帮我们完成这些操作。
 *
 * We create a custom {@link App} class that extends  {@link DaggerApplication}.
 * We then override applicationInjector() which tells Dagger how to make our @Singleton Component
 * We never have to call `component.inject(this)` as {@link DaggerApplication} will do that for us.
 *
 * @author tufei
 * @date 2017/9/11
 */

public class App extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}
