package com.yikangcheng.admin.yikang.activity.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.FenLeiAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.FenLeiBAdapter;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.base.contract.Contract;
import com.yikangcheng.admin.yikang.classify.ClassifyBean;
import com.yikangcheng.admin.yikang.presenter.ClassifyPresenter;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Fen extends BaseFragment implements Contract.View {

    private ClassifyPresenter mClassifyPresenter;
    private RecyclerView mRlvFragmentFenleiYou;
    private RecyclerView mRlvFragmentFenleiZuo;
    private List<ClassifyBean.EntityBean> mChildSubjectListBeans;
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
        mChildSubjectListBeans = new ArrayList<>();
        mFenLeiAdapter = new FenLeiAdapter(getContext(),mChildSubjectListBeans);
        mRlvFragmentFenleiZuo.setAdapter(mFenLeiAdapter);
        //右边
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        mRlvFragmentFenleiYou.setLayoutManager(linearLayoutManager1);
        mChildSubjectListBeanXES = new ArrayList<>();
        mFenLeiBAdapter = new FenLeiBAdapter(mChildSubjectListBeanXES,getContext());
        mRlvFragmentFenleiYou.setAdapter(mFenLeiBAdapter);

    }

    @Override
    protected void initData() {
        mClassifyPresenter = new ClassifyPresenter(this);
        mClassifyPresenter.start();

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_fen;
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void dissProgressBar() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showSucess(Object o) {
        if (o instanceof ClassifyBean){
            ClassifyBean classifyBean= (ClassifyBean) o;
            List<ClassifyBean.EntityBean> entity = classifyBean.getEntity();
            mFenLeiAdapter.addData(entity);
        }
    }
}
