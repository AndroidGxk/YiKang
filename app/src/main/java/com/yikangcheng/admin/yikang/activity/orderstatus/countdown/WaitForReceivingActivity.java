package com.yikangcheng.admin.yikang.activity.orderstatus.countdown;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

public class WaitForReceivingActivity extends BaseActivtiy {


    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);


    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_wait_for_receiving;
    }

    @Override
    protected void createPresenter() {

    }
}
