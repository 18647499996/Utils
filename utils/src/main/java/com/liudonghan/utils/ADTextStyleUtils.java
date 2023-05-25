package com.liudonghan.utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
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

    private static final String TAG = "Mac_Liu";
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

    private ADTextStyleUtils() {
    }

    public static ADTextStyleUtils getInstance() {
        //single chcekout
        if (null == instance) {
            synchronized (ADTextStyleUtils.class) {
                // double checkout
                if (null == instance) {
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
        if (null == textView) {
            return;
        }
        if (TextUtils.isEmpty(description) || null == context) {
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
        if (0 != drawablesIcon) {
            Drawable drawable = context.getResources().getDrawable(drawablesIcon);
            drawable.setBounds(0, 0, 0 == width ? drawable.getMinimumWidth() : width, 0 == height ? drawable.getMinimumHeight() : height);
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
        } else {
            textView.setCompoundDrawables(null, null, null, null);
        }
        if (null != textView) {
            textView.setText(spannableString);
        }
        clearConfig();
    }

    /**
     * 下划线
     *
     * @param textView    TextView组件
     * @param description 文字内容
     */
    public void handlerTextUnderLine(TextView textView, String description) {
        // 下划线
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        // 抗锯齿
        textView.getPaint().setAntiAlias(true);
        // 设置文本
        textView.setText(description);
    }

    /**
     * 中划线
     *
     * @param textView    TextView组件
     * @param description 文字内容
     */
    public void handlerTextCenterLine(TextView textView, String description) {
        // 中划线
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        // 设置中划线并加清晰
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        // 抗锯齿
        textView.getPaint().setAntiAlias(true);
        // 设置文本
        textView.setText(description);
    }

    /**
     * 设置TextView图标
     *
     * @param context  上下文
     * @param textView TextView组件
     * @param left     左侧图标
     * @param top      顶部图标
     * @param right    右侧图标
     * @param bottom   底部图标
     */
    public void setCompoundDrawables(Context context, TextView textView, @DrawableRes int left, @DrawableRes int top, @DrawableRes int right, @DrawableRes int bottom) {
        setCompoundDrawables(context, textView, left, top, right, bottom, 20);
    }

    /**
     * 设置TextView图标
     *
     * @param context  上下文
     * @param textView TextView组件
     * @param left     左侧图标
     * @param top      顶部图标
     * @param right    右侧图标
     * @param bottom   底部图标
     * @param padding  图标与文字距离
     */
    public void setCompoundDrawables(Context context, TextView textView, @DrawableRes int left, @DrawableRes int top, @DrawableRes int right, @DrawableRes int bottom, int padding) {
        if (null == context) {
            Log.i(TAG, "context is null");
            return;
        }
        if (null == textView) {
            Log.i(TAG, "TextView is null");
            return;
        }
        Drawable drawableLeft = null;
        Drawable drawableRight = null;
        Drawable drawableTop = null;
        Drawable drawableBottom = null;
        if (0 != left) {
            drawableLeft = context.getResources().getDrawable(left);
            drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
        }
        if (0 != right) {
            drawableRight = context.getResources().getDrawable(right);
            drawableRight.setBounds(0, 0, drawableRight.getMinimumWidth(), drawableRight.getMinimumHeight());
        }
        if (0 != top) {
            drawableTop = context.getResources().getDrawable(top);
            drawableTop.setBounds(0, 0, drawableTop.getMinimumWidth(), drawableTop.getMinimumHeight());
        }
        if (0 != bottom) {
            drawableBottom = context.getResources().getDrawable(bottom);
            drawableBottom.setBounds(0, 0, drawableBottom.getMinimumWidth(), drawableBottom.getMinimumHeight());
        }
        textView.setCompoundDrawablePadding(padding);
        textView.setCompoundDrawables(drawableLeft, drawableTop, drawableRight, drawableBottom);
    }

    private void clearConfig() {
        originSize = 0;
        originColor = 0;
        changeColor = 0;
        changeSize = 0;
        drawablesIcon = 0;
        drawablesPadding = 0;
        direction = Direction.LEFT;
        start = 0;
        end = 0;
        textView = null;
        description = "";
        width = 0;
        height = 0;
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
