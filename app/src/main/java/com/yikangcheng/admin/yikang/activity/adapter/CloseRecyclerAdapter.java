package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.bean.ClassifyListOneBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/5/17.
 * WF
 */
public class CloseRecyclerAdapter extends RecyclerView.Adapter<CloseRecyclerAdapter.Vh> {
    private List<ClassifyListOneBean> stringList = new ArrayList<>();
    private int mPosition;
    private Context context;

    public CloseRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<ClassifyListOneBean> stringList) {
        this.stringList.addAll(stringList);
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
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    class Vh extends RecyclerView.ViewHolder {
        TextView textView;
        View view_ge;

        public Vh(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_item_zuo);
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
