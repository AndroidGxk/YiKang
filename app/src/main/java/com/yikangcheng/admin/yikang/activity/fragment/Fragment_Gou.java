package com.yikangcheng.admin.yikang.activity.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.CloseActivity;
import com.yikangcheng.admin.yikang.activity.MainActivity;
import com.yikangcheng.admin.yikang.activity.adapter.RecommendAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.ShopRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.particulars.ParticularsActivity;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.ShopCarBean;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.DeleteShopPresenter;
import com.yikangcheng.admin.yikang.presenter.ShopCarPresenter;
import com.yikangcheng.admin.yikang.presenter.UpdateCountPresenter;
import com.yikangcheng.admin.yikang.util.SpacesItemDecoration;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

//todo 购物车返回页面问题
public class Fragment_Gou extends BaseFragment implements ShopRecyclerAdapter.TotalPriceListener, ShopRecyclerAdapter.checkBoxTouchListener, ICoreInfe, XRecyclerView.LoadingListener {
    private RecyclerView shop_recycler;
    private XRecyclerView shop_recyclertwo;
    private ShopRecyclerAdapter shopRecyclerAdapter;
    private RelativeLayout null_car;
    private RelativeLayout diviline;
    private RelativeLayout baseline;
    private RelativeLayout base_btn;
    private CheckBox all_check;
    private TextView text_total, num_text, heji, dele_text;
    private TextView tv_toolBar_right;
    private boolean judge = false;//判断编辑或完成
    private static ShopCarPresenter shopCarPresenter;
    private boolean isclick;
    private RecommendAdapter mRecommendAdapter;
    private DeleteShopPresenter deleteShopPresenter;
    private String mId = "";
    //标记
    private static int sign = 0;
    private MainActivity activity;
    private UpdateCountPresenter updateCountPresenter;
    private PromptDialog promptDialog;
    private NestedScrollView nestedSV;
    private int mPage = 1;
    private static LoginBean logUser;
    private Toolbar toolBar;

    @BindView(R.id.tv_toolBar_title)
    TextView tv_toolBar_title;
    @BindView(R.id.quan)
    TextView quan;

    @BindView(R.id.tabview)
    TextView tabview;

    @Override
    protected void initView(View view) {
        //设置状态栏颜色
//        if (!getLogUser(getContext()).getThemeColors().equals("")) {
//            StatusBarUtil.setStatusBarMode((Activity) getContext(), true, Color.parseColor(getLogUser(getContext()).getThemeColors()));
//        } else {
//            StatusBarUtil.setStatusBarMode((Activity) getContext(), true, R.color.colorToolbar);
//        }
        logUser = getLogUser(getContext());
        //创建对象
        promptDialog = new PromptDialog(getActivity());
        toolBar = (Toolbar) view.findViewById(R.id.toolBar);
        toolBar.setBackgroundColor(Color.parseColor(getLogUser(getContext()).getThemeColors()));
        //标题颜色
//        tv_toolBar_title.setTextColor(Color.parseColor());
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(100);
        shop_recycler = view.findViewById(R.id.shop_recycler);
        shop_recyclertwo = view.findViewById(R.id.shop_recyclertwo);
        all_check = view.findViewById(R.id.all_check);
        num_text = view.findViewById(R.id.num_text);
        GradientDrawable myGrad = (GradientDrawable) num_text.getBackground();
        myGrad.setColor(Color.parseColor(getLogUser(getContext()).getThemeColors()));
        diviline = view.findViewById(R.id.diviline);
        baseline = view.findViewById(R.id.baseline);
        dele_text = view.findViewById(R.id.dele_text);
        nestedSV = view.findViewById(R.id.nestedSV);
        base_btn = view.findViewById(R.id.base_btn);
        null_car = view.findViewById(R.id.null_car);
        tv_toolBar_right = view.findViewById(R.id.tv_toolBar_right);
        heji = view.findViewById(R.id.heji);
        text_total = view.findViewById(R.id.text_total);
        shopCarPresenter = new ShopCarPresenter(this);
        deleteShopPresenter = new DeleteShopPresenter(new DeleteShop());
        activity = (MainActivity) getActivity();

        /**
         * 为你推荐
         */
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        shop_recyclertwo.setLayoutManager(staggeredGridLayoutManager);
        mRecommendAdapter = new RecommendAdapter(getContext());
        shop_recyclertwo.setAdapter(mRecommendAdapter);

        mRecommendAdapter.setOnClickListener(new RecommendAdapter.OnClickListener() {
            @Override
            public void OnClickListener(View v, int id) {
                Intent intent = new Intent(getActivity(), ParticularsActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        int spanCount = 1; // 3 columns
        int spacing = 20; // 50px
        boolean includeEdge = true;
        shop_recyclertwo.addItemDecoration(new SpacesItemDecoration(spanCount, spacing, includeEdge));
        shop_recyclertwo.setLoadingListener(this);
        nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //判断是否滑到的底部
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    shop_recyclertwo.setLoadingMoreEnabled(true);//调用刷新控件对应的加载更多方法
                    onLoadMore();
                }
            }
        });

    }

    @Override
    protected void initData() {
        //修改数量
        updateCountPresenter = new UpdateCountPresenter(new UpdateCount());
        //解决滑动不流畅
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
        //请求数量接口
        shopRecyclerAdapter.setSumClickListener(new ShopRecyclerAdapter.sumClickListener() {
            @Override
            public void onClick(int count, int id) {
                LoginBean logUser = getLogUser(getContext());
                if (logUser != null) {
                    updateCountPresenter.request(logUser.getId(), id, count);
                    promptDialog.showLoading("");
                }
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
                    ToastUtils.show("请选择商品");
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
                promptDialog.showWarnAlert("确定要删除该商品吗？", new PromptButton("取消", new PromptButtonListener() {
                    @Override
                    public void onClick(PromptButton button) {
                    }
                }), confirm);
            }
        });

        /**
         * 跳转详情页面
         */
        shopRecyclerAdapter.setGoParClickListener(new ShopRecyclerAdapter.goParClickListener() {
            @Override
            public void onclick(int id) {
                Intent intent = new Intent(getContext(), ParticularsActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        if (logUser != null) {
            baseline.setVisibility(View.GONE);
            shopRecyclerAdapter.checkAll(false);
            shopRecyclerAdapter.remove();
            shopCarPresenter.request(logUser.getId());
        } else {
            baseline.setVisibility(View.GONE);
        }

        RelativeLayout.LayoutParams linearParams =(RelativeLayout.LayoutParams) tabview.getLayoutParams(); //取控件textView当前的布局参数
        linearParams.height =getStatusBarHeight(getContext());// 控件的高强制设成20
        tabview.setLayoutParams(linearParams);
        tabview.setBackgroundColor(Color.parseColor(getLogUser(getContext()).getThemeColors()));
    }
    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
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


    //按钮的定义，创建一个按钮的对象

    PromptButton confirm = new PromptButton("确定", new PromptButtonListener() {
        @Override
        public void onClick(PromptButton button) {
            List<ShopCarBean> shopList = shopRecyclerAdapter.getShopList();
            for (int i = 0; i < shopList.size(); i++) {
                int id = shopList.get(i).getId();
                mId += id + ",";
            }
            if (mId == null || mId.equals("")) {
                all_check.setChecked(false);
                shopRecyclerAdapter.checkAll(all_check.isChecked());
                return;
            }
            mId.substring(0, mId.length() - 1);
            deleteShopPresenter.request(getLogUser(getContext()).getId(), mId);
            mId = "";
            all_check.setChecked(false);
            shopRecyclerAdapter.checkAll(all_check.isChecked());

        }
    });

    @Override
    public void checked(boolean isCheck) {
        all_check.setChecked(isCheck);
    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        List<ShopCarBean> entity = (List<ShopCarBean>) request.getEntity();
        if (entity.size() == 0) {
            tv_toolBar_right.setVisibility(View.GONE);
            all_check.setVisibility(View.GONE);
            quan.setVisibility(View.GONE);
            heji.setVisibility(View.GONE);
            text_total.setVisibility(View.GONE);
            num_text.setVisibility(View.GONE);
            base_btn.setVisibility(View.GONE);
            null_car.setVisibility(View.VISIBLE);
            shop_recycler.setVisibility(View.GONE);
        } else {
            tv_toolBar_right.setVisibility(View.VISIBLE);
            all_check.setVisibility(View.VISIBLE);
            quan.setVisibility(View.VISIBLE);
            heji.setVisibility(View.VISIBLE);
            text_total.setVisibility(View.VISIBLE);
            num_text.setVisibility(View.VISIBLE);
            base_btn.setVisibility(View.VISIBLE);
            null_car.setVisibility(View.GONE);
            shop_recycler.setVisibility(View.VISIBLE);
        }
        activity.setCount(entity.size());
        shopRecyclerAdapter.remove();

        shopRecyclerAdapter.addAll(entity);
    }

    @Override
    public void fail(ApiException e) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        mPage++;
    }

    public static void getRequest() {
        shopCarPresenter.request(logUser.getId());
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences close = BaseApp.getApp().getSharedPreferences("close", Context.MODE_PRIVATE);
        boolean aBoolean = close.getBoolean("close", false);
        if (aBoolean) {
            if (close.getBoolean("closes", false)) {
                checked(false);
                totalPrice(0.0, 0);
            }
            return;
        } else {
            if (logUser != null) {
                baseline.setVisibility(View.GONE);
                shopRecyclerAdapter.checkAll(false);
                shopRecyclerAdapter.remove();
                shopCarPresenter.request(logUser.getId());
            } else {
                baseline.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume，为true时，Fragment已经可见
            SharedPreferences close = BaseApp.getApp().getSharedPreferences("close", Context.MODE_PRIVATE);
            boolean aBoolean = close.getBoolean("close", false);
            if (aBoolean) {
                if (close.getBoolean("closes", false)) {
                    checked(false);
                    totalPrice(0.0, 0);
                }
                return;
            } else {
                if (logUser != null) {
                    baseline.setVisibility(View.GONE);
                    shopRecyclerAdapter.checkAll(false);
                    shopRecyclerAdapter.remove();
                    shopCarPresenter.request(logUser.getId());
                } else {
                    baseline.setVisibility(View.GONE);
                }
            }
        } else {
            //相当于Fragment的onPause，为false时，Fragment不可见
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
            if (request.isSuccess()) {
                if (logUser != null) {
                    shopCarPresenter.request(logUser.getId());
                    heji.setVisibility(View.VISIBLE);
                    text_total.setVisibility(View.VISIBLE);
                    num_text.setVisibility(View.VISIBLE);
                    dele_text.setVisibility(View.GONE);
                    tv_toolBar_right.setText("编辑");
                    judge = false;
                }
            } else {
                ToastUtils.show(request.getMessage() + "");
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    /**
     * 设置标记
     */
    public static void getSign() {
        sign = 1;
    }

    public static void getSignY() {
        sign = 0;
    }

    //修改数量
    private class UpdateCount implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            if (request.isSuccess()) {
                promptDialog.dismiss();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
