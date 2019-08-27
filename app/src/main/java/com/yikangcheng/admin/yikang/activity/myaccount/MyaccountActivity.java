package com.yikangcheng.admin.yikang.activity.myaccount;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.MyAccountAdapter;
import com.yikangcheng.admin.yikang.activity.fragment.wodezhanghu.AmendFragment;
import com.yikangcheng.admin.yikang.activity.fragment.wodezhanghu.BasicFragment;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.UserDetailBean;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import butterknife.BindView;
import me.jessyan.autosize.internal.CustomAdapt;

public class MyaccountActivity extends BaseActivtiy   {


    private ImageView mImgActivityMyaccountFanhui;
    private RelativeLayout mToolbarActivityMyaccount;
    private TabLayout mTabActivityMyaccount;
    private ViewPager mViewPagerActivityMyaccount;
    private int width;
    private int height;
    @BindView(R.id.title)
    TextView title;
    @Override
    protected void initView() {
        //设置状态栏颜色
        if (!getLogUser(this).getThemeColors().equals("")) {
            StatusBarUtil.setStatusBarMode(this, true, Color.parseColor(getLogUser(this).getThemeColors()));
        } else {
            StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        }
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        mToolbarActivityMyaccount = (RelativeLayout) findViewById(R.id.toolbar_activity_myaccount);
        mToolbarActivityMyaccount.setBackgroundColor(Color.parseColor(getLogUser(this).getThemeColors()));
        //标题字体颜色
//        title.setTextColor(Color.parseColor());
        mImgActivityMyaccountFanhui = (ImageView) findViewById(R.id.img_activity_myaccount_fanhui);
        mTabActivityMyaccount = (TabLayout) findViewById(R.id.tab_activity_myaccount);
        mViewPagerActivityMyaccount = (ViewPager) findViewById(R.id.ViewPager_activity_myaccount);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("基本信息");
        strings.add("修改密码");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new BasicFragment());
        fragments.add(new AmendFragment());
        MyAccountAdapter myAccountAdapter = new MyAccountAdapter(getSupportFragmentManager(), strings, fragments);
        mViewPagerActivityMyaccount.setAdapter(myAccountAdapter);
        mTabActivityMyaccount.setupWithViewPager(mViewPagerActivityMyaccount);
        changeTextColor(mTabActivityMyaccount);
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
            method.invoke(mTabStrip, Color.parseColor(getLogUser(MyaccountActivity.this).getThemeColors()));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
