package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.bean.ClassifyListOneBean;
import com.yikangcheng.admin.yikang.bean.PariticShopBean;
import com.yikangcheng.admin.yikang.bean.ShopCarBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/5/17.
 * WF
 */
public class CloseRecyclerAdapter extends RecyclerView.Adapter<CloseRecyclerAdapter.Vh> {
    private List<ShopCarBean> stringList = new ArrayList<>();
    private List<PariticShopBean> stringLists = new ArrayList<>();
    private int mPosition;
    private Context mContext;

    public CloseRecyclerAdapter(Context context) {
        this.mContext = context;
    }


    public void addAll(List<ShopCarBean> stringList) {
        this.stringList.addAll(stringList);
        notifyDataSetChanged();
    }

    public void addAlls(PariticShopBean stringLists) {
        this.stringLists.add(stringLists);
        notifyDataSetChanged();
    }

    @Override
    public Vh onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.close_recycler_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(final Vh vh, final int position) {
        if (position == getItemCount() - 1) {
            vh.view_ge.setVisibility(View.GONE);
        }
        if (stringList.size() != 0) {
            ShopCarBean shopCarBean = stringList.get(position);
            final ShopCarBean.ShopSpecDetailedBean shopSpecDetailed = shopCarBean.getShopSpecDetailed();
            vh.close_title.setText(shopSpecDetailed.getCommodityName());
            vh.close_gui.setText(shopSpecDetailed.getSpecNames());
            java.text.DecimalFormat myformat = new java.text.DecimalFormat("0.00");
            String str = myformat.format(shopSpecDetailed.getRetailPrice());
            vh.close_price.setText("¥" + str);
            vh.close_num.setText("x" + shopCarBean.getBuyNum());
            if (shopSpecDetailed.getLogo().contains("http://") || shopSpecDetailed.getLogo().contains("https://")) {
                Glide.with(mContext).load(shopSpecDetailed.getLogo()).into(vh.close_img);
            } else {
                Glide.with(mContext).load(Constants.BASETUPIANSHANGCHUANURL + shopSpecDetailed.getLogo()).into(vh.close_img);
            }
        } else if (stringLists.size() != 0) {
            final PariticShopBean pariticShopBean = stringLists.get(position);
            vh.close_title.setText(pariticShopBean.getCommodityName());
            vh.close_gui.setText(pariticShopBean.getDataType());
            vh.close_price.setText("¥" + pariticShopBean.getPrice());
            vh.close_num.setText("x" + pariticShopBean.getBuyNum());
            Glide.with(mContext).load(pariticShopBean.getLogo()).into(vh.close_img);
        }

    }

    @Override
    public int getItemCount() {
        if (stringLists.size() == 0 && stringList.size() != 0) {
            return stringList.size();
        } else if (stringList.size() == 0 && stringLists.size() != 0) {
            return stringLists.size();
        } else {
            return 0;
        }
    }

    class Vh extends RecyclerView.ViewHolder {
        ImageView close_img;
        View view_ge;
        TextView close_title, close_gui, close_price, close_num;

        public Vh(View itemView) {
            super(itemView);
            close_img = (ImageView) itemView.findViewById(R.id.close_img);
            close_title = (TextView) itemView.findViewById(R.id.close_title);
            close_gui = (TextView) itemView.findViewById(R.id.close_gui);
            close_price = (TextView) itemView.findViewById(R.id.close_price);
            close_num = (TextView) itemView.findViewById(R.id.close_num);
            view_ge = (View) itemView.findViewById(R.id.view_ge);
        }
    }

    public interface Onclick {
        void onClick(int position);
    }

    Onclick mOnclick;

    public void setOnclickListener(Onclick mOnclick) {
        this.mOnclick = mOnclick;
    }


}
