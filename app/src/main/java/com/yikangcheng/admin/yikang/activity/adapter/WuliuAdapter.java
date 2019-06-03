package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.bean.TestBean;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：古祥坤 on 2019/5/19 23:24
 * 邮箱：1724959985@qq.com
 */
public class WuliuAdapter extends RecyclerView.Adapter<WuliuAdapter.Vh> {
    List<TestBean> arrayList = new ArrayList<>();
    private boolean isclick;
    Context context;
    List<Boolean> list = new ArrayList<>();

    public void addAll(List<TestBean> arrayList) {
        this.arrayList.addAll(arrayList);
    }

    public WuliuAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wuliu_recycler_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Vh vh, final int position) {
        for (int i = 0; i < 9; i++) {
            list.add(false);
        }
        if (isclick) {
            vh.itemView.setBackgroundResource(R.drawable.jiaoyi_recycler_shape);
            vh.check_btn.setVisibility(View.GONE);
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!list.get(position)) {
                        vh.itemView.setBackgroundResource(R.drawable.distri_recycler_shape);
                        vh.check_btn.setVisibility(View.VISIBLE);
                        list.set(position, true);
                    } else {
                        vh.itemView.setBackgroundResource(R.drawable.jiaoyi_recycler_shape);
                        vh.check_btn.setVisibility(View.GONE);
                        list.set(position, false);
                    }
                }
            });
        } else {
            vh.check_btn.setVisibility(View.GONE);
            vh.itemView.setBackgroundResource(R.drawable.jiaoyi_recycler_shape);
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "这是未选中状态", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //全部选中或者全部取消
    public void checkAll(boolean isCheck) {
        //循环数据
        for (int i = 0; i < arrayList.size(); i++) {
            //1代表为全部选中
            if (isCheck) {
                arrayList.get(i).setClick(1);
            } else {
                arrayList.get(i).setClick(0);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class Vh extends RecyclerView.ViewHolder {
        TextView textView;
        CheckBox check_btn;

        public Vh(View itemView) {
            super(itemView);
            check_btn = itemView.findViewById(R.id.check_btn);
        }
    }

    //编辑状态
    public void isclick(boolean isclick) {
        this.isclick = isclick;
        notifyDataSetChanged();
    }
}
