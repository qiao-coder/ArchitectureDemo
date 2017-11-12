package com.tufei.architecturedemo.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * @author tufei
 * @date 2017/7/11
 */

public class AppUtil {

    public static String getVersion(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "获取App版本号失败";
        }
        return packageInfo.versionName;
    }

}
