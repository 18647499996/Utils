package com.liudonghan.utils;

import android.content.Context;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:1/10/22
 */
public class SnackbarUtils {
    private static volatile SnackbarUtils instance = null;

    private SnackbarUtils() {
    }

    public static SnackbarUtils getInstance() {
        //single chcekout
        if (null == instance) {
            synchronized (SnackbarUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new SnackbarUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 显示悬浮提示框（ 异常 ）
     *
     * @param context 上下文
     * @param msg     提示语
     */
    public void showError(Context context, String msg) {
        show(context, msg, R.drawable.corners_bg_bar_error);
    }

    /**
     * 显示悬浮提示框（ 成功）
     *
     * @param context 上下文
     * @param msg     提示语
     */
    public void showSucceed(Context context, String msg) {
        show(context, msg, R.drawable.corners_bg_bar_succeed);
    }

    /**
     * 显示悬浮提示框（ 警告 ）
     *
     * @param context 上下文
     * @param msg     提示语
     */
    public void showWarn(Context context, String msg) {
        show(context, msg, R.drawable.corners_bg_bar_warn);
    }

    /**
     * 显示悬浮提示框
     *
     * @param context 上下文
     * @param msg     提示语
     * @param resId   设置背景
     */
    public void show(Context context, String msg, int resId) {
        show(context,msg,resId,15,220);
    }

    /**
     * 显示悬浮提示框
     *
     * @param context 上下文
     * @param msg     提示语
     * @param resId   设置背景
     */
    public void show(Context context, String msg, int resId, int marginLeft, int marginTop) {
        show(context,msg,resId,marginLeft,marginTop, Snackbar.SnackbarPosition.TOP);
    }

    /**
     * 显示悬浮提示框
     *
     * @param context 上下文
     * @param msg     提示语
     * @param resId   设置背景
     */
    public void show(Context context, String msg, int resId, int marginLeft, int marginTop, Snackbar.SnackbarPosition position) {
        if (null == context){
            return;
        }
        SnackbarManager.show(Snackbar
                        .with(context)
                        .position(position)
                        .duration(1000)
                        .margin(marginLeft, marginTop)
                        .backgroundDrawable(resId)
                        .text(msg));
    }
}
