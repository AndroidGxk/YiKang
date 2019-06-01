package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.particulars.ParticularsActivity;
import com.yikangcheng.admin.yikang.activity.seek.SeekActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.ClassifyCommodityListBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.activity.adapter.ClassCommodAdapter;
import com.yikangcheng.admin.yikang.presenter.CommodityPresenter;

import java.util.List;

import me.jessyan.autosize.internal.CustomAdapt;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * 搜索列表页面
 */
public class SeekListActivity extends BaseActivtiy implements CustomAdapt, ICoreInfe, View.OnClickListener {

    private TextView edit_seek_sousuo;
    private CommodityPresenter commodityPresenter;
    private XRecyclerView xrecycler;
    private ClassCommodAdapter classCommodAdapter;
    private TextView zonghe, xiaoliang, price_text;
    private RelativeLayout price;
    private ImageView qiehuan, back_img;
    private boolean zclick = true, xclick, pclick, qclick = true, pimgclick = true;
    private int id;
    private String count;
    private SmartRefreshLayout refreshLayout;
    private PromptDialog promptDialog;
    private int record;
    //页数
    private int mPage;

    @Override
    protected void initView() {
        //创建对象
        promptDialog = new PromptDialog(SeekListActivity.this);
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(1).loadingDuration(1000);
        promptDialog.showLoading("正在加载");
        final Intent intent = getIntent();
        count = intent.getStringExtra("count");
        id = intent.getIntExtra("id", 000);
        xrecycler = (XRecyclerView) findViewById(R.id.xrecycler);
        //搜素框
        edit_seek_sousuo = (TextView) findViewById(R.id.EditTixt_activity_seek_sousuo);
        zonghe = (TextView) findViewById(R.id.zonghe);
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
        commodityPresenter = new CommodityPresenter(this);
        if (count == null || count.equals("")) {
            commodityPresenter.request(id, 1, "", 1, 1);
        } else {
            commodityPresenter.request(id, 1, count, 1, 1);
        }
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
                mPage = 1;
                classCommodAdapter.removeAll();
                commodityPresenter.request(id, record, "", 1, mPage);
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPage++;
                commodityPresenter.request(id, record, "", 1, mPage);
                refreshLayout.finishLoadMore();
            }
        });
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
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 720;
    }

    @Override
    public void success(Object data) {
        promptDialog.dismiss();
        Request request = (Request) data;
        ClassifyCommodityListBean classifyCommodityListBean = (ClassifyCommodityListBean) request.getEntity();
        List<ClassifyCommodityListBean.CommodityListBean> commodityList = classifyCommodityListBean.getCommodityList();
        classCommodAdapter.addAll(commodityList);
    }

    @Override
    public void fail(ApiException e) {

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
                    mPage=1;
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
                    mPage=1;
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
                        mPage=1;
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
                        mPage=1;
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
