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
import com.yikangcheng.admin.yikang.activity.adapter.AccomplishAdapter_A;
import com.yikangcheng.admin.yikang.activity.orderstatus.CloseTheDealActivity;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.DeleteOrderBean;
import com.yikangcheng.admin.yikang.bean.PaidBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.DeleteOrderIdPresenter;
import com.yikangcheng.admin.yikang.presenter.PaidPresenter;
import com.yikangcheng.admin.yikang.util.SpacesItemDecoration;

import java.util.ArrayList;

/**
 * Created by lenovo on 2019/5/20.
 * WF
 */
public class AccomplishFragment extends BaseFragment implements ICoreInfe {
    private RecyclerView mRlvFragmentAccomplish;
    private AccomplishAdapter_A mAccomplishAdapter_a;
    private ImageView mImgFragmentAccomplish;
    private ImageView mImgFragmentAccomplishQuguanghuang;
    private RelativeLayout mRelativeLayout;
    private int mPage = 1;
    private int mDeleteItemPostion;
    private SmartRefreshLayout mSmartRefreshLayout;

    @Override
    protected void initView(View view) {
        mRlvFragmentAccomplish = view.findViewById(R.id.rlv_fragment_accomplish);
        mImgFragmentAccomplish = view.findViewById(R.id.img_fragment_accomplish);
        mImgFragmentAccomplishQuguanghuang = view.findViewById(R.id.img_fragment_accomplish_quguanghuang);
        mRelativeLayout = view.findViewById(R.id.relativeLayout);
        mSmartRefreshLayout = view.findViewById(R.id.refreshLayout);

        Glide.with(getContext()).load(R.drawable.dongtu).into(mImgFragmentAccomplish);

        //布局走向
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRlvFragmentAccomplish.setLayoutManager(linearLayoutManager);

        ArrayList<PaidBean.OrderBean> orderBeans = new ArrayList<>();
        //创建适配器
        mAccomplishAdapter_a = new AccomplishAdapter_A(orderBeans, getContext());
        //绑定适配器
        mRlvFragmentAccomplish.setAdapter(mAccomplishAdapter_a);

        //接口回调 跳转页面
        mAccomplishAdapter_a.setOnClickListener(new AccomplishAdapter_A.OnClickListener() {
            @Override
            public void OnClickListener(View v, int orderId) {
                Intent intent = new Intent(getContext(), CloseTheDealActivity.class);
                intent.putExtra("orderId", orderId);
                startActivity(intent);
            }
        });

        /**
         * P层
         */
        initMvp(mPage);

        //解决滑动不流畅
        mRlvFragmentAccomplish.setHasFixedSize(true);
        mRlvFragmentAccomplish.setNestedScrollingEnabled(false);

        //解决item间距
        int spanCount_tuijian = 1; // 3 columns
        int spacing_tuijian = 20; // 50px
        boolean includeEdge_tuijian = false;
        mRlvFragmentAccomplish.addItemDecoration(new SpacesItemDecoration(spanCount_tuijian, spacing_tuijian, includeEdge_tuijian));


        //显示隐藏
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
     * 删除订单
     */
    private void initDelete() {
        mAccomplishAdapter_a.setOnClickListenerDelete(new AccomplishAdapter_A.OnClickListenerDelete() {
            @Override
            public void OnClickListener(View v, int position) {
                mDeleteItemPostion = position;
                int orderId = mAccomplishAdapter_a.mList.get(position).getOrderId();
                DeleteOrderIdPresenter deleteOrderIdPresenter = new DeleteOrderIdPresenter(new delete());
                deleteOrderIdPresenter.request(orderId);
            }
        });
    }

    //p层
    private void initMvp(int page) {
        PaidPresenter paidPresenter = new PaidPresenter(this);
        paidPresenter.request(11, page, "SUCCESS");
    }

    //上拉加载
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
        return R.layout.fragment_accomplish;
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
            mAccomplishAdapter_a.mList.remove(mDeleteItemPostion);
            mAccomplishAdapter_a.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        PaidBean entity = (PaidBean) request.getEntity();
        mAccomplishAdapter_a.addAll(entity.getOrder());
    }

    @Override
    public void fail(ApiException e) {

    }
}
