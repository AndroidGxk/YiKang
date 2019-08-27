package com.yikangcheng.admin.yikang.activity.adapter.goodcomm_adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hjq.toast.ToastUtils;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.bean.AddressBean;
import com.yikangcheng.admin.yikang.bean.ProvinceBean;
import com.yikangcheng.admin.yikang.bean.UserCommDaiListBean;

import java.util.ArrayList;
import java.util.List;


/**
 * 待评价数据
 */
public class RemainRecyclerAdapter extends RecyclerView.Adapter<RemainRecyclerAdapter.Vh> {
    private Context context;
    private String color;
    private List<UserCommDaiListBean.OrderDetailsListBean> orderDetailsListBeans = new ArrayList<>();

    public RemainRecyclerAdapter(Context context, String color) {
        this.context = context;
        this.color = color;
    }


    public void addAll(List<UserCommDaiListBean.OrderDetailsListBean> orderDetailsListBeans) {
        this.orderDetailsListBeans.addAll(orderDetailsListBeans);
        notifyDataSetChanged();
    }

    public void removeAll() {
        this.orderDetailsListBeans.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.remaincommit_recycle_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, final int position) {
        final UserCommDaiListBean.OrderDetailsListBean orderDetailsListBean = orderDetailsListBeans.get(position);
        vh.title.setText(orderDetailsListBean.getCommodityName());
        vh.com_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(orderDetailsListBean.getId(), orderDetailsListBean.getCommodityLogo());
                }
            }
        });
        vh.com_btn.setTextColor(Color.parseColor(color));
        GradientDrawable gradientDrawable = (GradientDrawable) vh.com_btn.getBackground();
        gradientDrawable.setStroke(2, Color.parseColor(color));
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.inco_log);
        requestOptions.fallback(R.drawable.inco_log);
        Glide.with(context).load(Constants.BASETUPIANSHANGCHUANURL + orderDetailsListBean.getCommodityLogo())
                .apply(requestOptions)
                .into(vh.img);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClickIdListener!=null){
                    onClickIdListener.onClick(orderDetailsListBean.getCommodityId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderDetailsListBeans.size();
    }

    class Vh extends RecyclerView.ViewHolder {
        View view;
        ImageView img;
        TextView title;
        TextView com_btn;

        public Vh(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            com_btn = itemView.findViewById(R.id.com_btn);
        }
    }

    public interface onClickListener {
        void onClick(int id, String logo);
    }

    onClickListener onClickListener;

    public void setOnClickListener(RemainRecyclerAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    /**
     * 详情
     */
    public interface  onClickIdListener{
        void onClick(int id);
    }
    onClickIdListener onClickIdListener;

    public void setOnClickIdListener(onClickIdListener onClickIdListener) {
        this.onClickIdListener = onClickIdListener;
    }
}
