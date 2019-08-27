package com.yikangcheng.admin.yikang.activity.orderstatus.newactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.hjq.toast.ToastUtils;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.H5SecActivity;
import com.yikangcheng.admin.yikang.activity.OrderFormActivity;
import com.yikangcheng.admin.yikang.activity.adapter.ordernew_adapter.OrderWaitAdapter;
import com.yikangcheng.admin.yikang.activity.copy.CopyButtonLibrary;
import com.yikangcheng.admin.yikang.activity.orderstatus.countdown.ClockService;
import com.yikangcheng.admin.yikang.activity.particulars.ParticularsActivity;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.CreatOrderBean;
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
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

public class OrderWaitActivity extends BaseActivtiy implements ICoreInfe {
    @BindView(R.id.daifu_recycle)
    RecyclerView recycle;
    @BindView(R.id.opneorclose_btn)
    LinearLayout opneorclose_btn;
    @BindView(R.id.text_open_close)
    TextView text_open_close;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.nested)
    NestedScrollView nested;
    @BindView(R.id.user_name)
    TextView user_name;
    @BindView(R.id.user_phone)
    TextView user_phone;
    @BindView(R.id.user_address)
    TextView user_address;
    @BindView(R.id.invtype_text)
    TextView invtype_text;
    @BindView(R.id.danhao)
    TextView danhao;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.fangshi)
    TextView fangshi;
    @BindView(R.id.peisong)
    TextView peisong;
    @BindView(R.id.good_sumprice)
    TextView good_sumprice;
    @BindView(R.id.good_yun)
    TextView good_yun;
    @BindView(R.id.yingfukuan)
    TextView yingfukuan;
    @BindView(R.id.progress)
    TwoBallRotationProgressBar progress;
    @BindView(R.id.kefu)
    LinearLayout kefu;
    @BindView(R.id.copay_img)
    ImageView copay_img;
    @BindView(R.id.time_order)
    TextView time_order;
    @BindView(R.id.go_pay)
    TextView go_pay;
    @BindView(R.id.back_img)
    ImageView back_img;
    @BindView(R.id.cancel_btn)
    TextView cancel_btn;
    @BindView(R.id.toolbar_activity_orderfrom)
    RelativeLayout toolbar_activity_orderfrom;
    //待付款订单列表
    private OrderWaitAdapter waitAdapter;
    //接收订单ID
    private Intent mIntent;
    /**
     * 判断展开或者查看
     */
    public boolean isClick = false;
    //订单详情
    private CloseTheDeallPresenter closeTheDeallPresenter;
    private int mOrderId;
    private List<WaliDealBean.DetailsListBean> mList;
    //收起
    private List<WaliDealBean.DetailsListBean> mLists = new ArrayList<>();
    //展开
    private List<WaliDealBean.DetailsListBean> mListz = new ArrayList<>();
    private PromptDialog mPromptDialog;
    /**
     * 倒计时
     */
    public static String CLOCK_ACTION = "com.jereh.Clock_Action";
    public static int TIME = 30 * 60 * 1000;//倒计时2个小时
    private long sTime;
    private String mTime;
    private long ts;
    private String mCreateTime;
    private int mPosition;
    /**
     * 重新支付
     */
    private String mPayType = "";
    private WaliDealBean entity;
    private NewOrderPresenter newOrderPresenter;
    private PayBean mEntity;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    @Override
    protected void initView() {

        sharedPreferences = getSharedPreferences("orderInfo", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        //设置状态栏颜色
        if (!getLogUser(this).getThemeColors().equals("")) {
            StatusBarUtil.setStatusBarMode(this, true, Color.parseColor(getLogUser(this).getThemeColors()));
        } else {
            StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        }
        toolbar_activity_orderfrom.setBackgroundColor(Color.parseColor(getLogUser(this).getThemeColors()));
        cancel_btn.setTextColor(Color.parseColor(getLogUser(this).getThemeColors()));
        GradientDrawable myGrad = (GradientDrawable) go_pay.getBackground();
        myGrad.setColor(Color.parseColor(getLogUser(this).getThemeColors()));
        /**
         * 订单详情Adapter
         */
        waitAdapter = new OrderWaitAdapter(this);
        /**
         * 订单详情
         */
        closeTheDeallPresenter = new CloseTheDeallPresenter(this);
        /**
         * 接收订单ID
         */
        mIntent = getIntent();
        mOrderId = mIntent.getIntExtra("orderId_wait", 0);
        /**
         * 弹框创建
         */
        mPromptDialog = new PromptDialog(this);
        //设置自定义属性
        mPromptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);
        /**
         * 点击事件
         */
        onClickListener();
        /**
         * 注册倒计时广播
         */
        regReceiver();
        /**
         * 重新支付生成新订单
         */
        newOrderPresenter = new NewOrderPresenter(new NewOrder());
    }

    @Override
    protected void initEventData() {

        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setAdapter(waitAdapter);
        //解决滑动不流畅
        recycle.setHasFixedSize(true);
        recycle.setNestedScrollingEnabled(false);
        //请求接口
        closeTheDeallPresenter.request(mOrderId);
        go_pay.setClickable(true);
    }

    /**
     * 点击事件处理
     *
     * @return
     */
    public void onClickListener() {
        /**
         * 展开或合起
         */
        opneorclose_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isClick) {
                    hideItem();
                    nested.scrollTo(0, 0);
                    isClick = false;
                } else {
                    showItem();
                    isClick = true;
                }
            }
        });
        /**
         * 进入商品详情页
         */
        waitAdapter.setOnGoPariClickListener(new OrderWaitAdapter.onGoPariClickListener() {
            @Override
            public void onClick(int goodId) {
                Intent intent = new Intent(OrderWaitActivity.this, ParticularsActivity.class);
                intent.putExtra("id", goodId);
                startActivity(intent);
            }
        });
        /**
         * 点击客服
         */
        kefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Information info = new Information();
                info.setAppkey("7560599b63bf43378d05d018ded42cdd");
                SobotApi.setCustomRobotHelloWord(OrderWaitActivity.this, "您好，易康成客服很高兴为您服务，请问有什么可以帮助您的？");
                SobotApi.startSobotChat(OrderWaitActivity.this, info);
            }
        });

        /**
         * 复制单号
         */
        copay_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //传入需要复制的文字的控件
                CopyButtonLibrary copyButtonLibrary = new CopyButtonLibrary(getApplicationContext(), danhao);
                copyButtonLibrary.init();
            }
        });
        /**
         * 去支付
         */
        go_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPayType = entity.getOrder().getPayType();
                if (mPayType.equals("ALIPAY")) {
                    newOrderPresenter.request(entity.getOrder().getOrderId(), "ALIPAY", "");
                } else if (mPayType.equals("WEIXIN")) {
                    newOrderPresenter.request(entity.getOrder().getOrderId(), "WEIXIN", "");
                } else {
                    ToastUtils.show("系统错误,支付失败");
                }
            }
        });
        /**
         * 退出页面
         */
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        /**
         * 取消订单
         */
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPromptDialog.showWarnAlert("确定要删除订单吗？", new PromptButton("取消", new PromptButtonListener() {
                    @Override
                    public void onClick(PromptButton button) {
                    }
                }), new PromptButton("确定", new PromptButtonListener() {
                    @Override
                    public void onClick(PromptButton button) {
                        DeleteOrderIdPresenter deleteOrderIdPresenter = new DeleteOrderIdPresenter(new DeleteOrder());

                        deleteOrderIdPresenter.request(entity.getOrder().getOrderId());
                    }
                }));
            }
        });
    }


    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_order_wait;
    }

    @Override
    protected void createPresenter() {

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

    /**
     * 倒计时改变
     *
     * @return
     */
    private void changeTime() {
        if (sTime == -25201000) {
            time_order.setText("即将取消订单");
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
            time_order.setText("请在" + mTime + "秒内完成支付，超时订单自动取消");
        }
    }

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

    /**
     * 收起
     */
    public void hideItem() {
        waitAdapter.removeAll();
        mLists.clear();
        mListz.clear();
        if (mList != null) {
            for (int i = 0; i < 2; i++) {
                mLists.add(mList.get(i));
            }
            waitAdapter.addAll(mLists);
        }
        text_open_close.setText("查看更多");
        image.setBackgroundResource(R.drawable.zhanshi);
    }

    /**
     * 展开
     */
    public void showItem() {
        waitAdapter.removeAll();
        mListz.clear();
        mLists.clear();
        if (mList != null) {
            for (int i = 0; i < mList.size(); i++) {
                mListz.add(mList.get(i));
            }
            waitAdapter.addAll(mListz);
        }
        text_open_close.setText("点击收起");
        image.setBackgroundResource(R.drawable.jiantoushang);
    }

    /**
     * 删除订单数据请求
     */
    public class DeleteOrder implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;

            if (request.isSuccess()) {
                mIntent.putExtra("delete", "delete");
                setResult(2, mIntent);
                finish();
            } else {
                ToastUtils.show("" + request.getMessage());
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    /**
     * 支付
     */
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
                        if (sharedPreferences.getInt("orderType", 0) == 2) {
                            Intent intent = new Intent(OrderWaitActivity.this, OrderFormActivity.class);
                            intent.putExtra("tab", "daishouhuo");
                            startActivity(intent);
                            finish();
                        } else if (sharedPreferences.getInt("orderType", 0) == 3) {
                            Intent intent = new Intent(OrderWaitActivity.this, H5SecActivity.class);
                            intent.putExtra("title", "易康成杯乒乓球报名");
                            intent.putExtra("http", "https://www.yikch.com/mobile/appShow/tableTennisRegister?type=android&userId=" + getLogUser(BaseApp.getApp()).getId());
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            ToastUtils.show("支付结果确认中");

                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
//                            Intent intent = new Intent(WaitForpaymentActivity.this, PayResultActivity.class);
//                            intent.putExtra("pay", 2);
//                            startActivity(intent);
                            finish();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Intent intent = new Intent(OrderWaitActivity.this, OrderFormActivity.class);
                            intent.putExtra("tab", "daifukuan");
                            startActivity(intent);
                            finish();
                        }
                    }
                    break;
                }
                case SDK_CHECK_FLAG: {
                    ToastUtils.show("检查结果为：" + msg.obj);
                    break;
                }
                default:
                    break;
            }
        }
    };

    /**
     * 订单详情数据
     *
     * @param data
     */
    @Override
    public void success(Object data) {
        Request request = (Request) data;
        entity = (WaliDealBean) request.getEntity();
        mList = entity.getDetailsList();
        if (mList.size() > 2) {
            opneorclose_btn.setVisibility(View.VISIBLE);
            hideItem();
        } else if (mList.size() <= 2) {
            opneorclose_btn.setVisibility(View.GONE);
            waitAdapter.addAll(mList);
        } else {
            opneorclose_btn.setVisibility(View.GONE);
            waitAdapter.addAll(mList);
        }
        if (entity.getOrderBook() != null) {
            WaliDealBean.OrderBookBean orderBookBean = entity.getOrderBook();
            user_name.setText(orderBookBean.getReceiver());
            if (orderBookBean.getMobile().length() >= 11) {
                String frontMobile = orderBookBean.getMobile().substring(0, 3);
                String frontStr = frontMobile + "****";
                String UserMobile = frontStr + orderBookBean.getMobile().substring(7);
                user_phone.setText(UserMobile);
            }
            user_address.setText(orderBookBean.getAddress());
            //发票类型
            if (entity.getOrderBook().getInvoiceType() == 1) {
                invtype_text.setText("电子发票");
            } else if (entity.getOrderBook().getInvoiceType() == 2) {
                invtype_text.setText("纸质发票");
            } else {
                invtype_text.setText("无发票");
            }
            //支付方式
            if (entity.getOrder().getPayType().equals("WEIXIN")) {
                fangshi.setText("微信");
            } else if (entity.getOrder().getPayType().equals("ALIPAY")) {
                fangshi.setText("支付宝");
            }
            danhao.setText(entity.getOrder().getOrderNo());
            time.setText(entity.getOrder().getCreateTime());
            good_sumprice.setText("¥ " + entity.getOrder().getSumPrice());
            good_yun.setText("¥ " + entity.getOrder().getFreightPrice());
            yingfukuan.setText("¥ " + entity.getOrder().getRealPrice());
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
            time_order.setText("请在" + mTime + "秒内完成支付，超时订单自动取消");
            if (orderState.equals("INIT")) {
                //启动计时服务这个方法
                startService(new Intent(this, ClockService.class));
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progress.setVisibility(View.GONE);
                    progress.stopAnimator();
                    nested.setVisibility(View.VISIBLE);
                    go_pay.setClickable(true);
                }
            }, 500);
        }
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


    /**
     * 生成新的订单号
     */
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
                            PayTask alipay = new PayTask(OrderWaitActivity.this);
                            if (mEntity.getOrderinfo() == null || mEntity.getOrderinfo().equals("")) {
                                return;
                            }
                            Map<String, String> result = alipay.payV2(mEntity.getOrderinfo(), true);
                            Message msg = new Message();
                            msg.what = SDK_PAY_FLAG;
                            msg.obj = result;
                            mHandler.sendMessage(msg);
                            editor.putInt("orderType", mEntity.getIsZeroPurchase());
                            editor.commit();
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
                    editor.putInt("orderType", mEntity.getIsZeroPurchase());
                    editor.commit();
                    CreatOrderBean creatOrderBean = new CreatOrderBean();
                    creatOrderBean.setIsZeroPurchase(mEntity.getIsZeroPurchase());
                    setOrderInfo(OrderWaitActivity.this, creatOrderBean);
                    setNewPayInfo(OrderWaitActivity.this, mEntity);

                } else {
                    ToastUtils.show("系统错误,支付失败");
                }
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
