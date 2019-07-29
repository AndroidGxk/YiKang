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
import com.yikangcheng.admin.yikang.activity.adapter.CanceledAdapter_A;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.CloseBean;
import com.yikangcheng.admin.yikang.bean.DeleteOrderBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.ClosePresenter;
import com.yikangcheng.admin.yikang.presenter.DeleteOrderIdPresenter;
import com.yikangcheng.admin.yikang.util.SpacesItemDecoration;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;
import com.yikangcheng.admin.yikang.util.TwoBallRotationProgressBar;

import java.util.ArrayList;
import java.util.List;

import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * 已取消订单详情
 */
public class CanceledActivity extends BaseActivtiy implements ICoreInfe  {

    private ImageView back_img;
    private RelativeLayout mToolbarActivityCanceled;
    private RecyclerView mRlvActivityCanceled;
    private CanceledAdapter_A mCanceledAdapter_a;
    private ImageView mBackImg;
    private SmartRefreshLayout mRefreshLayout;
    private ImageView mImgFragmentAccomplish;
    private ImageView mImgFragmentAccomplishQuguanghuang;
    private RelativeLayout mRelativeLayout;
    private int mDeleteItemPostion;
    private int mPage = 1;
    private int mDeletePosition;
    private int height;
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
        height = display.getHeight();
        back_img = (ImageView) findViewById(R.id.back_img);
        mImgBut = (ImageView) findViewById(R.id.imgBut);
        mToolbarActivityCanceled = (RelativeLayout) findViewById(R.id.toolbar_activity_canceled);
        mRlvActivityCanceled = (RecyclerView) findViewById(R.id.rlv_activity_canceled);
        mRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        progress = (TwoBallRotationProgressBar) findViewById(R.id.progress);
        mImgFragmentAccomplish = (ImageView) findViewById(R.id.img_fragment_accomplish);
        mImgFragmentAccomplishQuguanghuang = (ImageView) findViewById(R.id.img_fragment_accomplish_quguanghuang);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        /**
         * 点击返回图标关闭当前页面
         */
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        /**
         * 布局走向
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRlvActivityCanceled.setLayoutManager(linearLayoutManager);

        List<CloseBean.OrderBean> orderBeans = new ArrayList<>();
        mCanceledAdapter_a = new CanceledAdapter_A(orderBeans, this);
        mRlvActivityCanceled.setAdapter(mCanceledAdapter_a);

        mCanceledAdapter_a.setOnClickListener(new CanceledAdapter_A.OnClickListener() {
            @Override
            public void OnClickListener(View v, int orderId, int position) {
                /**
                 * 点击跳到详情的下标  删除后回来删除条目用到
                 */
                mDeletePosition = position;
//                Intent intent = new Intent(CanceledActivity.this, FackOfActivity.class);
//                intent.putExtra("orderId_fack", orderId);
//                startActivityForResult(intent, 5);
            }
        });

        //点击去逛逛跳转首页
        mImgFragmentAccomplishQuguanghuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CanceledActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        /**
         * 一键置顶
         */
        mRlvActivityCanceled.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                mRlvActivityCanceled.smoothScrollToPosition(0);
            }
        });
        Glide.with(this).load(R.drawable.dongtu).into(mImgFragmentAccomplish);

        /**
         * P层
         */
        initMvp(mPage);

        //解决滑动不流畅
        mRlvActivityCanceled.setHasFixedSize(true);
        mRlvActivityCanceled.setNestedScrollingEnabled(false);
        //item之間空襲
        int spanCount_tuijian = 1; // 3 columns
        int spacing_tuijian = 20; // 50px
        boolean includeEdge_tuijian = false;
        mRlvActivityCanceled.addItemDecoration(new SpacesItemDecoration(spanCount_tuijian, spacing_tuijian, includeEdge_tuijian));


        /**
         * 上拉加载
         */
        initShuaXinJiaZai();


        initDelete();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5 && resultCode == 6) {
            String delete = data.getStringExtra("delete");
            if (delete.equals("delete")) {
                mCanceledAdapter_a.mList.remove(mDeletePosition);
                mCanceledAdapter_a.notifyDataSetChanged();
            }
        }
    }

    private void initDelete() {
        mCanceledAdapter_a.setOnClickListenerDelete(new CanceledAdapter_A.OnClickListenerDelete() {
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
            int orderId = mCanceledAdapter_a.mList.get(mDeleteItemPostion).getOrderId();
            DeleteOrderIdPresenter deleteOrderIdPresenter = new DeleteOrderIdPresenter(new delete());
            deleteOrderIdPresenter.request(orderId);
        }
    });


    private void initMvp(int page) {
        ClosePresenter closePresenter = new ClosePresenter(this);
        closePresenter.request(getLogUser(CanceledActivity.this).getId(), page, "CANCEL");
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
        return R.layout.activity_canceled;
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
            mCanceledAdapter_a.mList.remove(mDeleteItemPostion);
            mCanceledAdapter_a.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }


    @Override
    public void success(Object data) {
        Request request = (Request) data;
        CloseBean entity = (CloseBean) request.getEntity();
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
        mCanceledAdapter_a.allAll(entity.getOrder());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.GONE);
                progress.stopAnimator();
            }
        }, 500);
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
