package com.yikangcheng.admin.yikang.activity.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.RecomShopRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.ShopRecyclerAdapter;
import com.yikangcheng.admin.yikang.base.BaseFragment;


public class Fragment_Gou extends BaseFragment {
    private RecyclerView shop_recyclertwo,shop_recycler;
    private ShopRecyclerAdapter shopRecyclerAdapter;

    @Override
    protected void initView(View view) {
        shop_recycler = view.findViewById(R.id.shop_recycler);
        shop_recyclertwo = view.findViewById(R.id.shop_recyclertwo);

    }

    @Override
    protected void initData() {
        //解决滑动不流畅
        shop_recycler.setHasFixedSize(true);
        shop_recycler.setNestedScrollingEnabled(false);
        shop_recyclertwo.setHasFixedSize(true);
        shop_recyclertwo.setNestedScrollingEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                return false;
            }
        };
        // TODO: 2019/5/15 侧拉删除
        //购物车recyclerview
        shop_recycler.setLayoutManager(layoutManager);
        shopRecyclerAdapter = new ShopRecyclerAdapter();

        shop_recycler.setAdapter(shopRecyclerAdapter);
        //为你推荐recyclerview
        shop_recyclertwo.setLayoutManager(new GridLayoutManager(getContext(), 2));
        shop_recyclertwo.setAdapter(new RecomShopRecyclerAdapter());
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_gou;
    }

}
