package com.yikangcheng.admin.yikang.activity.fragment.orderform;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import com.yikangcheng.admin.yikang.activity.PartiCarActivity;
import com.yikangcheng.admin.yikang.activity.adapter.All_A_Adapter;
import com.yikangcheng.admin.yikang.activity.orderstatus.newactivity.OrderAccomActivity;
import com.yikangcheng.admin.yikang.activity.orderstatus.newactivity.OrderCancelActivity;
import com.yikangcheng.admin.yikang.activity.orderstatus.newactivity.OrderWaitActivity;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.ALLBean;
import com.yikangcheng.admin.yikang.bean.PayBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.paysdk.PayResult;
import com.yikangcheng.admin.yikang.presenter.AddShopPresenter;
import com.yikangcheng.admin.yikang.presenter.AllPresenter;
import com.yikangcheng.admin.yikang.presenter.DeleteOrderIdPresenter;
import com.yikangcheng.admin.yikang.presenter.NewOrderPresenter;
import com.yikangcheng.admin.yikang.util.TwoBallRotationProgressBar;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * 古祥坤 全部订单
 */
public class AllFragment extends BaseFragment implements ICoreInfe {
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
    private All_A_Adapter aAdapter;
    //查询全部订单
    private AllPresenter allPresenter;
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
    private ALLBean entity;
    private NewOrderPresenter newOrderPresenter;
    private AddShopPresenter addShopPresenter;
    private int num = 0;
    private int orderNum = 0;
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
        //待支付
        if (requestCode == 1 && resultCode == 2) {
            String delete = data.getStringExtra("delete");
            if (delete.equals("delete")) {
                aAdapter.removePosition(mPosition);
                aAdapter.notifyDataSetChanged();
            }
        }
        //已支付
        if (requestCode == 3 && resultCode == 4) {
            String delete = data.getStringExtra("delete");
            if (delete.equals("delete")) {
                aAdapter.removePosition(mPosition);
                aAdapter.notifyDataSetChanged();
            }
        }
        //已取消
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
        aAdapter = new All_A_Adapter(getContext());
        allPresenter = new AllPresenter(this);
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
        addShopPresenter = new AddShopPresenter(new AddShop());
    }

    @Override
    protected void initData() {
        //列表绑定方向
        StaggeredGridLayoutManager horizontalManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recycler.setLayoutManager(horizontalManager);
        //列表绑定适配器
        recycler.setAdapter(aAdapter);
        //网络请求
        allPresenter.request(getLogUser(getContext()).getId(), mPage, "");
        //删除订单
        deleteOrderIdPresenter = new DeleteOrderIdPresenter(new DeleteIcor());
    }

    /**
     * 处理点击事件
     *
     * @return
     */
    public void onTouchListener() {
        /**
         * 删除订单
         */
        aAdapter.setOnClickDeleteListener(new All_A_Adapter.OnClickDeleteListener() {
            @Override
            public void onClick(final int position, final int orderId) {
                mPromptDialog.showWarnAlert("确定要删除订单吗？", new PromptButton("取消", new PromptButtonListener() {
                    @Override
                    public void onClick(PromptButton button) {
                    }
                }), new PromptButton("确定", new PromptButtonListener() {
                    @Override
                    public void onClick(PromptButton button) {
                        mPosition = position;
                        deleteOrderIdPresenter.request(orderId);
                    }
                }));
            }
        });
        /**
         * 订单列表加载
         */
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPage++;
                //网络请求
                allPresenter.request(getLogUser(getContext()).getId(), mPage, "");
            }
        });
        /**
         * 订单列表刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                aAdapter.removeAll();
                //网络请求
                allPresenter.request(getLogUser(getContext()).getId(), mPage, "");
            }
        });

        /**
         * 去支付
         */
        aAdapter.setOnPayClickListener(new All_A_Adapter.onPayClickListener() {
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
        /**
         * 再次购买
         */
        aAdapter.setOnBuyClickListener(new All_A_Adapter.onBuyClickListener() {
            @Override
            public void onClick(int goodId, int num, double price) {
                addShopPresenter.request(getLogUser(getContext()).getId(), String.valueOf(goodId), "COMMODITY", String.valueOf(num), String.valueOf(price));
            }
        });
        /**
         * 再次购买多个商品
         */
        aAdapter.setOnBuySomeClickListener(new All_A_Adapter.onBuySomeClickListener() {
            @Override
            public void onClick(String orderBeans) {
                addShopPresenter.request(getLogUser(getContext()).getId(), orderBeans, "COMMODITY", 1 + "", 1 + "");
            }
        });
        /**
         * 跳转详情
         */
        aAdapter.setOnPartiCliclListener(new All_A_Adapter.onPartiCliclListener() {
            @Override
            public void onClick(int orderId, String orderState, int position) {
                mPosition = position;
                if (orderState.equals("INIT")) {
                    Intent intent = new Intent(getActivity(), OrderWaitActivity.class);
                    intent.putExtra("orderId_wait", orderId);
                    startActivityForResult(intent, 1);
                } else if (orderState.equals("SUCCESS")) {
                    Intent intent = new Intent(getActivity(), OrderAccomActivity.class);
                    intent.putExtra("orderId", orderId);
                    startActivityForResult(intent, 3);
                } else if (orderState.equals("CANCEL")) {
                    Intent intent = new Intent(getActivity(), OrderCancelActivity.class);
                    intent.putExtra("orderId_fack", orderId);
                    startActivityForResult(intent, 5);
                }
            }
        });
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_all;
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

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        entity = (ALLBean) request.getEntity();
        /**
         * 显示隐藏
         */
        if (entity == null) {
            refreshLayout.setVisibility(View.GONE);
            imgBut.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.dengdai).into(img_fragment_all);
            relativeLayout.setVisibility(View.VISIBLE);
        } else {
            List<ALLBean.OrderBean> orderBeans = entity.getOrder();
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
