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
import com.yikangcheng.admin.yikang.activity.adapter.All_A_Adapter;
import com.yikangcheng.admin.yikang.activity.orderstatus.CloseTheDealActivity;
import com.yikangcheng.admin.yikang.activity.orderstatus.FackOfActivity;
import com.yikangcheng.admin.yikang.activity.orderstatus.WaitForpaymentActivity;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.ALLBean;
import com.yikangcheng.admin.yikang.bean.DeleteOrderBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.AllPresenter;
import com.yikangcheng.admin.yikang.presenter.DeleteOrderIdPresenter;
import com.yikangcheng.admin.yikang.util.SpacesItemDecoration;

import java.util.ArrayList;

import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * Created by lenovo on 2019/5/20.
 * WF
 */
public class AllFragment extends BaseFragment implements ICoreInfe {
    private RecyclerView mRlvFragmentAllDingdan;
    private All_A_Adapter mAll_a_adapter;
    private ImageView mImgFragmentAll;
    private ImageView mImgFragmentAllQuguanghuang;
    private RelativeLayout mRelativeLayout;
    private SmartRefreshLayout mRefreshLayout;
    private int mPage = 1;
    private int mDeleteItemPostion;
    private int mDeletePosition;
    private PromptDialog mPromptDialog;
    private ImageView mImgBut;
    private GoTopScrollView mScrollView;


    @Override
    protected void initView(View view) {

        //创建对象
        mPromptDialog = new PromptDialog(getActivity());
        //设置自定义属性
        mPromptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);


        mRlvFragmentAllDingdan = view.findViewById(R.id.rlv_fragment_all_dingdan);
        mImgFragmentAll = view.findViewById(R.id.img_fragment_all);
        mImgFragmentAllQuguanghuang = view.findViewById(R.id.img_fragment_all_quguanghuang);
        mRelativeLayout = view.findViewById(R.id.relativeLayout);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        mImgBut = view.findViewById(R.id.imgBut);
        mScrollView = view.findViewById(R.id.scr);

        Glide.with(getContext()).load(R.drawable.dongtu).into(mImgFragmentAll);
        mScrollView.setScrollListener(mImgBut); //里面的参数就是那张小图片

        //布局走向
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRlvFragmentAllDingdan.setLayoutManager(linearLayoutManager);


        ArrayList<ALLBean.OrderBean> orderBeans = new ArrayList<>();

        //创建适配器
        mAll_a_adapter = new All_A_Adapter(getContext(), orderBeans);
        //绑定适配器
        mRlvFragmentAllDingdan.setAdapter(mAll_a_adapter);

        mAll_a_adapter.setOnClickListener(new All_A_Adapter.OnClickListener() {

            @Override
            public void OnClickListener(View v, int orderId, String orderState, int position) {
                mDeletePosition = position;
                if (orderState.equals("INIT")) {
                    Intent intent = new Intent(getActivity(), WaitForpaymentActivity.class);
                    intent.putExtra("orderId_wait", orderId);
                    startActivityForResult(intent, 1);
                } else if (orderState.equals("SUCCESS")) {
                    Intent intent = new Intent(getActivity(), CloseTheDealActivity.class);
                    intent.putExtra("orderId", orderId);
                    startActivityForResult(intent, 3);
                } else if (orderState.equals("CANCEL")) {
                    Intent intent = new Intent(getActivity(), FackOfActivity.class);
                    intent.putExtra("orderId_fack", orderId);
                    startActivityForResult(intent, 5);
                }
            }
        });
        /**
         * P层
         */
        initMvp(mPage);

        /**
         * 让rlv---item之间有空隙
         */
        int spanCount = 1; // 3 columns
        int spacing = 20; // 50px
        boolean includeEdge = false;
        mRlvFragmentAllDingdan.addItemDecoration(new SpacesItemDecoration(spanCount, spacing, includeEdge));
        if (orderBeans.size() < 0) {
            mRelativeLayout.setVisibility(View.VISIBLE);
            mRefreshLayout.setVisibility(View.GONE);
        } else {
            mRelativeLayout.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
        }

        initShuaXinJiaZai();

        /**
         * 点击垃圾桶删除订单
         */
        initDelete();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //待支付
        if (requestCode == 1 && resultCode == 2) {
            String delete = data.getStringExtra("delete");
            if (delete.equals("delete")) {
                mAll_a_adapter.mList.remove(mDeletePosition);
                mAll_a_adapter.notifyDataSetChanged();
            }
        }
        //已支付
        if (requestCode == 3 && resultCode == 4) {
            String delete = data.getStringExtra("delete");
            if (delete.equals("delete")) {
                mAll_a_adapter.mList.remove(mDeletePosition);
                mAll_a_adapter.notifyDataSetChanged();
            }
        }
        //已取消
        if (requestCode == 5 && resultCode == 6) {
            String delete = data.getStringExtra("delete");
            if (delete.equals("delete")) {
                mAll_a_adapter.mList.remove(mDeletePosition);
                mAll_a_adapter.notifyDataSetChanged();
            }
        }
    }

    private void initDelete() {
        mAll_a_adapter.setOnClickListenerDelete(new All_A_Adapter.OnClickListenerDelete() {
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
            int orderId = mAll_a_adapter.mList.get(mDeleteItemPostion).getOrderId();
            DeleteOrderIdPresenter deleteOrderIdPresenter = new DeleteOrderIdPresenter(new delete());
            deleteOrderIdPresenter.request(orderId);
        }
    });


    private void initMvp(int page) {
        AllPresenter allPresenter = new AllPresenter(this);
        allPresenter.request(getLogUser(getContext()).getId(), page);
    }

    private void initShuaXinJiaZai() {
        //刷新的监听事件
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //请求数据
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
        return R.layout.fragment_all;
    }

    public class delete implements ICoreInfe {

        public DeleteOrderBean mMEntity;

        @Override
        public void success(Object data) {
            Request request = (Request) data;
            mMEntity = (DeleteOrderBean) request.getEntity();
            //有需要在这打印一下message的返回值现在返回的是空的  让后台看一下  做一个判断
            // Log.e("aaa", "success: "+mMEntity.get );
            mAll_a_adapter.mList.remove(mDeleteItemPostion);
            mAll_a_adapter.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        ALLBean entity = (ALLBean) request.getEntity();
        mAll_a_adapter.allData(entity.getOrder());


    }

    @Override
    public void fail(ApiException e) {

    }


}
