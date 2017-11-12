package com.tufei.architecturedemo.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * activity管理类
 *
 * @author tufei
 * @date 2017/1/16
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 获取栈顶的activity
     * @return
     */
    public static Activity getCurrentActivity(){
       Activity currentActivity = activities.get(activities.size() - 1);
        return currentActivity;
    }
}
