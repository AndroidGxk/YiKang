package com.yikangcheng.admin.yikang.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.MessageListAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.jessyan.autosize.internal.CustomAdapt;

public class MessageActivity extends BaseActivtiy implements View.OnClickListener {

    private LinearLayout wuliu, tongzhi, kefu;
    private XRecyclerView xrecycler;
    private ImageView back_img;
    private int width;

    @Override
    protected void initView() {
        //设置状态栏颜色
        if (!getLogUser(this).getThemeColors().equals("")) {
            StatusBarUtil.setStatusBarMode(this, true, Color.parseColor(getLogUser(this).getThemeColors()));
        } else {
            StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        }
//        tabl = (RelativeLayout) findViewById(R.id.tabl);
//        tabl.setBackgroundColor(Color.parseColor(getLogUser(this).getThemeColors()));
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
        //点击事件
        wuliu.setOnClickListener(this);
        tongzhi.setOnClickListener(this);
        kefu.setOnClickListener(this);
        back_img.setOnClickListener(this);
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void createPresenter() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            跳转到物流页面
            case R.id.wuliu:
                startActivity(new Intent(MessageActivity.this, WuliuXinxiActivity.class));
                break;
//           跳转到系统通知
            case R.id.tongzhi:
                startActivity(new Intent(MessageActivity.this, SystemActivity.class));
                break;
//            跳转到客服消息
            case R.id.kefu:
                startActivity(new Intent(MessageActivity.this, CustomerActivity.class));
                break;
//            退出
            case R.id.back_img:
                finish();
                break;
        }
    }
}
