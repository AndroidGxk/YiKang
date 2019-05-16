package com.yikangcheng.admin.yikang.activity.adapter;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;

import java.util.ArrayList;
import java.util.List;


public class ShopRecyclerAdapter extends RecyclerView.Adapter<ShopRecyclerAdapter.Vh> {
    List<String> stringList = new ArrayList<>();

    public void addAll(List<String> stringList) {
        this.stringList.addAll(stringList);
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_recycler_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, int position) {
    }

    @Override
    public int getItemCount() {
//        return stringList.size();
        return 9;
    }

    class Vh extends RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mCount;
        TextView mPrice;
        TextView mBprice;

        public Vh(View itemView) {
            super(itemView);

        }
    }

}
