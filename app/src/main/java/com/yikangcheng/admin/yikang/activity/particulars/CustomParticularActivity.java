package com.yikangcheng.admin.yikang.activity.particulars;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;

import butterknife.BindView;
import butterknife.OnClick;

public class CustomParticularActivity extends BaseActivtiy {
    @BindView(R.id.wv_detail)
    WebView webView;
    @BindView(R.id.tv_goods_config)
    TextView tv_goods_config;
    @BindView(R.id.tv_goods_detail)
    TextView tv_goods_detail;
    @BindView(R.id.config_line)
    LinearLayout config_line;
    @BindView(R.id.nested)
    NestedScrollView nested;
    @BindView(R.id.tab_line)
    LinearLayout tab_line;
    @BindView(R.id.ll_comment)
    LinearLayout ll_comment;
    @BindView(R.id.kan_rela)
    RelativeLayout kan_rela;
    @BindView(R.id.goog_select)
    LinearLayout goog_select;
    @BindView(R.id.shang_text)
    TextView shang_text;
    @BindView(R.id.ping_text)
    TextView ping_text;
    @BindView(R.id.xiang_text)
    TextView xiang_text;
    @BindView(R.id.tuijian_text)
    TextView tuijian_text;
    @BindView(R.id.shang_img)
    ImageView shang_img;
    @BindView(R.id.ping_img)
    ImageView ping_img;
    @BindView(R.id.xiang_img)
    ImageView xiang_img;
    @BindView(R.id.tuijian_img)
    ImageView tuijian_img;
    private int height;

    @Override
    protected void initView() {
        tab_line.setAlpha(0);
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
        // 在安卓5.0之后，默认不允许加载http与https混合内容，需要设置webview允许其加载混合网络协议内容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
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

    @SuppressLint("NewApi")
    @Override
    protected void initEventData() {
        nested.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if (i1 >= ll_comment.getTop() - (height * 2) && i1 < goog_select.getTop() - (height * 2) && i1 < kan_rela.getTop() - (height * 2)) {
                    changeTextColor(R.color.colorText, R.color.colorRed, R.color.colorText, R.color.colorText);
                    changeLineShowHide(View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
                } else if (i1 >= goog_select.getTop() - (height * 2) && i1 < kan_rela.getTop() - (height * 2)) {
                    changeTextColor(R.color.colorText, R.color.colorText, R.color.colorRed, R.color.colorText);
                    changeLineShowHide(View.INVISIBLE, View.INVISIBLE, View.VISIBLE, View.INVISIBLE);
                } else if (i1 >= kan_rela.getTop() - (height * 2)) {
                    changeTextColor(R.color.colorText, R.color.colorText, R.color.colorText, R.color.colorRed);
                    changeLineShowHide(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
                } else {
                    changeTextColor(R.color.colorRed, R.color.colorText, R.color.colorText, R.color.colorText);
                    changeLineShowHide(View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                    float percent = Float.valueOf(Math.abs(i1)) / Float.valueOf(tab_line.getHeight());
                    tab_line.setAlpha(percent);
                }
            }
        });

        //获取控件高度
        getHeight();
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_custom_particular;
    }

    @Override
    protected void createPresenter() {

    }


    @OnClick({R.id.tv_goods_detail, R.id.tv_goods_config, R.id.shangpin_lin, R.id.pingjia_lin,
            R.id.xiangqing_lin, R.id.tuijian_lin})
    public void onViewClicked(View view) {
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
            //商品
            case R.id.shangpin_lin:
                changeTextColor(R.color.colorRed, R.color.colorText, R.color.colorText, R.color.colorText);
                changeLineShowHide(View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                //顶部
                nested.scrollTo(0, 0);
                break;
            //评价
            case R.id.pingjia_lin:
                changeTextColor(R.color.colorText, R.color.colorRed, R.color.colorText, R.color.colorText);
                changeLineShowHide(View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
                nested.scrollTo(0, ll_comment.getTop() - (height * 2));
                break;
            //详情
            case R.id.xiangqing_lin:
                changeTextColor(R.color.colorText, R.color.colorText, R.color.colorRed, R.color.colorText);
                changeLineShowHide(View.INVISIBLE, View.INVISIBLE, View.VISIBLE, View.INVISIBLE);
                nested.scrollTo(0, goog_select.getTop() - (height * 2));
                break;
            //推荐
            case R.id.tuijian_lin:
                changeTextColor(R.color.colorText, R.color.colorText, R.color.colorText, R.color.colorRed);
                changeLineShowHide(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
                nested.scrollTo(0, kan_rela.getTop() - (height * 2));
                break;
        }
    }

    /**
     * 更换字体颜色
     */
    public void changeTextColor(int sColor, int pColor, int xColor, int tColor) {
        shang_text.setTextColor(CustomParticularActivity.this.getResources().getColor(sColor));
        ping_text.setTextColor(CustomParticularActivity.this.getResources().getColor(pColor));
        xiang_text.setTextColor(CustomParticularActivity.this.getResources().getColor(xColor));
        tuijian_text.setTextColor(CustomParticularActivity.this.getResources().getColor(tColor));
    }

    /**
     * 隐藏显示红短线
     */
    public void changeLineShowHide(int sLine, int pLine, int xLine, int tLine) {
        shang_img.setVisibility(sLine);
        ping_img.setVisibility(pLine);
        xiang_img.setVisibility(xLine);
        tuijian_img.setVisibility(tLine);
    }

    /**
     * 获取控件高度
     */
    public void getHeight() {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        tab_line.measure(w, h);
        height = tab_line.getMeasuredHeight();
    }
}
