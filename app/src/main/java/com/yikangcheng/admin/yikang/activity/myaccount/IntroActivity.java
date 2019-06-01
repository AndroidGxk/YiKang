package com.yikangcheng.admin.yikang.activity.myaccount;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.jessyan.autosize.internal.CustomAdapt;

public class IntroActivity extends BaseActivtiy implements CustomAdapt {
    private ImageView back_img;
    private RelativeLayout mToolbarActivityIntro;
    private EditText mEtIntroActivityIntro;
    private TextView baocun_text;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        back_img = (ImageView) findViewById(R.id.back_img);
        mToolbarActivityIntro = (RelativeLayout) findViewById(R.id.toolbar_activity_intro);
        mEtIntroActivityIntro = (EditText) findViewById(R.id.et_intro_activity_intro);
        baocun_text = (TextView) findViewById(R.id.baocun_text);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void initEventData() {
        /**
         * 保存按钮
         */
        baocun_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String intro = mEtIntroActivityIntro.getText().toString();
                SharedPreferences sp = getSharedPreferences("intro", MODE_PRIVATE);
                sp.edit().putString("intro", intro).commit();
                finish();
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_intro;
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
        return 720;
    }
}
