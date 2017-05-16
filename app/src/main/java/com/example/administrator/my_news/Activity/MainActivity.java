package com.example.administrator.my_news.Activity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.my_news.Adapter.FragmentAdpter;
import com.example.administrator.my_news.Adapter.TopAdpter;
import com.example.administrator.my_news.Fragment.tab1;
import com.example.administrator.my_news.Fragment.tab2;
import com.example.administrator.my_news.Fragment.tab3;
import com.example.administrator.my_news.Fragment.tab4;
import com.example.administrator.my_news.R;
import com.example.administrator.my_news.Receiver.Myreceiver;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragments;
    private FragmentAdpter adpter;
    Myreceiver netWorkStateReceiver;

    //监听网络的变化
    @Override
    protected void onResume() {
        super.onResume();
        if (netWorkStateReceiver == null) {
            netWorkStateReceiver = new Myreceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkStateReceiver, filter);
        System.out.println("注册");
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.vp);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        fragments =  new ArrayList<>();
        fragments.add(new tab1());
        fragments.add(new tab2());
        fragments.add(new tab3());
        fragments.add(new tab4());
        adpter = new FragmentAdpter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adpter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
