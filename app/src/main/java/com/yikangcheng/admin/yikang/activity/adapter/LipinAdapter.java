package com.yikangcheng.admin.yikang.activity.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yikangcheng.admin.yikang.R;

/**
 * 作者：古祥坤 on 2019/9/4 14:26
 * 邮箱：1724959985@qq.com
 */
public class LipinAdapter extends RecyclerView.Adapter<LipinAdapter.Vh> {
    @NonNull
    @Override
    public LipinAdapter.Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_wo_lipin_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LipinAdapter.Vh vh, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class Vh extends RecyclerView.ViewHolder {

        public Vh(View itemView) {
            super(itemView);
        }
    }
}
