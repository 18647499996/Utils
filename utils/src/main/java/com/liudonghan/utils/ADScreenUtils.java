package com.liudonghan.utils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import java.util.Objects;

/**
 * Description：屏幕工具
 *
 * @author Created by: Li_Min
 * Time:4/13/23
 */
public class ADScreenUtils {

    private static final String TAG = "Mac_Liu";
    private Context context;
    private ContentResolver contentResolver;
    private OnContentObserverUtilsListener onContentObserverUtilsListener;

    private static volatile ADScreenUtils instance = null;

    private ADScreenUtils() {
    }

    public static ADScreenUtils getInstance() {
        //single checkout
        if (null == instance) {
            synchronized (ADScreenUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADScreenUtils();
                }
            }
        }
        return instance;
    }

    /**
     * todo 获取当前屏幕大小
     *
     * @param context 上下文引用
     * @return Point
     */
    @SuppressLint("ObsoleteSdkInt")
    public Point getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point out = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(out);
        } else {
            int width = display.getWidth();
            int height = display.getHeight();
            out.set(width, height);
        }
        return out;
    }

    /**
     * todo 获取当前屏幕大小
     *
     * @param window 当前窗体
     * @return Point
     */
    public Point getScreenSize(Window window) {
        if (window == null) {
            return null;
        }
        Point point = new Point();
        DisplayMetrics dm = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        } else {
            window.getWindowManager().getDefaultDisplay().getMetrics(dm);
        }
        point.set(dm.widthPixels, dm.heightPixels);
        return point;
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
                Log.i(TAG, "ContentObserver Listener onChange：" + selfChange + "     ------  " + uri.getPath());
                ContentResolver contentResolver = context.getContentResolver();
                Cursor query = contentResolver.query(uri, null, null, null, MediaStore.Video.VideoColumns.DATE_ADDED + " desc");
                if (null != query) {
                    while (query.moveToNext()) {
                        @SuppressLint("Range") String filePath = query.getString(query.getColumnIndex(MediaStore.Video.VideoColumns.DATA));
                        Log.i(TAG, "File Path：" + filePath + "   ~~~~~~~~~~~~ " + filePath.contains(".tmp"));
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
