package com.yikangcheng.admin.yikang.activity.fragment.coupon;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.CommissRecyclerAdapter;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.DiscountBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.DiscountPresenter;

import java.util.List;

/**
 * 作者：古祥坤 on 2019/5/25 18:56
 * 邮箱：1724959985@qq.com
 * 可使用的优惠券
 */
public class CommFragment extends BaseFragment implements ICoreInfe {

    private RecyclerView xrecycler;
    private DiscountPresenter discountPresenter;
    private CommissRecyclerAdapter commissRecyclerAdapter;
    private SmartRefreshLayout mRefreshLayout;
    private ImageView zanwuyouhuiquan;
    @Override
    protected void initView(View view) {
        xrecycler = view.findViewById(R.id.recycler);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        zanwuyouhuiquan = view.findViewById(R.id.zanwuyouhuiquan);
        xrecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        commissRecyclerAdapter = new CommissRecyclerAdapter(getContext());
        xrecycler.setAdapter(commissRecyclerAdapter);
        discountPresenter = new DiscountPresenter(this);
        discountPresenter.request(11);
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
        zanwuyouhuiquan.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.commfragment_item;
    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        DiscountBean entity = (DiscountBean) request.getEntity();
        List<DiscountBean.CouponCodeListBean> couponCodeList = entity.getCouponCodeList();
        commissRecyclerAdapter.addAll(couponCodeList);
    }

    @Override
    public void fail(ApiException e) {

    }
}
