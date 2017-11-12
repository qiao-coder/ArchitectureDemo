package com.tufei.architecturedemo.net.download;

/**
 *
 * @author tufei
 * @date 2017/9/12
 */

public interface OnDownListener {
    void onDown(long bytesLoaded, long total, boolean done, String url);
}
