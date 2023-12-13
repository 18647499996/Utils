package com.liudonghan.main;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.facebook.stetho.Stetho;
import com.liudonghan.db.GreenDaoManager;
import com.liudonghan.main.activity.error.ErrorActivity;
import com.liudonghan.utils.ADLaunchManager;
import com.liudonghan.utils.ADCrashErrorManager;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:4/6/23
 */
public class App extends Application implements ADLaunchManager.ADApplicationUtilsListener, ADCrashErrorManager.Builder.OnADCrashErrorHandlerListener {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
        // 解决打开相机和打开文件问题
//        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//        StrictMode.setVmPolicy(builder.build());
//        builder.detectFileUriExposure();
        GreenDaoManager.getInstance().init(getApplicationContext(), "liudonghan.db");
        ADCrashErrorManager.getInstance()
                .from(this)
                .errorActivity(ErrorActivity.class)
                .customErrorInfo("jsonObject")
                .listener(this)
                .apply();
        ADLaunchManager.init(this, this);
    }

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

    }

    @Override
    public void onActivityBackground() {
        Log.i("Mac_Liu", "后台服务另一个方法");
    }

    @Override
    public void onUncaughtException(Thread thread, Throwable throwable, String errorMsg) {

    }
}
