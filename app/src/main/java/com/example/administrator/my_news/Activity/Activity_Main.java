package com.example.administrator.my_news.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.my_news.R;

import java.lang.reflect.Method;
import java.util.List;

public class Activity_Main extends AppCompatActivity {
    private ImageView img_back;
    private ImageView img_setting;
    private WebView webView;
    private ProgressBar progressBar;

    WebSettings seting;
    PopupMenu popupMenu;
    Menu menu;

    private  ImageView imt_share;
    private ImageView img_shoucang;
    private boolean isFirst = true;
    private ImageView img_comment;

    private Button btn_send;
    private EditText edt_comment;
    private SQLiteDatabase db;

    String  title;
    String url;
    String img_urls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        url = intent.getStringExtra("url");
        img_urls = intent.getStringExtra("img_url");
        initView();
        initbottom();
    }

    private void initbottom() {
        img_comment = (ImageView) findViewById(R.id.img_pinglun);
        img_shoucang = (ImageView) findViewById(R.id.img_shoucang);
        btn_send = (Button) findViewById(R.id.btn_send);
        edt_comment = (EditText) findViewById(R.id.edt_comment);
        //评论
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = edt_comment.getText().toString();
                if(content!=null) {
                    Toast.makeText(Activity_Main.this, "评论成功", Toast.LENGTH_LONG).show();
                    edt_comment.setText("");
                }
            }
        });
        //收藏
        img_shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFirst){
                    db = openOrCreateDatabase("MyNews",MODE_PRIVATE,null);
                    db.execSQL("CREATE TABLE IF NOT EXISTS info (titles varchar(50), urls varchar(50), img_urls varchar(50))");
                    db.execSQL("insert into info(titles,urls,img_urls) values('" + title + "','" + url + "','" + img_urls + "')");
                    img_shoucang.setImageResource(R.drawable.s2);
                    Toast.makeText(Activity_Main.this,"收藏新闻成功",Toast.LENGTH_LONG).show();
                    isFirst = false;
                }else{
                    db.execSQL("delete from info where titles = ?",new String[]{title});
                    img_shoucang.setImageResource(R.drawable.s1);
                    Toast.makeText(Activity_Main.this,"取消收藏成功",Toast.LENGTH_LONG).show();
                    isFirst =true;
                }
            }
        });
    }


    private void initView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        img_setting = (ImageView) findViewById(R.id.img_setting);
        webView = (WebView) findViewById(R.id.webView);


        //菜单栏的加载
        popupMenu = new PopupMenu(this, img_setting);
        menu = popupMenu.getMenu();
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.demo07_popup_menu, menu);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        //跳转回主界面
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Activity_Main.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //加载页面
        Intent intent = getIntent();
        final String url = intent.getStringExtra("url");
        webView.loadUrl(url);
        seting = webView.getSettings();
        seting.setJavaScriptEnabled(true);//设置webview支持javascript脚本
        seting.setSupportZoom(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar.setProgress(newProgress);//设置进度值
                }
            }
        });
        //详情
        img_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIconEnable(menu,true);
                popupMenu.show();

            }
        });
        imt_share = (ImageView) findViewById(R.id.img_share);
        //分享
        imt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "我看到一篇精彩的新闻，网址是"+url);
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, "分享到"));
            }
        });

        // 监听事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        Toast.makeText(Activity_Main.this, "收藏",
                                Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setClass(Activity_Main.this, Activity_shoucang.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.item2:
                        Intent shareIntent = new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND);
                        shareIntent.putExtra(Intent.EXTRA_TEXT, "我看到一篇精彩的新闻，网址是"+url);
                        shareIntent.setType("text/plain");
                        startActivity(Intent.createChooser(shareIntent, "分享到"));


                        Toast.makeText(Activity_Main.this, "分享",
                                Toast.LENGTH_LONG).show();
                        break;
                    case R.id.item3:
                        Toast.makeText(Activity_Main.this, "评论",
                                Toast.LENGTH_LONG).show();
                        break;
                    case R.id.item4:
                        Toast.makeText(Activity_Main.this, "转发",
                                Toast.LENGTH_LONG).show();
                        break;
                    case R.id.item5:
                        Toast.makeText(Activity_Main.this, "字体",
                                Toast.LENGTH_LONG).show();
                        final String[] items = {"小","最小","正常","大","最大"};
                        AlertDialog dialog = new AlertDialog.Builder(Activity_Main.this).setTitle("字体大小").setIcon(R.drawable.font)
                                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
/*                                        Toast.makeText(Activity_Main.this, items[which], Toast.LENGTH_SHORT).show();*/
                                        switch (which) {

                                            case 0:
                                                seting.setTextSize(WebSettings.TextSize.SMALLEST);
                                                break;
                                            case 1:
                                                seting.setTextSize(WebSettings.TextSize.SMALLER);
                                                break;
                                            case 2:
                                                seting.setTextSize(WebSettings.TextSize.NORMAL);
                                                break;
                                            case 3:
                                                seting.setTextSize(WebSettings.TextSize.LARGER);
                                                break;
                                            case 4:
                                                seting.setTextSize(WebSettings.TextSize.LARGEST);
                                                break;
                                        }
                                        dialog.dismiss();
                                    }
                                }).create();
                        dialog.show();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }


    private void setIconEnable(Menu menu, boolean enable)
    {
        try
        {
            Class<?> clazz = Class.forName("com.android.internal.view.menu.MenuBuilder");
            Method m = clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            m.setAccessible(true);
            //传入参数
            m.invoke(menu, enable);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    //设置返回键动作（防止按返回键直接退出程序)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                webView.goBack();
                return true;
            } else {//当webview处于第一页面时,直接退出程序
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
