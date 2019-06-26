package com.yikangcheng.admin.yikang.activity.fragment.goodsrecommtion;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.Recommtion_Good_Adapter;
import com.yikangcheng.admin.yikang.activity.particulars.ParticularsActivity;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.RecommendBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.RecommendPresenter;

import java.util.List;

/**
 * 作者：古祥坤 on 2019/6/25 10:03
 * 邮箱：1724959985@qq.com
 * 家电
 */
public class Good_Recommiton_Home extends BaseFragment implements ICoreInfe {

    private RecommendPresenter recommendPresenter;
    private LoginBean logUser;
    private int mPage = 1;
    //商品列表
    private XRecyclerView rlv_fragment_accomplish;
    private Recommtion_Good_Adapter recommtion_good_adapter;
//    private SmartRefreshLayout refreshLayout;

    @Override
    protected void initView(View view) {
        recommendPresenter = new RecommendPresenter(this);
        logUser = getLogUser(getContext());
        rlv_fragment_accomplish = view.findViewById(R.id.rlv_fragment_accomplish);
//        refreshLayout = view.findViewById(R.id.refreshLayout);
        recommtion_good_adapter = new Recommtion_Good_Adapter(getContext());
    }

    @Override
    protected void initData() {
        if (logUser != null)
            recommendPresenter.request(logUser.getId(), 1, mPage);
        rlv_fragment_accomplish.setLayoutManager(new GridLayoutManager(getContext(), 2));
        if (!recommtion_good_adapter.hasObservers()){
            rlv_fragment_accomplish.setAdapter(recommtion_good_adapter);
        }else {
            recommtion_good_adapter.notifyDataSetChanged();
        }
        //解决滑动不流畅
        rlv_fragment_accomplish.setHasFixedSize(true);
        rlv_fragment_accomplish.setNestedScrollingEnabled(false);
        recommtion_good_adapter.setmListener(new Recommtion_Good_Adapter.OnClickListener() {
            @Override
            public void OnClickListener(int id) {
                Intent intent = new Intent(getContext(), ParticularsActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.good_recommiton_recycler_home;
    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        List<RecommendBean> entity = (List<RecommendBean>) request.getEntity();
        recommtion_good_adapter.addAll(entity);
    }

    @Override
    public void fail(ApiException e) {

    }
}
