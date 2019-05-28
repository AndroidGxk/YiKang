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
    public static final String RSA2_PRIVATE = "";
    public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIElWg0gSG5s2FVfFfdBvdFpAupuuhUFgdNotC05MUsqHjWccjJ1MlUu6bDFW+b75SZl0lBxfR8QF9MmKZIy8znUwgGJGglcH00xIqUCPZVlqCl1xtNc7GEZ2gT8n+mGNfJgcwjUhYj8T2ISiGd0YO1uUu8gpSOous+MJK0xFcj5AgMBAAECgYBGZyVbSIET6bRZffeMjkM7eMLFKE27DgCDTm4CxU3xCunjEgFTLn6c33E4E68REbsPHqzze5rZJz3FtuUstSfQVcJb0c2aCyWh57kvl+Fxi9dt9lL6qPkDWvZoWiNdPJBj+ZPHrGrBnPor9IbW8TerghQHkub/celoa/JNUHATAQJBALcYX0cNjn/0+E8xc/DtB0Wt4vCT9TeJsADmcN8GZx+avo0i7kEzIbrOCJF+vA9Jk93aF/Dbn/WnCrj1Xse0B+ECQQC0kbYelXwCH4ETl0UqjuuRRyQBkApK70+2Rxxy3tqZ0C/kT6S3fXBWHy73Bojr552YTM3hFb7D1MK65y8mkYQZAkAB/IK0G6KLItY6zbeLSpcEm4FVyNUlOBovuFBLKx+dSSl+EH3zOSHJjAitw2k45Tx0cLRHyaovmRNVtFvF4N8hAkA+VkN6QX8DOJ8WBVYSgC6hA99RTsnO3tk1A0219mufSDkQZ9JkqkB66t8K1s20K0zDxFgbCafG8Y+ceK1Vck0ZAkEAqsT0aPB8Me7TziwgnAj7ggNQisZEGRgFBlbpu5B0EKYGp7mrYZHB+hguYk0KGZSep0fxejSVLBZEXfvCVJsLoA==";

    @Override
    protected void initView() {
        //设置标题栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorTab);
        Display display = this.getWindowManager().getDefaultDisplay();
        height = display.getHeight();
        back_img = findViewById(R.id.back_img);
        pay_btn = findViewById(R.id.pay_btn);
        order_number = findViewById(R.id.order_number);
        money = findViewById(R.id.money);
        order_pay = findViewById(R.id.order_pay);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        CreatOrderBean creatorder = (CreatOrderBean) bundle.getSerializable("creatorder");
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
                        Map<String, String> result = alipay.payV2("alipay_sdk=alipay-sdk-java-3.1.0&app_id=2019030863469663&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22seller_id%22%3A%22%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.02%22%2C%22subject%22%3A%221%22%2C%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%22314VYGIAGG7ZOYY%22%7D&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA2&timestamp=2016-08-15%2012%3A12%3A15&version=1.0&sign=MsbylYkCzlfYLy9PeRwUUIg9nZPeN9SfXPNavUCroGKR5Kqvx0nEnd3eRmKxJuthNUx4ERCXe552EV9PfwexqW%2B1wbKOdYtDIb4%2B7PL3Pc94RZL0zKaWcaY3tSL89%2FuAVUsQuFqEJdhIukuKygrXucvejOUgTCfoUdwTi7z%2BZzQ%3D&version=1.0", true);
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
