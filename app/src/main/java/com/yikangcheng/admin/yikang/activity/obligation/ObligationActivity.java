package com.yikangcheng.admin.yikang.activity.obligation;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.MainActivity;
import com.yikangcheng.admin.yikang.activity.adapter.ObligationAdapter;
import com.yikangcheng.admin.yikang.activity.orderstatus.WaitForpaymentActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.DeleteOrderBean;
import com.yikangcheng.admin.yikang.bean.ObligationBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.DeleteOrderIdPresenter;
import com.yikangcheng.admin.yikang.presenter.ObligationPresenter;
import com.yikangcheng.admin.yikang.util.SpacesItemDecoration;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;
import com.yikangcheng.admin.yikang.util.TwoBallRotationProgressBar;

import java.util.ArrayList;

import me.jessyan.autosize.internal.CustomAdapt;
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * 我的——————待付款页面
 */
public class ObligationActivity extends BaseActivtiy implements ICoreInfe, CustomAdapt {

    private ImageView mImgActivityObligationFanhui;
    private RelativeLayout mToolbarActivityObligation;
    private RecyclerView mRlvActivityObligation;
    private ObligationAdapter mObligationAdapter;
    private int mPage = 1;
    private int mDeleteItemPostion;
    private SmartRefreshLayout mRefreshLayout;
    private ImageView mImgFragmentAccomplish;
    private ImageView mImgFragmentAccomplishQuguanghuang;
    private RelativeLayout mRelativeLayout;
    private int mDeletePosition;
    private int width;
    private PromptDialog mPromptDialog;
    private ImageView mImgBut;
    private ArrayList<ObligationBean.OrderBean> mOrderBeans;
    private TwoBallRotationProgressBar progress;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        //创建对象
        mPromptDialog = new PromptDialog(this);
        //设置自定义属性
        mPromptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(1000);
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        mImgActivityObligationFanhui = (ImageView) findViewById(R.id.img_activity_obligation_fanhui);
        mToolbarActivityObligation = (RelativeLayout) findViewById(R.id.toolbar_activity_obligation);
        mRlvActivityObligation = (RecyclerView) findViewById(R.id.rlv_activity_obligation);
        progress = (TwoBallRotationProgressBar) findViewById(R.id.progress);
        mRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        mImgFragmentAccomplish = (ImageView) findViewById(R.id.img_fragment_accomplish);
        mImgFragmentAccomplishQuguanghuang = (ImageView) findViewById(R.id.img_fragment_accomplish_quguanghuang);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        mImgBut = (ImageView) findViewById(R.id.imgBut);
        /**
         * 点击返回图标关闭当前页面
         */
        mImgActivityObligationFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**
         * 布局走向
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRlvActivityObligation.setLayoutManager(linearLayoutManager);

        mOrderBeans = new ArrayList<>();
        //创建适配器
        mObligationAdapter = new ObligationAdapter(this, mOrderBeans);
        //绑定适配器
        mRlvActivityObligation.setAdapter(mObligationAdapter);

        mObligationAdapter.setOnClickListener(new ObligationAdapter.OnClickListener() {
            @Override
            public void OnClickListener(View v, int orderId, int position) {
                /**
                 * 点击跳到详情的下标  删除后回来删除条目用到
                 */
                mDeletePosition = position;
                Intent intent = new Intent(ObligationActivity.this, WaitForpaymentActivity.class);
                intent.putExtra("orderId_wait", orderId);
                startActivityForResult(intent, 1);
            }
        });

        //点击去逛逛跳转首页
        mImgFragmentAccomplishQuguanghuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ObligationActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        /**
         * 一键置顶
         */
        mRlvActivityObligation.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalDy = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy -= dy;
                if (totalDy < 0) {
                    mImgBut.setVisibility(View.VISIBLE);
                } else {
                    mImgBut.setVisibility(View.GONE);
                }
            }
        });
        mImgBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRlvActivityObligation.smoothScrollToPosition(0);
            }
        });

        Glide.with(this).load(R.drawable.dongtu).into(mImgFragmentAccomplish);

        /**
         * P层
         */
        initMvp(mPage);

        //解决滑动不流畅
        mRlvActivityObligation.setHasFixedSize(true);
        mRlvActivityObligation.setNestedScrollingEnabled(false);
        //设置item间距
        int spanCount_tuijian = 1; // 3 columns
        int spacing_tuijian = 20; // 50px
        boolean includeEdge_tuijian = false;
        mRlvActivityObligation.addItemDecoration(new SpacesItemDecoration(spanCount_tuijian, spacing_tuijian, includeEdge_tuijian));


        /**
         * 上拉加载
         */
        initShuaXinJiaZai();

        /**
         * 点击垃圾桶删除订单
         */
        initDelete();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            String delete = data.getStringExtra("delete");
            if (delete.equals("delete")) {
                mObligationAdapter.mList.remove(mDeletePosition);
                mObligationAdapter.notifyDataSetChanged();
            }
        }
    }


    //点击删除
    private void initDelete() {
        mObligationAdapter.setOnClickListenerDelete(new ObligationAdapter.OnClickListenerDelete() {
            @Override
            public void OnClickListener(View v, int position) {
                mDeleteItemPostion = position;
                mPromptDialog.showWarnAlert("你确定要删除订单吗？", new PromptButton("取消", new PromptButtonListener() {
                    @Override
                    public void onClick(PromptButton button) {
                    }
                }), confirm);
            }
        });
    }

    //按钮的定义，创建一个按钮的对象
    PromptButton confirm = new PromptButton("确定", new PromptButtonListener() {
        @Override
        public void onClick(PromptButton button) {
            int orderId = mObligationAdapter.mList.get(mDeleteItemPostion).getOrderId();
            DeleteOrderIdPresenter deleteOrderIdPresenter = new DeleteOrderIdPresenter(new delete());
            deleteOrderIdPresenter.request(orderId);
        }
    });


    private void initMvp(int page) {
        ObligationPresenter obligationPresenter = new ObligationPresenter(this);
        obligationPresenter.request(getLogUser(ObligationActivity.this).getId(), page, "INIT");
    }

    //上拉加载
    private void initShuaXinJiaZai() {
        //刷新的监听事件
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //请求数据
//                mPage = 1;
//                initMvp(mPage);
                refreshLayout.finishRefresh();  //刷新完成
            }
        });
        //加载的监听事件
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                initMvp(mPage);
                refreshlayout.finishLoadmore();      //加载完成
            }
        });

    }


    @Override
    protected void initEventData() {
        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_obligation;
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

    public class delete implements ICoreInfe {

        public DeleteOrderBean mMEntity;

        @Override
        public void success(Object data) {
            Request request = (Request) data;
            mMEntity = (DeleteOrderBean) request.getEntity();
            //有需要在这打印一下message的返回值现在返回的是空的  让后台看一下  做一个判断
            // Log.e("aaa", "success: "+mMEntity.get );
            mObligationAdapter.mList.remove(mDeleteItemPostion);
            mObligationAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }


    @Override
    public void success(Object data) {
        Request request = (Request) data;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.GONE);
                progress.stopAnimator();
            }
        }, 500);
        ObligationBean entity = (ObligationBean) request.getEntity();
        if (entity.getOrder() == null) {
            mRefreshLayout.setVisibility(View.GONE);
            mImgBut.setVisibility(View.GONE);
            mRelativeLayout.setVisibility(View.VISIBLE);
        } else {
            mRelativeLayout.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            /**
             * 上拉加载
             */
            initShuaXinJiaZai();
        }
        mObligationAdapter.addAll(entity.getOrder());

    }

    @Override
    public void fail(ApiException e) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.GONE);
                progress.stopAnimator();
            }
        }, 500);
        mRefreshLayout.setVisibility(View.GONE);
        mImgBut.setVisibility(View.GONE);
        mRelativeLayout.setVisibility(View.VISIBLE);
    }
}
