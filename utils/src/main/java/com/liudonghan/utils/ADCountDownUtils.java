package com.liudonghan.utils;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:9/4/23
 */
public class ADCountDownUtils {
    private static ConcurrentHashMap<Object, Builder> concurrentHashMap = new ConcurrentHashMap<>();

    private static volatile ADCountDownUtils instance = null;

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

    public Builder tag(String tag) {
        Builder builderModel = concurrentHashMap.get(tag);
        if (null == builderModel) {
            Builder builder = new Builder(tag);
            concurrentHashMap.put(tag, builder);
            return builder;
        } else {
            return builderModel;
        }
    }

    public Builder from(TextView textView) {
        return new Builder(textView);
    }

    public Builder from(Button button) {
        return new Builder(button);
    }

    public void unTaskTimer(String tag) {
        Builder builder = concurrentHashMap.get(tag);
        if (null != builder) {
            builder.getTimer().cancel();
            concurrentHashMap.remove(tag);
        }
    }

    public static class Builder {
        private int interval = 1000;
        private int time = 60 * 1000;
        private String tag;
        private CountDownTimer timer;
        private boolean isFinished = true;
        private TextView textView;
        private String desc = "获取验证码";
        private String postfix = "";
        private OnADCountDownUtilsListener onADCountDownUtilsListener;

        public Builder() {

        }

        public Builder(String tag) {
            this.tag = tag;
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

        public Builder setTime(int time) {
            this.time = time * 1000;
            return this;
        }

        public CountDownTimer getTimer() {
            return timer;
        }

        public OnADCountDownUtilsListener getListener() {
            return onADCountDownUtilsListener;
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
                            onADCountDownUtilsListener.onTick(result, millisUntilFinished / 1000, time, interval, tag);
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
                            onADCountDownUtilsListener.onFinish(tag);
                        }
                    }
                };
            }
            timer.start();

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
            void onTick(String result, long countDown, int total, int interval, String tag);

            /**
             * todo 倒计时结束回调
             */
            void onFinish(String tag);
        }

        private boolean isFinished() {
            return isFinished;
        }
    }
}
