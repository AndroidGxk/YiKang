package com.yikangcheng.admin.yikang.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.aftersale.AfterSaleActivity;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.UserDetailBean;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.AfterSaleStatusPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.leefeng.promptlibrary.PromptDialog;

public class SeleGoodActivity extends BaseActivtiy implements ICoreInfe {

    //    private RelativeLayout rela, rela1;
    private int id;
    private AfterSaleStatusPresenter afterSaleStatusPresenter;
    private UserDetailBean logUser;
    private String pwd;
    private String mobile;
    //    private ImageView back_img;
    private WebView webView;
    private String s = "";
    private String path;
    int count = 1;
    private PromptDialog promptDialog;
    private RelativeLayout table;
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
            promptDialog.showLoading("正在上传");
            Log.e("Path", path);
            if (count == 1) {
                count++;
                webView.loadUrl("javascript:getImgSrc1('" + path + "')");
                promptDialog.showSuccess("上传成功");
            } else if (count == 2) {
                count++;
                webView.loadUrl("javascript:getImgSrc2('" + path + "')");
                promptDialog.showSuccess("上传成功");
            } else if (count == 3) {
                count++;
                webView.loadUrl("javascript:getImgSrc3('" + path + "')");
                promptDialog.showSuccess("上传成功");
            }else{
                promptDialog.dismiss();
            }
        }
    }
    @SuppressLint("JavascriptInterface")
    @Override
    protected void initView() {
        //创建对象
        promptDialog = new PromptDialog(this);
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);
        //设置状态栏颜色
        if (!getLogUser(this).getThemeColors().equals("")) {
            StatusBarUtil.setStatusBarMode(this, true, Color.parseColor(getLogUser(this).getThemeColors()));
        } else {
            StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        }
        table = (RelativeLayout) findViewById(R.id.table);
        table.setBackgroundColor(Color.parseColor(getLogUser(this).getThemeColors()));
        afterSaleStatusPresenter = new AfterSaleStatusPresenter(this);
        logUser = getUserInfo(this);
        mobile = logUser.getMobile();
        SharedPreferences userId = getSharedPreferences("userInfo", MODE_PRIVATE);
        pwd = userId.getString("pwd", "");
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 00);
//        //换货
//        rela = (RelativeLayout) findViewById(R.id.rela);
//        //退货
//        rela1 = (RelativeLayout) findViewById(R.id.rela1);
//        back_img = (ImageView) findViewById(R.id.back_img);
        afterSaleStatusPresenter.request(logUser.getUserId(), id);
        webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true);

        //自适应屏幕
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //设置Web视图
        //如果不设置WebViewClient，请求会跳转系统浏览器
        webView.canGoBack();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
                if (url.toString().contains("sina.cn")) {
                    webView.loadUrl("https://www.yikch.com/mobile/afterSale/toApply?detailsId=" + id + "&mobile=" + mobile + "&password=" + pwd + "&type=android&userId="+getLogUser(BaseApp.getApp()).getId());
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
                        webView.loadUrl("https://www.yikch.com/mobile/afterSale/toApply?detailsId=" + id + "&mobile=" + mobile + "&password=" + pwd + "&type=android&userId="+getLogUser(BaseApp.getApp()).getId());

                        return true;
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
        webView.setWebViewClient(new WebViewClient() {

            @Override
            // 在点击请求的是链接是才会调用，重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边。这个函数我们可以做很多操作，比如我们读取到某些特殊的URL，于是就可以不打开地址，取消这个操作，进行预先定义的其他操作，这对一个程序是非常必要的。
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 判断url链接中是否含有某个字段，如果有就执行指定的跳转（不执行跳转url链接），如果没有就加载url链接
                if (url.contains("order/againCourseOrder")) {
                    Intent i = new Intent(SeleGoodActivity.this, AfterSaleActivity.class);
                    startActivity(i);
                    return true;
                } else {
                    return false;
                }
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(this, "ww");
    }

    @Override
    protected void initEventData() {
//        back_img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//
//            }
//        });
//        //换货
//        rela.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SeleGoodActivity.this, AfterSaleActivity.class);
//                intent.putExtra("id", id);
//                startActivity(intent);
//            }
//        });
//        //退货
//        rela1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SeleGoodActivity.this, BarterActivity.class);
//                intent.putExtra("id", id);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_sele_good;
    }

    @Override
    protected void createPresenter() {

    }


    @Override
    public void success(Object data) {
        Request request = (Request) data;
        String entity = (String) request.getEntity();
        if (entity.equals("2")) {
//            Intent intent = new Intent(SeleGoodActivity.this, AfterSaleActivity.class);
//            intent.putExtra("id", id);
//            startActivity(intent);
//            finish();
//
            webView.loadUrl("https://www.yikch.com/mobile/afterSale/lookSaleApply?detailsId=" + id + "&mobile=" + mobile + "&password=" + pwd + "&type=android&userId="+getLogUser(BaseApp.getApp()).getId());
        } else {
            webView.loadUrl("https://www.yikch.com/mobile/afterSale/toApply?detailsId=" + id + "&mobile=" + mobile + "&password=" + pwd + "&type=android&userId="+getLogUser(BaseApp.getApp()).getId());
        }
    }

    @Override
    public void fail(ApiException e) {

    }

    @JavascriptInterface
    public void goBackAndroid(String msg) {
        finish();
    }

    @JavascriptInterface
    public void uploadChangeToAndroid() {
        startActivityForResult(new Intent(SeleGoodActivity.this, PhotoActivity.class), 1000);
    }


    @JavascriptInterface
    public void payMoneyToApp(String msg) {

        s += msg + ",";
        String[] split = s.split(",");
        if (split.length == 2) {
            Intent intent = new Intent(SeleGoodActivity.this, BarterPayActivity.class);
            String id = split[0];
            String money = split[1];
            intent.putExtra("id", id);
            intent.putExtra("money", money);
            startActivity(intent);
            s = "";
        }

    }

    @JavascriptInterface
    public void uploadRefundToAndroid() {
        startActivityForResult(new Intent(SeleGoodActivity.this, PhotoActivity.class), 1000);
    }
}
