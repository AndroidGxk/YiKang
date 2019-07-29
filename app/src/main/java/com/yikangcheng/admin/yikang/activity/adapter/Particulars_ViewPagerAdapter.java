package com.yikangcheng.admin.yikang.activity.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/5/20.
 * WF
 */
public class Particulars_ViewPagerAdapter extends PagerAdapter {

    List<String> stringList = new ArrayList<>();

    public Particulars_ViewPagerAdapter(List<String> strings) {
        this.stringList = strings;
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
