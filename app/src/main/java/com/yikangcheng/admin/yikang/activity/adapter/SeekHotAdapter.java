package com.yikangcheng.admin.yikang.activity.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.seek.SeekActivity;
import com.yikangcheng.admin.yikang.bean.SeekHotBean;

import java.util.ArrayList;

/**
 * Created by lenovo on 2019/5/27.
 * WF
 */
public class SeekHotAdapter extends RecyclerView.Adapter {
    private final ArrayList<SeekHotBean> mList;
    private final SeekActivity mContent;

    public SeekHotAdapter(SeekActivity seekActivity, ArrayList<SeekHotBean> seekHotBeans) {
        this.mList = seekHotBeans;
        this.mContent = seekActivity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContent).inflate(R.layout.item_seekhot_activity, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        Glide.with(mContent).load(mList.get(position).getImg()).into(holder1.mImg);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImg;

        public ViewHolder(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.img_item_seekhot_activity);
        }
    }
}
