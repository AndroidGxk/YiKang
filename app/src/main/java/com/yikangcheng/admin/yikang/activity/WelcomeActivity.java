package com.yikangcheng.admin.yikang.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.fragment.introduction.Introduction_four;
import com.yikangcheng.admin.yikang.activity.fragment.introduction.Introduction_one;
import com.yikangcheng.admin.yikang.activity.fragment.introduction.Introduction_three;
import com.yikangcheng.admin.yikang.activity.fragment.introduction.Introduction_two;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends BaseActivtiy {

    private ViewPager viewpage;
    List<Fragment> list_frag = new ArrayList<>();

    @Override
    protected void initView() {
        viewpage = findViewById(R.id.viewpage);
        Introduction_one introduction_one = new Introduction_one();
        Introduction_two introduction_two = new Introduction_two();
        Introduction_three introduction_three = new Introduction_three();
        Introduction_four introduction_four = new Introduction_four();
        list_frag.add(introduction_one);
        list_frag.add(introduction_two);
        list_frag.add(introduction_three);
        list_frag.add(introduction_four);
        viewpage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list_frag.get(i);
            }

            @Override
            public int getCount() {
                return list_frag.size();
            }

        });
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void createPresenter() {

    }
}
