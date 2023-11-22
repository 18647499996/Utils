package com.liudonghan.utils;

import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Description：Image工具
 *
 * @author Created by: Li_Min
 * Time:4/13/23
 */
public class ADImageUtils {

    private static volatile ADImageUtils instance = null;

    private ADImageUtils() {
    }

    public static ADImageUtils getInstance() {
        //single checkout
        if (null == instance) {
            synchronized (ADImageUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADImageUtils();
                }
            }
        }
        return instance;
    }

    /**
     * todo 设置等比图片宽高
     *
     * @param imageView imageView组件
     * @param width     宽
     * @param height    搞
     * @return ImageView
     */
    public ImageView setImageScale(ImageView imageView, int width, int height) {
        return setImageScale(imageView, width, height, 540);
    }

    /**
     * 设置等比图片宽高
     *
     * @param imageView imageView组件
     * @param width     宽
     * @param height    高
     * @param ratio     等比值（ 默认：540 ）
     *                  使用于聊天界面图片消息显示
     * @return ImageView
     */
    public ImageView setImageScale(ImageView imageView, int width, int height, int ratio) {
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        if (width == 0 || height == 0) {
            layoutParams.width = ratio;
            layoutParams.height = ratio;
            imageView.setLayoutParams(layoutParams);
            return imageView;
        }
        if (width > height) {
            layoutParams.width = ratio;
            layoutParams.height = ratio * height / width;
        } else {
            layoutParams.width = ratio * width / height;
            layoutParams.height = ratio;
        }
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }

    /**
     * 设置动态图片宽高
     *
     * @param imageView imageView组件
     * @param width     宽
     * @param height    搞
     * @return ImageView
     */
    public ImageView setImageWidthOrHeight(ImageView imageView, int width, int height) {
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }
}
