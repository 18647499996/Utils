package com.liudonghan.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.liudonghan.utils.ADNetworkUtils;
import com.liudonghan.utils.ADNotificationManager;

public class TestActivity extends AppCompatActivity implements ADNetworkUtils.OnNetworkUtilsChangeListener {

    private int notifyId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button button = findViewById(R.id.btn1);
        button.setText(String.valueOf(getIntent().getIntExtra("int", 0)));
        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 打开通知权限
                ADNotificationManager.getInstance().openNotifySetting(TestActivity.this);
            }
        });
    }

    @Override
    public void onConnect(ADNetworkUtils.NetworkType netType) {
        switch (netType) {
            case NETWORK_5G:
                Log.d("Mac_Liu", "网络监听：5G  Test");
                break;
            case NETWORK_WIFI:
                Log.d("Mac_Liu", "网络监听：WIFI  Test");
                break;
            case NETWORK_2G:
                Log.d("Mac_Liu", "网络监听：2G");
                break;
            case NETWORK_3G:
                Log.d("Mac_Liu", "网络监听：3G");
                break;
            case NETWORK_4G:
                Log.d("Mac_Liu", "网络监听：4G");
                break;
            default:
                Log.d("Mac_Liu", "网络监听：无网络");
                break;
        }
    }

    @Override
    public void onDisConnect() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}