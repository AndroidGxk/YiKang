package com.yikangcheng.admin.yikang.activity.myaccount;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.jessyan.autosize.internal.CustomAdapt;

public class NicknameActivity extends BaseActivtiy implements CustomAdapt {


    private ImageView mImgActivityNicknameFanhui;
    private Toolbar mToolbarActivityNickname;
    private EditText mEtActivityNickname;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        mImgActivityNicknameFanhui = findViewById(R.id.img_activity_nickname_fanhui);
        mToolbarActivityNickname = findViewById(R.id.toolbar_activity_nickname);
        mEtActivityNickname = findViewById(R.id.et_activity_nickname);

        /**
         * 点击返回图标关闭当前页面
         */
        mImgActivityNicknameFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /**
         * Toolbar
         */
        mToolbarActivityNickname.setTitle("");
        setSupportActionBar(mToolbarActivityNickname);
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_nickname;
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