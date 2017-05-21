package com.example.administrator.my_news.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.my_news.Adapter.Adpter_tuku;
import com.example.administrator.my_news.Adapter.TuKuAdpter;
import com.example.administrator.my_news.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/21 0021.
 */

public class Activity_test extends AppCompatActivity {
    //底部栏的跳转
    private LinearLayout ln_zhuye;
    private LinearLayout ln_yaowen;
    private LinearLayout ln_yonghu;
    private ImageView img_zhuye;
    private TextView tv_zhuye;

    private RecyclerView recyclerView;
    private Adpter_tuku tuku;
    private List<String> first_texts;
    private List<String> second_texts;
    private List<Integer> first_pic;
    private List<Integer> second_pic;
    private List<String> first_urls;
    private List<String> second_urls;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //初始化数据
        first_pic = new ArrayList<>();
        first_texts = new ArrayList<>();
        second_pic = new ArrayList<>();
        second_texts = new ArrayList<>();
        first_urls = new ArrayList<>();
        second_urls = new ArrayList<>();

        //初始化数据
        //新闻标题
        first_texts.add("社会新闻");
        first_texts.add("中国明星");
        first_texts.add("欧美明星");
        first_texts.add("韩国明星");
        first_texts.add("娱乐组图");
        first_texts.add("电影海报");
        first_texts.add("影视剧照");
        first_texts.add("清纯美女");
        first_texts.add("气质美女");
        first_texts.add("可爱唯美");
        first_texts.add("美丽校花");
        //新闻图片
        first_urls.add("http://ali-pic.showapi.com/852-2?page=1&type=1001");
        first_urls.add("http://ali-pic.showapi.com/852-2?page=1&type=2001");
        first_urls.add("http://ali-pic.showapi.com/852-2?page=1&type=2002");
        first_urls.add("http://ali-pic.showapi.com/852-2?page=1&type=2005");
        first_urls.add("http://ali-pic.showapi.com/852-2?page=1&type=3001");
        first_urls.add("http://ali-pic.showapi.com/852-2?page=1&type=3003");
        first_urls.add("http://ali-pic.showapi.com/852-2?page=1&type=3004");
        first_urls.add("http://ali-pic.showapi.com/852-2?page=1&type=4001");
        first_urls.add("http://ali-pic.showapi.com/852-2?page=1&type=4002");
        first_urls.add("http://ali-pic.showapi.com/852-2?page=1&type=4003");
        first_urls.add("http://ali-pic.showapi.com/852-2?page=1&type=4004");

        //设置图库列表
        recyclerView = (RecyclerView) findViewById(R.id.recyleview);
        tuku = new Adpter_tuku(first_texts,first_urls,Activity_test.this);
        LinearLayoutManager manger = new LinearLayoutManager(Activity_test.this);
        recyclerView.setLayoutManager(manger);
        //绘制分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(Activity_test.this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(tuku);


        //初始化底部栏
        ln_zhuye = (LinearLayout) findViewById(R.id.ln_zhuye);
        ln_yaowen = (LinearLayout) findViewById(R.id.ln_yaowen);
        img_zhuye = (ImageView) findViewById(R.id.img);
        tv_zhuye = (TextView) findViewById(R.id.tv);
        ln_yonghu = (LinearLayout) findViewById(R.id.ln_yonghu);
        ln_yaowen.setBackgroundColor(Color.parseColor("#DC143C"));
        tv_zhuye.setTextColor(Color.parseColor("#ffffff"));
        ln_yonghu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Activity_test.this, Activity_user.class);
                startActivity(intent);
            }
        });
        ln_zhuye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Activity_test.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ln_yaowen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ln_yaowen.setBackgroundColor(Color.parseColor("#DC143C"));
                tv_zhuye.setTextColor(Color.parseColor("#ffffff"));
            }
        });
    }
}
