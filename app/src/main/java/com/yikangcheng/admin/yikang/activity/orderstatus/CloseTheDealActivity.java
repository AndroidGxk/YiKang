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

import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.BarterActivity;
import com.yikangcheng.admin.yikang.activity.SeleGoodActivity;
import com.yikangcheng.admin.yikang.activity.adapter.CloseTheDealAdapter;
import com.yikangcheng.admin.yikang.activity.aftersale.AfterSaleActivity;
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
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

public class CloseTheDealActivity extends BaseActivtiy implements CustomAdapt, ICoreInfe, View.OnClickListener {

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
    private PromptDialog mPromptDialog;
    private CloseTheDealBean mEntity;
    private TextView mTv_queRen;
    private int mAfterSaleStatus;


    /**
     * 已完成页面
     */
    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        //创建对象
        mPromptDialog = new PromptDialog(this);
        //设置自定义属性
        mPromptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);

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
        //确认收货
        mTv_queRen = (TextView) findViewById(R.id.tv_QueRen);


        //点击事件
        mTvActivityCloseKeFu.setOnClickListener(this);
        mImgActivityCloseFizhi.setOnClickListener(this);


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

        //接口回调
        mCloseTheDealAdapter.setOnClickListener(new CloseTheDealAdapter.OnClickListener() {
            @Override
            public void OnClickListener(View v, int position, int afterSaleStatus) {
                mPosition = position;
                mAfterSaleStatus = afterSaleStatus;
            }
        });

        //点击售后
        mCloseTheDealAdapter.setAfterOnClickListener(new CloseTheDealAdapter.AfterOnClickListener() {
            @Override
            public void onAfterclick(int id, int sid, int ssid) {
                Intent intent = new Intent(CloseTheDealActivity.this, SeleGoodActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        //解决滑动不流畅
        mRlvActivityCloseShangPin.setHasFixedSize(true);
        mRlvActivityCloseShangPin.setNestedScrollingEnabled(false);

        //设置item间距
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
                mPromptDialog.showWarnAlert("你确定要删除订单吗？", new PromptButton("取消", new PromptButtonListener() {
                    @Override
                    public void onClick(PromptButton button) {
                    }
                }), confirm);
            }
        });

        //设置弹窗字体颜色
//        confirm.setTextColor(Color.parseColor("#FFFFFF"));
//        confirm.setFocusBacColor(Color.parseColor("#ffaf00"));
    }

    //按钮的定义，创建一个按钮的对象
    PromptButton confirm = new PromptButton("确定", new PromptButtonListener() {
        @Override
        public void onClick(PromptButton button) {
            DeleteOrderIdPresenter deleteOrderIdPresenter = new DeleteOrderIdPresenter(new delete());
            deleteOrderIdPresenter.request(mOrderId);
            mIntent.putExtra("delete", "delete");
            setResult(4, mIntent);
            finish();
        }
    });

    @Override
    protected void initEventData() {
        /**
         * 退出当前Activity
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

    //点击监听
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击跳转客服
            case R.id.tv_activity_close_keFu:
                Information info = new Information();
                info.setAppkey("7560599b63bf43378d05d018ded42cdd");
                SobotApi.setCustomRobotHelloWord(CloseTheDealActivity.this, "您好，易康成客服很高兴为您服务，请问有什么可以帮助您的？");
                SobotApi.startSobotChat(CloseTheDealActivity.this, info);
                break;
            //复制订单编号
            case R.id.img_activity_close_fizhi:
                //传入需要复制的文字的控件
                CopyButtonLibrary copyButtonLibrary = new CopyButtonLibrary(getApplicationContext(), mTvActivityCloseBiaohao);
                copyButtonLibrary.init();
                break;
        }
    }

    //Delete删除ICoreInfe
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
        mEntity = (CloseTheDealBean) request.getEntity();
        //添加数据到适配器
        mCloseTheDealAdapter.addAll(mEntity.getDetailsList());
        //用户名
        mTvActivityCloseName.setText(mEntity.getUserAddress().getReceiver());
        //地址
        mAddress.setText(mEntity.getUserAddress().getAddress());
        mCityStr.setText(mEntity.getUserAddress().getCityStr());
        mTownStr.setText(mEntity.getUserAddress().getTownStr());
        mProvinceStr.setText(mEntity.getUserAddress().getProvinceStr());
        mTvActivityCloseBiaohao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String orderNo = mEntity.getOrder().getOrderNo();
                Toast.makeText(CloseTheDealActivity.this, orderNo, Toast.LENGTH_SHORT).show();
            }
        });

        //订单编号
        mTvActivityCloseBiaohao.setText(mEntity.getOrder().getOrderNo());
        //运费
        mTvActivityCloseYunFei.setText(mEntity.getOrder().getFreightPrice() + "");
        //金额
        mTvActivityCloseJinE.setText(mEntity.getOrder().getRealPrice() + "");
        //总额
        mTvActivityCloseZhongJi.setText(mEntity.getOrder().getSumPrice() + "");
        //支付方式
        if (mEntity.getOrder().getOrderState().equals("SUCCESS")) {
            mTvActivityCloseFangShi.setText("支付成功");
        }
        //发票类型
        int invoiceType = mEntity.getOrderBook().getInvoiceType();
        Log.e("tag", "success: " + invoiceType);
        if (mEntity.getOrderBook().getInvoiceType() == 1) {
            mTvActivityCloseFaPianLeiXing.setText("电子发票");
        } else if (mEntity.getOrderBook().getInvoiceType() == 2) {
            mTvActivityCloseFaPianLeiXing.setText("纸质发票");
        } else {
            mTvActivityCloseFaPianLeiXing.setText("无发票");
        }

        //发票内容
        if (mEntity.getOrderBook().getInvoiceContent() == 1) {
            mTvActivityCloseNeiRong.setText("商品明细");
        } else {
            mTvActivityCloseNeiRong.setText("商品类别");
        }

        //确认收货
        if (mAfterSaleStatus != 0) {
            mTv_queRen.setVisibility(View.GONE);
        } else {
            mTv_queRen.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void fail(ApiException e) {

    }
}
