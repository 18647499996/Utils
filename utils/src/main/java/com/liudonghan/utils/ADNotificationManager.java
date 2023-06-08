package com.liudonghan.utils;

import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.RawRes;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:4/19/23
 */
public class ADNotificationManager {

    private static final String TAG = "Mac_Liu";

    private static volatile ADNotificationManager instance = null;

    private ADNotificationManager() {
    }

    public static ADNotificationManager getInstance() {
        //single checkout
        if (null == instance) {
            synchronized (ADNotificationManager.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADNotificationManager();
                }
            }
        }
        return instance;
    }

    /**
     * 创建推送渠道（ 多个 ）
     *
     * @param context             上下文
     * @param notificationChannel 渠道ID
     */
    public void set(Context context, NotificationChannel... notificationChannel) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannels(Arrays.asList(notificationChannel));
            }
        }
    }

    /**
     * 是否开启应用通知
     *
     * @param context 上下文
     * @return true 是 false 否
     */
    public boolean isNotifySettingEnabled(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            return notificationManagerCompat.areNotificationsEnabled();
        }
        AppOpsManager mAppOps;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            try {
                mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
                Class appOpsClass = Class.forName(AppOpsManager.class.getName());
                Method checkOpNoThrowMethod = appOpsClass.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class);
                Field opPostNotificationValue = appOpsClass.getDeclaredField("OP_POST_NOTIFICATION");
                int value = (Integer) opPostNotificationValue.get(Integer.class);
                return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, context.getApplicationInfo().uid, context.getPackageName()) == AppOpsManager.MODE_ALLOWED);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 是否开启渠道通知
     *
     * @param context   上下文
     * @param channelId 渠道ID
     * @return true 是 false 否
     */
    public boolean isNotifyChannelEnabled(Context context, String channelId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = manager.getNotificationChannel(channelId);
            return channel.getImportance() != NotificationManager.IMPORTANCE_NONE;
        }
        return false;
    }

    /**
     * 打开应用推送设置
     *
     * @param context 上下文
     */
    public void openNotifySetting(Context context) {
        openNotifySetting(context, "");
    }

    /**
     * 打开手机系统设置（ 推送渠道 ）
     *
     * @param context   上下文
     * @param channelId 渠道ID
     */
    public void openNotifySetting(Context context, String channelId) {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //适配 8.0及8.0以上(8.0需要先打开应用通知，再打开渠道通知)
            if (TextUtils.isEmpty(channelId)) {
                intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
            } else {
                intent.setAction(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, channelId);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 适配 5.0及5.0以上
            intent.putExtra("app_package", context.getPackageName());
            intent.putExtra("app_uid", context.getApplicationInfo().uid);
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            // 适配 4.4及4.4以上
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else {
            intent.setAction(Settings.ACTION_SETTINGS);
        }
        context.startActivity(intent);
    }

    /**
     * todo 构建发送通知管理
     *
     * @param context 上下文
     * @return NotifyManagerBuilder
     */
    public NotifyManagerBuilder from(Context context) {
        return new NotifyManagerBuilder(context);
    }

    /**
     * todo 构建通知渠道管理
     *
     * @param context 上下文
     * @return NotificationChannelBuilder
     */
    public NotificationChannelBuilder with(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new NotificationChannelBuilder(context);
        }
        return null;
    }

    /**
     * todo 获取渠道通知信息
     *
     * @param context   上下文
     * @param channelId 渠道ID
     * @return NotificationChannel
     */
    public NotificationChannel getNotificationChannel(Context context, String channelId) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return manager.getNotificationChannel(channelId);
        }
        return null;
    }

    /**
     * todo 获取所有渠道通知List
     *
     * @param context 上下文
     * @return List<NotificationChannel>
     */
    public List<NotificationChannel> getNotificationChannelList(Context context) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return manager.getNotificationChannels();
        }
        return null;
    }

    /**
     * todo 删除渠道通知
     *
     * @param context   上下文
     * @param channelId 渠道ID
     */
    public void deleteNotificationChannel(Context context, String channelId) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.deleteNotificationChannel(channelId);
        }
    }

    /**
     * todo 清除所有通知
     *
     * @param context 上下文
     */
    public void clearNotificationAll(Context context) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancelAll();
    }

    /**
     * todo 获取系统默认铃声Uri
     *
     * @param context 上下文
     * @return StatusBarNotification
     */
    public Uri getActualDefaultRingtoneUri(Context context) {
        return RingtoneManager.getActualDefaultRingtoneUri(context, RingtoneManager.TYPE_RINGTONE);
    }

    /**
     * todo 获取raw资源目录文件
     *
     * @param context 上下文
     * @param rawId   文件ID
     * @return Uri
     */
    public Uri getRingtoneFile(Context context, @RawRes int rawId) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.getApplicationContext().getPackageName() + "/" + rawId);
    }

    public static class NotifyManagerBuilder {

        private Context context;
        private int iconSmall;
        private int iconLarge;
        private String title;
        private String content;
        private String channelId;
        private int notifyId;
        private Uri sound;
        private int priority = NotificationCompat.PRIORITY_DEFAULT;
        private Class<?> aClass;

        public NotifyManagerBuilder(Context context) {
            this.context = context;
        }

        public NotifyManagerBuilder iconSmall(int iconSmall) {
            this.iconSmall = iconSmall;
            return this;
        }

        public NotifyManagerBuilder iconLarge(int iconLarge) {
            this.iconLarge = iconLarge;
            return this;
        }

        public NotifyManagerBuilder title(String title) {
            this.title = title;
            return this;
        }

        public NotifyManagerBuilder content(String content) {
            this.content = content;
            return this;
        }

        public NotifyManagerBuilder priority(int priority) {
            this.priority = priority;
            return this;
        }

        public NotifyManagerBuilder channelId(String channelId) {
            this.channelId = channelId;
            return this;
        }

        public NotifyManagerBuilder setSound(Uri sound) {
            this.sound = sound;
            return this;
        }

        public NotifyManagerBuilder notifyId(int notifyId) {
            this.notifyId = notifyId;
            return this;
        }

        public NotifyManagerBuilder pendingIntent(Class<?> aClass) {
            this.aClass = aClass;
            return this;
        }

        public void send() {
            if (null == context) {
                Log.i(TAG, "context is null");
                return;
            }
            if (TextUtils.isEmpty(title)) {
                Log.i(TAG, "notify title is null");
                return;
            }
            if (TextUtils.isEmpty(content)) {
                Log.i(TAG, "notify content is null");
                return;
            }
            if (TextUtils.isEmpty(channelId)) {
                Log.i(TAG, "notify channel id is null");
            }

            NotificationCompat.Builder builder = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) ? new NotificationCompat.Builder(context, channelId) : new NotificationCompat.Builder(context);
            builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), iconLarge)) // 设置自动收报机和通知中显示的大图标。
                    .setSmallIcon(0 == iconSmall ? ADApplicationUtils.getAppIcon(context) : iconSmall) // 小图标
                    .setContentText(TextUtils.isEmpty(content) ? null : content) // 内容
                    .setContentTitle(TextUtils.isEmpty(title) ? null : title) // 标题
                    .setPriority(priority) // 设置优先级 PRIORITY_DEFAULT
//                    .setContent(view)
//                    .setNumber(notifyId)
                    .setSound(sound)
                    .setWhen(System.currentTimeMillis()) // 设置通知发送的时间戳
                    .setShowWhen(true)// 设置是否显示时间戳
                    .setAutoCancel(true)// 点击通知后通知在通知栏上消失
                    .setDefaults(Notification.PRIORITY_HIGH)// 设置默认的提示音、振动方式、灯光等 使用的默认通知选项
                    .setContentIntent(getPendingIntent()) // 设置通知的点击事件
                    .setFullScreenIntent(getPendingIntent(), true);// 悬挂式通知8.0需手动打开
//                .setColorized(true)
//                .setGroupSummary(true)// 将此通知设置为一组通知的组摘要
//                .setGroup(NEW_GROUP)// 使用组密钥
//                .setDeleteIntent(pendingIntent)// 当用户直接从通知面板清除通知时 发送意图
//                .setFullScreenIntent(pendingIntent,true)
//                .setLights()//希望设备上的LED闪烁的argb值以及速率
//                .setTimeoutAfter(3000)//指定取消此通知的时间（如果尚未取消）。

            // 通知栏id
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(notifyId, builder.build()); // build()方法需要的最低API为16 ,

        }

        private PendingIntent getPendingIntent() {
            PendingIntent pendingIntent = null;
            if (null != this.aClass) {
                Intent intent = new Intent(context, aClass);
                pendingIntent = PendingIntent.getActivity(context, 1, intent, FLAG_UPDATE_CURRENT);
            }
            return pendingIntent;
        }
    }

    public static class NotificationChannelBuilder {

        private String id;
        private CharSequence name;
        private int importance;
        private boolean showBadge = true;
        private String des;
        private String groupId;
        private Uri sound;
        private boolean enableLights = true;
        private boolean enableVibration = true;
        private int lockScreenVisibility;
        private boolean bypassDnd = true;
        private Context context;

        public NotificationChannelBuilder() {

        }

        public NotificationChannelBuilder(Context context) {
            this.context = context;
        }

        public NotificationChannelBuilder(String id, CharSequence name) {
            this.id = id;
            this.name = name;
        }

        public NotificationChannelBuilder setChannelId(String id) {
            this.id = id;
            return this;
        }

        public NotificationChannelBuilder setChannelName(String name) {
            this.name = name;
            return this;
        }

        public NotificationChannelBuilder setImportance(int importance) {
            this.importance = importance;
            return this;
        }

        public NotificationChannelBuilder setShowBadge(boolean showBadge) {
            this.showBadge = showBadge;
            return this;
        }

        public NotificationChannelBuilder setDescription(String des) {
            this.des = des;
            return this;
        }

        public NotificationChannelBuilder setSound(Uri sound) {
            this.sound = sound;
            return this;
        }

        public NotificationChannelBuilder setGroupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public NotificationChannelBuilder enableLights(boolean enableLights) {
            this.enableLights = enableLights;
            return this;
        }

        public NotificationChannelBuilder enableVibration(boolean enableVibration) {
            this.enableVibration = enableVibration;
            return this;
        }

        public NotificationChannelBuilder lockScreenVisibility(int lockScreenVisibility) {
            this.lockScreenVisibility = lockScreenVisibility;
            return this;
        }

        public NotificationChannelBuilder bypassDnd(boolean bypassDnd) {
            this.bypassDnd = bypassDnd;
            return this;
        }

        public NotificationChannel builder() {
            NotificationChannel notificationChannel = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                if (TextUtils.isEmpty(id)) {
                    Log.i(TAG, "notify channel id is null");
                }
                if (TextUtils.isEmpty(name)) {
                    Log.i(TAG, "notify channel name is null");
                }

                notificationChannel = new NotificationChannel(id, name, 0 == importance ? NotificationManager.IMPORTANCE_HIGH : importance);
                // 是否禁止该渠道使用角标
                notificationChannel.setShowBadge(showBadge);
                // 配置通知渠道的属性
                notificationChannel.setDescription(des);
                // 设置渠道组id
                notificationChannel.setGroup(groupId);
                // 设置通知出现时的闪灯（如果 android 设备支持的话）
                notificationChannel.enableLights(enableLights);
                // 设置通知出现时的震动（如果 android 设备支持的话）
                notificationChannel.enableVibration(enableVibration);
                // 设置通知出现时的铃声
                notificationChannel.setSound(sound, new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                        .build());
                // 设置锁屏是否显示通知
                notificationChannel.setLockscreenVisibility(0 == lockScreenVisibility ? Notification.VISIBILITY_PUBLIC : lockScreenVisibility);
                // 设置是否可以绕过请勿打扰模式
                notificationChannel.setBypassDnd(bypassDnd);
            }

            return notificationChannel;
        }

        public void create() {
            if (null == context) {
                Log.i(TAG, "context is null");
                return;
            }
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationManager.createNotificationChannel(builder());
                }
            }
        }
    }
}
