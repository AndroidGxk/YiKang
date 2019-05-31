package com.yikangcheng.admin.yikang.activity.obligation;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yikangcheng.admin.yikang.R;
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

import java.util.ArrayList;
import java.util.List;

public class PaidActivity extends BaseActivtiy implements ICoreInfe {


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

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);

        mImgActivityPaidFanhui = (ImageView) findViewById(R.id.img_activity_paid_fanhui);
        mToolbarActivityPaid = (RelativeLayout) findViewById(R.id.toolbar_activity_paid);
        mRlvActivityPaid = (RecyclerView) findViewById(R.id.rlv_activity_paid);
        mRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        mImgFragmentAccomplish = (ImageView) findViewById(R.id.img_fragment_accomplish);
        mImgFragmentAccomplishQuguanghuang = (ImageView) findViewById(R.id.img_fragment_accomplish_quguanghuang);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);


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
            public void OnClickListener(View v, int orderId) {
                Intent intent = new Intent(PaidActivity.this, CloseTheDealActivity.class);
                intent.putExtra("orderId", orderId);
                startActivity(intent);
            }
        });

        /**
         * P层
         */
        initMvp(mPage);
        //解决滑动不流畅
        mRlvActivityPaid.setHasFixedSize(true);
        mRlvActivityPaid.setNestedScrollingEnabled(false);

        //item之間間距
        int spanCount_tuijian = 1; // 3 columns
        int spacing_tuijian = 20; // 50px
        boolean includeEdge_tuijian = false;
        mRlvActivityPaid.addItemDecoration(new SpacesItemDecoration(spanCount_tuijian, spacing_tuijian, includeEdge_tuijian));

        //设置显示隐藏
        if (orderBeans.size() < 0) {
            mRelativeLayout.setVisibility(View.VISIBLE);
            mRefreshLayout.setVisibility(View.GONE);
        } else {
            mRelativeLayout.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
        }


        /**
         * 上拉加载
         */
        initShuaXinJiaZai();


        /**
         * 刪除
         */
        initDelete();
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
                int orderId = mPaidAdapter_a.mList.get(position).getOrderId();
                DeleteOrderIdPresenter deleteOrderIdPresenter = new DeleteOrderIdPresenter(new delete());
                deleteOrderIdPresenter.request(orderId);
            }
        });
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
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPage++;
                initMvp(mPage);
                refreshLayout.finishLoadMore();      //加载完成
                //refreshLayout.finishLoadMoreWithNoMoreData();  //全部加载完成,没有数据了调用此方法  这个方法调用了就加载一夜再也不加载了
            }
        });
    }


    @Override
    protected void initEventData() {

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
        mPaidAdapter_a.addAll(entity.getOrder());
    }

    @Override
    public void fail(ApiException e) {

    }
}
