package com.yikangcheng.admin.yikang.activity.orderstatus;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;

import me.jessyan.autosize.internal.CustomAdapt;

public class CloseTheDealActivity extends BaseActivtiy implements CustomAdapt {

    private ImageView mImgActivityWaitfrrpaymentFanhui;
    private Toolbar mToolbarActivityWaitfrrpayment;
    private TextView mTvActivityCloseName;
    private TextView mTvActivityCloseDizi;
    private ImageView mImgActivityCloseChakan;
    private RecyclerView mRlvActivityCloseShangPin;
    private TextView mTvActivityCloseBiaohao;
    private ImageView mImgActivityCloseFizhi;
    private TextView mTvActivityCloseYunFei;
    private TextView mTvActivityCloseJinE;
    private TextView mTvActivityCloseZhongJi;
    private TextView mTvActivityCloseFangShi;
    private TextView mTvActivityCloseKeFu;
    private TextView mTvActivityCloseFaPianLeiXing;
    private TextView mTvActivityCloseNeiRong;
    private RecyclerView mRlvActivityCloseTuiJian;
    private ImageView mImgActivityCloseShanchu;
    private ImageView mImgActivityCloseQueDing;

    @Override
    protected void initView() {
//        //解决滑动不流畅
        mRlvActivityCloseShangPin.setHasFixedSize(true);
        mRlvActivityCloseShangPin.setNestedScrollingEnabled(false);

        mRlvActivityCloseTuiJian.setHasFixedSize(true);
        mRlvActivityCloseTuiJian.setNestedScrollingEnabled(false);


        mImgActivityWaitfrrpaymentFanhui = findViewById(R.id.img_activity_waitfrrpayment_fanhui);
        mToolbarActivityWaitfrrpayment = findViewById(R.id.toolbar_activity_waitfrrpayment);
        mTvActivityCloseName = findViewById(R.id.tv_activity_close_name);
        mTvActivityCloseDizi = findViewById(R.id.tv_activity_close_dizi);
        mImgActivityCloseChakan = findViewById(R.id.img_activity_close_chakan);
        mRlvActivityCloseShangPin = findViewById(R.id.rlv_activity_close_shangPin);
        mTvActivityCloseBiaohao = findViewById(R.id.tv_activity_close_biaohao);
        mImgActivityCloseFizhi = findViewById(R.id.img_activity_close_fizhi);
        mTvActivityCloseYunFei = findViewById(R.id.tv_activity_close_yunFei);
        mTvActivityCloseJinE = findViewById(R.id.tv_activity_close_jinE);
        mTvActivityCloseZhongJi = findViewById(R.id.tv_activity_close_zhongJi);
        mTvActivityCloseFangShi = findViewById(R.id.tv_activity_close_fangShi);
        mTvActivityCloseKeFu = findViewById(R.id.tv_activity_close_keFu);
        mTvActivityCloseFaPianLeiXing = findViewById(R.id.tv_activity_close_FaPianLeiXing);
        mTvActivityCloseNeiRong = findViewById(R.id.tv_activity_close_NeiRong);
        mRlvActivityCloseTuiJian = findViewById(R.id.rlv_activity_close_TuiJian);
        mImgActivityCloseShanchu = findViewById(R.id.img_activity_close_shanchu);
        mImgActivityCloseQueDing = findViewById(R.id.img_activity_close_queDing);

        mImgActivityWaitfrrpaymentFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mToolbarActivityWaitfrrpayment.setTitle("");
        setSupportActionBar(mToolbarActivityWaitfrrpayment);
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_close_the_deal;
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
