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
import com.yikangcheng.admin.yikang.activity.adapter.PaidAdapter_A;
import com.yikangcheng.admin.yikang.activity.orderstatus.CloseTheDealActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.DeleteOrderBean;
import com.yikangcheng.admin.yikang.bean.PaidBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.DeleteOrderIdPresenter;
import com.yikangcheng.admin.yikang.presenter.PaidPresenter;
import com.yikangcheng.admin.yikang.util.SpacesItemDecoration;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;
import com.yikangcheng.admin.yikang.util.TwoBallRotationProgressBar;

import java.util.ArrayList;
import java.util.List;

import me.jessyan.autosize.internal.CustomAdapt;
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

public class PaidActivity extends BaseActivtiy implements ICoreInfe  {


    private ImageView mImgActivityPaidFanhui;
    private RelativeLayout mToolbarActivityPaid;
    private RecyclerView mRlvActivityPaid;
    private PaidAdapter_A mPaidAdapter_a;
    private int mDeleteItemPostion;
    private int mPage = 1;
    private SmartRefreshLayout mRefreshLayout;
    private ImageView mImgFragmentAccomplish;
    private ImageView mImgFragmentAccomplishQuguanghuang;
    private RelativeLayout mRelativeLayout;
    private int mDeletePosition;
    private int width;
    private PromptDialog mPromptDialog;
    private ImageView mImgBut;
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
        //找控件
        mImgActivityPaidFanhui = (ImageView) findViewById(R.id.img_activity_paid_fanhui);
        mToolbarActivityPaid = (RelativeLayout) findViewById(R.id.toolbar_activity_paid);
        mRlvActivityPaid = (RecyclerView) findViewById(R.id.rlv_activity_paid);
        mRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        mImgFragmentAccomplish = (ImageView) findViewById(R.id.img_activity_pard);
        mImgFragmentAccomplishQuguanghuang = (ImageView) findViewById(R.id.img_fragment_accomplish_quguanghuang);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        progress = (TwoBallRotationProgressBar) findViewById(R.id.progress);
        mImgBut = (ImageView) findViewById(R.id.imgBut);

        /**
         * 点击返回图标关闭当前页面
         */
        mImgActivityPaidFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //布局走向
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRlvActivityPaid.setLayoutManager(linearLayoutManager);
        //创建适配器
        List<PaidBean.OrderBean> orderBeans = new ArrayList<>();
        mPaidAdapter_a = new PaidAdapter_A(this, orderBeans);
        mRlvActivityPaid.setAdapter(mPaidAdapter_a);

        mPaidAdapter_a.setOnClickListener(new PaidAdapter_A.OnClickListener() {
            @Override
            public void OnClickListener(View v, int orderId, int position) {
                /**
                 * 点击跳到详情的下标  删除后回来删除条目用到
                 */
                mDeletePosition = position;
                Intent intent = new Intent(PaidActivity.this, CloseTheDealActivity.class);
                intent.putExtra("orderId", orderId);
                startActivityForResult(intent, 3);
            }
        });
        //点击去逛逛跳转首页
        mImgFragmentAccomplishQuguanghuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaidActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        /**
         * 一键置顶
         */
        mRlvActivityPaid.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                mRlvActivityPaid.smoothScrollToPosition(0);
            }
        });

        Glide.with(this).load(R.drawable.dongtu).into(mImgFragmentAccomplish);

        /**
         * P层
         */
        initMvp(mPage);
        //解决滑动不流畅
        mRlvActivityPaid.setHasFixedSize(true);
        mRlvActivityPaid.setNestedScrollingEnabled(false);
        int spanCount_tuijian = 1; // 3 columns
        int spacing_tuijian = 20; // 50px
        boolean includeEdge_tuijian = false;
        mRlvActivityPaid.addItemDecoration(new SpacesItemDecoration(spanCount_tuijian, spacing_tuijian, includeEdge_tuijian));


        /**
         * 上拉加载
         */
        initShuaXinJiaZai();


        /**
         * 刪除
         */
        initDelete();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3 && resultCode == 4) {
            String delete = data.getStringExtra("delete");
            if (delete.equals("delete")) {
                mPaidAdapter_a.mList.remove(mDeletePosition);
                mPaidAdapter_a.notifyDataSetChanged();
            }
        }
    }

    /**
     * 刪除p
     *
     * @param page
     */
    private void initMvp(int page) {
        //p层
        PaidPresenter paidPresenter = new PaidPresenter(this);
        paidPresenter.request(getLogUser(PaidActivity.this).getId(), page, "SUCCESS");

    }

    private void initDelete() {
        mPaidAdapter_a.setOnClickListenerDelete(new PaidAdapter_A.OnClickListenerDelete() {
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
            int orderId = mPaidAdapter_a.mList.get(mDeleteItemPostion).getOrderId();
            DeleteOrderIdPresenter deleteOrderIdPresenter = new DeleteOrderIdPresenter(new delete());
            deleteOrderIdPresenter.request(orderId);
        }
    });


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
        return R.layout.activity_paid;
    }

    @Override
    protected void createPresenter() {

    }


    public class delete implements ICoreInfe {

        public DeleteOrderBean mMEntity;

        @Override
        public void success(Object data) {
            Request request = (Request) data;
            mMEntity = (DeleteOrderBean) request.getEntity();
            //有需要在这打印一下message的返回值现在返回的是空的  让后台看一下  做一个判断
            // Log.e("aaa", "success: "+mMEntity.get );
            mPaidAdapter_a.mList.remove(mDeleteItemPostion);
            mPaidAdapter_a.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }


    @Override
    public void success(Object data) {
        Request request = (Request) data;
        PaidBean entity = (PaidBean) request.getEntity();
        if (entity == null) {
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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.GONE);
                progress.stopAnimator();
            }
        }, 500);
        mPaidAdapter_a.addAll(entity.getOrder());
        //设置显示隐藏
        if (entity == null) {
            mRelativeLayout.setVisibility(View.VISIBLE);
            mRefreshLayout.setVisibility(View.GONE);
            mRlvActivityPaid.setVisibility(View.GONE);
            Glide.with(PaidActivity.this).load(R.drawable.dongtu).into(mImgFragmentAccomplish);
        } else {
            mRelativeLayout.setVisibility(View.GONE);
            mRlvActivityPaid.setVisibility(View.VISIBLE);
            mRefreshLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void fail(ApiException e) {
        mRefreshLayout.setVisibility(View.GONE);
        mImgBut.setVisibility(View.GONE);
        mRelativeLayout.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.GONE);
                progress.stopAnimator();
            }
        }, 500);
    }
}
