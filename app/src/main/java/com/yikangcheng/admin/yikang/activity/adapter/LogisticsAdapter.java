package com.yikangcheng.admin.yikang.activity.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.bean.WuLiuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/5/26.
 * WF
 */
public class LogisticsAdapter extends RecyclerView.Adapter<LogisticsAdapter.ViewHolder> {

    List<WuLiuBean> wuLiuBeans = new ArrayList<>();

    public void addAll(List<WuLiuBean> wuLiuBeans) {
        this.wuLiuBeans.addAll(wuLiuBeans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wuliurecycler_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder vh, int position) {
        WuLiuBean wuLiuBean = wuLiuBeans.get(position);
        vh.wuliu_title.setText(wuLiuBean.getContext());
        vh.wuliu_time.setText(wuLiuBean.getTime());
    }

    @Override
    public int getItemCount() {
        return wuLiuBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView wuliu_title;
        private TextView wuliu_time;

        public ViewHolder(View itemView) {
            super(itemView);
            wuliu_title = itemView.findViewById(R.id.wuliu_title);
            wuliu_time = itemView.findViewById(R.id.wuliu_time);
        }
    }
}
