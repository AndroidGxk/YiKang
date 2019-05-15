package com.yikangcheng.admin.yikang.activity.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.SeckillSecondActivity;
import com.yikangcheng.admin.yikang.base.BaseFragment;

public class Fragment_Miao extends BaseFragment {

    private ImageView miao_one_btn;

    @Override
    protected void initView(View view) {
        miao_one_btn = view.findViewById(R.id.miao_one_btn);
    }

    @Override
    protected void initData() {
        miao_one_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SeckillSecondActivity.class));
            }
        });
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_miao;
    }
}
