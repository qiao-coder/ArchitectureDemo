package com.tufei.architecturedemo.mvp.face;

import android.view.View;
import android.widget.Toast;

import com.tufei.architecturedemo.R;
import com.tufei.architecturedemo.base.mvp.BaseActivity;

import javax.inject.Inject;

import butterknife.OnClick;

/**
 * @author tufei
 * @date 2017/11/15.
 */
public class FaceActivity extends BaseActivity implements FaceContract.View{
    @Inject
    FaceContract.Presenter mPresenter;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_face;
    }

    @Override
    protected void attachPresenter() {
        attach(mPresenter);
    }

    @Override
    protected void initData() {
        mPresenter.getAccessToken();
    }

    @OnClick({R.id.btn_save_face, R.id.btn_recognize_face, R.id.btn_delete_face})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_save_face:
                mPresenter.saveFace();
                break;
            case R.id.btn_recognize_face:
                mPresenter.recognizeFace();
                break;
            case R.id.btn_delete_face:
                mPresenter.deleteFace();
                break;
            default:
                break;
        }
    }

    @Override
    public void showToast(String tip) {
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
    }
}
