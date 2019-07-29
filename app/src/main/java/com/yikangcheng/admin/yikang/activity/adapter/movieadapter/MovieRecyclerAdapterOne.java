package com.yikangcheng.admin.yikang.activity.adapter.movieadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yikangcheng.admin.yikang.R;

/**
 * 作者：古祥坤 on 2019/7/12 15:36
 * 邮箱：1724959985@qq.com
 */
public class MovieRecyclerAdapterOne extends RecyclerView.Adapter<MovieRecyclerAdapterOne.Vh> {
    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_recycler_item_one, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, int position) {
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClickListener!=null){
                    onClickListener.onClick();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class Vh extends RecyclerView.ViewHolder {

        public Vh(View itemView) {
            super(itemView);
        }
    }

    /**
     * 跳转到电影院页面
     */
    public interface onClickListener {
        void onClick();
    }

    onClickListener onClickListener;

    public void setOnClickListener(MovieRecyclerAdapterOne.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
