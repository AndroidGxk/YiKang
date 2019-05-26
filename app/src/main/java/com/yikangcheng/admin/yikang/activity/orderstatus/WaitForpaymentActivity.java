package com.yikangcheng.admin.yikang.activity.orderstatus;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.jessyan.autosize.internal.CustomAdapt;

/**
 * 这是订单详情里面的----等待付款
 */
public class WaitForpaymentActivity extends BaseActivtiy implements CustomAdapt {


    private ImageView mImgActivityWaitfrrpaymentFanhui;
    private Toolbar mToolbarActivityWaitfrrpayment;
    private TextView mTvActivityWaitfrrpaymentDaojishi;
    private TextView mTvActivityWaitfrrpaymentName;
    private TextView mTvActivityWaitfrrpaymentDizi;
    private RecyclerView mRlvActivityWaitfrrpaymentShangPin;
    private TextView mTvActivityWaitfrrpaymentBiaohao;
    private ImageView mImgActivityWaitfrrpaymentFizhi;
    private TextView mTvActivityWaitfrrpaymentYunFei;
    private TextView mTvActivityWaitfrrpaymentJinE;
    private TextView mTvActivityWaitfrrpaymentZhongJi;
    private TextView mTvActivityWaitfrrpaymentFangShi;
    private TextView mTvActivityWaitfrrpaymentKeFu;
    private TextView mTvActivityWaitfrrpaymentFaPianLeiXing;
    private TextView mTvActivityWaitfrrpaymentNeiRong;
    private RecyclerView mRlvActivityWaitfrrpaymentTuiJian;
    private ImageView mImgActivityWaitfrrpaymentShanchu;
    private ImageView mImgActivityWaitfrrpaymentQueDing;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        //ToolBar返回按钮
        mImgActivityWaitfrrpaymentFanhui = findViewById(R.id.img_activity_waitfrrpayment_fanhui);
        //ToolBar
        mToolbarActivityWaitfrrpayment = findViewById(R.id.toolbar_activity_waitfrrpayment);
        //倒计时
        mTvActivityWaitfrrpaymentDaojishi = findViewById(R.id.tv_activity_waitfrrpayment_daojishi);
        //用户姓名
        mTvActivityWaitfrrpaymentName = findViewById(R.id.tv_activity_waitfrrpayment_name);
        //用户地址
        mTvActivityWaitfrrpaymentDizi = findViewById(R.id.tv_activity_waitfrrpayment_dizi);
        //rlv-商品
        mRlvActivityWaitfrrpaymentShangPin = findViewById(R.id.rlv_activity_waitfrrpayment_shangPin);
        //商品编号
        mTvActivityWaitfrrpaymentBiaohao = findViewById(R.id.tv_activity_waitfrrpayment_biaohao);
        //复制商品编号
        mImgActivityWaitfrrpaymentFizhi = findViewById(R.id.img_activity_waitfrrpayment_fizhi);
        //商品运费
        mTvActivityWaitfrrpaymentYunFei = findViewById(R.id.tv_activity_waitfrrpayment_yunFei);
        //应付金额
        mTvActivityWaitfrrpaymentJinE = findViewById(R.id.tv_activity_waitfrrpayment_jinE);
        //总计金额
        mTvActivityWaitfrrpaymentZhongJi = findViewById(R.id.tv_activity_waitfrrpayment_zhongJi);
        //支付方式
        mTvActivityWaitfrrpaymentFangShi = findViewById(R.id.tv_activity_waitfrrpayment_fangShi);
        //联系客服
        mTvActivityWaitfrrpaymentKeFu = findViewById(R.id.tv_activity_waitfrrpayment_keFu);
        //发票类型
        mTvActivityWaitfrrpaymentFaPianLeiXing = findViewById(R.id.tv_activity_waitfrrpayment_FaPianLeiXing);
        //发票内容
        mTvActivityWaitfrrpaymentNeiRong = findViewById(R.id.tv_activity_waitfrrpayment_NeiRong);
        //rlv---为你推荐
        mRlvActivityWaitfrrpaymentTuiJian = findViewById(R.id.rlv_activity_waitfrrpayment_TuiJian);
        //删除订单
        mImgActivityWaitfrrpaymentShanchu = findViewById(R.id.img_activity_waitfrrpayment_shanchu);
        //去支付
        mImgActivityWaitfrrpaymentQueDing = findViewById(R.id.img_activity_waitfrrpayment_queDing);

        /**
         * 点击返回按钮关闭当前页面
         */
        mImgActivityWaitfrrpaymentFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /**
         * ToolBar
         */
        mToolbarActivityWaitfrrpayment.setTitle("");
        setSupportActionBar(mToolbarActivityWaitfrrpayment);
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_wait_forpayment;
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
