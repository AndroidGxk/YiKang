package com.yikangcheng.admin.yikang.activity.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.LikeBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.SeckillBean;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.LikePresenter;
import com.yikangcheng.admin.yikang.presenter.SeckillPresenter;
import com.yikangcheng.admin.yikang.util.UIUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.internal.CustomAdapt;
import me.jessyan.autosize.utils.LogUtils;

public class Fragment_Shou extends BaseFragment implements CustomAdapt {


    @Override
    protected void initView(View view) {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_shou;
    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 1920;
    }
}
