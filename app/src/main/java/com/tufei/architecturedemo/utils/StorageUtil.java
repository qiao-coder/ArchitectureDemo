package com.tufei.architecturedemo.utils;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;

import com.google.gson.Gson;
import com.tufei.architecturedemo.App;

import java.io.File;

/**
 * Created by wjc on 2017/12/28. App应用目录管理
 */
@SuppressWarnings("unused")
public class StorageUtil {
    /**
     * 判断是否是文件路径
     */
    public static boolean isFilePath(String path) {
        return new File(path).isDirectory();
    }

    /**
     * 获取应用的根目录
     */
    public static String getRoot() {
        String rootPath = App.getAppContext().getAppRootPath();
        return Environment.getExternalStorageDirectory() + "/" + rootPath + "/";
    }

    /**
     * 在应用根路径下创建文件夹（可以多级）
     */
    public static String create(@NonNull String dirName) {
        return createDir(getRoot() + dirName);
    }

    /**
     * 在某个路径下创建文件夹（可以多级）
     */
    public static String create(@NonNull String root, @NonNull String dirName) {
        return createDir(root + dirName);
    }

    private static String createDir(String path) {
        File dir = new File(path);
        if (!dir.exists() && dir.mkdirs()) {
            return path;
        }
        return path;
    }

    /**
     * 存储对象到SharePreferences文件中
     */
    public static void saveObjectToFile(String key, Object obj, boolean isEncrypt) {
        SharedPreferences sp = Utils.getDefaultSharePreferences();
        //本地化账户信息
        String json = new Gson().toJson(obj);
        if (isEncrypt) {
            json = Base64.encodeToString(json.getBytes(), Base64.DEFAULT);
        }
        sp.edit().putString(key, json).apply();
    }

    /**
     * 从SharePreferences文件中获取对象
     */
    public static Object parseObjectFromFile(String key, Class clazz, boolean isEncrypt) {
        SharedPreferences sp = Utils.getDefaultSharePreferences();
        String json = sp.getString(key, null);
        if (TextUtils.isEmpty(json)) return null;

        if (isEncrypt) {
            json = new String(Base64.decode(json.getBytes(), Base64.DEFAULT));
        }
        return new Gson().fromJson(json, clazz);
    }
}
