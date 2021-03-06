package com.yikangcheng.admin.yikang.activity.fragment.introduction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.LoginActivity;
import com.yikangcheng.admin.yikang.activity.MainActivity;
import com.yikangcheng.admin.yikang.base.BaseFragment;

/**
 * 作者：古祥坤 on 2019/5/21 16:06
 * 邮箱：1724959985@qq.com
 * 引导页
 */
public class Introduction_three extends BaseFragment {

    private TextView over_btn;

    @Override
    protected void initView(View view) {
        over_btn = view.findViewById(R.id.over_btn);

    }

    @Override
    protected void initData() {
        over_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getActivity().getSharedPreferences("stratapp", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("stratapp", "true");
                edit.commit();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.introduction_three;
    }
}
