package com.yikangcheng.admin.yikang.activity.particulars;

import android.graphics.Paint;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;

public class ParticularsActivity extends BaseActivtiy {


    private TextView mTvParticularsYuanjia;

    @Override
    protected void initView() {
        mTvParticularsYuanjia = findViewById(R.id.tv_particulars_yuanjia);
        /**
         * 在TextView文字中间加横线（原价）
         */
        mTvParticularsYuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_particulars;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void showSucess(Object o) {

    }
}
