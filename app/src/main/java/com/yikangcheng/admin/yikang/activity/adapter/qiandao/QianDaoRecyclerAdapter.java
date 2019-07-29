package com.yikangcheng.admin.yikang.activity.adapter.qiandao;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yikangcheng.admin.yikang.R;

/**
 * 作者：古祥坤 on 2019/7/17 16:29
 * 邮箱：1724959985@qq.com
 */
public class QianDaoRecyclerAdapter extends RecyclerView.Adapter<QianDaoRecyclerAdapter.Vh> {
    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sign_recycle_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, int position) {
        if (position == 3) {
            vh.imageView.setVisibility(View.VISIBLE);
            vh.date_line.setVisibility(View.INVISIBLE);
        } else {
            vh.imageView.setVisibility(View.INVISIBLE);
            vh.date_line.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class Vh extends RecyclerView.ViewHolder {
        ImageView imageView;
        LinearLayout date_line;

        public Vh(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.qiandao_img);
            date_line = itemView.findViewById(R.id.date_line);
        }
    }
}
