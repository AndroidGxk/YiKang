package com.yikangcheng.admin.yikang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.SeckillRecyclerAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;

/**
 * 秒杀二级页面
 */
public class SeckillSecondActivity extends BaseActivtiy {

    private RecyclerView recycler;
    private SeckillRecyclerAdapter seckillRecyclerAdapter;

    @Override
    protected void initView() {
        seckillRecyclerAdapter = new SeckillRecyclerAdapter();
        recycler = findViewById(R.id.recycler);
    }

    @Override
    protected void initEventData() {
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(seckillRecyclerAdapter);
        //解决滑动不流畅
        recycler.setHasFixedSize(true);
        recycler.setNestedScrollingEnabled(false);
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_seckill_second;
    }

    @Override
    protected void createPresenter() {

    }
}
