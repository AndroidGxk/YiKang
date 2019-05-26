package com.yikangcheng.admin.yikang.activity.coupon;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.MyAccountAdapter;
import com.yikangcheng.admin.yikang.activity.fragment.coupon.CommFragment;
import com.yikangcheng.admin.yikang.activity.fragment.coupon.EffecFragment;
import com.yikangcheng.admin.yikang.activity.fragment.wodezhanghu.AmendFragment;
import com.yikangcheng.admin.yikang.activity.fragment.wodezhanghu.BasicFragment;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.ArrayList;

public class CouponActivity extends BaseActivtiy {
    private TabLayout coupon_tab;
    private ViewPager viewpage;

    @Override
    protected void initView() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        coupon_tab = findViewById(R.id.coupon_tab);
        viewpage = findViewById(R.id.viewpage);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("可使用");
        strings.add("已失效");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new CommFragment());
        fragments.add(new EffecFragment());
        MyAccountAdapter myAccountAdapter = new MyAccountAdapter(getSupportFragmentManager(), strings, fragments);
        viewpage.setAdapter(myAccountAdapter);
        coupon_tab.setupWithViewPager(viewpage);
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_coupon;
    }

    @Override
    protected void createPresenter() {

    }
}