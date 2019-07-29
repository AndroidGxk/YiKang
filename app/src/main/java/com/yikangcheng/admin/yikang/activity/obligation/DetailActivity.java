package com.yikangcheng.admin.yikang.activity.obligation;

import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.jessyan.autosize.internal.CustomAdapt;

public class DetailActivity extends BaseActivtiy   {
    private ImageView back_img;
    private RelativeLayout mToolbarActivityDetail;
    private ImageView mImgActivityDetailQiaobao;
    private TextView mTvActivityDeteilYuE;
    private TextView mTvActivityDeteilWu;
    private RecyclerView mRlvActivityDetail;
    private int height;
    private int width;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        back_img = (ImageView) findViewById(R.id.back_img);
        mToolbarActivityDetail = (RelativeLayout) findViewById(R.id.toolbar_activity_detail);
        mImgActivityDetailQiaobao = (ImageView) findViewById(R.id.img_activity_detail_qiaobao);
        mTvActivityDeteilYuE = (TextView) findViewById(R.id.tv_activity_deteil_YuE);
        mTvActivityDeteilWu = (TextView) findViewById(R.id.tv_activity_deteil_Wu);
        mRlvActivityDetail = (RecyclerView) findViewById(R.id.rlv_activity_detail);


        Glide.with(DetailActivity.this).load(R.drawable.qianbao).into(mImgActivityDetailQiaobao);
        /**
         * 点击返回按钮关闭当前页面
         */
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void createPresenter() {

    }


}
