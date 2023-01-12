package com.liudonghan.main;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.liudonghan.utils.ADTextStyleUtils;


public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.activity_main_tv);
        findViewById(R.id.activity_main_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });
    }
}