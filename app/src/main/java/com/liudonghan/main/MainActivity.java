package com.liudonghan.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.heytap.msp.push.HeytapPushManager;
import com.liudonghan.utils.ADPicturePhotoUtils;
import com.liudonghan.utils.ADRegexUtils;
import com.liudonghan.utils.ADScreenRecordUtils;
import com.liudonghan.utils.ADTextStyleUtils;

import java.io.File;


public class MainActivity extends AppCompatActivity implements ADPicturePhotoUtils.ADImageFileCallback {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.activity_main_tv);
        Log.d("MAC_LIU", "验证字符串：" + ADRegexUtils.getInstance().getMobileAcute("我通过了你的好友验证请求，12现在我们可以开始聊天了"));
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
    }

    @Override
    public void handleResult(File file) {

    }
}