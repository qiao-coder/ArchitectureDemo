package com.tufei.architecturedemo.data;

import com.tufei.architecturedemo.data.bean.UserBean;
import com.tufei.architecturedemo.utils.BaseTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author tufei
 * @date 2017/11/15.
 */
public class FaceTaskTest extends BaseTest {

    private FaceTask mFaceTask;
    private String ID = "1";
    private String NAME = "古天乐";
    private String EVALUATION = "只有太阳才能黑的男人。";
    private String FACE_FILE_NAME = "gutianle.jpg";

    private String NO_MATCH_FACE_FILE_NAME = "wuyanzu.jpg";
    private String NO_FACE_FILE_NAME = "noface.png";

    @Before
    public void setup() {
        mFaceTask = new FaceTask(mHttpService);
        mFaceTask.getAccessToken().test();
    }

    @After
    public void cleanup() {
        mFaceTask.deleteFace(ID).test();
    }

    @Test
    public void testGetAccessToken() throws Exception {
        mFaceTask.getAccessToken()
                .test()
                .assertNoErrors();
    }

    @Test
    public void testSaveFace() throws Exception {
        byte[] bytes = fileToBytes(FACE_FILE_NAME);
        UserBean userBean = new UserBean(ID, NAME, EVALUATION);
        mFaceTask.saveFace(bytes, userBean)
                .test()
                .assertNoErrors();
    }

    @Test
    public void testRecognizeFace_haveFace() throws Exception {
        byte[] bytes = fileToBytes(FACE_FILE_NAME);

        UserBean userBean = new UserBean(ID, NAME, EVALUATION);
        mFaceTask.saveFace(bytes, userBean)
                .test()
                .assertNoErrors();
        //人脸注册完毕后，生效时间一般为5s以内，之后便可以进行识别或认证操作。
        Thread.sleep(2000);

        mFaceTask.recognizeFace(bytes)
                .test()
                .assertNoErrors();
    }
    @Test
    public void testRecognizeFace_noFace() throws Exception {
        byte[] bytes = fileToBytes(FACE_FILE_NAME);

        UserBean userBean = new UserBean(ID, NAME, EVALUATION);
        mFaceTask.saveFace(bytes, userBean)
                .test()
                .assertNoErrors();

        Thread.sleep(2000);

        byte[] realBytes = fileToBytes(NO_FACE_FILE_NAME);
        mFaceTask.recognizeFace(realBytes)
                .test()
                .assertError(Exception.class);
    }
    @Test
    public void testRecognizeFace_noMatchFace() throws Exception {

        byte[] bytes = fileToBytes(FACE_FILE_NAME);
        UserBean userBean = new UserBean(ID, NAME, EVALUATION);
        mFaceTask.saveFace(bytes, userBean)
                .test()
                .assertNoErrors();

        Thread.sleep(2000);

        byte[] realBytes = fileToBytes(NO_MATCH_FACE_FILE_NAME);
        mFaceTask.recognizeFace(realBytes)
                .test()
                .assertError(Exception.class);
    }

    @Test
    public void testDeleteFace() throws Exception {
        mFaceTask.deleteFace(ID)
                .test()
                .assertNoErrors();
    }

    private byte[] fileToBytes(String fileName) throws IOException{
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        int len;
        byte[] bytes;
        len = inputStream.available();
        bytes = new byte[len];
        inputStream.read(bytes, 0, len);
        inputStream.close();
        return bytes;
    }

}