package com.yikangcheng.admin.yikang.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.Orderform_ViewPagerAdapter;
import com.yikangcheng.admin.yikang.activity.fragment.orderform.AccomplishFragment;
import com.yikangcheng.admin.yikang.activity.fragment.orderform.AllFragment;
import com.yikangcheng.admin.yikang.activity.fragment.orderform.AwaitFragment;
import com.yikangcheng.admin.yikang.activity.fragment.orderform.CloseFragment;
import com.yikangcheng.admin.yikang.activity.fragment.orderform.CompletedlishFragment;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class OrderFormActivity extends BaseActivtiy {

    private ImageView back_img;
    private RelativeLayout mToolbarActivityOrderfrom;
    private TabLayout mTabActivityOrderform;
    private ViewPager mViewPagerOrderform;
    private int width;
    private String tab;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        Intent intent = getIntent();
        tab = intent.getStringExtra("tab");
        back_img = (ImageView) findViewById(R.id.back_img);
        mToolbarActivityOrderfrom = (RelativeLayout) findViewById(R.id.toolbar_activity_orderfrom);
        mTabActivityOrderform = (TabLayout) findViewById(R.id.tab_activity_orderform);
        mViewPagerOrderform = (ViewPager) findViewById(R.id.viewPager_orderform);

        /**
         * 点击返回关闭当前页面
         */
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final List<String> strings = new ArrayList<>();
        strings.add("全部");
        strings.add("待付款");
        strings.add("待收货");
        strings.add("已完成");
        strings.add("已取消");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new AllFragment());
        fragments.add(new AwaitFragment());
        fragments.add(new AccomplishFragment());
        fragments.add(new CompletedlishFragment());
        fragments.add(new CloseFragment());
        Orderform_ViewPagerAdapter orderform_viewPagerAdapter = new Orderform_ViewPagerAdapter(getSupportFragmentManager(), strings, fragments);
        mViewPagerOrderform.setAdapter(orderform_viewPagerAdapter);
        mTabActivityOrderform.setupWithViewPager(mViewPagerOrderform);
        mViewPagerOrderform.setOffscreenPageLimit(1);
        //设置下划线长度
        mTabActivityOrderform.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(mTabActivityOrderform, 10, 10);
            }
        });
        if (tab != null) {
            if (tab.equals("yiquxiao")) {
                mTabActivityOrderform.getTabAt(4).select();
            } else if (tab.equals("daifukuan")) {
                mTabActivityOrderform.getTabAt(1).select();
            } else if (tab.equals("daishouhuo")) {
                mTabActivityOrderform.getTabAt(2).select();
            }else if(tab.equals("yiwancheng")){
                mTabActivityOrderform.getTabAt(3).select();
            }
        }
//        /**
//         * 点击tab字体变大
//         */
//        mTabActivityOrderform.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            //这是选中状态
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                View view = tab.getCustomView();
//                if (null == view) {
//                    //寻找item布局
//                    tab.setCustomView(R.layout.custom_tab_layout_text);
//                }
//                //找控件
//                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
//                //设置文字加粗
//                textView.setTextColor(mTabActivityOrderform.getTabTextColors());
//                //设置文字字体大小
//                textView.setTextSize(15);
//                //这是设置字体
////                textView.setTypeface(Typeface.DEFAULT_BOLD);
//            }
//
//            //未选中状态
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                View view = tab.getCustomView();
//                if (null == view) {
//                    //寻找item布局
//                    tab.setCustomView(R.layout.custom_tab_layout_text);
//                }
//                //找控件
//                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
//                //设置当未选中状态字体大小
//                textView.setTextSize(12);
//                //设置字体
////                textView.setTypeface(Typeface.DEFAULT);
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });


    }


    /**
     * 设置指示器长度
     */
    /**
     * 通过反射机制 修改TableLayout 的下划线长度
     */

    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        //通过反射获取到
        Class tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //设置模式
        tabStrip.setAccessible(true);
        //获得tabview
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //设置tabView的padding为0，并且设置了margin
        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());
        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
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
