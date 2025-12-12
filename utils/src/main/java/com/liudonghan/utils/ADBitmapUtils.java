package com.liudonghan.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.util.Base64;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Description：Bitmap帮助类
 *
 * @author Created by: Li_Min
 * Time:3/18/23
 */
public class ADBitmapUtils {

    private static volatile ADBitmapUtils instance = null;
    private Map<String, Bitmap> mediaVideoPic = new HashMap<>();

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
     * bitmap转为base64
     * @param bitmap bitmap图片引用
     * @return String
     */
    public String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.NO_WRAP);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * todo 根据视频Url获取Bitmap
     *
     * @param videoUrl 视频Url地址
     * @return Bitmap
     */
    public Bitmap findVideoUrlByBitmap(String videoUrl) {
        Bitmap bitmap = mediaVideoPic.get(videoUrl);
        try {
            if (bitmap == null) {
                // 获取预览图
                MediaMetadataRetriever mmr = new MediaMetadataRetriever();
                mmr.setDataSource(videoUrl, new HashMap<>());
                Bitmap previewBitmap = mmr.getFrameAtTime();
                if (null == previewBitmap) {
                    return null;
                }
                // 缩放
                int PREVIEW_VIDEO_IMAGE_HEIGHT = 300; // Pixels
                int videoWidth = Integer.parseInt(Objects.requireNonNull(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH)));
                int videoHeight = Integer.parseInt(Objects.requireNonNull(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT)));
                int videoViewWidth = PREVIEW_VIDEO_IMAGE_HEIGHT * videoWidth / videoHeight;
                if (videoViewWidth == 0) {
                    return null;
                }
                bitmap = Bitmap.createBitmap(previewBitmap);
                mediaVideoPic.put(videoUrl, bitmap);
                mmr.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * todo 根据本地视频文件获取Bitmap
     *
     * @param localVideo 本地视频文件地址
     * @return Bitmap
     */
    public Bitmap findLocalVideoByBitmap(String localVideo) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(localVideo);
            //getFrameAtTime()--->在setDataSource()之后调用此方法。 如果可能，该方法在任何时间位置找到代表性的帧，
            // 并将其作为位图返回。这对于生成输入数据源的缩略图很有用。
            return retriever.getFrameAtTime();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return null;
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

    /**
     * todo 获取Bitmap图片宽高
     *
     * @param filePath 本地文件路径
     * @return int[] 0.宽 1.高
     */
    public static int[] getBitmapWidthHeight(String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 设置只解析图片边界信息而不真正加载图像数据
        // 通过路径加载图片
        BitmapFactory.decodeFile(filePath, options);
        int width = options.outWidth; // 获取图片宽度
        int height = options.outHeight; // 获取图片高度
        return new int[]{width, height};
    }
}
