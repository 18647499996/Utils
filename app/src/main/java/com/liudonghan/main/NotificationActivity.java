package com.liudonghan.main;

import android.app.NotificationChannel;
import android.net.Uri;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.mvp.ADBasePresenter;
import com.liudonghan.utils.ADNetworkUtils;
import com.liudonghan.utils.ADNotificationManager;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:3/29/23
 */
public class NotificationActivity extends ADBaseActivity<ADBasePresenter> implements ADNetworkUtils.OnNetworkUtilsChangeListener {

    private static final String TAG = "Mac_Liu";
    protected ADNetworkUtils.NetworkReceive networkReceive;
    @BindView(R.id.activity_btn1)
    Button activityBtn1;
    @BindView(R.id.activity_btn2)
    Button activityBtn2;
    @BindView(R.id.activity_btn3)
    Button activityBtn3;
    @BindView(R.id.activity_btn4)
    Button activityBtn4;
    @BindView(R.id.activity_btn5)
    Button activityBtn5;
    @BindView(R.id.activity_btn6)
    Button activityBtn6;
    @BindView(R.id.activity_btn7)
    Button activityBtn7;
    @BindView(R.id.activity_btn8)
    Button activityBtn8;
    @BindView(R.id.activity_btn9)
    Button activityBtn9;
    @BindView(R.id.activity_btn10)
    Button activityBtn10;

    private int notifyId;

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_notify;
    }

    @Override
    protected Object initBuilderTitle() throws RuntimeException {
        return null;
    }

    @Override
    protected ADBasePresenter createPresenter() throws RuntimeException {
        return (ADBasePresenter) mPresenter;
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        networkReceive = ADNetworkUtils.getInstance().setNetworkListener(this, this);
    }

    @Override
    protected void addListener() throws RuntimeException {

    }

    @OnClick({R.id.activity_btn1, R.id.activity_btn2, R.id.activity_btn3, R.id.activity_btn4, R.id.activity_btn5, R.id.activity_btn6,
            R.id.activity_btn7, R.id.activity_btn8, R.id.activity_btn9, R.id.activity_btn10})
    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException {
        switch (view.getId()) {
            case R.id.activity_btn1:
                // 创建通知渠道
                ADNotificationManager.getInstance().set(this,
                        new ADNotificationManager
                                .NotificationChannelBuilder("100100", "下载通知")
                                .builder(),
                        new ADNotificationManager
                                .NotificationChannelBuilder("100200", "即时通讯")
                                .setSound(ADNotificationManager.getInstance().getActualDefaultRingtoneUri(this))
                                .builder(),
                        new ADNotificationManager
                                .NotificationChannelBuilder("100400", "运营推送")
                                .builder(),
                        ADNotificationManager.getInstance()
                                .with(this)
                                .setChannelId("100600")
                                .setChannelName("订阅通知")
                                .builder());
                ADNotificationManager.getInstance()
                        .with(this)
                        .setChannelId("100500")
                        .setChannelName("订单推送")
                        .create();
                break;
            case R.id.activity_btn2:
                // 发送通知
//                new ADNotificationManager.NotifyManagerBuilder(TestActivity.this)
//                        .title("OPPO Find X6 Pro，强势来袭！")
//                        .content("时限24期免息，超光影三主摄，赶紧点击速抢>>")
//                        .channelId("100100")
//                        .send();
                ADNotificationManager.getInstance()
                        .from(this)
                        .title("OPPO Find X6 Pro，强势来袭！")
                        .content("时限24期免息，超光影三主摄，赶紧点击速抢>>")
                        .channelId("100200")
                        .notifyId(notifyId++)
                        .pendingIntent(TestActivity.class)
                        .send();
                break;
            case R.id.activity_btn3:
                // 打开应用通知设置
                ADNotificationManager.getInstance().openNotifySetting(this);
                break;
            case R.id.activity_btn4:
                // 打开渠道通知设置
                ADNotificationManager.getInstance().openNotifySetting(this, "100200");
                break;
            case R.id.activity_btn5:
                // 监测是否开启应用通知
                boolean notifySettingEnabled = ADNotificationManager.getInstance().isNotifySettingEnabled(this);
                Log.i(TAG, notifySettingEnabled ? "通知设置已开启" : "通知设置未开启");
                break;
            case R.id.activity_btn6:
                // 监测是否开启渠道通知
                boolean notifyChannelEnabled = ADNotificationManager.getInstance().isNotifyChannelEnabled(this, "100200");
                Log.i(TAG, notifyChannelEnabled ? "渠道通知已开启" : "渠道通知未开启");
                break;
            case R.id.activity_btn7:
                // 获取渠道通知信息
                NotificationChannel notificationChannel = ADNotificationManager.getInstance().getNotificationChannel(this, "100200");
                Log.i(TAG, "渠道信息：" + notificationChannel.toString());
                break;
            case R.id.activity_btn8:
                // 获取所有渠道通知信息
                List<NotificationChannel> notificationChannelList = ADNotificationManager.getInstance().getNotificationChannelList(this);
                for (int i = 0; i < notificationChannelList.size(); i++) {
                    Log.i(TAG, "渠道信息：" + notificationChannelList.get(i).toString());
                }
                break;
            case R.id.activity_btn9:
                // 删除通知渠道
                ADNotificationManager.getInstance().deleteNotificationChannel(this, "100200");
                break;
            case R.id.activity_btn10:
                // 获取通知栏消息
                Uri notificationMessage = ADNotificationManager.getInstance().getActualDefaultRingtoneUri(this);
//                Log.i(TAG, "通知消息：" + notificationMessage.toString());
                break;

        }
    }

    @Override
    protected void onDestroys() throws RuntimeException {
        ADNetworkUtils.getInstance().unregisterReceiver(this, networkReceive);
    }

    @Override
    public void onConnect(ADNetworkUtils.NetworkType netType) {

    }

    @Override
    public void onDisConnect() {

    }
}
