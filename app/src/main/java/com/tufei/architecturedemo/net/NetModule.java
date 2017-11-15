package com.tufei.architecturedemo.net;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author tufei
 * @date 2017/9/11
 */
@Module
public class NetModule {

    @Singleton
    @Provides
    HttpService provideRetrofit() {
        return RetrofitFactory.createRetrofit();
    }

//    @Singleton
//    @Provides
//    @Nullable
//    Retrofit provideRetrofit() {
//        return null;
//    }
}
