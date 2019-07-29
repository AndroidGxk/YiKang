package com.yikangcheng.admin.yikang.activity.adapter.movieadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;

/**
 * 作者：古祥坤 on 2019/7/12 15:36
 * 邮箱：1724959985@qq.com
 */
public class MovieRecyclerAdapterTwo extends RecyclerView.Adapter<MovieRecyclerAdapterTwo.Vh> {
    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_recycler_item_two, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, int position) {
        vh.text.getBackground().mutate().setAlpha(200);
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
        TextView text;

        public Vh(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
        }
    }
    /**
     * 跳转到电影页面
     */
    public interface onClickListener {
        void onClick();
    }

    onClickListener onClickListener;

    public void setOnClickListener(MovieRecyclerAdapterTwo.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
