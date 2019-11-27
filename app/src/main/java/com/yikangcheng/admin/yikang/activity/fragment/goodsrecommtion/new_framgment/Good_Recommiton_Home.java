package com.yikangcheng.admin.yikang.activity.fragment.goodsrecommtion.new_framgment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.Recommtion_Good_Adapter;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_New_Wo;
import com.yikangcheng.admin.yikang.activity.particulars.ParticularsActivity;
import com.yikangcheng.admin.yikang.base.BaseFragment;
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
    //商品列表
    private RecyclerView rlv_fragment_accomplish;
    private Recommtion_Good_Adapter recommtion_good_adapter;
    //    private SmartRefreshLayout refreshLayout;
    private ImageView noGood_img;
    private RelativeLayout linear;
    private int height;

    @Override
    protected void initView(View view) {
        recommendPresenter = new RecommendPresenter(this);
        rlv_fragment_accomplish = view.findViewById(R.id.rlv_fragment_accomplish);
//        refreshLayout = view.findViewById(R.id.refreshLayout);
        height = getActivity().getWindow().getDecorView().getHeight();
        linear = view.findViewById(R.id.linear);
        recommtion_good_adapter = new Recommtion_Good_Adapter(getContext());
        noGood_img = view.findViewById(R.id.noGood_img);
        Fragment_New_Wo homeFragment = (Fragment_New_Wo) getParentFragment();
        homeFragment.setChildObjectForPosition(view, 0);
    }

    @Override
    protected void initData() {
        recommendPresenter.request(10, 136);
        rlv_fragment_accomplish.setLayoutManager(new GridLayoutManager(getContext(), 2));
        if (!recommtion_good_adapter.hasObservers()) {
            rlv_fragment_accomplish.setAdapter(recommtion_good_adapter);
        } else {
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
        RecommendBean entity = (RecommendBean) request.getEntity();
        List<RecommendBean.CommodityListBean> commodityListBeans = entity.getCommodityList();
        if (commodityListBeans != null) {
            if (commodityListBeans.size() != 0) {
                rlv_fragment_accomplish.setVisibility(View.VISIBLE);
                noGood_img.setVisibility(View.GONE);
                recommtion_good_adapter.addAll(commodityListBeans);
            } else {
                noGood_img.setVisibility(View.VISIBLE);
                rlv_fragment_accomplish.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void fail(ApiException e) {

    }
}
