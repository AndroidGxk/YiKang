package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.MessageListAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

public class MessageActivity extends BaseActivtiy {

    private LinearLayout wuliu, tongzhi, kefu;
    private XRecyclerView xrecycler;
    private ImageView back_img;
    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        wuliu = findViewById(R.id.wuliu);
        xrecycler = findViewById(R.id.xrecycler);
        tongzhi = findViewById(R.id.tongzhi);
        kefu = findViewById(R.id.kefu);
        back_img = findViewById(R.id.back_img);
        xrecycler.setLayoutManager(new LinearLayoutManager(this));
        xrecycler.setAdapter(new MessageListAdapter());
    }

    @Override
    protected void initEventData() {
        /**
         * 跳转到物流页面
         */
        wuliu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MessageActivity.this, WuliuXinxiActivity.class));
            }
        });

        /**
         * 跳转到系统通知
         */
        tongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MessageActivity.this, SystemActivity.class));
            }
        });
        /**
         * 跳转到客服消息
         */
        kefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MessageActivity.this, CustomerActivity.class));
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
        return R.layout.activity_message;
    }

    @Override
    protected void createPresenter() {

    }
}
