package com.yikangcheng.admin.yikang.activity.orderstatus;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.FackOfAdapter_A;
import com.yikangcheng.admin.yikang.activity.copy.CopyButtonLibrary;
import com.yikangcheng.admin.yikang.activity.particulars.ParticularsActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.CloseTheDealBean;
import com.yikangcheng.admin.yikang.bean.DeleteOrderBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.WaliDealBean;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.CloseTheDeallPresenter;
import com.yikangcheng.admin.yikang.presenter.DeleteOrderIdPresenter;
import com.yikangcheng.admin.yikang.util.SpacesItemDecoration;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;
import com.yikangcheng.admin.yikang.util.TwoBallRotationProgressBar;

import java.util.ArrayList;

import me.jessyan.autosize.internal.CustomAdapt;
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

public class FackOfActivity extends BaseActivtiy implements ICoreInfe, CustomAdapt {
    private ImageView mImgActivityFackOfFanhui;
    private RelativeLayout mToolbarActivityFackOf;
    private TextView mTvActivityFackOfName;
    private TextView mTvActivityFackOfProvinceStr;
    private RecyclerView mRlvActivityFackOfShangPin;
    private TextView mTvActivityFackOfBiaohao;
    private ImageView mImgActivityFackOfFzhi;
    private TextView mTvActivityFackOfYunFei;
    private TextView mTvActivityFackOfJinE;
    private TextView mTvActivityFackOfZhongJi;
    private TextView mTvActivityFackOfFangShi;
    private RelativeLayout mTvActivityFackOfKeFu;
    private TextView mTvActivityFackOfFaPianLeiXing;
    private TextView mTvActivityFackOfNeiRong;
    private TextView mTvActivityFackOfShanchu;
    private FackOfAdapter_A mFackOfAdapter_a;
    //    private int mPosition;
//    private int mOrderId;
    private Intent mIntent;
    private int mOrderId_fack;
    private int width;
    private PromptDialog mPromptDialog;
    private TwoBallRotationProgressBar progress;
    private RelativeLayout rela1, rela2;

    @Override
    protected void initView() {
        mIntent = getIntent();
        mOrderId_fack = mIntent.getIntExtra("orderId_fack", 0);
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        //创建对象
        mPromptDialog = new PromptDialog(this);
        //设置自定义属性
        mPromptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);
        mImgActivityFackOfFanhui = (ImageView) findViewById(R.id.img_activity_fack_of_fanhui);
        mToolbarActivityFackOf = (RelativeLayout) findViewById(R.id.toolbar_activity_fack_of);
        rela1 = (RelativeLayout) findViewById(R.id.rela1);
        rela2 = (RelativeLayout) findViewById(R.id.rela2);
        mTvActivityFackOfName = (TextView) findViewById(R.id.tv_activity_fack_of_name);
        mTvActivityFackOfProvinceStr = (TextView) findViewById(R.id.tv_activity_fack_of_provinceStr);

        mRlvActivityFackOfShangPin = (RecyclerView) findViewById(R.id.rlv_activity_fack_of_shangPin);
        mTvActivityFackOfBiaohao = (TextView) findViewById(R.id.tv_activity_fack_of_biaohao);
        mImgActivityFackOfFzhi = (ImageView) findViewById(R.id.img_activity_fack_of_fzhi);
        mTvActivityFackOfYunFei = (TextView) findViewById(R.id.tv_activity_fack_of_yunFei);
        mTvActivityFackOfJinE = (TextView) findViewById(R.id.tv_activity_fack_of_jinE);
        mTvActivityFackOfZhongJi = (TextView) findViewById(R.id.tv_activity_fack_of_zhongJi);
        mTvActivityFackOfFangShi = (TextView) findViewById(R.id.tv_activity_fack_of_fangShi);
        mTvActivityFackOfKeFu = (RelativeLayout) findViewById(R.id.tv_activity_fack_of_keFu);
        mTvActivityFackOfFaPianLeiXing = (TextView) findViewById(R.id.tv_activity_fack_of_FaPianLeiXing);
        mTvActivityFackOfNeiRong = (TextView) findViewById(R.id.tv_activity_fack_of_NeiRong);
        mTvActivityFackOfShanchu = (TextView) findViewById(R.id.tv_activity_fack_of_shanchu);
        //进度条
        progress = (TwoBallRotationProgressBar) findViewById(R.id.progress);


        //点击客服跳转页面
        mTvActivityFackOfKeFu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Information info = new Information();
                info.setAppkey("7560599b63bf43378d05d018ded42cdd");
                SobotApi.setCustomRobotHelloWord(FackOfActivity.this, "您好，易康成客服很高兴为您服务，请问有什么可以帮助您的？");
                SobotApi.startSobotChat(FackOfActivity.this, info);
            }
        });

        //复制监听点击事件
        mImgActivityFackOfFzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //传入需要复制的文字的控件
                CopyButtonLibrary copyButtonLibrary = new CopyButtonLibrary(getApplicationContext(), mTvActivityFackOfBiaohao);
                copyButtonLibrary.init();
            }
        });


        CloseTheDeallPresenter closeTheDeallPresenter = new CloseTheDeallPresenter(this);
        closeTheDeallPresenter.request(mOrderId_fack);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRlvActivityFackOfShangPin.setLayoutManager(linearLayoutManager);
        ArrayList<WaliDealBean.DetailsListBean> mShopSpecDetailedBeans = new ArrayList<>();
        mFackOfAdapter_a = new FackOfAdapter_A(mShopSpecDetailedBeans, this);
        mRlvActivityFackOfShangPin.setAdapter(mFackOfAdapter_a);


        //解决滑动不流畅
        mRlvActivityFackOfShangPin.setHasFixedSize(true);
        mRlvActivityFackOfShangPin.setNestedScrollingEnabled(false);


        //详情
        mFackOfAdapter_a.setToGoodPritaul(new FackOfAdapter_A.toGoodPritaul() {
            @Override
            public void onclick(int id) {
                Intent intent = new Intent(FackOfActivity.this,ParticularsActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        int spanCount_tuijian = 1; // 3 columns
        int spacing_tuijian = 5; // 50px
        boolean includeEdge_tuijian = false;
        mRlvActivityFackOfShangPin.addItemDecoration(new SpacesItemDecoration(spanCount_tuijian, spacing_tuijian, includeEdge_tuijian));
        //刪除訂單
        initDelete();
    }

    //點擊按鈕刪除訂單
    private void initDelete() {
        mTvActivityFackOfShanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            DeleteOrderIdPresenter deleteOrderIdPresenter = new DeleteOrderIdPresenter(new delete());
            deleteOrderIdPresenter.request(mOrderId_fack);
            mIntent.putExtra("delete", "delete");
            setResult(6, mIntent);
            finish();
        }
    });

    @Override
    protected void initEventData() {
        /**
         * 点击返回按钮关闭当前页面
         */
        mImgActivityFackOfFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FackOfActivity.this.finish();
            }
        });

        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_fack_of;
    }

    @Override
    protected void createPresenter() {

    }

    public class delete implements ICoreInfe {

        public DeleteOrderBean mMEntity;

        @Override
        public void success(Object data) {
            Request request = (Request) data;
            mMEntity = (DeleteOrderBean) request.getEntity();
            //有需要在这打印一下message的返回值现在返回的是空的  让后台看一下  做一个判断
            // Log.e("aaa", "success: "+mMEntity.get );
//            mFackOfAdapter_a.mList.remove(mPosition);
//            mFackOfAdapter_a.notifyDataSetChanged();
        }

        @Override
        public void fail(ApiException e) {

        }
    }


    @Override
    public void success(Object data) {
        Request request = (Request) data;
        final WaliDealBean entity = (WaliDealBean) request.getEntity();
        mFackOfAdapter_a.addAll(entity.getDetailsList());
        //用户名
        String receiver = entity.getOrderBook().getReceiver();
        if (receiver == null || receiver.equals("")) {
            mTvActivityFackOfName.setText("匿名");
        } else {
            mTvActivityFackOfName.setText(receiver);
        }
        //地址
        mTvActivityFackOfProvinceStr.setText(entity.getOrderBook().getAddress());
        //订单编号
        mTvActivityFackOfBiaohao.setText(entity.getOrder().getOrderNo());
        //运费
        mTvActivityFackOfYunFei.setText(entity.getOrder().getFreightPrice() + "");
        //金额
        mTvActivityFackOfJinE.setText(entity.getOrder().getRealPrice() + "");
        //总额
        mTvActivityFackOfZhongJi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double sumPrice = entity.getOrder().getSumPrice();
                Toast.makeText(FackOfActivity.this, sumPrice + "", Toast.LENGTH_SHORT).show();
            }
        });
        mTvActivityFackOfZhongJi.setText(entity.getOrder().getSumPrice() + "");
        //支付方式
        if (entity.getOrder().getPayType().equals("WEIXIN")) {
            mTvActivityFackOfFangShi.setText("微信");
        } else if (entity.getOrder().getPayType().equals("ALIPAY")) {
            mTvActivityFackOfFangShi.setText("支付宝");
        }

        //发票类型
        if (entity.getOrderBook().getInvoiceType() == 1) {
            mTvActivityFackOfFaPianLeiXing.setText("电子发票");
        } else if (entity.getOrderBook().getInvoiceType() == 2) {
            mTvActivityFackOfFaPianLeiXing.setText("纸质发票");
        } else {
            mTvActivityFackOfFaPianLeiXing.setText("无发票");
        }


        //发票内容
        if (entity.getOrderBook().getInvoiceContent() == 1) {
            mTvActivityFackOfNeiRong.setText("商品明细");
        } else {
            mTvActivityFackOfNeiRong.setText("商品类别");
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.GONE);
                progress.stopAnimator();
                rela1.setVisibility(View.VISIBLE);
                rela2.setVisibility(View.VISIBLE);
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

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return width / 2;
    }
}
