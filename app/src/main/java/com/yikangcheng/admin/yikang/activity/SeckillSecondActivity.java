package com.yikangcheng.admin.yikang.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.SeckillRecyclerAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import me.jessyan.autosize.internal.CustomAdapt;

/**
 * 秒杀二级页面
 */
public class SeckillSecondActivity extends BaseActivtiy implements XRecyclerView.LoadingListener, CustomAdapt {

    private XRecyclerView recycler;
    private SeckillRecyclerAdapter seckillRecyclerAdapter;
    private List<String> stringList = new ArrayList<>();
    private ImageView back_img;

    @Override
    protected void initView() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        seckillRecyclerAdapter = new SeckillRecyclerAdapter();
        back_img = (ImageView) findViewById(R.id.back_img);
        recycler = (XRecyclerView) findViewById(R.id.recycler);
        for (int i = 0; i < 10; i++) {
            stringList.add("s");
        }
    }

    @Override
    protected void initEventData() {
        recycler.setLayoutManager(new LinearLayoutManager(this));
        //解决滑动不流畅
        recycler.setHasFixedSize(true);
        recycler.setNestedScrollingEnabled(false);
        //加载更多
        recycler.setLoadingListener(this);
        recycler.setLoadingMoreEnabled(true);
        recycler.setPullRefreshEnabled(true);
        seckillRecyclerAdapter.addAll(stringList);
        recycler.setAdapter(seckillRecyclerAdapter);
        /**
         * 退出
         */
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_seckill_second;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void onRefresh() {
        stringList.clear();
        seckillRecyclerAdapter.removeAll();
        for (int i = 0; i < 10; i++) {
            stringList.add("");
        }
        seckillRecyclerAdapter.addAll(stringList);
        recycler.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        for (int i = 0; i < 10; i++) {
            stringList.add("");
        }
        seckillRecyclerAdapter.addAll(stringList);
        recycler.loadMoreComplete();
    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 750;
    }

}
