package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.bean.RecommendBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by lenovo on 2019/5/22.
 * WF
 */
public class RecommendAdapter extends RecyclerView.Adapter {
    private final Context mContent;
    private final List<RecommendBean> mList = new ArrayList<>();
    private OnClickListener mListener;

    public void removeAll() {
        mList.clear();
    }

    public RecommendAdapter(Context context) {
        this.mContent = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContent).inflate(R.layout.item_recommend, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        //设置图片圆角角度
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.inco_log);
//        if (mList.get(position).getLogo().contains("http://") || mList.get(position).getLogo().contains("https://")) {
//            Glide.with(mContent).load(mList.get(position).getLogo())
//                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
//                    .apply(requestOptions)
//                    .into(holder1.mImg);
//        } else {
//            Glide.with(mContent).load(Constants.BASETUPIANSHANGCHUANURL + mList.get(position).getLogo())
//                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
//                    .apply(requestOptions)
//                    .into(holder1.mImg);
//        }
//        holder1.mTv_name.setText(mList.get(position).getName());
////        holder1.buymun.setText(mList.get(position).getTitle());
//        holder1.mJiage.setText("市场价:¥" + mList.get(position).getSourceprice());
//        holder1.mBprice.setText(mList.get(position).getCurrentprice() + "");
//        /**
//         * // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
//         */
//        holder1.mBprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
//
//        holder1.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int id = mList.get(position).getId();
//                mListener.OnClickListener(v, id);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<RecommendBean> entity1) {
        mList.addAll(entity1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImg;
        private final TextView mTv_name;
        private final TextView buymun;
        private final TextView mJiage;
        private final TextView mBprice;

        public ViewHolder(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.good_img);
            mTv_name = itemView.findViewById(R.id.recom_title);
            buymun = itemView.findViewById(R.id.recom_count);
            mJiage = itemView.findViewById(R.id.tv_jiage);
            mBprice = itemView.findViewById(R.id.text_bprice);
        }
    }

    public interface OnClickListener {
        void OnClickListener(View v, int id);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.mListener = listener;
    }
}
