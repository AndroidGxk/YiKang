package com.yikangcheng.admin.yikang.activity.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;

/**
 * Created by lenovo on 2019/5/17.
 * WF
 */
public class Comadapter extends RecyclerView.Adapter<Comadapter.Vh> {


    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_commit_item, parent, false);
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
        ImageView com_img;
        public Vh(View itemView) {
            super(itemView);
            com_img = itemView.findViewById(R.id.com_img);
        }
    }

}
