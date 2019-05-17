package com.yikangcheng.admin.yikang.activity.particulars;

import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;

public class ParticularsActivity extends BaseActivtiy {


    private TextView mTvParticularsYuanjia;
    private ImageView img_particulars_kefu;

    @Override
    protected void initView() {
        mTvParticularsYuanjia = findViewById(R.id.tv_particulars_yuanjia);
        img_particulars_kefu = findViewById(R.id.img_particulars_kefu);
        /**
         * 在TextView文字中间加横线（原价）
         */
        mTvParticularsYuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void initEventData() {
        img_particulars_kefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Information info = new Information();
                info.setAppkey("7560599b63bf43378d05d018ded42cdd");
                SobotApi.setCustomRobotHelloWord(ParticularsActivity.this, "您好，易康成客服很高兴为您服务，请问有什么可以帮助您的？");
                SobotApi.startSobotChat(ParticularsActivity.this, info);
            }
        });

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
