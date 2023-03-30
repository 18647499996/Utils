package com.liudonghan.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import static android.telephony.TelephonyManager.NETWORK_TYPE_GSM;
import static android.telephony.TelephonyManager.NETWORK_TYPE_IWLAN;
import static android.telephony.TelephonyManager.NETWORK_TYPE_NR;
import static android.telephony.TelephonyManager.NETWORK_TYPE_TD_SCDMA;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:3/29/23
 */
public class ADNetworkUtils {
    public static final String TAG = "Mac_Liu";
    public static final String ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private OnNetworkUtilsChangeListener onNetworkUtilsChangeListener;

    private static volatile ADNetworkUtils instance = null;

    public ADNetworkUtils() {
    }

    public static ADNetworkUtils getInstance() {
        //single checkout
        if (null == instance) {
            synchronized (ADNetworkUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADNetworkUtils();
                }
            }
        }
        return instance;
    }


    /**
     * 设置网络监听
     *
     * @param context                           上下文
     * @param onNetworkStateUtilsChangeListener 监听回调
     * @return NetworkReceive
     */
    public NetworkReceive setNetworkListener(Context context, OnNetworkUtilsChangeListener onNetworkStateUtilsChangeListener) {
        this.onNetworkUtilsChangeListener = onNetworkStateUtilsChangeListener;
        NetworkReceive networkReceive = new NetworkReceive();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ANDROID_NET_CHANGE_ACTION);
        context.registerReceiver(networkReceive, filter);
        return networkReceive;
    }

    public void unregisterReceiver(Context context, NetworkReceive networkReceive) {
        context.unregisterReceiver(networkReceive);
    }

    public class NetworkReceive extends BroadcastReceiver {


        public NetworkReceive() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                Log.e(TAG, "onReceive: Network Listener error");
                return;
            }
            if (intent.getAction().equals(ANDROID_NET_CHANGE_ACTION)) {
                //获取当前联网的网络类型
                NetworkType netType = getNetworkType(context);
                if (isNetworkAvailable(context)) {
                    Log.i(TAG, "onReceive: Network Listener connect succeed");
                    onNetworkUtilsChangeListener.onConnect(netType);
                } else {
                    Log.e(TAG, "onReceive: Network Listener connect fail");
                    onNetworkUtilsChangeListener.onDisConnect();
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
    public static NetworkType getNetworkType(Context context) {
        NetworkType netType = NetworkType.NETWORK_NO;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connMgr == null) {
            return NetworkType.NETWORK_NO;
        }
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {

            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                netType = NetworkType.NETWORK_WIFI;
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                switch (networkInfo.getSubtype()) {
                    case NETWORK_TYPE_GSM:
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        netType = NetworkType.NETWORK_2G;
                        break;
                    case NETWORK_TYPE_TD_SCDMA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        netType = NetworkType.NETWORK_3G;
                        break;

                    case NETWORK_TYPE_IWLAN:
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        netType = NetworkType.NETWORK_4G;
                        break;
                    case NETWORK_TYPE_NR:
                        netType = NetworkType.NETWORK_5G;
                        break;
                    default:
                        String subtypeName = networkInfo.getSubtypeName();
                        if (subtypeName.equalsIgnoreCase("TD-SCDMA")
                                || subtypeName.equalsIgnoreCase("WCDMA")
                                || subtypeName.equalsIgnoreCase("CDMA2000")) {
                            netType = NetworkType.NETWORK_3G;
                        } else {
                            netType = NetworkType.NETWORK_UNKNOWN;
                        }
                        break;
                }
            } else {
                netType = NetworkType.NETWORK_UNKNOWN;
            }
        }
        return netType;
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
     * 获取IP地址
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.INTERNET"/>}</p>
     *
     * @param useIPv4 是否用IPv4
     * @return IP地址
     */
    public String getIPAddress(final boolean useIPv4) {
        try {
            for (Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces(); nis.hasMoreElements(); ) {
                NetworkInterface ni = nis.nextElement();
                // 防止小米手机返回10.0.2.15
                if (!ni.isUp()) continue;
                for (Enumeration<InetAddress> addresses = ni.getInetAddresses(); addresses.hasMoreElements(); ) {
                    InetAddress inetAddress = addresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String hostAddress = inetAddress.getHostAddress();
                        boolean isIPv4 = hostAddress.indexOf(':') < 0;
                        if (useIPv4) {
                            if (isIPv4) return hostAddress;
                        } else {
                            if (!isIPv4) {
                                int index = hostAddress.indexOf('%');
                                return index < 0 ? hostAddress.toUpperCase() : hostAddress.substring(0, index).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "ip address get fail";
    }

    /**
     * 网络监听状态回调
     */
    public interface OnNetworkUtilsChangeListener {

        /**
         * 已连接
         *
         * @param netType NetType
         */
        void onConnect(NetworkType netType);

        /**
         * 连接断开
         */
        void onDisConnect();
    }

    /**
     * 网络类型枚举
     */
    public enum NetworkType {
        NETWORK_WIFI,
        NETWORK_5G,
        NETWORK_4G,
        NETWORK_3G,
        NETWORK_2G,
        NETWORK_UNKNOWN,
        NETWORK_NO
    }
}