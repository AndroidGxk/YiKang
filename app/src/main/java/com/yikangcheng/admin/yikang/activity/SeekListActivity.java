package com.yikangcheng.admin.yikang.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.ClassCommodAdapter;
import com.yikangcheng.admin.yikang.activity.particulars.ParticularsActivity;
import com.yikangcheng.admin.yikang.activity.seek.SeekActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.ClassifyCommodityListBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.CommodityPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;
import com.yikangcheng.admin.yikang.util.TwoBallRotationProgressBar;

import java.util.List;

import me.jessyan.autosize.internal.CustomAdapt;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * 搜索列表页面
 */
public class SeekListActivity extends BaseActivtiy implements ICoreInfe, View.OnClickListener {

    private TextView edit_seek_sousuo;
    private CommodityPresenter commodityPresenter;
    private XRecyclerView xrecycler;
    private ClassCommodAdapter classCommodAdapter;
    private TextView zonghe, xiaoliang, price_text;
    private RelativeLayout price;
    private RelativeLayout relat_one;
    private ImageView qiehuan, back_img;
    private boolean zclick = true, xclick, pclick, qclick = true, pimgclick = true;
    private int id;
    private String count;
    private SmartRefreshLayout refreshLayout;
    private int record;
    //页数
    private int mPage = 1;
    private int width;
    private ImageView xiaoxi;
    private ImageView imgBut, noGood_img;
    private TwoBallRotationProgressBar progress;

    @Override
    protected void initView() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorTab);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        Intent intent = getIntent();
        count = intent.getStringExtra("count");
        id = intent.getIntExtra("id", 000);
        xrecycler = (XRecyclerView) findViewById(R.id.xrecycler);
        //搜素框
        edit_seek_sousuo = (TextView) findViewById(R.id.EditTixt_activity_seek_sousuo);
        xiaoxi = (ImageView) findViewById(R.id.xiaoxi);
        zonghe = (TextView) findViewById(R.id.zonghe);
        imgBut = (ImageView) findViewById(R.id.imgBut);
        //加载进度
        progress = (TwoBallRotationProgressBar) findViewById(R.id.progress);
        //暂无商品
        noGood_img = (ImageView) findViewById(R.id.noGood_img);
        relat_one = (RelativeLayout) findViewById(R.id.rela);
        //加载刷新
        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        price_text = (TextView) findViewById(R.id.price_text);
        back_img = (ImageView) findViewById(R.id.back_img);
        zonghe.setOnClickListener(this);
        xiaoliang = (TextView) findViewById(R.id.xiaoliang);
        xiaoliang.setOnClickListener(this);
        price = (RelativeLayout) findViewById(R.id.price);
        price.setOnClickListener(this);
        qiehuan = (ImageView) findViewById(R.id.qiehuan);
        qiehuan.setOnClickListener(this);
        edit_seek_sousuo.setText(count);

        /**
         * P层
         */
        initMvp(mPage);

        xrecycler.setLayoutManager(new LinearLayoutManager(this));
        classCommodAdapter = new ClassCommodAdapter(this);
        xrecycler.setAdapter(classCommodAdapter);
        classCommodAdapter.setOnClickLisetener(new ClassCommodAdapter.onClickLisetener() {
            @Override
            public void onclick(int id) {
                Intent intent1 = new Intent(SeekListActivity.this, ParticularsActivity.class);
                intent1.putExtra("id", id);
                startActivity(intent1);
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                mPage = 1;
//                classCommodAdapter.removeAll();
//                if (count == null || count.equals("")) {
//                    commodityPresenter.request(id, record, "", 1, mPage);
//                } else {
//                    commodityPresenter.request(id, record, count, 1, mPage);
//                }
                refreshLayout.finishRefresh();
            }
        });
        //加载的监听事件
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                initMvp(mPage);
                refreshlayout.finishLoadmore();      //加载完成
            }
        });
        xiaoxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SeekListActivity.this, MessageActivity.class));
            }
        });
        xrecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalDy = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy -= dy;
                if (totalDy < -100) {
                    imgBut.setVisibility(View.VISIBLE);
                } else {
                    imgBut.setVisibility(View.GONE);
                }
            }
        });
        imgBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xrecycler.smoothScrollToPosition(0);
            }
        });
    }

    private void initMvp(int page) {
        commodityPresenter = new CommodityPresenter(this);
        if (count == null || count.equals("")) {
            commodityPresenter.request(id, 1, "", 1, page);
        } else {
            commodityPresenter.request(id, 1, count, 1, page);
        }
    }


    @Override
    protected void initEventData() {
        /**
         * 退出
         */
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        /**
         * 跳转
         */
        edit_seek_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SeekListActivity.this, SeekActivity.class));
                finish();
            }
        });
        /**
         * 进度条显示时
         */
        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_seek_list;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        ClassifyCommodityListBean classifyCommodityListBean = (ClassifyCommodityListBean) request.getEntity();
        List<ClassifyCommodityListBean.CommodityListBean> commodityList = classifyCommodityListBean.getCommodityList();
        if (commodityList == null) {
            noGood_img.setVisibility(View.VISIBLE);
            refreshLayout.setVisibility(View.GONE);
            relat_one.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            classCommodAdapter.addAll(commodityList);
            noGood_img.setVisibility(View.GONE);
            refreshLayout.setVisibility(View.VISIBLE);
            relat_one.setBackgroundColor(Color.parseColor("#EBEBEB"));
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.GONE);
                progress.stopAnimator();
            }
        }, 1500);
    }

    @Override
    public void fail(ApiException e) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.GONE);
                progress.stopAnimator();
            }
        }, 1500);
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zonghe:
                if (!zclick) {
                    classCommodAdapter.removeAll();
                    record = 1;
                    mPage = 1;
                    if (count == null || count.equals("")) {
                        commodityPresenter.request(id, record, "", 1, 1);
                    } else {
                        commodityPresenter.request(id, record, count, 1, 1);
                    }
                    zonghe.setTextColor(SeekListActivity.this.getResources().getColor(R.color.colorTab));
                    xiaoliang.setTextColor(SeekListActivity.this.getResources().getColor(R.color.colorText));
                    price_text.setTextColor(SeekListActivity.this.getResources().getColor(R.color.colorText));
                    zclick = true;
                    xclick = false;
                    pclick = false;
                    Drawable drawable = mContext.getResources().getDrawable(R.drawable.price_image);
                    // 这一步必须要做,否则不会显示.
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    price_text.setCompoundDrawables(null, null, drawable, null);
                    pimgclick = true;
                }
                break;
            case R.id.xiaoliang:
                if (!xclick) {
                    classCommodAdapter.removeAll();
                    record = 5;
                    mPage = 1;
                    if (count == null || count.equals("")) {
                        commodityPresenter.request(id, record, "", 1, 1);
                    } else {
                        commodityPresenter.request(id, record, count, 1, 1);
                    }
                    xiaoliang.setTextColor(SeekListActivity.this.getResources().getColor(R.color.colorTab));
                    zonghe.setTextColor(SeekListActivity.this.getResources().getColor(R.color.colorText));
                    price_text.setTextColor(SeekListActivity.this.getResources().getColor(R.color.colorText));
                    zclick = false;
                    xclick = true;
                    pclick = false;
                    Drawable drawable = mContext.getResources().getDrawable(R.drawable.price_image);
                    // 这一步必须要做,否则不会显示.
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    price_text.setCompoundDrawables(null, null, drawable, null);
                    pimgclick = true;
                }
                break;
            case R.id.price:
                if (!pclick) {
                    price_text.setTextColor(SeekListActivity.this.getResources().getColor(R.color.colorTab));
                    xiaoliang.setTextColor(SeekListActivity.this.getResources().getColor(R.color.colorText));
                    zonghe.setTextColor(SeekListActivity.this.getResources().getColor(R.color.colorText));
                    zclick = false;
                    xclick = false;
                    if (pimgclick) {
                        classCommodAdapter.removeAll();
                        record = 3;
                        mPage = 1;
                        if (count == null || count.equals("")) {
                            commodityPresenter.request(id, record, "", 1, 1);
                        } else {
                            commodityPresenter.request(id, record, count, 1, 1);
                        }
                        Drawable drawable = mContext.getResources().getDrawable(R.drawable.price_top);
                        // 这一步必须要做,否则不会显示.
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        price_text.setCompoundDrawables(null, null, drawable, null);
                        pimgclick = false;
                    } else {
                        classCommodAdapter.removeAll();
                        mPage = 1;
                        record = 4;
                        if (count == null || count.equals("")) {
                            commodityPresenter.request(id, record, "", 1, 1);
                        } else {
                            commodityPresenter.request(id, record, count, 1, 1);
                        }
                        Drawable drawable = mContext.getResources().getDrawable(R.drawable.price_button);
                        // 这一步必须要做,否则不会显示.
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        price_text.setCompoundDrawables(null, null, drawable, null);
                        pimgclick = true;
                    }
                }
                break;
            case R.id.qiehuan:
                if (!qclick) {
                    qiehuan.setImageResource(R.drawable.shuqiehuan);
                    xrecycler.setLayoutManager(new LinearLayoutManager(this));
                    classCommodAdapter.setState(1);
                    qclick = true;
                } else {
                    qiehuan.setImageResource(R.drawable.qiehuan);
                    xrecycler.setLayoutManager(new GridLayoutManager(this, 2));
                    classCommodAdapter.setState(2);
                    qclick = false;
                }
                break;
        }
    }

}
