package com.yikangcheng.admin.yikang.activity.huanhuo;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.Orderform_ViewPagerAdapter;
import com.yikangcheng.admin.yikang.activity.fragment.orderform.AccomplishFragment;
import com.yikangcheng.admin.yikang.activity.fragment.orderform.AllFragment;
import com.yikangcheng.admin.yikang.activity.fragment.orderform.AwaitFragment;
import com.yikangcheng.admin.yikang.activity.fragment.orderform.CloseFragment;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 申请售后/售后记录/售后状态
 */
public class ApplyafterActivity extends BaseActivtiy {
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tablayout)
    TabLayout tablayout;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
    }

    @Override
    protected void initEventData() {

        final List<String> strings = new ArrayList<>();
        strings.add("售后申请");
        strings.add("处理中");
        strings.add("售后评价");
        strings.add("申请记录");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new AllFragment());
        fragments.add(new AwaitFragment());
        fragments.add(new AccomplishFragment());
        fragments.add(new CloseFragment());
        Orderform_ViewPagerAdapter orderform_viewPagerAdapter = new Orderform_ViewPagerAdapter(getSupportFragmentManager(), strings, fragments);
        viewPager.setAdapter(orderform_viewPagerAdapter);
        tablayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(4);
        //设置下划线长度
        tablayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tablayout, 10, 10);
            }
        });
        /**
         * 点击tab字体变大
         */
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //这是选中状态
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                    //寻找item布局
                    tab.setCustomView(R.layout.custom_tab_layout_text);
                }
                //找控件
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                textView.setTextColor(Color.parseColor("#FFAF00"));
            }

            //未选中状态
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                    //寻找item布局
                    tab.setCustomView(R.layout.custom_tab_layout_text);
                }
                //找控件
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                textView.setTextColor(Color.parseColor("#333333"));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                    //寻找item布局
                    tab.setCustomView(R.layout.custom_tab_layout_text);
                }
                //找控件
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                textView.setTextColor(Color.parseColor("#FFAF00"));
            }
        });
        tablayout.getTabAt(0).select();
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
    protected int getActivtiyLayoutId() {
        return R.layout.activity_applyafter;
    }

    @Override
    protected void createPresenter() {

    }
}
