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
import android.widget.Toast;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.WaitForPaymentAdapter;
import com.yikangcheng.admin.yikang.activity.copy.CopyButtonLibrary;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.CloseTheDealBean;
import com.yikangcheng.admin.yikang.bean.DeleteOrderBean;
import com.yikangcheng.admin.yikang.bean.NewOrderBean;
import com.yikangcheng.admin.yikang.bean.NewOrderBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.CloseTheDeallPresenter;
import com.yikangcheng.admin.yikang.presenter.NewOrderPresenter;
import com.yikangcheng.admin.yikang.presenter.DeleteOrderIdPresenter;
import com.yikangcheng.admin.yikang.presenter.NewOrderPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.ArrayList;

import me.jessyan.autosize.internal.CustomAdapt;

/**
 * 这是订单详情里面的----等待付款
 */
public class WaitForpaymentActivity extends BaseActivtiy implements CustomAdapt, ICoreInfe {


    private ImageView mImgActivityWaitfrrpaymentFanhui;
    private RelativeLayout mToolbarActivityWaitfrrpayment;
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
    private TextView go_pay;
    private TextView mShanchu;
    private TextView mTvActivityFackOfProvinceStr;
    private TextView mTvActivityFackOfCityStr;
    private TextView mTvActivityFackOfTownStr;
    private TextView mTvActivityFackOfAddress;
    private WaitForPaymentAdapter mWaitForPaymentAdapter;
    private NewOrderPresenter newOrderPresenter;
    private int orderId;
    private String orderType;
    private int mPosition;
    private Intent mIntent;
    private int mOrderId_wait;

    @Override
    protected void initView() {
        mIntent = getIntent();
        mOrderId_wait = mIntent.getIntExtra("orderId_wait", 0);
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        //ToolBar返回按钮
        mImgActivityWaitfrrpaymentFanhui = (ImageView) findViewById(R.id.img_activity_waitfrrpayment_fanhui);
        //ToolBar
        mToolbarActivityWaitfrrpayment = (RelativeLayout) findViewById(R.id.toolbar_activity_waitfrrpayment);
        //倒计时
        mTvActivityWaitfrrpaymentDaojishi = (TextView) findViewById(R.id.tv_activity_waitfrrpayment_daojishi);
        //用户姓名
        mTvActivityWaitfrrpaymentName = (TextView) findViewById(R.id.tv_activity_waitfrrpayment_name);
        //用户地址
        mTvActivityFackOfProvinceStr = (TextView) findViewById(R.id.tv_activity_fack_of_provinceStr);
        mTvActivityFackOfCityStr = (TextView) findViewById(R.id.tv_activity_fack_of_cityStr);
        mTvActivityFackOfTownStr = (TextView) findViewById(R.id.tv_activity_fack_of_townStr);
        mTvActivityFackOfAddress = (TextView) findViewById(R.id.tv_activity_fack_of_address);
        //rlv-商品
        mRlvActivityWaitfrrpaymentShangPin = (RecyclerView) findViewById(R.id.rlv_activity_waitfrrpayment_shangPin);
        //商品编号
        mTvActivityWaitfrrpaymentBiaohao = (TextView) findViewById(R.id.tv_activity_waitfrrpayment_biaohao);
        //复制商品编号
        mImgActivityWaitfrrpaymentFizhi = (ImageView) findViewById(R.id.img_activity_waitfrrpayment_fizhi);
        //商品运费
        mTvActivityWaitfrrpaymentYunFei = (TextView) findViewById(R.id.tv_activity_waitfrrpayment_yunFei);
        //应付金额
        mTvActivityWaitfrrpaymentJinE = (TextView) findViewById(R.id.tv_activity_waitfrrpayment_jinE);
        //总计金额
        mTvActivityWaitfrrpaymentZhongJi = (TextView) findViewById(R.id.tv_activity_waitfrrpayment_zhongJi);
        //支付方式
        mTvActivityWaitfrrpaymentFangShi = (TextView) findViewById(R.id.tv_activity_waitfrrpayment_fangShi);
        //联系客服
        mTvActivityWaitfrrpaymentKeFu = (TextView) findViewById(R.id.tv_activity_waitfrrpayment_keFu);
        //发票类型
        mTvActivityWaitfrrpaymentFaPianLeiXing = (TextView) findViewById(R.id.tv_activity_waitfrrpayment_FaPianLeiXing);
        //发票内容
        mTvActivityWaitfrrpaymentNeiRong = (TextView) findViewById(R.id.tv_activity_waitfrrpayment_NeiRong);
        //删除订单
        mShanchu = (TextView) findViewById(R.id.tv_activity_wait_shanchu);
        //去支付
        go_pay = (TextView) findViewById(R.id.go_pay);


        //复制监听点击事件
        mImgActivityWaitfrrpaymentFizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //传入需要复制的文字的控件
                CopyButtonLibrary copyButtonLibrary = new CopyButtonLibrary(getApplicationContext(), mTvActivityWaitfrrpaymentBiaohao);
                copyButtonLibrary.init();
            }
        });

        //生成新的订单
        newOrderPresenter = new NewOrderPresenter(new NewOrder());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRlvActivityWaitfrrpaymentShangPin.setLayoutManager(linearLayoutManager);
        ArrayList<CloseTheDealBean.DetailsListBean> mShopSpecDetailedBeans = new ArrayList<>();
        mWaitForPaymentAdapter = new WaitForPaymentAdapter(mShopSpecDetailedBeans, this);
        mRlvActivityWaitfrrpaymentShangPin.setAdapter(mWaitForPaymentAdapter);

        mWaitForPaymentAdapter.setOnClickListener(new WaitForPaymentAdapter.OnClickListener() {
            @Override
            public void OnClickListener(View v, int position) {
                mPosition = position;
            }
        });

        /**
         * P层
         */
        CloseTheDeallPresenter closeTheDeallPresenter = new CloseTheDeallPresenter(this);
        closeTheDeallPresenter.request(mOrderId_wait);
        /**
         * 去支付
         */
        go_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newOrderPresenter.request(orderId, orderType);
            }
        });

        //刪除
        initDelete();
    }

    //點擊刪除訂單關閉當前Activity
    private void initDelete() {
        mShanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteOrderIdPresenter deleteOrderIdPresenter = new DeleteOrderIdPresenter(new delete());
                deleteOrderIdPresenter.request(mOrderId_wait);
                mIntent.putExtra("delete", "delete");
                setResult(2, mIntent);
                finish();
            }
        });
    }

    @Override
    protected void initEventData() {
        /**
         * 点击返回按钮关闭当前页面
         */
        mImgActivityWaitfrrpaymentFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

    public class delete implements ICoreInfe {

        public DeleteOrderBean mMEntity;

        @Override
        public void success(Object data) {
            Request request = (Request) data;
            mMEntity = (DeleteOrderBean) request.getEntity();
            //有需要在这打印一下message的返回值现在返回的是空的  让后台看一下  做一个判断
            // Log.e("aaa", "success: "+mMEntity.get );
            mWaitForPaymentAdapter.mList.remove(mPosition);
            mWaitForPaymentAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        CloseTheDealBean entity = (CloseTheDealBean) request.getEntity();
        mWaitForPaymentAdapter.addAll(entity.getDetailsList());
        //重新下单用到的Id
        orderId = entity.getOrder().getOrderId();
        orderType = entity.getOrder().getOrderType();
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

    /**
     * 生成新的订单号
     */
    private class NewOrder implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            NewOrderBean orderBean = (NewOrderBean) request.getEntity();
            Toast.makeText(WaitForpaymentActivity.this, "" + orderBean.getRequestId(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
