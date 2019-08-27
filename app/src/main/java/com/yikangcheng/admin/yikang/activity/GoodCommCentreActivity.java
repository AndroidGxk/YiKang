package com.yikangcheng.admin.yikang.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.MyAccountAdapter;
import com.yikangcheng.admin.yikang.activity.fragment.commit_center.CompleteCommit_Fragment;
import com.yikangcheng.admin.yikang.activity.fragment.commit_center.RemainCommit_Fragment;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * 评价中心展示数据，评价和未评价
 */
public class GoodCommCentreActivity extends BaseActivtiy {

    /**
     * 查询控件
     */
    @BindView(R.id.comm_tab)
    TabLayout mTab;
    @BindView(R.id.comm_pager)
    ViewPager mPager;
    @BindView(R.id.table)
    RelativeLayout table;
    @BindView(R.id.back_img)
    ImageView back_img;
    private ArrayList<String> strings;
    private ArrayList<Fragment> fragments;

    @Override
    protected void initView() {
        //设置状态栏颜色
        if (!getLogUser(this).getThemeColors().equals("")) {
            StatusBarUtil.setStatusBarMode(this, true, Color.parseColor(getLogUser(this).getThemeColors()));
        } else {
            StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        }
        table.setBackgroundColor(Color.parseColor(getLogUser(this).getThemeColors()));
        /**
         * 页面名称
         */
        strings = new ArrayList<>();
        /**
         * 添加展示的页面
         */
        fragments = new ArrayList<>();

    }
    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_good_comm_centre;
    }
    @Override
    protected void initEventData() {
        /**
         * 添加标题
         */
        strings.add("待评价");
        strings.add("已评价");
        /**
         * 添加fragment页面
         */
        //待评价
        fragments.add(new RemainCommit_Fragment());
        //已评价
        fragments.add(new CompleteCommit_Fragment());
        //将tablayout和viewpager联合
        MyAccountAdapter myAccountAdapter = new MyAccountAdapter(getSupportFragmentManager(), strings, fragments);
        mPager.setAdapter(myAccountAdapter);
        mTab.setupWithViewPager(mPager);
        changeTextColor(mTab);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 更换下标线颜色
     *
     * @param tabLayout
     */
    private void changeTextColor(TabLayout tabLayout) {
        try {
            //拿到tabLayout的mTabStrip属性
            Field field = TabLayout.class.getDeclaredField("mTabStrip");
            field.setAccessible(true);
            //拿mTabStrip属性里面的值
            Object mTabStrip = field.get(tabLayout);
            //通过mTabStrip对象来获取class类，不能用field来获取class类，参数不能写Integer.class
            Method method = mTabStrip.getClass().getDeclaredMethod("setSelectedIndicatorColor", int.class);
            method.setAccessible(true);
            method.invoke(mTabStrip, Color.parseColor(getLogUser(GoodCommCentreActivity.this).getThemeColors()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void createPresenter() {

    }
}
