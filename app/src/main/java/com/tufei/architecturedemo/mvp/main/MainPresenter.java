package com.tufei.architecturedemo.mvp.main;

import com.tufei.architecturedemo.mvp.model.LoginTask;
import com.tufei.architecturedemo.utils.LogUtil;

import javax.inject.Inject;

/**
 * 从UI{@link MainFragment}里监听用户的操作，当需要的时候接收数据并更新UI。
 * <p/>
 * 通过使用{@code @Inject}标记构造函数，Dagger会注入所需的依赖项
 * 创建一个MainPresenter的实例(如果失败，编译器会报错)。它是通过使用{@link MainModule}这样做的。
 * <p/>
 * 匕首生成的代码不需要公开访问构造函数或类，因此，为了确保开发人员不手动实例化该类，并且保证Dagger可使用构造函数，
 * 尽可能地最小化类/构造函数的可见性是很好的做法。(只要不是private就行，所以类/构造函数一般使用无修饰符，类加final)
 * <p>
 * Listens to user actions from the UI ({@link MainFragment}), retrieves the data and updates
 * the UI as required.
 * <p/>
 * By marking the constructor with {@code @Inject}, Dagger injects the dependencies required to
 * create an instance of the StatisticsPresenter (if it fails, it emits a compiler error). It uses
 * {@link MainModule} to do so.
 * <p/>
 * Dagger generated code doesn't require public access to the constructor or class, and
 * therefore, to ensure the developer doesn't instantiate the class manually and bypasses Dagger,
 * it's good practice minimise the visibility of the class/constructor as much as possible.
 *
 * @author tufei
 * @date 2017/9/12
 */

/**
 * 这里很奇怪。谷歌自己的todo-mvp-dagger有些presenter有加这个，有些没加。
 * 以前我测的时候，如果这里没有标注@ActivityScoped。
 * 你会发现，{@link MainActivity#mPresenter}和{@link MainFragment#mPresenter}
 * 不是同一个实例。也就是你想要在MainActivity的范围内，实现单例，办不到。
 * 但是...现在不会了...
 *
 */
//@ActivityScoped
final class MainPresenter implements MainContract.Presenter {
    private static final String TAG = "MainPresenter";
    private LoginTask mLoginTask;
    @Inject
    String taskId;

    @Inject
    MainPresenter(LoginTask loginTask) {
        mLoginTask = loginTask;
        //打印结果：taskId = null  原来  还没初始化啊  打印个鸡毛
        LogUtil.d(TAG, "taskId = " + taskId);
    }

    @Override
    public void onAttachView(MainContract.View view) {
        //正常打印出"taskId = 你好，我是SplashActivity!"
        LogUtil.d(TAG, "taskId = " + taskId);
    }

    @Override
    public void onDetachView() {

    }
}
