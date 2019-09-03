package com.yikangcheng.admin.yikang.activity.giftactivity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yikangcheng.admin.yikang.R;

import java.util.Random;

/**
 * 作者：古祥坤 on 2019/8/28 18:11
 * 邮箱：1724959985@qq.com
 */
public class MyGiftTwoAdapter extends RecyclerView.Adapter<MyGiftTwoAdapter.Vh> {
    Context context;
    int random;

    public MyGiftTwoAdapter(Context context, int random) {
        this.context = context;
        this.random = random;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_my_gift_recycler_item_two, parent, false);
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


        public Vh(View itemView) {
            super(itemView);
        }
    }


}
