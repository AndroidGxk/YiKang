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
import com.yikangcheng.admin.yikang.bean.DiscountCouponBean;

import java.util.ArrayList;
import java.util.List;


public class SelectCouponRecyclerAdapter extends RecyclerView.Adapter<SelectCouponRecyclerAdapter.Vh> {
    List<DiscountCouponBean.CouponCodeListBean>  stringList = new ArrayList<>();
    //记录下标
    private int mPosition;
    Context context;

    public SelectCouponRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<DiscountCouponBean.CouponCodeListBean>  stringList) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commis_recycler_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, final int position) {
        DiscountCouponBean.CouponCodeListBean couponCodeListBean = stringList.get(position);
        DiscountCouponBean.CouponCodeListBean.CouponBean coupon = couponCodeListBean.getCoupon();
        vh.title_text.setText(coupon.getTitle());
        String startTime = coupon.getStartTime();
        String startTimes = startTime.substring(0, 10);
        String endTime = coupon.getEndTime();
        String endTimes = endTime.substring(0, 10);
        vh.date_text.setText(startTimes + "—" + endTimes);
        vh.man_text.setText(coupon.getStatus() + "");
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

        public Vh(View itemView) {
            super(itemView);
            title_text = itemView.findViewById(R.id.title_text);
            man_text = itemView.findViewById(R.id.man_text);
            date_text = itemView.findViewById(R.id.date_text);
            price_text = itemView.findViewById(R.id.price_text);
        }
    }

}
