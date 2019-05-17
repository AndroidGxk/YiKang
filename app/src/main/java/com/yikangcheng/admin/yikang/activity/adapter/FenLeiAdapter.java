package com.yikangcheng.admin.yikang.activity.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.classify.ClassifyBean;

import java.util.List;

/**
 * Created by lenovo on 2019/5/17.
 * WF
 */
public class FenLeiAdapter extends RecyclerView.Adapter {
    private final List<ClassifyBean.EntityBean> mList;
    private final Context mContent;
    private OnClickListener mListener;
     int mPosition;

    public FenLeiAdapter(Context context, List<ClassifyBean.EntityBean> childSubjectListBeans) {
        this.mList = childSubjectListBeans;
        this.mContent = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContent).inflate(R.layout.item_fragmnet_fenlei_zuo, null, false);

        return new ViewHolder(inflate);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.mTv.setText(mList.get(position).getSubjectName());
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPosition = position;
                mListener.OnClickListener(v, position);
                notifyDataSetChanged();
            }
        });

        for (int i = 0; i < mList.size(); i++) {
            if (mPosition == position) {
                holder1.mTv.setTextColor(R.color.colorToolbar);
                holder1.itemView.setBackgroundColor(R.color.clolrBAai);
            } else {
                holder1.mTv.setTextColor(R.color.colorHei);
//                holder1.mTv.setBackgroundColor(R.color.colorTouMing);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<ClassifyBean.EntityBean> entity) {
        mList.addAll(entity);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv_item_zuo);
        }
    }

    public interface OnClickListener {
        void OnClickListener(View v, int position);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.mListener = listener;
    }
}
