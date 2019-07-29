package com.yikangcheng.admin.yikang.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.CustomerRecyclerAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.jessyan.autosize.internal.CustomAdapt;

public class CustomerActivity extends BaseActivtiy implements  View.OnClickListener {
    private RecyclerView xrecycler;
    private boolean isclick;
    private TextView compile_text;
    private CustomerRecyclerAdapter customerRecyclerAdapter;
    private RelativeLayout rela;
    private ImageView back_img;
    private int width;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.clolrBAai);
        Display display = this.getWindowManager().getDefaultDisplay();
        int height = display.getHeight();
        width = display.getWidth();
        xrecycler = (RecyclerView) findViewById(R.id.xrecycler);
        compile_text = (TextView) findViewById(R.id.compile_text);
        back_img = (ImageView) findViewById(R.id.back_img);
        rela = (RelativeLayout) findViewById(R.id.rela);
        xrecycler.setLayoutManager(new LinearLayoutManager(this));
        customerRecyclerAdapter = new CustomerRecyclerAdapter(this);
        xrecycler.setAdapter(customerRecyclerAdapter);
    }

    @Override
    protected void initEventData() {
        //点击事件
        back_img.setOnClickListener(this);
        compile_text.setOnClickListener(this);
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_customer;
    }

    @Override
    protected void createPresenter() {

    }


    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//          退出
            case R.id.back_img:
                finish();
                break;
//          编辑
            case R.id.compile_text:
                if (!isclick) {
                    isclick = true;
                    customerRecyclerAdapter.isclick(isclick);
                    compile_text.setText("完成");
                    rela.setVisibility(View.VISIBLE);
                } else {
                    isclick = false;
                    customerRecyclerAdapter.isclick(isclick);
                    compile_text.setText("编辑");
                    rela.setVisibility(View.GONE);
                }
                break;
        }
    }
}
