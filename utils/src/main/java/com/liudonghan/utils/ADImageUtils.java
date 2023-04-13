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
     * 设置等比图片宽高
     *
     * @param imageView imageView组件
     * @param width     宽
     * @param height    搞
     * @return ImageView
     */
    public ImageView setImageScale(ImageView imageView, int width, int height) {
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        if (width == 0 || height == 0) {
            layoutParams.width = 540;
            layoutParams.height = 540;
            imageView.setLayoutParams(layoutParams);
            return imageView;
        }
        if (width > height) {
            layoutParams.width = 540;
            layoutParams.height = 540 * height / width;
        } else {
            layoutParams.width = 540 * width / height;
            layoutParams.height = 540;
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
