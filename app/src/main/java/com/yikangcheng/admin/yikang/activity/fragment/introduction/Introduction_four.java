package com.yikangcheng.admin.yikang.activity.fragment.introduction;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.MainActivity;
import com.yikangcheng.admin.yikang.base.BaseFragment;

/**
 * 作者：古祥坤 on 2019/5/21 16:06
 * 邮箱：1724959985@qq.com
 * 引导页
 */
public class Introduction_four extends BaseFragment {


    private ImageView img;

    @Override
    protected void initView(View view) {
        img = view.findViewById(R.id.img);
    }

    @Override
    protected void initData() {
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.introduction_four;
    }
}
