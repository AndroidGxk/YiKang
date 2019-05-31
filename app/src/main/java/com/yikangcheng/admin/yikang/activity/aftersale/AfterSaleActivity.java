package com.yikangcheng.admin.yikang.activity.aftersale;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.jessyan.autosize.internal.CustomAdapt;

public class AfterSaleActivity extends BaseActivtiy implements CustomAdapt {


    private ImageView mImgActivityAftersaleFanhui;
    private Toolbar mToolbarActivityMyaccount;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);

        mImgActivityAftersaleFanhui = (ImageView) findViewById(R.id.img_activity_aftersale_fanhui);
        mToolbarActivityMyaccount = (Toolbar) findViewById(R.id.toolbar_activity_myaccount);

        mToolbarActivityMyaccount.setTitle("");
        setSupportActionBar(mToolbarActivityMyaccount);
        /**
         * 点击返回图标关闭放前页面
         */
        mImgActivityAftersaleFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_after_sale;
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
        return 720;
    }
}
