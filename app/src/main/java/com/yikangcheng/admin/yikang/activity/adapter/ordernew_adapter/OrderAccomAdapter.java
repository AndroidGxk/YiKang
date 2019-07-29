package com.yikangcheng.admin.yikang.activity.adapter.ordernew_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.bean.WaliDealBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：古祥坤 on 2019/7/9 14:43
 * 邮箱：1724959985@qq.com
 * 已完成订单详情
 */
public class OrderAccomAdapter extends RecyclerView.Adapter<OrderAccomAdapter.Vh> {
    List<WaliDealBean.DetailsListBean> mList = new ArrayList<>();
    Context mContext;

    public OrderAccomAdapter(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 添加数据
     */
    public void addAll(List<WaliDealBean.DetailsListBean> mList) {
        this.mList.addAll(mList);
        notifyDataSetChanged();
    }

    /**
     * 删除数据
     */
    public void removeAll() {
        this.mList.clear();
        notifyDataSetChanged();
    }

    /**
     * 删除单个数据
     *
     * @param position
     */
    public void removePosition(int position) {
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderaccom_recycler_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, final int position) {
        if (position == getItemCount() - 1) {
            vh.cutoff_line.setVisibility(View.GONE);
        } else {
            vh.cutoff_line.setVisibility(View.VISIBLE);
        }
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.inco_log);
        requestOptions.fallback(R.drawable.inco_log);
        final WaliDealBean.DetailsListBean.ShopSpecDetailedBean shopSpecDetailedBean = mList.get(position).getShopSpecDetailed();
        if (shopSpecDetailedBean != null) {
            if (shopSpecDetailedBean.getLogo().contains("http://") || shopSpecDetailedBean.getLogo().contains("https://")) {
                Glide.with(mContext).load(shopSpecDetailedBean.getLogo()).into(vh.good_img);
            } else {
                Glide.with(mContext).load(Constants.BASETUPIANSHANGCHUANURL + shopSpecDetailedBean.getLogo()).into(vh.good_img);
            }
            vh.good_title.setText(shopSpecDetailedBean.getCommodityName());
            vh.good_spec.setText("规格" + shopSpecDetailedBean.getSpecNames());
            vh.good_num.setText("x" + mList.get(position).getBuyNum());
            java.text.DecimalFormat myformat = new java.text.DecimalFormat("0.00");
            vh.good_price.setText("¥" + myformat.format(shopSpecDetailedBean.getRetailPrice()));
            vh.good_markprice.setText("市场价" + myformat.format(shopSpecDetailedBean.getMarketPrice()));
            /**
             * 进入详情
             */
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onGoPariAccomClickListener != null) {
                        onGoPariAccomClickListener.onClick(shopSpecDetailedBean.getCommodityId());
                    }
                }
            });
            /**
             * 再次购买商品
             */
            vh.buy_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onBuyGoodAccomListener != null) {
                        onBuyGoodAccomListener.onClick(shopSpecDetailedBean.getCommodityId(), mList.get(position).getBuyNum());
                    }
                }
            });
            /**
             * 查看售后
             */
            vh.shou_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onAfterListener != null) {
                        onAfterListener.onClick(mList.get(position).getId());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class Vh extends RecyclerView.ViewHolder {
        View cutoff_line;
        ImageView good_img;
        TextView good_title;
        TextView good_spec;
        TextView good_num;
        TextView good_price;
        TextView good_markprice;
        TextView buy_btn;
        TextView shou_btn;

        public Vh(View itemView) {
            super(itemView);
            cutoff_line = itemView.findViewById(R.id.cutoff_line);
            good_img = itemView.findViewById(R.id.good_img);
            good_title = itemView.findViewById(R.id.good_title);
            good_spec = itemView.findViewById(R.id.good_spec);
            good_num = itemView.findViewById(R.id.good_num);
            good_price = itemView.findViewById(R.id.good_price);
            good_markprice = itemView.findViewById(R.id.good_markprice);
            buy_btn = itemView.findViewById(R.id.buy_but);
            shou_btn = itemView.findViewById(R.id.shou_btn);
        }
    }

    /**
     * 点击商品进去商品详情
     */
    public interface onGoPariAccomClickListener {
        void onClick(int goodId);
    }

    onGoPariAccomClickListener onGoPariAccomClickListener;

    public void setOnGoPariAccomClickListener(OrderAccomAdapter.onGoPariAccomClickListener onGoPariAccomClickListener) {
        this.onGoPariAccomClickListener = onGoPariAccomClickListener;
    }

    /**
     * 再次购买此商品
     */
    public interface onBuyGoodAccomListener {
        void onClick(int goodId, int buyNum);
    }

    onBuyGoodAccomListener onBuyGoodAccomListener;

    public void setOnBuyGoodAccomListener(OrderAccomAdapter.onBuyGoodAccomListener onBuyGoodAccomListener) {
        this.onBuyGoodAccomListener = onBuyGoodAccomListener;
    }

    /**
     * 查看售后
     */
    public interface onAfterListener {
        void onClick(int id);
    }

    onAfterListener onAfterListener;

    public void setOnAfterListener(OrderAccomAdapter.onAfterListener onAfterListener) {
        this.onAfterListener = onAfterListener;
    }
}
