package com.liudonghan.main;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.liudonghan.utils.ADApplicationUtils;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:4/6/23
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        // 解决打开相机和打开文件问题
//        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//        StrictMode.setVmPolicy(builder.build());
//        builder.detectFileUriExposure();
        ADApplicationUtils.init(this, new ApplicationUtils() {
            @Override
            protected void onForeground() {
                Log.i("Mac_Liu", "前台服务");
            }

            @Override
            protected void onBackground() {
                Log.i("Mac_Liu", "后台服务");
            }

            @Override
            public void onActivityBackground() {
                Log.i("Mac_Liu","后台服务另一个方法");
            }
        });
    }

    public static abstract class ApplicationUtils implements ADApplicationUtils.ADApplicationUtilsListener {

        protected abstract void onForeground();

        protected abstract void onBackground();

        @Override
        public void onCreated(Activity activity, Bundle bundle) {

        }

        @Override
        public void onStarted(Activity activity) {

        }

        @Override
        public void onResumed(Activity activity) {

        }

        @Override
        public void onPaused(Activity activity) {

        }

        @Override
        public void onStopped(Activity activity) {

        }

        @Override
        public void onDestroyed(Activity activity) {

        }

        @Override
        public void onSaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityForeground() {
            onForeground();
        }

        @Override
        public void onActivityBackground() {
            onBackground();
        }
    }
}
