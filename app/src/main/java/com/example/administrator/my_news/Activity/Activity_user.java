package com.example.administrator.my_news.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.my_news.R;

/**
 * Created by Administrator on 2017/5/16 0016.
 */

public class Activity_user extends AppCompatActivity {
    //底部栏的跳转
    private LinearLayout ln_zhuye;
    private LinearLayout ln_yaowen;
    private LinearLayout ln_yonghu;
    private ImageView img_zhuye;
    private TextView tv_zhuye;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();

    }

    private void initView() {
        //初始化底部栏
        ln_zhuye = (LinearLayout) findViewById(R.id.ln_zhuye);
        ln_yaowen = (LinearLayout) findViewById(R.id.ln_yaowen);
        img_zhuye = (ImageView) findViewById(R.id.img);
        tv_zhuye = (TextView) findViewById(R.id.tv);
        ln_yonghu = (LinearLayout) findViewById(R.id.ln_yonghu);

        ln_yonghu.setBackgroundColor(Color.parseColor("#DC143C"));
        tv_zhuye.setTextColor(Color.parseColor("#ffffff"));


        ln_zhuye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Activity_user.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ln_yaowen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Activity_user.this, Activity_yaowen.class);
                startActivity(intent);
            }
        });
        ln_yonghu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ln_yaowen.setBackgroundColor(Color.parseColor("#DC143C"));
                tv_zhuye.setTextColor(Color.parseColor("#ffffff"));
            }
        });
    }
}
