package com.liudonghan.utils;

import android.os.Handler;

/**
 * ============================================================
 * <p/>
 * 版 权 ： 诺易(北京)科技服务有限公司
 * <p/>
 * 作 者 ： Li_Min
 * <p/>
 * 版 本 ： 2.0
 * <p/>
 * 创建日期 ：2016/12/14
 * <p/>
 * 描 述 ：Handler工具类
 * <p/>
 * 修订历史 ：
 * <p/>
 * ============================================================
 **/
public class ADHandlerUtils {

    public Handler handler = new Handler();
    public Runnable runnable = null;
    public static final long MILLIS = 1000;


    private static volatile ADHandlerUtils instance = null;

    private ADHandlerUtils() {
    }

    public static ADHandlerUtils getInstance() {
        //single chcekout
        if (null == instance) {
            synchronized (ADHandlerUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADHandlerUtils();
                }
            }
        }
        return instance;
    }

    /**
     * post延迟发送
     *
     * @param runnable 延迟接口
     * @param millis   延迟时间
     */
    public void delayExecute(Runnable runnable, long millis) {
        this.runnable = runnable;
        handler.postDelayed(runnable, millis);
    }

    /**
     * 清除handler引用
     */
    public void clearHandler() {
        if (runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }
}
