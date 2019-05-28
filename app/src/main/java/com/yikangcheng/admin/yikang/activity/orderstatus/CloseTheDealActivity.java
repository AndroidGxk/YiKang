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
import com.yikangcheng.admin.yikang.activity.adapter.CloseTheDealAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.CloseTheDealBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.CloseTheDeallPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import me.jessyan.autosize.internal.CustomAdapt;

public class CloseTheDealActivity extends BaseActivtiy implements CustomAdapt, ICoreInfe {

    private ImageView mImgActivityWaitfrrpaymentFanhui;
    private Toolbar mToolbarActivityWaitfrrpayment;
    private TextView mTvActivityCloseName;
    private TextView mImgActivityCloseChakan;
    private RecyclerView mRlvActivityCloseShangPin;
    private TextView mTvActivityCloseBiaohao;
    private ImageView mImgActivityCloseFizhi, back_img;
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
    private TextView mChanchu;
    private TextView mAddress;
    private TextView mProvinceStr;
    private TextView mCityStr;
    private TextView mTownStr;
    private CloseTheDealAdapter mCloseTheDealAdapter;


    @Override
    protected void initView() {

        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        Intent intent = getIntent();
        int orderId = intent.getIntExtra("orderId", 0);

        //返回按钮
        mImgActivityWaitfrrpaymentFanhui = findViewById(R.id.img_activity_waitfrrpayment_fanhui);
        //ToolBar
        mToolbarActivityWaitfrrpayment = findViewById(R.id.toolbar_activity_waitfrrpayment);
        //用户名
        mTvActivityCloseName = findViewById(R.id.tv_activity_close_name);
        //用户地址
        mAddress = findViewById(R.id.tv_activity_close_address);
        mProvinceStr = findViewById(R.id.tv_activity_close_provinceStr);
        mCityStr = findViewById(R.id.tv_activity_close_cityStr);
        mTownStr = findViewById(R.id.tv_activity_close_townStr);
        //查看物流按钮
        mImgActivityCloseChakan = findViewById(R.id.img_activity_close_chakan);
        //rlv商品
        mRlvActivityCloseShangPin = findViewById(R.id.rlv_activity_close_shangPin);
        //订单编号
        mTvActivityCloseBiaohao = findViewById(R.id.tv_activity_close_biaohao);
        //复制
        mImgActivityCloseFizhi = findViewById(R.id.img_activity_close_fizhi);
        //运费
        mTvActivityCloseYunFei = findViewById(R.id.tv_activity_close_yunFei);
        //应付金额
        mTvActivityCloseJinE = findViewById(R.id.tv_activity_close_jinE);
        //总计金额
        mTvActivityCloseZhongJi = findViewById(R.id.tv_activity_close_zhongJi);
        //支付方式
        mTvActivityCloseFangShi = findViewById(R.id.tv_activity_close_fangShi);
        //联系客服
        mTvActivityCloseKeFu = findViewById(R.id.tv_activity_close_keFu);
        //发票类型
        mTvActivityCloseFaPianLeiXing = findViewById(R.id.tv_activity_close_FaPianLeiXing);
        //发票内容
        mTvActivityCloseNeiRong = findViewById(R.id.tv_activity_close_NeiRong);
        //rlv---商品推荐
        mRlvActivityCloseTuiJian = findViewById(R.id.rlv_activity_close_TuiJian);
        //删除
        mChanchu = findViewById(R.id.tv_activity_closeThe_Deal_shanchu);

        mImgActivityWaitfrrpaymentFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mToolbarActivityWaitfrrpayment.setTitle("");
        setSupportActionBar(mToolbarActivityWaitfrrpayment);


        /**
         * P层
         */
        CloseTheDeallPresenter closeTheDeallPresenter = new CloseTheDeallPresenter(this);
        closeTheDeallPresenter.request(orderId);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRlvActivityCloseShangPin.setLayoutManager(linearLayoutManager);
        ArrayList<CloseTheDealBean.DetailsListBean> mShopSpecDetailedBeans = new ArrayList<>();
        mCloseTheDealAdapter = new CloseTheDealAdapter(mShopSpecDetailedBeans, this);
        mRlvActivityCloseShangPin.setAdapter(mCloseTheDealAdapter);

        //        //解决滑动不流畅
        mRlvActivityCloseShangPin.setHasFixedSize(true);
        mRlvActivityCloseShangPin.setNestedScrollingEnabled(false);

        mRlvActivityCloseTuiJian.setHasFixedSize(true);
        mRlvActivityCloseTuiJian.setNestedScrollingEnabled(false);
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

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        CloseTheDealBean entity = (CloseTheDealBean) request.getEntity();

        mCloseTheDealAdapter.addAll(entity.getDetailsList());

        //用户名
        mTvActivityCloseName.setText(entity.getUserAddress().getReceiver());
        //地址
        mAddress.setText(entity.getUserAddress().getAddress());
        mCityStr.setText(entity.getUserAddress().getCityStr());
        mTownStr.setText(entity.getUserAddress().getTownStr());
        mProvinceStr.setText(entity.getUserAddress().getProvinceStr());
        //订单编号
        mTvActivityCloseBiaohao.setText(entity.getOrder().getOrderNo());
        //运费
        mTvActivityCloseYunFei.setText(entity.getOrder().getFreightPrice()+"");


        //金额
        mTvActivityCloseJinE.setText(entity.getOrder().getRealPrice() + "");
        //总额

        mTvActivityCloseZhongJi.setText(entity.getOrder().getSumPrice() + "");
        //支付方式
        if (entity.getOrder().getOrderState().equals("SUCCESS")) {
            mTvActivityCloseFangShi.setText("支付成功");
        }

        //发票类型
        int invoiceType = entity.getOrderBook().getInvoiceType();
        Log.e("tag", "success: " + invoiceType);
        if (entity.getOrderBook().getInvoiceType() == 1) {
            mTvActivityCloseFaPianLeiXing.setText("电子发票");
        } else if (entity.getOrderBook().getInvoiceType() == 2) {
            mTvActivityCloseFaPianLeiXing.setText("纸质发票");
        } else {
            mTvActivityCloseFaPianLeiXing.setText("无发票");
        }

        //发票内容
        if (entity.getOrderBook().getInvoiceContent() == 1) {
            mTvActivityCloseNeiRong.setText("商品明细");
        } else {
            mTvActivityCloseNeiRong.setText("商品类别");
        }


    }

    @Override
    public void fail(ApiException e) {

    }
}
