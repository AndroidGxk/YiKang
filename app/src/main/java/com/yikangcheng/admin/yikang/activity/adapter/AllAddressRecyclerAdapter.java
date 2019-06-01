package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.bean.AddressBean;
import com.yikangcheng.admin.yikang.bean.AllAddressBean;
import com.yikangcheng.admin.yikang.bean.ProvinceBean;

import java.util.ArrayList;
import java.util.List;


public class AllAddressRecyclerAdapter extends RecyclerView.Adapter<AllAddressRecyclerAdapter.Vh> {
    List<AllAddressBean.ListUserAddressBean> stringList = new ArrayList<>();

    Context context;
    Integer mPosition = null;

    public AllAddressRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<AllAddressBean.ListUserAddressBean> stringList) {
        this.stringList.addAll(stringList);
        notifyDataSetChanged();
    }

    public void removeAll() {
        this.stringList.clear();
        notifyDataSetChanged();
    }

    public void deletePosition(int Position) {
        this.stringList.remove(Position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alladdress_recycler_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, final int position) {
        final AllAddressBean.ListUserAddressBean listUserAddressBean = stringList.get(position);
        vh.user_name.setText(listUserAddressBean.getReceiver());
        vh.user_address.setText(listUserAddressBean.getProvinceStr() + listUserAddressBean.getCityStr() + listUserAddressBean.getTownStr());
        vh.user_phone.setText(listUserAddressBean.getMobile());
        if (listUserAddressBean.getIsFirst() == 1) {
            vh.moren.setChecked(true);
        } else {
            vh.moren.setChecked(false);
        }
        vh.moren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPosition = position;
                onClickListener.onClick(listUserAddressBean);
                notifyDataSetChanged();
            }
        });
        /**
         * 编辑页面
         */
        vh.compile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compOnClickListener.onClick(listUserAddressBean);
            }
        });
        /**
         * 删除
         */
        vh.dele_rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteOnClickListener.onClick(listUserAddressBean.getId(), position);
            }
        });
        for (int i = 0; i < stringList.size(); i++) {
            if (mPosition != null) {
                if (mPosition == position) {
                    vh.moren.setChecked(true);
                } else {
                    vh.moren.setChecked(false);
                }
            }
        }
        /**
         * 选择商品
         */
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleAddressOnClick.onClick(listUserAddressBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class Vh extends RecyclerView.ViewHolder {
        TextView user_name;
        TextView user_phone;
        TextView user_address;
        RadioButton moren;
        RelativeLayout compile_btn, dele_rela;

        public Vh(View itemView) {
            super(itemView);
            user_name = itemView.findViewById(R.id.user_name);
            user_phone = itemView.findViewById(R.id.user_phone);
            user_address = itemView.findViewById(R.id.user_address);
            moren = itemView.findViewById(R.id.moren);
            compile_btn = itemView.findViewById(R.id.compile_btn);
            dele_rela = itemView.findViewById(R.id.dele_rela);
        }
    }

    onClickListener onClickListener;

    public void setOnClickListener(AllAddressRecyclerAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface onClickListener {
        void onClick(AllAddressBean.ListUserAddressBean listUserAddressBean);
    }

    compOnClickListener compOnClickListener;

    public void setCompOnClickListener(AllAddressRecyclerAdapter.compOnClickListener compOnClickListener) {
        this.compOnClickListener = compOnClickListener;
    }

    public interface compOnClickListener {
        void onClick(AllAddressBean.ListUserAddressBean listUserAddressBean);
    }

    deleteOnClickListener deleteOnClickListener;

    public void setDeleteOnClickListener(AllAddressRecyclerAdapter.deleteOnClickListener deleteOnClickListener) {
        this.deleteOnClickListener = deleteOnClickListener;
    }

    public interface deleteOnClickListener {
        void onClick(int id, int position);
    }

    public interface SeleAddressOnClick {
        void onClick(AllAddressBean.ListUserAddressBean userAddressBean);
    }

    SeleAddressOnClick seleAddressOnClick;

    public void setSeleAddressOnClick(SeleAddressOnClick seleAddressOnClick) {
        this.seleAddressOnClick = seleAddressOnClick;
    }
}
