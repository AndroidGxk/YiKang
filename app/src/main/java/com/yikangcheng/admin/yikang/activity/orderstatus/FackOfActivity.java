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
import com.yikangcheng.admin.yikang.activity.adapter.FackOfAdapter_A;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.CloseTheDealBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.CloseTheDeallPresenter;
import com.yikangcheng.admin.yikang.util.SpacesItemDecoration;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.ArrayList;

import me.jessyan.autosize.internal.CustomAdapt;

public class FackOfActivity extends BaseActivtiy implements ICoreInfe, CustomAdapt {
    private ImageView mImgActivityFackOfFanhui;
    private Toolbar mToolbarActivityFackOf;
    private TextView mTvActivityFackOfName;
    private TextView mTvActivityFackOfProvinceStr;
    private TextView mTvActivityFackOfCityStr;
    private TextView mTvActivityFackOfTownStr;
    private TextView mTvActivityFackOfAddress;
    private RecyclerView mRlvActivityFackOfShangPin;
    private TextView mTvActivityFackOfBiaohao;
    private ImageView mImgActivityFackOfFzhi;
    private TextView mTvActivityFackOfYunFei;
    private TextView mTvActivityFackOfJinE;
    private TextView mTvActivityFackOfZhongJi;
    private TextView mTvActivityFackOfFangShi;
    private TextView mTvActivityFackOfKeFu;
    private TextView mTvActivityFackOfFaPianLeiXing;
    private TextView mTvActivityFackOfNeiRong;
    private RecyclerView mRlvActivityFackOfTuiJian;
    private TextView mTvActivityFackOfShanchu;
    private FackOfAdapter_A mFackOfAdapter_a;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        int orderId_fack = intent.getIntExtra("orderId_fack", 0);
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        mImgActivityFackOfFanhui = findViewById(R.id.img_activity_fack_of_fanhui);
        mToolbarActivityFackOf = findViewById(R.id.toolbar_activity_fack_of);
        mTvActivityFackOfName = findViewById(R.id.tv_activity_fack_of_name);
        mTvActivityFackOfProvinceStr = findViewById(R.id.tv_activity_fack_of_provinceStr);
        mTvActivityFackOfCityStr = findViewById(R.id.tv_activity_fack_of_cityStr);
        mTvActivityFackOfTownStr = findViewById(R.id.tv_activity_fack_of_townStr);
        mTvActivityFackOfAddress = findViewById(R.id.tv_activity_fack_of_address);
        mRlvActivityFackOfShangPin = findViewById(R.id.rlv_activity_fack_of_shangPin);
        mTvActivityFackOfBiaohao = findViewById(R.id.tv_activity_fack_of_biaohao);
        mImgActivityFackOfFzhi = findViewById(R.id.img_activity_fack_of_fzhi);
        mTvActivityFackOfYunFei = findViewById(R.id.tv_activity_fack_of_yunFei);
        mTvActivityFackOfJinE = findViewById(R.id.tv_activity_fack_of_jinE);
        mTvActivityFackOfZhongJi = findViewById(R.id.tv_activity_fack_of_zhongJi);
        mTvActivityFackOfFangShi = findViewById(R.id.tv_activity_fack_of_fangShi);
        mTvActivityFackOfKeFu = findViewById(R.id.tv_activity_fack_of_keFu);
        mTvActivityFackOfFaPianLeiXing = findViewById(R.id.tv_activity_fack_of_FaPianLeiXing);
        mTvActivityFackOfNeiRong = findViewById(R.id.tv_activity_fack_of_NeiRong);
        mRlvActivityFackOfTuiJian = findViewById(R.id.rlv_activity_fack_of_TuiJian);
        mTvActivityFackOfShanchu = findViewById(R.id.tv_activity_fack_of_shanchu);

        mImgActivityFackOfFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mToolbarActivityFackOf.setTitle("");
        setSupportActionBar(mToolbarActivityFackOf);

        CloseTheDeallPresenter closeTheDeallPresenter = new CloseTheDeallPresenter(this);
        closeTheDeallPresenter.request(orderId_fack);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRlvActivityFackOfShangPin.setLayoutManager(linearLayoutManager);
        ArrayList<CloseTheDealBean.DetailsListBean> mShopSpecDetailedBeans = new ArrayList<>();
        mFackOfAdapter_a = new FackOfAdapter_A(mShopSpecDetailedBeans, this);
        mRlvActivityFackOfShangPin.setAdapter(mFackOfAdapter_a);

        int spanCount_tuijian = 1; // 3 columns
        int spacing_tuijian = 5; // 50px
        boolean includeEdge_tuijian = false;
        mRlvActivityFackOfShangPin.addItemDecoration(new SpacesItemDecoration(spanCount_tuijian, spacing_tuijian, includeEdge_tuijian));


    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_fack_of;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        CloseTheDealBean entity = (CloseTheDealBean) request.getEntity();
        mFackOfAdapter_a.addAll(entity.getDetailsList());

        //用户名
        mTvActivityFackOfName.setText(entity.getUserAddress().getReceiver());
        //地址
        mTvActivityFackOfAddress.setText(entity.getUserAddress().getAddress());
        mTvActivityFackOfCityStr.setText(entity.getUserAddress().getCityStr());
        mTvActivityFackOfTownStr.setText(entity.getUserAddress().getTownStr());
        mTvActivityFackOfProvinceStr.setText(entity.getUserAddress().getProvinceStr());
        //订单编号
        mTvActivityFackOfBiaohao.setText(entity.getOrder().getOrderNo());
        //运费
        mTvActivityFackOfYunFei.setText(entity.getOrder().getFreightPrice() + "");

        //金额
        mTvActivityFackOfJinE.setText(entity.getOrder().getRealPrice() + "");
        //总额
        mTvActivityFackOfZhongJi.setText(entity.getOrder().getSumPrice() + "");
        //支付方式
        if (entity.getOrder().getOrderState().equals("CANCEL")) {
            mTvActivityFackOfFangShi.setText("交易关闭");
        }

        //发票类型
        int invoiceType = entity.getOrderBook().getInvoiceType();
        Log.e("tag", "success: " + invoiceType);
        if (entity.getOrderBook().getInvoiceType() == 1) {
            mTvActivityFackOfFaPianLeiXing.setText("电子发票");
        } else if (entity.getOrderBook().getInvoiceType() == 2) {
            mTvActivityFackOfFaPianLeiXing.setText("纸质发票");
        } else {
            mTvActivityFackOfFaPianLeiXing.setText("无发票");
        }

        //发票内容
        if (entity.getOrderBook().getInvoiceContent() == 1) {
            mTvActivityFackOfNeiRong.setText("商品明细");
        } else {
            mTvActivityFackOfFaPianLeiXing.setText("商品类别");
        }
    }

    @Override
    public void fail(ApiException e) {

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
