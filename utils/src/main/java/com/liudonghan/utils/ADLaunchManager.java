package com.liudonghan.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:1/4/23
 */
public class ADLaunchManager {

    @SuppressLint("StaticFieldLeak")
    private static Application application;
    private static final String TAG = "Mac_Liu";


    private ADLaunchManager() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param app 应用
     */
    public static void init(@NonNull final Application app) {
        init(app, null);
    }

    /**
     * 初始化Application
     *
     * @param app                        应用
     * @param adApplicationUtilsListener 回调
     */
    public static void init(@NonNull final Application app, ADApplicationUtilsListener adApplicationUtilsListener) {
        ADLaunchManager.application = app;
        app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            private int foregroundActivities = 0;
            private boolean isChangingConfiguration;

            @Override
            public void onActivityCreated(@NonNull Activity activity, Bundle bundle) {
                ADActivityManagerUtils.getActivityManager().addActivity(activity);
                if (null != adApplicationUtilsListener) {
                    adApplicationUtilsListener.onCreated(activity, bundle);
                }
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                if (null != adApplicationUtilsListener) {
                    adApplicationUtilsListener.onStarted(activity);
                    foregroundActivities++;
                    if (foregroundActivities == 1 && !isChangingConfiguration) {
                        Log.i(TAG, "application run in foreground");
                        adApplicationUtilsListener.onActivityForeground();
                    }
                    isChangingConfiguration = false;
                }
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                if (null != adApplicationUtilsListener) {
                    adApplicationUtilsListener.onResumed(activity);
                }
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                if (null != adApplicationUtilsListener) {
                    adApplicationUtilsListener.onPaused(activity);
                }
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                if (null != adApplicationUtilsListener) {
                    adApplicationUtilsListener.onStopped(activity);
                    foregroundActivities--;
                    if (0 == foregroundActivities) {
                        Log.i(TAG, "application run in background");
                        adApplicationUtilsListener.onActivityBackground();
                    }
                    isChangingConfiguration = activity.isChangingConfigurations();
                }
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
                if (null != adApplicationUtilsListener) {
                    adApplicationUtilsListener.onSaveInstanceState(activity, bundle);
                }
            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                ADActivityManagerUtils.getActivityManager().finishActivity(activity);
                if (null != adApplicationUtilsListener) {
                    adApplicationUtilsListener.onDestroyed(activity);
                }
            }
        });
    }

    /**
     * 获取Application
     *
     * @return Application
     */
    public static Application getApp() {
        if (application != null) return application;
        throw new NullPointerException("u should init first");
    }


    /**
     * 获取应用图标
     * todo 使用次方法必须调用init()
     *
     * @return int
     */
    public static int getAppIcon() {
        return getAppIcon(getApp());
    }

    /**
     * 获取应用图标
     *
     * @param context 上下文
     * @return int
     */
    public static int getAppIcon(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.applicationInfo.icon;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * todo 获取apk构建日期
     *
     * @param context 上下文
     * @return String
     */
    public static String getAPKBuildDate(@NonNull Context context) {
        long buildDate;
        SimpleDateFormat simpleDateFormat = ADFormatUtils.getInstance().getSimpleDateFormat(ADFormatUtils.DEFAULT_FORMAT);
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            ZipFile zf = new ZipFile(ai.sourceDir);

            //If this failed, try with the old zip method
            ZipEntry ze = zf.getEntry("classes.dex");
            buildDate = ze.getTime();


            zf.close();
        } catch (Exception e) {
            buildDate = 0;
        }

        if (buildDate > 631152000000L) {
            return simpleDateFormat.format(new Date(buildDate));
        } else {
            return "";
        }
    }

    /**
     * todo 获取版本名称
     *
     * @param context 上下文
     * @return string
     */
    public static String getVersionName(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            return "Unknown";
        }
    }

    public static int getVersionCode(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * todo 设备厂商
     *
     * @return String
     */
    public static String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * todo 获取设备型号
     *
     * @return String
     */
    public static String getDeviceModel() {
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim().replaceAll("\\s*", "");
        } else {
            model = "";
        }
        return model;
    }

    /**
     * todo 获取设备系统版本号
     *
     * @return 设备系统版本号
     */
    public static int getSDKLevel() {
        return android.os.Build.VERSION.SDK_INT;
    }

    public static String getSDKVersion(){
        return Build.VERSION.RELEASE;
    }

    /**
     * todo 获取设备AndroidID
     *
     * @return AndroidID
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }


    public interface ADApplicationUtilsListener {

        void onCreated(Activity activity, Bundle bundle);

        void onStarted(Activity activity);

        void onResumed(Activity activity);

        void onPaused(Activity activity);

        void onStopped(Activity activity);

        void onDestroyed(Activity activity);

        void onSaveInstanceState(Activity activity, Bundle bundle);

        void onActivityForeground();

        void onActivityBackground();
    }
}