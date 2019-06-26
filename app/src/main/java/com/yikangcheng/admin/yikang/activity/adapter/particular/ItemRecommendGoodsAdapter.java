package com.yikangcheng.admin.yikang.activity.adapter.particular;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.bean.RecommendBean;

import java.util.List;

/**
 * item页底部推荐商品适配器
 */
public class ItemRecommendGoodsAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<RecommendBean> data;

    public ItemRecommendGoodsAdapter(Context context, List<RecommendBean> data) {
        this.context = context;
        this.data = data;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<RecommendBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void addData(List<RecommendBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public List<RecommendBean> getData() {
        return this.data;
    }

    public void clearData() {
        this.data.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_recommend_goods_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        RecommendBean recommendGoods = data.get(position);
        holder.tv_goods_name.setText(recommendGoods.getName());
        holder.tv_goods_price.setText("￥" + recommendGoods.getCurrentprice());
        Glide.with(context).load("https://static.yikch.com" + recommendGoods.getLogo()).into(holder.sdv_goods);
        holder.tv_goods_old_price.setText("￥" + recommendGoods.getSourceprice());
        return convertView;
    }

    class ViewHolder {
        private ImageView sdv_goods;
        private TextView tv_goods_name, tv_goods_price, tv_goods_old_price;

        public ViewHolder(View convertView) {
            sdv_goods = (ImageView) convertView.findViewById(R.id.sdv_goods);
            tv_goods_name = (TextView) convertView.findViewById(R.id.tv_goods_name);
            tv_goods_price = (TextView) convertView.findViewById(R.id.tv_goods_price);
            tv_goods_old_price = (TextView) convertView.findViewById(R.id.tv_goods_old_price);
            tv_goods_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
