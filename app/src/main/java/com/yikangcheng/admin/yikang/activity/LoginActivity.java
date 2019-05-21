package com.yikangcheng.admin.yikang.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;

import me.jessyan.autosize.internal.CustomAdapt;

public class LoginActivity extends BaseActivtiy implements CustomAdapt {

    private ImageView reg_image;
    private int height;

    @Override
    protected void initView() {
        reg_image = findViewById(R.id.reg_image);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
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

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return height / 2;
    }
}
