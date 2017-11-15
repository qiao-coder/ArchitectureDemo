package com.tufei.architecturedemo.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author tufei
 * @date 2017/11/15.
 */

public class FileUtil {
    public static byte[] getAssetsFile(Context context, String fileName) {
        InputStream asset;
        byte[] buffer = null;
        try {
            asset = context.getAssets().open(fileName);
            int len = asset.available();
            buffer = new byte[len];
            asset.read(buffer);
            asset.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

}
