package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yikangcheng.admin.yikang.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：古祥坤 on 2019/5/19 23:24
 * 邮箱：1724959985@qq.com
 */
public class SystemRecyclerAdapter extends RecyclerView.Adapter<SystemRecyclerAdapter.Vh> {
    ArrayList<String> arrayList = new ArrayList<>();
    Context context;
    private boolean isclick;
    List<Boolean> list = new ArrayList<>();

    public SystemRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void addAll(ArrayList<String> arrayList) {
        this.arrayList.addAll(arrayList);
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.system_recycler_item, parent, false);
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
                        vh.itemView.setBackgroundResource(R.drawable.system_recycler_shape);
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

    @Override
    public int getItemCount() {
        return 9;
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
