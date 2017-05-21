package com.example.administrator.my_news.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.my_news.R;
import com.example.administrator.my_news.Utils.SPUtils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class Activity_login extends AppCompatActivity {

    private TextView tv_username;
    private Button btn_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        //初始化Bmob客户端
        Bmob.initialize(this, "f8838f4d3eddeeab8f7c52ba7517a893");
        tv_username = (TextView) findViewById(R.id.tv_username);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        String username = BmobUser.getCurrentUser().getUsername();
        tv_username.setText(username+",欢迎你");
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Activity_login.this,Activity_user.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
