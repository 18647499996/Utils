package com.liudonghan.utils;

import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
        Map<Integer, Integer[]> map = new HashMap();
        String[] splits = content.split("");
        List<String> split = new ArrayList<>();
//        Log.e(MAC_LIU,"split：" + splits.length);
        for (int i = 0; i < splits.length; i++) {
            if (!TextUtils.isEmpty(splits[i])) {
//                Log.e(MAC_LIU, "split for：" + splits[i]);
                split.add(splits[i]);
            }
        }
        int start = -1;
        int end = -1;
        int count = 0;
        for (int i = 0; i < split.size(); i++) {
            if (isNumber(split.get(i))) {
                // 当前字符包含数字
                count++;
                if (-1 == start) {
                    start = i;
                } else {
                    end = i;
                }
            } else {
//                Log.d(MAC_LIU, "Count值：" + count + " ------- " + i + "次：" + start + " ----- " + i + "次：" + end);
                // 不包含数字
                if (count != 0) {
                    map.put(i, new Integer[]{start, end});
                }
                start = -1;
                end = -1;
                count = 0;
            }
        }
        // 最后一次
        if (count != 0) {
            map.put(content.length(), new Integer[]{start, end});
        }
//        Log.d(MAC_LIU, "Map大小：" + map.size());
        if (0 != map.size()) {
            String meager = content;
            Iterator<Map.Entry<Integer, Integer[]>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Integer[]> next = iterator.next();
                Integer[] value = next.getValue();
//                Log.d(MAC_LIU, "Count总数：" + count + " --------- " + "开始：" + value[0] + " ----- " + "结束：" + value[1]);
//                Log.d(MAC_LIU, "截取字符串：" + meager.substring(value[0], value[1] + 1));
                if (isTelPhoneNumber(meager.substring(value[0], value[1] + 1))) {
                    String before = meager.substring(0, value[0]);
                    String phone = meager.substring(value[0], value[1] + 1);
                    String suffix = meager.substring(value[1] + 1);
                    Log.d(MAC_LIU, "before：" + before + " --- " + "tel ：" + mobileNaked(phone) + " ----- " + "suffix：" + suffix);
                    meager = before + mobileNaked(phone) + suffix;
                    Log.d(MAC_LIU, "meager message content：" + meager);
                }
            }
            return meager;
        } else {
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
     *
     * @param num 脱敏
     * @return String
     */
    public String mobileNaked(String num) {
        if (TextUtils.isEmpty(num)) {
            return "";
        }
        if (!isTelPhoneNumber(num)) {
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

    public boolean isTelPhoneNumber(String value) {
        if (value != null && value.length() == 11) {
            Pattern pattern = Pattern.compile("^1[3|4|5|6|7|8][0-9]\\d{8}$");
            Matcher matcher = pattern.matcher(value);
            return matcher.matches();
        }
        return false;
    }

    /**
     * 验证手机号（简单）
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public boolean isMobileSimple(final CharSequence input) {
        return match(REGEX_MOBILE_SIMPLE, input);
    }

    /**
     * 验证手机号（精确）
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public boolean isMobileExact(final CharSequence input) {
        return match(REGEX_MOBILE_EXACT, input);
    }

    /**
     * 验证电话号码
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public boolean isTel(final CharSequence input) {
        return match(REGEX_TEL, input);
    }

    /**
     * 验证身份证号码15位
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public boolean isIDCard15(final CharSequence input) {
        return match(REGEX_ID_CARD15, input);
    }

    /**
     * 验证身份证号码18位
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public boolean isIDCard18(final CharSequence input) {
        return match(REGEX_ID_CARD18, input);
    }

    /**
     * 验证邮箱
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public boolean isEmail(final CharSequence input) {
        return match(REGEX_EMAIL, input);
    }

    /**
     * 验证URL
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public boolean isURL(final CharSequence input) {
        return match(REGEX_URL, input);
    }

    /**
     * 验证汉字
     *
     * @param input 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public boolean isZh(final CharSequence input) {
        return match(REGEX_ZH, input);
    }

    /**
     * 正则过滤器
     *
     * @param regex 匹配符
     * @param str   字符串
     * @return boolean
     */
    private boolean match(String regex, CharSequence str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 正则：手机号（简单）
     */
    public static final String REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$";
    /**
     * 正则：手机号（精确）
     * <p>移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188</p>
     * <p>联通：130、131、132、145、155、156、171、175、176、185、186</p>
     * <p>电信：133、153、173、177、180、181、189</p>
     * <p>全球星：1349</p>
     * <p>虚拟运营商：170</p>
     */
    public static final String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,1,3,5-8])|(18[0-9])|(147))\\d{8}$";
    /**
     * 正则：电话号码
     */
    public static final String REGEX_TEL = "^0\\d{2,3}[- ]?\\d{7,8}";
    /**
     * 正则：身份证号码15位
     */
    public static final String REGEX_ID_CARD15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    /**
     * 正则：身份证号码18位
     */
    public static final String REGEX_ID_CARD18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$";
    /**
     * 正则：邮箱
     */
    public static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    /**
     * 正则：URL
     */
    public static final String REGEX_URL = "[a-zA-z]+://[^\\s]*";

    /**
     * 正则：汉字
     */
    public static final String REGEX_ZH = "^[\\u4e00-\\u9fa5]+$";

}
