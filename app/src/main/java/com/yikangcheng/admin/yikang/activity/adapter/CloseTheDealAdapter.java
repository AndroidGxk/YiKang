package com.yikangcheng.admin.yikang.activity.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.orderstatus.CloseTheDealActivity;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.bean.CloseTheDealBean;
import com.yikangcheng.admin.yikang.bean.WaliDealBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/5/27.
 * WF
 */
public class CloseTheDealAdapter extends RecyclerView.Adapter {
    public ArrayList<WaliDealBean.DetailsListBean> mList;
    private CloseTheDealActivity mContent;
    private OnClickListener mListener;

    public CloseTheDealAdapter(ArrayList<WaliDealBean.DetailsListBean> extraData, CloseTheDealActivity closeTheDealActivity) {
        this.mList = extraData;
        this.mContent = closeTheDealActivity;
    }

    public void removeAll() {
        this.mList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContent).inflate(R.layout.item_closethedeal, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        if (mList.get(position).getShopSpecDetailed().getLogo().contains("http://") || mList.get(position).getShopSpecDetailed().getLogo().contains("https://")) {
            Glide.with(mContent).load( mList.get(position).getShopSpecDetailed().getLogo()).into(holder1.mImg);

        } else {
            Glide.with(mContent).load(Constants.BASETUPIANSHANGCHUANURL + mList.get(position).getShopSpecDetailed().getLogo()).into(holder1.mImg);
        }
        Glide.with(mContent).load(Constants.BASETUPIANSHANGCHUANURL + mList.get(position).getShopSpecDetailed().getLogo()).into(holder1.mImg);
        holder1.mName.setText(mList.get(position).getShopSpecDetailed().getCommodityName());
        holder1.mNum.setText("X" + mList.get(position).getBuyNum());
        holder1.mTitle.setText(mList.get(position).getShopSpecDetailed().getSpecNames());
        holder1.mPrice.setText(mList.get(position).getShopSpecDetailed().getRetailPrice() + "");
        if (mList.get(position).getAfterSaleStatus() == 0 && mList.get(position).getMailStatus() == 2 && mList.get(position).getSignStatus() == 1) {
            holder1.ok_shouhuo.setVisibility(View.VISIBLE);
        } else {
            holder1.ok_shouhuo.setVisibility(View.GONE);
        }
//        if (mList.get(position).getMailStatus() == 2) {
//            holder1.look_log.setVisibility(View.VISIBLE);
//        } else if (mList.get(position).getMailStatus() == 1) {
//            holder1.look_log.setVisibility(View.GONE);
//        }
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int afterSaleStatus = mList.get(position).getAfterSaleStatus();
                mListener.OnClickListener(v, position, afterSaleStatus);
            }
        });
        holder1.mShousou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                afterOnClickListener.onAfterclick(mList.get(position).getId(), mList.get(position).getDataId(), mList.get(position).getOrderId());
            }
        });
        holder1.ok_shouhuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (okBtnOnClickListener != null) {
                    okBtnOnClickListener.onOkBtnListener(mList.get(position).getId());
                }
            }
        });
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toGoodParitual != null) {
                    toGoodParitual.onclick(mList.get(position).getId());
                }
            }
        });
        holder1.look_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (looklog != null) {
                    looklog.onclick(mList.get(position).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addAll(List<WaliDealBean.DetailsListBean> detailsList) {
        this.mList.addAll(detailsList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImg;
        private TextView mName;
        private TextView mTitle;
        private TextView mPrice;
        private TextView mNum;
        private TextView mShousou;
        private TextView ok_shouhuo;
        private TextView look_log;

        public ViewHolder(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.img_item_closethedeal);
            mName = itemView.findViewById(R.id.tv_item_closethedeal_name);
            mTitle = itemView.findViewById(R.id.tv_item_closethedeal_title);
            mPrice = itemView.findViewById(R.id.tv_item_closethedeal_price);
            mNum = itemView.findViewById(R.id.tv_num);
            mShousou = itemView.findViewById(R.id.tv_item_closethedeal_shouhou);
            ok_shouhuo = itemView.findViewById(R.id.ok_shouhuo);
            look_log = itemView.findViewById(R.id.look_log);
        }
    }

    public interface OnClickListener {
        void OnClickListener(View v, int position, int afterSaleStatus);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.mListener = listener;
    }


    //点击售后
    AfterOnClickListener afterOnClickListener;

    public void setAfterOnClickListener(AfterOnClickListener afterOnClickListener) {
        this.afterOnClickListener = afterOnClickListener;
    }

    public interface AfterOnClickListener {
        void onAfterclick(int id, int sid, int ssid);
    }

    OkBtnOnClickListener okBtnOnClickListener;

    public void setOkBtnOnClickListener(OkBtnOnClickListener okBtnOnClickListener) {
        this.okBtnOnClickListener = okBtnOnClickListener;
    }

    //确认收货
    public interface OkBtnOnClickListener {
        void onOkBtnListener(int id);
    }

    toGoodParitual toGoodParitual;

    public void setToGoodParitual(CloseTheDealAdapter.toGoodParitual toGoodParitual) {
        this.toGoodParitual = toGoodParitual;
    }

    //商品详情
    public interface toGoodParitual {
        void onclick(int id);
    }

    Looklog looklog;

    public void setLooklog(Looklog looklog) {
        this.looklog = looklog;
    }

    //查看物流
    public interface Looklog {
        void onclick(int id);
    }
}
