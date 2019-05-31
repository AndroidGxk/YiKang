package com.yikangcheng.admin.yikang.activity.siteactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.List;

import me.jessyan.autosize.internal.CustomAdapt;

public class AiteActivity extends BaseActivtiy implements ICoreInfe, CustomAdapt {
    private ImageView mImgActivityAiteFanhui, address_null, img_activity_aite_fanhui;
    private RelativeLayout mToolbarActivityAite;
    private RecyclerView mRlvActivityAite;
    private TextView new_address, add_address, text;
    private AllAddressPresenter allAddressPresenter;
    private AllAddressRecyclerAdapter allAddressRecyclerAdapter;
    private UpdateAddressPresenter updateAddressPresenter;
    private NestedScrollView nested;
    private DeleteAddressPresenter deleteAddressPresenter;
    private View view;
    private int height;
    private int width;

    @Override
    protected void initView() {
        allAddressPresenter = new AllAddressPresenter(this);
        allAddressRecyclerAdapter = new AllAddressRecyclerAdapter(this);
        mImgActivityAiteFanhui = (ImageView) findViewById(R.id.img_activity_aite_fanhui);
        mToolbarActivityAite = (RelativeLayout) findViewById(R.id.toolbar_activity_aite);
        mRlvActivityAite = (RecyclerView) findViewById(R.id.rlv_activity_aite);
        address_null = (ImageView) findViewById(R.id.address_null);
        new_address = (TextView) findViewById(R.id.new_address);
        add_address = (TextView) findViewById(R.id.add_address);
        img_activity_aite_fanhui = (ImageView) findViewById(R.id.img_activity_aite_fanhui);
        nested = (NestedScrollView) findViewById(R.id.nested);
        text = (TextView) findViewById(R.id.text);
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        //删除弹框
        view = View.inflate(this, R.layout.delete_popup_item, null);

        updateAddressPresenter = new UpdateAddressPresenter(new UpdateAddress());
        deleteAddressPresenter = new DeleteAddressPresenter(new UpdateAddress());
        mRlvActivityAite.setLayoutManager(new LinearLayoutManager(this));
        mRlvActivityAite.setAdapter(allAddressRecyclerAdapter);
        //设置默认地址
        allAddressRecyclerAdapter.setOnClickListener(new AllAddressRecyclerAdapter.onClickListener() {
            @Override
            public void onClick(AllAddressBean.ListUserAddressBean listUserAddressBean) {
                Log.d("address-------", listUserAddressBean.toString());
                updateAddressPresenter.request(listUserAddressBean.getId(), getLogUser(AiteActivity.this).getId(),
                        listUserAddressBean.getReceiver(), listUserAddressBean.getMobile(), listUserAddressBean.getAddress(), 1,
                        listUserAddressBean.getProvinceId(), listUserAddressBean.getCityId(), listUserAddressBean.getTownId());
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
            public void onClick(int id, int position) {
//                deleteAddressPresenter.request(id);
//                allAddressRecyclerAdapter.deletePosition(position);
            }
        });
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

    @Override
    protected void initEventData() {

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
    }

    @Override
    public void fail(ApiException e) {

    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 720;
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoginBean logUser = getLogUser(this);
        allAddressPresenter.request(logUser.getId());
    }

    /**
     * 修改、删除用户地址
     */
    private class UpdateAddress implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            Toast.makeText(AiteActivity.this, "" + request.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException e) {

        }
    }

}
