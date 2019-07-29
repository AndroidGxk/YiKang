package com.yikangcheng.admin.yikang.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.qiandao.QianDaoRecyclerAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import butterknife.BindView;

public class SignInActivity extends BaseActivtiy {
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected void initView() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorTab);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setAdapter(new QianDaoRecyclerAdapter());
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_sign_in;
    }

    @Override
    protected void createPresenter() {

    }
}
