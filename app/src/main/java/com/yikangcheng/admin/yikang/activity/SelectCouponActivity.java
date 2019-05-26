package com.yikangcheng.admin.yikang.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.CommissRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.SelectCouponRecyclerAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.DiscountCouponBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.DiscountCouponPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.List;

public class SelectCouponActivity extends BaseActivtiy implements ICoreInfe {

    private XRecyclerView xrecycler;
    private DiscountCouponPresenter discountCouponPresenter;
    private SelectCouponRecyclerAdapter selectCouponRecyclerAdapter;

    @Override
    protected void initView() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorTab);
        xrecycler = findViewById(R.id.xrecycler);
        xrecycler.setLayoutManager(new LinearLayoutManager(this));
        discountCouponPresenter = new DiscountCouponPresenter(this);
        discountCouponPresenter.request(11, 1);
        selectCouponRecyclerAdapter = new SelectCouponRecyclerAdapter(this);
        xrecycler.setAdapter(selectCouponRecyclerAdapter);
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_select_coupon;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        DiscountCouponBean entity = (DiscountCouponBean) request.getEntity();
        List<DiscountCouponBean.CouponCodeListBean> couponCodeList = entity.getCouponCodeList();
        selectCouponRecyclerAdapter.addAll(couponCodeList);
    }

    @Override
    public void fail(ApiException e) {

    }
}