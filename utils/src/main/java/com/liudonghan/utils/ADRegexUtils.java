package com.liudonghan.utils;

import android.text.TextUtils;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description：常用工具类
 *
 * @author Created by: Li_Min
 * Time:3/14/23
 */
public class ADRegexUtils {

    private static final String MAC_LIU = "Mac_Liu";
    private static volatile ADRegexUtils instance = null;

    private ADRegexUtils() {
    }

    /**
     * 获取消息包含手机号码脱敏显示
     *
     * @param content 内容
     * @return String
     */
    public String getMobileAcute(String content) {
        if (TextUtils.isEmpty(content)) {
            return "";
        }
        if (content.length() < 11) {
            return content;
        }
        // 将字符串切割成数组
        String[] split = content.split("");
        int start = -1;
        int end = -1;
        int count = 0;
        for (int i = 0; i < split.length; i++) {
            if (isNumber(split[i])) {
                // 当前字符包含数字
                if (count < 11) {
                    if (-1 == start) {
                        start = i;
                    } else {
                        end = i;
                    }
                    count++;
                }
            } else {
                // 不包含数字
                if (count != 11) {
//                    Log.d(MAC_LIU, "Count值：" + count);
                    start = -1;
                    end = -1;
                    count = 0;
                }
            }
        }
        if (count != 0) {
            Log.d(MAC_LIU, "Count总数：" + count);
            Log.d(MAC_LIU, "开始：" + start + " ----- " + "结束：" + end);
            Log.d(MAC_LIU, "截取字符串：" + content.substring(start, end + 1));
            if (isTelPhoneNumber(content.substring(start, end + 1))) {
                String before = content.substring(0, start);
                String phone = content.substring(start, end + 1);
                String suffix = content.substring(end + 1);
                Log.d(MAC_LIU, "前缀：" + before);
                Log.d(MAC_LIU, "手机号码：" + mobileNaked(phone));
                Log.d(MAC_LIU, "后缀：" + suffix);
                return before + mobileNaked(phone) + suffix;
            } else {
                return content;
            }
        }else{
            return content;
        }
    }

    public static ADRegexUtils getInstance() {
        //single chcekout
        if (null == instance) {
            synchronized (ADRegexUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADRegexUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 手机号码脱敏显示
     * @param num 脱敏
     * @return String
     */
    public static String mobileNaked(String num) {
        if (TextUtils.isEmpty(num)) {
            return "";
        }
        if (!isTelPhoneNumber(num)){
            return num;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(num.subSequence(0, 3));
        for (int i = 0; i < num.length() - 5; i++) {
            sb.append("*");
        }
        sb.append(num.substring(num.length() - 2));
        return sb.toString();
    }


    /**
     * 验证数字
     *
     * @param str 字符串
     * @return boolean
     */
    public boolean isNumber(String str) {
        String regex = "^[0-9]*$";
        return match(regex, str);
    }

    public static boolean isTelPhoneNumber(String value) {
        if (value != null && value.length() == 11) {
            Pattern pattern = Pattern.compile("^1[3|4|5|6|7|8][0-9]\\d{8}$");
            Matcher matcher = pattern.matcher(value);
            return matcher.matches();
        }
        return false;
    }

    /**
     * 正则过滤器
     *
     * @param regex 匹配符
     * @param str   字符串
     * @return boolean
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

}
