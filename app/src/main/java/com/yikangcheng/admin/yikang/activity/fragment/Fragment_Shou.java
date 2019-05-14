package com.yikangcheng.admin.yikang.activity.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yikangcheng.admin.yikang.R;

/**
 * 作者：古祥坤 on 2019/5/14 11:59
 * 邮箱：1724959985@qq.com
 */
public class Fragment_Shou extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shou, container, false);
        return view;
    }
}
