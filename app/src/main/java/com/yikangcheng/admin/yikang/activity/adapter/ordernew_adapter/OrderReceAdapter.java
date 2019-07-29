package com.yikangcheng.admin.yikang.activity.adapter.ordernew_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
 * 待收货订单详情
 */
public class OrderReceAdapter extends RecyclerView.Adapter<OrderReceAdapter.Vh> {
    List<WaliDealBean.DetailsListBean> mList = new ArrayList<>();
    Context mContext;
    private Vh vh;

    public OrderReceAdapter(Context mContext) {
        this.mContext = mContext;
    }

    //判断有多少个商品物流号相同
    private int mSomeNum = 0;
    //记录相同物流商品的下标
    private int mPosition;
    private List<LinearLayout> linearLayouts = new ArrayList<>();

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderrece_recycler_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Vh vh1, final int position) {
        vh = vh1;
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.inco_log);
        requestOptions.fallback(R.drawable.inco_log);
        linearLayouts.add(vh.take_line);
        final WaliDealBean.DetailsListBean.ShopSpecDetailedBean shopSpecDetailedBean = mList.get(position).getShopSpecDetailed();
        if (shopSpecDetailedBean != null) {
            if (shopSpecDetailedBean.getLogo().contains("http://") || shopSpecDetailedBean.getLogo().contains("https://")) {
                Glide.with(mContext).load(shopSpecDetailedBean.getLogo()).into(vh.good_img);
            } else {
                Glide.with(mContext).load(Constants.BASETUPIANSHANGCHUANURL + shopSpecDetailedBean.getLogo()).into(vh.good_img);
            }
            /**
             * 是否发货
             */
            if (mList.get(position).getMailStatus() == 1) {
                if (position == getItemCount() - 1) {
                    vh.cutoff_line.setVisibility(View.GONE);
                } else {
                    vh.cutoff_line.setVisibility(View.VISIBLE);
                }
                vh.take_line.setVisibility(View.VISIBLE);
                vh.wuliu_mes_title.setText("您的订单未发货");
            } else if (mList.get(position).getMailStatus() == 2) {
                mPosition = position;
                vh.take_line.setVisibility(View.VISIBLE);
                mSomeNum++;
            }
//            if (mSomeNum >= 2) {
//                for (int i = 0; i < mPosition; i++) {
//                    vh.take_line = linearLayouts.get(i);
//                    vh.take_line.setVisibility(View.GONE);
//                }
//            }
            if (!(mList.get(position).getAfterSaleStatus() == 0 && mList.get(position).getMailStatus() == 2 && mList.get(position).getSignStatus() == 1)) {
                vh.shou_but.setVisibility(View.GONE);
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
             * 查看物流
             */
            vh.look_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onLookListener != null) {
                        onLookListener.onClick(mList.get(position).getId());
                    }
                }
            });
            /**
             * 确认收货
             */
            vh.shou_but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onTakeListener != null) {
                        onTakeListener.onClick(mList.get(position).getId(), position, vh.shou_but);
                    }
                }
            });
            /**
             * 查看物流
             */
            vh.take_line.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onLookListener != null) {
                        onLookListener.onClick(mList.get(position).getId());
                    }
                }
            });
            /**
             * 查看物流
             */
            vh.wuliu_mess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onLookListener != null) {
                        onLookListener.onClick(mList.get(position).getId());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void onClickClearListener() {
        for (int i = 0; i < this.linearLayouts.size(); i++) {
            vh.take_line = linearLayouts.get(i);
            vh.take_line.setVisibility(View.VISIBLE);
        }
        mSomeNum = 0;
        notifyDataSetChanged();
    }

    class Vh extends RecyclerView.ViewHolder {
        View cutoff_line;
        ImageView good_img;
        TextView good_title;
        TextView good_spec;
        TextView good_num;
        TextView good_price;
        TextView good_markprice;
        TextView look_btn;
        TextView shou_but;
        TextView wuliu_mes_title;
        LinearLayout take_line;
        LinearLayout wuliu_mess;

        public Vh(View itemView) {
            super(itemView);
            cutoff_line = itemView.findViewById(R.id.cutoff_line);
            good_img = itemView.findViewById(R.id.good_img);
            good_title = itemView.findViewById(R.id.good_title);
            good_spec = itemView.findViewById(R.id.good_spec);
            good_num = itemView.findViewById(R.id.good_num);
            good_price = itemView.findViewById(R.id.good_price);
            good_markprice = itemView.findViewById(R.id.good_markprice);
            look_btn = itemView.findViewById(R.id.look_btn);
            shou_but = itemView.findViewById(R.id.shou_but);
            wuliu_mes_title = itemView.findViewById(R.id.wuliu_mes_title);
            take_line = itemView.findViewById(R.id.take_line);
            wuliu_mess = itemView.findViewById(R.id.wuliu_mess);
        }
    }

    /**
     * 点击商品进去商品详情
     */
    public interface onGoPariAccomClickListener {
        void onClick(int goodId);
    }

    onGoPariAccomClickListener onGoPariAccomClickListener;

    public void setOnGoPariAccomClickListener(OrderReceAdapter.onGoPariAccomClickListener onGoPariAccomClickListener) {
        this.onGoPariAccomClickListener = onGoPariAccomClickListener;
    }

    /**
     * 查看物流
     */
    public interface onLookListener {
        void onClick(int id);
    }

    onLookListener onLookListener;

    public void setOnLookListener(OrderReceAdapter.onLookListener onLookListener) {
        this.onLookListener = onLookListener;
    }

    /**
     * 确认收货
     */
    public interface onTakeListener {
        void onClick(int id, int position, View button);
    }

    onTakeListener onTakeListener;

    public void setOnTakeListener(OrderReceAdapter.onTakeListener onTakeListener) {
        this.onTakeListener = onTakeListener;
    }
}
