package com.liudonghan.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:3/23/23
 */
public class ADFormatUtils {
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String YEAR_MONTH_DAY_HOUR_MINUTE = "yyyy-MM-dd HH:mm";
    public static final String YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static final String YEAR_MONTH = "yyyy-MM";
    public static final String HOUR_MINUTE = "HH:mm";


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
    public String formatDurations(int timeSeconds) {
        int second = timeSeconds % 60;
        int minuteTemp = timeSeconds / 60;
        if (minuteTemp > 0) {
            int minute = minuteTemp % 60;
            int hour = minuteTemp / 60;
            if (hour > 0) {
                return (hour >= 10 ? (hour + "") : ("0" + hour)) + ":" + (minute >= 10 ? (minute + "") : ("0" + minute)) + ":" + (second >= 10 ? (second + "") : ("0" + second));
            } else {
                return (minute >= 10 ? (minute + "") : ("0" + minute)) + ":" + (second >= 10 ? (second + "") : ("0" + second));
            }
        } else {
            return "00:" + (second >= 10 ? (second + "") : ("0" + second));
        }
    }

    /**
     * todo 获取SimpleDateFormat
     *
     * @return SimpleDateFormat
     */
    @SuppressLint("SimpleDateFormat")
    public SimpleDateFormat getSimpleDateFormat() {
        return getSimpleDateFormat("");
    }

    /**
     * todo 获取SimpleDateFormat
     *
     * @param format format格式
     * @return SimpleDateFormat
     */
    @SuppressLint("SimpleDateFormat")
    public SimpleDateFormat getSimpleDateFormat(String format) {
        return new SimpleDateFormat(TextUtils.isEmpty(format) ? DEFAULT_FORMAT : format);
    }

    /**
     * todo 根据时间戳获取时间
     *
     * @param millis 时间戳
     * @param format 格式
     * @return String
     */
    public String getTimeStampToFormat(long millis, String format) {
        return getSimpleDateFormat(format).format(new Date(millis));
    }

    /**
     * todo 获取时间戳（ 天数 ）
     *
     * @param dayNum 共计天数
     * @return long
     */
    public long getDayTimeStamp(int dayNum) {
        // 一天时间戳
        long date = 60 * 60 * 24 * 1000;
        return date * dayNum;
    }


    /**
     * todo 获取当前之前日期 （ 几天前 ）
     *
     * @param dayNum 共计天数
     * @return String
     */
    public String getBeforeDay(int dayNum) {
        long beforeTimeStamp = System.currentTimeMillis() - getDayTimeStamp(dayNum);
        return getTimeStampToFormat(beforeTimeStamp, ADFormatUtils.YEAR_MONTH_DAY);
    }

    /**
     * todo 根据日期获取时间戳
     *
     * @param simpleDate 日期
     * @return long
     */
    public long getSimpleDateToTime(String simpleDate) {
        return getSimpleDateToTime(simpleDate,DEFAULT_FORMAT);
    }

    /**
     * todo 根据日期获取时间戳
     *
     * @param simpleDate 日期
     * @param dateFormat 格式化 默认：yyyy-MM-dd HH:mm:ss
     * @return long
     */
    public long getSimpleDateToTime(String simpleDate,String dateFormat) {
        SimpleDateFormat simpleDateFormat = getSimpleDateFormat(dateFormat);
        try {
            Date date = simpleDateFormat.parse(simpleDate);
            return Objects.requireNonNull(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * todo 根据date日期获取时间差
     * 例如：几年前、几个月前、几天前、几小时前、几分钟前、刚刚
     *
     * @param date 日期
     * @return String
     */
    public String getTimeFormatText(Date date) {
        long minute = 60 * 1000;// 1分钟
        long hour = 60 * minute;// 1小时
        long day = 24 * hour;// 1天
        long month = 31 * day;// 月
        long year = 12 * month;// 年

        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        long r;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    /**
     * todo 格式化数量（ 浏览、点赞、收藏 ）
     *
     * @param num 数量
     * @return String
     */
    public String formatBrowseNum(String num) {
        if (TextUtils.isEmpty(num)) {
            // 数据为空直接返回0
            return "0";
        }
        try {
            StringBuffer sb = new StringBuffer();
            if (!ADRegexUtils.getInstance().isNumber(num)) {
                // 如果数据不是数字则直接返回0
                return "0";
            }


            BigDecimal b0 = new BigDecimal("1000");
            BigDecimal b1 = new BigDecimal("10000");
            BigDecimal b2 = new BigDecimal("100000000");
            BigDecimal b3 = new BigDecimal(num);

            String formatedNum = "";//输出结果
            String unit = "";//单位

            if (b3.compareTo(b0) == -1) {
                sb.append(b3.toString());
            } else if ((b3.compareTo(b0) == 0 || b3.compareTo(b0) == 1)
                    || b3.compareTo(b1) == -1) {
                formatedNum = b3.divide(b0).toString();
                unit = "k";
            } else if ((b3.compareTo(b1) == 0 && b3.compareTo(b1) == 1)
                    || b3.compareTo(b2) == -1) {
                formatedNum = b3.divide(b1).toString();
                unit = "w";
            } else if (b3.compareTo(b2) == 0 || b3.compareTo(b2) == 1) {
                formatedNum = b3.divide(b2).toString();
                unit = "亿";
            }
            if (!"".equals(formatedNum)) {
                int i = formatedNum.indexOf(".");
                if (i == -1) {
                    sb.append(formatedNum).append(unit);
                } else {
                    i = i + 1;
                    String v = formatedNum.substring(i, i + 1);
                    if (!v.equals("0")) {
                        sb.append(formatedNum.substring(0, i + 1)).append(unit);
                    } else {
                        sb.append(formatedNum.substring(0, i - 1)).append(unit);
                    }
                }
            }
            if (sb.length() == 0)
                return "0";
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return num;
        }
    }

}
