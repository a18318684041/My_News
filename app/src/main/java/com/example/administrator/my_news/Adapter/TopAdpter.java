package com.example.administrator.my_news.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.my_news.Activity.Activity_Main;
import com.example.administrator.my_news.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class TopAdpter extends RecyclerView.Adapter<TopAdpter.ViewHolder> {

    private Context context;
    private List<String> titles;
    private List<String> img_urls;
    private List<String> urls;

    public TopAdpter(Context context,List<String> titles,List<String> img_urls,List<String> urls){
        this.context = context;
        this.titles = titles;
        this.img_urls = img_urls;
        this.urls = urls;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(titles.get(position).length()>20){
            holder.txt.setText(titles.get(position).substring(0,20)+"...详情点击");
        }else {
            holder.txt.setText(titles.get(position));
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils
                        .get()
                        .url(img_urls.get(position))
                        .build()
                        .execute(new BitmapCallback()
                        {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(Bitmap response, int id) {
                                holder.img.setImageBitmap(response);
                            }
                        });
            }
        }).start();

        //跳转到详情页面
        holder.ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, Activity_Main.class);
                //传递url
                intent.putExtra("url",urls.get(position));
                intent.putExtra("img_url",img_urls.get(position));
                intent.putExtra("title",titles.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt;
        private ImageView img;
        private LinearLayout ln;
        public ViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.txt);
            img = (ImageView) itemView.findViewById(R.id.img);
            ln = (LinearLayout) itemView.findViewById(R.id.ln);
        }
    }
}
