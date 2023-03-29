package com.liudonghan.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:3/29/23
 */
public class ADNetworkStateUtils extends BroadcastReceiver {
    private static final String TAG = "Mac_Liu";
    public static final String ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    /**
     * 网络类型
     */
    private NetType netType;
    private OnNetworkStateUtilsChangeListener onNetworkStateUtilsChangeListener;


    private static volatile ADNetworkStateUtils instance = null;

    public ADNetworkStateUtils() {
        //初始化网络连接状态
        this.netType = NetType.NONE;
    }

    public static ADNetworkStateUtils getInstance() {
        //single checkout
        if (null == instance) {
            synchronized (ADNetworkStateUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADNetworkStateUtils();
                }
            }
        }
        return instance;
    }

    public void setNetworkStateListener(Context context, OnNetworkStateUtilsChangeListener onNetworkStateUtilsChangeListener) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ANDROID_NET_CHANGE_ACTION);
        context.registerReceiver(instance, filter);
        this.onNetworkStateUtilsChangeListener = onNetworkStateUtilsChangeListener;
    }

    public void unregisterReceiver(Context context) {
        context.unregisterReceiver(instance);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            Log.e(TAG, "onReceive: Network Listener error");
            return;
        }
        if (intent.getAction().equals(ANDROID_NET_CHANGE_ACTION)) {
            Log.i(TAG, "onReceive: Network Listener change");
            //获取当前联网的网络类型
            netType = getCurrentNetWorkType(context);
            if (isNetworkAvailable(context)) {
                Log.i(TAG, "onReceive: Network Listener connect succeed");
                if (onNetworkStateUtilsChangeListener != null) {
                    onNetworkStateUtilsChangeListener.onConnect(netType);
                }
            } else {
                Log.e(TAG, "onReceive: Network Listener connect fail");
                if (onNetworkStateUtilsChangeListener != null) {
                    onNetworkStateUtilsChangeListener.onDisConnect();
                }
            }
        }
    }

    /**
     * 获取当前网络类型
     *
     * @param context 上下文
     * @return NetType
     */
    public NetType getCurrentNetWorkType(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connMgr == null) {
            return NetType.NONE;
        }
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null) {
            return NetType.NONE;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            if ("cmnet".equals(networkInfo.getExtraInfo().toLowerCase())) {
                return NetType.CMNET;
            } else {
                return NetType.CMWAP;
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            return NetType.WIFI;
        }
        return NetType.NONE;
    }

    /**
     * 网络是否可用
     *
     * @param context 上下文
     * @return true 可用 false 不可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connMgr == null) {
            return false;
        }
        NetworkInfo[] infos = connMgr.getAllNetworkInfo();
        if (infos != null) {
            for (NetworkInfo info : infos) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 网络监听状态回调
     */
    public interface OnNetworkStateUtilsChangeListener {
        /**
         * 已连接
         *
         * @param netType NetType
         */
        void onConnect(NetType netType);

        /**
         * 连接断开
         */
        void onDisConnect();
    }

    /**
     * 网络类型枚举
     */
    public enum NetType {
        //任意网络
        AUTO,
        //WIFI
        WIFI,
        CMNET,
        //手机上网
        CMWAP,
        //无网络
        NONE
    }
}