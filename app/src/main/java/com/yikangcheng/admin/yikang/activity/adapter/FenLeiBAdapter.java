package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.bean.ClassifyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/5/17.
 * WF
 */
public class FenLeiBAdapter extends RecyclerView.Adapter {
    private final List<ClassifyBean.EntityBean.ChildSubjectListBeanX> mList;
    private final Context mContent;

    public FenLeiBAdapter(ArrayList<ClassifyBean.EntityBean.ChildSubjectListBeanX> childSubjectListBeans, Context context) {
        this.mContent = context;
        this.mList = childSubjectListBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContent).inflate(R.layout.item_fragment_fenlei_you, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.mTv.setText(mList.get(position).getSubjectName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<ClassifyBean.EntityBean.ChildSubjectListBeanX> childSubjectList) {
        mList.addAll(childSubjectList);
        notifyDataSetChanged();
    }

    public void removeAll() {
        this.mList.clear();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv_item_you);
        }
    }
}
