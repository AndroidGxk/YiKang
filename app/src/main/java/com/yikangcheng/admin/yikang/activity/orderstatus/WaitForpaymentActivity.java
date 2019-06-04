package com.yikangcheng.admin.yikang.activity.orderstatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.PayResultActivity;
import com.yikangcheng.admin.yikang.activity.adapter.WaitForPaymentAdapter;
import com.yikangcheng.admin.yikang.activity.copy.CopyButtonLibrary;
import com.yikangcheng.admin.yikang.activity.orderstatus.countdown.ClockService;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.CloseTheDealBean;
import com.yikangcheng.admin.yikang.bean.DeleteOrderBean;
import com.yikangcheng.admin.yikang.bean.PayBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.paysdk.PayResult;
import com.yikangcheng.admin.yikang.presenter.CloseTheDeallPresenter;
import com.yikangcheng.admin.yikang.presenter.DeleteOrderIdPresenter;
import com.yikangcheng.admin.yikang.presenter.NewOrderPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import me.jessyan.autosize.internal.CustomAdapt;
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

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
    private int width;
    private PayBean mEntity;
    private String mPayType;
    private PromptDialog mPromptDialog;
    public static String CLOCK_ACTION = "com.jereh.Clock_Action";
    public static int TIME = 30 * 60 * 1000;//倒计时2个小时
    private String mStime;
    private String mCreateTime;
    private Date mCurDate;

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
        int height = display.getHeight();
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
        regReceiver();//注册广播
//        startService(new Intent(this, ClockService.class));//启动计时服务

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
                newOrderPresenter.request(mOrderId_wait, "ALIPAY");
            }
        });

        //刪除
        initDelete();


        //获取下单时间转换时间戳
        try {
            dateToStamp(mCreateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //获取当前系统时间
        mCurDate = new Date(System.currentTimeMillis());

    }


    /*
     * 将时间转换为时间戳
     */
    public String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(s);
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
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

    /**
     * 广播接受者，接受来自ClockService（计时服务）的广播，ClockService每隔一秒
     * 钟发一次广播
     */
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
        if (TIME == 0) {
            mStime = "计时结束";
        } else {
            int hour = TIME / (1000 * 60 * 60);
            int minute = TIME % (1000 * 60 * 60) / (60 * 1000);
            int second = (TIME % (1000 * 60 * 60)) % (60 * 1000) / 1000;
            String shour = "" + hour, sminute = "" + minute, ssecond = "" + second;
            if (hour <= 9) {
                shour = "0" + hour;
            }
            if (minute <= 9) {
                sminute = "0" + minute;
            }
            if (second <= 9) {
                ssecond = "0" + second;
            }
            mStime = shour + ":" + sminute + ":" + ssecond;
        }
        mTvActivityWaitfrrpaymentDaojishi.setText(mStime + "");
    }

    //點擊刪除訂單關閉當前Activity
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
                        Intent intent = new Intent(WaitForpaymentActivity.this, PayResultActivity.class);
                        intent.putExtra("pay", 1);
                        startActivity(intent);
                        finish();
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(WaitForpaymentActivity.this, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Intent intent = new Intent(WaitForpaymentActivity.this, PayResultActivity.class);
                            intent.putExtra("pay", 2);
                            startActivity(intent);
                            finish();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Intent intent = new Intent(WaitForpaymentActivity.this, PayResultActivity.class);
                            intent.putExtra("pay", 2);
                            startActivity(intent);
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

        ;
    };

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

        mPayType = entity.getOrder().getPayType();
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
//        mTvActivityWaitfrrpaymentDaojishi.setText(mStime);
        String orderState = entity.getOrder().getOrderState();
        if (orderState.equals("INIT")) {
            //启动计时服务这个方法
            startService(new Intent(this, ClockService.class));
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
            mEntity = (PayBean) request.getEntity();
            Toast.makeText(WaitForpaymentActivity.this, "" + mEntity.getOrderId(), Toast.LENGTH_SHORT).show();
            //todo 支付宝
            // 构造PayTask 对象
            // 调用支付接口，获取支付结果
            Runnable payRunnable = new Runnable() {
                @Override
                public void run() {
                    PayTask alipay = new PayTask(WaitForpaymentActivity.this);
                    Map<String, String> result = alipay.payV2(mEntity.getOrderinfo(), true);
//                        Map<String, String> result = alipay.payV2("alipay_sdk=alipay-sdk-java-3.7.26.ALL&app_id=2019030863469663&biz_content=%7B%22body%22%3A%22%E6%98%93%E5%BA%B7%E6%88%90%22%2C%22out_trade_no%22%3A%22NO155906963237992%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E7%BE%8E%E5%A6%86%E6%B5%8B%E8%AF%95008%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=https%3A%2F%2Fwww.yikch.com%2Fapi%2Forder%2FpaySuccess&sign=ndtQ6fc9XGCC6iZJ%2FX2UrDpFzHEtVehwJivhN%2BEEwZyNsBxYUIj2og0GvMo0JX3oEiVPB57SrMRFv03G%2Fz5Wl3n9HCCiZZF9X1WDfC7PJ3CNVHjMWQ%2FGC5oQ%2FE3NIfV%2FEVtkDRqBmOfChgJa%2F1dM%2BfT9mMFcKdH7ZYkJjwSwdMg4%2FtYy8FGifR%2FSphG41JujEEQMJ4IyOuKtxcROrKpQpF7NLT1hbrEI0dh0JKLsfwTks463%2FEpE5U0Gf5Fc9Ta5PrEkBTJFwWp2MtXtbgHEMtxCj9%2B17pgaEiowlX3Vfle8NvTuJlb7rz2glzzONgwwqRwkpzXhKE0ukaKUQ61tGw%3D%3D&sign_type=RSA2&timestamp=2019-05-29+02%3A53%3A52&version=1.0", true);
                    Message msg = new Message();
                    msg.what = SDK_PAY_FLAG;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                }
            };
            // 必须异步调用
            Thread payThread = new Thread(payRunnable);
            payThread.start();

        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
