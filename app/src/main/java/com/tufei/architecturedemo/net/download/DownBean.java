package com.tufei.architecturedemo.net.download;

/**
 * @author tufei
 * @date 2017/7/14
 */

public class DownBean {
    /**
     * 下载的文件的地址
     */
    private String url;
    /**
     * 文件总长度
     */
    private long countLength;
    /**
     * 已下载长度
     */
    private long readedLength;
    /**
     * 文件名
     */
    private String fileName;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getCountLength() {
        return countLength;
    }

    public void setCountLength(long countLength) {
        this.countLength = countLength;
    }

    public long getReadedLength() {
        return readedLength;
    }

    public void setReadedLength(long readedLength) {
        this.readedLength = readedLength;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
