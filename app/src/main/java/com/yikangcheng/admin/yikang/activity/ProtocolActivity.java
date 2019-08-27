package com.yikangcheng.admin.yikang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;

import butterknife.BindView;

public class ProtocolActivity extends BaseActivtiy {
    @BindView(R.id.title_text)
    TextView title_text;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.back_img)
    ImageView back_img;
    @BindView(R.id.pb_progress)
    ProgressBar pbProgress;

    @Override
    protected void initView() {
        title_text.setText("用户注册协议");
    }

    @Override
    protected void initEventData() {
        webView.loadUrl("https://www.yikch.com/mobile/appShow/registerInfo?type=android");
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            //进度发生变化
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    pbProgress.setVisibility(View.GONE);
                } else {
                    // 加载中
                    pbProgress.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_h5_sec;
    }

    @Override
    protected void createPresenter() {

    }
}
