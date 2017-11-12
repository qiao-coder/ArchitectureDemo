package com.tufei.architecturedemo.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * 这是一个Dagger module。我们使用它来将我们的Applicatin类作为一个Context绑定给AppComponent。
 * 通过使用Dagger Android 我们不需要传递我们的Application实例给任何module。
 * 在这里，我们只需要简单地显示我们的Application作为一个Context。
 * Dagger.Android的优点之一，就是将你的Application和Activities提供给你的图表。(graph,中文为图表，这样子翻译合适吗？)
 *
 * This is a Dagger module. We use this to bind our Application class as a Context in the AppComponent
 * By using Dagger Android we do not need to pass our Application instance to any module,
 * we simply need to expose our Application as Context.
 * One of the advantages of Dagger.Android is that your
 * Application & Activities are provided into your graph for you.
 * {@link
 * AppComponent}.
 */
@Module
public abstract class ApplicationModule {
    //expose Application as an injectable context
    @Binds
    abstract Context bindContext(Application application);
}

