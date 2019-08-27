package com.yikangcheng.admin.yikang.activity.fragment.commit_center;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.hjq.toast.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.GoodComBaskActivity;
import com.yikangcheng.admin.yikang.activity.adapter.goodcomm_adapter.RemainRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.particulars.ParticularsActivity;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.UserCommDaiListBean;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.WaitListPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：古祥坤 on 2019/8/20 10:08
 * 邮箱：1724959985@qq.com
 * 评价页面待评价
 */
public class RemainCommit_Fragment extends BaseFragment implements ICoreInfe {

    @BindView(R.id.recy_remain)
    RecyclerView mRecy;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefre;
    @BindView(R.id.img)
    LinearLayout img;
    private WaitListPresenter waitListPresenter;
    int mPage = 1;
    private RemainRecyclerAdapter remainRecyclerAdapter;

    @Override
    protected void initView(View view) {
        mRecy.setLayoutManager(new LinearLayoutManager(getContext()));
        waitListPresenter = new WaitListPresenter(this);
        remainRecyclerAdapter = new RemainRecyclerAdapter(getContext(), getLogUser(BaseApp.getApp()).getThemeColors());
        mRecy.setAdapter(remainRecyclerAdapter);
        /**
         * 刷新列表
         */
        mRefre.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                remainRecyclerAdapter.removeAll();
                waitListPresenter.request(getLogUser(BaseApp.getApp()).getId(), mPage);
            }
        });
        /**
         * 加载列表
         */
        mRefre.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                waitListPresenter.request(getLogUser(BaseApp.getApp()).getId(), mPage);
            }
        });
        remainRecyclerAdapter.setOnClickListener(new RemainRecyclerAdapter.onClickListener() {
            @Override
            public void onClick(int id, String logo) {
                Intent intent = new Intent(getContext(), GoodComBaskActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("logo", logo);
                startActivity(intent);
            }
        });
        remainRecyclerAdapter.setOnClickIdListener(new RemainRecyclerAdapter.onClickIdListener() {
            @Override
            public void onClick(int id) {
                Intent intent = new Intent(getContext(), ParticularsActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        waitListPresenter.request(getLogUser(BaseApp.getApp()).getId(), mPage);

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.remaincommit_fragment;
    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        UserCommDaiListBean userCommDaiListBean = (UserCommDaiListBean) request.getEntity();
        List<UserCommDaiListBean.OrderDetailsListBean> orderDetailsListBeans = userCommDaiListBean.getOrderDetailsList();
        mRefre.finishLoadmore();
        mRefre.finishRefresh();
        if (orderDetailsListBeans != null) {
            remainRecyclerAdapter.addAll(orderDetailsListBeans);
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
