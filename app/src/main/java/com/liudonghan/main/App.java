package com.liudonghan.main;

import android.app.Application;
import android.os.StrictMode;

import androidx.multidex.MultiDex;

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
    }
}
