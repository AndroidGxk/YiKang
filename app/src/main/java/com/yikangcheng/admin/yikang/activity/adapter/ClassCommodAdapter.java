package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.bean.ClassifyCommodityListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：古祥坤 on 2019/5/24 16:26
 * 邮箱：1724959985@qq.com
 */
public class ClassCommodAdapter extends RecyclerView.Adapter {
    List<ClassifyCommodityListBean.CommodityListBean> listBeanList = new ArrayList<>();
    Context context;
    int state = 1;

    public ClassCommodAdapter(Context context) {
        this.context = context;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void addAll(List<ClassifyCommodityListBean.CommodityListBean> listBeanList) {
        this.listBeanList.addAll(listBeanList);
        notifyDataSetChanged();
    }

    public void removeAll() {
        this.listBeanList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classcommod_recycler_item, parent, false);
            return new Vh(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classcommod_recycler_two_item, parent, false);
            return new Vhtwo(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vh, final int position) {
        if (vh instanceof Vh) {
            ClassifyCommodityListBean.CommodityListBean commodityListBean = listBeanList.get(position);
            ((Vh) vh).title.setText(commodityListBean.getName());
            ((Vh) vh).count.setText(commodityListBean.getTitle());
            Glide.with(context).load(Constants.BASETUPIANSHANGCHUANURL + listBeanList.get(position).getLogo()).into(((Vh) vh).imageView);
            ((Vh) vh).price.setText("内购价：¥" + commodityListBean.getCurrentprice());
            ((Vh) vh).yuanprice.setText("市场价：¥" + commodityListBean.getSourceprice());
            ((Vh) vh).buy_text.setText(commodityListBean.getBuycount() + "人已购");
            ((Vh) vh).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickLisetener.onclick(listBeanList.get(position).getId());
                }
            });
        } else if (vh instanceof Vhtwo) {
            ClassifyCommodityListBean.CommodityListBean commodityListBean = listBeanList.get(position);
            ((Vhtwo) vh).title.setText(commodityListBean.getName());
            ((Vhtwo) vh).count.setText(commodityListBean.getTitle());
            Glide.with(context).load(Constants.BASETUPIANSHANGCHUANURL + listBeanList.get(position).getLogo()).into(((Vhtwo) vh).imageView);
            ((Vhtwo) vh).price.setText("内购价：¥" + commodityListBean.getCurrentprice());
            ((Vhtwo) vh).yuanprice.setText("市场价：¥" + commodityListBean.getSourceprice());
            ((Vhtwo) vh).buy_text.setText(commodityListBean.getBuycount() + "人已购");
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (state == 1) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public int getItemCount() {
        return listBeanList.size();
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
            price = itemView.findViewById(R.id.price);
            yuanprice = itemView.findViewById(R.id.yuanprice);
            buy_text = itemView.findViewById(R.id.buy_text);
        }
    }

    class Vhtwo extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView count;
        TextView price;
        TextView yuanprice;
        TextView buy_text;

        public Vhtwo(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.good_img);
            title = itemView.findViewById(R.id.good_title);
            count = itemView.findViewById(R.id.good_count);
            price = itemView.findViewById(R.id.good_price);
            yuanprice = itemView.findViewById(R.id.good_yuanprice);
            buy_text = itemView.findViewById(R.id.good_buy);
        }
    }

    public void setOnClickLisetener(ClassCommodAdapter.onClickLisetener onClickLisetener) {
        this.onClickLisetener = onClickLisetener;
    }

    onClickLisetener onClickLisetener;

    public interface onClickLisetener {
        void onclick(int id);
    }
}
