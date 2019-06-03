package com.yikangcheng.admin.yikang.activity.orderstatus;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.CloseTheDealAdapter;
import com.yikangcheng.admin.yikang.activity.copy.CopyButtonLibrary;
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

public class CloseTheDealActivity extends BaseActivtiy implements CustomAdapt, ICoreInfe {

    private RelativeLayout mToolbarActivityWaitfrrpayment;
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
    private ImageView mImgActivityCloseQueDing;
    private RecyclerView mRlvActivityCloseTuiJian;
    private TextView mChanchu;
    private TextView mAddress;
    private TextView mProvinceStr;
    private TextView mCityStr;
    private TextView mTownStr;
    private CloseTheDealAdapter mCloseTheDealAdapter;
    private int mPosition;
    private Intent mIntent;
    private int mOrderId;
    private int width;


    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        mIntent = getIntent();
        mOrderId = mIntent.getIntExtra("orderId", 0);
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        int height = display.getHeight();
        //ToolBar
        mToolbarActivityWaitfrrpayment = (RelativeLayout) findViewById(R.id.toolbar_activity_waitfrrpayment);
        //用户名
        mTvActivityCloseName = (TextView) findViewById(R.id.tv_activity_close_name);
        //用户地址
        mAddress = (TextView) findViewById(R.id.tv_activity_close_address);
        mProvinceStr = (TextView) findViewById(R.id.tv_activity_close_provinceStr);
        mCityStr = (TextView) findViewById(R.id.tv_activity_close_cityStr);
        mTownStr = (TextView) findViewById(R.id.tv_activity_close_townStr);
        back_img = (ImageView) findViewById(R.id.back_imgs);
        //查看物流按钮
        mImgActivityCloseChakan = (TextView) findViewById(R.id.img_activity_close_chakan);
        //rlv商品
        mRlvActivityCloseShangPin = (RecyclerView) findViewById(R.id.rlv_activity_close_shangPin);
        //订单编号
        mTvActivityCloseBiaohao = (TextView) findViewById(R.id.tv_activity_close_biaohao);
        //复制
        mImgActivityCloseFizhi = (ImageView) findViewById(R.id.img_activity_close_fizhi);
        //运费
        mTvActivityCloseYunFei = (TextView) findViewById(R.id.tv_activity_close_yunFei);
        //应付金额
        mTvActivityCloseJinE = (TextView) findViewById(R.id.tv_activity_close_jinE);
        //总计金额
        mTvActivityCloseZhongJi = (TextView) findViewById(R.id.tv_activity_close_zhongJi);
        //支付方式
        mTvActivityCloseFangShi = (TextView) findViewById(R.id.tv_activity_close_fangShi);
        //联系客服
        mTvActivityCloseKeFu = (TextView) findViewById(R.id.tv_activity_close_keFu);
        //发票类型
        mTvActivityCloseFaPianLeiXing = (TextView) findViewById(R.id.tv_activity_close_FaPianLeiXing);
        //发票内容
        mTvActivityCloseNeiRong = (TextView) findViewById(R.id.tv_activity_close_NeiRong);
        //删除
        mChanchu = (TextView) findViewById(R.id.tv_activity_closeThe_Deal_shanchu);


        //复制监听点击事件
        mImgActivityCloseFizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //传入需要复制的文字的控件
                CopyButtonLibrary copyButtonLibrary = new CopyButtonLibrary(getApplicationContext(), mTvActivityCloseBiaohao);
                copyButtonLibrary.init();
            }
        });

        /**
         * P层
         */
        CloseTheDeallPresenter closeTheDeallPresenter = new CloseTheDeallPresenter(this);
        closeTheDeallPresenter.request(mOrderId);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRlvActivityCloseShangPin.setLayoutManager(linearLayoutManager);
        ArrayList<CloseTheDealBean.DetailsListBean> mShopSpecDetailedBeans = new ArrayList<>();
        mCloseTheDealAdapter = new CloseTheDealAdapter(mShopSpecDetailedBeans, this);
        mRlvActivityCloseShangPin.setAdapter(mCloseTheDealAdapter);

        mCloseTheDealAdapter.setOnClickListener(new CloseTheDealAdapter.OnClickListener() {
            @Override
            public void OnClickListener(View v, int position) {
                mPosition = position;
            }
        });

        //        //解决滑动不流畅
        mRlvActivityCloseShangPin.setHasFixedSize(true);
        mRlvActivityCloseShangPin.setNestedScrollingEnabled(false);

        int spanCount_tuijian = 1; // 3 columns
        int spacing_tuijian = 5; // 50px
        boolean includeEdge_tuijian = false;
        mRlvActivityCloseShangPin.addItemDecoration(new SpacesItemDecoration(spanCount_tuijian, spacing_tuijian, includeEdge_tuijian));


        //刪除
        initDelete();

    }

    //點擊刪除按鈕刪除當前訂單並推出當前Activity
    private void initDelete() {
        mChanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteOrderIdPresenter deleteOrderIdPresenter = new DeleteOrderIdPresenter(new delete());
                deleteOrderIdPresenter.request(mOrderId);
                mIntent.putExtra("delete", "delete");
                setResult(4, mIntent);
                finish();
            }
        });
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
        return width / 2;
    }

    public class delete implements ICoreInfe {

        public DeleteOrderBean mMEntity;

        @Override
        public void success(Object data) {
            Request request = (Request) data;
            mMEntity = (DeleteOrderBean) request.getEntity();
            //有需要在这打印一下message的返回值现在返回的是空的  让后台看一下  做一个判断
            // Log.e("aaa", "success: "+mMEntity.get );
            mCloseTheDealAdapter.mList.remove(mPosition);
            mCloseTheDealAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
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
        mTvActivityCloseYunFei.setText(entity.getOrder().getFreightPrice() + "");


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
