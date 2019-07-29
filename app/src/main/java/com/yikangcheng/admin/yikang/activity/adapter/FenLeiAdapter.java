package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class FenLeiAdapter extends RecyclerView.Adapter<FenLeiAdapter.Vh> {
    private List<ClassifyListOneBean> stringList = new ArrayList<>();
    private int mPosition;
    private Context context;

    public FenLeiAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<ClassifyListOneBean> stringList) {
        this.stringList.addAll(stringList);
        notifyDataSetChanged();
    }


    @Override
    public Vh onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragmnet_fenlei_zuo, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(final Vh vh, final int position) {
        vh.textView.setText(stringList.get(position).getSubjectName());
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //记录点击下标
                mPosition = position;
                mOnclick.onClick(position);
                notifyDataSetChanged();
            }
        });
        for (int i = 0; i < stringList.size(); i++) {
            if (mPosition == position) {
                vh.textView.setTextColor(context.getResources().getColor(R.color.colorTab));
                vh.itemView.setBackgroundResource(R.drawable.fen_text_check_true);
                vh.textView.setTextSize(13);
            } else {
                vh.textView.setTextColor(context.getResources().getColor(R.color.colorText));
                vh.itemView.setBackgroundColor(context.getResources().getColor(R.color.colorTouMing));
                vh.textView.setTextSize(13);
            }
        }
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class Vh extends RecyclerView.ViewHolder {
        TextView textView;

        public Vh(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_item_zuo);
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
