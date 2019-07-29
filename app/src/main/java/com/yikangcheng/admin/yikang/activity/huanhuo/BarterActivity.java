package com.yikangcheng.admin.yikang.activity.huanhuo;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;

import me.jessyan.autosize.internal.CustomAdapt;

/**
 * 换货审核页面
 */
public class BarterActivity extends BaseActivtiy  {


    private int width;

    @Override
    protected void initView() {
        width = getWindow().getDecorView().getWidth();
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_barter2;
    }

    @Override
    protected void createPresenter() {

    }

}
