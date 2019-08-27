package com.yikangcheng.admin.yikang.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.RecommendAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.ShopRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.particulars.ParticularsActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
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
import com.yikangcheng.admin.yikang.util.TwoBallRotationProgressBar;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

public class PartiCarActivity extends BaseActivtiy implements ShopRecyclerAdapter.TotalPriceListener, ShopRecyclerAdapter.checkBoxTouchListener, ICoreInfe, XRecyclerView.LoadingListener {
    private RecyclerView shop_recycler;
    private XRecyclerView shop_recyclertwo;
    private ShopRecyclerAdapter shopRecyclerAdapter;
    private RelativeLayout null_car;
    private RelativeLayout diviline;
    private RelativeLayout baseline;
    private RelativeLayout ly_toolBar;
    private RelativeLayout base_btn;
    private CheckBox all_check;
    private TextView text_total, num_text, heji, dele_text;
    private TextView tv_toolBar_right;
    private boolean judge = false;//判断编辑或完成
    private static ShopCarPresenter shopCarPresenter;
    private List<ShopCarBean> entity;
    private boolean isclick;
    private RecommendAdapter mRecommendAdapter;
    private DeleteShopPresenter deleteShopPresenter;
    private String mId = "";
    private ImageView back_img;
    private static LoginBean logUser;
    private int mPage = 1;
    private NestedScrollView nestedSV;
    private int width;
    private PromptDialog promptDialog;
    private UpdateCountPresenter updateCountPresenter;
    private TwoBallRotationProgressBar progress;
    @BindView(R.id.title)
    TextView title;

    @Override
    protected void initView() {
//创建对象
        promptDialog = new PromptDialog(PartiCarActivity.this);
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(100);
        logUser = getLogUser(PartiCarActivity.this);
        //设置状态栏颜色
        if (!getLogUser(this).getThemeColors().equals("")) {
            StatusBarUtil.setStatusBarMode(this, true, Color.parseColor(getLogUser(this).getThemeColors()));
        } else {
            StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        }
        ly_toolBar = (RelativeLayout) findViewById(R.id.ly_toolBar);
        ly_toolBar.setBackgroundColor(Color.parseColor(getLogUser(this).getThemeColors()));
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        width = wm.getDefaultDisplay().getWidth();
        shop_recycler = (RecyclerView) findViewById(R.id.shop_recycler);
        shop_recyclertwo = (XRecyclerView) findViewById(R.id.shop_recyclertwo);
        all_check = (CheckBox) findViewById(R.id.all_check);
        num_text = (TextView) findViewById(R.id.num_text);
        GradientDrawable myGrad = (GradientDrawable) num_text.getBackground();
        myGrad.setColor(Color.parseColor(getLogUser(this).getThemeColors()));
        diviline = (RelativeLayout) findViewById(R.id.diviline);
        baseline = (RelativeLayout) findViewById(R.id.baseline);
        nestedSV = (NestedScrollView) findViewById(R.id.nestedSV);
        dele_text = (TextView) findViewById(R.id.dele_text);
        base_btn = (RelativeLayout) findViewById(R.id.base_btn);
        progress = (TwoBallRotationProgressBar) findViewById(R.id.progress);
        null_car = (RelativeLayout) findViewById(R.id.null_car);
        tv_toolBar_right = (TextView) findViewById(R.id.tv_toolBar_right);
//        tv_toolBar_right.setTextColor(Color.parseColor());
//        title.setTextColor(Color.parseColor());
        back_img = (ImageView) findViewById(R.id.back_img);
        heji = (TextView) findViewById(R.id.heji);
        text_total = (TextView) findViewById(R.id.text_total);
        shopCarPresenter = new ShopCarPresenter(this);
        deleteShopPresenter = new DeleteShopPresenter(new DeleteShop());

        /**
         * 为你推荐
         */
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        shop_recyclertwo.setLayoutManager(gridLayoutManager);
        mRecommendAdapter = new RecommendAdapter(this);
        shop_recyclertwo.setAdapter(mRecommendAdapter);
        mRecommendAdapter.setOnClickListener(new RecommendAdapter.OnClickListener() {
            @Override
            public void OnClickListener(View v, int id) {
                Intent intent = new Intent(PartiCarActivity.this, ParticularsActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        int spanCount = 2; // 3 columns
        int spacing = 20; // 50px
        boolean includeEdge = false;
        shop_recyclertwo.addItemDecoration(new SpacesItemDecoration(spanCount, spacing, includeEdge));
        if (logUser != null) {
        }
    }

    @Override
    protected void initEventData() {
        //修改数量
        updateCountPresenter = new UpdateCountPresenter(new UpdateCount());
        shop_recyclertwo.setHasFixedSize(true);
        shop_recyclertwo.setNestedScrollingEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(PartiCarActivity.this) {
            @Override
            public boolean canScrollVertically() {
                // 直接禁止垂直滑动
                return false;
            }
        };
        // TODO: 2019/5/15 侧拉删除
        //购物车recyclerview
        shop_recycler.setLayoutManager(layoutManager);
        shopRecyclerAdapter = new ShopRecyclerAdapter(PartiCarActivity.this);
        shopRecyclerAdapter.setTotalPriceListener(this);//设置总价回调接口
        shopRecyclerAdapter.setCheckBoxTouchListener(this);
        shop_recycler.setAdapter(shopRecyclerAdapter);
        //为你推荐recyclerview
        shop_recyclertwo.setLayoutManager(new GridLayoutManager(PartiCarActivity.this, 2));
        //请求数量接口
        shopRecyclerAdapter.setSumClickListener(new ShopRecyclerAdapter.sumClickListener() {
            @Override
            public void onClick(int count, int id) {
                LoginBean logUser = getLogUser(PartiCarActivity.this);
                if (logUser != null) {
                    updateCountPresenter.request(logUser.getId(), id, count);
                    promptDialog.showLoading("");
                }
            }
        });
        //点击事件

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
                    ToastUtils.show("请选择商品");
                    return;
                }
                List<ShopCarBean> shopList = shopRecyclerAdapter.getShopList();
                Intent intent = new Intent(PartiCarActivity.this, CloseActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("shopList", (Serializable) shopList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        shopRecyclerAdapter.setGoParClickListener(new ShopRecyclerAdapter.goParClickListener() {
            @Override
            public void onclick(int id) {
                Intent intent = new Intent(PartiCarActivity.this, ParticularsActivity.class);
                intent.putExtra("id", id);
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
         * 退出页面
         */
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
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
            deleteShopPresenter.request(getLogUser(PartiCarActivity.this).getId(), mId);
            mId = "";
            all_check.setChecked(false);
            shopRecyclerAdapter.checkAll(all_check.isChecked());

        }
    });

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_parti_car;
    }

    @Override
    protected void createPresenter() {

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
        if (entity != null) {
            if (entity.size() == 0) {
                null_car.setVisibility(View.VISIBLE);
                shop_recycler.setVisibility(View.GONE);
            }
            shopRecyclerAdapter.remove();
            shopRecyclerAdapter.addAll(entity);
        } else {
            ToastUtils.show("购物车数据为空");
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.GONE);
                progress.stopAnimator();
            }
        }, 500);
    }

    @Override
    public void fail(ApiException e) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.GONE);
                progress.stopAnimator();
            }
        }, 500);
    }

    public static void getRequest() {
        shopCarPresenter.request(logUser.getId());
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences close = getSharedPreferences("close", Context.MODE_PRIVATE);
        boolean aBoolean = close.getBoolean("close", false);
        if (aBoolean) {
            if (close.getBoolean("closes", false)) {
                checked(false);
                totalPrice(0.0, 0);
            }
            return;
        } else {
            if (logUser != null) {
                base_btn.setVisibility(View.VISIBLE);
                shopRecyclerAdapter.checkAll(false);
                shopRecyclerAdapter.remove();
                shopCarPresenter.request(logUser.getId());
            } else {
                base_btn.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onRefresh() {
        mRecommendAdapter.removeAll();
    }

    @Override
    public void onLoadMore() {
        mPage++;
    }

    /**
     * 删除购物车商品
     */
    private class DeleteShop implements ICoreInfe {
        @Override
        public void success(Object data) {
            LoginBean logUser = getLogUser(PartiCarActivity.this);
            Request request = (Request) data;
            ToastUtils.show(request.getMessage() + "");
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
