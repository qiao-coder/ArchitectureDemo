package com.tufei.architecturedemo.di;

import android.app.Application;

import com.tufei.architecturedemo.App;
import com.tufei.architecturedemo.mvp.single.SingleModule;
import com.tufei.architecturedemo.net.HttpService;
import com.tufei.architecturedemo.net.NetModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * 尽管Dagger允许标注一个{@link Component}为单例，但代码它自身必须确保只有一个类的实例被创建。
 * 不过这个操作已经在{@link App}里面完成了。
 *
 * Even though Dagger allows annotating a {@link Component} as a singleton, the code
 * itself must ensure only one instance of the class is created. This is done in {@link App}.
 *
 * {@link AndroidSupportInjectionModule}是必须的，这个module是来自Dagger.Android，
 * 用来帮助生成和定位SupComponents(子组件)
 *
 * {@link AndroidSupportInjectionModule}
 * is the module from Dagger.Android that helps with the generation
 * and location of subcomponents.
 *
 * @author tufei
 * @date 2017/9/11
 */
@Singleton
@Component(modules = {SingleModule.class,
        NetModule.class,
        ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<App> {

    HttpService httpService();

    /**
     * 必须写的模板代码。
     * 给予我们语法糖，让我们可以做DaggerAppComponent.builder().application(this).build().inject(this);
     * 永远不必实例化任何module，或者说我们将会传递application给那些module。
     * Application将会提供给我们的app。
     *
     * Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
     * never having to instantiate any modules or say which module we are passing the application to.
     * Application will just be provided into our app graph now.
     */
    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
