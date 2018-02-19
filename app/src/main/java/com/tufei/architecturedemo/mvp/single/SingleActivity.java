package com.tufei.architecturedemo.mvp.single;

import android.widget.TextView;

import com.tufei.architecturedemo.R;
import com.tufei.architecturedemo.base.mvp.BaseActivity;
import com.tufei.architecturedemo.utils.LogUtil;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * dagger的全局单例两种实现方式：
 * 1.通过在Module{@link SingleModule}里@Provides{@link SingleModule#provideSingle1()}来直接提供单例，在方法上标注@Singleton。
 * 反面教材：{@link SingleModule#provideNotSingle1()}
 * 2.通过在构造方法{@link Single2#Single2()}来提供，需要在类名上方标注@Singleton
 * 反面教材：{@link NotSingle2#NotSingle2()}
 *
 * @author tufei
 */
public class SingleActivity extends BaseActivity {
    @BindView(R.id.tv_log)
    TextView mTvLog;
    @Inject
    Single1 mSingle1_1;
    @Inject
    Single1 mSingle1_2;
    @Inject
    Single2 mSingle2_1;
    @Inject
    Single2 mSingle2_2;
    @Inject
    NotSingle1 mNotSingle1_1;
    @Inject
    NotSingle1 mNotSingle1_2;
    @Inject
    NotSingle2 mNotSingle2_1;
    @Inject
    NotSingle2 mNotSingle2_2;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_single;
    }

    @Override
    protected void initData() {
        LogUtil.d(TAG, "mSingle1_1 = " + mSingle1_1);
        LogUtil.d(TAG, "mSingle1_2 = " + mSingle1_2);
        LogUtil.d(TAG, "mSingle2_1 = " + mSingle2_1);
        LogUtil.d(TAG, "mSingle2_2 = " + mSingle2_2);
        LogUtil.d(TAG, "NotSingle1_1 = " + mNotSingle1_1);
        LogUtil.d(TAG, "NotSingle1_2 = " + mNotSingle1_2);
        LogUtil.d(TAG, "NotSingle2_1 = " + mNotSingle2_1);
        LogUtil.d(TAG, "NotSingle2_2 = " + mNotSingle2_2);
        mTvLog.setText("mSingle1_1 = " + mSingle1_1+"\n"+
                "mSingle1_2 = " + mSingle1_2+"\n"+
                "mSingle2_1 = " + mSingle2_1+"\n"+
                "mSingle2_2 = " + mSingle2_2+"\n"+
                "NotSingle1_1 = " + mNotSingle1_1+"\n"+
                "NotSingle1_2 = " + mNotSingle1_2+"\n"+
                "NotSingle2_1 = " + mNotSingle2_1+"\n"+
                "NotSingle2_2 = " + mNotSingle2_2);
    }

    @Override
    protected void attachPresenter() {

    }
}
