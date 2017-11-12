package com.tufei.architecturedemo.mvp.main;

import android.widget.Toast;

import com.tufei.architecturedemo.R;
import com.tufei.architecturedemo.base.mvp.BaseActivity;
import com.tufei.architecturedemo.utils.LogUtil;

import javax.inject.Inject;

/**
 * @author tufei
 * @date 2017/9/12
 */
public class MainActivity extends BaseActivity {

    public static final String TASK_ID = "task_id";
    @Inject
    MainFragment mMainFragment;
    @Inject
    MainContract.Presenter mPresenter;
    @Inject
    String taskId;


    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "mainFragment = " + mMainFragment);
        LogUtil.d(TAG, "mainPresenter = " + mPresenter);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_content, mMainFragment)
                .commit();
        Toast.makeText(this, "我是MainActivity,收到了来自SplashActivity的消息：" + taskId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void attachPresenter() {

    }
}
