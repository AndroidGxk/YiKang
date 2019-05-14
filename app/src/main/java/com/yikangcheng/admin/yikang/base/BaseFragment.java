package com.yikangcheng.admin.yikang.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {
    private Unbinder mButterKnife;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayoutId(), container,false);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mButterKnife = ButterKnife.bind(this, view);
        initData();
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract void initData();

    protected abstract int getFragmentLayoutId();


    @Override
    public void onDestroy() {
        if (mButterKnife != null)
            mButterKnife.unbind();
        super.onDestroy();
    }
}
