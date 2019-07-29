package com.yikangcheng.admin.yikang.activity.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yikangcheng.admin.yikang.R;

/**
 * 作者：古祥坤 on 2019/7/14 17:28
 * 邮箱：1724959985@qq.com
 */
public class CateImageAdapter extends RecyclerView.Adapter<CateImageAdapter.Vh> {
    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_goods_img, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, int position) {
        if (position == 0) {
            vh.imageView.setImageResource(R.drawable.qizhiyou_wanghong);
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setMargins(10, 0, 0, 0);
            vh.itemView.setLayoutParams(layout);
        } else if (position == 1) {
            vh.imageView.setImageResource(R.drawable.qizhiyou_fenzu);
        }else {
            vh.imageView.setImageResource(R.drawable.qizhiyou_yuehui);
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class Vh extends RecyclerView.ViewHolder {
        ImageView imageView;

        public Vh(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.sdv_item_head_img);
        }
    }
}
