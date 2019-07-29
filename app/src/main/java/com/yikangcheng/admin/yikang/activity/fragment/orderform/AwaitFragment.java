package com.yikangcheng.admin.yikang.activity.fragment.orderform;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.hjq.toast.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.OrderFormActivity;
import com.yikangcheng.admin.yikang.activity.adapter.AwaitAdapter;
import com.yikangcheng.admin.yikang.activity.orderstatus.newactivity.OrderWaitActivity;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.ObligationBean;
import com.yikangcheng.admin.yikang.bean.PayBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.paysdk.PayResult;
import com.yikangcheng.admin.yikang.presenter.DeleteOrderIdPresenter;
import com.yikangcheng.admin.yikang.presenter.NewOrderPresenter;
import com.yikangcheng.admin.yikang.presenter.ObligationPresenter;
import com.yikangcheng.admin.yikang.util.TwoBallRotationProgressBar;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * 古祥坤
 * 待付款页面
 */
public class AwaitFragment extends BaseFragment implements ICoreInfe {
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
    @BindView(R.id.progress)
    TwoBallRotationProgressBar progress;
    //订单列表Adapter
    private AwaitAdapter aAdapter;
    //查询全部订单
    private ObligationPresenter allPresenter;
    //分页页数
    private int mPage = 1;
    //弹框
    private PromptDialog mPromptDialog;
    //删除订单列表
    private DeleteOrderIdPresenter deleteOrderIdPresenter;
    //记录删除订单下标
    private int mPosition;
    private PayBean mEntity;
    private String mPayType = "";
    private NewOrderPresenter newOrderPresenter;
    private ObligationBean entity;

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
        if (requestCode == 1 && resultCode == 2) {
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
        aAdapter = new AwaitAdapter(getContext());
        allPresenter = new ObligationPresenter(this);
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
        //生成新的订单
        newOrderPresenter = new NewOrderPresenter(new NewOrder());
    }

    @Override
    protected void initData() {
        //列表绑定方向
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        //列表绑定适配器
        recycler.setAdapter(aAdapter);
        //网络请求
        allPresenter.request(getLogUser(getContext()).getId(), mPage, "INIT");
        //删除订单
        deleteOrderIdPresenter = new DeleteOrderIdPresenter(new DeleteIcor());

    }

    /**
     * 处理点击事件
     *
     * @return
     */
    public void onTouchListener() {
        //商品跳转详情
        aAdapter.setOnPartiCliclListener(new AwaitAdapter.onPartiCliclListener() {
            @Override
            public void onClick(int orderId, String orderState, int position) {
                mPosition = position;
                Intent intent = new Intent(getActivity(), OrderWaitActivity.class);
                intent.putExtra("orderId_wait", orderId);
                startActivityForResult(intent, 1);
            }
        });
        //订单加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                //网络请求
                allPresenter.request(getLogUser(getContext()).getId(), mPage, "INIT");
            }
        });
        //订单刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                aAdapter.removeAll();
                //网络请求
                allPresenter.request(getLogUser(getContext()).getId(), mPage, "INIT");
            }
        });
        /**
         * 去支付
         */
        aAdapter.setOnPayClickListener(new AwaitAdapter.onPayClickListener() {
            @Override
            public void onClick(int orderId, int position) {
                mPayType = entity.getOrder().get(position).getPayType();
                if (mPayType.equals("ALIPAY")) {
                    newOrderPresenter.request(orderId, "ALIPAY", "");
                } else if (mPayType.equals("WEIXIN")) {
                    newOrderPresenter.request(orderId, "WEIXIN", "");
                } else {
                    ToastUtils.show("系统错误,支付失败");
                }
            }
        });
    }

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_CHECK_FLAG = 2;
    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Intent intent = new Intent(getContext(), OrderFormActivity.class);
                        intent.putExtra("tab", "daishouhuo");
                        startActivity(intent);
                        getActivity().finish();
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            ToastUtils.show("支付结果确认中");

                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
//                            Intent intent = new Intent(WaitForpaymentActivity.this, PayResultActivity.class);
//                            intent.putExtra("pay", 2);
//                            startActivity(intent);
                            getActivity().finish();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Intent intent = new Intent(getContext(), OrderFormActivity.class);
                            intent.putExtra("tab", "daifukuan");
                            startActivity(intent);
                            getActivity().finish();
                        }
                    }
                    break;
                }
                case SDK_CHECK_FLAG: {
                    ToastUtils.show("检查结果为：" + msg.obj);
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_await;
    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        entity = (ObligationBean) request.getEntity();
        /**
         * 显示隐藏
         */
        if (entity == null) {
            refreshLayout.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.dengdai).into(img_fragment_all);
            imgBut.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
        } else {
            List<ObligationBean.OrderBean> orderBeans = entity.getOrder();
            relativeLayout.setVisibility(View.GONE);
            refreshLayout.setVisibility(View.VISIBLE);
            aAdapter.addAll(orderBeans);
        }
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefresh();
        progress.setVisibility(View.GONE);
        progress.stopAnimator();
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
     * 生成新的订单号
     */
    private class NewOrder implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            if (request.isSuccess()) {
                mEntity = (PayBean) request.getEntity();
                if (mPayType.equals("ALIPAY")) {
                    //todo 支付宝
                    // 构造PayTask 对象
                    // 调用支付接口，获取支付结果
                    Runnable payRunnable = new Runnable() {
                        @Override
                        public void run() {
                            PayTask alipay = new PayTask(getActivity());
                            if (mEntity.getOrderinfo() == null || mEntity.getOrderinfo().equals("")) {
                                return;
                            }
                            Map<String, String> result = alipay.payV2(mEntity.getOrderinfo(), true);
                            Message msg = new Message();
                            msg.what = SDK_PAY_FLAG;
                            msg.obj = result;
                            mHandler.sendMessage(msg);
                        }
                    };
                    // 必须异步调用
                    Thread payThread = new Thread(payRunnable);
                    payThread.start();
                } else if (mPayType.equals("WEIXIN")) {
                    PayReq req = new PayReq();//PayReq就是订单信息对象
                    //给req对象赋值
                    req.appId = "wx38a0aef5df24fe50";
                    req.partnerId = "1528387611";
                    req.prepayId = mEntity.getPrepayId();
                    req.packageValue = "Sign=WXPay";
                    req.nonceStr = mEntity.getNonceStr();
                    req.timeStamp = mEntity.getTimeStamp();
                    req.sign = mEntity.getSign();
                    BaseApp.mWxApi.sendReq(req);//将订单信息对象发送给微信服务器，即发送支付请求
                } else {
                    ToastUtils.show("系统错误,支付失败");
                }
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
