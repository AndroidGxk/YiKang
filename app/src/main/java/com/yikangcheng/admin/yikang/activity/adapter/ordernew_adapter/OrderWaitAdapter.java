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
 * 待付款订单详情
 */
public class OrderWaitAdapter extends RecyclerView.Adapter<OrderWaitAdapter.Vh> {
    List<WaliDealBean.DetailsListBean> mList = new ArrayList<>();
    Context mContext;

    public OrderWaitAdapter(Context mContext) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderwait_recycler_item, parent, false);
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
                    if (onGoPariClickListener != null) {
                        onGoPariClickListener.onClick(shopSpecDetailedBean.getCommodityId());
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

        public Vh(View itemView) {
            super(itemView);
            cutoff_line = itemView.findViewById(R.id.cutoff_line);
            good_img = itemView.findViewById(R.id.good_img);
            good_title = itemView.findViewById(R.id.good_title);
            good_spec = itemView.findViewById(R.id.good_spec);
            good_num = itemView.findViewById(R.id.good_num);
            good_price = itemView.findViewById(R.id.good_price);
            good_markprice = itemView.findViewById(R.id.good_markprice);
        }
    }

    /**
     * 点击商品进去商品详情
     */
    public interface onGoPariClickListener {
        void onClick(int goodId);
    }

    onGoPariClickListener onGoPariClickListener;

    public void setOnGoPariClickListener(OrderWaitAdapter.onGoPariClickListener onGoPariClickListener) {
        this.onGoPariClickListener = onGoPariClickListener;
    }
}
