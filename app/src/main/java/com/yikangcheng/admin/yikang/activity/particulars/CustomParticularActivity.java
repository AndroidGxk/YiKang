package com.yikangcheng.admin.yikang.activity.particulars;

import android.os.Build;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.widget.ItemListView;

public class CustomParticularActivity extends BaseActivtiy implements View.OnClickListener {

    private WebView webView;
    private TextView tv_goods_config, tv_goods_detail;
    private LinearLayout config_line;

    @Override
    protected void initView() {
        webView = (WebView) findViewById(R.id.wv_detail);
        config_line = (LinearLayout) findViewById(R.id.config_line);
        tv_goods_config = (TextView) findViewById(R.id.tv_goods_config);
        tv_goods_config.setOnClickListener(this);
        tv_goods_detail = (TextView) findViewById(R.id.tv_goods_detail);
        tv_goods_detail.setOnClickListener(this);
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
        webView.goBack();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
                if (url.toString().contains("sina.cn")) {
                    webView.loadUrl("https://www.yikch.com/mobile/yikang/proDetail/5372");
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
                        webView.loadUrl("https://www.yikch.com/mobile/yikang/proDetail/5372");
                        return true;
                    }
                }
                return false;
            }
        });
        webView.loadUrl("https://www.yikch.com/mobile/yikang/proDetail/5372");
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_custom_particular;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //商品介绍
            case R.id.tv_goods_detail:
                webView.setVisibility(View.VISIBLE);
                config_line.setVisibility(View.GONE);
                break;
            //商品规格
            case R.id.tv_goods_config:
                webView.setVisibility(View.GONE);
                config_line.setVisibility(View.VISIBLE);
                break;
        }
    }
}
