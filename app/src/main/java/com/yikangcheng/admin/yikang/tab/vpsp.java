package com.yikangcheng.admin.yikang.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


public class vpsp extends FragmentPagerAdapter {
    private final List<String> datas;

    public vpsp(FragmentManager fm, List<String> datas) {
        super(fm);
        this.datas=datas;
    }


    //返回选项卡的文本 ，，，添加选项卡

    @Override
    public CharSequence getPageTitle(int position) {
        return datas.get(position);
    }
    //动态创建fragment对象并返回

    @Override
    public Fragment getItem(int position) {
//            创建布局
        vcount vcount = new vcount();
        Bundle bundle = new Bundle();
//            放入值
        bundle.putString("name", datas.get(position));
//            放入布局文件中
        vcount.setArguments(bundle);
        return vcount;

    }
    //返回数量

    @Override
    public int getCount() {
        return datas.size();
    }
}
