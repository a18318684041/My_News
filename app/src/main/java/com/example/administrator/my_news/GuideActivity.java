package com.example.administrator.my_news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;


public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    //实现APP引导页面
    private ViewPager viewPager;
    private int[] imgArray;
    private List<View> viewList;
    private ViewGroup viewGroup;
    private GuideAdpter adpter;

    //底部小圆点的实现
    private ImageView img_point;
    private ImageView[] img_pointArray;

    //最后一页的按钮
    private Button btn_start;

    //判断用户是否是第一次安装
    private boolean isFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        btn_start = (Button) findViewById(R.id.btn_start);
        //实例化引导页的图片
        imgArray = new int[]{R.drawable.new1, R.drawable.new2, R.drawable.new3};
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        imgArray = new int[]{R.drawable.new1, R.drawable.new2, R.drawable.new3};
        viewList = new ArrayList<>();
        //获取一个布局的参数
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        //创建循环将数据导入ArrayList中
        for (int i = 0; i < imgArray.length; i++) {
            ImageView img = new ImageView(this);
            img.setLayoutParams(params);
            img.setBackgroundResource(imgArray[i]);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewList.add(img);
        }
        adpter = new GuideAdpter(viewList);
        viewPager.setAdapter(adpter);
        //设置滑动的监听
        viewPager.setOnPageChangeListener(this);


        //加载底部的小圆点
        viewGroup = (ViewGroup) findViewById(R.id.guide_ll_point);
        img_pointArray = new ImageView[viewList.size()];
        for (int i = 0; i < viewList.size(); i++) {
            img_point = new ImageView(this);
            img_point.setLayoutParams(new ViewGroup.LayoutParams(50, 50));
            img_point.setPadding(30, 0, 30, 0);
            img_pointArray[i] = img_point;
            if (i == 0) {
                img_point.setBackgroundResource(R.drawable.point_end);
            } else {
                img_point.setBackgroundResource(R.drawable.point_start);
            }
            //将数组中的ImageView加入到ViewGroup
            viewGroup.addView(img_pointArray[i]);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //循环设置当前页的标记图
        int length = imgArray.length;
        for (int i = 0; i < length; i++) {
            img_pointArray[position].setBackgroundResource(R.drawable.point_end);
            if (position != i) {
                img_pointArray[i].setBackgroundResource(R.drawable.point_start);
            }
        }

        //判断是否是最后一页，若是则显示按钮
        if (position == imgArray.length - 1) {
            btn_start.setVisibility(View.VISIBLE);
        } else {
            btn_start.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}