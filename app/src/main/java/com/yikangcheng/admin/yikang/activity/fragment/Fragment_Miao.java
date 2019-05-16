package com.yikangcheng.admin.yikang.activity.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.SeckillSecondActivity;
import com.yikangcheng.admin.yikang.activity.adapter.FirstSeckillRecyclerAdapter;
import com.yikangcheng.admin.yikang.base.BaseFragment;

public class Fragment_Miao extends BaseFragment {

    private ImageView miao_one_btn;
    private RecyclerView recycler_one;
    private FirstSeckillRecyclerAdapter firstSeckillRecyclerAdapter;

    @Override
    protected void initView(View view) {
        miao_one_btn = view.findViewById(R.id.miao_one_btn);
        recycler_one = view.findViewById(R.id.recycler_one);
        firstSeckillRecyclerAdapter = new FirstSeckillRecyclerAdapter();
    }

    @Override
    protected void initData() {
        miao_one_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SeckillSecondActivity.class));
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_one.setLayoutManager(layoutManager);
        recycler_one.setAdapter(firstSeckillRecyclerAdapter);
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_miao;
    }
}
