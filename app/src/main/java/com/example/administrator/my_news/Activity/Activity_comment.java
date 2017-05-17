package com.example.administrator.my_news.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import com.example.administrator.my_news.Adapter.CommentAdpter;
import com.example.administrator.my_news.Adapter.Myadpter;
import com.example.administrator.my_news.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/16 0016.
 */

public class Activity_comment extends AppCompatActivity {
    //评论页面
    private ImageView img_back;
    private RecyclerView recyclerView;
    private CommentAdpter adpter;
    private List<String> times = new ArrayList<>();
    private List<String> comments = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private SQLiteDatabase db;
    private  String url;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        initView();
    }

    private void initView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        recyclerView = (RecyclerView) findViewById(R.id.recyleview);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回到新闻详情页
                Intent intent = new Intent();
                intent.putExtra("url",url);
                intent.setClass(Activity_comment.this,Activity_Main.class);
                startActivity(intent);
            }
        });
        db = openOrCreateDatabase("MyNews",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS comments (times varchar(50), comments varchar(50), usernames varchar(50))");
        db = openOrCreateDatabase("MyNews",MODE_PRIVATE,null);
        Cursor cursor = db.rawQuery("select * from comments",null);
        if(cursor!=null) {
            while (cursor.moveToNext()) {
                String time = cursor.getString(cursor.getColumnIndex("times"));
                String comment = cursor.getString(cursor.getColumnIndex("comments"));
                String name = cursor.getString(cursor.getColumnIndex("usernames"));
                times.add(time);
                comments.add(comment);
                names.add(name);
            }
            cursor.close();
            db.close();
        }
        adpter = new CommentAdpter(Activity_comment.this,comments,names,times);
        LinearLayoutManager manger = new LinearLayoutManager(Activity_comment.this);
        recyclerView.setLayoutManager(manger);
        //绘制分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(Activity_comment.this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adpter);
    }
}
