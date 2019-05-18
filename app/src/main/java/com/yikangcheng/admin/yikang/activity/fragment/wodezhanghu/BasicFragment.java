package com.yikangcheng.admin.yikang.activity.fragment.wodezhanghu;

import android.view.View;
import android.widget.RelativeLayout;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseFragment;

public class BasicFragment extends BaseFragment {

    private RelativeLayout mReLayoutFragmentBasicName;
    private RelativeLayout mReLayoutFragmentBasicSave;
    private RelativeLayout mReLayoutFragmentBasicTouxiang;
    private RelativeLayout mReLayoutFragmentBasicPhone;
    private RelativeLayout mReLayoutFragmentBasic昵称;
    private RelativeLayout mReLayoutFragmentBasicSex;
    private RelativeLayout mReLayoutFragmentBasicSynopsis;

    @Override
    protected void initView(View view) {
        //用户姓名
        mReLayoutFragmentBasicName = view.findViewById(R.id.reLayout_fragment_basic_name);
        //保存
        mReLayoutFragmentBasicSave = view.findViewById(R.id.reLayout_fragment_basic_save);
        //头像
        mReLayoutFragmentBasicTouxiang = view.findViewById(R.id.reLayout_fragment_basic_touxiang);
        //手机号
        mReLayoutFragmentBasicPhone = view.findViewById(R.id.reLayout_fragment_basic_phone);
        //用户昵称
        mReLayoutFragmentBasic昵称 = view.findViewById(R.id.reLayout_fragment_basic_niceng);
        //性别
        mReLayoutFragmentBasicSex = view.findViewById(R.id.reLayout_fragment_basic_sex);
        //简介
        mReLayoutFragmentBasicSynopsis = view.findViewById(R.id.reLayout_fragment_basic_synopsis);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_basic;
    }
}
