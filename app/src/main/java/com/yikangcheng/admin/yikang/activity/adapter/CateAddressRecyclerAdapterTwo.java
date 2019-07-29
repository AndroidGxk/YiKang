package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：古祥坤 on 2019/7/15 18:09
 * 邮箱：1724959985@qq.com
 */
public class CateAddressRecyclerAdapterTwo extends RecyclerView.Adapter<CateAddressRecyclerAdapterTwo.Vh> {
    //记录下标
    private int mPosition;
    private Context context;
    private int id;

    private List<String> stringList = new ArrayList<>();

    public void addAll(List<String> stringList) {
        this.stringList.addAll(stringList);
        notifyDataSetChanged();
    }

    public CateAddressRecyclerAdapterTwo(Context context) {
        this.context = context;
    }

    public void setmPosition(int position) {
        mPosition = position;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ctae_address_zhineng_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Vh vh, final int position) {
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = 0;
                mPosition = position;
                if (mOnclick != null) {
                    mOnclick.onClick(position, vh.textView.getText().toString());
                }
                notifyDataSetChanged();
            }
        });
        vh.textView.setText(stringList.get(position));
        if (id == 0) {
            if (mPosition == position) {
                vh.textView.setTextColor(context.getResources().getColor(R.color.ColorTextAllT));
                vh.itemView.setBackgroundResource(R.color.clolrBAai);
                vh.imageView.setVisibility(View.VISIBLE);
            } else {
                vh.textView.setTextColor(context.getResources().getColor(R.color.ColorTextAllF));
                vh.imageView.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class Vh extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public Vh(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            imageView = itemView.findViewById(R.id.imag_dui);
        }
    }

    public interface Onclick {
        void onClick(int position, String info);
    }

    Onclick mOnclick;

    public void setOnclickListener(Onclick mOnclick) {
        this.mOnclick = mOnclick;
    }
}
