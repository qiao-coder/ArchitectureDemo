package com.tufei.architecturedemo.net;

import com.tufei.architecturedemo.di.AppComponent;

import dagger.Module;
import dagger.Provides;

/**
 * @author tufei
 * @date 2017/9/11
 */
@Module
public class NetModule {

    /**
     * 如果是通过@Provides提供实例，要实现单例，只需要在此标注@Singleton
     * 当然，{@link AppComponent}里，也要标注上@Singleton
     */
    @Provides
    HttpService provideHttpService() {
        return RetrofitFactory.createHttpService();
    }

//    @Singleton
//    @Provides
//    @Nullable
//    HttpService provideHttpService() {
//        return null;
//    }
}
