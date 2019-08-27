package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.bean.ClassifyBean;
import com.yikangcheng.admin.yikang.bean.ClassifyListOneBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/5/17.
 * WF
 */
public class FenLeiAdapter extends RecyclerView.Adapter<FenLeiAdapter.Vh> {
    private List<ClassifyListOneBean> stringList = new ArrayList<>();
    private int mPosition;
    private Context context;
    private String color;

    public FenLeiAdapter(Context context, String color) {
        this.context = context;
        this.color = color;
    }

    public void addAll(List<ClassifyListOneBean> stringList) {
        this.stringList.addAll(stringList);
        notifyDataSetChanged();
    }


    @Override
    public Vh onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragmnet_fenlei_zuo, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(final Vh vh, final int position) {
        vh.textView.setText(stringList.get(position).getSubjectName());
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //记录点击下标
                mPosition = position;
                mOnclick.onClick(position);
                notifyDataSetChanged();
            }
        });
        for (int i = 0; i < stringList.size(); i++) {
            if (mPosition == position) {
                vh.view.setVisibility(View.VISIBLE);
                vh.view.setBackgroundColor(Color.parseColor(color));
                vh.textView.setTextSize(13);
                vh.textView.setTextColor(Color.parseColor(color));
                vh.itemView.setBackgroundColor(context.getResources().getColor(R.color.clolrBAai));
            } else {
                vh.view.setVisibility(View.INVISIBLE);
                vh.textView.setTextColor(context.getResources().getColor(R.color.colorText));
                vh.itemView.setBackgroundColor(context.getResources().getColor(R.color.colorTouMing));
                vh.textView.setTextSize(13);
            }
        }
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class Vh extends RecyclerView.ViewHolder {
        TextView textView;
        View view;

        public Vh(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_item_zuo);
            view = (View) itemView.findViewById(R.id.view);
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
