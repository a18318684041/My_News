package com.example.administrator.my_news.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.my_news.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public class CommentAdpter extends RecyclerView.Adapter<CommentAdpter.ViewHolder> {
    private Context context;
    private List<String> comments;//评论详情
    private List<String> names;//用户名字
    private List<String> times;//评论发送的时间
    public CommentAdpter(Context context,List<String> comments,List<String> names,List<String> times){
        this.context = context;
        this.comments =comments;
        this.names = names;
        this.times = times;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.conmment_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
        //首先时间以及评论内容
        holder.tv_time.setText(times.get(position));
        holder.tv_content.setText(comments.get(position));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_time;
        private TextView tv_content;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(R.id.tv_comment);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }
}
