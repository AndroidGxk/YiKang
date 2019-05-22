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
import com.yikangcheng.admin.yikang.activity.obligation.ObligationActivity;
import com.yikangcheng.admin.yikang.bean.All_A_Bean;
import com.yikangcheng.admin.yikang.bean.All_B_Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/5/22.
 * WF
 */
public class ObligationAdapter_A extends RecyclerView.Adapter {
    private final ObligationActivity mContent;
    private final List<All_A_Bean> mList;

    public ObligationAdapter_A(List<All_A_Bean> all_a_beans, ObligationActivity obligationActivity) {
        this.mList = all_a_beans;
        this.mContent = obligationActivity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContent).inflate(R.layout.item_activity_obligation_a, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.mBianhao.setText(mList.get(position).getBianhao());
        holder1.mData.setText(mList.get(position).getData());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContent, LinearLayoutManager.VERTICAL, false);
        holder1.mRlv.setLayoutManager(linearLayoutManager);
        List<All_B_Bean> all_b_beans = new ArrayList<>();
        all_b_beans.add(new All_B_Bean(null, "榴莲", "特别臭，特别好吃", "188.00"));
        all_b_beans.add(new All_B_Bean(null, "樱桃", "特别甜，特别好吃", "188.00"));
        All_B_Adapter all_b_adapter = new All_B_Adapter(all_b_beans,mContent);
        holder1.mRlv.setAdapter(all_b_adapter);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mBianhao;
        private final ImageView mShanchu;
        private final TextView mData;
        private final TextView mFreight;
        private final TextView mNum;
        private final TextView mPrice;
        private final RecyclerView mRlv;

        public ViewHolder(View itemView) {
            super(itemView);
            mShanchu = itemView.findViewById(R.id.img_activity_obligation_shanchu);
            mBianhao = itemView.findViewById(R.id.tv_activity_obligation_bianhao);
            mData = itemView.findViewById(R.id.tv_activity_obligation_data);
            mFreight = itemView.findViewById(R.id.tv_activity_obligation_freight);
            mNum = itemView.findViewById(R.id.tv_activity_obligation_num);
            mPrice = itemView.findViewById(R.id.tv_activity_obligation_price);
            mRlv = itemView.findViewById(R.id.rlv_activity_obligation_item);

        }
    }
}
