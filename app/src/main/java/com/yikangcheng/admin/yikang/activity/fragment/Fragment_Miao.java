package com.yikangcheng.admin.yikang.activity.fragment;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import android.widget.Button;
import android.widget.Toast;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseFragment;

public class Fragment_Miao extends BaseFragment {
    private WebView webView;
    private String s;
    //    private ImageView miao_one_btn;
//    private RecyclerView recycler_one;
//    private FirstSeckillRecyclerAdapter firstSeckillRecyclerAdapter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("JavascriptInterface")
    @Override
    protected void initView(View view) {

//        miao_one_btn = view.findViewById(R.id.miao_one_btn);
//        recycler_one = view.findViewById(R.id.recycler_one);
//        firstSeckillRecyclerAdapter = new FirstSeckillRecyclerAdapter();
        webView = view.findViewById(R.id.webView);
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
                    webView.loadUrl("http://192.168.0.143/mobile/appShow/yikang/proDetailAndroid/3602.json?type=android");
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
                        webView.loadUrl("http://192.168.0.143/mobile/appShow/yikang/proDetailAndroid/3602.json?type=android");
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
        webView.loadUrl("http://192.168.0.143/mobile/appShow/yikang/proDetailAndroid/3602.json?type=android");
    }

    @Override
    protected void initData() {
//        miao_one_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getContext(), SeckillSecondActivity.class));
//            }
//        });
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recycler_one.setLayoutManager(layoutManager);
//        recycler_one.setAdapter(firstSeckillRecyclerAdapter);
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_miao;
    }

    @JavascriptInterface
    public void sayHello(String msg) {
        Log.v("gxk", "JSInferface--" + msg);
        s += msg;
        Toast.makeText(getContext(), "" + s, Toast.LENGTH_SHORT).show();
    }
}
