package com.yikangcheng.admin.yikang.activity.fragment.coupon;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
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

    private XRecyclerView xrecycler;

    @Override
    protected void initView(View view) {
        xrecycler = view.findViewById(R.id.xrecycler);
        xrecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        xrecycler.setAdapter(new EffecRecyclerAdapter(getContext()));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.commfragment_item;
    }
}
