package com.liudonghan.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.heytap.msp.push.HeytapPushManager;
import com.liudonghan.utils.ADNetworkStateUtils;
import com.liudonghan.utils.ADPicturePhotoUtils;
import com.liudonghan.utils.ADRegexUtils;
import com.liudonghan.utils.ADScreenRecordUtils;
import com.liudonghan.utils.ADTextStyleUtils;

import java.io.File;


public class MainActivity extends AppCompatActivity implements ADPicturePhotoUtils.ADImageFileCallback, ADNetworkStateUtils.OnNetworkStateUtilsChangeListener {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.activity_main_tv);
        // 我通过了你的好友验证请求，12现在我们可以开始聊天了   13534536434和18647499996和15210176281
        Log.d("Mac_Liu", "验证字符串：" + ADRegexUtils.getInstance().getMobileAcute("123456789123"));
        textView.setOnClickListener(view -> {
            ADTextStyleUtils.getInstance()
                    // 上下文
                    .from(MainActivity.this)
                    // 构建view
                    .findView(textView)
                    // 原始文本属性
                    .originAttr(22, R.color.colorAccent)
                    // 改变文本属性
                    .changeAttr(14, R.color.colorPrimary)
                    // 改变位置
                    .changePosition(0, 1)
                    // 设置文本图标属性
                    .drawablesIconAttr(R.mipmap.ic_launcher)
                    // 设置图标大小
                    .drawablesSize(textView.getHeight(), textView.getHeight())
                    // 设置文本
                    .textDescription("￥18990.00")
                    // 构建
                    .builder();
            ADScreenRecordUtils.getInstance().register(MainActivity.this, isScreenRecord -> Log.d("录屏监听：", String.valueOf(isScreenRecord)));
        });
        ADPicturePhotoUtils.getInstance().init(this).onCallBack(this);
        ADNetworkStateUtils.getInstance().setNetworkStateListener(this, this);
    }

    @Override
    public void handleResult(File file) {

    }

    @Override
    public void onConnect(ADNetworkStateUtils.NetType netType) {
        switch (netType) {
            case AUTO:
                Log.d("Mac_Liu","网络监听：任意网络");
                break;
            case NONE:
                Log.d("Mac_Liu","网络监听：无网络");
                break;
            case WIFI:
                Log.d("Mac_Liu","网络监听：WIFI");
                break;
            case CMNET:
                Log.d("Mac_Liu","网络监听：CMNET");
                break;
            case CMWAP:
                Log.d("Mac_Liu","网络监听：手机网络");
                break;
        }
    }

    @Override
    public void onDisConnect() {

    }
}