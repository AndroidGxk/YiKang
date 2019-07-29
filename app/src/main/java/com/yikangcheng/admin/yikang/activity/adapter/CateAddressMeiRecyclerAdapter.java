package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：古祥坤 on 2019/7/15 18:09
 * 邮箱：1724959985@qq.com
 */
public class CateAddressMeiRecyclerAdapter extends RecyclerView.Adapter<CateAddressMeiRecyclerAdapter.Vh> {
    //记录下标
    private int mPosition;
    private Context context;
    private int id;
    private List<String> stringList = new ArrayList<>();

    public void addAll(List<String> stringList) {
        this.stringList.addAll(stringList);
        notifyDataSetChanged();
    }

    public CateAddressMeiRecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ctae_address_text_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, final int position) {
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = 0;
                mPosition = position;
                if (mOnclick != null) {
                    mOnclick.onClick(position);
                }
                notifyDataSetChanged();
            }
        });
        vh.textView.setText(stringList.get(position));
        if (id == 0) {
            if (mPosition == position) {
                vh.textView.setTextColor(context.getResources().getColor(R.color.ColorTextAllT));
                vh.itemView.setBackgroundResource(R.color.clolrBAai);
            } else {
                vh.textView.setTextColor(context.getResources().getColor(R.color.ColorTextAllF));
                vh.itemView.setBackgroundColor(context.getResources().getColor(R.color.ColorTextAllBack));
            }
        }
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class Vh extends RecyclerView.ViewHolder {

        TextView textView;

        public Vh(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }

    public interface Onclick {
        void onClick(int position);
    }

    Onclick mOnclick;

    public void setOnclickListener(Onclick mOnclick) {
        this.mOnclick = mOnclick;
    }
}
