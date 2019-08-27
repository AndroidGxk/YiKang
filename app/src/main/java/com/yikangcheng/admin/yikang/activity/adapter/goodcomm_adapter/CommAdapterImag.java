package com.yikangcheng.admin.yikang.activity.adapter.goodcomm_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.bean.UserCommListBean;
import com.yikangcheng.admin.yikang.util.StarBar;

import java.util.ArrayList;
import java.util.List;


/**
 * 已评价数据
 */
public class CommAdapterImag extends RecyclerView.Adapter<CommAdapterImag.Vh> {
    private Context context;
    private List<String> userCommListBeans = new ArrayList<>();

    public CommAdapterImag(Context context) {
        this.context = context;
    }

    public void addAll(List<String> userCommListBeans) {
        this.userCommListBeans.addAll(userCommListBeans);
        notifyDataSetChanged();
    }

    public void removeAll() {
        this.userCommListBeans.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_goods_img, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, int position) {
        Glide.with(context).load(userCommListBeans.get(position))
                .into(vh.sdv_item_head_img);
    }

    @Override
    public int getItemCount() {
        return userCommListBeans.size();
    }

    class Vh extends RecyclerView.ViewHolder {

        ImageView sdv_item_head_img;

        public Vh(View itemView) {
            super(itemView);
            sdv_item_head_img = itemView.findViewById(R.id.sdv_item_head_img);
        }
    }
}
