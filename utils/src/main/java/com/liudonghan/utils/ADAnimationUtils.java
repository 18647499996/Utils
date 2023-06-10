package com.liudonghan.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2020/9/1
 */
public class ADAnimationUtils {
    private static volatile ADAnimationUtils instance = null;

    private ADAnimationUtils() {
    }

    public static ADAnimationUtils getInstance() {
        //single chcekout
        if (null == instance) {
            synchronized (ADAnimationUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADAnimationUtils();
                }
            }
        }
        return instance;
    }

    public void fadeIn(View view) {
        if (view.getVisibility() == View.VISIBLE) {
            return;
        }
        view.setEnabled(true);
        view.setVisibility(View.VISIBLE);
        Animation animation = new AlphaAnimation(0F, 1F);
        animation.setDuration(400);
        view.startAnimation(animation);
    }


    public void fadeOutGone(View view) {
        if (view.getVisibility() != View.VISIBLE) {
            return;
        }
        // Since the button is still clickable before fade-out animation
        // ends, we disable the button first to block click.
        view.setEnabled(false);
        Animation animation = new AlphaAnimation(1F, 0F);
        animation.setDuration(400);
        view.startAnimation(animation);
        view.setVisibility(View.GONE);
    }

    public void fadeOutInvisible(View view) {
        if (view.getVisibility() != View.VISIBLE) {
            return;
        }
        // Since the button is still clickable before fade-out animation
        // ends, we disable the button first to block click.
        view.setEnabled(false);
        Animation animation = new AlphaAnimation(1F, 0F);
        animation.setDuration(400);
        view.startAnimation(animation);
        view.setVisibility(View.INVISIBLE);
    }

    /**
     * 左右闪动（ 平移动画 ）
     *
     * @param view
     */
    public void shake(View view) {
        Animation translateAnimation = new TranslateAnimation(-20, 20, 0, 0);
        // 每次时间
        translateAnimation.setDuration(100);
        // 重复次数
        translateAnimation.setRepeatCount(2);
        // 倒序重复REVERSE  正序重复RESTART
        translateAnimation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(translateAnimation);
    }

    public void bottomTakIn(View view) {
        // Since the button is still clickable before fade-out animation
        // ends, we disable the button first to block click.
        view.setEnabled(false);
        Animation animation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 1, TranslateAnimation.RELATIVE_TO_SELF, 0);
        animation.setDuration(150);
        view.startAnimation(animation);
        view.setVisibility(View.VISIBLE);
    }

    public void bottomTakOut(View view) {
        // Since the button is still clickable before fade-out animation
        // ends, we disable the button first to block click.
        view.setEnabled(false);
        Animation animation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 1);
        animation.setDuration(150);
        view.startAnimation(animation);
        view.setVisibility(View.GONE);
    }

    public void rotate(View view, int angle) {
        view.animate().rotation(angle);
    }

    /**
     * 添加购物车平移动画
     *
     * @param cartNum         数量
     * @param view            页面父组件
     * @param imageViewBg     缩放图片组件
     * @param textViewCard    购物车组件
     * @param textViewCardNum 购物车数字组件
     */
    public void addShopCart(final int cartNum, View view, final ImageView imageViewBg, TextView textViewCard, final TextView textViewCardNum) {
        //计算动画开始/结束点的坐标的准备工作
        //得到父布局的起始点坐标（用于辅助计算动画开始/结束时的点的坐标）
        int[] parentLoc = new int[2];
        view.getLocationInWindow(parentLoc);
        //得到商品图片的坐标（用于计算动画开始的坐标）（此图片控件添加到根布局，居中）
        int[] startLoc = new int[2];
        imageViewBg.getLocationInWindow(startLoc);
        //购物车控件的坐标(用于计算动画结束后的坐标)
        int[] endLoc = new int[2];
        textViewCard.getLocationInWindow(endLoc);
        float startX = startLoc[0] - parentLoc[0] + imageViewBg.getWidth() / 2;
        float startY = startLoc[1] - parentLoc[1] + imageViewBg.getHeight() / 2;
        //商品掉落后的终点坐标：购物车起始点-父布局起始点+购物车图片的1/5
        float toX = endLoc[0] - parentLoc[0] + textViewCard.getWidth() / 2;
//        float toY = endLoc[1] - parentLoc[1] + ivCart.getHeight() * 2 / 5;
        float toY = endLoc[1] - parentLoc[1] - textViewCard.getHeight() * 5;
        //透明度和缩放动画，动画持续时间和动画透明度可以自己根据想要的效果调整
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(280);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0.1f, 1f, 0.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(300);

        //平移动画X轴 计算X轴要平移的距离，设置动画的偏移时间由透明度和缩放动画持续时间决定
        TranslateAnimation translateAnimationX = new TranslateAnimation(0, toX - startX, 0, 0);
        translateAnimationX.setStartOffset(300);
        translateAnimationX.setDuration(300);
        //设置线性插值器
        translateAnimationX.setInterpolator(new LinearInterpolator());

        //平移动画Y轴 同X轴
        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0, 0, toY - startY);
        translateAnimationY.setStartOffset(300);
        translateAnimationY.setDuration(400);
        //设置加速插值器
        translateAnimationY.setInterpolator(new AccelerateInterpolator());


        //动画集合
        final AnimationSet set = new AnimationSet(false);
        set.addAnimation(alphaAnimation);
        set.addAnimation(scaleAnimation);
        set.addAnimation(translateAnimationX);
        set.addAnimation(translateAnimationY);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                imageViewBg.setVisibility(View.VISIBLE);
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onAnimationEnd(Animation animation) {
                //动画执行完成
                if (cartNum > 99) {
                    textViewCardNum.setText(99 + "+");
                } else {
                    textViewCardNum.setText(String.valueOf(cartNum));
                }
                imageViewBg.setVisibility(View.GONE);
                imageViewBg.clearAnimation();
                set.cancel();
                animation.cancel();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //设置动画播放完以后消失，终止填充
        set.setFillAfter(false);
        imageViewBg.startAnimation(set);
    }

    /**
     * view拉伸动画（ 宽度拉伸 ）
     *
     * @param view     view组件
     * @param startAnim    开始宽度
     * @param endAnim  结束宽度
     * @param duration 动画时长
     */
    @SuppressLint("ObjectAnimatorBinding")
    public void startStretchWidthAnim(View view, int startAnim, int endAnim, int duration) {
        ObjectAnimator scaleX = ObjectAnimator.ofInt(view, "width", startAnim, endAnim);
        scaleX.setDuration(duration);
        scaleX.start();
    }
}
