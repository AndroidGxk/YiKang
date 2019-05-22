package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.bean.RecommendBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/5/22.
 * WF
 */
public class Recommend_Fragment_wo_Adapter extends RecyclerView.Adapter {
    private final Context mContent;
    private final ArrayList<RecommendBean> mList;

    public Recommend_Fragment_wo_Adapter(ArrayList<RecommendBean> recommendBeans, Context context) {
        this.mList = recommendBeans;
        this.mContent = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContent).inflate(R.layout.item_recommend,   null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        Glide.with(mContent).load(Constants.BASETUPIANSHANGCHUANURL + mList.get(position).getLogo()).into(holder1.mImg);
        holder1.mTv_name.setText(mList.get(position).getName());
        holder1.mTitle.setText(mList.get(position).getTitle());
        holder1.mJiage.setText(mList.get(position).getCurrentprice() + "");
        holder1.mBprice.setText("市场价：" + mList.get(position).getSourceprice());
        /**
         * // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
         */
        holder1.mBprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<RecommendBean> entity) {
        mList.addAll(entity);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImg;
        private final TextView mTv_name;
        private final TextView mTitle;
        private final TextView mJiage;
        private final TextView mBprice;

        public ViewHolder(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.recom_img);
            mTv_name = itemView.findViewById(R.id.recom_title);
            mTitle = itemView.findViewById(R.id.recom_count);
            mJiage = itemView.findViewById(R.id.tv_jiage);
            mBprice = itemView.findViewById(R.id.text_bprice);
        }
    }
}
