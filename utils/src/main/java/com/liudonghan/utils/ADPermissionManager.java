package com.liudonghan.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:1/31/24
 */
public class ADPermissionManager {

    public static final String REQUEST_INSTALL_PACKAGES = "android.permission.REQUEST_INSTALL_PACKAGES"; // 8.0及以上应用安装权限
    public static final String SYSTEM_ALERT_WINDOW = "android.permission.SYSTEM_ALERT_WINDOW"; // 6.0及以上悬浮窗权限
    public static final String READ_CALENDAR = "android.permission.READ_CALENDAR"; // 读取日程提醒
    public static final String WRITE_CALENDAR = "android.permission.WRITE_CALENDAR"; // 写入日程提醒
    public static final String CAMERA = "android.permission.CAMERA"; // 拍照权限
    public static final String READ_CONTACTS = "android.permission.READ_CONTACTS"; // 读取联系人
    public static final String WRITE_CONTACTS = "android.permission.WRITE_CONTACTS"; // 写入联系人
    public static final String GET_ACCOUNTS = "android.permission.GET_ACCOUNTS"; // 访问账户列表
    public static final String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION"; // 获取精确位置
    public static final String ACCESS_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION"; // 获取粗略位置
    public static final String RECORD_AUDIO = "android.permission.RECORD_AUDIO"; // 录音权限
    public static final String READ_PHONE_STATE = "android.permission.READ_PHONE_STATE"; // 读取电话状态
    public static final String CALL_PHONE = "android.permission.CALL_PHONE"; // 拨打电话
    public static final String READ_CALL_LOG = "android.permission.READ_CALL_LOG"; // 读取通话记录
    public static final String WRITE_CALL_LOG = "android.permission.WRITE_CALL_LOG"; // 写入通话记录
    public static final String ADD_VOICEMAIL = "com.android.voicemail.permission.ADD_VOICEMAIL"; // 添加语音邮件
    public static final String USE_SIP = "android.permission.USE_SIP"; // 使用SIP视频
    public static final String PROCESS_OUTGOING_CALLS = "android.permission.PROCESS_OUTGOING_CALLS"; // 处理拨出电话
    public static final String ANSWER_PHONE_CALLS = "android.permission.ANSWER_PHONE_CALLS";// 8.0危险权限：允许您的应用通过编程方式接听呼入电话。要在您的应用中处理呼入电话，您可以使用 acceptRingingCall() 函数
    public static final String READ_PHONE_NUMBERS = "android.permission.READ_PHONE_NUMBERS";// 8.0危险权限：权限允许您的应用读取设备中存储的电话号码
    public static final String BODY_SENSORS = "android.permission.BODY_SENSORS"; // 传感器
    public static final String SEND_SMS = "android.permission.SEND_SMS"; // 发送短信
    public static final String RECEIVE_SMS = "android.permission.RECEIVE_SMS"; // 接收短信
    public static final String READ_SMS = "android.permission.READ_SMS"; // 读取短信
    public static final String RECEIVE_WAP_PUSH = "android.permission.RECEIVE_WAP_PUSH"; // 接收WAP PUSH信息
    public static final String RECEIVE_MMS = "android.permission.RECEIVE_MMS"; // 接收彩信
    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE"; // 读取外部存储
    public static final String WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE"; // 写入外部存储

    private Activity mActivity;
    private List<String> mPermissions = new ArrayList<>();
    private boolean mConstant;

    private ADPermissionManager(Activity activity) {
        mActivity = activity;
    }

    /**
     * 设置请求的对象
     */
    public static ADPermissionManager with(Activity activity) {
        return new ADPermissionManager(activity);
    }

    /**
     * 设置权限组
     */
    public ADPermissionManager permission(String... permissions) {
        mPermissions.addAll(Arrays.asList(permissions));
        return this;
    }

    /**
     * 设置权限组
     */
    public ADPermissionManager permission(String[]... permissions) {
        for (String[] group : permissions) {
            mPermissions.addAll(Arrays.asList(group));
        }
        return this;
    }

    /**
     * 设置权限组
     */
    public ADPermissionManager permission(List<String> permissions) {
        mPermissions.addAll(permissions);
        return this;
    }

    /**
     * 被拒绝后继续申请，直到授权或者永久拒绝
     */
    public ADPermissionManager constantRequest() {
        mConstant = true;
        return this;
    }

    /**
     * 请求权限
     */
    public void request(OnPermission call) {
        // 如果没有指定请求的权限，就使用清单注册的权限进行请求
        if (mPermissions == null || mPermissions.size() == 0) {
            mPermissions = getManifestPermissions(mActivity);
        }
        if (mPermissions == null || mPermissions.size() == 0) {
            throw new IllegalArgumentException("The requested permission cannot be empty");
        }
        //使用isFinishing方法Activity在熄屏状态下会导致崩溃
        //if (mActivity == null || mActivity.isFinishing()) throw new IllegalArgumentException("Illegal Activity was passed in");
        if (mActivity == null) {
            throw new IllegalArgumentException("The activity is empty");
        }
        if (call == null) {
            throw new IllegalArgumentException("The permission request callback interface must be implemented");
        }
        checkTargetSdkVersion(mActivity, mPermissions);
        List<String> failPermissions = getFailPermissions(mActivity, mPermissions);
        if (failPermissions == null || failPermissions.size() == 0) {
            //证明权限已经全部授予过
            call.hasPermission(mPermissions, true);
        } else {
            //检测权限有没有在清单文件中注册
            checkPermissions(mActivity, mPermissions);
            //申请没有授予过的权限
            PermissionFragment.newInstant((new ArrayList<>(mPermissions)), mConstant).prepareRequest(mActivity, call);
        }
    }

    /**
     * 检查某些权限是否全部授予了
     *
     * @param context     上下文对象
     * @param permissions 需要请求的权限组
     */
    public boolean isHasPermission(Context context, String... permissions) {
        List<String> failPermissions = getFailPermissions(context, Arrays.asList(permissions));
        return failPermissions == null || failPermissions.size() == 0;
    }

    /**
     * 检查某些权限是否全部授予了
     *
     * @param context     上下文对象
     * @param permissions 需要请求的权限组
     */
    public boolean isHasPermission(Context context, String[]... permissions) {
        List<String> permissionList = new ArrayList<>();
        for (String[] group : permissions) {
            permissionList.addAll(Arrays.asList(group));
        }
        List<String> failPermissions = getFailPermissions(context, permissionList);
        return failPermissions == null || failPermissions.size() == 0;
    }

    /**
     * 返回应用程序在清单文件中注册的权限
     *
     * @param context 上下文
     * @return List<String>
     */
    public List<String> getManifestPermissions(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            return Arrays.asList(pm.getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS).requestedPermissions);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 检查targetSdkVersion是否符合要求
     *
     * @param context            上下文对象
     * @param requestPermissions 请求的权限组
     */
    public void checkTargetSdkVersion(Context context, List<String> requestPermissions) {
        //检查是否包含了8.0的权限
        if (requestPermissions.contains(REQUEST_INSTALL_PACKAGES) || requestPermissions.contains(ANSWER_PHONE_CALLS) || requestPermissions.contains(READ_PHONE_NUMBERS)) {
            //必须设置 targetSdkVersion >= 26 才能正常检测权限
            if (context.getApplicationInfo().targetSdkVersion < Build.VERSION_CODES.O) {
                throw new RuntimeException("The targetSdkVersion SDK must be 26 or more");
            }
        } else {
            //必须设置 targetSdkVersion >= 23 才能正常检测权限
            if (context.getApplicationInfo().targetSdkVersion < Build.VERSION_CODES.M) {
                throw new RuntimeException("The targetSdkVersion SDK must be 23 or more");
            }
        }
    }

    /**
     * 获取没有授予的权限
     *
     * @param context     上下文对象
     * @param permissions 需要请求的权限组
     */
    public static List<String> getFailPermissions(Context context, List<String> permissions) {
        //如果是安卓6.0以下版本就返回null
        if (!isOverMarshmallow()) {
            return null;
        }
        List<String> failPermissions = null;
        for (String permission : permissions) {
            //检测安装权限
            if (permission.equals(REQUEST_INSTALL_PACKAGES)) {
                if (!isHasInstallPermission(context)) {
                    if (failPermissions == null) failPermissions = new ArrayList<>();
                    failPermissions.add(permission);
                }
                continue;
            }
            //检测悬浮窗权限
            if (permission.equals(SYSTEM_ALERT_WINDOW)) {
                if (!isHasOverlaysPermission(context)) {
                    if (failPermissions == null) failPermissions = new ArrayList<>();
                    failPermissions.add(permission);
                }
                continue;
            }
            //检测8.0的两个新权限
            if (permission.equals(ANSWER_PHONE_CALLS) || permission.equals(READ_PHONE_NUMBERS)) {
                //检查当前的安卓版本是否符合要求
                if (!isOverOreo()) {
                    continue;
                }
            }
            //把没有授予过的权限加入到集合中
            if (context.checkSelfPermission(permission) == PackageManager.PERMISSION_DENIED) {
                if (failPermissions == null) failPermissions = new ArrayList<>();
                failPermissions.add(permission);
            }
        }

        return failPermissions;
    }

    /**
     * 获取没有授予的权限
     *
     * @param permissions  需要请求的权限组
     * @param grantResults 允许结果组
     */
    public static List<String> getFailPermissions(String[] permissions, int[] grantResults) {
        List<String> failPermissions = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            // 把没有授予过的权限加入到集合中，-1表示没有授予，0表示已经授予
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                failPermissions.add(permissions[i]);
            }
        }
        return failPermissions;
    }

    /**
     * 是否还能继续申请没有授予的权限
     *
     * @param activity        Activity对象
     * @param failPermissions 失败的权限
     */
    public static boolean isRequestDeniedPermission(Activity activity, List<String> failPermissions) {
        for (String permission : failPermissions) {
            // 检查是否还有权限还能继续申请的（这里指没有被授予的权限但是也没有被永久拒绝的）
            if (!checkSinglePermissionPermanentDenied(activity, permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查某个权限是否被永久拒绝
     *
     * @param activity   Activity对象
     * @param permission 请求的权限
     */
    public static boolean checkSinglePermissionPermanentDenied(Activity activity, String permission) {
        // 安装权限和浮窗权限不算，本身申请方式和危险权限申请方式不同，因为没有永久拒绝的选项，所以这里返回false
        if (permission.equals(REQUEST_INSTALL_PACKAGES) || permission.equals(SYSTEM_ALERT_WINDOW)) {
            return false;
        }
        // 检测8.0的两个新权限
        if (permission.equals(ANSWER_PHONE_CALLS) || permission.equals(READ_PHONE_NUMBERS)) {
            // 检查当前的安卓版本是否符合要求
            if (!isOverOreo()) {
                return false;
            }
        }
        if (isOverMarshmallow()) {
            return activity.checkSelfPermission(permission) == PackageManager.PERMISSION_DENIED && !activity.shouldShowRequestPermissionRationale(permission);
        }
        return false;
    }

    /**
     * 在权限组中检查是否有某个权限是否被永久拒绝
     *
     * @param activity    Activity对象
     * @param permissions 请求的权限
     */
    public static boolean checkMorePermissionPermanentDenied(Activity activity, List<String> permissions) {
        for (String permission : permissions) {
            if (checkSinglePermissionPermanentDenied(activity, permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取已授予的权限
     *
     * @param permissions  需要请求的权限组
     * @param grantResults 允许结果组
     */
    public static List<String> getSucceedPermissions(String[] permissions, int[] grantResults) {
        List<String> succeedPermissions = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            //把授予过的权限加入到集合中，-1表示没有授予，0表示已经授予
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                succeedPermissions.add(permissions[i]);
            }
        }
        return succeedPermissions;
    }


    /**
     * 是否是6.0以上版本
     */
    public static boolean isOverMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * 是否是8.0以上版本
     */
    public static boolean isOverOreo() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }

    /**
     * 是否有安装权限
     *
     * @param context 上下文
     * @return boolean
     */
    public static boolean isHasInstallPermission(Context context) {
        if (isOverOreo()) {
            return context.getPackageManager().canRequestPackageInstalls();
        }
        return true;
    }

    /**
     * 是否有悬浮窗权限
     *
     * @param context 上下文
     * @return boolean
     */
    public static boolean isHasOverlaysPermission(Context context) {
        if (isOverMarshmallow()) {
            return Settings.canDrawOverlays(context);
        }
        return true;
    }

    /**
     * 检测权限有没有在清单文件中注册
     *
     * @param activity           Activity对象
     * @param requestPermissions 请求的权限组
     */
    public void checkPermissions(Activity activity, List<String> requestPermissions) {
        List<String> manifest = getManifestPermissions(activity);
        if (manifest != null && manifest.size() != 0) {
            for (String permission : requestPermissions) {
                if (!manifest.contains(permission)) {
                    throw new ManifestRegisterException(permission);
                }
            }
        } else {
            throw new ManifestRegisterException(null);
        }
    }

    public interface OnPermission {

        /**
         * 是否权限已申请
         *
         * @param mPermissions 权限列表
         * @param isAll        true（ 所有权限已申请 ） false（ 部分权限未申请 ）
         */
        void hasPermission(List<String> mPermissions, boolean isAll);


        void noPermission(List<String> failPermissions, boolean checkMorePermission);
    }

    public static class ManifestRegisterException extends RuntimeException {

        ManifestRegisterException(String permission) {
            super(permission == null ? "No permissions are registered in the manifest file" : (permission + ": Permissions are not registered in the manifest file"));
        }
    }

    public static class PermissionFragment extends Fragment implements Runnable {

        private static final String PERMISSION_GROUP = "permission_group";//请求的权限
        private static final String REQUEST_CODE = "request_code";
        private static final String REQUEST_CONSTANT = "request_constant";

        private final static SparseArray<OnPermission> sContainer = new SparseArray<>();

        public static PermissionFragment newInstant(ArrayList<String> permissions, boolean constant) {
            PermissionFragment fragment = new PermissionFragment();
            Bundle bundle = new Bundle();
            int requestCode;
            //请求码随机生成，避免随机产生之前的请求码，必须进行循环判断
            do {
                //requestCode = new Random().nextInt(65535);//Studio编译的APK请求码必须小于65536
                requestCode = new Random().nextInt(255);//Eclipse编译的APK请求码必须小于256
            } while (sContainer.get(requestCode) != null);

            bundle.putInt(REQUEST_CODE, requestCode);
            bundle.putStringArrayList(PERMISSION_GROUP, permissions);
            bundle.putBoolean(REQUEST_CONSTANT, constant);
            fragment.setArguments(bundle);
            return fragment;
        }

        /**
         * 准备请求
         */
        public void prepareRequest(Activity activity, OnPermission call) {
            //将当前的请求码和对象添加到集合中
            sContainer.put(getArguments().getInt(REQUEST_CODE), call);
            activity.getFragmentManager().beginTransaction().add(this, activity.getClass().getName()).commit();
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            ArrayList<String> permissions = getArguments().getStringArrayList(PERMISSION_GROUP);
            if (permissions == null) {
                return;
            }
            if ((permissions.contains(REQUEST_INSTALL_PACKAGES) && !isHasInstallPermission(getActivity())) || (permissions.contains(SYSTEM_ALERT_WINDOW) && !isHasOverlaysPermission(getActivity()))) {
                if (permissions.contains(REQUEST_INSTALL_PACKAGES) && !isHasInstallPermission(getActivity())) {
                    //跳转到允许安装未知来源设置页面
                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:" + getActivity().getPackageName()));
                    startActivityForResult(intent, getArguments().getInt(REQUEST_CODE));
                }
                if (permissions.contains(SYSTEM_ALERT_WINDOW) && !isHasOverlaysPermission(getActivity())) {
                    //跳转到悬浮窗设置页面
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getActivity().getPackageName()));
                    startActivityForResult(intent, getArguments().getInt(REQUEST_CODE));
                }
            } else {
                requestPermission();
            }
        }

        /**
         * 请求权限
         */
        public void requestPermission() {
            if (isOverMarshmallow()) {
                ArrayList<String> permissions = getArguments().getStringArrayList(PERMISSION_GROUP);
                requestPermissions(Objects.requireNonNull(permissions).toArray(new String[permissions.size() - 1]), getArguments().getInt(REQUEST_CODE));
            }
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            OnPermission call = sContainer.get(requestCode);
            //根据请求码取出的对象为空，就直接返回不处理
            if (call == null) {
                return;
            }
            for (int i = 0; i < permissions.length; i++) {
                // 重新检查安装权限
                if (REQUEST_INSTALL_PACKAGES.equals(permissions[i])) {
                    if (isHasInstallPermission(getActivity())) {
                        grantResults[i] = PackageManager.PERMISSION_GRANTED;
                    } else {
                        grantResults[i] = PackageManager.PERMISSION_DENIED;
                    }
                }
                // 重新检查悬浮窗权限
                if (SYSTEM_ALERT_WINDOW.equals(permissions[i])) {
                    if (isHasOverlaysPermission(getActivity())) {
                        grantResults[i] = PackageManager.PERMISSION_GRANTED;
                    } else {
                        grantResults[i] = PackageManager.PERMISSION_DENIED;
                    }
                }
                //重新检查8.0的两个新权限
                if (permissions[i].equals(ANSWER_PHONE_CALLS) || permissions[i].equals(READ_PHONE_NUMBERS)) {

                    //检查当前的安卓版本是否符合要求
                    if (!isOverOreo()) {
                        grantResults[i] = PackageManager.PERMISSION_GRANTED;
                    }
                }
            }
            // 获取授予权限
            List<String> succeedPermissions = getSucceedPermissions(permissions, grantResults);
            // 如果请求成功的权限集合大小和请求的数组一样大时证明权限已经全部授予
            if (succeedPermissions.size() == permissions.length) {
                // 代表申请的所有的权限都授予了
                call.hasPermission(succeedPermissions, true);
            } else {
                // 获取拒绝权限
                List<String> failPermissions = getFailPermissions(permissions, grantResults);
                // 检查是否开启了继续申请模式，如果是则检查没有授予的权限是否还能继续申请
                if (getArguments().getBoolean(REQUEST_CONSTANT) && isRequestDeniedPermission(getActivity(), failPermissions)) {
                    // 如果有的话就继续申请权限，直到用户授权或者永久拒绝
                    requestPermission();
                    return;
                }
                // 代表申请的权限中有不同意授予的，如果有某个权限被永久拒绝就返回true给开发人员，让开发者引导用户去设置界面开启权限
                call.noPermission(failPermissions, checkMorePermissionPermanentDenied(getActivity(), failPermissions));
                // 证明还有一部分权限被成功授予，回调成功接口
                if (!succeedPermissions.isEmpty()) {
                    call.hasPermission(succeedPermissions, false);
                }
            }
            // 权限回调结束后要删除集合中的对象，避免重复请求
            sContainer.remove(requestCode);
            getFragmentManager().beginTransaction().remove(this).commit();
        }

        private boolean isBackCall;//是否已经回调了，避免安装权限和悬浮窗同时请求导致的重复回调

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            //super.onActivityResult(requestCode, resultCode, data);
            if (!isBackCall && requestCode == getArguments().getInt(REQUEST_CODE)) {
                isBackCall = true;
                // 需要延迟执行，不然有些华为机型授权了但是获取不到权限
                getActivity().getWindow().getDecorView().postDelayed(this, 500);
            }
        }

        /**
         * {@link Runnable#run()}
         */
        @Override
        public void run() {
            //请求其他危险权限
            requestPermission();
        }
    }
}
