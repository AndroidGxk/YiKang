package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.bean.CouponusableBean;

import java.util.ArrayList;
import java.util.List;


public class SelectCouponRecyclerAdapter extends RecyclerView.Adapter<SelectCouponRecyclerAdapter.Vh> {
    List<CouponusableBean> stringList = new ArrayList<>();
    //记录下标
    private int mPosition = -1;
    Context context;
    List<Boolean> list = new ArrayList<>();

    public SelectCouponRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<CouponusableBean> stringList) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commis_recycler_select_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Vh vh, final int position) {
        for (int i = 0; i < stringList.size(); i++) {
            list.add(false);
        }
        final CouponusableBean couponusableBean = stringList.get(position);
        vh.title_text.setText(couponusableBean.getTitle());
        String startTime = couponusableBean.getStartTime();
        String startTimes = startTime.substring(0, 10);
        String endTime = couponusableBean.getEndTime();
        String endTimes = endTime.substring(0, 10);
        vh.date_text.setText(startTimes + "—" + endTimes);
        vh.man_text.setText(couponusableBean.getInfo() + "");
        vh.price_text.setText(couponusableBean.getAmount() + "");
        if (couponusableBean.getType() == 1) {
            vh.type_text.setText("折扣券");
        } else if (couponusableBean.getType() == 2) {
            vh.type_text.setText("满减券");
        }
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vh.itemView.isSelected()) {
                    mPosition = -1;
                    onClickListener.onClick(0, 0);
                }else{
                    mPosition = position;
                    onClickListener.onClick(couponusableBean.getId(), couponusableBean.getAmount());
                }
                notifyDataSetChanged();
            }
        });
        for (int i = 0; i < stringList.size(); i++) {
            if (mPosition == position) {
                vh.itemView.setBackgroundResource(R.drawable.youhuiquan_true);
                vh.itemView.setSelected(true);
            } else {
                vh.itemView.setBackgroundResource(R.drawable.youhuiquanbg);
                vh.itemView.setSelected(false);
            }
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
        ConstraintLayout constrain;

        public Vh(View itemView) {
            super(itemView);
            title_text = itemView.findViewById(R.id.title_text);
            man_text = itemView.findViewById(R.id.man_text);
            date_text = itemView.findViewById(R.id.date_text);
            price_text = itemView.findViewById(R.id.price_text);
            type_text = itemView.findViewById(R.id.type_text);
            constrain = itemView.findViewById(R.id.constrain);
        }
    }

    onClickListener onClickListener;

    public void setOnClickListener(SelectCouponRecyclerAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    /**
     * 选择优惠券
     */
    public interface onClickListener {
        void onClick(int id, int money);
    }
}
