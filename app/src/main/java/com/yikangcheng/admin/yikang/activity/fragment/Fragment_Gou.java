package com.yikangcheng.admin.yikang.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.CloseActivity;
import com.yikangcheng.admin.yikang.activity.MainActivity;
import com.yikangcheng.admin.yikang.activity.adapter.RecommendAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.ShopRecyclerAdapter;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.RecommendBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.ShopCarBean;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.DeleteShopPresenter;
import com.yikangcheng.admin.yikang.presenter.RecommendPresenter;
import com.yikangcheng.admin.yikang.presenter.ShopCarPresenter;
import com.yikangcheng.admin.yikang.util.SpacesItemDecoration;

import java.io.Serializable;
import java.util.List;

//todo 购物车返回页面问题
public class Fragment_Gou extends BaseFragment implements ShopRecyclerAdapter.TotalPriceListener, ShopRecyclerAdapter.checkBoxTouchListener, ICoreInfe {
    private RecyclerView shop_recyclertwo, shop_recycler;
    private ShopRecyclerAdapter shopRecyclerAdapter;
    private RelativeLayout null_car;
    private RelativeLayout diviline;
    private RelativeLayout baseline;
    private RelativeLayout base_btn;
    private CheckBox all_check;
    private TextView text_total, num_text, heji, dele_text;
    private TextView tv_toolBar_right;
    private boolean judge = false;//判断编辑或完成
    private ShopCarPresenter shopCarPresenter;
    private List<ShopCarBean> entity;
    private boolean isclick;
    private RecommendAdapter mRecommendAdapter;
    private RecommendPresenter recommendPresenter;
    private DeleteShopPresenter deleteShopPresenter;
    private String mId = "";

    @Override
    protected void initView(View view) {
        shop_recycler = view.findViewById(R.id.shop_recycler);
        shop_recyclertwo = view.findViewById(R.id.shop_recyclertwo);
        all_check = view.findViewById(R.id.all_check);
        num_text = view.findViewById(R.id.num_text);
        diviline = view.findViewById(R.id.diviline);
        baseline = view.findViewById(R.id.baseline);
        dele_text = view.findViewById(R.id.dele_text);
        base_btn = view.findViewById(R.id.base_btn);
        null_car = view.findViewById(R.id.null_car);
        tv_toolBar_right = view.findViewById(R.id.tv_toolBar_right);
        heji = view.findViewById(R.id.heji);
        text_total = view.findViewById(R.id.text_total);
        shopCarPresenter = new ShopCarPresenter(this);
        deleteShopPresenter = new DeleteShopPresenter(new DeleteShop());
        MainActivity activity = (MainActivity) getActivity();
        //编辑按钮回调
        activity.setOnClickListener(new MainActivity.onClickListener() {
            @Override
            public void onclick() {
                if (!isclick) {
                    dele_text.setVisibility(View.VISIBLE);
                    num_text.setVisibility(View.GONE);
                    heji.setVisibility(View.GONE);
                    text_total.setVisibility(View.GONE);
                    isclick = true;
                } else {
                    dele_text.setVisibility(View.GONE);
                    num_text.setVisibility(View.VISIBLE);
                    heji.setVisibility(View.VISIBLE);
                    text_total.setVisibility(View.VISIBLE);
                    isclick = false;
                }
            }
        });

        /**
         * 为你推荐
         */
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        shop_recyclertwo.setLayoutManager(gridLayoutManager);
        mRecommendAdapter = new RecommendAdapter(getContext());
        shop_recyclertwo.setAdapter(mRecommendAdapter);
        recommendPresenter = new RecommendPresenter(new RecomICoreInfe());
        int spanCount = 2; // 3 columns
        int spacing = 20; // 50px
        boolean includeEdge = false;
        shop_recyclertwo.addItemDecoration(new SpacesItemDecoration(spanCount, spacing, includeEdge));

    }

    @Override
    protected void initData() {
//        //解决滑动不流畅
        shop_recyclertwo.setHasFixedSize(true);
        shop_recyclertwo.setNestedScrollingEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                return false;
            }
        };
        // TODO: 2019/5/15 侧拉删除
        //购物车recyclerview
        shop_recycler.setLayoutManager(layoutManager);
        shopRecyclerAdapter = new ShopRecyclerAdapter(getContext());
        shopRecyclerAdapter.setTotalPriceListener(this);//设置总价回调接口
        shopRecyclerAdapter.setCheckBoxTouchListener(this);
        shop_recycler.setAdapter(shopRecyclerAdapter);
        //为你推荐recyclerview
        shop_recyclertwo.setLayoutManager(new GridLayoutManager(getContext(), 2));
        all_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = all_check.isChecked();
                shopRecyclerAdapter.checkAll(checked);
            }
        });
        tv_toolBar_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (judge) {
                    heji.setVisibility(View.VISIBLE);
                    text_total.setVisibility(View.VISIBLE);
                    num_text.setVisibility(View.VISIBLE);
                    dele_text.setVisibility(View.GONE);
                    tv_toolBar_right.setText("编辑");
                    judge = false;
                } else {
                    heji.setVisibility(View.GONE);
                    text_total.setVisibility(View.GONE);
                    dele_text.setVisibility(View.VISIBLE);
                    num_text.setVisibility(View.GONE);
                    tv_toolBar_right.setText("完成");
                    judge = true;
                }

            }
        });
        /**
         * 去结算
         */
        num_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num_text.getText().toString().equals("去结算(0)")) {
                    Toast.makeText(getContext(), "请选择商品", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<ShopCarBean> shopList = shopRecyclerAdapter.getShopList();
                Intent intent = new Intent(getActivity(), CloseActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("shopList", (Serializable) shopList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        /**
         * 删除购物车商品
         */
        dele_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ShopCarBean> shopList = shopRecyclerAdapter.getShopList();
                for (int i = 0; i < shopList.size(); i++) {
                    int id = shopList.get(i).getId();
                    mId += id + ",";
                }
                String substring = mId.substring(0, mId.length() - 1);
                Toast.makeText(getContext(), "" + substring, Toast.LENGTH_SHORT).show();
                deleteShopPresenter.request(getLogUser(getContext()).getId(), mId);
                mId = "";
            }
        });
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_gou;
    }

    @Override
    public void totalPrice(double totalPrice, int count) {
        java.text.DecimalFormat myformat = new java.text.DecimalFormat("0.00");
        String str = myformat.format(totalPrice);
        text_total.setText("¥" + str);
        if (count > 99) {
            num_text.setText("去结算(99+)");
            return;
        }
        num_text.setText("去结算(" + count + ")");
    }

    @Override
    public void checked(boolean isCheck) {
        all_check.setChecked(isCheck);
    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        List<ShopCarBean> entity = (List<ShopCarBean>) request.getEntity();
        if (entity.size() == 0) {
            null_car.setVisibility(View.VISIBLE);
            shop_recycler.setVisibility(View.GONE);
        }
        shopRecyclerAdapter.remove();

        shopRecyclerAdapter.addAll(entity);
    }

    @Override
    public void fail(ApiException e) {

    }

    public class RecomICoreInfe implements ICoreInfe {

        @Override
        public void success(Object data) {
            Request request = (Request) data;
            List<RecommendBean> entity1 = (List<RecommendBean>) request.getEntity();
            mRecommendAdapter.removeAll();
            mRecommendAdapter.addData(entity1);
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        LoginBean logUser = getLogUser(getContext());
        if (logUser != null) {
            baseline.setVisibility(View.VISIBLE);
            diviline.setVisibility(View.VISIBLE);
            base_btn.setVisibility(View.VISIBLE);
            shopRecyclerAdapter.checkAll(false);
            shopRecyclerAdapter.remove();
            shopCarPresenter.request(logUser.getId());
            recommendPresenter.request(logUser.getId());
        } else {
            baseline.setVisibility(View.GONE);
            diviline.setVisibility(View.GONE);
            base_btn.setVisibility(View.GONE);
        }
    }

    /**
     * 删除购物车商品
     */
    private class DeleteShop implements ICoreInfe {
        @Override
        public void success(Object data) {
            LoginBean logUser = getLogUser(getContext());
            Request request = (Request) data;
            Toast.makeText(getContext(), request.getMessage() + "", Toast.LENGTH_SHORT).show();
            if (request.isSuccess()) {
                if (logUser != null) {
                    shopCarPresenter.request(logUser.getId());
                }
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
