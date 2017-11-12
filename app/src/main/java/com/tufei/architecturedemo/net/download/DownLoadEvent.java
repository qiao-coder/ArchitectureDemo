package com.tufei.architecturedemo.net.download;

/**
 * 文件加载事件
 * @author tufei
 */
public class DownLoadEvent {

    long total;
    long bytesLoaded;
    boolean done;

    public long getBytesLoaded() {
        return bytesLoaded;
    }

    public long getTotal() {
        return total;
    }

    public boolean getDone() {
        return done;
    }
    /**
     * 文件加载事件的构造函数.
     *
     * @param bytesLoaded 已加载文件的大小
     * @param total       文件总大小
     * @param done        是否完成
     */
    public DownLoadEvent(long bytesLoaded, long total, boolean done ) {
        this.total = total;
        this.bytesLoaded = bytesLoaded;
        this.done = done;
    }
}
