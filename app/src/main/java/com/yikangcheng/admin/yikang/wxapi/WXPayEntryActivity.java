package com.yikangcheng.admin.yikang.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.OrderFormActivity;

/**
 * 微信回调
 */
public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {


    private IWXAPI api;
    private ImageView back_img, img;
    private TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        back_img = findViewById(R.id.back_img);
        img = findViewById(R.id.imgs);
        text = findViewById(R.id.texts);
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
                    //支付成功后的逻辑
                    img_fragment_wo_daifukuan.putExtra("tab", "daishouhuo");
                    startActivity(img_fragment_wo_daifukuan);
                    finish();
                    break;
                case BaseResp.ErrCode.ERR_COMM:
                    img.setBackgroundResource(R.drawable.pay_error);
                    text.setText("支付失败");
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    img_fragment_wo_daifukuan.putExtra("tab", "daifukuan");
                    startActivity(img_fragment_wo_daifukuan);
                    finish();
                    break;
                default:
                    break;
            }
        }
    }

}
