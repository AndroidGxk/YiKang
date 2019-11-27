package com.yikangcheng.admin.yikang.activity.giftactivity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.bean.AllWelfareBean;
import com.yikangcheng.admin.yikang.bean.WelfareCourseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：古祥坤 on 2019/8/28 18:11
 * 邮箱：1724959985@qq.com
 */
public class MyGiftYIWanChengTwoAdapter extends RecyclerView.Adapter<MyGiftYIWanChengTwoAdapter.Vh> {
    Context context;
    List<AllWelfareBean.WelfareDetailsListBean> welfareDetailsListBeans = new ArrayList<>();

    public MyGiftYIWanChengTwoAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<AllWelfareBean.WelfareDetailsListBean> welfareDetailsListBeans) {
        this.welfareDetailsListBeans.addAll(welfareDetailsListBeans);
        notifyDataSetChanged();
    }

    public void removeAll() {
        this.welfareDetailsListBeans.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_my_gift_recycler_item_two, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, int position) {
        if(welfareDetailsListBeans.get(position).getCurselogo() != null&&!welfareDetailsListBeans.get(position).getCurselogo().equals("")){
            vh.good_title.setText(welfareDetailsListBeans.get(position).getCursename());
            //设置图片圆角角度
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.inco_log);
            if (welfareDetailsListBeans.get(position).getCurselogo().contains("http://") || welfareDetailsListBeans.get(position).getCurselogo().contains("https://")) {
                Glide.with(context).load(welfareDetailsListBeans.get(position).getCurselogo())
                        .apply(requestOptions)
                        .into(vh.good_img);
            } else {
                Glide.with(context).load("https://static.yikch.com" + welfareDetailsListBeans.get(position).getCurselogo())
                        .apply(requestOptions)
                        .into(vh.good_img);
            }
        }else{
            vh.good_title.setText(welfareDetailsListBeans.get(position).getCouponname());
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.fuliquan);
            requestOptions.fallback(R.drawable.fuliquan);
                Glide.with(context).load("")
                        .apply(requestOptions)
                        .into(vh.good_img);
        }
    }

    @Override
    public int getItemCount() {
        return welfareDetailsListBeans.size();
    }

    class Vh extends RecyclerView.ViewHolder {

        ImageView good_img;
        TextView good_title;

        public Vh(View itemView) {
            super(itemView);
            good_img = itemView.findViewById(R.id.good_img);
            good_title = itemView.findViewById(R.id.good_title);
        }
    }


}
