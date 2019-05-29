package com.yikangcheng.admin.yikang.activity.obligation;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

public class DetailActivity extends BaseActivtiy {
    private ImageView back_img;
    private Toolbar mToolbarActivityDetail;
    private ImageView mImgActivityDetailQiaobao;
    private TextView mTvActivityDeteilYuE;
    private TextView mTvActivityDeteilWu;
    private RecyclerView mRlvActivityDetail;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        back_img = findViewById(R.id.back_img);
        mToolbarActivityDetail = findViewById(R.id.toolbar_activity_detail);
        mImgActivityDetailQiaobao = findViewById(R.id.img_activity_detail_qiaobao);
        mTvActivityDeteilYuE = findViewById(R.id.tv_activity_deteil_YuE);
        mTvActivityDeteilWu = findViewById(R.id.tv_activity_deteil_Wu);
        mRlvActivityDetail = findViewById(R.id.rlv_activity_detail);

        /**
         * 点击返回按钮关闭当前页面
         */
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /**
         * TOOLBAR
         */
        mToolbarActivityDetail.setTitle("");
        setSupportActionBar(mToolbarActivityDetail);
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
