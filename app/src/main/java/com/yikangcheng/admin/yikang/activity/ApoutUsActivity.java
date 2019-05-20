package com.yikangcheng.admin.yikang.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

public class ApoutUsActivity extends BaseActivtiy {


    private ImageView mImgActivityApoutusFanhui;
    private Toolbar mToolbarActivityMyaccount;

    @Override
    protected void initView() {

        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);

        mImgActivityApoutusFanhui = findViewById(R.id.img_activity_apoutus_fanhui);
        mToolbarActivityMyaccount = findViewById(R.id.toolbar_activity_myaccount);


        mToolbarActivityMyaccount.setTitle("");
        setSupportActionBar(mToolbarActivityMyaccount);

        mImgActivityApoutusFanhui.setOnClickListener(new View.OnClickListener() {
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
        return R.layout.activity_apout_us;
    }

    @Override
    protected void createPresenter() {

    }
}
