package com.yikangcheng.admin.yikang.activity.siteactivity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

public class AiteActivity extends BaseActivtiy {


    private ImageView mImgActivityAiteFanhui;
    private Toolbar mToolbarActivityAite;
    private RecyclerView mRlvActivityAite;
    private RelativeLayout mRelativeLayoutActivityAite;

    @Override
    protected void initView() {
        /**
         * 这是收货地址页面  item布局已经写好  下面就是
         */
//        <?xml version="1.0" encoding="utf-8"?>
//<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
//        android:layout_width="match_parent"
//        android:layout_height="131dp"
//        android:background="@drawable/dizi_beijing">
//
//    <TextView
//        android:layout_width="58dp"
//        android:layout_height="wrap_content"
//        android:layout_marginLeft="13dp"
//        android:layout_marginTop="30dp"
//        android:text="某某某"
//        android:textColor="#ff333333"
//        android:textSize="19sp" />
//
//    <TextView
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:layout_marginLeft="247dp"
//        android:layout_marginTop="36dp"
//        android:text="138****0000"
//        android:textColor="#ff333333"
//        android:textSize="15sp" />
//
//    <EditText
//        android:layout_width="match_parent"
//        android:layout_height="wrap_content"
//        android:layout_marginLeft="13dp"
//        android:layout_marginTop="63dp"
//        android:layout_marginRight="13dp"
//        android:background="@null"
//        android:ellipsize="end"
//        android:hint="请输入收货地址"
//        android:singleLine="true"
//        android:textColor="#999999"
//        android:textSize="14sp" />
//
//    <ImageView
//        android:layout_width="65dp"
//        android:layout_height="17dp"
//        android:layout_marginLeft="15dp"
//        android:layout_marginTop="103dp"
//        android:src="@drawable/moren" />
//
//    <ImageView
//        android:layout_width="17dp"
//        android:layout_height="17dp"
//        android:layout_marginLeft="237dp"
//        android:layout_marginTop="102dp"
//        android:src="@drawable/xie" />
//
//    <TextView
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:layout_marginLeft="260dp"
//        android:layout_marginTop="103dp"
//        android:text="编辑"
//        android:textColor="#ff333333"
//        android:textSize="12sp" />
//
//    <ImageView
//        android:layout_width="17dp"
//        android:layout_height="17dp"
//        android:layout_marginLeft="300dp"
//        android:layout_marginTop="102dp"
//        android:src="@drawable/sc" />
//
//    <TextView
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:layout_marginLeft="321dp"
//        android:layout_marginTop="103dp"
//        android:text="删除"
//        android:textColor="#ff333333"
//        android:textSize="12sp" />
//</>
        mImgActivityAiteFanhui = findViewById(R.id.img_activity_aite_fanhui);
        mToolbarActivityAite = findViewById(R.id.toolbar_activity_aite);
        mRlvActivityAite = findViewById(R.id.rlv_activity_aite);
        mRelativeLayoutActivityAite = findViewById(R.id.relativeLayout_activity_aite);


        /**
         *  设置状态栏颜色
         */
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        /**
         * ToolBar
         */
        mToolbarActivityAite.setTitle("");
        setSupportActionBar(mToolbarActivityAite);
        /**
         * 点击返回按钮关闭当前页面
         */
        mImgActivityAiteFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        /**
         * 点击新增收货地址按钮跳转页面
         */
        mRelativeLayoutActivityAite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AiteActivity.this, CompileActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_aite;
    }

    @Override
    protected void createPresenter() {

    }
}
