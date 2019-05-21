package com.yikangcheng.admin.yikang.activity.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.FaddishActivity;
import com.yikangcheng.admin.yikang.activity.SeckillSecondActivity;
import com.yikangcheng.admin.yikang.activity.adapter.ArticRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.FaddRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.LikeAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.LuxuryRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.particulars.ParticularsActivity;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.LikeBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.LikePresenter;
import com.yikangcheng.admin.yikang.util.UIUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.internal.CustomAdapt;
import me.jessyan.autosize.utils.LogUtils;

public class Fragment_Shou extends BaseFragment implements CustomAdapt, ICoreInfe {

    private Banner banner, m_banner;
    List<Integer> imageList = new ArrayList<>();
    private MZBannerView mMZBanner;
    private RecyclerView bao_recycler, mei_recycle, artic_recycler, c_recycler;
    private FaddRecyclerAdapter faddRecyclerAdapter;
    private ArticRecyclerAdapter articRecyclerAdapter;
    private ImageView shou_miao_imag;
    private LikeAdapter mLikeAdapter;
    //    private SmartRefreshLayout mRefreshLayout;
    private int mPage = 1;
    private RelativeLayout bao_rela;
    private SmartRefreshLayout mSmartRefreshLayout;
    private LuxuryRecyclerAdapter luxuryRecyclerAdapter;

    @Override
    protected void initView(View view) {

        initP(mPage);
        AutoSizeConfig.getInstance().setCustomFragment(true);
        faddRecyclerAdapter = new FaddRecyclerAdapter();
        articRecyclerAdapter = new ArticRecyclerAdapter();
        luxuryRecyclerAdapter = new LuxuryRecyclerAdapter();
        banner = view.findViewById(R.id.banner);
        m_banner = view.findViewById(R.id.m_banner);
        mMZBanner = view.findViewById(R.id.bao_mzbanner);
        bao_rela = view.findViewById(R.id.bao_rela);
        bao_recycler = view.findViewById(R.id.bao_recycler);
        shou_miao_imag = view.findViewById(R.id.shou_miao_imag);
        c_recycler = view.findViewById(R.id.c_recycler);
        mei_recycle = view.findViewById(R.id.mei_recycle);
        artic_recycler = view.findViewById(R.id.artic_recycler);
        mSmartRefreshLayout = view.findViewById(R.id.refreshLayout);

//        mRefreshLayout.setOnLoadMoreListener(this);
//        mRefreshLayout.setOnRefreshListener(this);
//        mRefreshLayout = view.findViewById(R.id.refreshLayout);
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
                if (!UIUtils.isFastClick()) {
                    Intent intent = new Intent(getActivity(), ParticularsActivity.class);
                    startActivity(intent);
                }

            }
        });
        StaggeredGridLayoutManager ctaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        c_recycler.setLayoutManager(ctaggeredGridLayoutManager);
        List<LikeBean> entityBeans = new ArrayList<>();
        mLikeAdapter = new LikeAdapter(entityBeans, getContext());
        c_recycler.setAdapter(mLikeAdapter);

        /**刷新的监听事件
         *
         */
        initListener();


    }

    /**
     * 刷新加载
     */
    private void initListener() {
        //刷新的监听事件
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //请求数据
                refreshLayout.finishRefresh();  //刷新完成
            }
        });
        //加载的监听事件
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPage++;
                initP(mPage);
                //Log.e("tag", "onLoadMore: " + mPage + "-----------");
                refreshLayout.finishLoadMore();      //加载完成
                refreshLayout.finishLoadMoreWithNoMoreData();  //全部加载完成,没有数据了调用此方法
            }
        });
    }

    private void initP(int pager) {
        LikePresenter likePresenter = new LikePresenter(this);
        //Log.e("tag", "initP: " + pager + "======================");
        likePresenter.request(11, pager);
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

        aLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        bao_recycler.setLayoutManager(bLayoutManager);
        bao_recycler.setAdapter(faddRecyclerAdapter);
        mei_recycle.setLayoutManager(mLayoutManager);
        mei_recycle.setAdapter(faddRecyclerAdapter);
        artic_recycler.setLayoutManager(aLayoutManager);
        artic_recycler.setAdapter(luxuryRecyclerAdapter);
        shou_miao_imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!UIUtils.isFastClick()) {
                    startActivity(new Intent(getContext(), SeckillSecondActivity.class));
                }
            }
        });

        /**
         * 跳转到爆款页面
         */
        bao_rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!UIUtils.isFastClick()) {
                    startActivity(new Intent(getActivity(), FaddishActivity.class));
                }
            }
        });
        /**
         * 奢侈品跳转详情页
         */
        luxuryRecyclerAdapter.setOnClickListener(new LuxuryRecyclerAdapter.OnClickListener() {
            @Override
            public void OnClickListener(View v, int position) {
                if (!UIUtils.isFastClick()) {
                    startActivity(new Intent(getActivity(), ParticularsActivity.class));
                }
            }
        });
    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 1920;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void success(Object data) {
        Request request = (Request) data;
        List<LikeBean> entity = (List<LikeBean>) request.getEntity();
        // Log.e("list------------------------", entity.toString());
        mLikeAdapter.addData(entity);
    }

    @Override
    public void fail(ApiException e) {

    }

//    @Override
//    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//        mPage++;
//       initP();
//       mLikeAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//        mPage=1;
//        initP();
//
//
//    }

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
