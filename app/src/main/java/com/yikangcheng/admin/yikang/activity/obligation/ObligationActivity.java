package com.yikangcheng.admin.yikang.activity.obligation;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.ObligationAdapter_A;
import com.yikangcheng.admin.yikang.activity.adapter.Obligation_TuiJianAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.All_A_Bean;
import com.yikangcheng.admin.yikang.bean.All_B_Bean;
import com.yikangcheng.admin.yikang.bean.RecommendBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.RecommendPresenter;
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
    private RecyclerView mRlv_tuiJian;
    private Obligation_TuiJianAdapter mObligation_tuiJianAdapter;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        mImgActivityObligationFanhui = findViewById(R.id.img_activity_obligation_fanhui);
        mToolbarActivityObligation = findViewById(R.id.toolbar_activity_obligation);
        mRlvActivityObligation = findViewById(R.id.rlv_activity_obligation);
        mRlv_tuiJian = findViewById(R.id.rlv_activity_obligation_tuijian);

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
        mRlv_tuiJian.setHasFixedSize(true);
        mRlv_tuiJian.setNestedScrollingEnabled(false);

        //解决滑动不流畅
        mRlvActivityObligation.setHasFixedSize(true);
        mRlvActivityObligation.setNestedScrollingEnabled(false);

        /**
         * 待付款
         */

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRlvActivityObligation.setLayoutManager(linearLayoutManager);

        List<All_A_Bean> all_a_beans = new ArrayList<>();
        all_a_beans.add(new All_A_Bean(null, "订单编号:00000000000000000", "2019-05-25 00:00"));
        all_a_beans.add(new All_A_Bean(null, "订单编号:00000000000000000", "2019-05-25 00:00"));
        ObligationAdapter_A obligationAdapter_a = new ObligationAdapter_A(all_a_beans, this);
        mRlvActivityObligation.setAdapter(obligationAdapter_a);
        int spanCount = 1; // 3 columns
        int spacing = 20; // 50px
        boolean includeEdge = false;
        mRlvActivityObligation.addItemDecoration(new SpacesItemDecoration(spanCount, spacing, includeEdge));

        /**
         * 为你推荐
         */
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRlv_tuiJian.setLayoutManager(gridLayoutManager);
        ArrayList<RecommendBean> recommendBeans = new ArrayList<>();
        mObligation_tuiJianAdapter = new Obligation_TuiJianAdapter(recommendBeans, this);
        mRlv_tuiJian.setAdapter(mObligation_tuiJianAdapter);

        RecommendPresenter recommendPresenter = new RecommendPresenter(this);
        recommendPresenter.request(1);

        int spanCount_tuijian = 2; // 3 columns
        int spacing_tuijian = 20; // 50px
        boolean includeEdge_tuijian = false;
        mRlvActivityObligation.addItemDecoration(new SpacesItemDecoration(spanCount_tuijian, spacing_tuijian, includeEdge_tuijian));


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
        List<RecommendBean> entity1 = (List<RecommendBean>) request.getEntity();
        mObligation_tuiJianAdapter.addData(entity1);
    }

    @Override
    public void fail(ApiException e) {

    }
}
