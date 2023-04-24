package com.liudonghan.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:4/19/23
 */
public class ADNotificationManager {

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
     * 添加推送渠道（ 单个 ）
     *
     * @param context     上下文
     * @param channelId   渠道ID
     * @param channelName 渠道名称
     */
    public void createNotificationChannel(Context context, String channelId, String channelName) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
                channel.setShowBadge(true);
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    /**
     * 添加推送渠道（ 多个 ）
     *
     * @param context             上下文
     * @param notificationChannel 渠道ID
     */
    public void createNotificationChannel(Context context, NotificationChannel... notificationChannel) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannels(Arrays.asList(notificationChannel));
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotificationChannel getNotificationChannelConfig(ChannelConfig channelConfig) {
        NotificationChannel notificationChannel = new NotificationChannel(channelConfig.getChannelId(), channelConfig.getChannelName(), NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.setShowBadge(channelConfig.isShowBadge());
        return notificationChannel;
    }

    private static class ChannelConfig {

        private String channelId;
        private String channelName;
        private boolean isShowBadge;

        public ChannelConfig() {

        }

        public ChannelConfig(String channelId, String channelName) {
            this.channelId = channelId;
            this.channelName = channelName;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public boolean isShowBadge() {
            return isShowBadge;
        }

        public void setShowBadge(boolean showBadge) {
            isShowBadge = showBadge;
        }
    }
}
