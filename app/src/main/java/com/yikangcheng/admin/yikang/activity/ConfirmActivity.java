package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.CreatOrderBean;
import com.yikangcheng.admin.yikang.paysdk.OrderInfoUtil2_0;
import com.yikangcheng.admin.yikang.paysdk.PayResult;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.io.Serializable;
import java.util.Map;

import me.jessyan.autosize.internal.CustomAdapt;

public class ConfirmActivity extends BaseActivtiy implements CustomAdapt {

    private int height;
    private ImageView back_img;
    private TextView pay_btn, order_number, money, order_pay;
    private CreatOrderBean creatorder;

    @Override
    protected void initView() {
        //设置标题栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorTab);
        Display display = this.getWindowManager().getDefaultDisplay();
        height = display.getHeight();
        back_img = (ImageView) findViewById(R.id.back_img);
        pay_btn = (TextView) findViewById(R.id.pay_btn);
        order_number = (TextView) findViewById(R.id.order_number);
        money = (TextView) findViewById(R.id.money);
        order_pay = (TextView) findViewById(R.id.order_pay);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        creatorder = (CreatOrderBean) bundle.getSerializable("creatorder");
        order_number.setText(creatorder.getOrderNo());
        money.setText((int) creatorder.get_sumPrice() + "");
        order_pay.setText("支付宝");
        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 调用支付接口，获取支付结果
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        //todo 支付宝
                        // 构造PayTask 对象
                        PayTask alipay = new PayTask(ConfirmActivity.this);
                        Map<String, String> result = alipay.payV2(creatorder.getOrderinfo(), true);
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
        return R.layout.activity_confirm;
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
        return height / 2;
    }

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_CHECK_FLAG = 2;
    private Handler mHandler = new Handler() {
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
                        Toast.makeText(ConfirmActivity.this, "支付成功",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(ConfirmActivity.this, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(ConfirmActivity.this, "支付失败",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                case SDK_CHECK_FLAG: {
                    Toast.makeText(ConfirmActivity.this, "检查结果为：" + msg.obj,
                            Toast.LENGTH_SHORT).show();
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };
}
