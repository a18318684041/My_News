package com.example.administrator.my_news.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.my_news.Adapter.Myadpter;
import com.example.administrator.my_news.R;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Activity_shoucang extends AppCompatActivity {

    //收藏新闻的页面
    private RecyclerView recyclerView;
    private Myadpter myadpter;
    private List<String> titles;
    private List<String> img_urls;
    private List<String> urls;
    private SQLiteDatabase db;
    private ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoucang);
        initViews();
    }

    private void initViews() {
       img_back = (ImageView) findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Activity_shoucang.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        titles = new ArrayList<>();
        img_urls = new ArrayList<>();
        urls = new ArrayList<>();
        db = openOrCreateDatabase("MyNews",MODE_PRIVATE,null);
        Cursor cursor = db.rawQuery("select * from info",null);
        if(cursor!=null) {
            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndex("titles"));
                String url = cursor.getString(cursor.getColumnIndex("urls"));
                String img_url = cursor.getString(cursor.getColumnIndex("img_urls"));
                titles.add(title);
                urls.add(url);
                img_urls.add(img_url);
            }
            cursor.close();
            db.close();
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyleview);
        myadpter = new Myadpter(Activity_shoucang.this,titles,img_urls,urls);
        LinearLayoutManager manger = new LinearLayoutManager(Activity_shoucang.this);
        recyclerView.setLayoutManager(manger);
        //绘制分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(Activity_shoucang.this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(myadpter);
    }
}