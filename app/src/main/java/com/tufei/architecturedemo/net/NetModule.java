package com.tufei.architecturedemo.net;

import com.tufei.architecturedemo.utils.RetrofitFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * @author tufei
 * @date 2017/9/11
 */
@Module
public class NetModule {

    @Singleton
    @Provides
    Retrofit provideRetrofit() {
        return RetrofitFactory.createRetrofit();
    }

//    @Singleton
//    @Provides
//    @Nullable
//    Retrofit provideRetrofit() {
//        return null;
//    }
}
