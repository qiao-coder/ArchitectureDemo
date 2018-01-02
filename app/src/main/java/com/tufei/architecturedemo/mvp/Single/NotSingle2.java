package com.tufei.architecturedemo.mvp.Single;

import javax.inject.Inject;

/**
 * 这里少了@Singleton，你会发现日志输出里，NotSingle2没有实现全局单例
 * @author tufei
 * @date 2017/12/7.
 */
public class NotSingle2 {
    @Inject
    public NotSingle2(){

    }
}
