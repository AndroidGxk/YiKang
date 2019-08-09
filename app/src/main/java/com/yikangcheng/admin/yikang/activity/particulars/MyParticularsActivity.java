package com.yikangcheng.admin.yikang.activity.particulars;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.Comadapter;
import com.yikangcheng.admin.yikang.activity.adapter.GoodPartiRecdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.RecommendPresenter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyParticularsActivity extends BaseActivtiy implements View.OnClickListener, ICoreInfe {
    private NestedScrollView nested;
    private LinearLayout tab_line, ll_recommend, line4;
    private LinearLayout shangpin_lin, pingjia_lin, xiangqing_lin, tuijian_lin;
    private TextView shang_text, ping_text, xiang_text, tuijian_text;
    private ImageView shang_img, ping_img, xiang_img, tuijian_img;
    private RelativeLayout shang_relat, ping_rela, rela_tabl, xiangqing_relat;
    private int height;
    private int ping_height;
    private int height2;
    private int tabheight;
    private int tablheight;
    private RecyclerView recom_recycler, img_count_recycler;
    private RecommendPresenter recommendPresenter;
    private GoodPartiRecdapter goodPartiRecdapter;
    private Comadapter comadapter;
    private Banner banner;
    private List<String> list_path;
    private WebView webView;
    private int recommendMeasuredHeight;
    private int xiangqingRelatMeasuredHeight;
    private int webViewMeasuredHeight;
    private int nestedScrollViewTop;
    @BindView(R.id.rela)
    RelativeLayout rela;

    @Override
    protected void initView() {
        nested = (NestedScrollView) findViewById(R.id.nested);
        tab_line = (LinearLayout) findViewById(R.id.tab_line);
        shangpin_lin = (LinearLayout) findViewById(R.id.shangpin_lin);
        pingjia_lin = (LinearLayout) findViewById(R.id.pingjia_lin);
        xiangqing_lin = (LinearLayout) findViewById(R.id.xiangqing_lin);
        tuijian_lin = (LinearLayout) findViewById(R.id.tuijian_lin);
        ll_recommend = (LinearLayout) findViewById(R.id.ll_recommend);
        line4 = (LinearLayout) findViewById(R.id.line4);
        //商品
        shang_relat = (RelativeLayout) findViewById(R.id.shang_relat);
        xiangqing_relat = (RelativeLayout) findViewById(R.id.xiangqing_relat);
        ping_rela = (RelativeLayout) findViewById(R.id.ping_rela);
        rela_tabl = (RelativeLayout) findViewById(R.id.rela_tabl);
        shang_text = (TextView) findViewById(R.id.shang_text);
        ping_text = (TextView) findViewById(R.id.ping_text);
        xiang_text = (TextView) findViewById(R.id.xiang_text);
        tuijian_text = (TextView) findViewById(R.id.tuijian_text);
        shang_img = (ImageView) findViewById(R.id.shang_img);
        ping_img = (ImageView) findViewById(R.id.ping_img);
        xiang_img = (ImageView) findViewById(R.id.xiang_img);
        tuijian_img = (ImageView) findViewById(R.id.tuijian_img);
        webView = (WebView) findViewById(R.id.webview);
        //banner轮播图
        banner = (Banner) findViewById(R.id.banner);
        recom_recycler = (RecyclerView) findViewById(R.id.recom_recycler);
        img_count_recycler = (RecyclerView) findViewById(R.id.img_count_recycler);
        tab_line.setAlpha(0);

        getHeight();
        rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    //控制nestedScrollView滑动到一定的距离
    public void scrollByDistance(int dy) {
        if (nestedScrollViewTop == 0) {
            int[] intArray = new int[2];
            nested.getLocationOnScreen(intArray);
            nestedScrollViewTop = intArray[1];
        }
        int distance = dy - nestedScrollViewTop;//必须算上nestedScrollView本身与屏幕的距离
        nested.fling(distance);//添加上这句滑动才有效
        nested.smoothScrollBy(0, distance);
    }

    @SuppressLint("NewApi")
    public void getHeight() {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        shang_relat.measure(w, h);
        ping_rela.measure(w, h);
        ll_recommend.measure(w, h);
        tab_line.measure(w, h);
        xiangqing_relat.measure(w, h);
        webView.measure(w, h);
        height = shang_relat.getMeasuredHeight();
        height2 = ping_rela.getMeasuredHeight();
        xiangqingRelatMeasuredHeight = xiangqing_relat.getMeasuredHeight();
        webViewMeasuredHeight = webView.getMeasuredHeight();
        recommendMeasuredHeight = ll_recommend.getMeasuredHeight();
        tabheight = tab_line.getMeasuredHeight();
        tablheight = rela_tabl.getMeasuredHeight();
        ping_height = height + recommendMeasuredHeight;
    }

    @SuppressLint("NewApi")
    @Override
    protected void initEventData() {
        shangpin_lin.setOnClickListener(this);
        pingjia_lin.setOnClickListener(this);
        xiangqing_lin.setOnClickListener(this);
        tuijian_lin.setOnClickListener(this);
        nested.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                float percent = Float.valueOf(Math.abs(i1)) / Float.valueOf(tab_line.getHeight());
                tab_line.setAlpha(percent);
                Log.d("GTT", "Scroll" + i1);
            }
        });

        recommendPresenter = new RecommendPresenter(this);
        recommendPresenter.request(92, 1, 1);
        recom_recycler.setLayoutManager(new GridLayoutManager(this, 3));
        goodPartiRecdapter = new GoodPartiRecdapter();
        recom_recycler.setAdapter(goodPartiRecdapter);
        //评论
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        img_count_recycler.setLayoutManager(linearLayoutManager);
        comadapter = new Comadapter();
        img_count_recycler.setAdapter(comadapter);

        //解决滑动不流畅
        recom_recycler.setHasFixedSize(true);
        recom_recycler.setNestedScrollingEnabled(false);
        img_count_recycler.setHasFixedSize(true);
        img_count_recycler.setNestedScrollingEnabled(false);
        //放图片地址的集合
        list_path = new ArrayList<>();
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner.setBannerAnimation(Transformer.ScaleInOut);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
//        banner.setImages(list_path);
        banner.start();
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
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.getSettings().setBlockNetworkImage(false);
        webView.loadUrl("https://www.yikch.com/mobile/appShow/index?type=android");

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_my_particulars;
    }

    @Override
    protected void createPresenter() {

    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shangpin_lin:
                shang_img.setVisibility(View.VISIBLE);
                ping_img.setVisibility(View.INVISIBLE);
                xiang_img.setVisibility(View.INVISIBLE);
                tuijian_img.setVisibility(View.INVISIBLE);
                nested.scrollTo(0, 0);
                break;
            case R.id.pingjia_lin:
                shang_img.setVisibility(View.INVISIBLE);
                ping_img.setVisibility(View.VISIBLE);
                xiang_img.setVisibility(View.INVISIBLE);
                tuijian_img.setVisibility(View.INVISIBLE);
                nested.scrollTo(0, height - (tabheight + tablheight));
                break;
            case R.id.xiangqing_lin:
                shang_img.setVisibility(View.INVISIBLE);
                ping_img.setVisibility(View.INVISIBLE);
                xiang_img.setVisibility(View.VISIBLE);
                tuijian_img.setVisibility(View.INVISIBLE);
                scrollByDistance(xiangqingRelatMeasuredHeight);
                break;
            case R.id.tuijian_lin:
                shang_img.setVisibility(View.INVISIBLE);
                ping_img.setVisibility(View.INVISIBLE);
                xiang_img.setVisibility(View.INVISIBLE);
                tuijian_img.setVisibility(View.VISIBLE);
                nested.scrollTo(0, (xiangqingRelatMeasuredHeight + tabheight + tablheight + webViewMeasuredHeight));
                break;
        }
    }

    @Override
    public void success(Object data) {

    }

    @Override
    public void fail(ApiException e) {

    }
}
