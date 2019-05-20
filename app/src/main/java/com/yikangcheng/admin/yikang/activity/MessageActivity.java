package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

public class MessageActivity extends BaseActivtiy {

    private LinearLayout wuliu, tongzhi, kefu;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        wuliu = findViewById(R.id.wuliu);
        tongzhi = findViewById(R.id.tongzhi);
        kefu = findViewById(R.id.kefu);
    }

    @Override
    protected void initEventData() {
        /**
         * 跳转到物流页面
         */
        wuliu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MessageActivity.this, WuliuXinxiActivity.class));
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void createPresenter() {

    }
}
