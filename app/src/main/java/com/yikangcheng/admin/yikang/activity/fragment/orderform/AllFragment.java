package com.yikangcheng.admin.yikang.activity.fragment.orderform;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.All_A_Adapter;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.ALLBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.AllPresenter;
import com.yikangcheng.admin.yikang.util.SpacesItemDecoration;

import java.util.ArrayList;

/**
 * Created by lenovo on 2019/5/20.
 * WF
 */
public class AllFragment extends BaseFragment implements ICoreInfe {
    private RecyclerView mRlvFragmentAllDingdan;
    private All_A_Adapter mAll_a_adapter;
    private ImageView mImgFragmentAll;
    private ImageView mImgFragmentAllQuguanghuang;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void initView(View view) {
        mRlvFragmentAllDingdan = view.findViewById(R.id.rlv_fragment_all_dingdan);
        mImgFragmentAll = view.findViewById(R.id.img_fragment_all);
        mImgFragmentAllQuguanghuang = view.findViewById(R.id.img_fragment_all_quguanghuang);
        mRelativeLayout = view.findViewById(R.id.relativeLayout);

        Glide.with(getContext()).load(R.drawable.dongtu).into(mImgFragmentAll);

        //布局走向
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRlvFragmentAllDingdan.setLayoutManager(linearLayoutManager);


        ArrayList<ALLBean.OrderBean> orderBeans = new ArrayList<>();

        //创建适配器
        mAll_a_adapter = new All_A_Adapter(getContext(), orderBeans);
        //绑定适配器
        mRlvFragmentAllDingdan.setAdapter(mAll_a_adapter);

        /**
         * P层
         */
        AllPresenter allPresenter = new AllPresenter(this);
//        LoginBean logUser = getLogUser(getContext());
        allPresenter.request(11, 1);

        /**
         * 让rlv---item之间有空隙
         */
        int spanCount = 1; // 3 columns
        int spacing = 20; // 50px
        boolean includeEdge = false;
        mRlvFragmentAllDingdan.addItemDecoration(new SpacesItemDecoration(spanCount, spacing, includeEdge));
        if (orderBeans.size() < 0) {
            mRelativeLayout.setVisibility(View.VISIBLE);
            mRlvFragmentAllDingdan.setVisibility(View.GONE);
        } else {
            mRelativeLayout.setVisibility(View.GONE);
            mRlvFragmentAllDingdan.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_all;
    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        ALLBean entity = (ALLBean) request.getEntity();
        mAll_a_adapter.allData(entity.getOrder());

    }

    @Override
    public void fail(ApiException e) {

    }
}
