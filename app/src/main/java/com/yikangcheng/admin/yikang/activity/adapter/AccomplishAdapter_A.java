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
import com.yikangcheng.admin.yikang.bean.PaidBean;
import com.yikangcheng.admin.yikang.bean.WaitSignBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/5/26.
 * WF
 */
public class AccomplishAdapter_A extends RecyclerView.Adapter {
    private Context mContent;
    public ArrayList<WaitSignBean.OrderBean> mList = new ArrayList<>();
    private List<WaitSignBean.OrderBean.OrderDetailsListBean> goodsize;
    private String color;

    public AccomplishAdapter_A(Context context, String color) {
        this.mContent = context;
        this.color = color;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_new_one_recycler_item_shouhuo, parent, false);
            return new DVhOne(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_new_recycler_item_shouhuo, parent, false);
            return new DVh(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vh, final int position) {
        goodsize = mList.get(position).getOrderDetailsList();
        if (goodsize != null) {
            if (vh instanceof DVhOne) {
                ((DVhOne) vh).order_status.setTextColor(Color.parseColor(color));
                ((DVhOne) vh).yellow_text.setTextColor(Color.parseColor(color));
                GradientDrawable myGrad = (GradientDrawable) ((DVhOne) vh).yellow_text.getBackground();
                myGrad.setStroke(2,Color.parseColor(color));

                ((DVhOne) vh).order_num.setText(mList.get(position).getOrderNo() + "");
                java.text.DecimalFormat myformat1 = new java.text.DecimalFormat("0.00");
                String moneyStr = myformat1.format(mList.get(position).getOrderDetailsList().get(0).getPrice());
                ((DVhOne) vh).one_good_price.setText("¥" + moneyStr);
                if (mList.get(position).getOrderState().equals("SUCCESS")) {
                    ((DVhOne) vh).order_status.setText("等待收货");
                }
                if (mList.get(position).getOrderState().equals("REFUND")) {
                    ((DVhOne) vh).order_status.setText("等待收货");
                }
                if (mList.get(position).getOrderState().equals("CANCEL")) {
                    ((DVhOne) vh).order_status.setText("已取消");
                }
                if (mList.get(position).getOrderState().equals("INIT")) {
                    ((DVhOne) vh).order_status.setText("等待支付");
                }
                ((DVhOne) vh).one_good_title.setText(mList.get(position).getOrderDetailsList().get(0).getShopName());
                ((DVhOne) vh).one_good_spec.setText(mList.get(position).getOrderDetailsList().get(0).getSpecNames());
                ((DVhOne) vh).one_good_num.setText("x" + mList.get(position).getOrderDetailsList().get(0).getBuyNum());
                ((DVhOne) vh).order_sum.setText("¥" + myformat1.format(mList.get(position).getRealPrice()));
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.placeholder(R.drawable.inco_log);
                requestOptions.fallback(R.drawable.inco_log);
                if (goodsize.get(0).getShopImg().contains("https://") || goodsize.get(0).getShopImg().contains("http://")) {
                    Glide.with(mContent).load(goodsize.get(0).getShopImg()).
                            apply(requestOptions).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into(((DVhOne) vh).one_good_img);
                } else {
                    Glide.with(mContent).load(Constants.BASETUPIANSHANGCHUANURL + goodsize.get(0).getShopImg()).
                            apply(requestOptions).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into(((DVhOne) vh).one_good_img);
                }
                ((DVhOne) vh).yellow_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onTakeOnclickListener != null) {
                            onTakeOnclickListener.onClick(mList.get(position).getCouponcodeId(), position, mList.get(position).getOrderId());
                        }
                    }
                });
                ((DVhOne) vh).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onPartiCliclListener != null) {
                            onPartiCliclListener.onClick(mList.get(position).getOrderId(), mList.get(position).getOrderState(), position);
                        }
                    }
                });

            } else if (vh instanceof DVh) {
                ((DVh) vh).mZhuangtai.setTextColor(Color.parseColor(color));
                ((DVh) vh).yellow_text.setTextColor(Color.parseColor(color));
                GradientDrawable myGrad = (GradientDrawable) ((DVh) vh).yellow_text.getBackground();
                myGrad.setStroke(2,Color.parseColor(color));

                ((DVh) vh).price_num_line.getBackground().mutate().setAlpha(240);
                ((DVh) vh).mBianhao.setText(mList.get(position).getOrderNo() + "");
                java.text.DecimalFormat myformat1 = new java.text.DecimalFormat("0.00");
                String moneyStr = myformat1.format(mList.get(position).getRealPrice());
                ((DVh) vh).mPrice.setText("¥" + moneyStr);
                ((DVh) vh).good_num.setText("共" + mList.get(position).getOrderDetailsList().size() + "件");
                if (mList.get(position).getOrderState().equals("SUCCESS")) {
                    ((DVh) vh).mZhuangtai.setText("等待收货");
                }
                if (mList.get(position).getOrderState().equals("REFUND")) {
                    ((DVh) vh).mZhuangtai.setText("已取消");
                }
                if (mList.get(position).getOrderState().equals("CANCEL")) {
                    ((DVh) vh).mZhuangtai.setText("已取消");
                }
                if (mList.get(position).getOrderState().equals("INIT")) {
                    ((DVh) vh).mZhuangtai.setText("等待支付");
                }
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContent, LinearLayoutManager.HORIZONTAL, false);
                ((DVh) vh).mRlv.setLayoutManager(linearLayoutManager);
                AccomplishAdapter_B accomplishAdapter_b = new AccomplishAdapter_B(mContent);
                ((DVh) vh).mRlv.setAdapter(accomplishAdapter_b);
                accomplishAdapter_b.addAll(goodsize);
                accomplishAdapter_b.setOnGoParicListener(new AccomplishAdapter_B.onGoParicListener() {
                    @Override
                    public void onClick(int goodId) {
                        if (onPartiCliclListener != null) {
                            onPartiCliclListener.onClick(mList.get(position).getOrderId(), mList.get(position).getOrderState(), position);
                        }
                    }
                });
                ((DVh) vh).yellow_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onTakeOnclickListener != null) {
                            onTakeOnclickListener.onClick(mList.get(position).getCouponcodeId(), position, mList.get(position).getOrderId());
                        }
                    }
                });
                ((DVh) vh).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onPartiCliclListener != null) {
                            onPartiCliclListener.onClick(mList.get(position).getOrderId(), mList.get(position).getOrderState(), position);
                        }
                    }
                });
            }
        } else {
            ((DVhOne) vh).yellow_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onTakeOnclickListener != null) {
                        onTakeOnclickListener.onClick(mList.get(position).getCouponcodeId(), position, mList.get(position).getOrderId());
                    }
                }
            });
            ((DVhOne) vh).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onPartiCliclListener != null) {
                        onPartiCliclListener.onClick(mList.get(position).getOrderId(), mList.get(position).getOrderState(), position);
                    }
                }
            });
        }

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
        this.mList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position).getOrderDetailsList() == null) {
            return 1;
        } else {
            if (mList.get(position).getOrderDetailsList().size() == 1) {
                return 1;
            } else {
                return 2;
            }
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
        private TextView gray_text;
        private TextView yellow_text;

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
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addAll(List<WaitSignBean.OrderBean> order) {
        mList.addAll(order);
        notifyDataSetChanged();
    }

    /**
     * 确认收货
     */
    public interface onTakeOnclickListener {
        void onClick(int orderDetailsId, int position, int orderId);
    }

    onTakeOnclickListener onTakeOnclickListener;

    public void setOnTakeOnclickListener(AccomplishAdapter_A.onTakeOnclickListener onTakeOnclickListener) {
        this.onTakeOnclickListener = onTakeOnclickListener;
    }

    /**
     * 跳转详情
     */
    public interface onPartiCliclListener {
        void onClick(int orderId, String orderState, int position);
    }

    onPartiCliclListener onPartiCliclListener;

    public void setOnPartiCliclListener(onPartiCliclListener onPartiCliclListener) {
        this.onPartiCliclListener = onPartiCliclListener;
    }
}