package com.yikangcheng.admin.yikang.activity.orderstatus;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.FackOfAdapter_A;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.CloseTheDealBean;
import com.yikangcheng.admin.yikang.bean.DeleteOrderBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.CloseTheDeallPresenter;
import com.yikangcheng.admin.yikang.presenter.DeleteOrderIdPresenter;
import com.yikangcheng.admin.yikang.util.SpacesItemDecoration;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.ArrayList;

import me.jessyan.autosize.internal.CustomAdapt;

public class FackOfActivity extends BaseActivtiy implements ICoreInfe, CustomAdapt {
    private ImageView mImgActivityFackOfFanhui;
    private RelativeLayout mToolbarActivityFackOf;
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
    private TextView mTvActivityFackOfShanchu;
    private FackOfAdapter_A mFackOfAdapter_a;
    private int mPosition;
    private int mOrderId;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        int orderId_fack = intent.getIntExtra("orderId_fack", 0);
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        mImgActivityFackOfFanhui = (ImageView) findViewById(R.id.img_activity_fack_of_fanhui);
        mToolbarActivityFackOf = (RelativeLayout) findViewById(R.id.toolbar_activity_fack_of);
        mTvActivityFackOfName = (TextView) findViewById(R.id.tv_activity_fack_of_name);
        mTvActivityFackOfProvinceStr = (TextView) findViewById(R.id.tv_activity_fack_of_provinceStr);
        mTvActivityFackOfCityStr = (TextView) findViewById(R.id.tv_activity_fack_of_cityStr);
        mTvActivityFackOfTownStr = (TextView) findViewById(R.id.tv_activity_fack_of_townStr);
        mTvActivityFackOfAddress = (TextView) findViewById(R.id.tv_activity_fack_of_address);
        mRlvActivityFackOfShangPin = (RecyclerView) findViewById(R.id.rlv_activity_fack_of_shangPin);
        mTvActivityFackOfBiaohao = (TextView) findViewById(R.id.tv_activity_fack_of_biaohao);
        mImgActivityFackOfFzhi = (ImageView) findViewById(R.id.img_activity_fack_of_fzhi);
        mTvActivityFackOfYunFei = (TextView) findViewById(R.id.tv_activity_fack_of_yunFei);
        mTvActivityFackOfJinE = (TextView) findViewById(R.id.tv_activity_fack_of_jinE);
        mTvActivityFackOfZhongJi = (TextView) findViewById(R.id.tv_activity_fack_of_zhongJi);
        mTvActivityFackOfFangShi = (TextView) findViewById(R.id.tv_activity_fack_of_fangShi);
        mTvActivityFackOfKeFu = (TextView) findViewById(R.id.tv_activity_fack_of_keFu);
        mTvActivityFackOfFaPianLeiXing = (TextView) findViewById(R.id.tv_activity_fack_of_FaPianLeiXing);
        mTvActivityFackOfNeiRong = (TextView) findViewById(R.id.tv_activity_fack_of_NeiRong);
        mTvActivityFackOfShanchu = (TextView) findViewById(R.id.tv_activity_fack_of_shanchu);


        /**
         * 点击返回按钮关闭当前页面
         */
        mImgActivityFackOfFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        CloseTheDeallPresenter closeTheDeallPresenter = new CloseTheDeallPresenter(this);
        closeTheDeallPresenter.request(orderId_fack);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRlvActivityFackOfShangPin.setLayoutManager(linearLayoutManager);
        ArrayList<CloseTheDealBean.DetailsListBean> mShopSpecDetailedBeans = new ArrayList<>();
        mFackOfAdapter_a = new FackOfAdapter_A(mShopSpecDetailedBeans, this);
        mRlvActivityFackOfShangPin.setAdapter(mFackOfAdapter_a);

        mFackOfAdapter_a.setOnClickListener(new FackOfAdapter_A.OnClickListener() {
            @Override
            public void OnClickListener(View v, int position) {
                mPosition = position;
            }
        });

        //        //解决滑动不流畅
        mRlvActivityFackOfShangPin.setHasFixedSize(true);
        mRlvActivityFackOfShangPin.setNestedScrollingEnabled(false);


        int spanCount_tuijian = 1; // 3 columns
        int spacing_tuijian = 5; // 50px
        boolean includeEdge_tuijian = false;
        mRlvActivityFackOfShangPin.addItemDecoration(new SpacesItemDecoration(spanCount_tuijian, spacing_tuijian, includeEdge_tuijian));

        //刪除訂單
        initDelete();

    }

    //點擊按鈕刪除訂單
    private void initDelete() {
        mTvActivityFackOfShanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOrderId = mFackOfAdapter_a.mList.get(mPosition).getOrderId();
                DeleteOrderIdPresenter deleteOrderIdPresenter = new DeleteOrderIdPresenter(new delete());
                deleteOrderIdPresenter.request(mOrderId);
                finish();
            }
        });
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

    public class delete implements ICoreInfe {

        public DeleteOrderBean mMEntity;

        @Override
        public void success(Object data) {
            Request request = (Request) data;
            mMEntity = (DeleteOrderBean) request.getEntity();
            //有需要在这打印一下message的返回值现在返回的是空的  让后台看一下  做一个判断
            // Log.e("aaa", "success: "+mMEntity.get );
            mFackOfAdapter_a.mList.remove(mPosition);
            mFackOfAdapter_a.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
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
