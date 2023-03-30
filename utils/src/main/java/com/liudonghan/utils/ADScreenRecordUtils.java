package com.liudonghan.utils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Objects;

/**
 * Description：ContentResolver获取临时文件
 *
 * @author Created by: Li_Min
 * Time:1/13/23
 */
public class ADScreenRecordUtils {

    private static final String TAG = "Mac_Liu";
    private Context context;
    private ContentResolver contentResolver;
    private OnContentObserverUtilsListener onContentObserverUtilsListener;

    private static volatile ADScreenRecordUtils instance = null;

    private ADScreenRecordUtils() {
    }

    public static ADScreenRecordUtils getInstance() {
        //single chcekout
        if (null == instance) {
            synchronized (ADScreenRecordUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADScreenRecordUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 注册ContentResolver监听
     *
     * @param context 上下文
     */
    public void register(Context context, OnContentObserverUtilsListener onContentObserverUtilsListener) {
        this.context = context;
        this.contentResolver = context.getContentResolver();
        this.onContentObserverUtilsListener = onContentObserverUtilsListener;
        contentResolver.registerContentObserver(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, true, observer);
    }

    public ContentObserver observer = new ContentObserver(new Handler(Objects.requireNonNull(Looper.myLooper()))) {
        @Override
        public void onChange(boolean selfChange, @Nullable Uri uri) {
            super.onChange(selfChange);
            if (null != uri) {
                Log.d(TAG, "ContentObserver Listener onChange：" + selfChange + "     ------  " + uri.getPath());
                ContentResolver contentResolver = context.getContentResolver();
                Cursor query = contentResolver.query(uri, null, null, null, MediaStore.Video.VideoColumns.DATE_ADDED + " desc");
                if (null != query) {
                    while (query.moveToNext()) {
                        @SuppressLint("Range") String filePath = query.getString(query.getColumnIndex(MediaStore.Video.VideoColumns.DATA));
                        Log.d(TAG, "File Path：" + filePath + "   ~~~~~~~~~~~~ " + filePath.contains(".tmp"));
                        if (null != onContentObserverUtilsListener) {
                            onContentObserverUtilsListener.isScreenRecord(filePath.contains(".tmp"));
                        }
                    }
                    query.close();
                }
            }
        }
    };

    /**
     * 注销
     */
    public void destroy() {
        contentResolver.unregisterContentObserver(observer);
        contentResolver = null;
        context = null;
    }

    public interface OnContentObserverUtilsListener {

        /**
         * 监听是否录屏
         *
         * @param isScreenRecord true 正在录屏 false 没有录屏
         */
        void isScreenRecord(boolean isScreenRecord);
    }
}