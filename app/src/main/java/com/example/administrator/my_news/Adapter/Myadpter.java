package com.example.administrator.my_news.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.administrator.my_news.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/5 0005.
 */
public class Myadpter extends BaseAdapter {

    private List<String> title;
    private List<String> head;
    private Context context;

    public Myadpter(List<String> title, List<String> head, Context context) {
        this.title = title;
        this.head = head;
        this.context = context;
    }

    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public Object getItem(int position) {
        return title.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.txt);
        tv.setText(title.get(position));
        final ImageView img = (ImageView) convertView.findViewById(R.id.img);
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils
                        .get()
                        .url(head.get(position))
                        .build()
                        .execute(new BitmapCallback()
                        {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(Bitmap response, int id) {
                                img.setImageBitmap(response);
                            }
                        });

            }
        }).start();
        return convertView;
    }
}
