package com.tufei.architecturedemo.utils;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.robolectric.shadows.ShadowLog;

/**
 * @author tufei
 * @date 2018/2/27.
 */
public class RobolectricRule implements TestRule {
    @Override
    public Statement apply(Statement base, Description description) {
        ShadowLog.stream = System.out;
        //RxJava异步代码，变同步，用于测试
        RxJava.asyncToSync();
        return base;
    }
}
