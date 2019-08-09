package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.bean.DiscountBean;
import com.yikangcheng.admin.yikang.bean.ProvinceBean;

import java.util.ArrayList;
import java.util.List;


public class EffecRecyclerAdapter extends RecyclerView.Adapter<EffecRecyclerAdapter.Vh> {
    List<DiscountBean.CouponListBean> stringList = new ArrayList<>();
    //记录下标
    private int mPosition;
    Context context;

    public EffecRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<DiscountBean.CouponListBean> stringList) {
        this.stringList.addAll(stringList);
        notifyDataSetChanged();
    }

    public void removeAll() {
        this.stringList.clear();
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.effec_recycler_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, final int position) {
        DiscountBean.CouponListBean couponCodeListBean = stringList.get(position);
        vh.title_text.setText(couponCodeListBean.getTitle());
        String startTime = couponCodeListBean.getStartTime();
        String startTimes = startTime.substring(0, 10);
        String endTime = couponCodeListBean.getEndTime();
        String endTimes = endTime.substring(0, 10);
        vh.date_text.setText(startTimes + "—" + endTimes);
        vh.man_text.setText(couponCodeListBean.getInfo() + "");
        vh.price_text.setText(couponCodeListBean.getAmount() + "");
        if (couponCodeListBean.getType() == 1) {
            vh.type_text.setText("折扣券");
        } else if (couponCodeListBean.getType() == 2) {
            vh.type_text.setText("满减券");
        }
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class Vh extends RecyclerView.ViewHolder {
        TextView title_text;
        TextView man_text;
        TextView price_text;
        TextView date_text;
        TextView type_text;

        public Vh(View itemView) {
            super(itemView);
            title_text = itemView.findViewById(R.id.title_text);
            man_text = itemView.findViewById(R.id.man_text);
            date_text = itemView.findViewById(R.id.date_text);
            price_text = itemView.findViewById(R.id.price_text);
            type_text = itemView.findViewById(R.id.type_text);
        }
    }
}
