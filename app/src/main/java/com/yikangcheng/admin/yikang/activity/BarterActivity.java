package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.UserDetailBean;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

/**
 * 退货
 */
public class BarterActivity extends BaseActivtiy {
    private String path;
    int count = 1;
    private WebView webView;
    private String pwd;
    private String mobile;
    private int id;
    private UserDetailBean logUser;

    /**
     * 回传值
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == 1001) {
            path = data.getStringExtra("path");
            Log.e("Path", path);
            if (count == 1) {
                count++;
                webView.loadUrl("javascript:getImgSrc1('" + path + "')");
            } else if (count == 2) {
                count++;
                webView.loadUrl("javascript:getImgSrc2('" + path + "')");
            } else if (count == 3) {
                count++;
                webView.loadUrl("javascript:getImgSrc3('" + path + "')");
            }
        }
    }

    @Override
    protected void initView() {
        //设置状态栏颜色
        if (!getLogUser(this).getThemeColors().equals("")) {
            StatusBarUtil.setStatusBarMode(this, true, Color.parseColor(getLogUser(this).getThemeColors()));
        } else {
            StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        }
        webView = (WebView) findViewById(R.id.webView);
        Intent intent = getIntent();
        SharedPreferences userId = getSharedPreferences("userInfo", MODE_PRIVATE);
        pwd = userId.getString("pwd", "");
        logUser = getUserInfo(this);
        mobile = logUser.getMobile();
        //商品流水Id
        id = intent.getIntExtra("id", 00);
        WebSettings webSettings = webView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        // 在安卓5.0之后，默认不允许加载http与https混合内容，需要设置webview允许其加载混合网络协议内容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //设置Web视图
        //如果不设置WebViewClient，请求会跳转系统浏览器
        webView.canGoBack();
        webView.goBack();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
                if (url.toString().contains("sina.cn")) {
                    webView.loadUrl("https://www.yikch.com/mobile/afterSale/applyRefund?detailsId=" + id + "&mobile=" + mobile + "&password=" + pwd + "&type=android&userId="+getLogUser(BarterActivity.this).getId());
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
                        webView.loadUrl("https://www.yikch.com/mobile/afterSale/applyRefund?detailsId=" + id + "&mobile=" + mobile + "&password=" + pwd + "&type=android&userId="+getLogUser(BarterActivity.this).getId());

                        return true;
                    }
                }
                return false;
            }
        });
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //这event.getAction() == KeyEvent.ACTION_DOWN表示是返回键事件   
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {//表示按返回键 时的操作  
                        webView.goBack();//后退    
                        return true;//已处理     返回true表示被处理否则返回false    
                    }
                }
                return false;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(this, "ww");
        webView.loadUrl("https://www.yikch.com/mobile/afterSale/applyRefund?detailsId=" + id + "&mobile=" + mobile + "&password=" + pwd + "&type=android&userId="+getLogUser(BarterActivity.this).getId());
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_barter;
    }

    @Override
    protected void createPresenter() {

    }

    @JavascriptInterface
    public void goBackAndroid(String msg) {
        finish();
    }
    @JavascriptInterface
    public void uploadRefundToAndroid() {
        startActivityForResult(new Intent(BarterActivity.this, PhotoActivity.class), 1000);
    }
}
