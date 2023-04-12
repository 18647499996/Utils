package com.liudonghan.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

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

    /**
     * 获取视频封面图
     *
     * @param videoFilePath 视频文件路径
     * @return String
     */
    public String getVideoCoverUrl(String videoFilePath) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(videoFilePath);
            //getFrameAtTime()--->在setDataSource()之后调用此方法。 如果可能，该方法在任何时间位置找到代表性的帧，
            // 并将其作为位图返回。这对于生成输入数据源的缩略图很有用。
            Bitmap bitmap = retriever.getFrameAtTime();
            return saveBitmap(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), Objects.requireNonNull(bitmap));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return "";
    }

    /**
     * 获取视频时长
     *
     * @param videoFilePath 视频文件路径
     * @return long
     */
    public long getVideoDuration(String videoFilePath) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(videoFilePath);
            String duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            return Long.parseLong(Objects.requireNonNull(duration));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return 0;
    }

    /**
     * 保存图片
     *
     * @param filePath 文件路径
     * @param bitmap   bitmap图片
     * @return String
     */
    public static String saveBitmap(String filePath, Bitmap bitmap) {
        String jpegName = filePath + "/AD_" + System.currentTimeMillis() + ".jpg";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(jpegName);
            BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
            return jpegName;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
