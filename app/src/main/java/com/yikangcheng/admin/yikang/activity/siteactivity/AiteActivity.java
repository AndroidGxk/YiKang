package com.yikangcheng.admin.yikang.activity.siteactivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.AllAddressRecyclerAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.AllAddressBean;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.AllAddressPresenter;
import com.yikangcheng.admin.yikang.presenter.DeleteAddressPresenter;
import com.yikangcheng.admin.yikang.presenter.UpdateAddressPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;
import com.yikangcheng.admin.yikang.util.TwoBallRotationProgressBar;

import java.util.List;

import me.jessyan.autosize.internal.CustomAdapt;
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

public class AiteActivity extends BaseActivtiy implements ICoreInfe  {
    private ImageView mImgActivityAiteFanhui, address_null, img_activity_aite_fanhui;
    private RelativeLayout mToolbarActivityAite;
    private RecyclerView mRlvActivityAite;
    private TextView new_address, add_address, text;
    private AllAddressPresenter allAddressPresenter;
    private AllAddressRecyclerAdapter allAddressRecyclerAdapter;
    private UpdateAddressPresenter updateAddressPresenter;
    private NestedScrollView nested;
    private DeleteAddressPresenter deleteAddressPresenter;
    private int width;
    private String address = "";
    private PromptDialog promptDialog;
    private int mPosition;
    private int mId;
    private TwoBallRotationProgressBar progress;

    @Override
    protected void initView() {
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        //创建对象
        promptDialog = new PromptDialog(this);
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(1000);
        Intent intent = getIntent();
        address = intent.getStringExtra("address");
        allAddressPresenter = new AllAddressPresenter(this);
        allAddressRecyclerAdapter = new AllAddressRecyclerAdapter(this);
        mImgActivityAiteFanhui = (ImageView) findViewById(R.id.img_activity_aite_fanhui);
        mToolbarActivityAite = (RelativeLayout) findViewById(R.id.toolbar_activity_aite);
        mRlvActivityAite = (RecyclerView) findViewById(R.id.rlv_activity_aite);
        address_null = (ImageView) findViewById(R.id.address_null);
        new_address = (TextView) findViewById(R.id.new_address);
        add_address = (TextView) findViewById(R.id.add_address);
        progress = (TwoBallRotationProgressBar) findViewById(R.id.progress);
        img_activity_aite_fanhui = (ImageView) findViewById(R.id.img_activity_aite_fanhui);
        nested = (NestedScrollView) findViewById(R.id.nested);
        text = (TextView) findViewById(R.id.text);
        updateAddressPresenter = new UpdateAddressPresenter(new UpdateAddress());
        deleteAddressPresenter = new DeleteAddressPresenter(new UpdateAddress());
        mRlvActivityAite.setLayoutManager(new LinearLayoutManager(this));
        mRlvActivityAite.setAdapter(allAddressRecyclerAdapter);
        //设置默认地址
        allAddressRecyclerAdapter.setOnClickListener(new AllAddressRecyclerAdapter.onClickListener() {
            @Override
            public void onClick(AllAddressBean.ListUserAddressBean listUserAddressBean) {
                updateAddressPresenter.request(listUserAddressBean.getId(), getLogUser(AiteActivity.this).getId(),
                        listUserAddressBean.getReceiver(), listUserAddressBean.getMobile(), listUserAddressBean.getAddress(), 1,
                        listUserAddressBean.getProvinceId(), listUserAddressBean.getCityId(), listUserAddressBean.getTownId());
                promptDialog.showLoading("加载中...");
            }
        });
        allAddressRecyclerAdapter.setSeleAddressOnClick(new AllAddressRecyclerAdapter.SeleAddressOnClick() {
            @Override
            public void onClick(AllAddressBean.ListUserAddressBean userAddressBean) {
                if (address != null && address.equals("true")) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("result", userAddressBean);
                    intent.putExtras(bundle);
                    /*
                     * 调用setResult方法表示我将Intent对象返回给之前的那个Activity，这样就可以在onActivityResult方法中得到Intent对象，
                     */
                    setResult(1001, intent);
                    //    结束当前这个Activity对象的生命
                    finish();
                }
            }
        });
        /**
         * 跳转编辑页面
         */
        allAddressRecyclerAdapter.setCompOnClickListener(new AllAddressRecyclerAdapter.compOnClickListener() {
            @Override
            public void onClick(AllAddressBean.ListUserAddressBean listUserAddressBean) {
                Intent intent = new Intent(AiteActivity.this, UserCompileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("listUserAddressBean", listUserAddressBean);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        /**
         * 删除用户地址
         */
        allAddressRecyclerAdapter.setDeleteOnClickListener(new AllAddressRecyclerAdapter.deleteOnClickListener() {
            @Override
            public void onClick(final int id, final int position) {
                mId = id;
                mPosition = position;
                promptDialog.showWarnAlert("确定要删除吗？", confirm, Noconfirm);
            }
        });
        confirm.setTextColor(Color.parseColor("#FFAF00"));
        Noconfirm.setTextColor(Color.parseColor("#666666"));
        /**
         *  设置状态栏颜色
         */
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        /**
         * 点击返回按钮关闭当前页面
         */
        mImgActivityAiteFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        /**
         * 点击新增收货地址按钮跳转页面
         */
        new_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AiteActivity.this, CompileActivity.class);
                startActivity(intent);
            }
        });

        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AiteActivity.this, CompileActivity.class);
                startActivity(intent);
            }
        });
        //解决滑动不流畅
        mRlvActivityAite.setHasFixedSize(true);
        mRlvActivityAite.setNestedScrollingEnabled(false);
        img_activity_aite_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //取消按钮的定义，创建一个按钮的对象
    final PromptButton Noconfirm = new PromptButton("取消", new PromptButtonListener() {
        @Override
        public void onClick(PromptButton button) {

        }
    });
    //确定按钮的定义，创建一个按钮的对象
    final PromptButton confirm = new PromptButton("删除", new PromptButtonListener() {
        @Override
        public void onClick(PromptButton button) {
            deleteAddressPresenter.request(mId);
            allAddressRecyclerAdapter.deletePosition(mPosition);
            int size = allAddressRecyclerAdapter.getSize();
            if (size == 0) {
                nested.setVisibility(View.GONE);
                address_null.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);
                add_address.setVisibility(View.VISIBLE);
            }
        }
    });


    @Override
    protected void initEventData() {
        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_aite;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        AllAddressBean entity = (AllAddressBean) request.getEntity();
        List<AllAddressBean.ListUserAddressBean> listUserAddress = entity.getListUserAddress();
        //显示隐藏
        if (listUserAddress.size() == 0 || listUserAddress == null) {
            Glide.with(this).load(R.drawable.dongtu).into(address_null);
            nested.setVisibility(View.GONE);
            address_null.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);
            add_address.setVisibility(View.VISIBLE);
        } else {
            nested.setVisibility(View.VISIBLE);
            address_null.setVisibility(View.GONE);
            text.setVisibility(View.GONE);
            add_address.setVisibility(View.GONE);
        }
        allAddressRecyclerAdapter.removeAll();
        allAddressRecyclerAdapter.addAll(listUserAddress);
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

    }

    @Override
    protected void onResume() {
        super.onResume();
        LoginBean logUser = getLogUser(this);
        allAddressPresenter.request(logUser.getId());
    }

    /**
     * 修改用户地址
     */
    private class UpdateAddress implements ICoreInfe {
        @Override
        public void success(Object data) {
            promptDialog.dismiss();
        }

        @Override
        public void fail(ApiException e) {
            promptDialog.dismiss();
        }
    }

}
