package com.yikangcheng.admin.yikang.activity.obligation;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.CanceledAdapter_A;
import com.yikangcheng.admin.yikang.activity.orderstatus.FackOfActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.CloseBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.ClosePresenter;
import com.yikangcheng.admin.yikang.util.SpacesItemDecoration;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class CanceledActivity extends BaseActivtiy implements ICoreInfe{

    private ImageView back_img;
    private RelativeLayout mToolbarActivityCanceled;
    private RecyclerView mRlvActivityCanceled;
    private CanceledAdapter_A mCanceledAdapter_a;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        back_img = (ImageView) findViewById(R.id.back_img);
        mToolbarActivityCanceled = (RelativeLayout) findViewById(R.id.toolbar_activity_canceled);
        mRlvActivityCanceled = (RecyclerView) findViewById(R.id.rlv_activity_canceled);

        /**
         * 点击返回图标关闭当前页面
         */
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        int spanCount_tuijian = 1; // 3 columns
        int spacing_tuijian = 20; // 50px
        boolean includeEdge_tuijian = false;
        mRlvActivityCanceled.addItemDecoration(new SpacesItemDecoration(spanCount_tuijian, spacing_tuijian, includeEdge_tuijian));


        /**
         * 布局走向
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRlvActivityCanceled.setLayoutManager(linearLayoutManager);

        List<CloseBean.OrderBean> orderBeans = new ArrayList<>();
        mCanceledAdapter_a = new CanceledAdapter_A(orderBeans,this);
        mRlvActivityCanceled.setAdapter(mCanceledAdapter_a);

        mCanceledAdapter_a.setOnClickListener(new CanceledAdapter_A.OnClickListener() {
            @Override
            public void OnClickListener(View v, int orderId) {
                Intent intent = new Intent(CanceledActivity.this, FackOfActivity.class);
                intent.putExtra("orderId_fack", orderId);
                startActivity(intent);
            }
        });

        ClosePresenter closePresenter = new ClosePresenter(this);
        closePresenter.request(11, 1, "CANCEL");
        //解决滑动不流畅
        mRlvActivityCanceled.setHasFixedSize(true);
        mRlvActivityCanceled.setNestedScrollingEnabled(false);
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
    public void success(Object data) {
        Request request = (Request) data;
        CloseBean entity = (CloseBean) request.getEntity();
        mCanceledAdapter_a.allAll(entity.getOrder());
    }

    @Override
    public void fail(ApiException e) {

    }
}
