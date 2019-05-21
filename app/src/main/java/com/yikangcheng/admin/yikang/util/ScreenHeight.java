package com.yikangcheng.admin.yikang.util;

import android.content.Context;
import android.view.WindowManager;

import com.yikangcheng.admin.yikang.app.BaseApp;

/**
 * 作者：古祥坤 on 2019/5/21 11:22
 * 邮箱：1724959985@qq.com
 */
public class ScreenHeight {
    public static int getHeight() {
        Context context = BaseApp.getForegroundActivity();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }
}
