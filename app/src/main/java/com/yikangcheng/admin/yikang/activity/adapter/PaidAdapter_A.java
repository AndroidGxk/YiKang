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
import com.yikangcheng.admin.yikang.activity.obligation.PaidActivity;
import com.yikangcheng.admin.yikang.bean.PaidBean;
import java.util.List;

/**
 * Created by lenovo on 2019/5/24.
 * WF
 */
public class PaidAdapter_A extends RecyclerView.Adapter {
    private List<PaidBean.OrderBean> mList;
    private PaidActivity mContent;

    public PaidAdapter_A(PaidActivity paidActivity, List<PaidBean.OrderBean> orderBeans) {
        this.mContent = paidActivity;
        this.mList = orderBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContent).inflate(R.layout.item_fragment_all_a, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.mBianhao.setText("订单编号:"+mList.get(position).getOrderNo());
        holder1.mData.setText(""+mList.get(position).getCreateTime());
        if (mList.get(position).getOrderState().equals("SUCCESS")) {
            holder1.mZhuangtai.setText("已支付");
        }
        holder1.mPrice.setText("合计：¥" + mList.get(position).getRealPrice());

        holder1.mRlv.setLayoutManager(new LinearLayoutManager(mContent));
        PaidAdapter_B paidAdapter_b = new PaidAdapter_B(mContent,mList.get(position).getOrderDetailsList());
        holder1.mRlv.setAdapter(paidAdapter_b);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addAll(List<PaidBean.OrderBean> order) {
        this.mList.addAll(order);
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mBianhao;
        private  ImageView mShanchu;
        private  TextView mData;
        private  TextView mPrice;
        private  RecyclerView mRlv;
        private  TextView mZhuangtai;

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