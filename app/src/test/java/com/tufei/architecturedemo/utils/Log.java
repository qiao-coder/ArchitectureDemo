package com.tufei.architecturedemo.utils;

import org.robolectric.shadows.ShadowLog;

/**
 * @author tufei
 * @date 2017/11/15.
 */

public class Log {
    public static void out(){
        ShadowLog.stream = System.out;
    }
}
