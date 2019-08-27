package com.yikangcheng.admin.yikang.activity.orderstatus.newactivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.H5SecActivity;
import com.yikangcheng.admin.yikang.activity.PartiCarActivity;
import com.yikangcheng.admin.yikang.activity.adapter.ordernew_adapter.OrderReceAdapter;
import com.yikangcheng.admin.yikang.activity.copy.CopyButtonLibrary;
import com.yikangcheng.admin.yikang.activity.particulars.ParticularsActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.WaliDealBean;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.AddShopPresenter;
import com.yikangcheng.admin.yikang.presenter.CloseTheDeallPresenter;
import com.yikangcheng.admin.yikang.presenter.DeleteOrderIdPresenter;
import com.yikangcheng.admin.yikang.presenter.SignOrderPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;
import com.yikangcheng.admin.yikang.util.TwoBallRotationProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

/**
 * 新版待收货订单详细页面
 */
public class OrderReceActivity extends BaseActivtiy implements ICoreInfe {
    @BindView(R.id.daishou_recycle)
    RecyclerView recycle;
    @BindView(R.id.opneorclose_btn)
    LinearLayout opneorclose_btn;
    @BindView(R.id.text_open_close)
    TextView text_open_close;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.nested)
    NestedScrollView nested;
    @BindView(R.id.user_name)
    TextView user_name;
    @BindView(R.id.user_phone)
    TextView user_phone;
    @BindView(R.id.user_address)
    TextView user_address;
    @BindView(R.id.invtype_text)
    TextView invtype_text;
    @BindView(R.id.danhao)
    TextView danhao;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.fangshi)
    TextView fangshi;
    @BindView(R.id.peisong)
    TextView peisong;
    @BindView(R.id.good_sumprice)
    TextView good_sumprice;
    @BindView(R.id.good_yun)
    TextView good_yun;
    @BindView(R.id.yingfukuan)
    TextView yingfukuan;
    @BindView(R.id.progress)
    TwoBallRotationProgressBar progress;
    @BindView(R.id.kefu)
    LinearLayout kefu;
    @BindView(R.id.copay_img)
    ImageView copay_img;
    @BindView(R.id.cancel_btn)
    TextView cancel_btn;
    @BindView(R.id.back_img)
    ImageView back_img;
    @BindView(R.id.toolbar_activity_orderfrom)
    RelativeLayout toolbar_activity_orderfrom;

    //待收货订单列表
    private OrderReceAdapter receAdapter;
    //接收订单ID
    private Intent mIntent;
    /**
     * 判断展开或者查看
     */
    public boolean isClick = false;
    //订单详情
    private CloseTheDeallPresenter closeTheDeallPresenter;
    private int mOrderId;
    private List<WaliDealBean.DetailsListBean> mList;
    //收起
    private List<WaliDealBean.DetailsListBean> mLists = new ArrayList<>();
    //展开
    private List<WaliDealBean.DetailsListBean> mListz = new ArrayList<>();
    private PromptDialog mPromptDialog;
    //再次购买
    private AddShopPresenter addShopPresenter;
    //是否可以取消订单
    private boolean isCancel = false;
    private SignOrderPresenter signOrderPresenter;
    private int mPosition;
    private TextView mButton;
    private Dialog dialog;
    private View viewd;
    private WaliDealBean entity;

    @Override
    protected void initView() {
        //设置状态栏颜色
        if (!getLogUser(this).getThemeColors().equals("")) {
            StatusBarUtil.setStatusBarMode(this, true, Color.parseColor(getLogUser(this).getThemeColors()));
        } else {
            StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        }
        toolbar_activity_orderfrom.setBackgroundColor(Color.parseColor(getLogUser(this).getThemeColors()));
        cancel_btn.setTextColor(Color.parseColor(getLogUser(this).getThemeColors()));
        GradientDrawable gradientDrawable = (GradientDrawable) cancel_btn.getBackground();
        gradientDrawable.setStroke(2, Color.parseColor(getLogUser(this).getThemeColors()));
        /**
         * 订单详情
         */
        closeTheDeallPresenter = new CloseTheDeallPresenter(this);
        /**
         * 接收订单ID
         */
        mIntent = getIntent();
        mOrderId = mIntent.getIntExtra("orderId", 0);
        /**
         * 弹框创建
         */
        mPromptDialog = new PromptDialog(this);
        //设置自定义属性
        mPromptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);

        /**
         * 再次购买
         */
        addShopPresenter = new AddShopPresenter(new AddShop());
        /**
         * 订单详情Adapter
         */
        receAdapter = new OrderReceAdapter(this, getLogUser(this).getThemeColors());
        /**
         * 点击事件
         */
        onClickListener();
        /**
         * 确认收货
         */
        signOrderPresenter = new SignOrderPresenter(new SignOrder());
        viewd = getLayoutInflater().inflate(R.layout.orderrecy_dialog, null);
        dialog = new Dialog(OrderReceActivity.this, R.style.dialogWindowAnim);
    }

    /**
     * 点击事件处理
     *
     * @return
     */
    public void onClickListener() {
        /**
         * 展开或合起
         */
        opneorclose_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isClick) {
                    hideItem();
                    nested.scrollTo(0, 0);
                    receAdapter.onClickClearListener();
                    isClick = false;
                } else {
                    showItem();
                    receAdapter.onClickClearListener();
                    isClick = true;
                }
            }
        });
        /**
         * 进入商品详情页
         */
        receAdapter.setOnGoPariAccomClickListener(new OrderReceAdapter.onGoPariAccomClickListener() {
            @Override
            public void onClick(int goodId) {
                Intent intent = new Intent(OrderReceActivity.this, ParticularsActivity.class);
                intent.putExtra("id", goodId);
                startActivity(intent);
            }
        });
        /**
         * 查看物流
         */
        receAdapter.setOnLookListener(new OrderReceAdapter.onLookListener() {
            @Override
            public void onClick(int id) {
                Intent intent = new Intent(OrderReceActivity.this, H5SecActivity.class);
                intent.putExtra("http", "https://www.yikch.com/mobile/appShow/orderLogistics?orderDetailsId=" + id + "&type=android");
                intent.putExtra("title", "物流状态");
                startActivity(new Intent(intent));
            }
        });
        /**
         * 确认收货
         */
        receAdapter.setOnTakeListener(new OrderReceAdapter.onTakeListener() {
            @Override
            public void onClick(final int id, int position, View button) {
                mPosition = position;
                mButton = (TextView) button;
                mPromptDialog.showWarnAlert("确定要收货吗？", new PromptButton("取消", new PromptButtonListener() {
                    @Override
                    public void onClick(PromptButton button) {
                        receAdapter.notifyDataSetChanged();
                    }
                }), new PromptButton("确定", new PromptButtonListener() {
                    @Override
                    public void onClick(PromptButton button) {
                        if (getLogUser(OrderReceActivity.this) != null) {
                            signOrderPresenter.request(getLogUser(OrderReceActivity.this).getId(), id);
                            mPromptDialog.showLoading("加载中...");
                        }
                    }
                }));
            }
        });
        /**
         * 点击客服
         */
        kefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Information info = new Information();
                info.setAppkey("7560599b63bf43378d05d018ded42cdd");
                SobotApi.setCustomRobotHelloWord(OrderReceActivity.this, "您好，易康成客服很高兴为您服务，请问有什么可以帮助您的？");
                SobotApi.startSobotChat(OrderReceActivity.this, info);
            }
        });

        /**
         * 复制单号
         */
        copay_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //传入需要复制的文字的控件
                CopyButtonLibrary copyButtonLibrary = new CopyButtonLibrary(getApplicationContext(), danhao);
                copyButtonLibrary.init();
            }
        });
        /**
         * 取消订单
         */
        cancel_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).getMailStatus() == 2) {
                        isCancel = true;
                    }
                }
                if (isCancel) {
                    TextView lianxi_kefu = viewd.findViewById(R.id.lianxi_kefu);
                    TextView queren_btn = viewd.findViewById(R.id.queren_btn);
                    dialog.setContentView(viewd, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    Window window = dialog.getWindow();
                    //设置显示动画
                    window.setWindowAnimations(R.style.dialogWindowAnim);
                    WindowManager.LayoutParams wl = window.getAttributes();
                    wl.x = 0;
                    wl.y = 0;
                    //设置显示位置
                    dialog.onWindowAttributesChanged(wl);
                    //设置点击外围消散
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    lianxi_kefu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            Information info = new Information();
                            info.setAppkey("7560599b63bf43378d05d018ded42cdd");
                            SobotApi.setCustomRobotHelloWord(OrderReceActivity.this, "您好，易康成客服很高兴为您服务，请问有什么可以帮助您的？");
                            SobotApi.startSobotChat(OrderReceActivity.this, info);
                        }
                    });
                    queren_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    return;
                } else {
                    mPromptDialog.showWarnAlert("你确定要删除订单吗？", new PromptButton("取消", new PromptButtonListener() {
                        @Override
                        public void onClick(PromptButton button) {
                        }
                    }), new PromptButton("确定", new PromptButtonListener() {
                        @Override
                        public void onClick(PromptButton button) {
                            DeleteOrderIdPresenter deleteOrderIdPresenter = new DeleteOrderIdPresenter(new DeleteOrder());

                            deleteOrderIdPresenter.request(entity.getOrder().getOrderId());
                        }
                    }));
                }
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
    }

    @Override
    protected void initEventData() {
        closeTheDeallPresenter.request(mOrderId);
        //布局走向
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setAdapter(receAdapter);
        //解决滑动不流畅
        recycle.setHasFixedSize(true);
        recycle.setNestedScrollingEnabled(false);
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_order_rece;
    }

    @Override
    protected void createPresenter() {

    }

    /**
     * 收起
     */
    public void hideItem() {
        receAdapter.removeAll();
        mLists.clear();
        mListz.clear();
        if (mList != null) {
            for (int i = 0; i < 2; i++) {
                mLists.add(mList.get(i));
            }
            receAdapter.addAll(mLists);
        }
        text_open_close.setText("查看更多");
        image.setBackgroundResource(R.drawable.zhanshi);
    }

    /**
     * 展开
     */
    public void showItem() {
        receAdapter.removeAll();
        mListz.clear();
        mLists.clear();
        if (mList != null) {
            for (int i = 0; i < mList.size(); i++) {
                mListz.add(mList.get(i));
            }
            receAdapter.addAll(mListz);
        }
        text_open_close.setText("点击收起");
        image.setBackgroundResource(R.drawable.jiantoushang);
    }

    /**
     * 订单详情数据
     *
     * @param data
     */
    @Override
    public void success(Object data) {
        Request request = (Request) data;
        entity = (WaliDealBean) request.getEntity();
        mList = entity.getDetailsList();
        if (mList.size() > 2) {
            opneorclose_btn.setVisibility(View.VISIBLE);
            hideItem();
        } else if (mList.size() <= 2) {
            opneorclose_btn.setVisibility(View.GONE);
            receAdapter.addAll(mList);
        } else {
            opneorclose_btn.setVisibility(View.GONE);
            receAdapter.addAll(mList);
        }
        if (entity.getOrderBook() != null) {
            WaliDealBean.OrderBookBean orderBookBean = entity.getOrderBook();
            user_name.setText(orderBookBean.getReceiver());
            if (orderBookBean.getMobile().length() >= 11) {
                String frontMobile = orderBookBean.getMobile().substring(0, 3);
                String frontStr = frontMobile + "****";
                String UserMobile = frontStr + orderBookBean.getMobile().substring(7);
                user_phone.setText(UserMobile);
            }
            user_address.setText(orderBookBean.getAddress());
            //发票类型
            if (entity.getOrderBook().getInvoiceType() == 1) {
                invtype_text.setText("电子发票");
            } else if (entity.getOrderBook().getInvoiceType() == 2) {
                invtype_text.setText("纸质发票");
            } else {
                invtype_text.setText("无发票");
            }
            //支付方式
            if (entity.getOrder().getPayType().equals("WEIXIN")) {
                fangshi.setText("微信");
            } else if (entity.getOrder().getPayType().equals("ALIPAY")) {
                fangshi.setText("支付宝");
            }
            danhao.setText(entity.getOrder().getOrderNo());
            time.setText(entity.getOrder().getCreateTime());
            good_sumprice.setText("¥ " + entity.getOrder().getSumPrice());
            good_yun.setText("¥ " + entity.getOrder().getFreightPrice());
            yingfukuan.setText("¥ " + entity.getOrder().getRealPrice());
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progress.setVisibility(View.GONE);
                    progress.stopAnimator();
                    nested.setVisibility(View.VISIBLE);
                }
            }, 500);
        }
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

    /**
     * 删除订单数据请求
     */
    public class DeleteOrder implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            if (request.isSuccess()) {
                mIntent.putExtra("delete", "delete");
                setResult(4, mIntent);
                finish();
            } else {
                ToastUtils.show("" + request.getMessage());
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    /**
     * 再次购买商品
     */
    private class AddShop implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            if (request.isSuccess()) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPromptDialog.dismissImmediately();
                        startActivity(new Intent(OrderReceActivity.this, PartiCarActivity.class));
                    }
                }, 1000);
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    /**
     * 确认收货
     */
    private class SignOrder implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            if (request.isSuccess()) {
                mButton.setVisibility(View.GONE);
                mPromptDialog.showSuccess(request.getMessage());
                receAdapter.notifyDataSetChanged();
                mIntent.putExtra("shouhuo", "shouhuo");
                setResult(5, mIntent);
            } else {
                mPromptDialog.showSuccess(request.getMessage());
            }
        }

        @Override
        public void fail(ApiException e) {
            mPromptDialog.dismiss();
        }
    }
}
