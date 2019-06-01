package com.yikangcheng.admin.yikang.activity.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.SeekListActivity;
import com.yikangcheng.admin.yikang.activity.adapter.FenLeiAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.FenLeiBAdapter;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.ClassifyListOneBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.ClassifyPresenter;

import java.util.List;

import me.jessyan.autosize.internal.CustomAdapt;
import me.leefeng.promptlibrary.PromptDialog;

public class Fragment_Fen extends BaseFragment implements ICoreInfe, CustomAdapt {
    private RecyclerView mRlvFragmentFenleiYou;
    private RecyclerView mRlvFragmentFenleiZuo;
    private FenLeiAdapter mFenLeiAdapter;
    private FenLeiBAdapter mFenLeiBAdapter;
    private ClassifyPresenter classifyPresenter;
    private List<ClassifyListOneBean.ChildSubjectListBeanX> childSubjectList;
    private List<ClassifyListOneBean> entity;
    private int height;
    private PromptDialog promptDialog;

    @Override
    protected void initView(View view) {
        //创建对象
        promptDialog = new PromptDialog(getActivity());
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(1).loadingDuration(1000);
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        height = wm.getDefaultDisplay().getHeight();
        promptDialog.showLoading("正在加载");
        mRlvFragmentFenleiYou = view.findViewById(R.id.rlv__fragment_fenlei_you);
        mRlvFragmentFenleiZuo = view.findViewById(R.id.rlv__fragment_fenlei_zuo);
        classifyPresenter = new ClassifyPresenter(this);
        classifyPresenter.request();
        //左边
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRlvFragmentFenleiZuo.setLayoutManager(linearLayoutManager);
        mFenLeiAdapter = new FenLeiAdapter(getContext());
        mRlvFragmentFenleiZuo.setAdapter(mFenLeiAdapter);
        //右边
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        mRlvFragmentFenleiYou.setLayoutManager(linearLayoutManager1);
        mFenLeiBAdapter = new FenLeiBAdapter(getContext());
        mRlvFragmentFenleiYou.setAdapter(mFenLeiBAdapter);
        mFenLeiAdapter.setOnclickListener(new FenLeiAdapter.Onclick() {
            @Override
            public void onClick(int position) {
                mFenLeiBAdapter.removeAll();
                mFenLeiBAdapter.addData(entity.get(position).getChildSubjectList());
            }
        });
        mFenLeiBAdapter.setOnClickListener(new FenLeiBAdapter.onClickListener() {
            @Override
            public void onClick(int id) {
                Intent intent = new Intent(getContext(), SeekListActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
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

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        entity = (List<ClassifyListOneBean>) request.getEntity();
        mFenLeiAdapter.addAll(entity);
        childSubjectList = entity.get(0).getChildSubjectList();
        mFenLeiBAdapter.addData(childSubjectList);
        promptDialog.dismiss();
    }

    @Override
    public void fail(ApiException e) {

    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return height / 2;
    }
}
