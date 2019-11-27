package com.yikangcheng.admin.yikang.activity.giftactivity.adapter;

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

import com.hjq.toast.ToastUtils;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.bean.AllAddressBean;
import com.yikangcheng.admin.yikang.bean.WelfareCourseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：古祥坤 on 2019/8/28 17:22
 * 邮箱：1724959985@qq.com
 */
public class MyGiftOneAdapter extends RecyclerView.Adapter<MyGiftOneAdapter.Vh> {

    Context context;
    int mPosition;
    List<WelfareCourseBean> welfareCourseBeans = new ArrayList<>();

    public MyGiftOneAdapter(Context context) {
        this.context = context;
    }

    AllAddressBean.ListUserAddressBean listUserAddressBean;

    public void addAddress(AllAddressBean.ListUserAddressBean listUserAddressBean, int mPosition) {
        this.listUserAddressBean = listUserAddressBean;
        this.mPosition = mPosition;
        notifyDataSetChanged();
    }

    public void addAll(List<WelfareCourseBean> welfareCourseBeans) {
        this.welfareCourseBeans.addAll(welfareCourseBeans);
        notifyDataSetChanged();
    }

    public void removieAll() {
        this.welfareCourseBeans.clear();
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_my_gift_recycler_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, final int position) {
        vh.recy.setLayoutManager(new LinearLayoutManager(context));
        MyGiftTwoAdapter myGiftTwoAdapter = new MyGiftTwoAdapter(context);
        vh.recy.setAdapter(myGiftTwoAdapter);
        final List<WelfareCourseBean.WelfareDetailsListBean> welfareDetailsListBeans = welfareCourseBeans.get(position).getWelfareDetailsList();
        myGiftTwoAdapter.removeAll();
        if (welfareDetailsListBeans != null)
            myGiftTwoAdapter.addAll(welfareDetailsListBeans);
        vh.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    if (listUserAddressBean != null) {
                        onClickListener.onClick(listUserAddressBean.getId(), position, welfareCourseBeans.get(position).getOrderNumber());
                    } else {
                        ToastUtils.show("请选择收货地址");
                    }
                }
            }
        });
        if (mPosition == position) {
            if (listUserAddressBean != null) {
                vh.line2.setVisibility(View.VISIBLE);
                vh.line1.setVisibility(View.GONE);
                vh.name.setText(listUserAddressBean.getReceiver());
                String mobile = listUserAddressBean.getMobile();
                if (mobile.length() >= 11) {
                    String frontMobile = mobile.substring(0, 3);
                    String frontStr = frontMobile + "****";
                    String AllMobile = frontStr + mobile.substring(7, mobile.length());
                    vh.phone.setText(AllMobile);
                }
                vh.address.setText(listUserAddressBean.getProvinceStr() + listUserAddressBean.getCityStr() + listUserAddressBean.getTownStr() + listUserAddressBean.getAddress());
            } else {
                vh.line2.setVisibility(View.GONE);
                vh.line1.setVisibility(View.VISIBLE);
            }
        }
        vh.line1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickAddressListener != null) {
                    onClickAddressListener.onClick(position);
                }
            }
        });
        vh.line2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickAddressListener != null) {
                    onClickAddressListener.onClick(position);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return welfareCourseBeans.size();
    }

    class Vh extends RecyclerView.ViewHolder {
        RecyclerView recy;
        ImageView img;
        LinearLayout line1;
        LinearLayout linear;
        LinearLayout line2;
        TextView name;
        TextView phone;
        TextView address;

        public Vh(View itemView) {
            super(itemView);
            recy = itemView.findViewById(R.id.recy);
            img = itemView.findViewById(R.id.img);
            line1 = itemView.findViewById(R.id.line1);
            line2 = itemView.findViewById(R.id.line2);
            linear = itemView.findViewById(R.id.linear);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            address = itemView.findViewById(R.id.address);
        }
    }

    /**
     * 领取
     */
    public interface onClickListener {
        void onClick(int id, int position, String welfareId);
    }

    onClickListener onClickListener;

    public void setOnClickListener(MyGiftOneAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    /**
     * 选择地址
     */
    public interface onClickAddressListener {
        void onClick(int position);
    }

    onClickAddressListener onClickAddressListener;

    public void setOnClickAddressListener(onClickAddressListener onClickAddressListener) {
        this.onClickAddressListener = onClickAddressListener;
    }
}
