package com.liudonghan.utils;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:9/4/23
 */
public class ADCountDownUtils {
    private static volatile ADCountDownUtils instance = null;
    private static CountDownTimer timer;

    private ADCountDownUtils() {
    }

    public static ADCountDownUtils getInstance() {
        //single checkout
        if (null == instance) {
            synchronized (ADCountDownUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADCountDownUtils();
                }
            }
        }
        return instance;
    }

    public Builder find() {
        return new Builder();
    }

    public Builder find(Button button) {
        return new Builder(button);
    }

    public Builder find(TextView textView) {
        return new Builder(textView);
    }

    public void destroy() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public static class Builder {

        private int interval = 1000;
        private int time = 60 * 1000;
        private String desc = "获取验证码";
        private String postfix = "";
        private TextView textView;
        private boolean isFinished = true;
        private OnADCountDownUtilsListener onADCountDownUtilsListener;

        public Builder() {

        }

        public Builder(Button button) {
            this.textView = button;
        }

        public Builder(TextView textView) {
            this.textView = textView;
        }

        public Builder setInterval(int interval) {
            this.interval = interval;
            return this;
        }

        /**
         * todo 设置总时长
         *
         * @param time 单位（ 秒 ）
         * @return Builder
         */
        public Builder setTime(int time) {
            this.time = time * 1000;
            return this;
        }

        /**
         * todo 设置View描述文字
         *
         * @param desc 文字
         * @return Builder
         */
        public Builder setDesc(String desc) {
            this.desc = desc;
            return this;
        }

        /**
         * todo 设置倒计时后缀
         *
         * @param postfix 后缀描述
         * @return Builder
         */
        public Builder setPostfix(String postfix) {
            this.postfix = postfix;
            return this;
        }

        public Builder request(OnADCountDownUtilsListener onADCountDownUtilsListener) {
            this.onADCountDownUtilsListener = onADCountDownUtilsListener;
            return this;
        }

        public void start() {
            if (timer != null && !isFinished()) {
                return;
            }
            if (timer == null) {
                timer = new CountDownTimer(time, interval) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        isFinished = false;
                        String result = (millisUntilFinished / 1000) + " " + postfix;
                        if (null != textView) {
                            textView.setText(result);
                            textView.setEnabled(false);
                            textView.setAlpha((float) 0.5);
                        }
                        if (onADCountDownUtilsListener != null) {
                            onADCountDownUtilsListener.onTick(result, millisUntilFinished / 1000, time, interval);
                        }
                    }

                    @Override
                    public void onFinish() {
                        isFinished = true;
                        if (null != textView) {
                            textView.setEnabled(true);
                            textView.setAlpha((float) 1.0);
                            textView.setText(desc);
                        }
                        if (onADCountDownUtilsListener != null) {
                            onADCountDownUtilsListener.onFinish();
                        }
                    }
                };
            }
            timer.start();

        }

        private boolean isFinished() {
            return isFinished;
        }

        public interface OnADCountDownUtilsListener {

            /**
             * todo 倒计时回调
             *
             * @param result    结果
             * @param countDown 倒计时时长
             * @param total     总时长
             * @param interval  间隔时长
             */
            void onTick(String result, long countDown, int total, int interval);

            /**
             * todo 倒计时结束回调
             */
            void onFinish();
        }
    }


}
