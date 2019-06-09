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
import com.yikangcheng.admin.yikang.activity.obligation.PaidActivity;
import com.yikangcheng.admin.yikang.bean.PaidBean;

import java.util.List;

/**
 * Created by lenovo on 2019/5/25.
 * WF
 */
public class PaidAdapter_B extends RecyclerView.Adapter {
    private List<PaidBean.OrderBean.OrderDetailsListBean> mList;
    private PaidActivity mContent;
    private OnClickListener mListener;

    public PaidAdapter_B(PaidActivity content, List<PaidBean.OrderBean.OrderDetailsListBean> orderDetailsList) {
        this.mContent = content;
        this.mList = orderDetailsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContent).inflate(R.layout.item_fragment_all_b, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        Glide.with(mContent).load("https://static.yikch.com" + mList.get(position).getShopImg()).into(holder1.mImg);
        holder1.mName.setText(mList.get(position).getShopName());
        holder1.mNum.setText("X" + mList.get(position).getBuyNum());
        holder1.mTitle.setText(mList.get(position).getSpecNames());
        holder1.mPrice.setText(mList.get(position).getPrice() + "");
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int orderId = mList.get(position).getOrderId();
                mListener.OnClickListener(v,orderId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImg;
        private  TextView mName;
        private  TextView mTitle;
        private  TextView mPrice;
        private  TextView mNum;

        public ViewHolder(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.img_fragment_all_b);
            mName = itemView.findViewById(R.id.tv_fragment_all_b_name);
            mTitle = itemView.findViewById(R.id.tv_fragment_all_b_title);
            mPrice = itemView.findViewById(R.id.tv_fragment_all_b_price);
            mNum = itemView.findViewById(R.id.tv_num);
        }
    }

    public interface OnClickListener {
        void OnClickListener(View v, int orderId);
    }
    public void setOnClickListener(OnClickListener listener){
        this.mListener=listener;
    }
}
