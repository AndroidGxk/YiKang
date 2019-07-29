package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.os.Bundle;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.ALLBean;

public class BuyAgainActivity extends BaseActivtiy {

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ALLBean.OrderBean orderBean = (ALLBean.OrderBean) bundle.getSerializable("orderBean");
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_buy_again;
    }

    @Override
    protected void createPresenter() {

    }
}
