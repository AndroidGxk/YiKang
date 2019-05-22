package com.yikangcheng.admin.yikang.activity;

import android.view.Display;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.jessyan.autosize.internal.CustomAdapt;

public class ConfirmActivity extends BaseActivtiy implements CustomAdapt {

    private int height;

    @Override
    protected void initView() {
        //设置标题栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorTab);
        Display display = this.getWindowManager().getDefaultDisplay();
        height = display.getHeight();
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_confirm;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return height / 2;
    }
}
