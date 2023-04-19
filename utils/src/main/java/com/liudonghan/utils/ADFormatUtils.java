package com.liudonghan.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:3/23/23
 */
public class ADFormatUtils {
    private static volatile ADFormatUtils instance = null;

    private ADFormatUtils() {
    }

    public static ADFormatUtils getInstance() {
        //single checkout
        if (null == instance) {
            synchronized (ADFormatUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADFormatUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 保留两位小微
     *
     * @param decimal 小数
     * @return String
     */
    public String retain2Decimal(String decimal) {
        if (TextUtils.isEmpty(decimal)) {
            return "";
        }
        Double d = Double.parseDouble(decimal);
        return retain2Decimal(d);
    }

    /**
     * 保留两位小微
     *
     * @param decimal 小数
     * @return String
     */
    @SuppressLint("DefaultLocale")
    public String retain2Decimal(Double decimal) {
        return String.format("%.2f", decimal);
    }

    /**
     * 格式化时长
     *
     * @param timeSeconds
     * @return
     */
    public static String formatDurations(int timeSeconds) {
        int second = timeSeconds % 60;
        int minuteTemp = timeSeconds / 60;
        if (minuteTemp > 0) {
            int minute = minuteTemp % 60;
            int hour = minuteTemp / 60;
            if (hour > 0) {
                return (hour >= 10 ? (hour + "") : ("0" + hour)) + ":" + (minute >= 10 ? (minute + "") : ("0" + minute))
                        + ":" + (second >= 10 ? (second + "") : ("0" + second));
            } else {
                return (minute >= 10 ? (minute + "") : ("0" + minute)) + ":"
                        + (second >= 10 ? (second + "") : ("0" + second));
            }
        } else {
            return "00:" + (second >= 10 ? (second + "") : ("0" + second));
        }
    }
}
