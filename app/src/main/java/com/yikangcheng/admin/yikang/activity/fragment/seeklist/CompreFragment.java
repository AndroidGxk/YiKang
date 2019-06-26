package com.yikangcheng.admin.yikang.activity.fragment.seeklist;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.seeklistnew.SeekListNewAdapter;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.ClassifyCommodityListBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.CommodityPresenter;
import com.yikangcheng.admin.yikang.util.TwoBallRotationProgressBar;

import java.util.List;

/**
 * 作者：古祥坤 on 2019/6/21 09:26
 * 邮箱：1724959985@qq.com
 */
public class CompreFragment extends BaseFragment implements ICoreInfe {
    private CommodityPresenter commodityPresenter;
    private RecyclerView recycler;
    private ImageView noGood_img;
    //加载
    private SmartRefreshLayout refreshLayout;
    //加载进度
//    private TwoBallRotationProgressBar progress;

    private int id;
    private String count;
    //页数
    private int mPage = 1;
    private SeekListNewAdapter seekListNewAdapter;


    @Override
    protected void initView(View view) {
        recycler = view.findViewById(R.id.xrecycler);
        commodityPresenter = new CommodityPresenter(this);
        //获取值
        Intent intent = getActivity().getIntent();
        count = intent.getStringExtra("count");
        id = intent.getIntExtra("id", 000);
        //暂无商品
        noGood_img = view.findViewById(R.id.noGood_img);
        //加载刷新
        refreshLayout = view.findViewById(R.id.refreshLayout);
        //加载进度
//        progress = view.findViewById(R.id.progress);
        //商品Adapter
        seekListNewAdapter = new SeekListNewAdapter(getContext());
        //设置布局走向
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(seekListNewAdapter);
    }

    @Override
    protected void initData() {
        if (count == null || count.equals("")) {
            commodityPresenter.request(id, 1, "", 1, mPage);
        } else {
            commodityPresenter.request(id, 1, count, 1, mPage);
        }
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_seek_list;
    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        ClassifyCommodityListBean classifyCommodityListBean = (ClassifyCommodityListBean) request.getEntity();
        List<ClassifyCommodityListBean.CommodityListBean> commodityList = classifyCommodityListBean.getCommodityList();
        if (commodityList == null) {
            noGood_img.setVisibility(View.VISIBLE);
            refreshLayout.setVisibility(View.GONE);
        } else {
            seekListNewAdapter.addAll(commodityList);
            noGood_img.setVisibility(View.GONE);
            refreshLayout.setVisibility(View.VISIBLE);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                progress.setVisibility(View.GONE);
//                progress.stopAnimator();
            }
        }, 1500);
    }

    @Override
    public void fail(ApiException e) {

    }
}
