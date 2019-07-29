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
 * 作者：古祥坤 on 2019/7/14 15:19
 * 邮箱：1724959985@qq.com
 */
public class LiveRecyclerAdapter extends RecyclerView.Adapter<LiveRecyclerAdapter.Vh> {
    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_recycler_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, final int position) {
        switch (position) {
            case 0:
                vh.image.setBackgroundResource(R.drawable.meishi1);
                vh.text.setText("美食");
                break;
            case 1:
                vh.image.setBackgroundResource(R.drawable.che1);
                vh.text.setText("汽车美容");
                break;
            case 2:
                vh.image.setBackgroundResource(R.drawable.jiudian1);
                vh.text.setText("酒店/住宿");
                break;
            case 3:
                vh.image.setBackgroundResource(R.drawable.tianchongxing1);
                vh.text.setText("电影");
                break;
            case 4:
                vh.image.setBackgroundResource(R.drawable.yiliaolei1);
                vh.text.setText("医疗/健康");
                break;
            case 5:
                vh.image.setBackgroundResource(R.drawable.feiji2);
                vh.text.setText("旅游");
                break;
            case 6:
                vh.image.setBackgroundResource(R.drawable.yu);
                vh.text.setText("超市/生鲜");
                break;
            case 7:
                vh.image.setBackgroundResource(R.drawable.meifa1);
                vh.text.setText("丽人/美发");
                break;
            case 8:
                vh.image.setBackgroundResource(R.drawable.ktvtianchong1);
                vh.text.setText("KTV");
                break;
            case 9:
                vh.image.setBackgroundResource(R.drawable.shoujichongzhitianchong1);
                vh.text.setText("手机充值");
                break;
        }
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class Vh extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;

        public Vh(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
        }
    }

    public interface onClickListener {
        void onClick(int position);
    }

    onClickListener onClickListener;

    public void setOnClickListener(LiveRecyclerAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
