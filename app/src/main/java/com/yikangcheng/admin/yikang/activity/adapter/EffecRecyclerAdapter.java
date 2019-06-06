package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.bean.ProvinceBean;

import java.util.ArrayList;
import java.util.List;


public class EffecRecyclerAdapter extends RecyclerView.Adapter<EffecRecyclerAdapter.Vh> {
    List<ProvinceBean> stringList = new ArrayList<>();
    //记录下标
    private int mPosition;
    Context context;

    public EffecRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<ProvinceBean> stringList) {
        this.stringList.addAll(stringList);
        notifyDataSetChanged();
    }

    public void removeAll() {
        this.stringList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.effec_recycler_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, final int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class Vh extends RecyclerView.ViewHolder {
        TextView city_text;

        public Vh(View itemView) {
            super(itemView);
        }
    }

}
