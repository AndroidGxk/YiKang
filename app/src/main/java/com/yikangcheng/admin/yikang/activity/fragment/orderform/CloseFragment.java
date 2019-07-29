package com.yikangcheng.admin.yikang.activity.fragment.orderform;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.PartiCarActivity;
import com.yikangcheng.admin.yikang.activity.adapter.All_A_Adapter;
import com.yikangcheng.admin.yikang.activity.adapter.CloseAdapter_A;
import com.yikangcheng.admin.yikang.activity.orderstatus.FackOfActivity;
import com.yikangcheng.admin.yikang.activity.orderstatus.newactivity.OrderCancelActivity;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.ALLBean;
import com.yikangcheng.admin.yikang.bean.CloseBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.AddShopPresenter;
import com.yikangcheng.admin.yikang.presenter.AllPresenter;
import com.yikangcheng.admin.yikang.presenter.ClosePresenter;
import com.yikangcheng.admin.yikang.presenter.DeleteOrderIdPresenter;
import com.yikangcheng.admin.yikang.util.TwoBallRotationProgressBar;

import java.util.List;

import butterknife.BindView;
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * 已取消订单列表
 */
public class CloseFragment extends BaseFragment implements ICoreInfe {
    @BindView(R.id.rlv_fragment_all_dingdan)
    RecyclerView recycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.imgBut)
    ImageView imgBut;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.img_fragment_all)
    ImageView img_fragment_all;
    //订单列表Adapter
    private CloseAdapter_A aAdapter;
    //查询全部订单
    private ClosePresenter allPresenter;
    //分页页数
    private int mPage = 1;
    //弹框
    private PromptDialog mPromptDialog;
    //删除订单列表
    private DeleteOrderIdPresenter deleteOrderIdPresenter;
    //记录删除订单下标
    private int mPosition;
    private AddShopPresenter addShopPresenter;
    @BindView(R.id.progress)
    TwoBallRotationProgressBar progress;

    /**
     * 回传值
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5 && resultCode == 6) {
            String delete = data.getStringExtra("delete");
            if (delete.equals("delete")) {
                aAdapter.removePosition(mPosition);
                aAdapter.notifyDataSetChanged();
            }
        }
    }

    @SuppressLint("NewApi")
    @Override
    protected void initView(View view) {
        aAdapter = new CloseAdapter_A(getContext());
        allPresenter = new ClosePresenter(this);
        //点击事件处理
        onTouchListener();
        //允许上拉下拉
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadmore(true);
        //弹框
        //创建对象
        mPromptDialog = new PromptDialog(getActivity());
        //设置自定义属性
        mPromptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(1000);
        addShopPresenter = new AddShopPresenter(new AddShop());

    }

    @Override
    protected void initData() {
        //列表绑定方向
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        //列表绑定适配器
        recycler.setAdapter(aAdapter);
        //网络请求
        allPresenter.request(getLogUser(getContext()).getId(), mPage, "CANCEL");
        //删除订单
        deleteOrderIdPresenter = new DeleteOrderIdPresenter(new DeleteIcor());
    }

    /**
     * 处理点击事件
     *
     * @return
     */
    public void onTouchListener() {
        //跳转订单详情
        aAdapter.setOnPartiCliclListener(new CloseAdapter_A.onPartiCliclListener() {
            @Override
            public void onClick(int orderId, String orderState, int position) {
                mPosition = position;
                Intent intent = new Intent(getActivity(), OrderCancelActivity.class);
                intent.putExtra("orderId_fack", orderId);
                startActivityForResult(intent, 5);
            }
        });
        //删除订单
        aAdapter.setOnDeleteClickListener(new CloseAdapter_A.onDeleteClickListener() {
            @Override
            public void onClick(final int oriderId, final int position) {
                mPromptDialog.showWarnAlert("你确定要删除订单吗？", new PromptButton("取消", new PromptButtonListener() {
                    @Override
                    public void onClick(PromptButton button) {
                    }
                }), new PromptButton("确定", new PromptButtonListener() {
                    @Override
                    public void onClick(PromptButton button) {
                        mPosition = position;
                        deleteOrderIdPresenter.request(oriderId);
                    }
                }));
            }
        });
        //订单加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                //网络请求
                allPresenter.request(getLogUser(getContext()).getId(), mPage, "CANCEL");
            }
        });
        //订单刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                aAdapter.removeAll();
                //网络请求
                allPresenter.request(getLogUser(getContext()).getId(), mPage, "CANCEL");
            }
        });
        /**
         * 再次购买
         */
        aAdapter.setOnBuyClickListener(new CloseAdapter_A.onBuyClickListener() {
            @Override
            public void onClick(int goodId, int num, double price) {
                addShopPresenter.request(getLogUser(getContext()).getId(), String.valueOf(goodId), "COMMODITY", String.valueOf(num), String.valueOf(price));
            }
        });
        /**
         * 再次购买多个商品
         */
        aAdapter.setOnBuySomeClickListener(new CloseAdapter_A.onBuySomeClickListener() {
            @Override
            public void onClick(String orderBeans) {
                addShopPresenter.request(getLogUser(getContext()).getId(), orderBeans, "COMMODITY", 1 + "", 1 + "");
            }
        });
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_close;
    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        CloseBean entity = (CloseBean) request.getEntity();
        /**
         * 显示隐藏
         */
        if (entity == null) {
            refreshLayout.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.dengdai).into(img_fragment_all);
            imgBut.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
        } else {
            List<CloseBean.OrderBean> orderBeans = entity.getOrder();
            relativeLayout.setVisibility(View.GONE);
            refreshLayout.setVisibility(View.VISIBLE);
            aAdapter.addAll(orderBeans);
        }
        progress.setVisibility(View.GONE);
        progress.stopAnimator();
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefresh();
    }

    @Override
    public void fail(ApiException e) {
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefresh();
        refreshLayout.setVisibility(View.GONE);
        Glide.with(this).load(R.drawable.dengdai).into(img_fragment_all);
        imgBut.setVisibility(View.GONE);
        relativeLayout.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
        progress.stopAnimator();
    }

    /**
     * 删除订单
     */
    private class DeleteIcor implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            if (request.isSuccess()) {
                aAdapter.removePosition(mPosition);
                aAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    /**
     * 添加商品
     */
    private class AddShop implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            if (request.isSuccess()) {
                startActivity(new Intent(getContext(), PartiCarActivity.class));
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
