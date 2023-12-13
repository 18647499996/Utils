package com.liudonghan.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:10/24/23
 */
public class ADCrashErrorManager {

    public static final String AD_CRASH_ERROR_MANAGER = "AD_CRASH_ERROR_MANAGER";

    public static final String AD_CRASH_ERROR_MANAGER_TIMESTAMP = "AD_CRASH_ERROR_MANAGER_TIMESTAMP";

    public static final String AD_CRASH_ERROR_STACK_TRACE = "AD_CRASH_ERROR_STACK_TRACE";

    public static final String AD_CRASH_ERROR_CUSTOM_JSON = "AD_CRASH_ERROR_CUSTOM_JSON";


    private static volatile ADCrashErrorManager instance = null;

    private ADCrashErrorManager() {
    }

    public static ADCrashErrorManager getInstance() {
        //single checkout
        if (null == instance) {
            synchronized (ADCrashErrorManager.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADCrashErrorManager();
                }
            }
        }
        return instance;
    }

    public Builder from(Context context) {
        return new Builder(context);
    }

    public static class Builder implements Thread.UncaughtExceptionHandler {


        private Context context;
        private Thread.UncaughtExceptionHandler mDefaultCrashHandler;
        // todo 异常活动页面
        private Class<? extends Activity> errorActivityClass = null;
        // todo 自定义异常信息（ json字符串 ）
        private String errorJson;
        private OnADCrashErrorHandlerListener listener;

        public Builder(Context context) {
            this.context = context;
        }

        @Override
        public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
            if (null != listener) {
                listener.onUncaughtException(thread, throwable, throwablePrintWriterErrorText(throwable));
            }
            if (hasCrashedInTheLastSeconds(context)) {
                if (null != mDefaultCrashHandler) {
                    mDefaultCrashHandler.uncaughtException(thread, throwable);
                    return;
                }
            } else {
                setLastCrashTimestamp(context, new Date().getTime());
                if (null != getErrorActivityClass()) {
                    String stackTrace = throwablePrintWriterErrorText(throwable);
                    ADIntentManager.getInstance()
                            .from(context)
                            .startClass(getErrorActivityClass())
                            .putExt(AD_CRASH_ERROR_STACK_TRACE, stackTrace)
                            .putExt(AD_CRASH_ERROR_CUSTOM_JSON, getErrorJson())
                            .addFlag(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            .builder();
                }
            }

            killCurrentProcess();
        }

        public Builder errorActivity(Class<? extends Activity> errorActivityClass) {
            this.errorActivityClass = errorActivityClass;
            return this;
        }

        public Class<? extends Activity> getErrorActivityClass() {
            return errorActivityClass;
        }

        public Builder customErrorInfo(String errorJson) {
            this.errorJson = errorJson;
            return this;
        }

        public String getErrorJson() {
            return errorJson;
        }

        public void apply() {
            mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        }

        public Builder listener(OnADCrashErrorHandlerListener listener) {
            this.listener = listener;
            return this;
        }

        public interface OnADCrashErrorHandlerListener {
            /**
             * todo 异常捕获回调接口
             * @param thread
             * @param throwable
             * @param errorMsg
             */
            void onUncaughtException(Thread thread, Throwable throwable, String errorMsg);
        }
    }

    private static boolean hasCrashedInTheLastSeconds(@NonNull Context context) {
        long lastTimestamp = getLastCrashTimestamp(context);
        long currentTimestamp = new Date().getTime();
        return (lastTimestamp <= currentTimestamp && currentTimestamp - lastTimestamp < 3000);
    }

    private static long getLastCrashTimestamp(@NonNull Context context) {
        return context.getSharedPreferences(AD_CRASH_ERROR_MANAGER, Context.MODE_PRIVATE).getLong(AD_CRASH_ERROR_MANAGER_TIMESTAMP, -1);
    }

    @SuppressLint("ApplySharedPref")
    private static void setLastCrashTimestamp(@NonNull Context context, long timestamp) {
        context.getSharedPreferences(AD_CRASH_ERROR_MANAGER, Context.MODE_PRIVATE).edit().putLong(AD_CRASH_ERROR_MANAGER_TIMESTAMP, timestamp).commit();
    }

    public static void killCurrentProcess() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }


    /**
     * todo 异常堆栈信息
     *
     * @param throwable 异常实现类
     * @return String
     */
    public static String throwablePrintWriterErrorText(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }

    /**
     * todo 获取异常详细信息
     *
     * @param context 上下文
     * @param intent  intent引用
     * @return String
     */
    public static String getErrorDetailsFromIntent(Context context, Intent intent) {
        return "Build Version: " + ADLaunchManager.getVersionName(context) + "\n" +
                "Build Date: " + ADLaunchManager.getAPKBuildDate(context) + "\n" +
                "Build SDK: " + "Android " + ADLaunchManager.getSDKVersion() + " Level " + ADLaunchManager.getSDKLevel() + "\n" +
                "Device Mode: " + ADLaunchManager.getDeviceManufacturer() + " " + ADLaunchManager.getDeviceModel() + "\n" +
                "Build Custom Error: " + intent.getStringExtra(AD_CRASH_ERROR_CUSTOM_JSON) + "\n" +
                "Occurrence Time: " + ADFormatUtils.getInstance().getTimeStampToFormat(System.currentTimeMillis(), ADFormatUtils.DEFAULT_FORMAT) + "\n" +
                "Stack trace: " + intent.getStringExtra(AD_CRASH_ERROR_STACK_TRACE);
    }
}
