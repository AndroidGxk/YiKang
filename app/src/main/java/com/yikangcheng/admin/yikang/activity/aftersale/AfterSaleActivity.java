package com.yikangcheng.admin.yikang.activity.aftersale;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Display;
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
import com.yikangcheng.admin.yikang.activity.BarterPayActivity;
import com.yikangcheng.admin.yikang.activity.PhotoActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.UserDetailBean;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.AfterSaleStatusPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.jessyan.autosize.internal.CustomAdapt;

public class AfterSaleActivity extends BaseActivtiy implements  ICoreInfe {


    /**
     * 换货页面
     */
    private WebView webView;
    private String phone;
    private String pass;

    //    private ImageView mImgActivityAftersaleFanhui;
//    private Toolbar mToolbarActivityMyaccount;
//    private int height;
    private int width;
    private int id;
    private String path;
    int count = 1;
    private String s = "";
    private UserDetailBean logUser;
    private String pwd;
    private long mobile;
    private AfterSaleStatusPresenter afterSaleStatusPresenter;

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

    //    private RelativeLayout rela;
    @SuppressLint("JavascriptInterface")
    @Override
    protected void initView() {
//        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        afterSaleStatusPresenter = new AfterSaleStatusPresenter(this);
        logUser = getUserInfo(this);
        Display display = this.getWindowManager().getDefaultDisplay();
        mobile = logUser.getMobile();
        width = display.getWidth();
        phone = "15210957177";
        pass = "111111";
        Intent intent = getIntent();
        SharedPreferences userId = getSharedPreferences("userInfo", MODE_PRIVATE);
        pwd = userId.getString("pwd", "");
        //商品流水Id
        id = intent.getIntExtra("id", 00);
        afterSaleStatusPresenter.request(logUser.getUserId(), id);
//        height = display.getHeight();
//        mImgActivityAftersaleFanhui = (ImageView) findViewById(R.id.img_activity_aftersale_fanhui);
//        mToolbarActivityMyaccount = (Toolbar) findViewById(R.id.toolbar_activity_myaccount);
//        rela = (RelativeLayout) findViewById(R.id.rela);
//
//        //ToolBar
//        mToolbarActivityMyaccount.setTitle("");
//        setSupportActionBar(mToolbarActivityMyaccount);
//        /**
//         * 点击返回图标关闭放前页面
//         */
//        mImgActivityAftersaleFanhui.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        rela.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(AfterSaleActivity.this, BrterActivity.class));
//            }
//        });
        webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true);
        // 在安卓5.0之后，默认不允许加载http与https混合内容，需要设置webview允许其加载混合网络协议内容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //自适应屏幕
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //设置Web视图
        //如果不设置WebViewClient，请求会跳转系统浏览器
        webView.canGoBack();
        webView.goBack();
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
    }


    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_after_sale;
    }

    @Override
    protected void createPresenter() {

    }



    @JavascriptInterface
    public void uploadChangeToAndroid() {
        startActivityForResult(new Intent(AfterSaleActivity.this, PhotoActivity.class), 1000);
    }

    @JavascriptInterface
    public void goBackAndroid(String msg) {
        finish();
    }

    @JavascriptInterface
    public void payMoneyToApp(String msg) {
        s += msg + ",";
        String[] split = s.split(",");
        if (split.length == 2) {
            Intent intent = new Intent(AfterSaleActivity.this, BarterPayActivity.class);
            String id = split[0];
            String money = split[1];
            intent.putExtra("id", id);
            intent.putExtra("money", money);
            startActivity(intent);
            s = "";
        }

    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        final String stitu = (String) request.getEntity();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
                if (url.toString().contains("sina.cn")) {
                    if (stitu.equals("0")) {
                        webView.loadUrl("https://www.yikch.com/mobile/afterSale/applyChange?detailsId=" + id + "&mobile=" + mobile + "&password=" + pwd + "&type=android");
                    } else {
                        webView.loadUrl("https://www.yikch.com/mobile/afterSale/lookSaleApply?detailsId=" + id + "&mobile=" + mobile + "&password=" + pwd + "&type=android");
                    }
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
                        if (stitu.equals("0")) {
                            webView.loadUrl("https://www.yikch.com/mobile/afterSale/applyChange?detailsId=" + id + "&mobile=" + mobile + "&password=" + pwd + "&type=android");
                        } else {
                            webView.loadUrl("https://www.yikch.com/mobile/afterSale/lookSaleApply?detailsId=" + id + "&mobile=" + mobile + "&password=" + pwd + "&type=android");
                        }
                        return true;
                    }
                }
                return false;
            }
        });
        if (stitu.equals("0")) {
            webView.loadUrl("https://www.yikch.com/mobile/afterSale/applyChange?detailsId=" + id + "&mobile=" + mobile + "&password=" + pwd + "&type=android");
        } else {
            webView.loadUrl("https://www.yikch.com/mobile/afterSale/lookSaleApply?detailsId=" + id + "&mobile=" + mobile + "&password=" + pwd + "&type=android");
        }
    }

    @Override
    public void fail(ApiException e) {

    }
}
