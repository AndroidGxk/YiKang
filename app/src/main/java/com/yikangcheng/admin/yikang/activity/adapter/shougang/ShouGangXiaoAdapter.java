package com.yikangcheng.admin.yikang.activity.adapter.shougang;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;

/**
 * 作者：古祥坤 on 2019/7/11 14:36
 * 邮箱：1724959985@qq.com
 */
public class ShouGangXiaoAdapter extends RecyclerView.Adapter<ShouGangXiaoAdapter.Vh> {
    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shougang_xiao_recycler_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, int position) {
        if (position == getItemCount() - 1) {
            vh.cutoff_line.setVisibility(View.GONE);
        } else {
            vh.cutoff_line.setVisibility(View.VISIBLE);
        }
        if (position == 0) {
            vh.mess_img.setBackgroundResource(R.drawable.shougang_list_xxiao);
            vh.mess_title.setText("系统消息");
            vh.mess_count.setText("您的购物车里的宝贝降价啦");
            vh.mess_time.setText("星期二");
        } else if (position == 1) {
            vh.mess_img.setBackgroundResource(R.drawable.shougang_list_kxiao);
            vh.mess_title.setText("小康");
            vh.mess_count.setText("您有什么问题么？");
            vh.mess_time.setText("2019/06/20");
        } else if (position == 2) {
            vh.mess_img.setBackgroundResource(R.drawable.shougang_list_zxiao);
            vh.mess_title.setText("最新消息");
            vh.mess_count.setText("您的购物车里的宝贝降价啦");
            vh.mess_time.setText("2019/06/18");
        } else {
            vh.mess_img.setBackgroundResource(R.drawable.shougang_list_xxiao);
            vh.mess_title.setText("系统消息");
            vh.mess_count.setText("您的购物车里的宝贝降价啦");
            vh.mess_time.setText("2019/06/15");
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class Vh extends RecyclerView.ViewHolder {
        ImageView mess_img;
        TextView mess_title;
        TextView mess_count;
        TextView mess_time;
        View cutoff_line;

        public Vh(View itemView) {
            super(itemView);
            mess_img = itemView.findViewById(R.id.mess_img);
            mess_title = itemView.findViewById(R.id.mess_title);
            mess_count = itemView.findViewById(R.id.mess_count);
            mess_time = itemView.findViewById(R.id.mess_time);
            cutoff_line = itemView.findViewById(R.id.cutoff_line);
        }
    }
}
