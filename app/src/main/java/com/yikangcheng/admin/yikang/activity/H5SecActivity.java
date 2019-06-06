package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Shou;
import com.yikangcheng.admin.yikang.activity.particulars.ParticularsActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.jessyan.autosize.internal.CustomAdapt;

public class H5SecActivity extends BaseActivtiy implements CustomAdapt {

    private static WebView webview;
    private String http;
    private ImageView back_img;
    private ProgressBar pbProgress;
    private SmartRefreshLayout refreshLayout;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorTab);
        //进度条
        pbProgress = (ProgressBar) findViewById(R.id.pb_progress);
        webview = (WebView) findViewById(R.id.webView);
        back_img = (ImageView) findViewById(R.id.back_img);
        //加载刷新
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setEnableLoadMore(false);
        Fragment_Shou.getGoBack();
        Intent intent = getIntent();
        http = intent.getStringExtra("http");
        WebSettings webSettings = webview.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //扩大比例的缩放
        webview.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //设置Web视图
        //如果不设置WebViewClient，请求会跳转系统浏览器
        webview.canGoBack();
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
                if (url.toString().contains("sina.cn")) {
                    webview.loadUrl(http);
                    return true;
                }
                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (request.getUrl().toString().contains("sina.cn")) {
                        webview.loadUrl(http);
                        return true;
                    }
                }
                return false;
            }
        });
        webview.setWebChromeClient(new WebChromeClient() {
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
        webview.getSettings().setJavaScriptEnabled(true);
        webview.addJavascriptInterface(this, "ww");
        webview.loadUrl(http);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                webview.loadUrl(http);
                refreshLayout.finishRefresh();
            }
        });
    }

    @Override
    protected void initEventData() {
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 如果还有页面需要这样你就复制这些代码就可以了
                 */
                if (webview.canGoBack()) {
                    webview.goBack();
                } else {
                    finish();
                }
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

    @JavascriptInterface
    public void partID(String msg) {
        if (msg.indexOf(".json") != -1) {
            String[] split = msg.split(".json");
            Intent intent = new Intent(H5SecActivity.this, ParticularsActivity.class);
            int id = Integer.parseInt(split[0]);
            intent.putExtra("id", id);
            intent.putExtra("h5", "true");
            startActivity(intent);
        } else {
            Intent intent = new Intent(H5SecActivity.this, ParticularsActivity.class);
            int id = Integer.parseInt(msg);
            intent.putExtra("id", id);
            intent.putExtra("h5", "true");
            startActivity(intent);
        }
    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    public static void getGoBack() {
        webview.goBack();
    }

    @Override
    public float getSizeInDp() {
        return 0;
    }
}
