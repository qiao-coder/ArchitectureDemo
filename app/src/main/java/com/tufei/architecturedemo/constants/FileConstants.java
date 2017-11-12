package com.tufei.architecturedemo.constants;

import android.os.Environment;

/**
 *
 * @author tufei
 * @date 2017/6/28
 */

public class FileConstants {
    /**
     * SD卡路径
     */
    public static final String SD_PATH = Environment.getExternalStorageDirectory().getPath();
    /**
     * 存放应用相关文件的主目录
     */
    public static final String FILE_PATH = SD_PATH + "/tufei/";
    /**
     * apk存放的目录
     */
    public static final String APK_PATH = FILE_PATH+"apk/";
    /**
     * apk名称
     */
    public static final String APK_NAME = "tufei.apk";
}
