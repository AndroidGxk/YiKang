package com.yikangcheng.admin.yikang.activity.walletactivity;

import android.graphics.Color;
import android.widget.RelativeLayout;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

/**
 * 钱包充值
 */
public class RechargeActivity extends BaseActivtiy {

    private RelativeLayout toolbar_activity_detail;
    @Override
    protected void initView() {
        //设置状态栏颜色
        if (!getLogUser(this).getThemeColors().equals("")) {
            StatusBarUtil.setStatusBarMode(this, true, Color.parseColor(getLogUser(this).getThemeColors()));
        } else {
            StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        }
        toolbar_activity_detail = (RelativeLayout) findViewById(R.id.rela);
        toolbar_activity_detail.setBackgroundColor(Color.parseColor(getLogUser(this).getThemeColors()));
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void createPresenter() {

    }
}
