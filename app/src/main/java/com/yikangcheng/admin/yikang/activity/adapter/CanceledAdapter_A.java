package com.yikangcheng.admin.yikang.activity.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.obligation.CanceledActivity;
import com.yikangcheng.admin.yikang.bean.CloseBean;

import java.util.List;

/**
 * Created by lenovo on 2019/5/26.
 * WF
 */
public class CanceledAdapter_A extends RecyclerView.Adapter {
    private final List<CloseBean.OrderBean> mList;
    private final CanceledActivity mCentent;

    public CanceledAdapter_A(List<CloseBean.OrderBean> orderBeans, CanceledActivity canceledActivity) {
        this.mList = orderBeans;
        this.mCentent = canceledActivity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mCentent).inflate(R.layout.item_fragment_all_a, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.mBianhao.setText("订单编号:" + mList.get(position).getOrderNo());
        holder1.mData.setText("" + mList.get(position).getCreateTime());
        if (mList.get(position).getOrderState().equals("SUCCESS")) {
            holder1.mZhuangtai.setText("交易关闭");
        }
        holder1.mPrice.setText("合计：¥" + mList.get(position).getRealPrice());
        holder1.mRlv.setLayoutManager(new LinearLayoutManager(mCentent));
        CanceledAdapter_B canceledAdapter_b = new CanceledAdapter_B(mList.get(position).getOrderDetailsList(),mCentent);
        holder1.mRlv.setAdapter(canceledAdapter_b);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void allAll(List<CloseBean.OrderBean> order) {
        mList.addAll(order);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mBianhao;
        private ImageView mShanchu;
        private TextView mData;
        private TextView mPrice;
        private RecyclerView mRlv;
        private TextView mZhuangtai;

        public ViewHolder(View itemView) {
            super(itemView);
            mShanchu = itemView.findViewById(R.id.img_fragment_all_shanchu);
            mBianhao = itemView.findViewById(R.id.tv_fragment_all_bianhao);
            mData = itemView.findViewById(R.id.tv_fragment_all_data);
            mPrice = itemView.findViewById(R.id.tv_fragment_all_price);
            mRlv = itemView.findViewById(R.id.rlv_fragment_all_item);
            mZhuangtai = itemView.findViewById(R.id.tv_fragment_all_zhuangtai);
        }
    }
}