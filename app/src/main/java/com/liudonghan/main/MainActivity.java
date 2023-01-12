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
                        .from(MainActivity.this)
                        .findView(textView)
                        .originAttr(22, R.color.colorAccent)
                        .changeAttr(14, R.color.colorPrimary)
                        .changePosition(0, 1)
                        .drawablesIconAttr(R.mipmap.ic_launcher, ADTextStyleUtils.Direction.BOTTOM, 18)
                        .drawablesSize(textView.getHeight(), textView.getHeight())
                        .textDescription("￥18990.00")
                        .builder();
            }
        });
    }
}