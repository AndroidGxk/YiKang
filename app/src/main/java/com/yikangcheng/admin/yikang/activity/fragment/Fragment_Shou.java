package com.yikangcheng.admin.yikang.activity.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.CloseActivity;
import com.yikangcheng.admin.yikang.activity.H5SecActivity;
import com.yikangcheng.admin.yikang.activity.PartiCarActivity;
import com.yikangcheng.admin.yikang.activity.SeekListNewActivity;
import com.yikangcheng.admin.yikang.activity.SeleGoodActivity;
import com.yikangcheng.admin.yikang.activity.aftersale.AfterSaleActivity;
import com.yikangcheng.admin.yikang.activity.h5activity.InsideActivity;
import com.yikangcheng.admin.yikang.activity.particulars.ParticularsActivity;
import com.yikangcheng.admin.yikang.activity.seek.SeckillActivity;
import com.yikangcheng.admin.yikang.activity.seek.SeekActivity;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


public class Fragment_Shou extends BaseFragment implements ICoreInfe {


    private static WebView webView;
    private String s;
    private SmartRefreshLayout refreshLayout;
    private ProgressBar pbProgress;
    private ImageView imgBut;
    private TextView text_seek;

    @SuppressLint("NewApi")
    @Override
    protected void initView(View view) {
        webView = view.findViewById(R.id.webview);
        //返回顶部按钮
        imgBut = view.findViewById(R.id.imgBut);
        //进度条
        pbProgress = view.findViewById(R.id.pb_progress);
        //加载刷新
        refreshLayout = view.findViewById(R.id.refreshLayout);
        text_seek = view.findViewById(R.id.text_seek);
        refreshLayout.setEnableLoadmore(false);
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
                    webView.loadUrl("https://www.yikch.com/mobile/appShow/index?type=android");
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
                        webView.loadUrl("https://www.yikch.com/mobile/appShow/index?type=android");
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
        webView.loadUrl("https://www.yikch.com/mobile/appShow/index?type=android");
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                String url = webView.getUrl();
                webView.loadUrl(url);
                refreshLayout.finishRefresh();
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
        webView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if (i1 >= 100) {
                    imgBut.setVisibility(View.VISIBLE);
                } else {
                    imgBut.setVisibility(View.GONE);
                }
            }
        });
        imgBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.scrollTo(0, 0);
            }
        });
        text_seek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SeekActivity.class));
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            // 在点击请求的是链接是才会调用，重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边。这个函数我们可以做很多操作，比如我们读取到某些特殊的URL，于是就可以不打开地址，取消这个操作，进行预先定义的其他操作，这对一个程序是非常必要的。
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 判断url链接中是否含有某个字段，如果有就执行指定的跳转（不执行跳转url链接），如果没有就加载url链接
                if (url.contains("/appShow/proDetail")) {
                    Intent intent = new Intent(getActivity(), ParticularsActivity.class);
                    intent.putExtra("id", url);
                    startActivity(intent);
                    return true;
                } else if (url.contains("/appShow/recommendList")) {
                    Intent intent = new Intent(getContext(), H5SecActivity.class);
                    intent.putExtra("http", url);
                    startActivity(intent);
                    return true;
                } else if (url.contains("appShow/proSomeList")) {
                    try {
                        //URL中文乱码转换
                        String decode = URLDecoder.decode(new String(url.getBytes("ISO-8859-1"), "UTF-8"), "UTF-8");
                        String reg = "[^\u4e00-\u9fa5]";
                        decode = decode.replaceAll(reg, "");
                        Intent intent1 = new Intent(getContext(), SeekListNewActivity.class);
                        intent1.putExtra("count", decode);
                        startActivity(intent1);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    return true;
                } else if (url.contains("appShow/advertorial")) {
                    Intent intent = new Intent(getContext(), H5SecActivity.class);
                    intent.putExtra("http", url);
                    startActivity(intent);
                    return true;
                } else if (url.contains("appShow/activity")) {
                    Intent intent = new Intent(getContext(), H5SecActivity.class);
                    intent.putExtra("http", url);
                    startActivity(intent);
                    return true;
                } else if (url.contains("appShow/majorcredit")) {
                    Intent intent = new Intent(getContext(), H5SecActivity.class);
                    intent.putExtra("http", url);
                    startActivity(intent);
                    return true;
                } else if (url.contains("appShow/sixoneeight")) {
                    Intent intent = new Intent(getContext(), H5SecActivity.class);
                    intent.putExtra("http", url);
                    startActivity(intent);
                    return true;
                } else if (url.contains("appShow/internalActivity")) {
                    Intent intent = new Intent(getContext(), InsideActivity.class);
                    intent.putExtra("http", url);
                    startActivity(intent);
                    return true;
                } else if (url.contains("mobile/appShow")) {
                    if (url.contains("market")) {
                        Intent intent = new Intent(getContext(), H5SecActivity.class);
                        intent.putExtra("http", url);
                        intent.putExtra("title", "挚友超市");
                        startActivity(intent);
                        return true;
                    } else if (url.contains("xiazhi")) {
                        Intent intent = new Intent(getContext(), H5SecActivity.class);
                        intent.putExtra("http", url);
                        intent.putExtra("title", "夏至福利");
                        startActivity(intent);
                        return true;
                    }
                    Intent intent = new Intent(getContext(), H5SecActivity.class);
                    intent.putExtra("http", url);
                    startActivity(intent);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }


    @Override
    protected void initData() {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_shou;
    }


    @JavascriptInterface
    public void saySomeMenu(String msg) {
        Intent intent = new Intent(getContext(), SeekListNewActivity.class);
        int i = Integer.parseInt(msg);
        intent.putExtra("id", i);
        startActivity(intent);
    }

    @JavascriptInterface
    public void sayHello(String msg) {
//        s += msg + ",";
//        String[] split = s.split(",");
//        if (split.length == 4) {
//            int id = getLogUser(getContext()).getId();
//            addShopPresenter.request(id, split[0], split[1], split[2], split[3]);
//            s = "";
//        }
    }

    @JavascriptInterface
    public void orderBuy(String msg) {
        s += msg + ",";
        String[] split = s.split(",");
        if (split.length == 7) {
            Intent intent = new Intent(getContext(), CloseActivity.class);
            intent.putExtra("goodinfo", s);
            startActivity(intent);
            s = "";
        }
    }

    @JavascriptInterface
    public void goBackIndex(String msg) {
        Intent intent = new Intent(getContext(), H5SecActivity.class);
        intent.putExtra("http", msg);
        startActivity(intent);
    }

    @JavascriptInterface
    public void goCust() {
        Information info = new Information();
        info.setAppkey("7560599b63bf43378d05d018ded42cdd");
        SobotApi.setCustomRobotHelloWord(getActivity(), "您好，易康成客服很高兴为您服务，请问有什么可以帮助您的？");
        SobotApi.startSobotChat(getActivity(), info);
    }

//
//    @JavascriptInterface
//    public void partID(String msg) {
//        if (msg.indexOf(".json") != -1) {
//            String[] split = msg.split(".json");
//            Intent intent = new Intent(getContext(), ParticularsActivity.class);
//            int id = Integer.parseInt(split[0]);
//            intent.putExtra("id", id);
//            startActivity(intent);
//        } else {
//            Intent intent = new Intent(getContext(), ParticularsActivity.class);
//            int id = Integer.parseInt(msg);
//            intent.putExtra("id", id);
//            startActivity(intent);
//        }
//    }

    public static void getGoBack() {
        if (webView != null) {
            webView.goBack();
        }
    }

    @JavascriptInterface
    public void goCar() {
        startActivity(new Intent(getContext(), PartiCarActivity.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        webView.setVisibility(View.VISIBLE);
    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        Toast.makeText(getContext(), "" + request.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fail(ApiException e) {

    }
}
