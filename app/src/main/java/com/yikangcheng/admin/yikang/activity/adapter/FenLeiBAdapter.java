package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.bean.ClassifyBean;
import com.yikangcheng.admin.yikang.bean.ClassifyListOneBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/5/17.
 * WF
 */
public class FenLeiBAdapter extends RecyclerView.Adapter {
    private final List<ClassifyListOneBean.ChildSubjectListBeanX> mList = new ArrayList<>();
    private final Context mContent;

    public FenLeiBAdapter(Context context) {
        this.mContent = context;
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
        if (position == getItemCount() - 1) {
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setMargins(0, 0, 0, 85);
            holder.itemView.setLayoutParams(layout);
        }
        holder1.mTv.setText(mList.get(position).getSubjectName());
        holder1.tv_item_you.setLayoutManager(new GridLayoutManager(mContent, 3));
        FenLeiZilieAdapter fenLeiZilieAdapter = new FenLeiZilieAdapter(mContent);
        List<ClassifyListOneBean.ChildSubjectListBeanX.ChildSubjectListBean> childSubjectList = mList.get(position).getChildSubjectList();
        fenLeiZilieAdapter.addData(childSubjectList);
        holder1.tv_item_you.setAdapter(fenLeiZilieAdapter);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<ClassifyListOneBean.ChildSubjectListBeanX> childSubjectList) {
        mList.addAll(childSubjectList);
        notifyDataSetChanged();
    }

    public void removeAll() {
        this.mList.clear();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTv;
        RecyclerView tv_item_you;

        public ViewHolder(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv_item_you);
            tv_item_you = itemView.findViewById(R.id.tv_item_recycler);
        }
    }
}
