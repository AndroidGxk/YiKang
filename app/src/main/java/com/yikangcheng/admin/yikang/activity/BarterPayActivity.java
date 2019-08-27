package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.hjq.toast.ToastUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.PayBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.paysdk.PayResult;
import com.yikangcheng.admin.yikang.presenter.NewOrderPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;
import com.yikangcheng.admin.yikang.util.UIUtils;

import java.util.Map;

import butterknife.BindView;

public class BarterPayActivity extends BaseActivtiy implements ICoreInfe {

    /**
     * 重新支付页面
     */
    private TextView money_count;
    private RelativeLayout alpay_rela, wechat_rela, rela;
    //判断微信支付宝
    private boolean zf_btn = true, wx_btn = false;
    private NewOrderPresenter newOrderPresenter;
    private int idInt;
    private Double moneyDou;
    private String ipAddressString;
    private String type;
    private ImageView back_img;
    private PayBean mEntity;
    @BindView(R.id.title)
    TextView title;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void initView() {
        SharedPreferences sharedPreferences = getSharedPreferences("orderInfo", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        //设置状态栏颜色
        if (!getLogUser(this).getThemeColors().equals("")) {
            StatusBarUtil.setStatusBarMode(this, true, Color.parseColor(getLogUser(this).getThemeColors()));
        } else {
            StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        }
//        title.setTextColor(Color.parseColor());
        //获取Ip地址
        ipAddressString = UIUtils.getIpAddressString();
        money_count = (TextView) findViewById(R.id.money_count);
        alpay_rela = (RelativeLayout) findViewById(R.id.alpay_rela);
        rela = (RelativeLayout) findViewById(R.id.rela);
        back_img = (ImageView) findViewById(R.id.back_img);
        wechat_rela = (RelativeLayout) findViewById(R.id.wechat_rela);
        rela.setBackgroundColor(Color.parseColor(getLogUser(this).getThemeColors()));
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        idInt = Integer.parseInt(id);
        String money = intent.getStringExtra("money");
        moneyDou = Double.parseDouble(money);
        money_count.setText(money);
        newOrderPresenter = new NewOrderPresenter(this);
    }

    @Override
    protected void initEventData() {
        //退出
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //支付宝
        alpay_rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (zf_btn) {
                    return;
                } else {
                    wx_btn = false;
                    zf_btn = true;
                    alpay_rela.setBackgroundResource(R.drawable.yellow_pay_shape);
                    wechat_rela.setBackgroundResource(R.drawable.gray_pay_shape);
                }
            }
        });
        //微信
        wechat_rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wx_btn) {
                    return;
                } else {
                    wx_btn = true;
                    zf_btn = false;
                    alpay_rela.setBackgroundResource(R.drawable.gray_pay_shape);
                    wechat_rela.setBackgroundResource(R.drawable.yellow_pay_shape);
                }
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_barter_pay;
    }

    public void toPayOnclick(View view) {
        newOrderPresenter.request(idInt, zf_btn ? "ALIPAY" : "WEIXIN", zf_btn ? "" : ipAddressString);
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        if (request.isSuccess()) {

            mEntity = (PayBean) request.getEntity();
            type = zf_btn ? "ALIPAY" : "WEIXIN";
            if (type.equals("ALIPAY")) {
                //todo 支付宝
                // 构造PayTask 对象
                // 调用支付接口，获取支付结果
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(BarterPayActivity.this);
                        Map<String, String> result = alipay.payV2(mEntity.getOrderinfo(), true);
//                        Map<String, String> result = alipay.payV2("alipay_sdk=alipay-sdk-java-3.7.26.ALL&app_id=2019030863469663&biz_content=%7B%22body%22%3A%22%E6%98%93%E5%BA%B7%E6%88%90%22%2C%22out_trade_no%22%3A%22NO155906963237992%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E7%BE%8E%E5%A6%86%E6%B5%8B%E8%AF%95008%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=https%3A%2F%2Fwww.yikch.com%2Fapi%2Forder%2FpaySuccess&sign=ndtQ6fc9XGCC6iZJ%2FX2UrDpFzHEtVehwJivhN%2BEEwZyNsBxYUIj2og0GvMo0JX3oEiVPB57SrMRFv03G%2Fz5Wl3n9HCCiZZF9X1WDfC7PJ3CNVHjMWQ%2FGC5oQ%2FE3NIfV%2FEVtkDRqBmOfChgJa%2F1dM%2BfT9mMFcKdH7ZYkJjwSwdMg4%2FtYy8FGifR%2FSphG41JujEEQMJ4IyOuKtxcROrKpQpF7NLT1hbrEI0dh0JKLsfwTks463%2FEpE5U0Gf5Fc9Ta5PrEkBTJFwWp2MtXtbgHEMtxCj9%2B17pgaEiowlX3Vfle8NvTuJlb7rz2glzzONgwwqRwkpzXhKE0ukaKUQ61tGw%3D%3D&sign_type=RSA2&timestamp=2019-05-29+02%3A53%3A52&version=1.0", true);
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
            } else if (type.equals("WEIXIN")) {
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
//                Toast.makeText(WaitForpaymentActivity.this, "haha", Toast.LENGTH_SHORT).show();
                editor.putInt("orderType", mEntity.getIsZeroPurchase());
                editor.commit();
            }
        }
    }

    @Override
    public void fail(ApiException e) {

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

                        if (sharedPreferences.getInt("orderType", 0) == 2) {
                            Intent intent = new Intent(BarterPayActivity.this, OrderFormActivity.class);
                            intent.putExtra("tab", "daishouhuo");
                            startActivity(intent);
                            finish();
                        } else if (sharedPreferences.getInt("orderType", 0) == 3) {
                            Intent intent = new Intent(BarterPayActivity.this, H5SecActivity.class);
                            intent.putExtra("http", "daishouhuo");
                            startActivity(intent);
                            finish();
                        }
//                        Intent intent = new Intent(BarterPayActivity.this, PayResultActivity.class);
//                        intent.putExtra("pay", 1);
//                        startActivity(intent);
//                        finish();
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            ToastUtils.show("支付结果确认中");
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Intent intent = new Intent(BarterPayActivity.this, PayResultActivity.class);
                            intent.putExtra("pay", 2);
                            startActivity(intent);
                            finish();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Intent intent = new Intent(BarterPayActivity.this, PayResultActivity.class);
                            intent.putExtra("pay", 2);
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

}
