/*
package com.yikangcheng.admin.yikang.activity.orderstatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.WaitForPaymentAdapter;
import com.yikangcheng.admin.yikang.activity.copy.CopyButtonLibrary;
import com.yikangcheng.admin.yikang.activity.obligation.PaidActivity;
import com.yikangcheng.admin.yikang.activity.orderstatus.countdown.ClockService;
import com.yikangcheng.admin.yikang.activity.particulars.ParticularsActivity;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.DeleteOrderBean;
import com.yikangcheng.admin.yikang.bean.PayBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.WaliDealBean;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.paysdk.PayResult;
import com.yikangcheng.admin.yikang.presenter.CloseTheDeallPresenter;
import com.yikangcheng.admin.yikang.presenter.DeleteOrderIdPresenter;
import com.yikangcheng.admin.yikang.presenter.NewOrderPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;
import com.yikangcheng.admin.yikang.util.TwoBallRotationProgressBar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import me.jessyan.autosize.internal.CustomAdapt;
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

*/
/**
 * 这是订单详情里面的----等待付款
 *//*

public class WaitForpaymentActivity extends BaseActivtiy implements  ICoreInfe {
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
    private RelativeLayout mTvActivityWaitfrrpaymentKeFu;
    private TextView mTvActivityWaitfrrpaymentFaPianLeiXing;
    private TextView mTvActivityWaitfrrpaymentNeiRong;
    private TextView go_pay;
    private TextView mShanchu;
    private TextView mTvActivityFackOfProvinceStr;
    private WaitForPaymentAdapter mWaitForPaymentAdapter;
    private NewOrderPresenter newOrderPresenter;
    private int orderId;
    private String orderType;
    private int mPosition;
    private Intent mIntent;
    private int mOrderId_wait;
    private int width;
    private PayBean mEntity;
    private String mPayType = "";
    private PromptDialog mPromptDialog;
    public static String CLOCK_ACTION = "com.jereh.Clock_Action";
    public static int TIME = 30 * 60 * 1000;//倒计时2个小时
    private String mStime;
    private String mCreateTime;
    private String mTime;
    private long ts;
    private NestedScrollView mNestedScrollView;
    private TwoBallRotationProgressBar progress;
    private RelativeLayout rela1, rela2;
    private long sTime;

    @Override
    protected void initView() {
        mIntent = getIntent();
        mOrderId_wait = mIntent.getIntExtra("orderId_wait", 0);
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        //创建对象
        mPromptDialog = new PromptDialog(this);
        //设置自定义属性
        mPromptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        //ToolBar返回按钮
        mImgActivityWaitfrrpaymentFanhui = (ImageView) findViewById(R.id.img_activity_waitfrrpayment_fanhui);
        //ToolBar
        mToolbarActivityWaitfrrpayment = (RelativeLayout) findViewById(R.id.toolbar_activity_waitfrrpayment);
        rela1 = (RelativeLayout) findViewById(R.id.rela1);
        rela2 = (RelativeLayout) findViewById(R.id.rela2);
        //倒计时
        mTvActivityWaitfrrpaymentDaojishi = (TextView) findViewById(R.id.tv_activity_waitfrrpayment_daojishi);
        //用户姓名
        mTvActivityWaitfrrpaymentName = (TextView) findViewById(R.id.tv_activity_waitfrrpayment_name);
        //用户地址
        mTvActivityFackOfProvinceStr = (TextView) findViewById(R.id.tv_activity_fack_of_provinceStr);
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
        mTvActivityWaitfrrpaymentKeFu = (RelativeLayout) findViewById(R.id.tv_activity_waitfrrpayment_keFu);
        //发票类型
        mTvActivityWaitfrrpaymentFaPianLeiXing = (TextView) findViewById(R.id.tv_activity_waitfrrpayment_FaPianLeiXing);
        //发票内容
        mTvActivityWaitfrrpaymentNeiRong = (TextView) findViewById(R.id.tv_activity_waitfrrpayment_NeiRong);
        //删除订单
        mShanchu = (TextView) findViewById(R.id.tv_activity_wait_shanchu);
        //去支付
        go_pay = (TextView) findViewById(R.id.go_pay);
        //进度条
        progress = (TwoBallRotationProgressBar) findViewById(R.id.progress);
        mNestedScrollView = (NestedScrollView) findViewById(R.id.nestedSV);
        regReceiver();//注册广播
        //解决滑动不流畅
        mRlvActivityWaitfrrpaymentShangPin.setHasFixedSize(true);
        mRlvActivityWaitfrrpaymentShangPin.setNestedScrollingEnabled(false);
        //点击客服跳转页面
        mTvActivityWaitfrrpaymentKeFu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Information info = new Information();
                info.setAppkey("7560599b63bf43378d05d018ded42cdd");
                SobotApi.setCustomRobotHelloWord(WaitForpaymentActivity.this, "您好，易康成客服很高兴为您服务，请问有什么可以帮助您的？");
                SobotApi.startSobotChat(WaitForpaymentActivity.this, info);
            }
        });
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
        ArrayList<WaliDealBean.DetailsListBean> mShopSpecDetailedBeans = new ArrayList<>();
        mWaitForPaymentAdapter = new WaitForPaymentAdapter(mShopSpecDetailedBeans, this);
        mRlvActivityWaitfrrpaymentShangPin.setAdapter(mWaitForPaymentAdapter);
        mWaitForPaymentAdapter.setOnClickListener(new WaitForPaymentAdapter.OnClickListener() {
            @Override
            public void OnClickListener(View v, int position) {
                mPosition = position;
            }
        });

        */
/**
         * P层
         *//*

        CloseTheDeallPresenter closeTheDeallPresenter = new CloseTheDeallPresenter(this);
        closeTheDeallPresenter.request(mOrderId_wait);
        */
/**
         * 去支付
         *//*

        go_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPayType.equals("ALIPAY")) {
                    newOrderPresenter.request(mOrderId_wait, "ALIPAY", "");
                } else if (mPayType.equals("WEIXIN")) {
                    newOrderPresenter.request(mOrderId_wait, "WEIXIN", "");
                } else {
                    Toast.makeText(WaitForpaymentActivity.this, "系统错误,支付失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
        */
/**
         * 跳转商品详情
         *//*

        mWaitForPaymentAdapter.setToGetIdListener(new WaitForPaymentAdapter.toGetIdListener() {
            @Override
            public void onClickListener(int id) {
                Intent intent = new Intent(WaitForpaymentActivity.this, ParticularsActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        //刪除
        initDelete();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        super.unregisterReceiver(clockReceiver);
        TIME = 30 * 60 * 1000;
        Intent intent = new Intent();
        intent.setAction(ClockService.CLOCK_SERVICE_ACTION);
        intent.putExtra("method", "stop");
        super.sendBroadcast(intent);
    }


    private void regReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CLOCK_ACTION);
        super.registerReceiver(clockReceiver, intentFilter);
    }

    */
/**
     * 广播接受者，接受来自ClockService（计时服务）的广播，ClockService每隔一秒
     * 钟发一次广播
     *//*

    private BroadcastReceiver clockReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            changeTime();//改变TextView中的显示时间
        }
    };

    //通过发送广播，控制计时服务
    //继续计时
    public void restart(View view) {
        Intent intent = new Intent();
        intent.setAction(ClockService.CLOCK_SERVICE_ACTION);
        intent.putExtra("method", "continue");
        super.sendBroadcast(intent);
    }

    //通过发送广播，控制计时服务
    //暂停计时
    public void pause(View view) {
        Intent intent = new Intent();
        intent.setAction(ClockService.CLOCK_SERVICE_ACTION);
        intent.putExtra("method", "pause");
        super.sendBroadcast(intent);
    }

    private void changeTime() {
        mStime = "";
        if (sTime == -25201000) {
            mTvActivityWaitfrrpaymentDaojishi.setText("即将取消订单");
        } else {
            long l = System.currentTimeMillis();
            long time = l - ts;
            SimpleDateFormat sdf = new SimpleDateFormat("mm分ss");
            //三十分钟
            SimpleDateFormat simpleDateFormats = new SimpleDateFormat("mm:ss");
            Date threeTime = null;
            try {
                threeTime = simpleDateFormats.parse("30:01");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long three_time = threeTime.getTime();
            sTime = three_time - time;
            // 时间戳转换成时间
            mTime = sdf.format(new Date(sTime));
            mTvActivityWaitfrrpaymentDaojishi.setText("剩余" + mTime + "秒系统将取消订单");
        }
    }

    private void initDelete() {
        mShanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPromptDialog.showWarnAlert("你确定要删除订单吗？", new PromptButton("取消", new PromptButtonListener() {
                    @Override
                    public void onClick(PromptButton button) {
                    }
                }), confirm);
            }
        });
    }

    //按钮的定义，创建一个按钮的对象
    PromptButton confirm = new PromptButton("确定", new PromptButtonListener() {
        @Override
        public void onClick(PromptButton button) {
            DeleteOrderIdPresenter deleteOrderIdPresenter = new DeleteOrderIdPresenter(new delete());
            deleteOrderIdPresenter.request(mOrderId_wait);
            mIntent.putExtra("delete", "delete");
            setResult(2, mIntent);
            finish();
        }
    });


    @Override
    protected void initEventData() {
        */
/**
         * 点击返回按钮关闭当前页面
         *//*

        mImgActivityWaitfrrpaymentFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
    }


    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_CHECK_FLAG = 2;
    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Intent intent = new Intent(WaitForpaymentActivity.this, PaidActivity.class);
//                        intent.putExtra("pay", 1);
                        startActivity(intent);
                        finish();
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(WaitForpaymentActivity.this, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
//                            Intent intent = new Intent(WaitForpaymentActivity.this, PayResultActivity.class);
//                            intent.putExtra("pay", 2);
//                            startActivity(intent);
                            finish();
                        } else {
//                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
//                            Intent intent = new Intent(WaitForpaymentActivity.this, PayResultActivity.class);
//                            intent.putExtra("pay", 2);
//                            startActivity(intent);
                            finish();
                        }
                    }
                    break;
                }
                case SDK_CHECK_FLAG: {
                    Toast.makeText(WaitForpaymentActivity.this, "检查结果为：" + msg.obj,
                            Toast.LENGTH_SHORT).show();
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_wait_forpayment;
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
        WaliDealBean entity = (WaliDealBean) request.getEntity();
        mWaitForPaymentAdapter.addAll(entity.getDetailsList());
        mPayType = entity.getOrder().getPayType();
        //重新下单用到的Id
        orderId = entity.getOrder().getOrderId();
        orderType = entity.getOrder().getOrderType();
        //用户名
        mTvActivityWaitfrrpaymentName.setText(entity.getOrderBook().getReceiver());
        //地址
        mTvActivityFackOfProvinceStr.setText(entity.getOrderBook().getAddress());
        //订单编号
        mTvActivityWaitfrrpaymentBiaohao.setText(entity.getOrder().getOrderNo());
        //运费
        mTvActivityWaitfrrpaymentYunFei.setText(entity.getOrder().getFreightPrice() + "");


        //金额
        mTvActivityWaitfrrpaymentJinE.setText(entity.getOrder().getRealPrice() + "");
        //总额
        mTvActivityWaitfrrpaymentZhongJi.setText(entity.getOrder().getSumPrice() + "");

        //支付方式
        if (mPayType.equals("WEIXIN")) {
            mTvActivityWaitfrrpaymentFangShi.setText("微信");
        } else if (mPayType.equals("ALIPAY")) {
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

        mCreateTime = entity.getOrder().getCreateTime();
        String orderState = entity.getOrder().getOrderState();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(mCreateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ts = date.getTime();
        long l = System.currentTimeMillis();
        long time = l - ts;
        SimpleDateFormat sdf = new SimpleDateFormat("mm分ss");
        //三十分钟
        SimpleDateFormat simpleDateFormats = new SimpleDateFormat("mm:ss");
        Date threeTime = null;
        try {
            threeTime = simpleDateFormats.parse("30:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long three_time = threeTime.getTime();
        long sTime = three_time - time;
        // 时间戳转换成时间
        mTime = sdf.format(new Date(sTime));
        mTvActivityWaitfrrpaymentDaojishi.setText("剩余" + mTime + "秒系统将取消订单");
        if (orderState.equals("INIT")) {
            //启动计时服务这个方法
            startService(new Intent(this, ClockService.class));
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.GONE);
                progress.stopAnimator();
                rela1.setVisibility(View.VISIBLE);
                rela2.setVisibility(View.VISIBLE);
            }
        }, 500);
    }

    @Override
    public void fail(ApiException e) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.GONE);
                progress.stopAnimator();
            }
        }, 500);
    }

    */
/**
     * 生成新的订单号
     *//*

    private class NewOrder implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            if (request.isSuccess()) {
                mEntity = (PayBean) request.getEntity();
                if (mPayType.equals("ALIPAY")) {
                    //todo 支付宝
                    // 构造PayTask 对象
                    // 调用支付接口，获取支付结果
                    Runnable payRunnable = new Runnable() {
                        @Override
                        public void run() {
                            PayTask alipay = new PayTask(WaitForpaymentActivity.this);
                            if (mEntity.getOrderinfo() == null || mEntity.getOrderinfo().equals("")) {
                                return;
                            }
                            Map<String, String> result = alipay.payV2(mEntity.getOrderinfo(), true);
                            Message msg = new Message();
                            msg.what = SDK_PAY_FLAG;
                            msg.obj = result;
                            mHandler.sendMessage(msg);
                        }
                    };
                    // 必须异步调用
                    Thread payThread = new Thread(payRunnable);
                    payThread.start();
                } else if (mPayType.equals("WEIXIN")) {
                    PayReq req = new PayReq();//PayReq就是订单信息对象
                    //给req对象赋值
                    req.appId = "wx38a0aef5df24fe50";
                    req.partnerId = "1528387611";
                    req.prepayId = mEntity.getPrepayId();
                    req.packageValue = "Sign=WXPay";
                    req.nonceStr = mEntity.getNonceStr();
                    req.timeStamp = mEntity.getTimeStamp();
                    req.sign = mEntity.getSign();
                    BaseApp.mWxApi.sendReq(req);//将订单信息对象发送给微信服务器，即发送支付请求
                } else {
                    Toast.makeText(WaitForpaymentActivity.this, "系统错误,支付失败", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
*/
