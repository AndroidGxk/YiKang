package com.yikangcheng.admin.yikang.activity.fragment.orderform;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.MainActivity;
import com.yikangcheng.admin.yikang.activity.adapter.CloseAdapter_A;
import com.yikangcheng.admin.yikang.activity.orderstatus.FackOfActivity;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.CloseBean;
import com.yikangcheng.admin.yikang.bean.DeleteOrderBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.ClosePresenter;
import com.yikangcheng.admin.yikang.presenter.DeleteOrderIdPresenter;
import com.yikangcheng.admin.yikang.util.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by lenovo on 2019/5/20.
 * WF
 */
public class CloseFragment extends BaseFragment implements ICoreInfe {
    private RecyclerView mRlvFragmentClose;
    private CloseAdapter_A mCloseAdapter_a;
    private SmartRefreshLayout mRefreshLayout;
    private ImageView mImgFragmentAccomplish;
    private ImageView mImgFragmentAccomplishQuguanghuang;
    private RelativeLayout mRelativeLayout;
    private int mPage = 1;
    private int mDeleteItemPostion;
    private int mDeletePosition;
    private PromptDialog mPromptDialog;
    private ImageView mImgBut;

    @Override
    protected void initView(View view) {

        //创建对象
        mPromptDialog = new PromptDialog(getActivity());
        //设置自定义属性
        mPromptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);

        mImgBut = view.findViewById(R.id.imgBut);
        mRlvFragmentClose = view.findViewById(R.id.rlv_fragment_close);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        mImgFragmentAccomplish = view.findViewById(R.id.img_fragment_accomplish);
        mImgFragmentAccomplishQuguanghuang = view.findViewById(R.id.img_fragment_accomplish_quguanghuang);
        mRelativeLayout = view.findViewById(R.id.relativeLayout);

        //布局走向
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRlvFragmentClose.setLayoutManager(linearLayoutManager);

        List<CloseBean.OrderBean> orderBeans = new ArrayList<>();
        //创建适配器
        mCloseAdapter_a = new CloseAdapter_A(orderBeans, getContext());
        //绑定适配器
        mRlvFragmentClose.setAdapter(mCloseAdapter_a);
        //跳转订单详情页面
        mCloseAdapter_a.setOnClickListener(new CloseAdapter_A.OnClickListener() {
            @Override
            public void OnClickListener(View v, int orderId, int position) {
                mDeletePosition = position;
                Intent intent = new Intent(getActivity(), FackOfActivity.class);
                intent.putExtra("orderId_fack", orderId);
                startActivityForResult(intent, 5);
            }
        });

        //点击去逛逛跳转首页
        mImgFragmentAccomplishQuguanghuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });


        /**
         * 一键置顶
         */
        mRlvFragmentClose.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalDy = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy -= dy;
                if (totalDy < 0) {
                    mImgBut.setVisibility(View.VISIBLE);
                } else {
                    mImgBut.setVisibility(View.GONE);
                }
            }
        });
        mImgBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRlvFragmentClose.smoothScrollToPosition(0);
            }
        });

        //加载动图
        Glide.with(this).load(R.drawable.dongtu).into(mImgFragmentAccomplish);

        /**
         * P层
         */
        initMvp(mPage);


        //解决滑动不流畅
        mRlvFragmentClose.setHasFixedSize(true);
        mRlvFragmentClose.setNestedScrollingEnabled(false);

        //解决item间距
        int spanCount_tuijian = 1; // 3 columns
        int spacing_tuijian = 20; // 50px
        boolean includeEdge_tuijian = false;
        mRlvFragmentClose.addItemDecoration(new SpacesItemDecoration(spanCount_tuijian, spacing_tuijian, includeEdge_tuijian));

        /**
         * 上拉加载
         */
        initShuaXinJiaZai();

        /**
         * 点击垃圾桶删除订单
         */
        initDelete();
    }

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
                mCloseAdapter_a.mList.remove(mDeletePosition);
                mCloseAdapter_a.notifyDataSetChanged();
            }
        }
    }

    //点击删除订单并提示弹窗
    private void initDelete() {
        mCloseAdapter_a.setOnClickListenerDelete(new CloseAdapter_A.OnClickListenerDelete() {
            @Override
            public void OnClickListener(View v, int position) {
                mDeleteItemPostion = position;
                mPromptDialog.showWarnAlert("你确定要删除订单吗？", new PromptButton("取消", new PromptButtonListener() {
                    @Override
                    public void onClick(PromptButton button) {
                    }
                }), confirm);
            }
        });
    }

    //按钮的定义，创建一个按钮的对象
    PromptButton confirm = new PromptButton("确定", new PromptButtonListener() {
        @Override
        public void onClick(PromptButton button) {
            int orderId = mCloseAdapter_a.mList.get(mDeleteItemPostion).getOrderId();
            DeleteOrderIdPresenter deleteOrderIdPresenter = new DeleteOrderIdPresenter(new delete());
            deleteOrderIdPresenter.request(orderId);
        }
    });

    //P层
    private void initMvp(int page) {
        ClosePresenter closePresenter = new ClosePresenter(this);
        closePresenter.request(getLogUser(getContext()).getId(), page, "CANCEL");
    }

    //上拉加载
    private void initShuaXinJiaZai() {
        //刷新的监听事件
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                //请求数据
//                mPage = 1;
//                initMvp(mPage);
                refreshLayout.finishRefresh();  //刷新完成
            }
        });
        //加载的监听事件
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPage++;
                initMvp(mPage);
                refreshLayout.finishLoadMore();      //加载完成
                //refreshLayout.finishLoadMoreWithNoMoreData();  //全部加载完成,没有数据了调用此方法  这个方法调用了就加载一夜再也不加载了
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_close;
    }

    /**
     * 删除订单
     */
    public class delete implements ICoreInfe {

        public DeleteOrderBean mMEntity;

        @Override
        public void success(Object data) {
            Request request = (Request) data;
            mMEntity = (DeleteOrderBean) request.getEntity();
            //有需要在这打印一下message的返回值现在返回的是空的  让后台看一下  做一个判断
            // Log.e("aaa", "success: "+mMEntity.get );
            mCloseAdapter_a.mList.remove(mDeleteItemPostion);
            mCloseAdapter_a.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }


    @Override
    public void success(Object data) {
        Request request = (Request) data;
        CloseBean entity = (CloseBean) request.getEntity();

        /**
         * 显示隐藏
         */
        if (entity.getOrder() == null) {
            mRefreshLayout.setVisibility(View.GONE);
            mImgBut.setVisibility(View.GONE);
            mRelativeLayout.setVisibility(View.VISIBLE);
        } else {
            mRelativeLayout.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            /**
             * 上拉加载
             */
            initShuaXinJiaZai();
        }
        mCloseAdapter_a.AddAll(entity.getOrder());
    }

    @Override
    public void fail(ApiException e) {

    }
}
