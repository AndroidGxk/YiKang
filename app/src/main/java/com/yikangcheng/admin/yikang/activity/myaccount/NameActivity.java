package com.yikangcheng.admin.yikang.activity.myaccount;

import android.content.SharedPreferences;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.jessyan.autosize.internal.CustomAdapt;

public class NameActivity extends BaseActivtiy implements CustomAdapt {


    private ImageView mImgActivityNameFanhui;
    private RelativeLayout mToolbarActivityName;
    private EditText mEtActivityName;
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
        mImgActivityNameFanhui = (ImageView) findViewById(R.id.img_activity_name_fanhui);
        mToolbarActivityName = (RelativeLayout) findViewById(R.id.toolbar_activity_name);
        mEtActivityName = (EditText) findViewById(R.id.et_activity_name);
        ok_btn = (TextView) findViewById(R.id.ok_btn);
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
         * 保存
         */
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences name = getSharedPreferences("name", MODE_PRIVATE);
                SharedPreferences.Editor edit = name.edit();
                edit.putString("name", mEtActivityName.getText().toString());
                edit.commit();
                finish();
            }
        });
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

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return width/2;
    }
}
