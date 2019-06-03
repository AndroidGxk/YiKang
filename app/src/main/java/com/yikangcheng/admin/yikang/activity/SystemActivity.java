package com.yikangcheng.admin.yikang.activity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.SystemRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.WuliuAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.jessyan.autosize.internal.CustomAdapt;

public class SystemActivity extends BaseActivtiy implements CustomAdapt {
    private RecyclerView xrecycler;
    private TextView compile_text;
    private boolean isclick;
    private SystemRecyclerAdapter systemRecyclerAdapter;
    private RelativeLayout rela;
    private ImageView back_img;
    private int width;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.clolrBAai);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        width = wm.getDefaultDisplay().getWidth();
        xrecycler = (RecyclerView) findViewById(R.id.xrecycler);
        rela = (RelativeLayout) findViewById(R.id.rela);
        back_img = (ImageView) findViewById(R.id.back_img);
        compile_text = (TextView) findViewById(R.id.compile_text);
        xrecycler.setLayoutManager(new LinearLayoutManager(this));
        systemRecyclerAdapter = new SystemRecyclerAdapter(this);
        xrecycler.setAdapter(systemRecyclerAdapter);
    }

    @Override
    protected void initEventData() {
        //编辑
        compile_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isclick) {
                    isclick = true;
                    systemRecyclerAdapter.isclick(isclick);
                    compile_text.setText("完成");
                    rela.setVisibility(View.VISIBLE);
                } else {
                    isclick = false;
                    systemRecyclerAdapter.isclick(isclick);
                    compile_text.setText("编辑");
                    rela.setVisibility(View.GONE);
                }
            }
        });
        /**
         * 退出
         */
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_system;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return width / 2;
    }
}
