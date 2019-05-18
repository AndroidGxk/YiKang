package com.yikangcheng.admin.yikang.activity.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by lenovo on 2019/5/17.
 * WF
 */
public class MyAccountAdapter extends FragmentPagerAdapter {

    private final ArrayList<Fragment> mList;
    private final ArrayList<String> mTitle;

    public MyAccountAdapter(FragmentManager fm, ArrayList<String> strings, ArrayList<Fragment> fragments) {
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
        return mTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }
}
