package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.bean.ClassifyListOneBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/5/17.
 * WF
 */
public class FenLeiZilieAdapter extends RecyclerView.Adapter {
    private final List<ClassifyListOneBean.ChildSubjectListBeanX.ChildSubjectListBean> mList = new ArrayList<>();
    private final Context mContent;

    public FenLeiZilieAdapter(Context context) {
        this.mContent = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContent).inflate(R.layout.item_fen_right_item, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.right_text.setText(mList.get(position).getSubjectName());
        Glide.with(mContent).load(Constants.BASETUPIANSHANGCHUANURL + mList.get(position).getIcon()).into(holder1.image);
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onclick(position, mList.get(position).getSubjectId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<ClassifyListOneBean.ChildSubjectListBeanX.ChildSubjectListBean> childSubjectList) {
        mList.addAll(childSubjectList);
        notifyDataSetChanged();
    }

    public void removeAll() {
        this.mList.clear();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView right_text;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.right_img);
            right_text = itemView.findViewById(R.id.right_text);
        }
    }

    onClickListener onClickListener;

    public void setOnClickListener(FenLeiZilieAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface onClickListener {
        void onclick(int position, int id);
    }
}
