package com.yikangcheng.admin.yikang.activity.obligation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.CanceledAdapter_A;
import com.yikangcheng.admin.yikang.activity.orderstatus.FackOfActivity;
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

import java.util.ArrayList;
import java.util.List;

import me.jessyan.autosize.internal.CustomAdapt;

public class CanceledActivity extends BaseActivtiy implements ICoreInfe, CustomAdapt {

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

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        back_img = (ImageView) findViewById(R.id.back_img);
        mToolbarActivityCanceled = (RelativeLayout) findViewById(R.id.toolbar_activity_canceled);
        mRlvActivityCanceled = (RecyclerView) findViewById(R.id.rlv_activity_canceled);
        mRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
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
                Intent intent = new Intent(CanceledActivity.this, FackOfActivity.class);
                intent.putExtra("orderId_fack", orderId);
                startActivityForResult(intent, 5);
            }
        });

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
                int orderId = mCanceledAdapter_a.mList.get(position).getOrderId();
                DeleteOrderIdPresenter deleteOrderIdPresenter = new DeleteOrderIdPresenter(new delete());
                deleteOrderIdPresenter.request(orderId);
            }
        });
    }


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
        return R.layout.activity_canceled;
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
        mCanceledAdapter_a.allAll(entity.getOrder());
    }

    @Override
    public void fail(ApiException e) {

    }
}
