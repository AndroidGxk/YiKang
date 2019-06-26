package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.bean.ClassifyListOneBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/5/17.
 * WF
 */
public class GoodPartiRecdapter extends RecyclerView.Adapter<GoodPartiRecdapter.Vh> {


    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.good_parti_rec_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class Vh extends RecyclerView.ViewHolder {
        ImageView good_img;
        TextView good_title;
        TextView good_count;
        TextView good_price;

        public Vh(View itemView) {
            super(itemView);
            good_img = itemView.findViewById(R.id.good_img);
            good_title = itemView.findViewById(R.id.good_title);
            good_count = itemView.findViewById(R.id.good_count);
            good_price = itemView.findViewById(R.id.good_price);
        }
    }

}
