package com.yikangcheng.admin.yikang.activity.fragment.coupon;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.CommissRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.EffecRecyclerAdapter;
import com.yikangcheng.admin.yikang.base.BaseFragment;

/**
 * 作者：古祥坤 on 2019/5/25 18:56
 * 邮箱：1724959985@qq.com
 * 已失效的优惠券
 */
public class EffecFragment extends BaseFragment {

    private RecyclerView xrecycler;
    private SmartRefreshLayout mRefreshLayout;
    private ImageView zanwushixiao;

    @Override
    protected void initView(View view) {
        xrecycler = view.findViewById(R.id.recycler);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        zanwushixiao = view.findViewById(R.id.zanwushixiao);
        xrecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        xrecycler.setAdapter(new EffecRecyclerAdapter(getContext()));
        //刷新的监听事件
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //请求数据
//                mPage = 1;
//                initMvp(mPage);
                refreshLayout.finishRefresh();  //刷新完成
            }
        });
        zanwushixiao.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.commfragment_item;
    }
}
