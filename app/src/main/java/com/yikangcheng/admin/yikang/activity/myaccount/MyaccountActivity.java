package com.yikangcheng.admin.yikang.activity.myaccount;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.MyAccountAdapter;
import com.yikangcheng.admin.yikang.activity.fragment.wodezhanghu.AmendFragment;
import com.yikangcheng.admin.yikang.activity.fragment.wodezhanghu.BasicFragment;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.UserDetailBean;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.ArrayList;

import me.jessyan.autosize.internal.CustomAdapt;

public class MyaccountActivity extends BaseActivtiy implements CustomAdapt {


    private ImageView mImgActivityMyaccountFanhui;
    private RelativeLayout mToolbarActivityMyaccount;
    private TabLayout mTabActivityMyaccount;
    private ViewPager mViewPagerActivityMyaccount;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);


        mImgActivityMyaccountFanhui = findViewById(R.id.img_activity_myaccount_fanhui);
        mToolbarActivityMyaccount = findViewById(R.id.toolbar_activity_myaccount);
        mTabActivityMyaccount = findViewById(R.id.tab_activity_myaccount);
        mViewPagerActivityMyaccount = findViewById(R.id.ViewPager_activity_myaccount);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("基本信息");
        strings.add("修改密码");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new BasicFragment());
        fragments.add(new AmendFragment());
        MyAccountAdapter myAccountAdapter = new MyAccountAdapter(getSupportFragmentManager(), strings, fragments);
        mViewPagerActivityMyaccount.setAdapter(myAccountAdapter);
        mTabActivityMyaccount.setupWithViewPager(mViewPagerActivityMyaccount);



        /**
         * 点击返回按钮 返回上一页
         */
        mImgActivityMyaccountFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 720;
    }
}
