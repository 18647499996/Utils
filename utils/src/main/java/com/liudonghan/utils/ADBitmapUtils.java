package com.liudonghan.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Description：Bitmap帮助类
 *
 * @author Created by: Li_Min
 * Time:3/18/23
 */
public class ADBitmapUtils {

    private static volatile ADBitmapUtils instance = null;

    private ADBitmapUtils() {
    }

    public static ADBitmapUtils getInstance() {
        //single chcekout
        if (null == instance) {
            synchronized (ADBitmapUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADBitmapUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 分享二维码图片base64编码
     *
     * @param base64 二维码图片base64字符串
     * @return Bitmap
     */
    public Bitmap getQrCodeBase64(String base64) {
        String str = base64.substring(22);
        byte[] decodes = android.util.Base64.decode(str, 0);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = false;
        //为true时，返回的bitmap为null
        return BitmapFactory.decodeByteArray(decodes, 0, decodes.length, opts);
    }
}
