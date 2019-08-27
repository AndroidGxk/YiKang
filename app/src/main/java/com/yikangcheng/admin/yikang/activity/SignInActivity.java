package com.yikangcheng.admin.yikang.activity;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.qiandao.QianDaoRecyclerAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import butterknife.BindView;

public class SignInActivity extends BaseActivtiy {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.table)
    RelativeLayout table;
    @Override
    protected void initView() {
        //设置状态栏颜色
        if (!getLogUser(this).getThemeColors().equals("")) {
            StatusBarUtil.setStatusBarMode(this, true, Color.parseColor(getLogUser(this).getThemeColors()));
        } else {
            StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        }
        table = (RelativeLayout) findViewById(R.id.table);
        table.setBackgroundColor(Color.parseColor(getLogUser(this).getThemeColors()));
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
