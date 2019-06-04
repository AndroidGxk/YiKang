package com.yikangcheng.admin.yikang.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yikangcheng.admin.yikang.R;

import me.jessyan.autosize.utils.LogUtils;

/**
 * @author happy_movie
 * @date 2019/1/27 11:35
 * QQ:45198565
 * 佛曰：永无BUG 盘他！
 */
public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {


    private IWXAPI api;

    private TextView payResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_result);
        LogUtils.e("com.bw.movie.wxapi包哈哈哈啊");
        api = WXAPIFactory.createWXAPI(this, "wx49c4b23b233d97ff");
        api.handleIntent(getIntent(), this);


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
            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    //支付成功后的逻辑
                    break;
                case BaseResp.ErrCode.ERR_COMM:
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    break;
                default:
                    break;
            }
        }
    }

}
