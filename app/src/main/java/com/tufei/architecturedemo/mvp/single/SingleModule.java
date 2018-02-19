package com.tufei.architecturedemo.mvp.single;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author tufei
 * @date 2017/12/7.
 */
@Module
public class SingleModule {
    @Singleton
    @Provides
    Single1 provideSingle1(){
        return new Single1();
    }

    /**
     * 少了@Singleton,无法实现单例
     * @return
     */
    @Provides
    NotSingle1 provideNotSingle1(){
        return new NotSingle1();
    }

}
