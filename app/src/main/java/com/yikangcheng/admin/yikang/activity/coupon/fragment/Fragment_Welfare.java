package com.yikangcheng.admin.yikang.activity.coupon.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.MyAccountAdapter;
import com.yikangcheng.admin.yikang.activity.fragment.coupon.CommFragment;
import com.yikangcheng.admin.yikang.activity.fragment.coupon.EffecFragment;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * 作者：古祥坤 on 2019/8/28 16:03
 * 邮箱：1724959985@qq.com
 */
public class Fragment_Welfare extends BaseFragment {
    private TabLayout coupon_tab;
    private ViewPager viewpage;
    private ImageView back_img;
    private RelativeLayout table;
    private int height;
    private int width;
    @BindView(R.id.title)
    TextView title;

    @Override
    protected void initView(View view) {
        //设置状态栏颜色
        if (!getLogUser(BaseApp.getApp()).getThemeColors().equals("")) {
            StatusBarUtil.setStatusBarMode(getActivity(), true, Color.parseColor(getLogUser(BaseApp.getApp()).getThemeColors()));
        } else {
            StatusBarUtil.setStatusBarMode(getActivity(), true, R.color.colorToolbar);
        }
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        coupon_tab = (TabLayout) view.findViewById(R.id.coupon_tab);
        table = (RelativeLayout) view.findViewById(R.id.table);
        table.setBackgroundColor(Color.parseColor(getLogUser(BaseApp.getApp()).getThemeColors()));
        //标题颜色
//        title.setTextColor(Color.parseColor());
        viewpage = (ViewPager) view.findViewById(R.id.viewpage);
        back_img = (ImageView) view.findViewById(R.id.back_img);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("可使用");
        strings.add("已失效");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new CommFragment());
        fragments.add(new EffecFragment());
        MyAccountAdapter myAccountAdapter = new MyAccountAdapter(getActivity().getSupportFragmentManager(), strings, fragments);
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
            method.invoke(mTabStrip, Color.parseColor(getLogUser(BaseApp.getApp()).getThemeColors()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initData() {
        /**
         * 退出
         */
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_welfare;
    }
}
