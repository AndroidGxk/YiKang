package com.yikangcheng.admin.yikang.activity.adapter.goodcomm_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.AwaitAdapter;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.bean.GoodComBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：古祥坤 on 2019/7/17 16:29
 * 邮箱：1724959985@qq.com
 */
public class GoodCommRecyclerAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<GoodComBean.CourseAssessListBean> courseAssessListBeans = new ArrayList<>();

    public GoodCommRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<GoodComBean.CourseAssessListBean> courseAssessListBeans) {
        this.courseAssessListBeans.addAll(courseAssessListBeans);
        notifyDataSetChanged();
    }

    public void removeAll() {
        this.courseAssessListBeans.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_good_com_item, parent, false);
            return new Vh1(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_good_com_item2, parent, false);
            return new Vh2(view);
        } else if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_good_com_item3, parent, false);
            return new Vh3(view);
        } else if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_good_com_item4, parent, false);
            return new Vh4(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vh, int position) {
        GoodComBean.CourseAssessListBean courseAssessListBean = courseAssessListBeans.get(position);
        final ArrayList<String> imagList = new ArrayList<>();
        if (vh instanceof Vh1) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.touxiang_2);
            requestOptions.fallback(R.drawable.touxiang_2);
            Glide.with(context).load(Constants.BASETUPIANSHANGCHUANURL + courseAssessListBean.getAvatar())
                    .apply(requestOptions)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(((Vh1) vh).img);
            ((Vh1) vh).count.setText(courseAssessListBean.getContent());
            if (courseAssessListBean.getNickName().equals("") || courseAssessListBean.getNickName() == null) {
                ((Vh1) vh).name.setText(courseAssessListBean.getMobile());
            } else {
                ((Vh1) vh).name.setText(courseAssessListBean.getNickName());
            }
            if (!courseAssessListBean.getImage1().equals("")) {
                imagList.add(courseAssessListBean.getImage1());
                requestOptions.placeholder(R.drawable.inco_log);
                requestOptions.fallback(R.drawable.inco_log);
                Glide.with(context).load(courseAssessListBean.getImage1())
                        .apply(requestOptions)
                        .into(((Vh1) vh).imag1);
                ((Vh1) vh).imag1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onClickListener != null) {
                            onClickListener.onClick(imagList);
                        }
                    }
                });
            }
            ((Vh1) vh).date.setText(courseAssessListBean.getCreateTime());
            ((Vh1) vh).spec.setText(courseAssessListBean.getSpecNames());
        } else if (vh instanceof Vh3) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.touxiang_2);
            requestOptions.fallback(R.drawable.touxiang_2);
            Glide.with(context).load(Constants.BASETUPIANSHANGCHUANURL + courseAssessListBean.getAvatar())
                    .apply(requestOptions)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(((Vh3) vh).img);
            ((Vh3) vh).count.setText(courseAssessListBean.getContent());
            if (courseAssessListBean.getNickName().equals("") || courseAssessListBean.getNickName() == null) {

                ((Vh3) vh).name.setText(courseAssessListBean.getMobile());
            } else {
                ((Vh3) vh).name.setText(courseAssessListBean.getNickName());
            }
            if (!courseAssessListBean.getImage1().equals("")) {
                imagList.add(courseAssessListBean.getImage1());
                requestOptions.placeholder(R.drawable.inco_log);
                requestOptions.fallback(R.drawable.inco_log);
                Glide.with(context).load(courseAssessListBean.getImage1())
                        .apply(requestOptions)
                        .into(((Vh3) vh).imag1);
                ((Vh3) vh).imag1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onClickListener != null) {
                            onClickListener.onClick(imagList);
                        }
                    }
                });
            }
            if (!courseAssessListBean.getImage2().equals("")) {
                imagList.add(courseAssessListBean.getImage2());
                requestOptions.placeholder(R.drawable.inco_log);
                requestOptions.fallback(R.drawable.inco_log);
                Glide.with(context).load(courseAssessListBean.getImage2())
                        .apply(requestOptions)
                        .into(((Vh3) vh).img2);
                ((Vh3) vh).img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onClickListener != null) {
                            onClickListener.onClick(imagList);
                        }
                    }
                });
            }
            ((Vh3) vh).date.setText(courseAssessListBean.getCreateTime());
            ((Vh3) vh).spec.setText(courseAssessListBean.getSpecNames());
        } else if (vh instanceof Vh4) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.touxiang_2);
            requestOptions.fallback(R.drawable.touxiang_2);
            Glide.with(context).load(Constants.BASETUPIANSHANGCHUANURL + courseAssessListBean.getAvatar())
                    .apply(requestOptions)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(((Vh4) vh).img);
            ((Vh4) vh).count.setText(courseAssessListBean.getContent());
            if (courseAssessListBean.getNickName().equals("") || courseAssessListBean.getNickName() == null) {
                ((Vh4) vh).name.setText(courseAssessListBean.getMobile());
            } else {
                ((Vh4) vh).name.setText(courseAssessListBean.getNickName());
            }
            if (!courseAssessListBean.getImage1().equals("")) {
                imagList.add(courseAssessListBean.getImage1());
                requestOptions.placeholder(R.drawable.inco_log);
                requestOptions.fallback(R.drawable.inco_log);
                Glide.with(context).load(courseAssessListBean.getImage1())
                        .apply(requestOptions)
                        .into(((Vh4) vh).imag1);
                ((Vh4) vh).imag1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onClickListener != null) {
                            onClickListener.onClick(imagList);
                        }
                    }
                });
            }
            if (!courseAssessListBean.getImage2().equals("")) {
                imagList.add(courseAssessListBean.getImage2());
                requestOptions.placeholder(R.drawable.inco_log);
                requestOptions.fallback(R.drawable.inco_log);
                Glide.with(context).load(courseAssessListBean.getImage2())
                        .apply(requestOptions)
                        .into(((Vh4) vh).imag2);
                ((Vh4) vh).imag2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onClickListener != null) {
                            onClickListener.onClick(imagList);
                        }
                    }
                });
            }
            if (!courseAssessListBean.getImage3().equals("")) {
                imagList.add(courseAssessListBean.getImage3());
                requestOptions.placeholder(R.drawable.inco_log);
                requestOptions.fallback(R.drawable.inco_log);
                Glide.with(context).load(courseAssessListBean.getImage3())
                        .apply(requestOptions)
                        .into(((Vh4) vh).imag3);
                ((Vh4) vh).imag3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onClickListener != null) {
                            onClickListener.onClick(imagList);
                        }
                    }
                });
            }
            ((Vh4) vh).date.setText(courseAssessListBean.getCreateTime());
            ((Vh4) vh).spec.setText(courseAssessListBean.getSpecNames());
        } else if (vh instanceof Vh2) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.touxiang_2);
            requestOptions.fallback(R.drawable.touxiang_2);
            Glide.with(context).load(Constants.BASETUPIANSHANGCHUANURL + courseAssessListBean.getAvatar())
                    .apply(requestOptions)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(((Vh2) vh).img);
            ((Vh2) vh).count.setText(courseAssessListBean.getContent());
            if (courseAssessListBean.getNickName().equals("") || courseAssessListBean.getNickName() == null) {
                ((Vh2) vh).name.setText(courseAssessListBean.getMobile());
            } else {
                ((Vh2) vh).name.setText(courseAssessListBean.getNickName());
            }
            ((Vh2) vh).date.setText(courseAssessListBean.getCreateTime());
            ((Vh2) vh).spec.setText(courseAssessListBean.getSpecNames());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (courseAssessListBeans.get(position).getImage1().equals("")) {
            return 2;
        } else if (!courseAssessListBeans.get(position).getImage1().equals("") && courseAssessListBeans.get(position).getImage2().equals("") && courseAssessListBeans.get(position).getImage3().equals("")) {
            return 1;
        } else if (!courseAssessListBeans.get(position).getImage1().equals("") && !courseAssessListBeans.get(position).getImage2().equals("") && courseAssessListBeans.get(position).getImage3().equals("")) {
            return 3;
        } else if (!courseAssessListBeans.get(position).getImage1().equals("") && !courseAssessListBeans.get(position).getImage2().equals("") && !courseAssessListBeans.get(position).getImage3().equals("")) {
            return 4;
        } else {
            return 2;
        }
    }

    class Vh1 extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView count;
        TextView date;
        TextView spec;
        ImageView imag1;

        public Vh1(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            count = itemView.findViewById(R.id.count);
            date = itemView.findViewById(R.id.date);
            img = itemView.findViewById(R.id.img);
            imag1 = itemView.findViewById(R.id.img1);
            spec = itemView.findViewById(R.id.spec);
        }
    }

    class Vh3 extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView count;
        TextView date;
        TextView spec;
        ImageView imag1;
        ImageView img2;

        public Vh3(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            count = itemView.findViewById(R.id.count);
            date = itemView.findViewById(R.id.date);
            img = itemView.findViewById(R.id.img);
            imag1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            spec = itemView.findViewById(R.id.spec);
        }
    }

    class Vh4 extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView count;
        TextView date;
        TextView spec;
        ImageView imag1;
        ImageView imag2;
        ImageView imag3;

        public Vh4(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            count = itemView.findViewById(R.id.count);
            date = itemView.findViewById(R.id.date);
            img = itemView.findViewById(R.id.img);
            imag1 = itemView.findViewById(R.id.img1);
            imag2 = itemView.findViewById(R.id.img2);
            imag3 = itemView.findViewById(R.id.img3);
            spec = itemView.findViewById(R.id.spec);
        }
    }

    class Vh2 extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView count;
        TextView date;
        TextView spec;

        public Vh2(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.deta);
            count = itemView.findViewById(R.id.count);
            img = itemView.findViewById(R.id.img);
            spec = itemView.findViewById(R.id.spec);
        }
    }

    @Override
    public int getItemCount() {
        return courseAssessListBeans.size();
    }

    /**
     * 接口
     */
    public interface onClickListener {
        void onClick(ArrayList<String> pathList);
    }

    onClickListener onClickListener;

    public void setOnClickListener(onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
