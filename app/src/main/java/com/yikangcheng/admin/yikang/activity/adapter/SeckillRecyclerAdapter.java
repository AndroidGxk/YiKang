package com.yikangcheng.admin.yikang.activity.adapter;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.util.SaleProgressView;

import java.util.ArrayList;
import java.util.List;


public class SeckillRecyclerAdapter extends RecyclerView.Adapter<SeckillRecyclerAdapter.Vh> {
    List<String> stringList = new ArrayList<>();
    int i = 60;

    public void addAll(List<String> stringList) {
        this.stringList.addAll(stringList);
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seckill_recycler_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, int position) {
        vh.mBprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);  //中划线
        vh.progressView.setTotalAndCurrentCount(100, i,100);
        i = i + 5;
    }

    @Override
    public int getItemCount() {
//        return stringList.size();
        return 109;
    }

    class Vh extends RecyclerView.ViewHolder {
        TextView mPrice;
        TextView mBprice;
        SaleProgressView progressView;

        public Vh(View itemView) {
            super(itemView);
            mPrice = itemView.findViewById(R.id.text_price);
            mBprice = itemView.findViewById(R.id.b_price);
            progressView = itemView.findViewById(R.id.spv);
        }
    }

}
