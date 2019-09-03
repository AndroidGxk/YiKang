package com.yikangcheng.admin.yikang.activity.fragment.juhe.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.bean.AddressBean;
import com.yikangcheng.admin.yikang.bean.ProvinceBean;
import com.yikangcheng.admin.yikang.bean.SectionImageBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class SectionRecyclerAdapter extends RecyclerView.Adapter<SectionRecyclerAdapter.Vh> {
    List<SectionImageBean> stringList = new ArrayList<>();
    Context context;

    public SectionRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<SectionImageBean> stringList) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shouye_recycler_recycler_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, final int position) {
        if (position == getItemCount() - 1) {
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setMargins(0, 0, 0, 285);
            vh.itemView.setLayoutParams(layout);
        }
        SectionImageBean sectionImageBean = stringList.get(position);
        vh.title.setText(sectionImageBean.getImagesTitle());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.inco_log);
        requestOptions.fallback(R.drawable.inco_log);
        Glide.with(context).load(Constants.BASETUPIANSHANGCHUANURL + sectionImageBean.getImagesUrl()).apply(requestOptions).into(vh.image);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class Vh extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;

        public Vh(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
        }
    }

    /**
     * 点击事件
     */
    onClickListener onClickListener;

    public void setOnClickListener(SectionRecyclerAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface onClickListener {
        void onClick(int position);
    }
}
