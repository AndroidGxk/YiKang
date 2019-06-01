package com.yikangcheng.admin.yikang.activity.siteactivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.AddressRecyclerAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.AddressBean;
import com.yikangcheng.admin.yikang.bean.AllAddressBean;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.ProvinceBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.AddressPresenter;
import com.yikangcheng.admin.yikang.presenter.InsertAddressPresenter;
import com.yikangcheng.admin.yikang.presenter.UpdateAddressPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import me.jessyan.autosize.internal.CustomAdapt;

/**
 * 这是编辑地址页面
 */
public class UserCompileActivity extends BaseActivtiy implements ICoreInfe, CustomAdapt {
    private CheckBox check_btn;
    private ImageView back_img;
    private EditText name_edit, phone_text, relay_address;
    private TextView address_text, add_address;
    //选择地址
    private View mInflate;
    private RecyclerView address_recycler;
    private TabLayout mTabLayout;
    private String defaultProvince = "省份"; //显示在上面tab中的省份
    private String defaultCity = "城市"; //显示在上面tab中的城市
    private String defaultDistrict = "区县"; //显示在上面tab中的区县
    private int address;
    private AddressRecyclerAdapter addressRecyclerAdapter;
    private AddressPresenter addressPresenter;
    //记录省市ID
    private int mid;
    //记录城市ID
    private int cityid;
    //记录地址
    List<AddressBean> addressBeans = new ArrayList<>();
    private Dialog mDialog;
    private int width;
    private int height;
    private int proId;
    private int cityId;
    private int countryId;
    private UpdateAddressPresenter updateAddressPresenter;
    private AllAddressBean.ListUserAddressBean listUserAddressBean;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        listUserAddressBean = (AllAddressBean.ListUserAddressBean) bundle.get("listUserAddressBean");
        name_edit = (EditText) findViewById(R.id.name_edit);
        phone_text = (EditText) findViewById(R.id.phone_text);
        relay_address = (EditText) findViewById(R.id.relay_address);
        address_text = (TextView) findViewById(R.id.address_text);
        back_img = (ImageView) findViewById(R.id.back_img);
        add_address = (TextView) findViewById(R.id.add_address);
        check_btn = (CheckBox) findViewById(R.id.check_btn);
        name_edit.setText(listUserAddressBean.getReceiver());
        phone_text.setText(listUserAddressBean.getMobile());
        relay_address.setText(listUserAddressBean.getAddress());
        address_text.setText(listUserAddressBean.getCityStr() + listUserAddressBean.getTownStr());
        proId = listUserAddressBean.getProvinceId();
        cityId = listUserAddressBean.getCityId();
        countryId = listUserAddressBean.getTownId();
        if (listUserAddressBean.getIsFirst() == 1) {
            check_btn.setChecked(true);
        } else {
            check_btn.setChecked(false);
        }
        updateAddressPresenter = new UpdateAddressPresenter(new UpdateAddress());
        /**
         * 退出
         */
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        /**
         *  设置状态栏颜色
         */
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        mInflate = LayoutInflater.from(UserCompileActivity.this).inflate(R.layout.site_compile, null);
        mTabLayout = mInflate.findViewById(R.id.tablayout);
        address_recycler = mInflate.findViewById(R.id.add_recycler);
        mTabLayout.addTab(mTabLayout.newTab().setText(defaultProvince));
        mTabLayout.addTab(mTabLayout.newTab().setText(defaultCity));
        mTabLayout.addTab(mTabLayout.newTab().setText(defaultDistrict));
        address_recycler.setLayoutManager(new LinearLayoutManager(this));
        addressRecyclerAdapter = new AddressRecyclerAdapter(this);
        address_recycler.setAdapter(addressRecyclerAdapter);
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        mDialog = new Dialog(this, R.style.BottomDialog);
        /**
         * 请求数据
         */
        addressPresenter = new AddressPresenter(this);
    }

    @Override
    protected void initEventData() {
        /**
         * 点击事件
         */
        addressRecyclerAdapter.setOnClickListener(new AddressRecyclerAdapter.onClickListener() {
            @Override
            public void onclick(int id, AddressBean addressBean) {
                addressBeans.add(addressBean);
                address++;
                if (address == 2) {
                    cityid = id;
                    addressPresenter.request(id);
                    mTabLayout.getTabAt(2).select();
                } else if (address == 1) {
                    mid = id;
                    mTabLayout.getTabAt(1).select();
                    addressPresenter.request(id);
                } else {
                    String address = addressBeans.get(0).getAddress();
                    proId = addressBeans.get(0).getId();
                    cityId = addressBeans.get(1).getId();
                    countryId = addressBeans.get(2).getId();
                    String city = addressBeans.get(1).getAddress();
                    String country = addressBeans.get(2).getAddress();
                    address_text.setText(city + country);
                    addressBeans.clear();
                    mDialog.dismiss();
                }
            }
        });
        address_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addressPresenter.request(1);
                mTabLayout.getTabAt(0).select();
                //设置dialog的宽高为屏幕的宽高
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, height - (int) (height * 0.4));
                mDialog.setContentView(mInflate, layoutParams);
                mDialog.getWindow().setGravity(Gravity.BOTTOM);
                mDialog.setCanceledOnTouchOutside(true);
                mDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
                mDialog.show();
            }
        });
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().equals("省份")) {
                    addressPresenter.request(1);
                    mid = 0;
                    address = 0;
                } else if (tab.getText().equals("城市")) {
                    if (mid != 0) {
                        cityid = 0;
                        addressPresenter.request(mid);
                        address = 1;
                    } else {
                        Toast.makeText(UserCompileActivity.this, "请先选择省份", Toast.LENGTH_SHORT).show();
                        mTabLayout.getTabAt(0).select();
                    }

                } else if (tab.getText().equals("区县")) {
                    if (mid == 0) {
                        Toast.makeText(UserCompileActivity.this, "请先选择省份", Toast.LENGTH_SHORT).show();
                        mTabLayout.getTabAt(0).select();
                        return;
                    }
                    if (cityid == 0) {
                        Toast.makeText(UserCompileActivity.this, "请先选择城市", Toast.LENGTH_SHORT).show();
                        mTabLayout.getTabAt(1).select();
                        return;
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int isFirst;
                String name = name_edit.getText().toString();
                String phone = phone_text.getText().toString();
                String address = address_text.getText().toString();
                String xiangqing = relay_address.getText().toString();
                boolean checked = check_btn.isChecked();
                if (checked) {
                    isFirst = 1;
                } else {
                    isFirst = 0;
                }
                updateAddressPresenter.request(listUserAddressBean.getId(), getLogUser(UserCompileActivity.this).getId(),
                        name, phone, xiangqing, isFirst,
                        proId, cityId, countryId);
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_user_compile;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        List<ProvinceBean> entity = (List<ProvinceBean>) request.getEntity();
        addressRecyclerAdapter.removeAll();
        addressRecyclerAdapter.addAll(entity);
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

    /**
     * 修改用户地址
     */
    private class UpdateAddress implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            Toast.makeText(UserCompileActivity.this, "" + request.getMessage(), Toast.LENGTH_SHORT).show();
            if (request.isSuccess()) {
                finish();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
