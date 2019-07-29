package com.yikangcheng.admin.yikang.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.hjq.toast.ToastUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.logging.LogManager;
/** 微信客户端回调activity示例 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;
    @Override  
    protected void onCreate(Bundle savedInstanceState) {
        api = WXAPIFactory.createWXAPI(this, "wx38a0aef5df24fe50", false);
        api.handleIntent(getIntent(), this);  
        super.onCreate(savedInstanceState);  
    }  
    @Override  
    public void onReq(BaseReq arg0) { }
  
    @Override  
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
        case BaseResp.ErrCode.ERR_OK:
            Log.d("GTT","分享成功");
            //分享成功
            break;  
        case BaseResp.ErrCode.ERR_USER_CANCEL:  
            //分享取消  
            Log.d("GTT","分享取消");
            break;
        case BaseResp.ErrCode.ERR_AUTH_DENIED:  
            //分享拒绝  
            Log.d("GTT","分享拒绝");
            break;
        }  
    }  
}