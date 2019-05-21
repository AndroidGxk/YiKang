package com.yikangcheng.admin.yikang.activity.adapter;

import android.graphics.Paint;
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
 *
 */
public class LuxuryRecyclerAdapter extends RecyclerView.Adapter<LuxuryRecyclerAdapter.Vh> {
    List<String> stringList = new ArrayList<>();
    private OnClickListener mListener;

    public void addAll(List<String> stringList) {
        this.stringList.addAll(stringList);
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shechi_recycler_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, final int position) {
        vh.mBprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);  //中划线
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.OnClickListener(v, position);
            }
        });
        vh.geng_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        if (position == 8) {
            vh.geng_img.setVisibility(View.VISIBLE);
            vh.geng_text.setVisibility(View.VISIBLE);
        } else {
            vh.geng_img.setVisibility(View.GONE);
            vh.geng_text.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    class Vh extends RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mCount;
        TextView mPrice;
        TextView mBprice;
        ImageView geng_img;
        TextView geng_text;

        public Vh(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.text_title);
            mCount = itemView.findViewById(R.id.text_count);
            mPrice = itemView.findViewById(R.id.text_price);
            mBprice = itemView.findViewById(R.id.text_bprice);
            geng_img = itemView.findViewById(R.id.geng_img);
            geng_text = itemView.findViewById(R.id.geng_text);
        }
    }


    public interface OnClickListener {
        void OnClickListener(View v, int position);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.mListener = listener;
    }

}
