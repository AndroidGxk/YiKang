package com.yikangcheng.admin.yikang.activity.adapter.seeklistnew;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.ClassCommodAdapter;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.bean.ClassifyCommodityListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：古祥坤 on 2019/6/21 10:17
 * 邮箱：1724959985@qq.com
 */
public class StaffphysiAdapter extends RecyclerView.Adapter<StaffphysiAdapter.Vh> {

    List<ClassifyCommodityListBean.CommodityListBean> listBeanList = new ArrayList<>();
    Context context;

    public StaffphysiAdapter(Context context) {
        this.context = context;
    }

    //添加数据
    public void addAll(List<ClassifyCommodityListBean.CommodityListBean> listBeanList) {
        this.listBeanList.addAll(listBeanList);
        notifyDataSetChanged();
    }

    //删除数据
    public void removeAll() {
        this.listBeanList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medical_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, final int position) {
        final ClassifyCommodityListBean.CommodityListBean commodityListBean = listBeanList.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.inco_log);
        if (commodityListBean.getLogo().contains("http://") || commodityListBean.getLogo().contains("https://")) {
            Glide.with(context)
                    .load(commodityListBean.getLogo())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .apply(requestOptions)
                    .into(vh.good_img);
        } else {
            Glide.with(context)
                    .load(Constants.BASETUPIANSHANGCHUANURL + commodityListBean.getLogo())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .apply(requestOptions)
                    .into(vh.good_img);
        }
        vh.good_title.setText(commodityListBean.getName());
        vh.good_price.setText("¥" + commodityListBean.getCurrentprice());
        vh.good_marprice.setText("门店价:¥" + commodityListBean.getSourceprice());
        vh.good_buynum.setText("月销" + commodityListBean.getBuycount() + "笔");
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLisetener.onclick(commodityListBean.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeanList.size();
    }

    class Vh extends RecyclerView.ViewHolder {
        private ImageView good_img;
        private TextView good_title;
        private TextView good_price;
        private TextView good_marprice;
        private TextView good_buynum;

        public Vh(View itemView) {
            super(itemView);
            good_img = itemView.findViewById(R.id.good_img);
            good_title = itemView.findViewById(R.id.good_title);
            good_price = itemView.findViewById(R.id.good_price);
            good_marprice = itemView.findViewById(R.id.good_marprice);
            good_buynum = itemView.findViewById(R.id.good_buynum);
        }
    }

    public void setOnClickLisetener(ClassCommodAdapter.onClickLisetener onClickLisetener) {
        this.onClickLisetener = onClickLisetener;
    }

    ClassCommodAdapter.onClickLisetener onClickLisetener;

    public interface onClickLisetener {
        void onclick(int id);
    }
}
