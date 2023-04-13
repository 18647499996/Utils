package com.liudonghan.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

/**
 * Description：屏幕工具
 *
 * @author Created by: Li_Min
 * Time:4/13/23
 */
public class ADScreenUtils {

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

    @SuppressLint("ObsoleteSdkInt")
    public static Point getScreenSize(Context context) {
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
}
