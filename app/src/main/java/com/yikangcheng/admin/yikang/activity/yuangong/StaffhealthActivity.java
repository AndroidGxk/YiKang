package com.yikangcheng.admin.yikang.activity.yuangong;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.SeekListNewActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import butterknife.BindView;

public class StaffhealthActivity extends BaseActivtiy {
    @BindView(R.id.back_img)
    ImageView back_img;
    @BindView(R.id.aixiaoxin)
    ImageView aixiaoxin;
    @BindView(R.id.gengduo_taocan)
    TextView gengduo_taocan;
    @BindView(R.id.line1)
    LinearLayout line1;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.clolrBAai);
    }

    @Override
    protected void initEventData() {
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        aixiaoxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StaffhealthActivity.this, PhysicalActivity.class));
            }
        });
        gengduo_taocan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StaffhealthActivity.this, PhyListActivity.class);
                intent.putExtra("id", 878);
                startActivity(intent);
            }
        });
        line1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StaffhealthActivity.this, AticleActivity.class));
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_staffhealth;
    }

    @Override
    protected void createPresenter() {

    }
}
