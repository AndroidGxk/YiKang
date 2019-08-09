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
import com.yikangcheng.admin.yikang.activity.adapter.EffecRecyclerAdapter;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.DiscountBean;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.DiscountPresenter;

import java.util.List;

/**
 * 作者：古祥坤 on 2019/5/25 18:56
 * 邮箱：1724959985@qq.com
 * 已失效的优惠券
 */
public class EffecFragment extends BaseFragment implements ICoreInfe {

    private RecyclerView xrecycler;
    private SmartRefreshLayout mRefreshLayout;
    private ImageView zanwushixiao;
    private DiscountPresenter discountPresenter;
    private EffecRecyclerAdapter effecRecyclerAdapter;
    private LoginBean loginBean;
    private ImageView zanwuyouhuiquan;


    @Override
    protected void initView(View view) {
        zanwuyouhuiquan = view.findViewById(R.id.zanwuyouhuiquan);
        zanwuyouhuiquan.setImageResource(R.drawable.zanwushixiao);
        xrecycler = view.findViewById(R.id.recycler);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        zanwushixiao = view.findViewById(R.id.zanwushixiao);
        xrecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        effecRecyclerAdapter = new EffecRecyclerAdapter(getContext());
        xrecycler.setAdapter(effecRecyclerAdapter);
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
        zanwushixiao.setVisibility(View.GONE);
        discountPresenter = new DiscountPresenter(this);
        loginBean = getLogUser(getContext());
        if (loginBean != null) {
            discountPresenter.request(loginBean.getId(), -1);
        }
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
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.commfragment_item;
    }

    /**
     * 已失效的优惠券
     *
     * @param data
     */
    @Override
    public void success(Object data) {
        Request request = (Request) data;
        DiscountBean entity = (DiscountBean) request.getEntity();
        List<DiscountBean.CouponListBean> couponCodeList = entity.getCouponList();
        if (couponCodeList.size() != 0) {
            effecRecyclerAdapter.addAll(couponCodeList);
        } else {
            zanwuyouhuiquan.setVisibility(View.VISIBLE);
            mRefreshLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void fail(ApiException e) {
        zanwuyouhuiquan.setVisibility(View.VISIBLE);
        mRefreshLayout.setVisibility(View.GONE);
    }
}
