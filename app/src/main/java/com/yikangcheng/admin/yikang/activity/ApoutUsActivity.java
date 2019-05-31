package com.yikangcheng.admin.yikang.activity;

import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.jessyan.autosize.internal.CustomAdapt;

public class ApoutUsActivity extends BaseActivtiy implements CustomAdapt {


    private ImageView back_img;
    private RelativeLayout mToolbarActivityMyaccount;
    private int height;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        Display display = this.getWindowManager().getDefaultDisplay();
        height = display.getHeight();
        back_img = (ImageView) findViewById(R.id.back_img);
        mToolbarActivityMyaccount = (RelativeLayout) findViewById(R.id.activity_myaccount);
    }

    @Override
    protected void initEventData() {
        /**
         * 退出
         */
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_apout_us;
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
