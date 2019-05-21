package com.yikangcheng.admin.yikang.activity.fragment.orderform;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseFragment;

/**
 * Created by lenovo on 2019/5/20.
 * WF
 */
public class AwaitFragment extends BaseFragment {
    private ImageView mImgFragmentAwait;
    private ImageView mImgFragmentAwaitQuguanghuang;

    @Override
    protected void initView(View view) {
        //动图
        mImgFragmentAwait = view.findViewById(R.id.img_fragment_await);
        //去逛逛
        mImgFragmentAwaitQuguanghuang = view.findViewById(R.id.img_fragment_await_quguanghuang);

        Glide.with(this).load(R.drawable.dongtu).into(mImgFragmentAwait);


    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_await;
    }
}
