package com.yikangcheng.admin.yikang.activity;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

public class ConfirmActivity extends BaseActivtiy {

    @Override
    protected void initView() {
        //设置标题栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorTab);
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
}
