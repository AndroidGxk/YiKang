package com.yikangcheng.admin.yikang.activity.myaccount;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.MyAccountAdapter;
import com.yikangcheng.admin.yikang.activity.fragment.wodezhanghu.AmendFragment;
import com.yikangcheng.admin.yikang.activity.fragment.wodezhanghu.BasicFragment;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;

import java.util.ArrayList;

public class MyaccountActivity extends BaseActivtiy {


    private ImageView mImgActivityMyaccountFanhui;
    private Toolbar mToolbarActivityMyaccount;
    private TabLayout mTabActivityMyaccount;
    private ViewPager mViewPagerActivityMyaccount;

    @Override
    protected void initView() {
        mImgActivityMyaccountFanhui = findViewById(R.id.img_activity_myaccount_fanhui);
        mToolbarActivityMyaccount = findViewById(R.id.toolbar_activity_myaccount);
        mTabActivityMyaccount = findViewById(R.id.tab_activity_myaccount);
        mViewPagerActivityMyaccount = findViewById(R.id.ViewPager_activity_myaccount);

        //ToolBar
        mToolbarActivityMyaccount.setTitle("");
        setSupportActionBar(mToolbarActivityMyaccount);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("基本信息");
        strings.add("修改密码");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new BasicFragment());
        fragments.add(new AmendFragment());

        MyAccountAdapter myAccountAdapter = new MyAccountAdapter(getSupportFragmentManager(), strings, fragments);
        mViewPagerActivityMyaccount.setAdapter(myAccountAdapter);
        mTabActivityMyaccount.setupWithViewPager(mViewPagerActivityMyaccount);
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_myaccount;
    }

    @Override
    protected void createPresenter() {

    }
}
