package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.bean.ALLBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 古祥坤
 */
public class All_A_Adapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<ALLBean.OrderBean> orderBeans = new ArrayList<>();
    private List<ALLBean.OrderBean.OrderDetailsListBean> goodsize;
    //多个商品再次购买
    private String goodsId = "";
    private String color;

    public All_A_Adapter(Context mContext, String color) {
        this.mContext = mContext;
        this.color = color;
    }

    /**
     * 添加数据
     *
     * @param orderBeans
     */
    public void addAll(List<ALLBean.OrderBean> orderBeans) {
        this.orderBeans.addAll(orderBeans);
        notifyDataSetChanged();
    }

    /**
     * 删除数据
     */
    public void removeAll() {
        this.orderBeans.clear();
        notifyDataSetChanged();
    }

    /**
     * 删除单个数据
     *
     * @param position
     */
    public void removePosition(int position) {
        this.orderBeans.remove(position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_new_one_recycler_item, parent, false);
            return new DVhOne(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_new_recycler_item, parent, false);
            return new DVh(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder vh, final int position) {
        goodsize = orderBeans.get(position).getOrderDetailsList();
        if (vh instanceof DVhOne) {
            ((DVhOne) vh).order_status.setTextColor(Color.parseColor(color));
            ((DVhOne) vh).yellow_text.setTextColor(Color.parseColor(color));
            GradientDrawable myGrad = (GradientDrawable) ((DVhOne) vh).yellow_text.getBackground();
            myGrad.setStroke(2,Color.parseColor(color));
            ((DVhOne) vh).order_num.setText(orderBeans.get(position).getOrderNo() + "");
            java.text.DecimalFormat myformat1 = new java.text.DecimalFormat("0.00");
            String moneyStr = myformat1.format(orderBeans.get(position).getOrderDetailsList().get(0).getPrice());
            ((DVhOne) vh).one_good_price.setText("¥" + moneyStr);
            if (orderBeans.get(position).getOrderState().equals("SUCCESS")) {
                ((DVhOne) vh).order_status.setText("已完成");
                ((DVhOne) vh).delete_text.setVisibility(View.VISIBLE);
                ((DVhOne) vh).yellow_text.setText("再次购买");
                ((DVhOne) vh).yellow_text.setVisibility(View.GONE);
                ((DVhOne) vh).delete_text.setText("删除订单");
            }
            if (orderBeans.get(position).getOrderState().equals("REFUND")) {
                ((DVhOne) vh).order_status.setText("等待收货");
                ((DVhOne) vh).yellow_text.setText("确认收货");
                ((DVhOne) vh).delete_text.setVisibility(View.GONE);
            }
            if (orderBeans.get(position).getOrderState().equals("CANCEL")) {
                ((DVhOne) vh).order_status.setText("已取消");
                ((DVhOne) vh).yellow_text.setText("再次购买");
                ((DVhOne) vh).delete_text.setText("删除订单");
                ((DVhOne) vh).yellow_text.setVisibility(View.GONE);
                ((DVhOne) vh).delete_text.setVisibility(View.VISIBLE);
            }
            if (orderBeans.get(position).getOrderState().equals("INIT")) {
                ((DVhOne) vh).order_status.setText("等待支付");
                ((DVhOne) vh).yellow_text.setText("去支付");
                ((DVhOne) vh).delete_text.setVisibility(View.GONE);
            }
            ((DVhOne) vh).one_good_title.setText(orderBeans.get(position).getOrderDetailsList().get(0).getShopName());
            ((DVhOne) vh).one_good_spec.setText(orderBeans.get(position).getOrderDetailsList().get(0).getSpecNames());
            ((DVhOne) vh).one_good_num.setText("x" + orderBeans.get(position).getOrderDetailsList().get(0).getBuyNum());
            ((DVhOne) vh).order_sum.setText("¥" + myformat1.format(orderBeans.get(position).getRealPrice()));
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.inco_log);
            requestOptions.fallback(R.drawable.inco_log);
            if (goodsize.get(0).getShopImg().contains("https://") || goodsize.get(0).getShopImg().contains("http://")) {
                Glide.with(mContext).load(goodsize.get(0).getShopImg()).
                        apply(requestOptions).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into(((DVhOne) vh).one_good_img);
            } else {
                Glide.with(mContext).load(Constants.BASETUPIANSHANGCHUANURL + goodsize.get(0).getShopImg()).
                        apply(requestOptions).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into(((DVhOne) vh).one_good_img);
            }
            /**
             * 删除订单
             */
            ((DVhOne) vh).delete_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onClickDeleteListener != null) {
                        onClickDeleteListener.onClick(position, orderBeans.get(position).getOrderId());
                    }
                }
            });
            /**
             * 跳转详情
             */
            ((DVhOne) vh).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onPartiCliclListener != null) {
                        onPartiCliclListener.onClick(orderBeans.get(position).getOrderId(), orderBeans.get(position).getOrderState(), position);
                    }
                }
            });
            /**
             * 再次购买
             */
            ((DVhOne) vh).yellow_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (((DVhOne) vh).yellow_text.getText().toString().equals("去支付")) {
                        if (onPayClickListener != null) {
                            onPayClickListener.onClick(orderBeans.get(position).getOrderId(), position);
                        }
                    } else if (((DVhOne) vh).yellow_text.getText().toString().equals("再次购买")) {
                        if (onBuyClickListener != null) {
                            onBuyClickListener.onClick(orderBeans.get(position).getOrderDetailsList().get(0).getDataId(), orderBeans.get(position).getOrderDetailsList().get(0).getBuyNum(), orderBeans.get(position).getOrderDetailsList().get(0).getPrice());
                        }
                    }
                }
            });

        } else if (vh instanceof DVh) {
            ((DVh) vh).mZhuangtai.setTextColor(Color.parseColor(color));
            ((DVh) vh).yellow_text.setTextColor(Color.parseColor(color));
            GradientDrawable myGrad = (GradientDrawable) ((DVh) vh).yellow_text.getBackground();
            myGrad.setStroke(2,Color.parseColor(color));
            ((DVh) vh).price_num_line.getBackground().mutate().setAlpha(240);
            ((DVh) vh).mBianhao.setText(orderBeans.get(position).getOrderNo() + "");
            java.text.DecimalFormat myformat1 = new java.text.DecimalFormat("0.00");
            String moneyStr = myformat1.format(orderBeans.get(position).getRealPrice());
            ((DVh) vh).mPrice.setText("¥" + moneyStr);
            ((DVh) vh).good_num.setText("共" + orderBeans.get(position).getOrderDetailsList().size() + "件");
            if (orderBeans.get(position).getOrderState().equals("SUCCESS")) {
                ((DVh) vh).mZhuangtai.setText("已完成");
                ((DVh) vh).delete_text.setVisibility(View.VISIBLE);
                ((DVh) vh).yellow_text.setText("再次购买");
                ((DVh) vh).delete_text.setText("删除订单");
            }
            if (orderBeans.get(position).getOrderState().equals("REFUND")) {
                ((DVh) vh).mZhuangtai.setText("等待收货");
                ((DVh) vh).yellow_text.setText("确认收货");
                ((DVh) vh).delete_text.setVisibility(View.GONE);
            }
            if (orderBeans.get(position).getOrderState().equals("CANCEL")) {
                ((DVh) vh).mZhuangtai.setText("已取消");
                ((DVh) vh).yellow_text.setText("再次购买");
                ((DVh) vh).delete_text.setText("删除订单");
                ((DVh) vh).delete_text.setVisibility(View.VISIBLE);
            }
            if (orderBeans.get(position).getOrderState().equals("INIT")) {
                ((DVh) vh).mZhuangtai.setText("等待支付");
                ((DVh) vh).yellow_text.setText("去支付");
                ((DVh) vh).delete_text.setVisibility(View.GONE);
            }
            int w = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
            int h = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
            ((DVh) vh).price_num_line.measure(w, h);
            int width = ((DVh) vh).price_num_line.getMeasuredWidth();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            ((DVh) vh).mRlv.setLayoutManager(linearLayoutManager);
            All_B_Adapter all_b_adapter = new All_B_Adapter(mContext);
            all_b_adapter.setWidht(width + 100);
            ((DVh) vh).mRlv.setAdapter(all_b_adapter);
            all_b_adapter.allAll(goodsize);
            //跳转详情
            all_b_adapter.setOnGoParicListener(new All_B_Adapter.onGoParicListener() {
                @Override
                public void onClick(int goodId) {
                    if (onPartiCliclListener != null) {
                        onPartiCliclListener.onClick(orderBeans.get(position).getOrderId(), orderBeans.get(position).getOrderState(), position);
                    }
                }
            });
            /**
             * 订单删除
             */
            ((DVh) vh).delete_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onClickDeleteListener != null) {
                        onClickDeleteListener.onClick(position, orderBeans.get(position).getOrderId());
                    }
                }
            });
            /**
             * 再次购买
             */
            ((DVh) vh).yellow_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (((DVh) vh).yellow_text.getText().toString().equals("去支付")) {
                        if (onPayClickListener != null) {
                            onPayClickListener.onClick(orderBeans.get(position).getOrderId(), position);
                        }
                    } else if (((DVh) vh).yellow_text.getText().toString().equals("再次购买")) {
                        if (onBuySomeClickListener != null) {
                            for (int i = 0; i < orderBeans.get(position).getOrderDetailsList().size(); i++) {
                                int gId = orderBeans.get(position).getOrderDetailsList().get(i).getDataId();
                                goodsId += gId + ",";
                            }
                            onBuySomeClickListener.onClick(goodsId.substring(0, goodsId.length() - 1));
                            goodsId = "";
                        }
                    }
                }
            });
            /**
             * 跳转详情
             */
            ((DVh) vh).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onPartiCliclListener != null) {
                        onPartiCliclListener.onClick(orderBeans.get(position).getOrderId(), orderBeans.get(position).getOrderState(), position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return orderBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (orderBeans.get(position).getOrderDetailsList().size() == 1) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * 等待收货多个商品
     */
    class DVh extends RecyclerView.ViewHolder {
        private TextView mBianhao;
        private TextView mPrice;
        private TextView good_num;
        private TextView order_sum;
        private RecyclerView mRlv;
        private TextView mZhuangtai;
        private LinearLayout price_num_line;
        private LinearLayout order_line;
        private TextView delete_text;
        private TextView yellow_text;
        private RelativeLayout good_rela;

        public DVh(View itemView) {
            super(itemView);
            mBianhao = itemView.findViewById(R.id.order_num);
            mPrice = itemView.findViewById(R.id.good_price);
            good_num = itemView.findViewById(R.id.good_num);
            mRlv = itemView.findViewById(R.id.good_recycler);
            mZhuangtai = itemView.findViewById(R.id.order_status);
            price_num_line = itemView.findViewById(R.id.price_num_line);
            order_line = itemView.findViewById(R.id.order_line);
            order_sum = itemView.findViewById(R.id.order_sum);
            yellow_text = itemView.findViewById(R.id.yellow_text);
            delete_text = itemView.findViewById(R.id.delete_text);
            good_rela = itemView.findViewById(R.id.good_rela);
        }
    }

    /**
     * 等待收货单个商品
     */
    class DVhOne extends RecyclerView.ViewHolder {

        TextView order_num;
        TextView order_status;
        TextView one_good_title;
        TextView one_good_price;
        TextView one_good_spec;
        TextView order_sum;
        TextView one_good_num;
        TextView yellow_text;
        ImageView one_good_img;
        TextView delete_text;
        RelativeLayout one_good_rela;

        public DVhOne(View itemView) {
            super(itemView);
            order_num = itemView.findViewById(R.id.order_num);
            order_status = itemView.findViewById(R.id.order_status);
            one_good_title = itemView.findViewById(R.id.one_good_title);
            one_good_price = itemView.findViewById(R.id.one_good_price);
            one_good_spec = itemView.findViewById(R.id.one_good_spec);
            order_sum = itemView.findViewById(R.id.order_sum);
            one_good_num = itemView.findViewById(R.id.one_good_num);
            yellow_text = itemView.findViewById(R.id.yellow_text);
            one_good_img = itemView.findViewById(R.id.one_good_img);
            delete_text = itemView.findViewById(R.id.delete_text);
            one_good_rela = itemView.findViewById(R.id.one_good_rela);
        }
    }

    /**
     * 删除订单
     */
    OnClickDeleteListener onClickDeleteListener;

    public interface OnClickDeleteListener {
        void onClick(int position, int orderId);
    }

    public void setOnClickDeleteListener(OnClickDeleteListener onClickDeleteListener) {
        this.onClickDeleteListener = onClickDeleteListener;
    }

    /**
     * 去支付
     */
    public interface onPayClickListener {
        void onClick(int orderId, int position);
    }

    onPayClickListener onPayClickListener;

    public void setOnPayClickListener(onPayClickListener onPayClickListener) {
        this.onPayClickListener = onPayClickListener;
    }

    /**
     * 再次购买
     */
    public interface onBuyClickListener {
        void onClick(int goodId, int num, double price);
    }

    onBuyClickListener onBuyClickListener;

    public void setOnBuyClickListener(All_A_Adapter.onBuyClickListener onBuyClickListener) {
        this.onBuyClickListener = onBuyClickListener;
    }

    /**
     * 再次购买多个商品
     */
    public interface onBuySomeClickListener {
        void onClick(String goodsId);

    }

    onBuySomeClickListener onBuySomeClickListener;

    public void setOnBuySomeClickListener(All_A_Adapter.onBuySomeClickListener onBuySomeClickListener) {
        this.onBuySomeClickListener = onBuySomeClickListener;
    }

    /**
     * 跳转详情
     */
    public interface onPartiCliclListener {
        void onClick(int orderId, String orderState, int position);
    }

    onPartiCliclListener onPartiCliclListener;

    public void setOnPartiCliclListener(All_A_Adapter.onPartiCliclListener onPartiCliclListener) {
        this.onPartiCliclListener = onPartiCliclListener;
    }
}
