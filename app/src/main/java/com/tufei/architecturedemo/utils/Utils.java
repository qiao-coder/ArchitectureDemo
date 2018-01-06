package com.tufei.architecturedemo.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;


/**
 * Created by wjc on 2017/12/28. Utils初始化相关
 */
@SuppressWarnings("unused")
public final class Utils {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    private static boolean debug;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull Context context) {
        init(context, false);
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     * @param debug   调试模式 默认-false
     */
    public static void init(@NonNull Context context, boolean debug) {
        Utils.mContext = context.getApplicationContext();
        Utils.debug = debug;
//        if (debug) Logger.init().methodCount(1).hideThreadInfo();
    }

    /**
     * 获取AppContext
     *
     * @return AppContext
     */
    public static Context getContext() {
        if (mContext != null) return mContext;
        throw new NullPointerException("u should init first");
    }

    /**
     * 判断是否在Debug模式
     *
     * @return true | false
     */
    public static boolean isDebug() {
        return debug;
    }

    /**
     * 获取默认的SharedPreferences
     */
    public static SharedPreferences getDefaultSharePreferences() {
        return PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    /**
     * 获取App名称
     *
     * @return App名称
     */
    public static String getAppName() {
        try {
            PackageManager pm = Utils.getContext().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), 0);
            return pi == null ? null : pi.applicationInfo.loadLabel(pm).toString();
        } catch (PackageManager.NameNotFoundException e) {
//            Logger.e(e.getMessage());
            return null;
        }
    }
}