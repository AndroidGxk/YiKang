package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.bean.ShopCarBean;
import com.yikangcheng.admin.yikang.util.AddSubLayout;

import java.util.ArrayList;
import java.util.List;


public class ShopRecyclerAdapter extends RecyclerView.Adapter<ShopRecyclerAdapter.Vh> {
    List<ShopCarBean> shopList = new ArrayList<>();
    private Context mContext;

    public ShopRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addAll(List<ShopCarBean> shopList) {
        this.shopList.addAll(shopList);
        notifyDataSetChanged();
    }
    public void remove() {
        this.shopList.clear();
    }

    //接口
    private TotalPriceListener totalPriceListener;

    public void setTotalPriceListener(TotalPriceListener totalPriceListener) {
        this.totalPriceListener = totalPriceListener;
    }

    private checkBoxTouchListener checkBoxTouchListener;

    public void setCheckBoxTouchListener(ShopRecyclerAdapter.checkBoxTouchListener checkBoxTouchListener) {
        this.checkBoxTouchListener = checkBoxTouchListener;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_recycler_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, final int position) {
        final ShopCarBean.ShopSpecDetailedBean shopSpecDetailedBean = shopList.get(position).getShopSpecDetailed();
        if (shopSpecDetailedBean.getCheck() == 0)
            vh.mCheckBox.setChecked(false);
        else
            vh.mCheckBox.setChecked(true);
        java.text.DecimalFormat myformat = new java.text.DecimalFormat("0.00");
        String str = myformat.format(shopSpecDetailedBean.getRetailPrice());
        vh.mPrice.setText("¥" + str);
        //选中商品设置总价
        vh.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                shopSpecDetailedBean.setCheck(b ? 1 : 0);
                sum();
                //反向选中
                revercheckAll();
            }
        });
        vh.addSubLayout.setCount(shopList.get(position).getBuyNum());
        vh.addSubLayout.setAddSubListener(new AddSubLayout.AddSubListener() {
            @Override
            public void addSub(int count) {
                shopList.get(position).setBuyNum(count);
                //计算价格
                sum();
            }
        });
        Glide.with(mContext).load(Constants.BASETUPIANSHANGCHUANURL + shopSpecDetailedBean.getLogo()).into(vh.shop_img);
        vh.shop_count.setText(shopSpecDetailedBean.getCommodityName());
        vh.num_text.setText(shopSpecDetailedBean.getSpecNames());
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }

    class Vh extends RecyclerView.ViewHolder {
        CheckBox mCheckBox;
        TextView mPrice, shop_count, num_text;
        AddSubLayout addSubLayout;
        ImageView shop_img;

        public Vh(View itemView) {
            super(itemView);
            mCheckBox = itemView.findViewById(R.id.check_btn);
            mPrice = itemView.findViewById(R.id.price);
            addSubLayout = itemView.findViewById(R.id.addsub);
            shop_count = itemView.findViewById(R.id.shop_count);
            num_text = itemView.findViewById(R.id.num_text);
            shop_img = itemView.findViewById(R.id.shop_img);
        }
    }

    //全部选中或者全部取消
    public void checkAll(boolean isCheck) {
        //循环数据
        for (int i = 0; i < shopList.size(); i++) {
            //1代表为全部选中
            shopList.get(i).getShopSpecDetailed().setCheck(isCheck ? 1 : 0);
        }
        notifyDataSetChanged();
        //选中之后计算总价方法
        sum();
    }

    //获取选中商品
    public List<ShopCarBean> getShopList() {
        List<ShopCarBean> shopCarBeans = new ArrayList<>();
        for (int i = 0; i < shopList.size(); i++) {
            ShopCarBean shopCarBean = shopList.get(i);
            ShopCarBean.ShopSpecDetailedBean shopSpecDetailed = shopCarBean.getShopSpecDetailed();
            if (shopSpecDetailed.getCheck() == 1) {
                shopCarBeans.add(shopCarBean);
            }
        }
        return shopCarBeans;
    }

    //反向全选或者全部取消
    public void revercheckAll() {
        //判断是否全选
        boolean ischeck;
        //记录选中个数
        int num = 0;
        //循环数据
        for (int i = 0; i < shopList.size(); i++) {
            //判断选中或者未选中
            if (shopList.get(i).getShopSpecDetailed().getCheck() == 1) {
                num++;
            }
        }
        if (num == shopList.size()) {
            ischeck = true;
        } else {
            ischeck = false;
        }
        //给总价格接口设置值
        if (checkBoxTouchListener != null) {
            checkBoxTouchListener.checked(ischeck);
        }
        //选中之后计算总价方法
        sum();
    }

    //计算总价格
    private void sum() {
        double totalPrice = 0;
        int count = 0;
        //循环商品
        for (int i = 0; i < shopList.size(); i++) {
            ShopCarBean.ShopSpecDetailedBean shopSpecDetailedBean = shopList.get(i).getShopSpecDetailed();
            //如果是选中状态才能获取价格（1,是选中状态,0是未选中状态）
            if (shopSpecDetailedBean.getCheck() == 1) {
                //价钱乘以数量得到总价格
                totalPrice = totalPrice + shopSpecDetailedBean.getRetailPrice() * shopList.get(i).getBuyNum();
                count = count + shopList.get(i).getBuyNum();
            }
        }
        //给总价格接口设置值
        if (totalPriceListener != null) {
            totalPriceListener.totalPrice(totalPrice, count);
        }
    }

    //内部类接口
    public interface TotalPriceListener {
        void totalPrice(double totalPrice, int count);
    }

    //内部类接口
    public interface checkBoxTouchListener {
        void checked(boolean isCheck);
    }
}

