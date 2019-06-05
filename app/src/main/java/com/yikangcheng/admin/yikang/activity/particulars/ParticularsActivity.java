package com.yikangcheng.admin.yikang.activity.particulars;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.NonNull;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.ConsultingContent;
import com.sobot.chat.api.model.Information;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.CloseActivity;
import com.yikangcheng.admin.yikang.activity.H5SecActivity;
import com.yikangcheng.admin.yikang.activity.PartiCarActivity;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Miao;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Shou;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.AddShopPresenter;
import com.yikangcheng.admin.yikang.presenter.OrderBuyPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.jessyan.autosize.internal.CustomAdapt;

public class ParticularsActivity extends BaseActivtiy implements CustomAdapt, ICoreInfe {
    private WebView webView;
    private int id;
    private String s = "";
    private AddShopPresenter addShopPresenter;
    private ImageView back_img;
    private OrderBuyPresenter orderBuyPresenter;
    private ProgressBar pbProgress;
    private SmartRefreshLayout refreshLayout;
    String Cust = "";
    private int width;
    private String ss = "";

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorTab);
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        if (Fragment_Shou.class != null) {
            Fragment_Shou.getGoBack();
        }
        if (Fragment_Miao.class != null) {
            Fragment_Miao.getGoWeb();
        }
        Intent intent1 = getIntent();
        String h5 = intent1.getStringExtra("h5");
        if (h5 != null && h5.equals("true")) {
            H5SecActivity.getGoBack();
        }
        //加载刷新
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setEnableLoadMore(false);
        //进度条
        pbProgress = (ProgressBar) findViewById(R.id.pb_progress);
        webView = (WebView) findViewById(R.id.webvieww);
        back_img = (ImageView) findViewById(R.id.back_img);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        addShopPresenter = new AddShopPresenter(this);
        orderBuyPresenter = new OrderBuyPresenter(new OrderBuy());
    }

    @SuppressLint("JavascriptInterface")
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
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
                if (url.toString().contains("sina.cn")) {
                    webView.loadUrl("https://www.yikch.com/mobile/appShow/proDetail/" + id + ".json?type=android");
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
                        webView.loadUrl("https://www.yikch.com/mobile/appShow/proDetail/" + id + ".json?type=android");
                        return true;
                    }
                }
                return false;
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
        webView.loadUrl("https://www.yikch.com/mobile/appShow/proDetail/" + id + ".json?type=android");
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                webView.loadUrl("https://www.yikch.com/mobile/appShow/proDetail/" + id + ".json?type=android");
                refreshLayout.finishRefresh();
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_particulars;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return width / 2;
    }

    @JavascriptInterface
    public void sayHello(String msg) {
        if (msg.equals("")) {
            msg = "1";
        }
        s += msg + ",";
        String[] split = s.split(",");
        if (split.length == 4) {
            int id = getLogUser(this).getId();
            addShopPresenter.request(id, split[0], split[1], split[2], split[3]);
            s = "";
        }
    }

    @JavascriptInterface
    public void orderBuy(String msg) {
        if (msg.equals("")) {
            msg = "1";
        }
        ss += msg + ",";
        String[] split = this.ss.split(",");
        if (split.length == 8) {
            if (split[7].equals("1")) {
                Intent intent = new Intent(ParticularsActivity.this, CloseActivity.class);
                intent.putExtra("goodinfo", this.ss);
                startActivity(intent);
                this.ss = "";
            } else if (split[7].equals("noProInfo")) {
                Toast.makeText(this, "该规格下暂无商品信息", Toast.LENGTH_SHORT).show();
                this.ss = "";
                return;
            } else if (split[7].equals("pleaseWriteNum")) {
                Toast.makeText(this, "请输入要购买的数量", Toast.LENGTH_SHORT).show();
                this.ss = "";
                return;
            } else if (split[7].equals("numNotThan")) {
                Toast.makeText(this, "购买数量不能大于当前库存", Toast.LENGTH_SHORT).show();
                this.ss = "";
                return;
            }
        }
    }

    @JavascriptInterface
    public void goCust(String msg) {
        Cust += msg + ",";
        String[] split = Cust.split(",");
        if (split.length == 5) {
            Information info = new Information();
            //咨询内容
            ConsultingContent consultingContent = new ConsultingContent();
            //咨询内容标题，必填
            consultingContent.setSobotGoodsTitle(split[0]);
            //咨询内容图片，选填 但必须是图片地址
            consultingContent.setSobotGoodsImgUrl(split[1]);
            //咨询来源页，必填
            consultingContent.setSobotGoodsFromUrl(split[2]);
            //描述，选填
            consultingContent.setSobotGoodsDescribe(split[0]);
            //标签，选填
            consultingContent.setSobotGoodsLable("¥" + split[3]);
            //可以设置为null
            info.setConsultingContent(consultingContent);
            info.setAppkey("7560599b63bf43378d05d018ded42cdd");
            SobotApi.setCustomRobotHelloWord(ParticularsActivity.this, "您好，易康成客服很高兴为您服务，请问有什么可以帮助您的？");
            SobotApi.startSobotChat(ParticularsActivity.this, info);
            Cust = "";
        }
    }


    @JavascriptInterface
    public void goCar() {
        startActivity(new Intent(ParticularsActivity.this, PartiCarActivity.class));
    }

    /**
     * 添加购物车
     *
     * @param data
     */
    @Override
    public void success(Object data) {
        Request request = (Request) data;
        Toast.makeText(this, "" + request.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fail(ApiException e) {

    }

    /**
     * 创建订单
     */
    private class OrderBuy implements ICoreInfe {
        @Override
        public void success(Object data) {

        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
