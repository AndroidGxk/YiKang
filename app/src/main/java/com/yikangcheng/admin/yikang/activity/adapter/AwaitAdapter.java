package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.bean.ObligationBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/5/26.
 * WF
 */
public class AwaitAdapter extends RecyclerView.Adapter {
    private Context mContent;
    public ArrayList<ObligationBean.OrderBean> mList;
    private OnClickListener mListener;
    private OnClickListenerDelete mListenerDelete;

    public AwaitAdapter(ArrayList<ObligationBean.OrderBean> orderBeans, Context context) {
        this.mList = orderBeans;
        this.mContent = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContent).inflate(R.layout.item_fragment_all_a, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holder1 = (ViewHolder) holder;

        holder1.mBianhao.setText("订单编号:" + mList.get(position).getOrderNo());
        holder1.mData.setText(mList.get(position).getCreateTime());

        holder1.mPrice.setText("合计：¥" + mList.get(position).getRealPrice());
        if (mList.get(position).getOrderState().equals("INIT")) {
            holder1.mZhuangtai.setText("未支付");
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContent, LinearLayoutManager.VERTICAL, false);
        holder1.mRlv.setLayoutManager(linearLayoutManager);
        AwaitAdapter_B awaitAdapter_b = new AwaitAdapter_B(mList.get(position).getOrderDetailsList(), mContent);
        holder1.mRlv.setAdapter(awaitAdapter_b);
        awaitAdapter_b.setOnClickListener(new AwaitAdapter_B.OnClickListener() {
            @Override
            public void OnClickListener(View v, int orderId) {
                mListener.OnClickListener(v, orderId, position);
            }
        });
        holder1.mShanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListenerDelete.OnClickListener(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addAll(List<ObligationBean.OrderBean> order) {
        mList.addAll(order);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private  TextView mBianhao;
        private LinearLayout mShanchu;
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

    public interface OnClickListener {
        void OnClickListener(View v, int orderId, int position);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.mListener = listener;
    }

    public interface OnClickListenerDelete {
        void OnClickListener(View v, int position);
    }

    public void setOnClickListenerDelete(OnClickListenerDelete listener) {
        this.mListenerDelete = listener;
    }
}
