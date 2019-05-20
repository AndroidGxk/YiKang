package com.yikangcheng.admin.yikang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

/**
 * 结算页面
 */
public class CloseActivity extends BaseActivtiy {

    @Override
    protected void initView() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);

    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_close;
    }

    @Override
    protected void createPresenter() {

    }
}
