package com.yikangcheng.admin.yikang.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.WuliuAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.TestBean;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class WuliuXinxiActivity extends BaseActivtiy {

    private RecyclerView xrecycler;
    private TextView compile_text;
    private WuliuAdapter wuliuAdapter;
    private RelativeLayout rela;
    private CheckBox check_btn;
    private boolean isclick;
    private List<TestBean> testBeans;
    private ImageView back_img;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.clolrBAai);
        xrecycler = findViewById(R.id.xrecycler);
        check_btn = findViewById(R.id.check_btn);
        back_img = findViewById(R.id.back_img);
        compile_text = findViewById(R.id.compile_text);
        rela = findViewById(R.id.rela);
        xrecycler.setLayoutManager(new LinearLayoutManager(this));
        wuliuAdapter = new WuliuAdapter(this);
        xrecycler.setAdapter(wuliuAdapter);
    }

    @Override
    protected void initEventData() {
        //编辑
        compile_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isclick) {
                    isclick = true;
                    wuliuAdapter.isclick(isclick);
                    compile_text.setText("完成");
                    rela.setVisibility(View.VISIBLE);
                } else {
                    isclick = false;
                    wuliuAdapter.isclick(isclick);
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
        return R.layout.activity_wuliu_xinxi;
    }

    @Override
    protected void createPresenter() {

    }
}