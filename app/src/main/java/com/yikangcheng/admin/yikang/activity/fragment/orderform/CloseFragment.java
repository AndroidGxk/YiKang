package com.yikangcheng.admin.yikang.activity.fragment.orderform;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.CloseAdapter_A;
import com.yikangcheng.admin.yikang.activity.orderstatus.FackOfActivity;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.CloseBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.ClosePresenter;
import com.yikangcheng.admin.yikang.util.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/5/20.
 * WF
 */
public class CloseFragment extends BaseFragment implements ICoreInfe {
    private RecyclerView mRlvFragmentClose;
    private CloseAdapter_A mCloseAdapter_a;

    @Override
    protected void initView(View view) {
        mRlvFragmentClose = view.findViewById(R.id.rlv_fragment_close);

        //布局走向
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRlvFragmentClose.setLayoutManager(linearLayoutManager);

        List<CloseBean.OrderBean> orderBeans = new ArrayList<>();
        mCloseAdapter_a = new CloseAdapter_A(orderBeans, getContext());
        mRlvFragmentClose.setAdapter(mCloseAdapter_a);
        mCloseAdapter_a.setOnClickListener(new CloseAdapter_A.OnClickListener() {
            @Override
            public void OnClickListener(View v, int orderId) {
                Intent intent = new Intent(getActivity(), FackOfActivity.class);
                intent.putExtra("orderId_fack", orderId);
                startActivity(intent);
            }
        });

        ClosePresenter closePresenter = new ClosePresenter(this);
        closePresenter.request(11, 1, "CANCEL");


        int spanCount_tuijian = 1; // 3 columns
        int spacing_tuijian = 20; // 50px
        boolean includeEdge_tuijian = false;
        mRlvFragmentClose.addItemDecoration(new SpacesItemDecoration(spanCount_tuijian, spacing_tuijian, includeEdge_tuijian));


    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_close;
    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        CloseBean entity = (CloseBean) request.getEntity();
        mCloseAdapter_a.AddAll(entity.getOrder());
    }

    @Override
    public void fail(ApiException e) {

    }
}
