package com.tufei.architecturedemo.di;

import com.tufei.architecturedemo.mvp.EmptyActivity;
import com.tufei.architecturedemo.mvp.main.MainActivity;
import com.tufei.architecturedemo.mvp.main.MainModule;
import com.tufei.architecturedemo.mvp.splash.SplashActivity;
import com.tufei.architecturedemo.mvp.splash.SplashModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 我们想要Dagger.Android去创建一个SubComponent,
 * 一个拥有一个父Component(ActivityBindingModule所在模块的父Component，也就是AppComponent)的SubComponent。
 * 这里最精彩最美妙的地方就是，你不需要再告诉AppComponent它将会拥有哪些SubComponent。
 * 你也不需要告诉那些SubComponent，关于AppComponent的存在。
 * 我们也会告诉Dagger.Android，这个生成的SubComponent需要包含指定的模块，并且要注意一个范围注解@ActivityScoped。
 * 当Dagger.Android注解处理器运行时，它会为我们创建3个SubComponent。
 * <p>
 * We want Dagger.Android to create a SubComponent which has a parent Component of whichever module ActivityBindingModule is on,
 * in our case that will be AppComponent. The beautiful part about this setup is that you never need to tell AppComponent that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that AppComponent exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the specified modules and be aware of a scope annotation @ActivityScoped
 * When Dagger.Android annotation processor runs it will create 3 subcomponents for us.
 *
 * @author tufei
 */
@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = SplashModule.class)
    abstract SplashActivity splashActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity mainActivity();

    /**
     * 如果你的Activity不需要任何Module，即Activity简单到不需要注入任何东西的时候，也要在这里声明它
     * 除非你的Activity不再是继承自{@link dagger.android.support.DaggerAppCompatActivity}
     *
     * @return
     */
    @ActivityScoped
    @ContributesAndroidInjector()
    abstract EmptyActivity emptyActivity();
}
