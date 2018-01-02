package com.tufei.architecturedemo.mvp.face;

import android.content.Context;

import com.tufei.architecturedemo.base.mvp.BasePresenter;
import com.tufei.architecturedemo.data.FaceTask;
import com.tufei.architecturedemo.data.bean.UserBean;
import com.tufei.architecturedemo.utils.FileUtil;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author tufei
 * @date 2017/11/15.
 */

public class FacePresenter extends BasePresenter implements FaceContract.Presenter {
    private FaceContract.View mView;
    private FaceTask mFaceTask;
    private Context mContext;
    private String ID = "1";
    private String NAME = "古天乐";
    private String EVALUATION = "只有太阳才能黑的男人。";
    private String FILENAME = "gutianle.jpg";

    @Inject
    FacePresenter(FaceTask faceTask, Context context) {
        mFaceTask = faceTask;
        mContext = context;
    }

    @Override
    public void onAttachView(FaceContract.View view) {
        mView = view;
    }

    @Override
    public void getAccessToken() {
        Disposable disposable = mFaceTask.getAccessToken()
                .subscribe(accessToken -> {
                        },
                        throwable ->
                                mView.showToast("获取access_token失败，失败原因：" + throwable.getMessage()));
        addDisposable(disposable);
    }

    @Override
    public void saveFace() {
        byte[] bytes = FileUtil.getAssetsFile(mContext, FILENAME);
        UserBean userBean = new UserBean(ID, NAME, EVALUATION);
        addDisposable(
                mFaceTask
                        .saveFace(bytes, userBean)
                        .subscribe(httpResult -> mView.showToast("保存人脸成功"),
                                throwable -> mView.showToast("保存人脸失败，失败原因："
                                        + throwable.getMessage())));
    }

    @Override
    public void recognizeFace() {
        byte[] bytes = FileUtil.getAssetsFile(mContext, FILENAME);
        Disposable disposable = mFaceTask
                .recognizeFace(bytes)
                .subscribe(userBean ->
                                mView.showToast("识别人脸成功!\n姓名：" +
                                        userBean.getName() + "\n评价：" + userBean.getEvaluation()),
                        throwable ->
                                mView.showToast("识别人脸失败,失败原因："+throwable.getMessage()));
        addDisposable(disposable);
    }

    @Override
    public void deleteFace() {
        Disposable disposable = mFaceTask.deleteFace(ID)
                .subscribe(httpResult -> mView.showToast("删除人脸成功"),
                        throwable -> mView.showToast("删除人脸失败，失败原因：" + throwable.getMessage()));
        addDisposable(disposable);
    }

}
