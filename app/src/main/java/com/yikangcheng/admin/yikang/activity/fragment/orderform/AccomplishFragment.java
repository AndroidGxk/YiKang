package com.yikangcheng.admin.yikang.activity.fragment.orderform;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.AccomplishAdapter_A;
import com.yikangcheng.admin.yikang.activity.orderstatus.CloseTheDealActivity;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.PaidBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
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

    @Override
    protected void initView(View view) {
        mRlvFragmentAccomplish = view.findViewById(R.id.rlv_fragment_accomplish);
        mImgFragmentAccomplish = view.findViewById(R.id.img_fragment_accomplish);
        mImgFragmentAccomplishQuguanghuang = view.findViewById(R.id.img_fragment_accomplish_quguanghuang);
        mRelativeLayout = view.findViewById(R.id.relativeLayout);

        Glide.with(getContext()).load(R.drawable.dongtu).into(mImgFragmentAccomplish);

        //解决滑动不流畅
        mRlvFragmentAccomplish.setHasFixedSize(true);
        mRlvFragmentAccomplish.setNestedScrollingEnabled(false);

        int spanCount_tuijian = 1; // 3 columns
        int spacing_tuijian = 20; // 50px
        boolean includeEdge_tuijian = false;
        mRlvFragmentAccomplish.addItemDecoration(new SpacesItemDecoration(spanCount_tuijian, spacing_tuijian, includeEdge_tuijian));
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

        //p层
        PaidPresenter paidPresenter = new PaidPresenter(this);
        paidPresenter.request(11, 1, "SUCCESS");


        if (orderBeans.size() < 0) {
            mRelativeLayout.setVisibility(View.VISIBLE);
            mRlvFragmentAccomplish.setVisibility(View.GONE);
        } else {
            mRelativeLayout.setVisibility(View.GONE);
            mRlvFragmentAccomplish.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_accomplish;
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
