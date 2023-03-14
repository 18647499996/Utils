package com.liudonghan.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description：常用工具类
 *
 * @author Created by: Li_Min
 * Time:3/14/23
 */
public class ADRegexUtils {

    private static volatile ADRegexUtils instance = null;

    private ADRegexUtils() {
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
     * 验证数字
     *
     * @param str 字符串
     * @return boolean
     */
    public boolean IsNumber(String str) {
        String regex = "^[0-9]*$";
        return match(regex, str);
    }

    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

}
