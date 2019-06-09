package com.yikangcheng.admin.yikang.activity.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.orderstatus.FackOfActivity;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.bean.CloseTheDealBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/5/28.
 * WF
 */
public class FackOfAdapter_A extends RecyclerView.Adapter {
    private FackOfActivity mContent;
    public ArrayList<CloseTheDealBean.DetailsListBean> mList;
    private OnClickListener mListener;

    public FackOfAdapter_A(ArrayList<CloseTheDealBean.DetailsListBean> mShopSpecDetailedBeans, FackOfActivity fackOfActivity) {
        this.mList = mShopSpecDetailedBeans;
        this.mContent = fackOfActivity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContent).inflate(R.layout.item_closethedeal, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        Glide.with(mContent).load(Constants.BASETUPIANSHANGCHUANURL + mList.get(position).getShopSpecDetailed().getLogo()).into(holder1.mImg);
        holder1.mName.setText(mList.get(position).getShopSpecDetailed().getCommodityName());
        holder1.mNum.setText("X" + mList.get(position).getBuyNum());
        holder1.mTitle.setText(mList.get(position).getShopSpecDetailed().getSpecNames());
        holder1.mPrice.setText(mList.get(position).getShopSpecDetailed().getRetailPrice() + "");

        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.OnClickListener(v, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addAll(List<CloseTheDealBean.DetailsListBean> detailsList) {
        this.mList.addAll(detailsList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImg;
        private TextView mName;
        private TextView mTitle;
        private TextView mPrice;
        private TextView mNum;
        private TextView mShousou;

        public ViewHolder(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.img_item_closethedeal);
            mName = itemView.findViewById(R.id.tv_item_closethedeal_name);
            mTitle = itemView.findViewById(R.id.tv_item_closethedeal_title);
            mPrice = itemView.findViewById(R.id.tv_item_closethedeal_price);
            mNum = itemView.findViewById(R.id.tv_num);
            mShousou = itemView.findViewById(R.id.tv_item_closethedeal_shouhou);
        }
    }

    public interface OnClickListener {
        void OnClickListener(View v, int position);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.mListener = listener;
    }
}
