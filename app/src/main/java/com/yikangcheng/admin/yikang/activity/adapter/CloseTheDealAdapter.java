package com.yikangcheng.admin.yikang.activity.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.SupportActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.orderstatus.CloseTheDealActivity;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.bean.CloseTheDealBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/5/27.
 * WF
 */
public class CloseTheDealAdapter extends RecyclerView.Adapter {
    public ArrayList<CloseTheDealBean.DetailsListBean> mList;
    private CloseTheDealActivity mContent;
    private OnClickListener mListener;

    public CloseTheDealAdapter(ArrayList<CloseTheDealBean.DetailsListBean> extraData, CloseTheDealActivity closeTheDealActivity) {
        this.mList = extraData;
        this.mContent = closeTheDealActivity;
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
        holder1.mShousou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                afterOnClickListener.onAfterclick(mList.get(position).getId(), mList.get(position).getDataId(), mList.get(position).getOrderId());
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


    //点击售后
    AfterOnClickListener afterOnClickListener;

    public void setAfterOnClickListener(AfterOnClickListener afterOnClickListener) {
        this.afterOnClickListener = afterOnClickListener;
    }

    public interface AfterOnClickListener {
        void onAfterclick(int id, int sid, int ssid);
    }
}
