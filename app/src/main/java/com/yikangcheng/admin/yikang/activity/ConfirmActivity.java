package com.yikangcheng.admin.yikang.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.hjq.toast.ToastUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.orderstatus.newactivity.OrderWaitActivity;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.CreatOrderBean;
import com.yikangcheng.admin.yikang.paysdk.PayResult;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.Map;

import butterknife.BindView;

/**
 * 支付页面
 */
public class ConfirmActivity extends BaseActivtiy {

    private int height;
    private ImageView back_img;
    private TextView pay_btn, order_number, money, order_pay;
    private CreatOrderBean creatorder;
    private int width;
    private View dialogview;
    private Dialog dialog;
    private RelativeLayout table;
    @BindView(R.id.dingdan_text)
    TextView dingdan_text;
    @BindView(R.id.title)
    TextView title;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void initView() {
        sharedPreferences = getSharedPreferences("orderInfo", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        closeSwipeBack();
        //设置标题栏颜色
        if (!getLogUser(this).getThemeColors().equals("")) {
            StatusBarUtil.setStatusBarMode(this, true, Color.parseColor(getLogUser(this).getThemeColors()));
        } else {
            StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        }
        dingdan_text.setTextColor(Color.parseColor(getLogUser(this).getThemeColors()));
//        title.setTextColor(Color.parseColor());
        table = (RelativeLayout) findViewById(R.id.table);
        table.setBackgroundColor(Color.parseColor(getLogUser(this).getThemeColors()));
        Display display = this.getWindowManager().getDefaultDisplay();
        height = display.getHeight();
        width = display.getWidth();
        back_img = (ImageView) findViewById(R.id.back_img);
        pay_btn = (TextView) findViewById(R.id.pay_btn);
        order_number = (TextView) findViewById(R.id.order_number);
        money = (TextView) findViewById(R.id.money);
        order_pay = (TextView) findViewById(R.id.order_pay);
        creatorder = getOrderInfo(this);
        order_number.setText(creatorder.getOrderNo());
        money.setText((double) creatorder.getSumPrice() + "");
        String payType = creatorder.getPayType();
        if (payType.equals("ALIPAY")) {
            order_pay.setText("支付宝");
        } else if (payType.equals("WEIXIN")) {
            order_pay.setText("微信");
        }

        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String payType = creatorder.getPayType();
                if (payType.equals("ALIPAY")) {
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
                            editor.putInt("orderType", creatorder.getIsZeroPurchase());
                            editor.commit();
                        }
                    };
                    // 必须异步调用
                    Thread payThread = new Thread(payRunnable);
                    payThread.start();
                } else if (payType.equals("WEIXIN")) {
                    PayReq req = new PayReq();//PayReq就是订单信息对象
                    //给req对象赋值
                    req.appId = "wx38a0aef5df24fe50";
                    req.partnerId = "1528387611";
                    req.prepayId = creatorder.getPrepayId();
                    req.packageValue = "Sign=WXPay";
                    req.nonceStr = creatorder.getNonceStr();
                    req.timeStamp = creatorder.getTimeStamp();
                    req.sign = creatorder.getSign();
                    BaseApp.mWxApi.sendReq(req);//将订单信息对象发送给微信服务器，即发送支付请求
                    editor.putInt("orderType", creatorder.getIsZeroPurchase());
                    setOrderInfo(ConfirmActivity.this, creatorder);
                    editor.commit();
                }
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
                showDialog();
//
            }
        });
    }

    private boolean isdialog = false;

    @Override
    public void onBackPressed() {
        if (!isdialog) {
            showDialog();
        } else {
            dialog.dismiss();
        }
    }

    private void showDialog() {
        //dialog展示优惠券
        dialogview = getLayoutInflater().inflate(R.layout.confirm_dialog, null);
        TextView likai_btn = dialogview.findViewById(R.id.likai_btn);
        GradientDrawable grad = (GradientDrawable) likai_btn.getBackground();
        grad.setColor(Color.parseColor(getLogUser(this).getThemeColors()));
        TextView jixu_btn = dialogview.findViewById(R.id.jixu_btn);
        jixu_btn.setTextColor(Color.parseColor(getLogUser(this).getThemeColors()));
        likai_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent img_fragment_wo_daifukuan = new Intent(ConfirmActivity.this, OrderFormActivity.class);
                img_fragment_wo_daifukuan.putExtra("tab", "daifukuan");
                startActivity(img_fragment_wo_daifukuan);
                finish();
            }
        });
        jixu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog = new Dialog(ConfirmActivity.this, R.style.dialogWindowAnim);
        dialog.setContentView(dialogview, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        //设置显示动画
        window.setWindowAnimations(R.style.dialogWindowAnim);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = 0;
        //设置显示位置
        dialog.onWindowAttributesChanged(wl);
        //设置点击外围消散
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_confirm;
    }

    @Override
    protected void createPresenter() {

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
                            Intent intent = new Intent(ConfirmActivity.this, OrderFormActivity.class);
                            intent.putExtra("tab", "daishouhuo");
                            startActivity(intent);
                            finish();
                        } else if (sharedPreferences.getInt("orderType", 0) == 3) {
                            Intent intent = new Intent(ConfirmActivity.this, H5SecActivity.class);
                            intent.putExtra("title", "易康成杯乒乓球报名");
                            intent.putExtra("http", "https://www.yikch.com/mobile/appShow/tableTennisRegister?type=android&userId=" + getLogUser(BaseApp.getApp()).getId());
                            startActivity(intent);
                            finish();
                        } else if (sharedPreferences.getInt("orderType", 0) == 1) {
                            ToastUtils.show("您已参加评论返现购活动，收到货后请及时评价哦");
                        }
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            ToastUtils.show("支付结果确认中");
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Intent img_fragment_wo_daifukuan = new Intent(ConfirmActivity.this, OrderFormActivity.class);
                            img_fragment_wo_daifukuan.putExtra("tab", "daifukuan");
                            startActivity(img_fragment_wo_daifukuan);
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
