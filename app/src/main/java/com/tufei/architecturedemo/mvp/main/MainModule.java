package com.tufei.architecturedemo.mvp.main;

import com.tufei.architecturedemo.di.ActivityScoped;
import com.tufei.architecturedemo.di.FragmentScoped;
import com.tufei.architecturedemo.mvp.splash.SplashActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * 谷歌todo-mvp-dagger就是下面这样做的。
 * 1)Activity里的每一个Fragment，要在module里面如下声明。用的是FragmentScoped来标注。
 * 并且Fragment还有用@Inject标注其无参构造方法。
 * 2)Presenter都是用@ActivityScoped来标注。无法使用FragmentScoped来标注，不然你build project就会报错。
 *
 * @author tufei
 * @date 2017/9/12
 */
@Module
public abstract class MainModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract MainFragment mainFragment();

    @ActivityScoped
    @Binds
    abstract MainContract.Presenter mainPresenter(MainPresenter mainPresenter);

    /**
     * 可以模仿下面这样。方便的地方在于，你不需要在{@link MainActivity}像以前一样显式地用
     * "getIntent().getStringExtra(TASK_ID);"这样的代码来获取{@link SplashActivity}传过来的{@link MainActivity#TASK_ID}
     * 所对应的值，甚至当你{@link MainFragment}也需要这个值的时候，你也不用显式地传递这个值给它，只需要使用@Inject来注入。
     * <p>
     * 同理，可以考虑将activity的一些值放在这里，然后，你在{@link MainActivity}、{@link MainFragment}、{@link MainPresenter}
     * 等等地方都用@Inject得到这些值。
     *
     * @param activity
     * @return
     */
    @ActivityScoped
    @Provides
    static String provideTaskId(MainActivity activity) {
        return activity.getIntent().getStringExtra(MainActivity.TASK_ID);
    }

}
