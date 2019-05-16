package com.yikangcheng.admin.yikang.activity.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.SeckillSecondActivity;
import com.yikangcheng.admin.yikang.activity.adapter.ArticRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.FaddRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.RecomShopRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.particulars.ParticularsActivity;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Shou extends BaseFragment {

    private Banner banner, m_banner;
    List<Integer> imageList = new ArrayList<>();
    private MZBannerView mMZBanner;
    private RecyclerView bao_recycler, mei_recycle, artic_recycler, c_recycler;
    private FaddRecyclerAdapter faddRecyclerAdapter;
    private ArticRecyclerAdapter articRecyclerAdapter;
    private ImageView shou_miao_imag;
    private RecomShopRecyclerAdapter recomShopRecyclerAdapter;

    @Override
    protected void initView(View view) {
        faddRecyclerAdapter = new FaddRecyclerAdapter();
        articRecyclerAdapter = new ArticRecyclerAdapter();
        recomShopRecyclerAdapter = new RecomShopRecyclerAdapter();
        banner = view.findViewById(R.id.banner);
        m_banner = view.findViewById(R.id.m_banner);
        mMZBanner = view.findViewById(R.id.bao_mzbanner);
        bao_recycler = view.findViewById(R.id.bao_recycler);
        shou_miao_imag = view.findViewById(R.id.shou_miao_imag);
        c_recycler = view.findViewById(R.id.c_recycler);
        mei_recycle = view.findViewById(R.id.mei_recycle);
        artic_recycler = view.findViewById(R.id.artic_recycler);
        for (int i = 0; i < 3; i++) {
            if (i % 2 == 0) {
                imageList.add(R.drawable.bao);
            } else {
                imageList.add(R.drawable.ce2);
            }
        }
        //解决滑动不流畅
        bao_recycler.setHasFixedSize(true);
        bao_recycler.setNestedScrollingEnabled(false);
        mei_recycle.setHasFixedSize(true);
        mei_recycle.setNestedScrollingEnabled(false);
        artic_recycler.setHasFixedSize(true);
        artic_recycler.setNestedScrollingEnabled(false);
        c_recycler.setHasFixedSize(true);
        c_recycler.setNestedScrollingEnabled(false);
        faddRecyclerAdapter.setOnClickListener(new FaddRecyclerAdapter.OnClickListener() {
            @Override
            public void OnClickListener(View v, int position) {
                /**
                 * 点击秒杀跳转到详情页面
                 */
                Intent intent = new Intent(getActivity(), ParticularsActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void initData() {
        banner.setImages(imageList);
        banner.setImageLoader(new GlideImageLoader());
        banner.isAutoPlay(false);
        banner.start();
        //美妆Banner
        m_banner.setImages(imageList);
        m_banner.setImageLoader(new GlideImageLoader());
        m_banner.isAutoPlay(false);
        m_banner.start();
        mMZBanner.setIndicatorVisible(false);  // 设置是否显示指示器
        // 设置数据
        mMZBanner.setPages(imageList, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        mMZBanner.start();//开始轮播
        //爆款列表
        GridLayoutManager bLayoutManager = new GridLayoutManager(getContext(), 3);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getContext(), 3);
        LinearLayoutManager aLayoutManager = new LinearLayoutManager(getContext());
        StaggeredGridLayoutManager ctaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        aLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        bao_recycler.setLayoutManager(bLayoutManager);
        bao_recycler.setAdapter(faddRecyclerAdapter);
        mei_recycle.setLayoutManager(mLayoutManager);
        mei_recycle.setAdapter(faddRecyclerAdapter);
        artic_recycler.setLayoutManager(aLayoutManager);
        artic_recycler.setAdapter(faddRecyclerAdapter);
        c_recycler.setLayoutManager(ctaggeredGridLayoutManager);
        c_recycler.setAdapter(recomShopRecyclerAdapter);
        shou_miao_imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SeckillSecondActivity.class));
            }
        });
    }

    public static class BannerViewHolder implements MZViewHolder<Integer> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            // 数据绑定
            mImageView.setImageResource(data);
        }
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_shou;
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

}
