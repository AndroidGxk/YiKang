package com.yikangcheng.admin.yikang.activity.myaccount;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

public class PhoneActivity extends BaseActivtiy {


    private ImageView mImgActivityPhoneFanhui;
    private Toolbar mToolbarActivityPhone;
    private TextView mTvDangqianActivityPhone;
    private ImageView mImgXiaActivityPhone;
    private EditText mEtPhoneActivityPhone;
    private EditText mEtCodeActivityPhone;
    private TextView mTvHuoquActivityPhond;
    private RelativeLayout mRelativeLayoutBaocunActivityPhone;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        //返回
        mImgActivityPhoneFanhui = findViewById(R.id.img_activity_phone_fanhui);
        //toolbar
        mToolbarActivityPhone = findViewById(R.id.toolbar_activity_phone);
        //当前手机号
        mTvDangqianActivityPhone = findViewById(R.id.tv_dangqian_activity_phone);
        //向下箭头的小图标
        mImgXiaActivityPhone = findViewById(R.id.img_xia_activity_phone);
        //要修改的手机号
        mEtPhoneActivityPhone = findViewById(R.id.et_phone_activity_phone);
        //验证码
        mEtCodeActivityPhone = findViewById(R.id.et_code_activity_phone);
        //获取验证码
        mTvHuoquActivityPhond = findViewById(R.id.tv_huoqu_activity_phond);
        //保存
        mRelativeLayoutBaocunActivityPhone = findViewById(R.id.relativeLayout_baocun_activity_phone);
        /**
         * ToolBar
         */
        mToolbarActivityPhone.setTitle("");
        setSupportActionBar(mToolbarActivityPhone);
        /**
         * 点击返回图标关当前页面
         */
        mImgActivityPhoneFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_phone;
    }

    @Override
    protected void createPresenter() {

    }
}
