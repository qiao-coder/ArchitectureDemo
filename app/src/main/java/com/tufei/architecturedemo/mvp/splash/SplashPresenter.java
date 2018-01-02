package com.tufei.architecturedemo.mvp.splash;


import android.content.Context;

import com.tufei.architecturedemo.base.mvp.BasePresenter;
import com.tufei.architecturedemo.data.VersionTask;
import com.tufei.architecturedemo.di.ActivityScoped;
import com.tufei.architecturedemo.net.NetModule;
import com.tufei.architecturedemo.utils.AppUtil;
import com.tufei.architecturedemo.utils.LogUtil;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * 从UI{@link SplashActivity}里监听用户的操作，当需要的时候接收数据并更新UI。
 * <p/>
 * 通过使用{@code @Inject}标记构造函数，Dagger会注入所需的依赖项
 * 创建一个SplashPresenter的实例(如果失败，编译器会报错)。它是通过使用{@link SplashModule}这样做的。
 * <p/>
 * 匕首生成的代码不需要公开访问构造函数或类，因此，为了确保开发人员不手动实例化该类，并且保证Dagger可使用构造函数，
 * 尽可能地最小化类/构造函数的可见性是很好的做法。(只要不是private就行，所以一般使用无修饰符)
 * <p>
 * <p>
 * Listens to user actions from the UI ({@link SplashActivity}), retrieves the data and updates
 * the UI as required.
 * <p/>
 * By marking the constructor with {@code @Inject}, Dagger injects the dependencies required to
 * create an instance of the StatisticsPresenter (if it fails, it emits a compiler error). It uses
 * {@link SplashModule} to do so.
 * <p/>
 * Dagger generated code doesn't require public access to the constructor or class, and
 * therefore, to ensure the developer doesn't instantiate the class manually and bypasses Dagger,
 * it's good practice minimise the visibility of the class/constructor as much as possible.
 *
 * @author tufei
 * @date 2017/9/12
 */
@ActivityScoped
final class SplashPresenter extends BasePresenter implements SplashContract.Presenter {

    private VersionTask mVersionTask;
    private Context mContext;
    private SplashContract.View mView;

    /**
     * 如果参数没有被标注为@Nullable，Dagger就会认为它就是@NonNull。如果你传了null进来，运行时，会抛空指针。
     * <p>
     * 那么问题来了，如果我非要传一个@Nullable的参数呢？
     * 1)构造方法里，这个参数一定要加@Nullable；
     * 2)module里，相关参数的提供方法也要用@Nullable标注，参考{@link NetModule}末尾被注释的代码。
     * <p>
     * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
     * with {@code @Nullable} values.
     */
    @Inject
    SplashPresenter(VersionTask versionTask, Context context) {
        mVersionTask = versionTask;
        mContext = context;
    }

    @Override
    public void onAttachView(SplashContract.View view) {
        mView = view;
    }

    /**
     * 使用RxJava的时候，一定一定要用lambda来使用。以前我不懂lambda，一直用的observer，后来，才发现自己有多傻逼。
     * 学lambda的基本用法，花不了半天时间的。
     */
    @Override
    public void checkUpdate() {
        Disposable disposable = mVersionTask
                .getVersion()
                .subscribe(
                        version -> {
                            LogUtil.d(TAG, "查询版本信息成功。");
                            if (version.getVersion().equals(AppUtil.getVersion(mContext))) {
                                mView.showMainActivity();
                            } else {
                                update(version.getPath());
                            }
                        },
                        throwable -> {
                            LogUtil.d(TAG, "查询版本信息失败，失败原因：" + throwable.getMessage());
                            mView.showToast("查询版本信息失败!");
                        }
                );
        /**
         *注意，每一个异步任务，都要把调用{@link BasePresenter#addDisposable(Disposable)}方法，
         * 存储相关的disposable
         *
         * 好处是：我们永远不用担心，由于activity/fragment被摧毁后了，异步任务才完成，
         * 这时候，调用activity的相关方法，会抛空指针异常。
         * 因为，只要你调用了{@link BasePresenter#addDisposable(Disposable)}方法，存储相关的disposable，
         * 在activity/fragment被摧毁时，
         * {@link BasePresenter#onDetachView()} 方法会被调用，取消所有异步任务。
         *
         * 有趣的是，你可以翻阅谷歌的todo-mvp和todo-mvp-dagger，还有todo-mvp-rxjava，这三个分支，
         * 他们对这种异步任务的处理都不一样。但有异曲同工之妙。
         */
        addDisposable(disposable);
    }

    /**
     * 更新(rxjava2+retrofit2实现下载更新监听是比较麻烦的)
     *
     * @param path
     */
    private void update(String path) {
        Disposable disposable = mVersionTask
                .update(path, (bytesLoaded, total, done, url) -> {
                    //下载进度
                    LogUtil.d(TAG, "progress = " + (int) (bytesLoaded * 100 / total));
                    mView.showDownProgress((int) (bytesLoaded * 100 / total));
                })
                .subscribe(responseBody -> {
                            LogUtil.d(TAG, "下载成功");
                            mView.showMainActivity();
                        },
                        throwable -> {
                            LogUtil.e(TAG, "下载失败，失败原因：" + throwable.getMessage());
                            mView.showToast("下载失败");
                            mView.showMainActivity();
                        }
                );
        addDisposable(disposable);
    }
}
