package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.bean.All_B_Bean;

import java.util.List;

/**
 * Created by lenovo on 2019/5/20.
 * WF
 */
public class All_B_Adapter extends RecyclerView.Adapter {
    private final Context mContent;
    private final List<All_B_Bean> mList;

    public All_B_Adapter(List<All_B_Bean> all_b_beans, Context content) {
        this.mList = all_b_beans;
        this.mContent = content;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContent).inflate(R.layout.item_fragment_all_b, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.mName.setText(mList.get(position).getName());
        holder1.mTitle.setText(mList.get(position).getTitle());
        holder1.mPrice.setText(mList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImg;
        private final TextView mName;
        private final TextView mTitle;
        private final TextView mPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.imag);
            mName = itemView.findViewById(R.id.tv_fragment_all_b_name);
            mTitle = itemView.findViewById(R.id.tv_fragment_all_b_title);
            mPrice = itemView.findViewById(R.id.tv_fragment_all_b_price);
        }
    }
}
