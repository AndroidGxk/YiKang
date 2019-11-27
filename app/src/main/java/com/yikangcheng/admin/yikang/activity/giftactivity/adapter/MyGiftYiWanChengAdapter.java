package com.yikangcheng.admin.yikang.activity.giftactivity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.bean.AllAddressBean;
import com.yikangcheng.admin.yikang.bean.AllWelfareBean;
import com.yikangcheng.admin.yikang.bean.WelfareCourseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：古祥坤 on 2019/8/28 17:22
 * 邮箱：1724959985@qq.com
 */
public class MyGiftYiWanChengAdapter extends RecyclerView.Adapter<MyGiftYiWanChengAdapter.Vh> {

    Context context;
    List<AllWelfareBean> welfareCourseBeans = new ArrayList<>();

    public MyGiftYiWanChengAdapter(Context context) {
        this.context = context;
    }


    public void addAll( List<AllWelfareBean> welfareCourseBeans) {
        this.welfareCourseBeans.addAll(welfareCourseBeans);
        notifyDataSetChanged();
    }

    public void removeAll(){
        this.welfareCourseBeans.clear();
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_my_gift_recycler_item_wancheng, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, int position) {
        if (welfareCourseBeans.get(position).getWelfareDetailsList() == null) {
            vh.linear.setVisibility(View.GONE);
                return;
        }
        vh.recy.setLayoutManager(new LinearLayoutManager(context));
        MyGiftYIWanChengTwoAdapter myGiftTwoAdapter = new MyGiftYIWanChengTwoAdapter(context);
        vh.recy.setAdapter(myGiftTwoAdapter);
        if (welfareCourseBeans.get(position).getWelfareDetailsList() != null)
            myGiftTwoAdapter.addAll(welfareCourseBeans.get(position).getWelfareDetailsList());
        vh.time.setText(welfareCourseBeans.get(position).getWelfarefetchTime());
    }


    @Override
    public int getItemCount() {
        return welfareCourseBeans.size();
    }

    class Vh extends RecyclerView.ViewHolder {
        RecyclerView recy;
        TextView name;
        TextView phone;
        TextView address;
        TextView time;

        LinearLayout linear;

        public Vh(View itemView) {
            super(itemView);
            recy = itemView.findViewById(R.id.recy);
            name = itemView.findViewById(R.id.user_name);
            phone = itemView.findViewById(R.id.user_phone);
            time = itemView.findViewById(R.id.time);
            address = itemView.findViewById(R.id.user_address);
            linear = itemView.findViewById(R.id.linear);
        }
    }

    /**
     * 领取
     */
    public interface onClickListener {
        void onClick(int id);
    }

    onClickListener onClickListener;

    public void setOnClickListener(MyGiftYiWanChengAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    /**
     * 选择地址
     */
    public interface onClickAddressListener {
        void onClick();
    }

    onClickAddressListener onClickAddressListener;

    public void setOnClickAddressListener(onClickAddressListener onClickAddressListener) {
        this.onClickAddressListener = onClickAddressListener;
    }
}
