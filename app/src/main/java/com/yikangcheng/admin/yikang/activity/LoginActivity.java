package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;

public class LoginActivity extends BaseActivtiy {

    private ImageView reg_image;

    @Override
    protected void initView() {
        reg_image = findViewById(R.id.reg_image);
    }

    @Override
    protected void initEventData() {
        reg_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void createPresenter() {

    }

}
