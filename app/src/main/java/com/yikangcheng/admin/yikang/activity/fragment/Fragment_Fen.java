package com.yikangcheng.admin.yikang.activity.fragment;

import android.view.View;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.tab.VerticalPager;
import com.yikangcheng.admin.yikang.tab.vpsp;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.TabView;

public class Fragment_Fen extends BaseFragment {

    private VerticalTabLayout mTablayout;
    private VerticalPager mViewpager;
    private List<String> datas = new ArrayList<String>();
    private com.yikangcheng.admin.yikang.tab.vpsp vpsp;


    @Override
    protected void initView(View view) {
        mViewpager = view.findViewById(R.id.viewpager);
        mTablayout = view.findViewById(R.id.tablayout);
    }

    @Override
    protected void initData() {
        datas.add("推荐");
        datas.add("要闻");
        datas.add("娱乐");
        datas.add("科技");
        datas.add("汽车");
        datas.add("体育");
        datas.add("推荐");
        datas.add("要闻");
        datas.add("娱乐");
        datas.add("科技");
        datas.add("汽车");
        datas.add("体育");
        datas.add("推荐");
        datas.add("要闻");
        datas.add("娱乐");
        datas.add("科技");
        datas.add("汽车");
        datas.add("体育");

        //适配器
        vpsp = new vpsp(getChildFragmentManager(), datas);
        mViewpager.setAdapter(vpsp);
        //进行关联
        mTablayout.setupWithViewPager(mViewpager);
        /*mTablayout.setTabBadge(7, 32);
        mTablayout.setTabBadge(2, -1);
        mTablayout.setTabBadge(3, -1);
        mTablayout.setTabBadge(4, -1);*/

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_fen;
    }
}
