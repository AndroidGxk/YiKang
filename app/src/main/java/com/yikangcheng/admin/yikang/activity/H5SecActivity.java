package com.yikangcheng.admin.yikang.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.coupon.CouponActivity;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Shou;
import com.yikangcheng.admin.yikang.activity.h5activity.H5DiscountActivity;
import com.yikangcheng.admin.yikang.activity.h5activity.H5RecommendListActivity;
import com.yikangcheng.admin.yikang.activity.h5activity.H5WuliuZhuangtaiActivity;
import com.yikangcheng.admin.yikang.activity.particulars.ParticularsActivity;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class H5SecActivity extends BaseActivtiy {

    private static WebView webview;
    private String http, title;
    private ImageView back_img;
    private ProgressBar pbProgress;
    private TextView title_text;
    private RelativeLayout tabl;
    private String none;
    private String color;
    private int width;
    private Dialog bottomDialog;
    private View contentMeiView;
    private LinearLayout wx_pengyouquan, wx_haoyou;

    @Override
    protected void initView() {
        //不允许侧滑退出
        closeSwipeBack();
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorTab);
        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();

        //进度条
        pbProgress = (ProgressBar) findViewById(R.id.pb_progress);
        webview = (WebView) findViewById(R.id.webView);
        back_img = (ImageView) findViewById(R.id.back_img);
        tabl = (RelativeLayout) findViewById(R.id.tabl);
        title_text = (TextView) findViewById(R.id.title_text);
        Fragment_Shou.getGoBack();
        Intent intent = getIntent();
        http = intent.getStringExtra("http");
        none = intent.getStringExtra("none");
        color = intent.getStringExtra("color");
        if (http == null) {
            http = "https://www.yikch.com/mobile/appShow/activity?type=android";
        }
        if (none != null) {
            if (none.equals("yes")) {
                tabl.setVisibility(View.GONE);
            }
        }
        if (color != null) {
            if (color.equals("bai")) {
                tabl.setBackgroundColor(Color.WHITE);
                StatusBarUtil.setStatusBarMode(this, true, R.color.clolrBAai);
            }
        }

        title = intent.getStringExtra("title");
        if (title != null && !title.equals("")) {
            title_text.setText(title);
        }
        if (title != null) {
            if (title.equals("夏至福利") || title.equals("挚友超市"))
                tabl.setVisibility(View.GONE);
        }
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
        // 在安卓5.0之后，默认不允许加载http与https混合内容，需要设置webview允许其加载混合网络协议内容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
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
        webview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //这event.getAction() == KeyEvent.ACTION_DOWN表示是返回键事件   
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {//表示按返回键 时的操作  
                        webview.goBack();//后退    
                        return true;//已处理     返回true表示被处理否则返回false    
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
        //webview在安卓5.0之前默认允许其加载混合网络协议内容
// 在安卓5.0之后，默认不允许加载http与https混合内容，需要设置webview允许其加载混合网络协议内容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webview.getSettings().setBlockNetworkImage(false);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.addJavascriptInterface(this, "ww");
        webview.loadUrl(http);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            // 在点击请求的是链接是才会调用，重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边。这个函数我们可以做很多操作，比如我们读取到某些特殊的URL，于是就可以不打开地址，取消这个操作，进行预先定义的其他操作，这对一个程序是非常必要的。
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 判断url链接中是否含有某个字段，如果有就执行指定的跳转（不执行跳转url链接），如果没有就加载url链接
                if (url.contains("/appShow/proDetail")) {
                    Intent intent = new Intent(H5SecActivity.this, ParticularsActivity.class);
                    intent.putExtra("id", url + "&userId=" + getLogUser(BaseApp.getApp()).getId());
                    startActivity(intent);
                    return true;
                } else if (url.contains("/login")) {
                    finish();
                    return true;
                } else if (url.contains("appShow/recommendList")) {
                    Intent intent = new Intent(H5SecActivity.this, H5RecommendListActivity.class);
                    intent.putExtra("http", url);
                    startActivity(intent);
                    return true;
                } else if (url.contains("discounts")) {
                    //优惠券链接
                    Intent intent = new Intent(H5SecActivity.this, H5DiscountActivity.class);
                    intent.putExtra("http", url);
                    intent.putExtra("none", "yes");
                    startActivity(intent);
                    return true;
                } else if (url.contains("appShow/orderLogistics")) {
                    Intent intent = new Intent(H5SecActivity.this, H5WuliuZhuangtaiActivity.class);
                    intent.putExtra("http", url);
                    startActivity(intent);
                    return true;
                } else if (url.contains("/appShow/checkIn")) {
//                    Intent intent = new Intent(H5SecActivity.this, H5WuliuZhuangtaiActivity.class);
//                    intent.putExtra("http", "https://www.yikch.com/mobile/appShow/checkIn?type=android&userId=" + getLogUser(H5SecActivity.this).getId() + "");
//                    startActivity(intent);
//                    webview.loadUrl();
//                    title_text.setText("签到");
                    Intent intent = new Intent(H5SecActivity.this, H5SecActivity.class);
                    intent.putExtra("http", "https://www.yikch.com/mobile/appShow/checkIn?type=android&userId=" + getLogUser(H5SecActivity.this).getId() + "");
                    intent.putExtra("title", "签到");
                    startActivity(intent);
                    return true;
                } else if (url.contains("/appShow/yousheng")) {
//                    webview.loadUrl();
//                    title_text.setText("优胜教育内部购");
                    Intent intent = new Intent(H5SecActivity.this, H5SecActivity.class);
                    intent.putExtra("http", "https://www.yikch.com/mobile/appShow/yousheng?type=android");
                    intent.putExtra("title", "优胜内购");
                    startActivity(intent);
                    return true;
                } else if (url.contains("/mobile/appShow/qixiFestival")) {
                    Intent intent = new Intent(H5SecActivity.this, H5SecActivity.class);
                    intent.putExtra("http", "https://www.yikch.com/mobile/appShow/qixiFestival?type=android&userId=" + getLogUser(H5SecActivity.this).getId() + "");
                    intent.putExtra("title", "七夕情人节");
                    startActivity(intent);
                    return true;
                } else if (url.contains("/mobile/appShow/turnOver")) {
                    Intent intent = new Intent(H5SecActivity.this, H5SecActivity.class);
                    String userUrl = url + "&userId=" + getLogUser(H5SecActivity.this).getId();
                    intent.putExtra("http", userUrl);
                    Log.d("GTT", userUrl);
                    intent.putExtra("title", "抽奖");
                    startActivity(intent);
                    return true;
                } else {
                    return false;
                }
            }
        });
        Log.d("GT", webview.getUrl());
    }

    @Override
    protected void initEventData() {
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 判断网页是否还可以返回
                 */
                if (webview.canGoBack()) {
                    webview.goBack();
                } else {
                    finish();
                }
            }
        });
    }


    /**
     * @return
     */

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

    @JavascriptInterface
    public void toSeeCoupon() {
        startActivity(new Intent(H5SecActivity.this, CouponActivity.class));
    }

    String Cust = "";

    @JavascriptInterface
    public void sayShare(String msg) {
        Log.d("GTT", msg);
        if (msg.equals("")) {
            msg = "1";
        }
        Cust += msg + ",";
        final String[] split = Cust.split(",");
        Log.d("GTT", split.length + "");
        if (split.length == 4) {
            Cust = "";
            bottomDialog = new Dialog(this, R.style.BottomDialog);
            contentMeiView = View.inflate(this, R.layout.wxshaer_item,
                    null);
            //设置dialog的宽高为屏幕的宽高
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            bottomDialog.setContentView(contentMeiView, layoutParams);
            bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
            bottomDialog.setCanceledOnTouchOutside(true);
            bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialogs);
            bottomDialog.show();
            wx_pengyouquan = contentMeiView.findViewById(R.id.wx_pengyou);
            wx_haoyou = contentMeiView.findViewById(R.id.wx_haoyou);
            wx_pengyouquan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wechatShare(1, split[0], split[1], split[3], split[2]);
                    bottomDialog.dismiss();
                }
            });
            wx_haoyou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wechatShare(0, split[0], split[1], split[3], split[2]);
                    bottomDialog.dismiss();
                }
            });
        }
    }

    public static void getGoBack() {
        webview.goBack();
    }

    /**
     * 微信分享 （这里仅提供一个分享网页的示例，其它请参看官网示例代码）
     *
     * @param flag(0:分享到微信好友，1：分享到微信朋友圈)
     */
    private void wechatShare(int flag, String title, String count, String url, String imgUrl) {
        IWXAPI wxApi =  BaseApp.mWxApi;
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;
        msg.description = count;
        //这里替换一张自己工程里的图片资源
        msg.setThumbImage(getBitmap(imgUrl));
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = flag == 0 ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        wxApi.sendReq(req);
    }

    /**
     * @param path 网络图片地址
     */
    public Bitmap getBitmap(String path) {
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }
}
