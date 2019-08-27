package com.yikangcheng.admin.yikang.activity.obligation;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hjq.toast.ToastUtils;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.walletactivity.IncomeActivity;
import com.yikangcheng.admin.yikang.activity.walletactivity.RechargeActivity;
import com.yikangcheng.admin.yikang.activity.walletactivity.WithdrawActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import butterknife.BindView;
import me.jessyan.autosize.internal.CustomAdapt;

public class DetailActivity extends BaseActivtiy {
    private ImageView back_img;
    private RelativeLayout mToolbarActivityDetail;
    private RelativeLayout toolbar_activity_detail;
    private int height;
    private int width;
    @BindView(R.id.chongzhi)
    LinearLayout chongzhi;
    @BindView(R.id.tixian)
    LinearLayout tixian;
    @BindView(R.id.mingxi)
    LinearLayout mingxi;

    @Override
    protected void initView() {
        //设置状态栏颜色
        if (!getLogUser(this).getThemeColors().equals("")) {
            StatusBarUtil.setStatusBarMode(this, true, Color.parseColor(getLogUser(this).getThemeColors()));
        } else {
            StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        }
        toolbar_activity_detail = (RelativeLayout) findViewById(R.id.toolbar_activity_detail);
        toolbar_activity_detail.setBackgroundColor(Color.parseColor(getLogUser(this).getThemeColors()));
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        back_img = (ImageView) findViewById(R.id.back_img);
        mToolbarActivityDetail = (RelativeLayout) findViewById(R.id.toolbar_activity_detail);

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
        chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show("功能未完善，尽请期待");
//                startActivity(new Intent(DetailActivity.this, RechargeActivity.class));
            }
        });
        tixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show("功能未完善，尽请期待");
//                startActivity(new Intent(DetailActivity.this, WithdrawActivity.class));
            }
        });
        mingxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show("功能未完善，尽请期待");
//                startActivity(new Intent(DetailActivity.this, IncomeActivity.class));
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void createPresenter() {

    }


}
