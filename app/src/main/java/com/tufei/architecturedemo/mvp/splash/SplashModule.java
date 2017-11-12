package com.tufei.architecturedemo.mvp.splash;

import com.tufei.architecturedemo.di.ActivityScoped;

import dagger.Binds;
import dagger.Module;

/**
 * 这是一个Dagger module。我们用它自动创建SplashSubComponent,并绑定{@link SplashPresenter}到图表。
 * This is a Dagger module. We use this to auto create the AdEditTaskSubComponent and bind
 * the {@link SplashPresenter} to the graph
 *
 * @author tufei
 * @date 2017/9/11
 */

@Module
public abstract class SplashModule {

    @ActivityScoped
    @Binds
    abstract SplashContract.Presenter splashPresenter(SplashPresenter splashPresenter);

}
