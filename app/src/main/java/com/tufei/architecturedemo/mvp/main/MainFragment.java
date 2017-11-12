package com.tufei.architecturedemo.mvp.main;

import android.view.View;
import android.widget.TextView;

import com.tufei.architecturedemo.R;
import com.tufei.architecturedemo.base.mvp.BaseFragment;
import com.tufei.architecturedemo.mvp.EmptyActivity;
import com.tufei.architecturedemo.utils.LogUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author tufei
 * @date 2017/9/12
 */
public class MainFragment extends BaseFragment implements MainContract.View {
    @Inject
    MainContract.Presenter mPresenter;
    @Inject
    String taskId;
    @BindView(R.id.tv_text)
    TextView mTvText;

    @Inject
    public MainFragment() {
        //需要用@Inject标注一个public的无参构造函数,不然无法正常注入到Activity里面
        // Requires empty public constructor
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void attachPresenter() {
        attach(mPresenter);
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "mainPresenter = " + mPresenter);
        LogUtil.d(TAG, "taskId = " + taskId);
        mTvText.setText("我是MainFragment，收到了来自SplashActivity的消息：\n" + taskId);
    }

    @OnClick({R.id.btn_jump})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_jump:
                startActivity(EmptyActivity.class);
                break;
            default:
                break;
        }
    }
}
