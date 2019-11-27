package com.yikangcheng.admin.yikang.activity.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.bean.WelfareCourseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：古祥坤 on 2019/9/5 16:23
 * 邮箱：1724959985@qq.com
 */
public class WelfareCourseAdapter extends RecyclerView.Adapter<WelfareCourseAdapter.Vh> {

    private   List<WelfareCourseBean.WelfareDetailsListBean> welfareCourseBeans= new ArrayList<>();
    private Context context;

    public WelfareCourseAdapter(Context context) {
        this.context = context;
    }

    public void addAll(  List<WelfareCourseBean.WelfareDetailsListBean> welfareCourseBeans) {
        this.welfareCourseBeans.addAll(welfareCourseBeans);
        notifyDataSetChanged();
    }

    public void removeAll() {
        this.welfareCourseBeans.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.welfare_recycler_item, parent, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh vh, int position) {
        if(welfareCourseBeans.get(position).getCurselogo() != null&&!welfareCourseBeans.get(position).getCurselogo().equals("")){
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.inco_log);
            vh.good_title.setText(welfareCourseBeans.get(position).getCursename());
            if (welfareCourseBeans.get(position).getCurselogo().contains("http://") || welfareCourseBeans.get(position).getCurselogo().contains("https://")) {
                Glide.with(context)
                        .load(welfareCourseBeans.get(position).getCurselogo())
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                        .apply(requestOptions)
                        .into(vh.good_img);
            } else {
                Glide.with(context)
                        .load(Constants.BASETUPIANSHANGCHUANURL + welfareCourseBeans.get(position).getCurselogo())
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                        .apply(requestOptions)
                        .into(vh.good_img);
            }
        }else{
            vh.good_title.setText(welfareCourseBeans.get(position).getCouponname());
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.fuliquan);
            requestOptions.fallback(R.drawable.fuliquan);
                Glide.with(context).load("")
                        .apply(requestOptions)
                        .into(vh.good_img);
        }
        if (welfareCourseBeans.get(position).getType() == 0) {
            vh.good_type.setText("节日礼品");
        } else {
            vh.good_type.setText("生日礼品");

        }
    }

    @Override
    public int getItemCount() {
        return welfareCourseBeans.size();
    }

    class Vh extends RecyclerView.ViewHolder {
        ImageView good_img;
        TextView good_title;
        TextView good_type;

        public Vh(View itemView) {
            super(itemView);
            good_img = itemView.findViewById(R.id.good_img);
            good_title = itemView.findViewById(R.id.good_title);
            good_type = itemView.findViewById(R.id.good_type);
        }
    }
}
