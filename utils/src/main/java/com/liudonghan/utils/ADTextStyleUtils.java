package com.liudonghan.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;

/**
 * Description： TextViewStyle工具
 *
 * @author Created by: Li_Min
 * Time:1/12/23
 */
public class ADTextStyleUtils {

    private Context context;
    private TextView textView;
    private int originSize;
    private int originColor;
    private int changeSize;
    private int changeColor;
    private int drawablesIcon;
    private int drawablesPadding;
    private Direction direction;
    private int start;
    private int end;
    private String description;
    private int width, height;

    private static volatile ADTextStyleUtils instance = null;

    private ADTextStyleUtils(){}

    public static ADTextStyleUtils getInstance(){
     //single chcekout
     if(null == instance){
        synchronized (ADTextStyleUtils.class){
            // double checkout
            if(null == instance){
                instance = new ADTextStyleUtils();
            }
        }
     }
     return instance;
    }


    public ADTextStyleUtils from(Context context) {
        this.context = context;
        return this;
    }

    public ADTextStyleUtils findView(TextView textView) {
        this.textView = textView;
        return this;
    }

    /**
     * 原始属性（ 文字大小、文字眼阿瑟 ）
     *
     * @param originSize  大小
     * @param originColor 颜色
     * @return ADTextStyleUtils
     */
    public ADTextStyleUtils originAttr(int originSize, @ColorRes int originColor) {
        this.originSize = originSize;
        this.originColor = originColor;
        return this;
    }

    /**
     * 改变属性（ 文字大小、文字颜色 ）
     *
     * @param changeSize  大小
     * @param changeColor 颜色
     * @return ADTextStyleUtils
     */
    public ADTextStyleUtils changeAttr(int changeSize, @ColorRes int changeColor) {
        this.changeSize = changeSize;
        this.changeColor = changeColor;
        return this;
    }

    /**
     * 改变位置
     *
     * @param start 开始位置
     * @param end   结束位置
     * @return ADTextStyleUtils
     */
    public ADTextStyleUtils changePosition(int start, int end) {
        this.start = start;
        this.end = end;
        return this;
    }

    /**
     * 设置图标属性
     *
     * @param drawablesIcon 图标
     * @return ADTextStyleUtils
     */
    public ADTextStyleUtils drawablesIconAttr(@DrawableRes int drawablesIcon) {
        drawablesIconAttr(drawablesIcon, Direction.LEFT, 8);
        return this;
    }

    /**
     * 设置图标属性
     *
     * @param drawablesIcon 图标
     * @param direction     显示区域
     * @return ADTextStyleUtils
     */
    public ADTextStyleUtils drawablesIconAttr(@DrawableRes int drawablesIcon, Direction direction) {
        drawablesIconAttr(drawablesIcon, direction, 8);
        return this;
    }

    /**
     * 设置图标属性
     *
     * @param drawablesIcon    图标
     * @param direction        显示区域
     * @param drawablesPadding 图标与文字间距
     * @return ADTextStyleUtils
     */
    public ADTextStyleUtils drawablesIconAttr(@DrawableRes int drawablesIcon, Direction direction, int drawablesPadding) {
        this.drawablesIcon = drawablesIcon;
        this.direction = direction;
        this.drawablesPadding = drawablesPadding;
        return this;
    }

    /**
     * 图标大小
     *
     * @param width  宽
     * @param height 高
     * @return ADTextStyleUtils
     */
    public ADTextStyleUtils drawablesSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * 设置文本
     *
     * @param description 文本名称
     * @return ADTextStyleUtils
     */
    public ADTextStyleUtils textDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * 构建TextView样式
     */
    public void builder() {
        if (TextUtils.isEmpty(description)) {
            return;
        }
        SpannableString spannableString = new SpannableString(description);
        if (0 != changeColor) {
            // 改变文本
            spannableString.setSpan(new AbsoluteSizeSpan(changeSize, true), start, end, Spanned.SPAN_INTERMEDIATE);
            spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(changeColor)), start, end, Spanned.SPAN_INTERMEDIATE);
        }
        if (0 != originColor) {
            // 原文本
            spannableString.setSpan(new AbsoluteSizeSpan(originSize, true), end, description.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(originColor)), end, description.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (0 != drawablesIcon && null != textView) {
            Drawable drawable = context.getResources().getDrawable(drawablesIcon);
            drawable.setBounds(0, 0, 0 == width ? textView.getHeight() : width, 0 == height ? textView.getHeight() : height);
            switch (direction) {
                case LEFT:
                    textView.setCompoundDrawables(drawable, null, null, null);
                    break;
                case TOP:
                    textView.setCompoundDrawables(null, drawable, null, null);
                    break;
                case RIGHT:
                    textView.setCompoundDrawables(null, null, drawable, null);
                    break;
                case BOTTOM:
                    textView.setCompoundDrawables(null, null, null, drawable);
                    break;
            }
            textView.setCompoundDrawablePadding(drawablesPadding);
        }
        if (null != textView) {
            textView.setText(spannableString);
        }
    }


    /**
     * 图标显示位置枚举
     */
    public enum Direction {
        LEFT,
        TOP,
        RIGHT,
        BOTTOM
    }
}
