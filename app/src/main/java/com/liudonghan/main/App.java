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
        ADApplicationUtils.init(this, new ADApplicationUtils.ADApplicationUtilsListener() {
            @Override
            public void onCreated(Activity activity, Bundle bundle) {
                Log.i("Mac_Liu","onCreated：" + activity.getLocalClassName());
            }

            @Override
            public void onStarted(Activity activity) {
                Log.i("Mac_Liu","onStarted：" + activity.getLocalClassName());
            }

            @Override
            public void onResumed(Activity activity) {
                Log.i("Mac_Liu","onResumed：" + activity.getLocalClassName());
            }

            @Override
            public void onPaused(Activity activity) {
                Log.i("Mac_Liu","onPaused：" + activity.getLocalClassName());
            }

            @Override
            public void onStopped(Activity activity) {
                Log.i("Mac_Liu","onStopped：" + activity.getLocalClassName());
            }

            @Override
            public void onDestroyed(Activity activity) {
                Log.i("Mac_Liu","onDestroyed：" + activity.getLocalClassName());
            }

            @Override
            public void onSaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityForeground() {
                Log.i("Mac_Liu","前台运行");
            }

            @Override
            public void onActivityBackground() {
                Log.i("Mac_Liu","后台运行");
            }
        });
    }
}
