package com.example.administrator.my_news.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.my_news.Activity.Activity_Tuku;
import com.example.administrator.my_news.R;
import com.example.administrator.my_news.bean.Data;
import com.example.administrator.my_news.bean.Img_Data;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/5/21 0021.
 */

public class Adpter_tuku extends RecyclerView.Adapter<Adpter_tuku.ViewHolder>{
    private List<String> first_text;
    private List<String> urls;
    private Context context;

    private Img_Data data;
    private Gson gson = null;
    private ArrayList<String> img_urls;
    public  Adpter_tuku(List<String> first_text,List<String> urls,Context context){
        this.first_text =first_text;
        this.context = context;
        this.urls =urls;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tuku,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        img_urls = new ArrayList<>();
        holder.tv_type.setText(first_text.get(position));
        holder.ln_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //第一行的点击事件
                //使用OKhttpUtils进行网络请求
                String url = urls.get(position);
                String appcode = "6732a405ddb24ee6be13957c3161d2dc";
                Map<String, String> headers = new HashMap<String, String>();
                //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
                headers.put("Authorization", "APPCODE " + appcode);
                OkHttpUtils
                        .get()
                        .url(url)
                        .headers(headers)
                        .build()
                        .execute(new StringCallback()
                        {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.d("AAA", "onError: "+e.toString());
                                Toast.makeText(context,"请求失败",Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Toast.makeText(context,"请求成功",Toast.LENGTH_LONG).show();
                                /*Log.d("AAA", "onResponse: "+response.toString());*/
                                String result = response.toString();
                                gson = new Gson();
                                data = gson.fromJson(result.trim(), Img_Data.class);
/*                                for (int i = 0; i < data.getShowapi_res_body().getPagebean().getContentlist().get(0).getList().size(); i++) {
                                    img_urls.add(data.getShowapi_res_body().getPagebean().getContentlist().get(0).getList().get(i).getMiddle());
                                }*/
                                for (int k = 0; k < data.getShowapi_res_body().getPagebean().getContentlist().size(); k++) {
                                    for (int i = 0; i < data.getShowapi_res_body().getPagebean().getContentlist().get(k).getList().size(); i++)
                                        img_urls.add(data.getShowapi_res_body().getPagebean().getContentlist().get(k).getList().get(i).getBig());
                                }
                                Intent intent = new Intent();
                                intent.setClass(context, Activity_Tuku.class);
                                intent.putStringArrayListExtra("imgurls", img_urls);
                                context.startActivity(intent);
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return first_text.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_type;
        private LinearLayout ln_first;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_type = (TextView) itemView.findViewById(R.id.tv_type);
            ln_first = (LinearLayout) itemView.findViewById(R.id.first);
        }
    }
}
