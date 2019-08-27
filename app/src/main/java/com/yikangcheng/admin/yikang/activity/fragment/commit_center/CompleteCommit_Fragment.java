package com.yikangcheng.admin.yikang.activity.fragment.commit_center;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.hjq.toast.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.PhotoBigActivity;
import com.yikangcheng.admin.yikang.activity.adapter.goodcomm_adapter.CompleteRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.particulars.ParticularsActivity;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.UserCommListBean;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.AssessListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：古祥坤 on 2019/8/20 10:08
 * 邮箱：1724959985@qq.com
 * 评价页面已评价
 */
public class CompleteCommit_Fragment extends BaseFragment implements ICoreInfe {
    @BindView(R.id.recy_comple)
    RecyclerView mRecy;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefre;
    @BindView(R.id.img)
    LinearLayout img;
    private AssessListPresenter assessListPresenter;
    /*分类*/
    int mPage = 1;
    private UserCommListBean userCommListBeans;
    private CompleteRecyclerAdapter completeRecyclerAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void initView(View view) {
        assessListPresenter = new AssessListPresenter(this);
        linearLayoutManager = new LinearLayoutManager(getContext());
        completeRecyclerAdapter = new CompleteRecyclerAdapter(getContext());
    }

    @Override
    protected void initData() {
        assessListPresenter.request(getLogUser(BaseApp.getApp()).getId(), mPage);
        mRecy.setLayoutManager(linearLayoutManager);
        mRecy.setAdapter(completeRecyclerAdapter);
        /**
         * 刷新列表
         */
        mRefre.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                completeRecyclerAdapter.removeAll();
                assessListPresenter.request(getLogUser(BaseApp.getApp()).getId(), mPage);
            }
        });
        /**
         * 加载列表
         */
        mRefre.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                assessListPresenter.request(getLogUser(BaseApp.getApp()).getId(), mPage);
            }
        });
        /**
         * 大图
         */
        completeRecyclerAdapter.setOnClickListener(new CompleteRecyclerAdapter.onClickListener() {
            @Override
            public void onClick(ArrayList<String> pathList) {
                Intent intent = new Intent(getContext(), PhotoBigActivity.class);
                intent.putStringArrayListExtra("data", pathList);
                startActivity(intent);
            }
        });
        /**
         * 详情
         */
        completeRecyclerAdapter.setOnClickIdListener(new CompleteRecyclerAdapter.onClickIdListener() {
            @Override
            public void onClick(int id) {
                Intent intent = new Intent(getContext(), ParticularsActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.completecommit_fragment;
    }


    /**
     * 个人评论列表
     *
     * @param data
     */
    @Override
    public void success(Object data) {
        Request request = (Request) data;
        userCommListBeans = (UserCommListBean) request.getEntity();
        List<UserCommListBean.CourseAssessListBean> courseAssessList = userCommListBeans.getCourseAssessList();
        mRefre.finishLoadmore();
        mRefre.finishRefresh();
        if (courseAssessList != null) {
            completeRecyclerAdapter.addAll(courseAssessList);
            img.setVisibility(View.GONE);
            mRefre.setVisibility(View.VISIBLE);
        } else {
            mRefre.setVisibility(View.GONE);
            img.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void fail(ApiException e) {

    }
}
