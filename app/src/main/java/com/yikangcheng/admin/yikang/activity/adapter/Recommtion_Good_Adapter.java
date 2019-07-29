package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.yikangcheng.admin.yikang.bean.RecommendBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：古祥坤 on 2019/5/24 16:26
 * 邮箱：1724959985@qq.com
 */
public class Recommtion_Good_Adapter extends RecyclerView.Adapter<Recommtion_Good_Adapter.Vh> {

    private List<RecommendBean.CommodityListBean>  recommendBeans = new ArrayList<>();
    private Context mContext;

    public Recommtion_Good_Adapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addAll(List<RecommendBean.CommodityListBean>  recommendBeans) {
        this.recommendBeans.addAll(recommendBeans);
        notifyDataSetChanged();
    }

    public void removeAll() {
        this.recommendBeans.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.good_recommiton_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, final int position) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.inco_log);
        requestOptions.fallback(R.drawable.inco_log);
        if (position %2==0) {
            if(position==0){
                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layout.setMargins(0, 0,0 , 80);
                vh.itemView.setLayoutParams(layout);
            }else if(position==1){
                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layout.setMargins(0, 0,0 , 80);
                vh.itemView.setLayoutParams(layout);
            }else{
                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layout.setMargins(0, -50,0 , 80);
                vh.itemView.setLayoutParams(layout);
            }
        }else{
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setMargins(0, -20, 0, 50);
            vh.itemView.setLayoutParams(layout);
        }
        final RecommendBean.CommodityListBean commodityListBean = recommendBeans.get(position);
        //设置图片圆角角度
        if (commodityListBean.getLogo().contains("https://") || commodityListBean.getLogo().contains("http://")) {
            Glide.with(mContext).load(commodityListBean.getLogo())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .apply(requestOptions)
                    .into(vh.imageView);
        } else {
            Glide.with(mContext).load(Constants.BASETUPIANSHANGCHUANURL + commodityListBean.getLogo())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .apply(requestOptions)
                    .into(vh.imageView);
        }
        vh.count.setText(commodityListBean.getName());
        vh.title.setText(commodityListBean.getName());
        vh.price.setText("内购价格:¥" + commodityListBean.getCurrentprice());
        vh.yuanprice.setText("市场价格:¥" + commodityListBean.getSourceprice());
//        vh.buy_text.setText(commodityListBean.getFreight()+"人已购买");
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.OnClickListener(commodityListBean.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return recommendBeans.size();
    }

    class Vh extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView count;
        TextView price;
        TextView yuanprice;
        TextView buy_text;

        public Vh(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.good_img);
            title = itemView.findViewById(R.id.good_title);
            count = itemView.findViewById(R.id.good_count);
            price = itemView.findViewById(R.id.good_price);
            yuanprice = itemView.findViewById(R.id.good_marprice);
            buy_text = itemView.findViewById(R.id.good_buynum);
        }
    }

    public interface OnClickListener {
        void OnClickListener(int id);
    }

    private OnClickListener mListener;

    public void setmListener(OnClickListener mListener) {
        this.mListener = mListener;
    }
}
