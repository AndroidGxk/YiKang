package com.yikangcheng.admin.yikang.activity.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：古祥坤 on 2019/5/26 15:24
 * 邮箱：1724959985@qq.com
 */
public class MessageListAdapter extends RecyclerView.Adapter {
    List<String> list = new ArrayList<>();

    public void addAll(List<String> list) {
        this.addAll(list);
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_recycler_item, parent, false);
            return new Vh(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.symessage_recycler_item, parent, false);
            return new Symessage(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Vh) {
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    /**
     * 交易物流
     */
    class Vh extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView count;

        public Vh(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.wuliu_img);
            title = itemView.findViewById(R.id.wuliu_title);
            count = itemView.findViewById(R.id.wuliu_count);
        }
    }

    /**
     * 系统消息
     */
    class Symessage extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;
        TextView count;

        public Symessage(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.sy_img);
            title = itemView.findViewById(R.id.sy_title);
            count = itemView.findViewById(R.id.sy_count);
        }
    }

    /**
     * 客服消息
     */
    class Cumessage extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;
        TextView count;

        public Cumessage(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.kefu_img);
            title = itemView.findViewById(R.id.kefu_title);
            count = itemView.findViewById(R.id.kefu_count);
        }
    }

}
