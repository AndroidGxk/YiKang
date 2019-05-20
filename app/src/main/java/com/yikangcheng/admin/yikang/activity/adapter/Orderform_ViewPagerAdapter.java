package com.yikangcheng.admin.yikang.activity.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lenovo on 2019/5/20.
 * WF
 */
public class Orderform_ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mList;
    private final List<String> mTitle;

    public Orderform_ViewPagerAdapter(FragmentManager fm, List<String> strings, List<Fragment> fragments) {
        super(fm);
        this.mTitle = strings;
        this.mList = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }
}
