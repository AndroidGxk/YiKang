package com.yikangcheng.admin.yikang.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.Orderform_ViewPagerAdapter;
import com.yikangcheng.admin.yikang.activity.fragment.orderform.AccomplishFragment;
import com.yikangcheng.admin.yikang.activity.fragment.orderform.AllFragment;
import com.yikangcheng.admin.yikang.activity.fragment.orderform.AwaitFragment;
import com.yikangcheng.admin.yikang.activity.fragment.orderform.CloseFragment;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class OrderFormActivity extends BaseActivtiy {

    private ImageView mImgActivityOrderfromFanhui;
    private Toolbar mToolbarActivityOrderfrom;
    private TabLayout mTabActivityOrderform;
    private ViewPager mViewPagerOrderform;

    @Override
    protected void initView() {

        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        mImgActivityOrderfromFanhui = findViewById(R.id.img_activity_orderfrom_fanhui);
        mToolbarActivityOrderfrom = findViewById(R.id.toolbar_activity_orderfrom);
        mTabActivityOrderform = findViewById(R.id.tab_activity_orderform);
        mViewPagerOrderform = findViewById(R.id.viewPager_orderform);

        mToolbarActivityOrderfrom.setTitle("");
        setSupportActionBar(mToolbarActivityOrderfrom);

        /**
         * 点击返回关闭当前页面
         */
        mImgActivityOrderfromFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        List<String> strings = new ArrayList<>();
        strings.add("全部");
        strings.add("等待付款");
        strings.add("交易完成");
        strings.add("交易关闭");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new AllFragment());
        fragments.add(new AwaitFragment());
        fragments.add(new AccomplishFragment());
        fragments.add(new CloseFragment());
        Orderform_ViewPagerAdapter orderform_viewPagerAdapter = new Orderform_ViewPagerAdapter(getSupportFragmentManager(), strings, fragments);
        mViewPagerOrderform.setAdapter(orderform_viewPagerAdapter);
        mTabActivityOrderform.setupWithViewPager(mViewPagerOrderform);

    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_order_form;
    }

    @Override
    protected void createPresenter() {

    }
}