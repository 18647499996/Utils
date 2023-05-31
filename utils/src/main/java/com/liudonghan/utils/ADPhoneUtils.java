package com.liudonghan.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:5/31/23
 */
public class ADPhoneUtils {
    private static volatile ADPhoneUtils instance = null;

    private ADPhoneUtils() {
    }

    public static ADPhoneUtils getInstance() {
        //single checkout
        if (null == instance) {
            synchronized (ADPhoneUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADPhoneUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 获取运行商名称
     * 46000 中国移动（ GSM ）
     * 46001 中国联通（ GSM ）
     * 46002 中国移动（ TD-S ）
     * 46003 中国电信（ CDMA ）
     * 46004 空（ 似乎是专门用来做测试的 ）
     * 46005 中国电信（ CDMA ）
     * 46006 中国联通（ WCDMA ）
     * 46007 中国移动（ TD-S ）
     * 46008 中国电信（ FDD-LTE ）
     * 46009 中国电信（ FDD-LTE ）
     * 46010 中国电信（ FDD-LTE ）
     * 46011 中国电信（ FDD-LTE ）
     *
     * @param context 上下文
     * @return String
     */
    public String getSimOperator(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String operator = tm != null ? tm.getSimOperator() : null;
        if (operator == null) return null;
        switch (operator) {
            case "46000":
            case "46002":
            case "46007":
                return "中国移动";
            case "46001":
            case "46006":
                return "中国联通";
            case "46003":
            case "46005":
            case "46008":
            case "46009":
            case "46010":
            case "46011":
                return "中国电信";
            default:
                return operator;
        }
    }
}
