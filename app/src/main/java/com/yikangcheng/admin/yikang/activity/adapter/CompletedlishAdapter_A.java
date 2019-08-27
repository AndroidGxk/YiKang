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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.hjq.toast.ToastUtils;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.bean.CloseBean;
import com.yikangcheng.admin.yikang.bean.HaveSignBean;
import com.yikangcheng.admin.yikang.bean.PaidBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/5/26.
 * WF
 */
public class CompletedlishAdapter_A extends RecyclerView.Adapter {
    //多个商品再次购买
    private String goodsId = "";
    private Context mContent;
    public List<HaveSignBean.OrderBean> mLists = new ArrayList<>();
    private List<HaveSignBean.OrderBean.OrderDetailsListBean> goodsize;
    private String color;

    public CompletedlishAdapter_A(Context context, String color) {
        this.mContent = context;
        this.color = color;
    }

    /**
     * 添加数据
     *
     * @param mList
     */
    public void addAll(List<HaveSignBean.OrderBean> mList) {
        this.mLists.addAll(mList);
        notifyDataSetChanged();
    }

    /**
     * 删除数据
     */
    public void removeAll() {
        this.mLists.clear();
        notifyDataSetChanged();
    }

    /**
     * 删除单个数据
     *
     * @param position
     */
    public void removePosition(int position) {
        this.mLists.remove(position);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_new_one_recycler_item_quxiao, parent, false);
            return new DVhOne(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_new_recycler_item_quxiao, parent, false);
            return new DVh(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vh, final int position) {
        goodsize = mLists.get(position).getOrderDetailsList();
        if (goodsize != null) {
            if (vh instanceof DVhOne) {
                ((DVhOne) vh).order_status.setTextColor(Color.parseColor(color));
                ((DVhOne) vh).yellow_text.setTextColor(Color.parseColor(color));
                GradientDrawable myGrad = (GradientDrawable) ((DVhOne) vh).yellow_text.getBackground();
                myGrad.setStroke(2, Color.parseColor(color));
                ((DVhOne) vh).order_num.setText(mLists.get(position).getOrderNo() + "");
                java.text.DecimalFormat myformat1 = new java.text.DecimalFormat("0.00");
                String moneyStr = myformat1.format(mLists.get(position).getOrderDetailsList().get(0).getPrice());
                ((DVhOne) vh).one_good_price.setText("¥" + moneyStr);
                if (mLists.get(position).getOrderState().equals("SUCCESS")) {
                    ((DVhOne) vh).order_status.setText("已完成");
                }
                if (mLists.get(position).getOrderState().equals("REFUND")) {
                    ((DVhOne) vh).order_status.setText("已取消");
                }
                if (mLists.get(position).getOrderState().equals("CANCEL")) {
                    ((DVhOne) vh).order_status.setText("已取消");
                }
                if (mLists.get(position).getOrderState().equals("INIT")) {
                    ((DVhOne) vh).order_status.setText("等待支付");
                }
                ((DVhOne) vh).one_good_title.setText(mLists.get(position).getOrderDetailsList().get(0).getShopName());
                ((DVhOne) vh).one_good_spec.setText(mLists.get(position).getOrderDetailsList().get(0).getSpecNames());
                ((DVhOne) vh).one_good_num.setText("x" + mLists.get(position).getOrderDetailsList().get(0).getBuyNum());
                ((DVhOne) vh).order_sum.setText("¥" + myformat1.format(mLists.get(position).getRealPrice()));
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
                ((DVhOne) vh).delete_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onDeleteClickListener != null) {
                            onDeleteClickListener.onClick(mLists.get(position).getOrderId(), position);
                        }
                    }
                });
                ((DVhOne) vh).yellow_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onBuyClickListener != null) {
                            onBuyClickListener.onClick(mLists.get(position).getOrderDetailsList().get(0).getDataId(), mLists.get(position).getOrderDetailsList().get(0).getBuyNum(), mLists.get(position).getOrderDetailsList().get(0).getPrice());
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
                            onPartiCliclListener.onClick(mLists.get(position).getOrderId(), mLists.get(position).getOrderState(), position);
                        }
                    }
                });
            } else if (vh instanceof DVh) {
                ((DVh) vh).mZhuangtai.setTextColor(Color.parseColor(color));
                ((DVh) vh).yellow_text.setTextColor(Color.parseColor(color));
                GradientDrawable myGrad = (GradientDrawable) ((DVh) vh).yellow_text.getBackground();
                myGrad.setStroke(2, Color.parseColor(color));
                ((DVh) vh).price_num_line.getBackground().mutate().setAlpha(240);
                ((DVh) vh).mBianhao.setText(mLists.get(position).getOrderNo() + "");
                java.text.DecimalFormat myformat1 = new java.text.DecimalFormat("0.00");
                String moneyStr = myformat1.format(mLists.get(position).getRealPrice());
                ((DVh) vh).mPrice.setText("¥" + moneyStr);
                ((DVh) vh).good_num.setText("共" + mLists.get(position).getOrderDetailsList().size() + "件");
                if (mLists.get(position).getOrderState().equals("SUCCESS")) {
                    ((DVh) vh).mZhuangtai.setText("已完成");
                }
                if (mLists.get(position).getOrderState().equals("REFUND")) {
                    ((DVh) vh).mZhuangtai.setText("等待收货");
                }
                if (mLists.get(position).getOrderState().equals("CANCEL")) {
                    ((DVh) vh).mZhuangtai.setText("已取消");
                }
                if (mLists.get(position).getOrderState().equals("INIT")) {
                    ((DVh) vh).mZhuangtai.setText("等待支付");
                }

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContent, LinearLayoutManager.HORIZONTAL, false);
                ((DVh) vh).mRlv.setLayoutManager(linearLayoutManager);
                CompletedlishAdapter_B all_b_adapter = new CompletedlishAdapter_B(mContent);
                ((DVh) vh).mRlv.setAdapter(all_b_adapter);
                all_b_adapter.addAll(goodsize);
                ((DVh) vh).delete_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onDeleteClickListener != null) {
                            onDeleteClickListener.onClick(mLists.get(position).getOrderId(), position);
                        }
                    }
                });
                all_b_adapter.setOnGoParicListener(new CloseAdapter_B.onGoParicListener() {
                    @Override
                    public void onClick(int goodId) {
                        if (onPartiCliclListener != null) {
                            onPartiCliclListener.onClick(mLists.get(position).getOrderId(), mLists.get(position).getOrderState(), position);
                        }
                    }
                });
                ((DVh) vh).yellow_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onBuySomeClickListener != null) {
                            for (int i = 0; i < mLists.get(position).getOrderDetailsList().size(); i++) {
                                int gId = mLists.get(position).getOrderDetailsList().get(i).getDataId();
                                goodsId += gId + ",";
                            }
                            onBuySomeClickListener.onClick(goodsId.substring(0, goodsId.length() - 1));
                            goodsId = "";
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
                            onPartiCliclListener.onClick(mLists.get(position).getOrderId(), mLists.get(position).getOrderState(), position);
                        }
                    }
                });
            }
        } else {
            ((DVhOne) vh).delete_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onDeleteClickListener != null) {
                        onDeleteClickListener.onClick(mLists.get(position).getOrderId(), position);
                    }
                }
            });
            ((DVhOne) vh).yellow_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onBuyClickListener != null) {
                        onBuyClickListener.onClick(mLists.get(position).getOrderDetailsList().get(0).getDataId(), mLists.get(position).getOrderDetailsList().get(0).getBuyNum(), mLists.get(position).getOrderDetailsList().get(0).getPrice());
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mLists.get(position).getOrderDetailsList() == null) {
            return 1;
        } else {
            if (mLists.get(position).getOrderDetailsList().size() == 1) {
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
        private TextView delete_text;
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
            delete_text = itemView.findViewById(R.id.delete_text);
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
        TextView delete_text;
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
            delete_text = itemView.findViewById(R.id.delete_text);
        }
    }

    class NullView extends RecyclerView.ViewHolder {

        public NullView(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

    /**
     * 删除订单
     */
    public interface onDeleteClickListener {
        void onClick(int oriderId, int position);
    }

    onDeleteClickListener onDeleteClickListener;

    public void setOnDeleteClickListener(onDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
    }

    /**
     * 再次购买
     */
    public interface onBuyClickListener {
        void onClick(int goodId, int num, double price);
    }

    onBuyClickListener onBuyClickListener;

    public void setOnBuyClickListener(onBuyClickListener onBuyClickListener) {
        this.onBuyClickListener = onBuyClickListener;
    }

    /**
     * 再次购买多个商品
     */
    public interface onBuySomeClickListener {
        void onClick(String goodsId);

    }

    onBuySomeClickListener onBuySomeClickListener;

    public void setOnBuySomeClickListener(onBuySomeClickListener onBuySomeClickListener) {
        this.onBuySomeClickListener = onBuySomeClickListener;
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

    public interface onClicklistener {
        void onClick(int id, int lago);
    }

    onClicklistener onClicklistener;

    public void setOnClicklistener(CompletedlishAdapter_A.onClicklistener onClicklistener) {
        this.onClicklistener = onClicklistener;
    }
}