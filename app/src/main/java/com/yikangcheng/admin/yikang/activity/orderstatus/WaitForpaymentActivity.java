package com.yikangcheng.admin.yikang.activity.orderstatus;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.WaitForPaymentAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.CloseTheDealBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.CloseTheDeallPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.ArrayList;

import me.jessyan.autosize.internal.CustomAdapt;

/**
 * 这是订单详情里面的----等待付款
 */
public class WaitForpaymentActivity extends BaseActivtiy implements CustomAdapt, ICoreInfe {


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
    private TextView mShanchu;
    private TextView mTvActivityFackOfProvinceStr;
    private TextView mTvActivityFackOfCityStr;
    private TextView mTvActivityFackOfTownStr;
    private TextView mTvActivityFackOfAddress;
    private WaitForPaymentAdapter mWaitForPaymentAdapter;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        int orderId_wait = intent.getIntExtra("orderId_wait", 0);
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
        mTvActivityFackOfProvinceStr = findViewById(R.id.tv_activity_fack_of_provinceStr);
        mTvActivityFackOfCityStr = findViewById(R.id.tv_activity_fack_of_cityStr);
        mTvActivityFackOfTownStr = findViewById(R.id.tv_activity_fack_of_townStr);
        mTvActivityFackOfAddress = findViewById(R.id.tv_activity_fack_of_address);
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
        mShanchu = findViewById(R.id.tv_activity_wait_shanchu);
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


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRlvActivityWaitfrrpaymentShangPin.setLayoutManager(linearLayoutManager);
        ArrayList<CloseTheDealBean.DetailsListBean> mShopSpecDetailedBeans = new ArrayList<>();
        mWaitForPaymentAdapter = new WaitForPaymentAdapter(mShopSpecDetailedBeans, this);
        mRlvActivityWaitfrrpaymentShangPin.setAdapter(mWaitForPaymentAdapter);

        /**
         * P层
         */
        CloseTheDeallPresenter closeTheDeallPresenter = new CloseTheDeallPresenter(this);
        closeTheDeallPresenter.request(orderId_wait);
//        closeTheDeallPresenter.request(orderId_wait_2);
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

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        CloseTheDealBean entity = (CloseTheDealBean) request.getEntity();
        mWaitForPaymentAdapter.addAll(entity.getDetailsList());

        //用户名
        mTvActivityWaitfrrpaymentName.setText(entity.getUserAddress().getReceiver());
        //地址
        mTvActivityFackOfAddress.setText(entity.getUserAddress().getAddress());
        mTvActivityFackOfCityStr.setText(entity.getUserAddress().getCityStr());
        mTvActivityFackOfTownStr.setText(entity.getUserAddress().getTownStr());
        mTvActivityFackOfProvinceStr.setText(entity.getUserAddress().getProvinceStr());
        //订单编号
        mTvActivityWaitfrrpaymentBiaohao.setText(entity.getOrder().getOrderNo());
        //运费
        mTvActivityWaitfrrpaymentYunFei.setText(entity.getOrder().getFreightPrice() + "");


        //金额
        mTvActivityWaitfrrpaymentJinE.setText(entity.getOrder().getRealPrice() + "");
        //总额
        mTvActivityWaitfrrpaymentZhongJi.setText(entity.getOrder().getSumPrice() + "");
        //支付方式
        if (entity.getOrder().getPayType().equals("WEIXIN")) {
            mTvActivityWaitfrrpaymentFangShi.setText("微信");
        } else if (entity.getOrder().getPayType().equals("ALIPAY")) {
            mTvActivityWaitfrrpaymentFangShi.setText("支付宝");
        }

        //发票类型
        int invoiceType = entity.getOrderBook().getInvoiceType();
        Log.e("tag", "success: " + invoiceType);
        if (entity.getOrderBook().getInvoiceType() == 1) {
            mTvActivityWaitfrrpaymentFaPianLeiXing.setText("电子发票");
        } else if (entity.getOrderBook().getInvoiceType() == 2) {
            mTvActivityWaitfrrpaymentFaPianLeiXing.setText("纸质发票");
        } else {
            mTvActivityWaitfrrpaymentFaPianLeiXing.setText("无发票");
        }

        //发票内容
        if (entity.getOrderBook().getInvoiceContent() == 1) {
            mTvActivityWaitfrrpaymentNeiRong.setText("商品明细");
        } else {
            mTvActivityWaitfrrpaymentNeiRong.setText("商品类别");
        }


    }

    @Override
    public void fail(ApiException e) {

    }
}
