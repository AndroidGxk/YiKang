package com.yikangcheng.admin.yikang.activity.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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

import com.bumptech.glide.Glide;
import com.hjq.toast.ToastUtils;
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
import com.yikangcheng.admin.yikang.activity.coupon.CouponActivity;
import com.yikangcheng.admin.yikang.activity.h5activity.H5DiscountActivity;
import com.yikangcheng.admin.yikang.activity.h5activity.H5MessActivity;
import com.yikangcheng.admin.yikang.activity.h5activity.H5RecommendListActivity;
import com.yikangcheng.admin.yikang.activity.h5activity.InsideActivity;
import com.yikangcheng.admin.yikang.activity.particulars.ParticularsActivity;
import com.yikangcheng.admin.yikang.activity.seek.SeekActivity;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import me.leefeng.promptlibrary.PromptDialog;


public class Fragment_Shou extends BaseFragment implements ICoreInfe {

    private ImageView imageView;
    private ImageView quxiao;
    private ImageView shouye;
    private ImageView mess_img;
    private ImageView chakan;
    private View dialogview;
    private Dialog dialog;
    private static WebView webView;
    private String s;
    private SmartRefreshLayout refreshLayout;
    private ProgressBar pbProgress;
    private ImageView imgBut;
    private ImageView back_img;
    private TextView text_seek;
    private PromptDialog promptDialog;
    //dialog展示
    private int countDialog = 0;


    @SuppressLint("NewApi")
    @Override
    protected void initView(View view) {
        mess_img = view.findViewById(R.id.mess_img);
        //创建对象
        promptDialog = new PromptDialog(getActivity());
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);
        promptDialog.showLoading("加载中...");
        webView = view.findViewById(R.id.webview);
        //返回顶部按钮
        imgBut = view.findViewById(R.id.imgBut);
        //进度条
        pbProgress = view.findViewById(R.id.pb_progress);
        //加载刷新
        refreshLayout = view.findViewById(R.id.refreshLayout);
        text_seek = view.findViewById(R.id.text_seek);
        back_img = view.findViewById(R.id.back_img);
        refreshLayout.setEnableLoadmore(false);
        WebSettings webSettings = webView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
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
                    promptDialog.dismissImmediately();
                    if (countDialog == 0) {
                        SharedPreferences sp = BaseApp.getApp().getSharedPreferences("activity", Context.MODE_PRIVATE);
                        if (sp != null) {
                            String str = sp.getString("acti", "");
                            if (str.equals("register")) {
                                showDialog();
                            }
                            countDialog++;
                        }
                    }
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
        // 在安卓5.0之后，默认不允许加载http与https混合内容，需要设置webview允许其加载混合网络协议内容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
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
        mess_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), H5MessActivity.class));
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            // 在点击请求的是链接是才会调用，重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边。这个函数我们可以做很多操作，比如我们读取到某些特殊的URL，于是就可以不打开地址，取消这个操作，进行预先定义的其他操作，这对一个程序是非常必要的。
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 判断url链接中是否含有某个字段，如果有就执行指定的跳转（不执行跳转url链接），如果没有就加载url链接
                if (url.contains("/appShow/proDetail")) {
                    //商品详情链接
                    Intent intent = new Intent(getActivity(), ParticularsActivity.class);
                    intent.putExtra("id", url + "&userId=" + getLogUser(getContext()).getId());
                    startActivity(intent);
                    return true;
                } else if (url.contains("appShow/yousheng")) {
                    //内部购
                    Intent intent = new Intent(getContext(), H5SecActivity.class);
                    intent.putExtra("http", url);
                    intent.putExtra("title", "优胜教育内部购");
                    startActivity(intent);
                    return true;
                } else if (url.contains("/appShow/recommendList")) {
                    //精选活动大图链接
                    Intent intent = new Intent(getContext(), H5RecommendListActivity.class);
                    intent.putExtra("http", url);
                    startActivity(intent);
                    return true;
                } else if (url.contains("appShow/zeroPurchase")) {
                    /**
                     * 签到页面
                     */
                    Intent intent = new Intent(getContext(), H5SecActivity.class);
                    intent.putExtra("http", "https://www.yikch.com/mobile/appShow/zeroPurchase?type=android");
                    intent.putExtra("title", "专场0元购");
                    startActivity(intent);
                    return true;
                } else if (url.contains("queryCourse.name")) {
                    //找相似链接
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
                    //轮播图链接
                    Intent intent = new Intent(getContext(), H5SecActivity.class);
                    intent.putExtra("http", url);
                    startActivity(intent);
                    return true;
                } else if (url.contains("appShow/activity")) {
                    if (url.contains("appShow/activity1")) {
                        Intent intent = new Intent(getContext(), H5SecActivity.class);
                        intent.putExtra("http", url);
                        intent.putExtra("title", "每日精品");
                        startActivity(intent);
                    } else if (url.contains("appShow/activity2")) {
                        Intent intent = new Intent(getContext(), H5SecActivity.class);
                        intent.putExtra("http", url);
                        intent.putExtra("title", "狠货推荐");
                        startActivity(intent);
                    } else if (url.contains("appShow/activity3")) {
                        Intent intent = new Intent(getContext(), H5SecActivity.class);
                        intent.putExtra("http", url);
                        intent.putExtra("title", "企业福利");
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getContext(), H5SecActivity.class);
                        intent.putExtra("http", url);
                        intent.putExtra("title", "周秒杀");
                        startActivity(intent);
                    }
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
                    } else if (url.contains("mobile/appShow/aixiaoxin")) {
                        //抢购链接
                        Intent intent = new Intent(getContext(), H5SecActivity.class);
                        intent.putExtra("http", url);
                        intent.putExtra("title", "爱小心内购");
                        startActivity(intent);
                        return true;
                    } else if (url.contains("superToday")) {
                        //抢购链接
                        Intent intent = new Intent(getContext(), H5SecActivity.class);
                        intent.putExtra("http", url);
                        intent.putExtra("none", "yes");
                        startActivity(intent);
                        return true;
                    } else if (url.contains("discounts")) {
                        //优惠券链接
                        Intent intent = new Intent(getContext(), H5DiscountActivity.class);
                        intent.putExtra("http", url);
                        Log.d("GTTT", url);
                        intent.putExtra("none", "yes");
                        startActivity(intent);
                        return true;
                    }
                    return false;
                } else if (url.contains("mobile/login")) {
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    private void showDialog() {
        //dialog展示优惠券
        dialogview = getLayoutInflater().inflate(R.layout.three_dialog, null);
        imageView = dialogview.findViewById(R.id.img);
        quxiao = dialogview.findViewById(R.id.quxiao);
        chakan = dialogview.findViewById(R.id.chakan);
        shouye = dialogview.findViewById(R.id.shouye);
        chakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = BaseApp.getApp().getSharedPreferences("activity", Context.MODE_PRIVATE);
                SharedPreferences.Editor activit = sp.edit();
                activit.putString("acti", "login");
                activit.commit();
                startActivity(new Intent(getContext(), CouponActivity.class));
                dialog.dismiss();
            }
        });
        shouye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = BaseApp.getApp().getSharedPreferences("activity", Context.MODE_PRIVATE);
                SharedPreferences.Editor activit = sp.edit();
                activit.putString("acti", "login");
                activit.commit();
                dialog.dismiss();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = BaseApp.getApp().getSharedPreferences("activity", Context.MODE_PRIVATE);
                SharedPreferences.Editor activit = sp.edit();
                activit.putString("acti", "login");
                activit.commit();
                startActivity(new Intent(getContext(), CouponActivity.class));
                dialog.dismiss();
            }
        });
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = BaseApp.getApp().getSharedPreferences("activity", Context.MODE_PRIVATE);
                SharedPreferences.Editor activit = sp.edit();
                activit.putString("acti", "login");
                activit.commit();
                dialog.dismiss();
            }
        });

        Glide.with(getContext()).load(R.drawable.huodong)
                .into(imageView);
        dialog = new Dialog(getContext(), R.style.dialogWindowAnim);
        dialog.setContentView(dialogview, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        //设置显示动画
        window.setWindowAnimations(R.style.dialogWindowAnim);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = 0;
        //设置显示位置
        dialog.onWindowAttributesChanged(wl);
        //设置点击外围消散
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
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
        ToastUtils.show("" + request.getMessage());
    }

    @Override
    public void fail(ApiException e) {

    }
}
