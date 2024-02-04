package com.liudonghan.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;


/**
 * Description：Intent管理工具
 *
 * @author Created by: Li_Min
 * Time:6/1/23
 */
public class ADIntentManager {
    private static volatile ADIntentManager instance = null;
    private static final String TAG = "Mac_Liu";
    private Builder builder;

    private ADIntentManager() {
    }

    public static ADIntentManager getInstance() {
        //single checkout
        if (null == instance) {
            synchronized (ADIntentManager.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADIntentManager();
                }
            }
        }
        return instance;
    }

    public static void openCamera(){

    }

    /**
     * 构建Intent实例
     *
     * @param context 上下文
     * @return ADIntentManager
     */
    public ADIntentManager from(Context context) {
        builder = new Builder(new Intent(), context);
        return this;
    }

    /**
     * 隐式启动
     *
     * @param action 页面路径
     * @return Builder
     */
    public Builder startAction(String action) {
        builder.getIntent().setAction(action);
        return builder;
    }

    /**
     * 显示启动
     *
     * @param c class类
     * @return Builder
     */
    public Builder startClass(Class<?> c) {
        builder.getIntent().setClass(builder.getContext(), c);
        return builder;
    }

    public static class Builder {
        private Intent intent;
        private Context context;

        public Builder(Intent intent, Context context) {
            this.intent = intent;
            this.context = context;
        }

        public Context getContext() {
            return context;
        }

        public Intent getIntent() {
            return intent;
        }

        public Builder putExt(String key, String value) {
            intent.putExtra(key, value);
            return this;
        }

        public Builder putExt(String key, int value) {
            intent.putExtra(key, value);
            return this;
        }

        public Builder putExt(String key, double value) {
            intent.putExtra(key, value);
            return this;
        }

        public Builder putExt(String key, long value) {
            intent.putExtra(key, value);
            return this;
        }

        public Builder putExt(String key, Serializable value) {
            intent.putExtra(key, value);
            return this;
        }

        public Builder putExt(String key, Bundle value) {
            intent.putExtra(key, value);
            return this;
        }

        public Builder putExt(String key, Parcelable value) {
            intent.putExtra(key, value);
            return this;
        }

        public Builder addFlag(int intentFlag) {
            intent.addFlags(intentFlag);
            return this;
        }

        public void builder() {
            if (null == context) {
                Log.i(TAG, "context is null");
                return;
            }
            if (null == intent) {
                Log.i(TAG, "intent is null");
                return;
            }
            context.startActivity(intent);
        }
    }
}
