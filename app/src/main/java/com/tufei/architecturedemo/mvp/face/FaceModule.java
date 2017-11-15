package com.tufei.architecturedemo.mvp.face;

import com.tufei.architecturedemo.di.ActivityScoped;

import dagger.Binds;
import dagger.Module;

/**
 * @author tufei
 * @date 2017/9/15
 */
@Module
public abstract class FaceModule {

    @ActivityScoped
    @Binds
    abstract FaceContract.Presenter facePresenter(FacePresenter facePresenter);

}
