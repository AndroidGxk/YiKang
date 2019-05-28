package com.yikangcheng.admin.yikang.activity;

import android.view.View;
import android.widget.ImageView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

public class FaddishActivity extends BaseActivtiy {

    private ImageView back_img;

    @Override
    protected void initView() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        back_img = findViewById(R.id.back_img);
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
        return R.layout.activity_faddish;
    }

    @Override
    protected void createPresenter() {

    }
}
