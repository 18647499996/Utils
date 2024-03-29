package com.liudonghan.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:6/14/23
 */
public class ADFragmentUtils {
    private static volatile ADFragmentUtils instance = null;

    private ADFragmentUtils() {
    }

    public static ADFragmentUtils getInstance() {
        //single checkout
        if (null == instance) {
            synchronized (ADFragmentUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADFragmentUtils();
                }
            }
        }
        return instance;
    }

    /**
     * todo 根据Activity View 获取Fragment引用（ Activity 嵌套 Fragment ）
     *
     * @param fragmentActivity activity引用
     * @param viewId           viewId
     * @param position         索引
     * @return Fragment
     */
    public Fragment findFragmentByViewId(FragmentActivity fragmentActivity, int viewId, int position) {
        return fragmentActivity.getSupportFragmentManager().findFragmentByTag("android:switcher:" + viewId + ":" + position);
    }

    /**
     * todo 根据Fragment View 获取Fragment引用（ Fragment 嵌套 Fragment ）
     *
     * @param fragment fragment引用
     * @param viewId   viewId
     * @param position 索引
     * @return Fragment
     */
    public Fragment findFragmentByViewId(Fragment fragment, int viewId, int position) {
        return fragment.getChildFragmentManager().findFragmentByTag("android:switcher:" + viewId + ":" + position);
    }
}
