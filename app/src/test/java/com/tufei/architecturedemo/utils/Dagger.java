package com.tufei.architecturedemo.utils;


import com.tufei.architecturedemo.di.AppComponent;
import com.tufei.architecturedemo.di.DaggerAppComponent;

import org.robolectric.RuntimeEnvironment;

/**
 * @author tufei
 * @date 2017/11/15.
 */

public class Dagger {
    public static AppComponent getAppComponent(){
        AppComponent appComponent = DaggerAppComponent.builder().application(RuntimeEnvironment.application).build();
        return appComponent;
    }
}
