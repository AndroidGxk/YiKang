package com.yikangcheng.admin.yikang.wxapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.H5SecActivity;
import com.yikangcheng.admin.yikang.activity.OrderFormActivity;
import com.yikangcheng.admin.yikang.activity.orderstatus.newactivity.OrderWaitActivity;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;

/**
 * 微信回调
 */
public class WXPayEntryActivity extends BaseActivtiy implements IWXAPIEventHandler {


    private IWXAPI api;
    private ImageView back_img, img;
    private TextView text;
    private SharedPreferences sharedPreferences;

    @Override
    protected void initView() {
        back_img = (ImageView) findViewById(R.id.back_img);
        img = (ImageView) findViewById(R.id.imgs);
        text = (TextView) findViewById(R.id.texts);
        api = WXAPIFactory.createWXAPI(this, "wx38a0aef5df24fe50");
        api.handleIntent(getIntent(), this);
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.pay_result;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);

    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

            Intent img_fragment_wo_daifukuan = new Intent(WXPayEntryActivity.this, OrderFormActivity.class);
            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    if (getOrderInfo(BaseApp.getApp()) == null) {
                        if (getOrderInfo(BaseApp.getApp()).getIsZeroPurchase() == 2) {
                            Intent intent = new Intent(WXPayEntryActivity.this, OrderFormActivity.class);
                            intent.putExtra("tab", "daishouhuo");
                            startActivity(intent);
                            finish();
                        } else if (getOrderInfo(BaseApp.getApp()).getIsZeroPurchase() == 3) {
                            Intent intent = new Intent(WXPayEntryActivity.this, H5SecActivity.class);
                            intent.putExtra("title", "易康成杯乒乓球报名");
                            intent.putExtra("http", "https://www.yikch.com/mobile/appShow/tableTennisRegister?type=android&userId=" + getLogUser(BaseApp.getApp()).getId());
                            startActivity(intent);
                            finish();
                        } else if (getOrderInfo(BaseApp.getApp()).getIsZeroPurchase() == 1) {
                            ToastUtils.show("您已参加评论返现购活动，收到货后请及时评价哦");
                            Intent intent = new Intent(WXPayEntryActivity.this, OrderFormActivity.class);
                            intent.putExtra("tab", "daishouhuo");
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        if (getOrderInfo(BaseApp.getApp()).getIsZeroPurchase() == 2) {
                            Intent intent = new Intent(WXPayEntryActivity.this, OrderFormActivity.class);
                            intent.putExtra("tab", "daishouhuo");
                            startActivity(intent);
                            finish();
                        } else if (getOrderInfo(BaseApp.getApp()).getIsZeroPurchase() == 3) {
                            Intent intent = new Intent(WXPayEntryActivity.this, H5SecActivity.class);
                            intent.putExtra("title", "易康成杯乒乓球报名");
                            intent.putExtra("http", "https://www.yikch.com/mobile/appShow/tableTennisRegister?type=android&userId=" + getLogUser(BaseApp.getApp()).getId());
                            startActivity(intent);
                            finish();
                        } else if (getOrderInfo(BaseApp.getApp()).getIsZeroPurchase() == 1) {
                            ToastUtils.show("您已参加评论返现购活动，收到货后请及时评价哦");
                            Intent intent = new Intent(WXPayEntryActivity.this, OrderFormActivity.class);
                            intent.putExtra("tab", "daishouhuo");
                            startActivity(intent);
                            finish();
                        }
                    }
                    getDeleteOrderInfo(BaseApp.getApp());
                    getDeleteNewPayInfo(BaseApp.getApp());
//                    //支付成功后的逻辑
//                    img_fragment_wo_daifukuan.putExtra("tab", "daishouhuo");
//                    startActivity(img_fragment_wo_daifukuan);
//                    finish();
                    break;
                case BaseResp.ErrCode.ERR_COMM:
                    img.setBackgroundResource(R.drawable.pay_error);
                    if (getOrderInfo(BaseApp.getApp()) == null) {
                        text.setText("支付失败");
                    } else {
                        text.setText("支付失败");
                    }
                    getDeleteOrderInfo(BaseApp.getApp());
                    getDeleteNewPayInfo(BaseApp.getApp());
//                    if (sharedPreferences.getInt("orderType", 0) == 2) {
//                        Intent intent = new Intent(WXPayEntryActivity.this, OrderFormActivity.class);
//                        intent.putExtra("tab", "daishouhuo");
//                        startActivity(intent);
//                        finish();
//                        ToastUtils.show("普通订单");
//                    } else if (sharedPreferences.getInt("orderType", 0) == 3) {
//                        Intent intent = new Intent(WXPayEntryActivity.this, H5SecActivity.class);
//                        intent.putExtra("title","易康成杯乒乓球报名");
//                        intent.putExtra("http", "https://www.yikch.com/mobile/appShow/tableTennisRegister?type=android&userId=" + getLogUser(BaseApp.getApp()).getId());
//                        startActivity(intent);
//                        ToastUtils.show("乒乓球订单");
//                        finish();
//                    }else if(sharedPreferences.getInt("orderType", 0) == 1){
//                        ToastUtils.show("您已参加评论返现购活动，收到货后请及时评价哦");
//                    }
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    img_fragment_wo_daifukuan.putExtra("tab", "daifukuan");
                    startActivity(img_fragment_wo_daifukuan);
                    finish();
                    getDeleteOrderInfo(BaseApp.getApp());
                    getDeleteNewPayInfo(BaseApp.getApp());
                    break;
                default:
                    break;
            }
        }
    }

}
