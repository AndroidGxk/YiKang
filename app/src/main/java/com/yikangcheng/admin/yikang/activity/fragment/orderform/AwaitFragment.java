package com.yikangcheng.admin.yikang.activity.fragment.orderform;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.AwaitAdapter;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.ObligationBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
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

    @Override
    protected void initView(View view) {
        //动图
        mImgFragmentAwait = view.findViewById(R.id.img_fragment_await);
        //去逛逛
        mImgFragmentAwaitQuguanghuang = view.findViewById(R.id.img_fragment_await_quguanghuang);
        mRelativeLayout = view.findViewById(R.id.relativeLayout);
        mRlvFragmentAllDingdan = view.findViewById(R.id.rlv_fragment_await_dingdan);
        Glide.with(this).load(R.drawable.dongtu).into(mImgFragmentAwait);

        //解决滑动不流畅
        mRlvFragmentAllDingdan.setHasFixedSize(true);
        mRlvFragmentAllDingdan.setNestedScrollingEnabled(false);

        int spanCount_tuijian = 1; // 3 columns
        int spacing_tuijian = 20; // 50px
        boolean includeEdge_tuijian = false;
        mRlvFragmentAllDingdan.addItemDecoration(new SpacesItemDecoration(spanCount_tuijian, spacing_tuijian, includeEdge_tuijian));


        /**
         * 布局走向
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRlvFragmentAllDingdan.setLayoutManager(linearLayoutManager);
        ArrayList<ObligationBean.OrderBean> orderBeans = new ArrayList<>();
        mAwaitAdapter = new AwaitAdapter(orderBeans,getContext());
        mRlvFragmentAllDingdan.setAdapter(mAwaitAdapter);

        if (orderBeans.size() < 0) {
            mRelativeLayout.setVisibility(View.VISIBLE);
            mRlvFragmentAllDingdan.setVisibility(View.GONE);
        } else {
            mRelativeLayout.setVisibility(View.GONE);
            mRlvFragmentAllDingdan.setVisibility(View.VISIBLE);
        }

        ObligationPresenter obligationPresenter = new ObligationPresenter(this);
        obligationPresenter.request(11, 1, "INIT");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_await;
    }

    @Override
    public void success(Object data) {
        Request request= (Request) data;
        ObligationBean entity = (ObligationBean) request.getEntity();
        mAwaitAdapter.addAll(entity.getOrder());
    }

    @Override
    public void fail(ApiException e) {

    }
}
