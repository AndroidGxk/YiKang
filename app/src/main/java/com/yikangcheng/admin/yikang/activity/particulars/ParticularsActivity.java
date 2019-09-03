package com.yikangcheng.admin.yikang.activity.particulars;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.LongDef;
import android.support.annotation.NonNull;
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
import android.widget.Toast;

import com.hjq.toast.ToastUtils;
import com.hjq.toast.style.ToastQQStyle;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.ConsultingContent;
import com.sobot.chat.api.model.Information;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.CloseActivity;
import com.yikangcheng.admin.yikang.activity.GoodComActivity;
import com.yikangcheng.admin.yikang.activity.H5SecActivity;
import com.yikangcheng.admin.yikang.activity.PartiCarActivity;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Miao;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Shou;
import com.yikangcheng.admin.yikang.activity.seek.SeckillActivity;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.ShopCarBean;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.AddShopPresenter;
import com.yikangcheng.admin.yikang.presenter.ShopCarPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import butterknife.BindView;

public class ParticularsActivity extends BaseActivtiy implements ICoreInfe {
    private WebView webView;
    private int id;
    private String s = "";
    private AddShopPresenter addShopPresenter;
    private ImageView back_img, img_top;
    private ProgressBar pbProgress;
    private SmartRefreshLayout refreshLayout;
    String Cust = "";
    private int width;
    private String ss = "";
    private ShopCarPresenter shopCarPresenter;
    Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                webView.loadUrl("javascript:redDot(" + entity.size() + ")");
                Log.d("GTT", entity.size() + "");
                if (logUser != null) {
                    shopCarPresenter.request(logUser.getId());
                }
            }
        }
    };
    private List<ShopCarBean> entity;
    private LoginBean logUser;
    private String url;
    private Dialog bottomDialog;
    private View contentMeiView;
    private LinearLayout wx_pengyouquan, wx_haoyou;
    String Custs = "";
    private IWXAPI wxApi;
    private Bitmap bitmap;
    private RelativeLayout tabl;
    @BindView(R.id.title)
    TextView title;
    private String comm;

    @Override
    protected void initView() {
        //设置状态栏颜色
        if (!getLogUser(this).getThemeColors().equals("")) {
            StatusBarUtil.setStatusBarMode(this, true, Color.parseColor(getLogUser(this).getThemeColors()));
        } else {
            StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        }
        SharedPreferences close = getSharedPreferences("close", MODE_PRIVATE);
//        title.setTextColor(Color.parseColor());
        SharedPreferences.Editor edit = close.edit();
        edit.putBoolean("close", true);
        edit.commit();
        shopCarPresenter = new ShopCarPresenter(new ShopNum());
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        if (Fragment_Shou.class != null) {
            Fragment_Shou.getGoBack();
        }
        if (Fragment_Miao.class != null) {
            Fragment_Miao.getGoWeb();
        }
        if (SeckillActivity.class != null) {
            SeckillActivity.getGoWeb();
        }
        Intent intent1 = getIntent();
        String h5 = intent1.getStringExtra("h5");
        if (h5 != null && h5.equals("true")) {
            H5SecActivity.getGoBack();
        }
        //加载刷新
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setEnableLoadmore(false);
        //进度条
        pbProgress = (ProgressBar) findViewById(R.id.pb_progress);
        tabl = (RelativeLayout) findViewById(R.id.tabl);
        tabl.setBackgroundColor(Color.parseColor(getLogUser(this).getThemeColors()));
        webView = (WebView) findViewById(R.id.webvieww);
        back_img = (ImageView) findViewById(R.id.back_img);
        img_top = (ImageView) findViewById(R.id.img_top);
        Intent intent = getIntent();
        String scheme = intent.getScheme();
        Uri uri = intent.getData();
        if (uri != null) {
            String test1 = uri.getQueryParameter("id");//参数1
            Toast.makeText(this, "" + test1, Toast.LENGTH_SHORT).show();
        }
        id = intent.getIntExtra("id", 0);
        url = intent.getStringExtra("id");
        addShopPresenter = new AddShopPresenter(this);
        logUser = getLogUser(ParticularsActivity.this);
        if (logUser != null) {
            shopCarPresenter.request(logUser.getId());
        }
    }

    @SuppressLint({"JavascriptInterface", "NewApi"})
    @Override
    protected void initEventData() {
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
        webView.goBack();
        /**
         * http和https协议混合加载
         */
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.getSettings().setBlockNetworkImage(false);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
                if (url.toString().contains("sina.cn")) {
                    if (id == 0) {
                        webView.loadUrl(url);
                    } else {
                        webView.loadUrl("https://www.yikch.com/mobile/appShow/proDetail/" + id + "?type=android&userId=" + getLogUser(ParticularsActivity.this).getId());
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
                        if (id == 0) {
                            webView.loadUrl(url);
                        } else {
                            webView.loadUrl("https://www.yikch.com/mobile/appShow/proDetail/" + id + "?type=android&userId=" + getLogUser(ParticularsActivity.this).getId());
                        }
                        return true;
                    }
                }
                return false;
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                Log.d("GTT", "WebView3:" + view.getUrl() + "\\n" + "   URL3:" + url);
                super.onLoadResource(view, url);
            }
        });
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
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
        if (id == 0) {
            webView.loadUrl(url);
        } else {
            webView.loadUrl("https://www.yikch.com/mobile/appShow/proDetail/" + id + "?type=android&userId=" + getLogUser(ParticularsActivity.this).getId());
        }
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                webView.loadUrl(webView.getUrl());
                refreshLayout.finishRefresh();
            }
        });
        webView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if (i1 >= 100) {
                    img_top.setVisibility(View.VISIBLE);
                } else {
                    img_top.setVisibility(View.GONE);
                }
            }
        });
        img_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.scrollTo(0, 0);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            // 在点击请求的是链接是才会调用，重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边。这个函数我们可以做很多操作，比如我们读取到某些特殊的URL，于是就可以不打开地址，取消这个操作，进行预先定义的其他操作，这对一个程序是非常必要的。
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 判断url链接中是否含有某个字段，如果有就执行指定的跳转（不执行跳转url链接），如果没有就加载url链接
                if (url.contains("mobile/login")) {
                    ToastUtils.show("未知错误");
                    finish();
                    return true;
                } else if (url.contains("mobile/appShow/xiazhi")) {
                    Intent intent = new Intent(ParticularsActivity.this, H5SecActivity.class);
                    intent.putExtra("http", "https://www.yikch.com/mobile/appShow/xiazhi?type=android&userId=" + getLogUser(ParticularsActivity.this).getId());
                    intent.putExtra("title", "夏至福利");
                    intent.putExtra("none", "no");
                    startActivity(intent);
                    return true;
                } else if (url.contains("mobile/appShow/market")) {
                    Intent intent = new Intent(ParticularsActivity.this, H5SecActivity.class);
                    intent.putExtra("http", "https://www.yikch.com/mobile/appShow/market?type=android&userId=" + getLogUser(ParticularsActivity.this).getId());
                    intent.putExtra("title", "挚友超市");
                    intent.putExtra("none", "no");
                    startActivity(intent);
                    return true;
                } else if (url.contains("from=proDetail")) {
                    Intent intent = new Intent(ParticularsActivity.this, ParticularsActivity.class);
                    intent.putExtra("id", url + "&userId=" + getLogUser(ParticularsActivity.this).getId());
                    startActivity(intent);
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                bitmap = null;
                super.onLoadResource(view, url);
            }
        });
    }

//    https://www.yikch.com/mobile/appShow/index?type=android

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_particulars;
    }

    @Override
    protected void createPresenter() {

    }

    @JavascriptInterface
    public void goMoreEvaluateToAndroid(String msg) {
        if (msg.equals("")) {
            msg = "";
        }
        comm += msg + ",";
        String[] split = comm.split(",");
        if (split.length == 2) {
            Intent intent = new Intent(ParticularsActivity.this, GoodComActivity.class);
            intent.putExtra("detaid", split[0]);
            intent.putExtra("goodid", split[1]);
            Log.d("GTT", split[0]);
            Log.d("GTT", split[1]);
            startActivity(intent);
            comm = "";
        }
    }

    @JavascriptInterface
    public void sayHello(String msg) {
        if (msg.equals("")) {
            msg = "1";
        }
        s += msg + ",";
        String[] split = s.split(",");
        if (split.length == 5) {
            if (split[4].equals("1")) {
                SharedPreferences close = getSharedPreferences("close", MODE_PRIVATE);
                SharedPreferences.Editor edit = close.edit();
                edit.putBoolean("close", false);
                edit.putBoolean("closes", false);
                edit.commit();
                int id = getLogUser(this).getId();
                addShopPresenter.request(id, split[0], split[1], split[2], split[3]);
                s = "";
            } else if (split[4].equals("noProInfo")) {
                ToastUtils.show("该规格下暂无商品信息");
                s = "";
                return;
            } else if (split[4].equals("pleaseWriteNum")) {
                ToastUtils.show("请输入要购买的数量");
                s = "";
                return;
            } else if (split[4].equals("numNotThan")) {
                ToastUtils.show("购买数量不能大于当前库存");
                s = "";
                return;
            }
        }
    }

    @JavascriptInterface
    public void orderBuy(String msg) {
        //todo 立即购买
        if (msg.equals("")) {
            msg = "1";
        }
        ss += msg + ",";
        String[] split = this.ss.split(",");
        if (split.length == 9) {
            if (split[8].equals("1")) {
                Intent intent = new Intent(ParticularsActivity.this, CloseActivity.class);
                intent.putExtra("goodinfo", this.ss);
                startActivity(intent);
                this.ss = "";
                if (logUser != null) {
                    shopCarPresenter.request(logUser.getId());
                }
            } else if (split[8].equals("noProInfo")) {
                ToastUtils.show("该规格下暂无商品信息");
                this.ss = "";
                return;
            } else if (split[8].equals("pleaseWriteNum")) {
                ToastUtils.show("请输入要购买的数量");
                this.ss = "";
                return;
            } else if (split[8].equals("numNotThan")) {
                ToastUtils.show("购买数量不能大于当前库存");
                this.ss = "";
                return;
            }
        }
        if (split.length == 8) {
            if (split[7].equals("1")) {
                Intent intent = new Intent(ParticularsActivity.this, CloseActivity.class);
                intent.putExtra("goodinfo", this.ss);
                startActivity(intent);
                this.ss = "";
                if (logUser != null) {
                    shopCarPresenter.request(logUser.getId());
                }
            } else if (split[7].equals("noProInfo")) {
                ToastUtils.show("该规格下暂无商品信息");
                this.ss = "";
                return;
            } else if (split[7].equals("pleaseWriteNum")) {
                ToastUtils.show("请输入要购买的数量");
                this.ss = "";
                return;
            } else if (split[7].equals("numNotThan")) {
                ToastUtils.show("购买数量不能大于当前库存");
                this.ss = "";
                return;
            }
        }
    }

    @JavascriptInterface
    public void goCust(String msg) {
        if (msg.equals("")) {
            msg = "1";
        }
        Cust += msg + ",";
        String[] split = Cust.split(",");
        if (split.length == 5) {
            Information info = new Information();
//            //咨询内容
            ConsultingContent consultingContent = new ConsultingContent();
//            //咨询内容标题
            consultingContent.setSobotGoodsTitle(split[0]);
//            //咨询内容图片
            consultingContent.setSobotGoodsImgUrl(split[1]);
//            //咨询来源页
            consultingContent.setSobotGoodsFromUrl(split[2]);
//            //描述
            consultingContent.setSobotGoodsDescribe(split[0]);
//            //价格
            consultingContent.setSobotGoodsLable("¥" + split[3]);
            info.setConsultingContent(consultingContent);
            info.setAppkey("7560599b63bf43378d05d018ded42cdd");
            LoginBean logUser = getLogUser(ParticularsActivity.this);
            info.setUid(String.valueOf(logUser.getId()));
            SobotApi.setCustomRobotHelloWord(ParticularsActivity.this, "您好，易康成客服很高兴为您服务，请问有什么可以帮助您的？");
            SobotApi.startSobotChat(ParticularsActivity.this, info);
            Cust = "";
        }
    }

    @JavascriptInterface
    public void goCar() {
        SharedPreferences close = getSharedPreferences("close", MODE_PRIVATE);
        SharedPreferences.Editor edit = close.edit();
        edit.putBoolean("close", false);
        edit.putBoolean("closes", false);
        edit.commit();
        startActivity(new Intent(ParticularsActivity.this, PartiCarActivity.class));
    }


    @JavascriptInterface
    public void sayDetailShare(String msg) {
        Log.d("GTT", msg);
        Custs += msg + ",";
        final String[] split = Custs.split(",");
        Log.d("GTT", split.length + "");
        if (split.length == 4) {
            Custs = "";
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
            final IWXAPI wxApi = BaseApp.mWxApi;
            if (bitmap == null) {
                bitmap = createBitmapThumbnail(getBitmap(split[2]));
            }
            wx_pengyouquan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WXWebpageObject webpage = new WXWebpageObject();
                    WXMediaMessage msg = new WXMediaMessage(webpage);
                    msg.title = split[0];
                    msg.description = split[1];
                    webpage.webpageUrl = split[3];
                    //这里替换一张自己工程里的图片资源
                    msg.setThumbImage(bitmap);
                    SendMessageToWX.Req req = new SendMessageToWX.Req();
                    req.transaction = String.valueOf(System.currentTimeMillis());
                    req.message = msg;
                    req.scene = 1 == 0 ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
                    wxApi.sendReq(req);
                    bottomDialog.dismiss();
                }
            });
            wx_haoyou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WXWebpageObject webpage = new WXWebpageObject();
                    WXMediaMessage msg = new WXMediaMessage(webpage);
                    msg.title = split[0];
                    msg.description = split[1];
                    webpage.webpageUrl = split[3];
                    //这里替换一张自己工程里的图片资源
                    msg.setThumbImage(bitmap);
                    SendMessageToWX.Req req = new SendMessageToWX.Req();
                    req.transaction = String.valueOf(System.currentTimeMillis());
                    req.message = msg;
                    req.scene = 0 == 0 ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
                    wxApi.sendReq(req);
                    bottomDialog.dismiss();
                }
            });
        }
    }

    //压缩图片
    public Bitmap createBitmapThumbnail(Bitmap bitMap) {
        int width = bitMap.getWidth();
        int height = bitMap.getHeight();
        // 设置想要的大小
        int newWidth = 99;
        int newHeight = 99;
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newBitMap = Bitmap.createBitmap(bitMap, 0, 0, width, height, matrix, true);
        return newBitMap;
    }

    /**
     * 添加购物车
     *
     * @param data
     */
    @Override
    public void success(Object data) {
        Request request = (Request) data;
        ToastUtils.show("" + request.getMessage());
    }

    @Override
    public void fail(ApiException e) {

    }


    /**
     * 购物车数量
     */
    private class ShopNum implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            entity = (List<ShopCarBean>) request.getEntity();
            handler.sendEmptyMessageDelayed(1, 1000);
        }

        @Override
        public void fail(ApiException e) {

        }
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

    /**
     * Bitmap转换成byte[]并且进行压缩,压缩到不大于maxkb
     *
     * @param bitmap
     * @param
     * @return
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap, int maxkb) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        int options = 100;
        while (output.toByteArray().length > maxkb && options != 10) {
            output.reset(); //清空output
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, output);//这里压缩options%，把压缩后的数据存放到output中
            options -= 10;
        }
        return output.toByteArray();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeMessages(1);
    }
}
