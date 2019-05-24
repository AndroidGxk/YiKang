package com.yikangcheng.admin.yikang.activity.myaccount;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

public class NameActivity extends BaseActivtiy {


    private ImageView mImgActivityNameFanhui;
    private Toolbar mToolbarActivityName;
    private EditText mEtActivityName;
    private RelativeLayout mRelativeLayoutActivityNameBaocun;

    @Override
    protected void initView() {
//设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        mImgActivityNameFanhui = findViewById(R.id.img_activity_name_fanhui);
        mToolbarActivityName = findViewById(R.id.toolbar_activity_name);
        mEtActivityName = findViewById(R.id.et_activity_name);
        mRelativeLayoutActivityNameBaocun = findViewById(R.id.relativeLayout_activity_name_baocun);


        /**
         * 点击返回图标关闭当前页面
         */
        mImgActivityNameFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /**
         * Toolbar
         */
        mToolbarActivityName.setTitle("");
        setSupportActionBar(mToolbarActivityName);
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_name;
    }

    @Override
    protected void createPresenter() {

    }
}
