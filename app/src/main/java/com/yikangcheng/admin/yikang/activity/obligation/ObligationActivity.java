package com.yikangcheng.admin.yikang.activity.obligation;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.ObligationAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.Obligation_TuiJianAdapter;
import com.yikangcheng.admin.yikang.activity.orderstatus.WaitForpaymentActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.All_A_Bean;
import com.yikangcheng.admin.yikang.bean.ObligationBean;
import com.yikangcheng.admin.yikang.bean.RecommendBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.ObligationPresenter;
import com.yikangcheng.admin.yikang.util.SpacesItemDecoration;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的——————待付款页面
 */
public class ObligationActivity extends BaseActivtiy implements ICoreInfe {

    private ImageView mImgActivityObligationFanhui;
    private Toolbar mToolbarActivityObligation;
    private RecyclerView mRlvActivityObligation;
    private ObligationAdapter mObligationAdapter;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        mImgActivityObligationFanhui = findViewById(R.id.img_activity_obligation_fanhui);
        mToolbarActivityObligation = findViewById(R.id.toolbar_activity_obligation);
        mRlvActivityObligation = findViewById(R.id.rlv_activity_obligation);

        mToolbarActivityObligation.setTitle("");
        setSupportActionBar(mToolbarActivityObligation);

        /**
         * 点击返回图标关闭当前页面
         */
        mImgActivityObligationFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //解决滑动不流畅
        mRlvActivityObligation.setHasFixedSize(true);
        mRlvActivityObligation.setNestedScrollingEnabled(false);

        int spanCount_tuijian = 1; // 3 columns
        int spacing_tuijian = 20; // 50px
        boolean includeEdge_tuijian = false;
        mRlvActivityObligation.addItemDecoration(new SpacesItemDecoration(spanCount_tuijian, spacing_tuijian, includeEdge_tuijian));


        /**
         * 布局走向
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRlvActivityObligation.setLayoutManager(linearLayoutManager);

        ArrayList<ObligationBean.OrderBean> orderBeans = new ArrayList<>();
        //创建适配器
        mObligationAdapter = new ObligationAdapter(this, orderBeans);
        //绑定适配器
        mRlvActivityObligation.setAdapter(mObligationAdapter);

        mObligationAdapter.setOnClickListener(new ObligationAdapter.OnClickListener() {
            @Override
            public void OnClickListener(View v, int orderId) {
                Intent intent = new Intent(ObligationActivity.this, WaitForpaymentActivity.class);
                intent.putExtra("orderId_wait", orderId);
                startActivity(intent);
            }
        });

        ObligationPresenter obligationPresenter = new ObligationPresenter(this);
        obligationPresenter.request(getLogUser(ObligationActivity.this).getId(), 1, "INIT");
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_obligation;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        ObligationBean entity = (ObligationBean) request.getEntity();
        mObligationAdapter.addAll(entity.getOrder());
    }

    @Override
    public void fail(ApiException e) {

    }
}
