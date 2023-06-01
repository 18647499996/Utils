package com.liudonghan.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.liudonghan.utils.ADNetworkUtils;

public class TestActivity extends AppCompatActivity implements ADNetworkUtils.OnNetworkUtilsChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button button = findViewById(R.id.btn1);
        button.setText(String.valueOf(getIntent().getIntExtra("int",0)));
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