package com.yikangcheng.admin.yikang.activity.fragment.orderform;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.All_A_Adapter;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.All_A_Bean;
import com.yikangcheng.admin.yikang.util.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2019/5/20.
 * WF
 */
public class AllFragment extends BaseFragment {
    private RecyclerView mRlvFragmentAllDingdan;
    private RecyclerView mRlvFragmentAllTuijian;

    @Override
    protected void initView(View view) {
        mRlvFragmentAllDingdan = view.findViewById(R.id.rlv_fragment_all_dingdan);
        mRlvFragmentAllTuijian = view.findViewById(R.id.rlv_fragment_all_tuijian);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRlvFragmentAllDingdan.setLayoutManager(linearLayoutManager);

        List<All_A_Bean> all_a_beans = new ArrayList<>();
        all_a_beans.add(new All_A_Bean(null, "订单编号:00000000000000000", "2019-05-25 00:00"));
        all_a_beans.add(new All_A_Bean(null, "订单编号:00000000000000000", "2019-05-25 00:00"));


        All_A_Adapter all_a_adapter = new All_A_Adapter(all_a_beans, getContext());
        mRlvFragmentAllDingdan.setAdapter(all_a_adapter);

        int spanCount = 1; // 3 columns
        int spacing = 20; // 50px
        boolean includeEdge = false;
        mRlvFragmentAllDingdan.addItemDecoration(new SpacesItemDecoration(spanCount, spacing, includeEdge));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_all;
    }
}
