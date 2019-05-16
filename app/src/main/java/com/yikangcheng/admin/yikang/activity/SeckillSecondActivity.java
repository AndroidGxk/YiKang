package com.yikangcheng.admin.yikang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.SeckillRecyclerAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;

import java.util.ArrayList;
import java.util.List;

/**
 * 秒杀二级页面
 */
public class SeckillSecondActivity extends BaseActivtiy implements XRecyclerView.LoadingListener {

    private XRecyclerView recycler;
    private SeckillRecyclerAdapter seckillRecyclerAdapter;
    private List<String> stringList = new ArrayList<>();

    @Override
    protected void initView() {
        seckillRecyclerAdapter = new SeckillRecyclerAdapter();
        recycler = findViewById(R.id.recycler);
        for (int i = 0; i < 10; i++) {
            stringList.add("");
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
}
