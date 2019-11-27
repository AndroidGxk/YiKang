package com.yikangcheng.admin.yikang.activity.giftactivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.hjq.toast.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.model.Information;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.giftactivity.adapter.MyGiftOneAdapter;
import com.yikangcheng.admin.yikang.activity.giftactivity.adapter.MyGiftYiWanChengAdapter;
import com.yikangcheng.admin.yikang.activity.siteactivity.AiteActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.AllAddressBean;
import com.yikangcheng.admin.yikang.bean.AllWelfareBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.WelfareCourseBean;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.AllWelfareCourseListPresenter;
import com.yikangcheng.admin.yikang.presenter.CreateFuLiOrderPresenter;
import com.yikangcheng.admin.yikang.presenter.WelfareCourseListPresenter;

import java.util.List;

import butterknife.BindView;

public class MyGiftActivity extends BaseActivtiy implements ICoreInfe {


    private AllAddressBean.ListUserAddressBean userAddressBean;
    private WelfareCourseListPresenter welfareCoursePresenter;
    private AllWelfareCourseListPresenter allWelfareCourseListPresenter;
    private MyGiftYiWanChengAdapter myGiftYiWanChengAdapter;
    private CreateFuLiOrderPresenter createFuLiOrderPresenter;
    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.recy2)
    RecyclerView recy2;
    private MyGiftOneAdapter myGiftOneAdapter;
    private LinearLayoutManager linearLayoutManager;
    private View dialogview;
    private Dialog dialog;
    @BindView(R.id.back_img)
    ImageView back_img;
    @BindView(R.id.kefu_img)
    ImageView kefu_img;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int mPosition;
    /**
     * 回传值
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == 1001) {
            Bundle bundle = data.getExtras();
            userAddressBean = (AllAddressBean.ListUserAddressBean) bundle.getSerializable("result");
            if (userAddressBean != null) {
                myGiftOneAdapter.addAddress(userAddressBean, mPosition);
            }
        }
    }
    @Override
    protected void initView() {
        recy.setHasFixedSize(true);
        recy.setNestedScrollingEnabled(false);
        recy2.setHasFixedSize(true);
        recy2.setNestedScrollingEnabled(false);
        linearLayoutManager = new LinearLayoutManager(this);
        myGiftOneAdapter = new MyGiftOneAdapter(this);
        myGiftYiWanChengAdapter = new MyGiftYiWanChengAdapter(this);
        //未领取礼品
        welfareCoursePresenter = new WelfareCourseListPresenter(this);
        welfareCoursePresenter.request(getLogUser(this).getId(), 2);
        /**
         * 已领取
         */
        allWelfareCourseListPresenter = new AllWelfareCourseListPresenter(new AllWelfare());
        allWelfareCourseListPresenter.request(getLogUser(this).getId());
        /**
         * 领取礼品
         */
        createFuLiOrderPresenter = new CreateFuLiOrderPresenter(new CreateFuLi());
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                allWelfareCourseListPresenter.request(getLogUser(MyGiftActivity.this).getId());
                welfareCoursePresenter.request(getLogUser(MyGiftActivity.this).getId(), 2);
                refreshLayout.finishRefresh();
            }
        });
    }

    @Override
    protected void initEventData() {
        recy.setLayoutManager(linearLayoutManager);
        recy.setAdapter(myGiftOneAdapter);
        recy2.setLayoutManager(new LinearLayoutManager(this));
        recy2.setAdapter(myGiftYiWanChengAdapter);
        /**
         *显示领取弹框
         */
        myGiftOneAdapter.setOnClickListener(new MyGiftOneAdapter.onClickListener() {
            @Override
            public void onClick(int id, int position, String welid) {
                mPosition = position;
                createFuLiOrderPresenter.request(welid, id, getLogUser(MyGiftActivity.this).getId());
            }
        });

        /**
         * 选择地址
         */
        myGiftOneAdapter.setOnClickAddressListener(new MyGiftOneAdapter.onClickAddressListener() {
            @Override
            public void onClick(int position) {
                mPosition = position;
                Intent addIntent = new Intent(MyGiftActivity.this, AiteActivity.class);
                addIntent.putExtra("address", "true");
                startActivityForResult(addIntent, 1000);
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
        /**
         * 客服
         */
        kefu_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Information info = new Information();
                info.setAppkey("7560599b63bf43378d05d018ded42cdd");
                SobotApi.setCustomRobotHelloWord(MyGiftActivity.this, "您好，易康成客服很高兴为您服务，请问有什么可以帮助您的？");
                SobotApi.startSobotChat(MyGiftActivity.this, info);
            }
        });
    }

    private void showDialog() {
        //dialog展示优惠券
        dialogview = getLayoutInflater().inflate(R.layout.lipin_lingqu, null);
        ImageView dismissImg = dialogview.findViewById(R.id.dismissImg);
        dialog = new Dialog(MyGiftActivity.this, R.style.dialogWindowAnim);
        dialog.setContentView(dialogview, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
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
        dismissImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                refreshLayout.autoRefresh();
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_my_gift;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        List<WelfareCourseBean> welfareCourseBeans = (List<WelfareCourseBean>) request.getEntity();
        myGiftOneAdapter.removieAll();
        myGiftOneAdapter.addAll(welfareCourseBeans);
    }

    @Override
    public void fail(ApiException e) {

    }

    /**
     * 已领取
     */
    private class AllWelfare implements ICoreInfe {

        @Override
        public void success(Object data) {
            Request request = (Request) data;
            List<AllWelfareBean> allWelfareBean = (List<AllWelfareBean>) request.getEntity();
            myGiftYiWanChengAdapter.removeAll();
            myGiftYiWanChengAdapter.addAll(allWelfareBean);
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    /**
     * 领取礼品
     */
    private class CreateFuLi implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            if (request.isSuccess()) {
                showDialog();

            } else {
                ToastUtils.show(request.getMessage());
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
