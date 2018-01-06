package com.tufei.architecturedemo.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;


import java.security.MessageDigest;
import java.util.Random;

/**
 * Created by wjc on 2017/12/28. 系统工具类
 */
@SuppressWarnings("unused")
public class SystemUtil {

    /**
     * 关闭软键盘
     */
    public static void closeInputMethod(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive())
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) Utils.getContext().getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }

    /**
     * 获取应用的版本名称
     */
    public static String getVersionName() {
        try {
            PackageManager manager = Utils.getContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(Utils.getContext().getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
//            Logger.e(e.getMessage());
            return "1.0.0";
        }
    }

    /**
     * 获取应用的内部版本号
     */
    public static int getVersionCode() {
        try {
            PackageManager manager = Utils.getContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(Utils.getContext().getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
//            Logger.e(e.getMessage());
            return 1;
        }
    }

    /**
     * md5加密
     */
    public static String md5(String info) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();
            StringBuilder strBuf = new StringBuilder();
            for (byte anEncryption : encryption) {
                if (Integer.toHexString(0xff & anEncryption).length() == 1) {
                    strBuf.append("0").append(Integer.toHexString(0xff & anEncryption));
                } else {
                    strBuf.append(Integer.toHexString(0xff & anEncryption));
                }
            }
            return strBuf.toString();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取临时文件的文件名称
     */
    public static String getTempFileName() {
        char[] str = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6',
                '7', '8', '9'};
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() <= 32) {
            sb.append(str[random.nextInt(str.length)]);
        }
        return sb.toString();
    }
}
