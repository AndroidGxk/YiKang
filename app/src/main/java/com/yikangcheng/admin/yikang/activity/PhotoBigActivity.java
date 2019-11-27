package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.PhotoPagerAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;

import java.util.ArrayList;
//
import butterknife.BindView;

public class PhotoBigActivity extends BaseActivtiy {
    @BindView(R.id.vp_photo_pager)
    ViewPager mViewPager;

    @Override
    protected void initView() {

    }

    @Override
    protected void initEventData() {
        Intent intent = getIntent();
        ArrayList<String> data = intent.getStringArrayListExtra("data");
        if(data!=null){
            mViewPager.setAdapter(new PhotoPagerAdapter(this, data));
        }
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_photo_big;
    }

    @Override
    protected void createPresenter() {

    }
}
