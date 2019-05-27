package com.yikangcheng.admin.yikang.activity.siteactivity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import com.yikangcheng.admin.yikang.presenter.UpdateAddressPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.List;

import me.jessyan.autosize.internal.CustomAdapt;
import retrofit2.http.Query;

public class AiteActivity extends BaseActivtiy implements ICoreInfe, CustomAdapt {


    private ImageView mImgActivityAiteFanhui,address_null;
    private Toolbar mToolbarActivityAite;
    private RecyclerView mRlvActivityAite;
    private TextView new_address;
    private AllAddressPresenter allAddressPresenter;
    private AllAddressRecyclerAdapter allAddressRecyclerAdapter;
    private UpdateAddressPresenter updateAddressPresenter;

    @Override
    protected void initView() {
        /**
         * 这是收货地址页面  item布局已经写好  下面就是
         */
        allAddressPresenter = new AllAddressPresenter(this);
        allAddressRecyclerAdapter = new AllAddressRecyclerAdapter(this);
        mImgActivityAiteFanhui = findViewById(R.id.img_activity_aite_fanhui);
        mToolbarActivityAite = findViewById(R.id.toolbar_activity_aite);
        mRlvActivityAite = findViewById(R.id.rlv_activity_aite);
        address_null = findViewById(R.id.address_null);
        new_address = findViewById(R.id.new_address);
        updateAddressPresenter = new UpdateAddressPresenter(new UpdateAddress());
        mRlvActivityAite.setLayoutManager(new LinearLayoutManager(this));
        mRlvActivityAite.setAdapter(allAddressRecyclerAdapter);
        allAddressRecyclerAdapter.setOnClickListener(new AllAddressRecyclerAdapter.onClickListener() {
            @Override
            public void onClick(AllAddressBean.ListUserAddressBean listUserAddressBean) {
                Log.d("address-------",listUserAddressBean.toString());
                updateAddressPresenter.request(listUserAddressBean.getId(), getLogUser(AiteActivity.this).getId(),
                        listUserAddressBean.getReceiver(), listUserAddressBean.getMobile(), listUserAddressBean.getAddress(), 1,
                        listUserAddressBean.getProvinceId(), listUserAddressBean.getCityId(), listUserAddressBean.getTownId());
            }
        });
        /**
         *  设置状态栏颜色
         */
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        /**
         * ToolBar
         */
        mToolbarActivityAite.setTitle("");
        setSupportActionBar(mToolbarActivityAite);
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
        //解决滑动不流畅
        mRlvActivityAite.setHasFixedSize(true);
        mRlvActivityAite.setNestedScrollingEnabled(false);

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
        listUserAddress.clear();
        if(listUserAddress.size()==0||listUserAddress==null){
            Glide.with(this).load(R.drawable.dongtu).into(address_null);
            new_address.setText("去添加收货地址");
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
     * 修改用户地址
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
