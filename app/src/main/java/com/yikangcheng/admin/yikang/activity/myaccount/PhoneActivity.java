package com.yikangcheng.admin.yikang.activity.myaccount;


import android.content.SharedPreferences;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.jessyan.autosize.internal.CustomAdapt;

public class PhoneActivity extends BaseActivtiy implements CustomAdapt {

    private ImageView back_img;
    private TextView ok_btn;
    private int height;
    private int width;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        back_img = (ImageView) findViewById(R.id.back_img);
        back_img = (ImageView) findViewById(R.id.back_img);
        ok_btn = (TextView) findViewById(R.id.ok_btn);
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
        /**
         * 保存
         */
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences name = getSharedPreferences("name", MODE_PRIVATE);
                SharedPreferences.Editor edit = name.edit();
                edit.commit();
                finish();
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_phone;
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
        return width/2;
    }
}
