package com.yikangcheng.admin.yikang.activity.adapter.goodcomm_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.bean.UserCommListBean;
import com.yikangcheng.admin.yikang.util.StarBar;

import java.util.ArrayList;
import java.util.List;


/**
 * 已评价数据
 */
public class CompleteRecyclerAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<UserCommListBean.CourseAssessListBean> userCommListBeans = new ArrayList<>();

    public CompleteRecyclerAdapter(Context context) {
        this.context = context;
    }


    public void addAll(List<UserCommListBean.CourseAssessListBean> userCommListBeans) {
        this.userCommListBeans.addAll(userCommListBeans);
        notifyDataSetChanged();
    }

    public void removeAll() {
        this.userCommListBeans.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complecommit_recycle_item, parent, false);
            return new Vh1(view);
        } else if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complecommit_recycle_item2, parent, false);
            return new Vh2(view);
        } else if (viewType == 3) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complecommit_recycle_item3, parent, false);
            return new Vh3(view);
        } else if (viewType == 4) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complecommit_recycle_item4, parent, false);
            return new Vh4(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vh, final int position) {
        final ArrayList<String> imagList = new ArrayList<>();
        if (vh instanceof Vh1) {
            ((Vh1) vh).start.setIntegerMark(true);
            ((Vh1) vh).start.setStarMark(userCommListBeans.get(position).getStarClass());
            ((Vh1) vh).good_title.setText(userCommListBeans.get(position).getCommodityName());
            ((Vh1) vh).good_commit.setText(userCommListBeans.get(position).getContent());
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.inco_log);
            requestOptions.fallback(R.drawable.inco_log);
            Glide.with(context).load(Constants.BASETUPIANSHANGCHUANURL + userCommListBeans.get(position).getLogo())
                    .apply(requestOptions)
                    .into(((Vh1) vh).good_img);
            ((Vh1) vh).rela.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onClickIdListener!=null){
                        onClickIdListener.onClick(userCommListBeans.get(position).getCommodityId());
                    }
                }
            });
        } else if (vh instanceof Vh2) {
            ((Vh2) vh).start.setIntegerMark(true);
            ((Vh2) vh).start.setStarMark(userCommListBeans.get(position).getStarClass());
            ((Vh2) vh).good_title.setText(userCommListBeans.get(position).getCommodityName());
            ((Vh2) vh).good_commit.setText(userCommListBeans.get(position).getContent());
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.inco_log);
            requestOptions.fallback(R.drawable.inco_log);
            Glide.with(context).load(Constants.BASETUPIANSHANGCHUANURL + userCommListBeans.get(position).getLogo())
                    .apply(requestOptions)
                    .into(((Vh2) vh).good_img);
            ((Vh2) vh).rela.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onClickIdListener!=null){
                        onClickIdListener.onClick(userCommListBeans.get(position).getCommodityId());
                    }
                }
            });
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            ((Vh2) vh).img_recy.setLayoutManager(linearLayoutManager);
            CommAdapterImag commAdapterImag = new CommAdapterImag(context);
            ((Vh2) vh).img_recy.setAdapter(commAdapterImag);
            if (!userCommListBeans.get(position).getImage1().equals("")) {
                imagList.add(userCommListBeans.get(position).getImage1());
                Glide.with(context).load(userCommListBeans.get(position).getImage1())
                        .apply(requestOptions)
                        .into(((Vh2) vh).imag1);
                ((Vh2) vh).imag1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onClickListener != null) {
                            onClickListener.onClick(imagList);
                        }
                    }
                });
            }
            commAdapterImag.addAll(imagList);

        } else if (vh instanceof Vh3) {
            ((Vh3) vh).start.setIntegerMark(true);
            ((Vh3) vh).start.setStarMark(userCommListBeans.get(position).getStarClass());
            ((Vh3) vh).good_title.setText(userCommListBeans.get(position).getCommodityName());
            ((Vh3) vh).good_commit.setText(userCommListBeans.get(position).getContent());
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.inco_log);
            requestOptions.fallback(R.drawable.inco_log);
            Glide.with(context).load(Constants.BASETUPIANSHANGCHUANURL + userCommListBeans.get(position).getLogo())
                    .apply(requestOptions)
                    .into(((Vh3) vh).good_img);
            ((Vh3) vh).rela.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onClickIdListener!=null){
                        onClickIdListener.onClick(userCommListBeans.get(position).getCommodityId());
                    }
                }
            });
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            ((Vh3) vh).img_recy.setLayoutManager(linearLayoutManager);
            CommAdapterImag commAdapterImag = new CommAdapterImag(context);
            ((Vh3) vh).img_recy.setAdapter(commAdapterImag);
            if (!userCommListBeans.get(position).getImage1().equals("")) {
                imagList.add(userCommListBeans.get(position).getImage1());
                Glide.with(context).load(userCommListBeans.get(position).getImage1())
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
            if (!userCommListBeans.get(position).getImage2().equals("")) {
                imagList.add(userCommListBeans.get(position).getImage2());
                Glide.with(context).load(userCommListBeans.get(position).getImage2())
                        .apply(requestOptions)
                        .into(((Vh3) vh).imag2);
                ((Vh3) vh).imag2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onClickListener != null) {
                            onClickListener.onClick(imagList);
                        }
                    }
                });
            }
            commAdapterImag.addAll(imagList);
        } else if (vh instanceof Vh4) {
            ((Vh4) vh).start.setIntegerMark(true);
            ((Vh4) vh).start.setStarMark(userCommListBeans.get(position).getStarClass());
            ((Vh4) vh).good_title.setText(userCommListBeans.get(position).getCommodityName());
            ((Vh4) vh).good_commit.setText(userCommListBeans.get(position).getContent());
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.inco_log);
            requestOptions.fallback(R.drawable.inco_log);
            Glide.with(context).load(Constants.BASETUPIANSHANGCHUANURL + userCommListBeans.get(position).getLogo())
                    .apply(requestOptions)
                    .into(((Vh4) vh).good_img);
            ((Vh4) vh).rela.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onClickIdListener!=null){
                        onClickIdListener.onClick(userCommListBeans.get(position).getCommodityId());
                    }
                }
            });
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            ((Vh4) vh).img_recy.setLayoutManager(linearLayoutManager);
            CommAdapterImag commAdapterImag = new CommAdapterImag(context);
            ((Vh4) vh).img_recy.setAdapter(commAdapterImag);
            if (!userCommListBeans.get(position).getImage1().equals("")) {
                imagList.add(userCommListBeans.get(position).getImage1());
                Glide.with(context).load(userCommListBeans.get(position).getImage1())
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
            if (!userCommListBeans.get(position).getImage2().equals("")) {
                imagList.add(userCommListBeans.get(position).getImage2());
                Glide.with(context).load(userCommListBeans.get(position).getImage2())
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
            if (!userCommListBeans.get(position).getImage3().equals("")) {
                imagList.add(userCommListBeans.get(position).getImage3());
                Glide.with(context).load(userCommListBeans.get(position).getImage3())
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
            commAdapterImag.addAll(imagList);
        }
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClickIdListener!=null){
                    onClickIdListener.onClick(userCommListBeans.get(position).getCommodityId());
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (userCommListBeans.get(position).getImage1().equals("") && userCommListBeans.get(position).getImage2().equals("") && userCommListBeans.get(position).getImage3().equals("")) {
            return 1;
        } else if (!userCommListBeans.get(position).getImage1().equals("") && userCommListBeans.get(position).getImage2().equals("")) {
            return 2;
        } else if (!userCommListBeans.get(position).getImage1().equals("") && !userCommListBeans.get(position).getImage2().equals("") && userCommListBeans.get(position).getImage3().equals("")) {
            return 3;
        } else if (!(userCommListBeans.get(position).getImage1().equals("") && userCommListBeans.get(position).getImage2().equals("") && userCommListBeans.get(position).getImage3().equals(""))) {
            return 4;
        } else {
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return userCommListBeans.size();
    }

    class Vh1 extends RecyclerView.ViewHolder {
        StarBar start;
        View view;
        RelativeLayout rela;
        TextView good_commit;
        TextView good_title;
        ImageView good_img;

        public Vh1(View itemView) {
            super(itemView);
            start = itemView.findViewById(R.id.start);
            view = itemView.findViewById(R.id.view);
            rela = itemView.findViewById(R.id.rela);
            good_title = itemView.findViewById(R.id.good_title);
            good_commit = itemView.findViewById(R.id.good_commit);
            good_img = itemView.findViewById(R.id.good_img);
        }
    }

    class Vh2 extends RecyclerView.ViewHolder {
        StarBar start;
        View view;
        RelativeLayout rela;
        TextView good_commit;
        TextView good_title;
        ImageView good_img;
        ImageView imag1;
        RecyclerView img_recy;

        public Vh2(View itemView) {
            super(itemView);
            start = itemView.findViewById(R.id.start);
            view = itemView.findViewById(R.id.view);
            rela = itemView.findViewById(R.id.rela);
            good_title = itemView.findViewById(R.id.good_title);
            good_commit = itemView.findViewById(R.id.good_commit);
            good_img = itemView.findViewById(R.id.good_img);
            imag1 = itemView.findViewById(R.id.img1);
            img_recy = itemView.findViewById(R.id.img_recy);
        }
    }

    class Vh3 extends RecyclerView.ViewHolder {
        StarBar start;
        View view;
        RelativeLayout rela;
        TextView good_commit;
        TextView good_title;
        ImageView good_img;
        ImageView imag1;
        ImageView imag2;
        RecyclerView img_recy;

        public Vh3(View itemView) {
            super(itemView);
            start = itemView.findViewById(R.id.start);
            view = itemView.findViewById(R.id.view);
            rela = itemView.findViewById(R.id.rela);
            good_title = itemView.findViewById(R.id.good_title);
            good_commit = itemView.findViewById(R.id.good_commit);
            good_img = itemView.findViewById(R.id.good_img);
            imag1 = itemView.findViewById(R.id.img1);
            imag2 = itemView.findViewById(R.id.img2);
            img_recy = itemView.findViewById(R.id.img_recy);
        }
    }

    class Vh4 extends RecyclerView.ViewHolder {
        StarBar start;
        View view;
        RelativeLayout rela;
        TextView good_commit;
        TextView good_title;
        ImageView good_img;
        ImageView imag1;
        ImageView imag2;
        ImageView imag3;
        RecyclerView img_recy;

        public Vh4(View itemView) {
            super(itemView);
            start = itemView.findViewById(R.id.start);
            view = itemView.findViewById(R.id.view);
            rela = itemView.findViewById(R.id.rela);
            good_title = itemView.findViewById(R.id.good_title);
            good_commit = itemView.findViewById(R.id.good_commit);
            good_img = itemView.findViewById(R.id.good_img);
            imag1 = itemView.findViewById(R.id.img1);
            imag2 = itemView.findViewById(R.id.img2);
            imag3 = itemView.findViewById(R.id.img3);
            img_recy = itemView.findViewById(R.id.img_recy);
        }
    }

    /**
     * 接口
     */
    public interface onClickListener {
        void onClick(ArrayList<String> pathList);
    }

    onClickListener onClickListener;

    public void setOnClickListener(CompleteRecyclerAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    /**
     * 跳转详情
     */
    public interface  onClickIdListener{
        void onClick(int id);
    }
    onClickIdListener onClickIdListener;

    public void setOnClickIdListener(CompleteRecyclerAdapter.onClickIdListener onClickIdListener) {
        this.onClickIdListener = onClickIdListener;
    }
}
