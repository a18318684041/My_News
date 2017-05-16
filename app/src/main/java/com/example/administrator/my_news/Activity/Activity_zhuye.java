package com.example.administrator.my_news.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.my_news.Adapter.MyFragmentAdpter;
import com.example.administrator.my_news.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/16 0016.
 */

public class Activity_zhuye extends AppCompatActivity {
    //主页面的activity
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragments;
    private MyFragmentAdpter adpter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuye);
        initView();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.vp);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        fragments =  new ArrayList<>();
        fragments.add(new tab5());
        fragments.add(new tab6());
        fragments.add(new tab7());
        adpter = new MyFragmentAdpter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adpter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
