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
import com.yikangcheng.admin.yikang.bean.LikeBean;

import java.util.List;

/**
 * Created by lenovo on 2019/5/18.
 * WF
 */
public class LikeAdapter extends RecyclerView.Adapter {
    private final Context mContent;
    public final List<LikeBean> mList;

    public LikeAdapter(List<LikeBean> entityBeans, Context context) {
        this.mList = entityBeans;
        this.mContent = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View inflate = LayoutInflater.from(mContent).inflate(R.layout.recom_shop_recycler_item, null, false);
            return new ViewHolder(inflate);
        } else {
            View inflate = LayoutInflater.from(mContent).inflate(R.layout.recom_shop_recycler_item_b, null, false);
            return new ViewHolderB(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == 1) {
            ViewHolder holder1 = (ViewHolder) holder;
            Glide.with(mContent).load("https://static.yikch.com"+mList.get(position).getLogo()).into(holder1.mImg);
            holder1.mTv_title.setText(mList.get(position).getName());
            holder1.mTv_jiage.setText(mList.get(position).getCurrentprice()+"");
            holder1.mTv_shichangjia.setText("市场价："+mList.get(position).getSourceprice());
            // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
            holder1.mTv_shichangjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG );
        } else {
            ViewHolderB holderB = (ViewHolderB) holder;
            Glide.with(mContent).load("https://static.yikch.com"+mList.get(position).getLogo()).into(holderB.mImg_b);
            holderB.mTv_title_b.setText(mList.get(position).getName());
            holderB.mTv_jiage_b.setText(mList.get(position).getCurrentprice()+"");
            holderB.mTv_shichangjia_b.setText("市场价："+mList.get(position).getSourceprice()+"");
            // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
            holderB.mTv_shichangjia_b.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG );
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return 1;
        } else {
            return 2;
        }
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<LikeBean> entity) {
        mList.addAll(entity);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImg;
        private TextView mTv_title;
        private TextView mTv_jianjie;
        private TextView mTv_jiage;
        private TextView mTv_shichangjia;
        private TextView mTv_yigou;

        public ViewHolder(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.recom_img);
            mTv_title = itemView.findViewById(R.id.recom_title);
            mTv_jianjie = itemView.findViewById(R.id.recom_count);
            mTv_jiage = itemView.findViewById(R.id.tv_jiage);
            mTv_shichangjia = itemView.findViewById(R.id.text_bprice);
            mTv_yigou = itemView.findViewById(R.id.tv_yigou);
        }

    }

    class ViewHolderB extends RecyclerView.ViewHolder {
        private ImageView mImg_b;
        private TextView mTv_title_b;
        private TextView mTv_jianjie_b;
        private TextView mTv_jiage_b;
        private TextView mTv_shichangjia_b;
        private TextView mTv_yigou_b;

        public ViewHolderB(View itemView) {
            super(itemView);
            mImg_b = itemView.findViewById(R.id.recom_img_b);
            mTv_title_b = itemView.findViewById(R.id.recom_title_b);
            mTv_jianjie_b = itemView.findViewById(R.id.recom_count_b);
            mTv_jiage_b = itemView.findViewById(R.id.tv_jiage_b);
            mTv_shichangjia_b = itemView.findViewById(R.id.text_bprice_b);
            mTv_yigou_b = itemView.findViewById(R.id.tv_yigou_b);
        }
    }

}
