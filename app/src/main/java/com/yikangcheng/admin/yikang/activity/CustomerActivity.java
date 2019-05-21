package com.yikangcheng.admin.yikang.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.CustomerRecyclerAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

public class CustomerActivity extends BaseActivtiy {
    private RecyclerView xrecycler;
    private boolean isclick;
    private TextView compile_text;
    private CustomerRecyclerAdapter customerRecyclerAdapter;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.clolrBAai);
        xrecycler = findViewById(R.id.xrecycler);
        compile_text = findViewById(R.id.compile_text);
        xrecycler.setLayoutManager(new LinearLayoutManager(this));
        customerRecyclerAdapter = new CustomerRecyclerAdapter(this);
        xrecycler.setAdapter(customerRecyclerAdapter);
    }

    @Override
    protected void initEventData() {
        //编辑
        compile_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isclick) {
                    isclick = true;
                    customerRecyclerAdapter.isclick(isclick);
                    compile_text.setText("完成");
                } else {
                    isclick = false;
                    customerRecyclerAdapter.isclick(isclick);
                    compile_text.setText("编辑");
                }
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_customer;
    }

    @Override
    protected void createPresenter() {

    }
}
