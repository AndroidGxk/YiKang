package com.yikangcheng.admin.yikang.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.jessyan.autosize.internal.CustomAdapt;

public class ApoutUsActivity extends BaseActivtiy implements CustomAdapt {


    private ImageView back_img;
    private RelativeLayout mToolbarActivityMyaccount;
    private int height;
    private int width;
    private String versionName;
    private TextView versions_text;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        Display display = this.getWindowManager().getDefaultDisplay();
        height = display.getHeight();
        width = display.getWidth();
        back_img = (ImageView) findViewById(R.id.back_img);
        versions_text = (TextView) findViewById(R.id.versions_text);
        mToolbarActivityMyaccount = (RelativeLayout) findViewById(R.id.activity_myaccount);
        try {
            versionName = getVersionName();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        versions_text.setText("当前版本号：" + versionName);
    }

    //查询当前App版本号
    private String getVersionName() throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        String version = packInfo.versionName;
        return version;
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_apout_us;
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
        return width / 2;
    }
}
