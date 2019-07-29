package com.yikangcheng.admin.yikang.activity.upush;

import android.os.Build;
import android.view.WindowManager;

import com.umeng.message.inapp.InAppMessageManager;
import com.umeng.message.inapp.UmengSplashMessageActivity;

public class SplashTestActivity extends UmengSplashMessageActivity {

    @Override
    public boolean onCustomPretreatment() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        InAppMessageManager mInAppMessageManager = InAppMessageManager.getInstance(this);
        //设置应用内消息为Debug模式
        mInAppMessageManager.setInAppMsgDebugMode(true);
        //参数为Activity的完整包路径，下面仅是示例代码，请按实际需求填写
        mInAppMessageManager.setMainActivityPath("com.yikangcheng.admin.yikang.activity.WelcomeActivity");
        return super.onCustomPretreatment();
    }
}
