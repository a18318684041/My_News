package com.example.administrator.my_news.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.my_news.Adapter.TopAdpter;
import com.example.administrator.my_news.R;
import com.example.administrator.my_news.bean.Data;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


public class tab4 extends Fragment {
    String result;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tab4, container, false);
        //Button btn = (Button) view.findViewById(R.id.btn);
        final List<String> titles = new ArrayList<String>();
        final List<String> img_urls = new ArrayList<String>();
 /*       btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                List<String> url = new ArrayList<String>();
                OkHttpClient mOkHttpClient = new OkHttpClient();
                final Request request = new Request.Builder()
                        .url("http://v.juhe.cn/toutiao/index?type=&key=c22f0ceb7bfdc561e9aef2860b882f67")
                        .build();

                Call call = mOkHttpClient.newCall(request);

                call.enqueue(new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        result = response.body().string();
                        Log.d("AAA", "run: ");
                    }
                });

                OkHttpUtils
                        .get()
                        .url("http://v.juhe.cn/toutiao/index?type=&key=c22f0ceb7bfdc561e9aef2860b882f67")
                        .build()
                        .execute(new StringCallback()
                        {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                result = response;
                                if(result!=null) {
                                    Log.d("AAA", "run: " + result);
                                    //解析JSON数据
                                    Gson mGson = new Gson();
                                    Data data = new Data();
                                    data = mGson.fromJson(result.trim(), Data.class);
                                    data = mGson.fromJson(result, Data.class);
                                    for (int i = 0; i < data.getResult().getData().size(); i++) {
                                        Log.d("AAA", "run: " + data.getResult().getData().get(i).getTitle());
                                        titles.add(data.getResult().getData().get(i).getTitle());
                                        img_urls.add(data.getResult().getData().get(i).getThumbnail_pic_s());
                                    }
                                    RecyclerView recyleview = (RecyclerView) view.findViewById(R.id.recyleview);
                                    TopAdpter adpter = new TopAdpter(view.getContext(),titles,img_urls);

                                    LinearLayoutManager manger = new LinearLayoutManager(view.getContext());
                                    recyleview.setLayoutManager(manger);
                                    //绘制分割线
                                    recyleview.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
                                    recyleview.setAdapter(adpter);
                                }

                            }
                        });
            }
        });*/

        String url = "http://v.juhe.cn/toutiao/index?type=tiyu&key=c22f0ceb7bfdc561e9aef2860b882f67";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        result = response;
                        if(result!=null) {
                            Log.d("AAA", "run: " + result);
                            //解析JSON数据
                            Gson mGson = new Gson();
                            Data data = new Data();
                            data = mGson.fromJson(result.trim(), Data.class);
                            data = mGson.fromJson(result, Data.class);
                            for (int i = 0; i < data.getResult().getData().size(); i++) {
                                Log.d("AAA", "run: " + data.getResult().getData().get(i).getTitle());
                                titles.add(data.getResult().getData().get(i).getTitle());
                                img_urls.add(data.getResult().getData().get(i).getThumbnail_pic_s());
                            }
                            RecyclerView recyleview = (RecyclerView) view.findViewById(R.id.recyleview);
                            TopAdpter adpter = new TopAdpter(view.getContext(),titles,img_urls);

                            LinearLayoutManager manger = new LinearLayoutManager(view.getContext());
                            recyleview.setLayoutManager(manger);
                            //绘制分割线
                            recyleview.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
                            recyleview.setAdapter(adpter);
                        }
                    }
                });
        return view;
    }
}
