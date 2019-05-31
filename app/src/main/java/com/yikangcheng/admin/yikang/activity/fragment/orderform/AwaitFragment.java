package com.yikangcheng.admin.yikang.activity.fragment.orderform;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.AwaitAdapter;
import com.yikangcheng.admin.yikang.activity.orderstatus.WaitForpaymentActivity;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.DeleteOrderBean;
import com.yikangcheng.admin.yikang.bean.ObligationBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.DeleteOrderIdPresenter;
import com.yikangcheng.admin.yikang.presenter.ObligationPresenter;
import com.yikangcheng.admin.yikang.util.SpacesItemDecoration;

import java.util.ArrayList;

/**
 * Created by lenovo on 2019/5/20.
 * WF
 */
public class AwaitFragment extends BaseFragment implements ICoreInfe {
    private ImageView mImgFragmentAwait;
    private ImageView mImgFragmentAwaitQuguanghuang;
    private RecyclerView mRlvFragmentAllDingdan;
    private RelativeLayout mRelativeLayout;
    private AwaitAdapter mAwaitAdapter;
    private SmartRefreshLayout mSmartRefreshLayout;
    private int mDeleteItemPostion;
    private int mPage = 1;

    @Override
    protected void initView(View view) {
        //动图
        mImgFragmentAwait = view.findViewById(R.id.img_fragment_await);
        mSmartRefreshLayout = view.findViewById(R.id.refreshLayout);
        //去逛逛
        mImgFragmentAwaitQuguanghuang = view.findViewById(R.id.img_fragment_await_quguanghuang);
        mRelativeLayout = view.findViewById(R.id.relativeLayout);
        mRlvFragmentAllDingdan = view.findViewById(R.id.rlv_fragment_await_dingdan);
        //加载动图
        Glide.with(this).load(R.drawable.dongtu).into(mImgFragmentAwait);


        /**
         * 布局走向
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRlvFragmentAllDingdan.setLayoutManager(linearLayoutManager);
        ArrayList<ObligationBean.OrderBean> orderBeans = new ArrayList<>();
        mAwaitAdapter = new AwaitAdapter(orderBeans, getContext());
        mRlvFragmentAllDingdan.setAdapter(mAwaitAdapter);


        /**
         * 点击跳转订单详情页面
         */
        mAwaitAdapter.setOnClickListener(new AwaitAdapter.OnClickListener() {
            @Override
            public void OnClickListener(View v, int orderId) {
                Intent intent = new Intent(getActivity(), WaitForpaymentActivity.class);
                intent.putExtra("orderId_wait", orderId);
                startActivity(intent);
            }
        });
        /**
         * P层
         */
        initMvp(mPage);


//        //解决滑动不流畅
//        mRlvFragmentAllDingdan.setHasFixedSize(true);
//        mRlvFragmentAllDingdan.setNestedScrollingEnabled(false);


        //item之间的间距
        int spanCount_tuijian = 1; // 3 columns
        int spacing_tuijian = 20; // 50px
        boolean includeEdge_tuijian = false;
        mRlvFragmentAllDingdan.addItemDecoration(new SpacesItemDecoration(spanCount_tuijian, spacing_tuijian, includeEdge_tuijian));


        /**
         * 判断页面显示隐藏
         */
        if (orderBeans.size() < 0) {
            mRelativeLayout.setVisibility(View.VISIBLE);
            mSmartRefreshLayout.setVisibility(View.GONE);
        } else {
            mRelativeLayout.setVisibility(View.GONE);
            mSmartRefreshLayout.setVisibility(View.VISIBLE);
        }

        /**
         * 上拉加载
         */
        initShuaXinJiaZai();

        /**
         * 点击垃圾桶删除订单
         */
        initDelete();
    }

    /**
     * 删除订单按钮
     */
    private void initDelete() {
        mAwaitAdapter.setOnClickListenerDelete(new AwaitAdapter.OnClickListenerDelete() {
            @Override
            public void OnClickListener(View v, int position) {
                mDeleteItemPostion = position;
                int orderId = mAwaitAdapter.mList.get(position).getOrderId();
                DeleteOrderIdPresenter deleteOrderIdPresenter = new DeleteOrderIdPresenter(new delete());
                deleteOrderIdPresenter.request(orderId);
            }
        });
    }


    /**
     * 删除订单P层
     *
     * @param page
     */
    private void initMvp(int page) {
        ObligationPresenter obligationPresenter = new ObligationPresenter(this);
        obligationPresenter.request(getLogUser(getContext()).getId(), page, "INIT");

    }

    /**
     * 上拉刷新
     */
    private void initShuaXinJiaZai() {
        //刷新的监听事件
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                //请求数据
//                mPage = 1;
//                initMvp(mPage);
                refreshLayout.finishRefresh();  //刷新完成
            }
        });
        //加载的监听事件
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
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
    protected void initData() {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_await;
    }

    /**
     * 删除订单
     */
    public class delete implements ICoreInfe {

        public DeleteOrderBean mMEntity;

        @Override
        public void success(Object data) {
            Request request = (Request) data;
            mMEntity = (DeleteOrderBean) request.getEntity();
            //有需要在这打印一下message的返回值现在返回的是空的  让后台看一下  做一个判断
            // Log.e("aaa", "success: "+mMEntity.get );
            mAwaitAdapter.mList.remove(mDeleteItemPostion);
            mAwaitAdapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        ObligationBean entity = (ObligationBean) request.getEntity();
        mAwaitAdapter.addAll(entity.getOrder());
    }

    @Override
    public void fail(ApiException e) {

    }
}
