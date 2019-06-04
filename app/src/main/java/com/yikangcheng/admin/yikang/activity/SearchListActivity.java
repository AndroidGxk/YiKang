package com.yikangcheng.admin.yikang.activity;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.updater.Updater;
import com.yikangcheng.admin.yikang.updater.UpdaterConfig;
import com.yikangcheng.admin.yikang.util.UpdaterUtils;

public class SearchListActivity extends AppCompatActivity {
    private static final String APK_URL = "https://www.yikch.com/static/app/yikang.apk";

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);
        editText = (EditText) findViewById(R.id.et_download);
        editText.setText(APK_URL);
        try {
            String versionName = getVersionName();
            Toast.makeText(this, "" + versionName, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void download(View view) {
        String url = editText.getText().toString();
        if (TextUtils.isEmpty(editText.getText().toString())) {
            url = APK_URL;
        }
        UpdaterConfig config = new UpdaterConfig.Builder(this)
                .setTitle(getResources().getString(R.string.app_name))
                .setDescription("更新")
                .setFileUrl(url)
                .setCanMediaScanner(true)
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE
                        | DownloadManager.Request.NETWORK_WIFI)
                .build();
        Updater.get().showLog(true).download(config);
    }

    public void setting(View view) {
        //如果没有停用,先去停用,然后点击下载按钮. 测试用户关闭下载服务
        UpdaterUtils.showDownloadSetting(this);
    }

    private String getVersionName() throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        String version = packInfo.versionName;
        return version;
    }
}
