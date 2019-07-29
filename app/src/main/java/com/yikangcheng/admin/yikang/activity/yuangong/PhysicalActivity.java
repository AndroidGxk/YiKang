package com.yikangcheng.admin.yikang.activity.yuangong;

import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.ClassCommodAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import butterknife.BindView;

/**
 * 体检项目
 */
public class PhysicalActivity extends BaseActivtiy {
    @BindView(R.id.img_fanhui)
    ImageView img_fanhui;
    @BindView(R.id.yuanjia)
    TextView yuanjia;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.clolrBAai);
    }

    @Override
    protected void initEventData() {
        img_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_physical;
    }

    @Override
    protected void createPresenter() {

    }
}
