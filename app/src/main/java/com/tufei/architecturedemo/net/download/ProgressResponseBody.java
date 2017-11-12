package com.tufei.architecturedemo.net.download;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 *
 * @author tufei
 * @date 2017/7/11
 */

public class ProgressResponseBody extends ResponseBody {
    private static final String TAG = "ProgressResponseBody";
    private final ResponseBody responseBody;
    private OnDownListener listener;
    private BufferedSource bufferedSource;
    private String url;

    public ProgressResponseBody(ResponseBody responseBody, String url) {
        this.responseBody = responseBody;
        this.url = url;
    }

    public ProgressResponseBody(ResponseBody responseBody, String url, OnDownListener listener) {
        this.responseBody = responseBody;
        this.url = url;
        this.listener = listener;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (null == bufferedSource) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long bytesReaded = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                bytesReaded += bytesRead != -1 ? bytesRead : 0;
                //实时发送当前已读取(上传/下载)的字节
                if (listener != null) {
                    listener.onDown(bytesReaded,
                            responseBody.contentLength(),
                            bytesRead == -1 && bytesRead == responseBody.contentLength(),
                            url);
                }
//                RxBus.getInstance().post(url,
//                        new DownLoadEvent(bytesReaded, responseBody.contentLength(),
//                                bytesRead == -1 && bytesRead == responseBody.contentLength()));

                return bytesRead;
            }
        };
    }
}
