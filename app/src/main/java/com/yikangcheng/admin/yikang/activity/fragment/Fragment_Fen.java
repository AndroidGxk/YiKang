package com.yikangcheng.admin.yikang.activity.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.FenLeiAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.FenLeiBAdapter;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.ClassifyBean;

import java.util.ArrayList;

public class Fragment_Fen extends BaseFragment {
    private RecyclerView mRlvFragmentFenleiYou;
    private RecyclerView mRlvFragmentFenleiZuo;
    private FenLeiAdapter mFenLeiAdapter;
    private FenLeiBAdapter mFenLeiBAdapter;
    private ArrayList<ClassifyBean.EntityBean.ChildSubjectListBeanX> mChildSubjectListBeanXES;


    @Override
    protected void initView(View view) {
        mRlvFragmentFenleiYou = view.findViewById(R.id.rlv__fragment_fenlei_you);
        mRlvFragmentFenleiZuo = view.findViewById(R.id.rlv__fragment_fenlei_zuo);

        //左边
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRlvFragmentFenleiZuo.setLayoutManager(linearLayoutManager);
        mFenLeiAdapter = new FenLeiAdapter(getContext());
        mRlvFragmentFenleiZuo.setAdapter(mFenLeiAdapter);
        //右边
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        mRlvFragmentFenleiYou.setLayoutManager(linearLayoutManager1);
        mChildSubjectListBeanXES = new ArrayList<>();
        mFenLeiBAdapter = new FenLeiBAdapter(mChildSubjectListBeanXES, getContext());
        mRlvFragmentFenleiYou.setAdapter(mFenLeiBAdapter);
        mFenLeiAdapter.setOnclickListener(new FenLeiAdapter.Onclick() {
            @Override
            public void onClick(int position) {
                mFenLeiBAdapter.removeAll();
                mChildSubjectListBeanXES.clear();
            }
        });

    }

    @Override
    protected void initData() {
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_fen;
    }
}
