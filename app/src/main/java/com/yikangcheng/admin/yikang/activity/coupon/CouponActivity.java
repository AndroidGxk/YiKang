package com.yikangcheng.admin.yikang.activity.coupon;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.OrderFormActivity;
import com.yikangcheng.admin.yikang.activity.adapter.MyAccountAdapter;
import com.yikangcheng.admin.yikang.activity.fragment.coupon.CommFragment;
import com.yikangcheng.admin.yikang.activity.fragment.coupon.EffecFragment;
import com.yikangcheng.admin.yikang.activity.fragment.wodezhanghu.AmendFragment;
import com.yikangcheng.admin.yikang.activity.fragment.wodezhanghu.BasicFragment;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import butterknife.BindView;
import me.jessyan.autosize.internal.CustomAdapt;

public class CouponActivity extends BaseActivtiy {
    private TabLayout coupon_tab;
    private ViewPager viewpage;
    private ImageView back_img;
    private RelativeLayout table;
    private int height;
    private int width;
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
        coupon_tab = (TabLayout) findViewById(R.id.coupon_tab);
        table = (RelativeLayout) findViewById(R.id.table);
        table.setBackgroundColor(Color.parseColor(getLogUser(this).getThemeColors()));
        //标题颜色
//        title.setTextColor(Color.parseColor());
        viewpage = (ViewPager) findViewById(R.id.viewpage);
        back_img = (ImageView) findViewById(R.id.back_img);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("可使用");
        strings.add("已失效");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new CommFragment());
        fragments.add(new EffecFragment());
        MyAccountAdapter myAccountAdapter = new MyAccountAdapter(getSupportFragmentManager(), strings, fragments);
        viewpage.setAdapter(myAccountAdapter);
        coupon_tab.setupWithViewPager(viewpage);
        changeTextColor(coupon_tab);
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
            method.invoke(mTabStrip, Color.parseColor(getLogUser(CouponActivity.this).getThemeColors()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initEventData() {
        /**
         * 退出
         */
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_coupon;
    }

    @Override
    protected void createPresenter() {

    }

}
