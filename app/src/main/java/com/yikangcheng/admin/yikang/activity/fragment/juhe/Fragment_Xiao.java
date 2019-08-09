package com.yikangcheng.admin.yikang.activity.fragment.juhe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.H5SecActivity;
import com.yikangcheng.admin.yikang.activity.adapter.shougang.ShouGangXiaoAdapter;
import com.yikangcheng.admin.yikang.base.BaseFragment;

import butterknife.BindView;

/**
 * 作者：古祥坤 on 2019/7/10 15:04
 * 邮箱：1724959985@qq.com
 */
public class Fragment_Xiao extends BaseFragment {
    @BindView(R.id.mess_recycler)
    RecyclerView mess_recycler;
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.pb_progress)
    ProgressBar pbProgress;

    @Override
    protected void initView(View view) {
        mess_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mess_recycler.setAdapter(new ShouGangXiaoAdapter());
    }

    @SuppressLint("JavascriptInterface")
    @Override
    protected void initData() {
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
                    webView.loadUrl("https://www.yikch.com/mobile/appShow/systemList?userId=" + getLogUser(getContext()).getId() + "&type=android");
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
                        webView.loadUrl("https://www.yikch.com/mobile/appShow/systemList?userId=" + getLogUser(getContext()).getId() + "&type=android");
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
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(this, "ww");
        webView.loadUrl("https://www.yikch.com/mobile/appShow/newsList?userId=" + getLogUser(getContext()).getId() + "&type=android");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            // 在点击请求的是链接是才会调用，重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边。这个函数我们可以做很多操作，比如我们读取到某些特殊的URL，于是就可以不打开地址，取消这个操作，进行预先定义的其他操作，这对一个程序是非常必要的。
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 判断url链接中是否含有某个字段，如果有就执行指定的跳转（不执行跳转url链接），如果没有就加载url链接
                if (url.contains("/appShow/logisticsList")) {
                    //物流消息
                    Intent intent = new Intent(getActivity(), H5SecActivity.class);
                    intent.putExtra("http", url);
                    intent.putExtra("title", "物流消息");
//                    intent.putExtra("color", "bai");
                    startActivity(intent);
                    return true;
                } else if (url.contains("/appShow/systemList")) {
                    //系统消息
                    Intent intent = new Intent(getActivity(), H5SecActivity.class);
                    intent.putExtra("http", url);
                    intent.putExtra("title", "系统消息");
//                    intent.putExtra("color", "bai");
                    startActivity(intent);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    @JavascriptInterface
    public void zhiCustomBtn() {
        Information info = new Information();
        info.setAppkey("7560599b63bf43378d05d018ded42cdd");
        SobotApi.setCustomRobotHelloWord(getActivity(), "您好，易康成客服很高兴为您服务，请问有什么可以帮助您的？");
        SobotApi.startSobotChat(getActivity(), info);
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_ji_xiao;
    }
}
