package com.liudonghan.utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.io.File;

/**
 * 拍照、选择照片
 *
 * @author Administrator
 */
public class ADPicturePhotoUtils {

    private Context mContext;
    /**
     * isCrop 是否裁剪
     * isSpuare 是否正方形裁剪边框
     */
    private boolean isCrop = false, isSquare = true;
    private static final String TAG = "Mac_Liu";

    /**
     * 判断是否是拍照，拍照时true，相册是FALSE
     */
    private boolean photoFlag = false;

    /**
     * 调用系统拍照、图片浏览、图片裁剪状态
     */
    private static final int VIDEO_CAPTURE = 0;
    private static final int TAKE_CAMERA = 1;
    private static final int FETCH_PHOTO = 2;
    private static final int CROP_PHOTO = 3;

    /**
     * 返回图片的URI
     */
    private Uri mUri = null;
    /**
     * 图片默认保存路径
     */
    private File mFile;

    /**
     * 从相册中选择图片
     */
    private File sdcardTempFile;

    private ADImageFileCallback adImageFileCallback;

    /**
     * 获取图片的回调接口
     */
    public interface ADImageFileCallback {

        void handleResult(File file);

    }

    private Uri getPictureUri() {
        return mUri;
    }


    private static volatile ADPicturePhotoUtils instance = null;

    private ADPicturePhotoUtils() {
    }

    public static ADPicturePhotoUtils getInstance() {
        //single chcekout
        if (null == instance) {
            synchronized (ADPicturePhotoUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADPicturePhotoUtils();
                }
            }
        }
        return instance;
    }


    /**
     * 初始化相册、拍照选择工具
     *
     * @param context 上下文
     */
    public ADPicturePhotoUtils init(Context context) {
        init(context, new Config(false, true, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath(), "AD_" + System.currentTimeMillis()));
        return this;
    }

    /**
     * 回调方法
     *
     * @param adImageFileCallback 回调函数
     */
    public void onCallBack(ADImageFileCallback adImageFileCallback) {
        this.adImageFileCallback = adImageFileCallback;
    }

    /**
     * 初始化相册、拍照选择工具
     *
     * @param context 上下文
     * @param config  配置属性
     */
    public ADPicturePhotoUtils init(Context context, Config config) {
        this.mContext = context;
        this.isCrop = config.isCrop();
        this.isSquare = config.isSquare();
        this.mFile = new File(config.getFilePath(), config.getFileName());
        this.sdcardTempFile = new File(config.getFilePath(), config.getFileName() + ".jpg");
        Log.i(TAG, "config path：" + mFile.getAbsolutePath());
        return this;
    }

    /**
     * 拍照
     */
    public void takePicture() {
        photoFlag = true;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 以下程序是采用相机拍照，拍照后的图片存放在相册中，采用ContentValues方式保存的是拍照后的原图， 其它方式会不太清晰
        ContentValues values = new ContentValues();
        mUri = mContext.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        if (isCrop) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri.fromFile(mFile));
        }
        ((Activity) mContext).startActivityForResult(intent, TAKE_CAMERA);
    }

    /**
     * 录像的方法
     */
    public void videotape() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        adImageFileCallback.handleResult(mFile);
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 7);//限制录制时间10秒
        ((Activity) mContext).startActivityForResult(intent, VIDEO_CAPTURE);
    }

    /**
     * 相册
     */
    public void getPicture() {
        photoFlag = false;

        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
        } else {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        ((Activity) mContext).startActivityForResult(intent, FETCH_PHOTO);
    }

    /**
     * 传递裁剪参数
     *
     * @param uri 图片URI
     */
    public void cropImageUri(Uri uri, File file) {
        if (null == file) {
            Log.i(TAG, "启动相机异常");
            return;
        }

        Intent intent = new Intent("com.android.camera.action.CROP");

        intent.setDataAndType(uri, "image/*");
        // 裁切信号
        intent.putExtra("crop", "true");
        if (isSquare) {
            //android api大于28
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                // 裁切框 的宽高比
                intent.putExtra("aspectX", 3);
                intent.putExtra("aspectY", 2);
            } else {
                // 裁切框 的宽高比
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
            }

        } else {
            // 裁切框 的宽高比
            intent.putExtra("aspectX", 5);
            intent.putExtra("aspectY", 2);
        }
        // 自动缩放
        intent.putExtra("scale", true);
        // 裁切后图片的输出格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        // 不启用面部识别
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));

        ((Activity) mContext).startActivityForResult(intent, CROP_PHOTO);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Activity.RESULT_OK == resultCode) {
            Log.i(TAG, "onActivityResult is Ok");
            Uri photoUri = null;
            switch (requestCode) {
                case TAKE_CAMERA:
                    if (isCrop) {
                        photoUri = getPictureUri();
                        cropImageUri(photoUri, mFile);
                    } else {
                        adImageFileCallback.handleResult(mFile);
                    }
                    break;
                case FETCH_PHOTO:
                    if (data != null && data.getData() != null) {
                        if (isCrop) {
                            Log.i(TAG, "Crop Image File Path：" + data.getData().toString());
                            cropImageUri(data.getData(), sdcardTempFile);
                        } else {
                            sendPicByUri(data.getData());
                        }
                    }
                    break;
                case CROP_PHOTO:
                    if (sdcardTempFile != null && !photoFlag) {
                        Log.i(TAG, "print SDCard TempFile:" + sdcardTempFile.getAbsolutePath());
                        adImageFileCallback.handleResult(sdcardTempFile);
                    } else {
                        Log.i(TAG, "print File:" + mFile.getAbsolutePath());
                        adImageFileCallback.handleResult(mFile);
                    }
                    break;
            }
        }
    }

    /**
     * 不剪切图片
     *
     * @param selectedImage 选择图片
     */
    public void sendPicByUri(Uri selectedImage) {
        Cursor cursor = mContext.getContentResolver().query(selectedImage, null, null, null, null);
        String st8 = "找不到图片";
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex("_data");
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            cursor = null;

            if (picturePath == null || picturePath.equals("null")) {
                Toast toast = Toast.makeText(mContext, st8, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                return;
            }
            sdcardTempFile = new File(picturePath);
            adImageFileCallback.handleResult(sdcardTempFile);
        } else {
            File file = new File(selectedImage.getPath());
            if (!file.exists()) {
                Toast toast = Toast.makeText(mContext, st8, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                return;

            }
            adImageFileCallback.handleResult(file);
        }

    }

    public static class Config {

        private boolean isCrop = false;

        private boolean isSquare = false;

        private String filePath;

        private String fileName;


        public Config(boolean isCrop, boolean isSquare, String filePath, String fileName) {
            this.isCrop = isCrop;
            this.isSquare = isSquare;
            this.filePath = filePath;
            this.fileName = fileName;
        }

        public boolean isCrop() {
            return isCrop;
        }

        public void setCrop(boolean crop) {
            isCrop = crop;
        }

        public boolean isSquare() {
            return isSquare;
        }

        public void setSquare(boolean square) {
            isSquare = square;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
    }
}
