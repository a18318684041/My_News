package com.example.administrator.my_news.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.my_news.Adapter.TuKuAdpter;
import com.example.administrator.my_news.R;
import com.example.administrator.my_news.bean.Img_Data;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/21 0021.
 */

public class Activity_Tuku extends AppCompatActivity {
    private ImageView imt_back;
    private RecyclerView recyclerView;
    private TuKuAdpter adpter;
    private List<String> urls;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuku);
        Log.d("AAA", "initView: ");
        urls = new ArrayList<>();
        final Intent intent = getIntent();
        urls.addAll(intent.getStringArrayListExtra("imgurls"));
        imt_back = (ImageView) findViewById(R.id.img_back);
        recyclerView = (RecyclerView) findViewById(R.id.recyleview);

        adpter = new TuKuAdpter(urls,Activity_Tuku.this);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
/*        LinearLayoutManager manger = new LinearLayoutManager(Activity_Tuku.this);
        recyclerView.setLayoutManager(manger);*/
        recyclerView.setAdapter(adpter);


        imt_back = (ImageView) findViewById(R.id.img_back);
        imt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Activity_Tuku.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
