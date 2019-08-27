package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.bean.AddressBean;
import com.yikangcheng.admin.yikang.bean.ProvinceBean;

import java.util.ArrayList;
import java.util.List;


public class AddressRecyclerAdapter extends RecyclerView.Adapter<AddressRecyclerAdapter.Vh> {
    List<ProvinceBean> stringList = new ArrayList<>();
    //记录下标
    private int mPosition = 0;
    Context context;
    String color;
    public AddressRecyclerAdapter(Context context,String color) {
        this.context = context;
        this.color = color;
    }

    public void addAll(List<ProvinceBean> stringList) {
        this.stringList.addAll(stringList);
        notifyDataSetChanged();
    }

    public void removeAll() {
        this.stringList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_recycler_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, final int position) {
        final String areaName = stringList.get(position).getAreaName();
        vh.city_text.setText(areaName);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressBean addressBean = new AddressBean();
                addressBean.setAddress(areaName);
                addressBean.setId(stringList.get(position).getId());
                onClickListener.onclick(stringList.get(position).getId(), addressBean);
            }
        });
        if (mPosition == position) {
            vh.city_text.setTextColor(Color.parseColor(color));
        } else {
            vh.city_text.setTextColor(context.getResources().getColor(R.color.colorText));
        }
        mPosition = 0;
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class Vh extends RecyclerView.ViewHolder {
        TextView city_text;

        public Vh(View itemView) {
            super(itemView);
            city_text = itemView.findViewById(R.id.city_text);
        }
    }

    /**
     * 接口回调
     */
    public interface onClickListener {
        void onclick(int id, AddressBean addressBean);
    }

    onClickListener onClickListener;

    public void setOnClickListener(AddressRecyclerAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
