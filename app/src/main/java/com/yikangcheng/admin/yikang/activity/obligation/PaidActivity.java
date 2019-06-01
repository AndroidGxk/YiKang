package com.yikangcheng.admin.yikang.activity.obligation;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.PaidAdapter_A;
import com.yikangcheng.admin.yikang.activity.orderstatus.CloseTheDealActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.ObligationBean;
import com.yikangcheng.admin.yikang.bean.PaidBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.ObligationPresenter;
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

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);

        mImgActivityPaidFanhui = (ImageView) findViewById(R.id.img_activity_paid_fanhui);
        mToolbarActivityPaid = (RelativeLayout) findViewById(R.id.toolbar_activity_paid);
        mRlvActivityPaid = (RecyclerView) findViewById(R.id.rlv_activity_paid);


        /**
         * 点击返回图标关闭当前页面
         */
        mImgActivityPaidFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //解决滑动不流畅
        mRlvActivityPaid.setHasFixedSize(true);
        mRlvActivityPaid.setNestedScrollingEnabled(false);

        int spanCount_tuijian = 1; // 3 columns
        int spacing_tuijian = 20; // 50px
        boolean includeEdge_tuijian = false;
        mRlvActivityPaid.addItemDecoration(new SpacesItemDecoration(spanCount_tuijian, spacing_tuijian, includeEdge_tuijian));
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

        //p层
        PaidPresenter paidPresenter = new PaidPresenter(this);
        paidPresenter.request(getLogUser(PaidActivity.this).getId(), 1, "SUCCESS");

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
