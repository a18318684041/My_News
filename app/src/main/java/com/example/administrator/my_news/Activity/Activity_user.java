package com.example.administrator.my_news.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.my_news.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;

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

    //实现登录
    private EditText edt_phone;
    private EditText edt_code;
    private Button btn_getcode;
    private Button btn_login;
    private Handler handler;
    private Runnable runnable;
    private int num = 60;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();
        //实现登录
        initLogin();
    }

    private void initLogin() {
        Bmob.initialize(this, "f8838f4d3eddeeab8f7c52ba7517a893");
        //实现倒计时
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                num--;
                btn_getcode.setText(String.valueOf(num));
                if (num > 0) {
                    handler.postDelayed(this, 1000);
                    btn_getcode.setClickable(false);
                } else {
                    btn_getcode.setClickable(true);
                }
            }
        };
        //初始化登录组件
        edt_code = (EditText) findViewById(R.id.edt_code);
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_getcode = (Button) findViewById(R.id.btn_code);
        btn_getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = edt_phone.getText().toString();
                //倒计时
                handler.postDelayed(runnable, 1000);
                BmobSMS.requestSMSCode(phone, "test", new QueryListener<Integer>() {

                    @Override
                    public void done(Integer smsId, BmobException ex) {
                        if (ex == null) {
                            Log.i("smile", "短信id：" + smsId);//用于查询本次短信发送详情
                        }
                    }
                });
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在点击一次之后将可点击时间变为不可点
                btn_login.setClickable(false);
                String phone = edt_phone.getText().toString();
                String code = edt_code.getText().toString();
                BmobUser.signOrLoginByMobilePhone(phone, code, new LogInListener<BmobUser>() {

                    @Override
                    public void done(BmobUser user, BmobException e) {
                        if (user != null) {
                            //在登录完成以后可以再次点击
                            btn_login.setClickable(true);
                            Toast.makeText(Activity_user.this, "登录成功", Toast.LENGTH_LONG).show();
                            //登录成功后跳转至详情页
                            Intent intent = new Intent();
                            intent.setClass(Activity_user.this, Activity_login.class);
                            startActivity(intent);
                        } else {
                            //登录失败后也可以继续点击
                            btn_login.setClickable(true);
                            Toast.makeText(Activity_user.this, "登录失败", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
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
                intent.setClass(Activity_user.this, Activity_test.class);
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