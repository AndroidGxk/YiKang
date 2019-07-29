package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
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
public class CompletedlishAdapter_B extends RecyclerView.Adapter {
    private final Context mContent;
    private final List<HaveSignBean.OrderBean.OrderDetailsListBean> mList = new ArrayList<>();
    private CloseAdapter_B.OnClickListener mListener;

    public CompletedlishAdapter_B(Context content) {
        this.mContent = content;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContent).inflate(R.layout.item_seekhot_activity, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        if (position == getItemCount() - 1) {
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setMargins(0, 0, 300, 0);
            holder.itemView.setLayoutParams(layout);
        }
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.inco_log);
        requestOptions.fallback(R.drawable.inco_log);
        if (mList.get(position).getShopImg().contains("https://") || mList.get(position).getShopImg().contains("http://")) {
            Glide.with(mContent).load(mList.get(position).getShopImg()).apply(requestOptions).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into(holder1.mImg);
        } else {
            Glide.with(mContent).load(Constants.BASETUPIANSHANGCHUANURL + mList.get(position).getShopImg()).apply(requestOptions).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into(holder1.mImg);
        }
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onGoParicListener != null) {
                    onGoParicListener.onClick(mList.get(position).getDataId());
                }
            }
        });
    }

    public void addAll(List<HaveSignBean.OrderBean.OrderDetailsListBean> mList) {
        this.mList.addAll(mList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImg;

        public ViewHolder(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.img_item_seekhot_activity);
        }
    }


    public interface OnClickListener {
        void OnClickListener(View v, int orderId);
    }

    public void setOnClickListener(CloseAdapter_B.OnClickListener listener) {
        this.mListener = listener;
    }
    /**
     * 跳转详情
     */
    public interface onGoParicListener {
        void onClick(int goodId);
    }

    CloseAdapter_B.onGoParicListener onGoParicListener;

    public void setOnGoParicListener(CloseAdapter_B.onGoParicListener onGoParicListener) {
        this.onGoParicListener = onGoParicListener;
    }
}