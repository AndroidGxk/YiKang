package com.yikangcheng.admin.yikang.activity.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;

/**
 * 作者：古祥坤 on 2019/7/14 15:19
 * 邮箱：1724959985@qq.com
 */
public class LiveShopRecyclerAdapter extends RecyclerView.Adapter<LiveShopRecyclerAdapter.Vh> {
    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_shop_recycler_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, final int position) {
        if (position == getItemCount() - 1) {
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setMargins(0, 0, 0, 150);
            vh.itemView.setLayoutParams(layout);
            vh.fenge_xian.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class Vh extends RecyclerView.ViewHolder {
        View fenge_xian;

        public Vh(View itemView) {
            super(itemView);
            fenge_xian = itemView.findViewById(R.id.fenge_xian);
        }
    }

}
