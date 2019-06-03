package com.yikangcheng.admin.yikang.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.MessageListAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.jessyan.autosize.internal.CustomAdapt;

public class MessageActivity extends BaseActivtiy implements CustomAdapt {

    private LinearLayout wuliu, tongzhi, kefu;
    private XRecyclerView xrecycler;
    private ImageView back_img;
    private int width;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        width = wm.getDefaultDisplay().getWidth();
        wuliu = (LinearLayout) findViewById(R.id.wuliu);
        xrecycler = (XRecyclerView) findViewById(R.id.xrecycler);
        tongzhi = (LinearLayout) findViewById(R.id.tongzhi);
        kefu = (LinearLayout) findViewById(R.id.kefu);
        back_img = (ImageView) findViewById(R.id.back_img);
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

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return width / 2;
    }
}
