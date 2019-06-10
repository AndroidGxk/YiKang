package com.yikangcheng.admin.yikang.activity.siteactivity;

import android.app.Dialog;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.AddressRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.edittext_delete.ETextWithDelete;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.AddressBean;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.ProvinceBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.AddressPresenter;
import com.yikangcheng.admin.yikang.presenter.InsertAddressPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import me.jessyan.autosize.internal.CustomAdapt;

/**
 * 这是新增地址页面
 */
public class CompileActivity extends BaseActivtiy implements ICoreInfe, CustomAdapt {

    private CheckBox check_btn;
    private ImageView back_img;
    private ETextWithDelete name_edit;
    private ETextWithDelete phone_text;
    private TextView address_text, add_address;
    private ETextWithDelete relay_address;
    private Dialog mDialog;
    private int width;
    private int height;
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
    private InsertAddressPresenter insertAddressPresenter;
    private int proId;
    private int cityId;
    private int countryId = 0;

    @Override
    protected void initView() {
        /**
         *  设置状态栏颜色
         */
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);

        mInflate = LayoutInflater.from(CompileActivity.this).inflate(R.layout.site_compile, null);
        mTabLayout = mInflate.findViewById(R.id.tablayout);
        address_recycler = mInflate.findViewById(R.id.add_recycler);
        mTabLayout.addTab(mTabLayout.newTab().setText(defaultProvince));
        mTabLayout.addTab(mTabLayout.newTab().setText(defaultCity));
        mTabLayout.addTab(mTabLayout.newTab().setText(defaultDistrict));
        address_recycler.setLayoutManager(new LinearLayoutManager(this));
        addressRecyclerAdapter = new AddressRecyclerAdapter(this);
        address_recycler.setAdapter(addressRecyclerAdapter);
        name_edit = (ETextWithDelete) findViewById(R.id.name_edit);
        back_img = (ImageView) findViewById(R.id.back_img);
        check_btn = (CheckBox) findViewById(R.id.check_btn);
        phone_text = (ETextWithDelete) findViewById(R.id.phone_text);
        address_text = (TextView) findViewById(R.id.address_text);
        add_address = (TextView) findViewById(R.id.add_address);
        relay_address = (ETextWithDelete) findViewById(R.id.relay_address);
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        mDialog = new Dialog(this, R.style.BottomDialog);
        /**
         * 请求数据
         */
        addressPresenter = new AddressPresenter(this);
        insertAddressPresenter = new InsertAddressPresenter(new AddressIntface());
        /**
         * 点击返回按钮关闭当前页面
         */
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                        Toast.makeText(CompileActivity.this, "请先选择省份", Toast.LENGTH_SHORT).show();
                        mTabLayout.getTabAt(0).select();
                    }

                } else if (tab.getText().equals("区县")) {
                    if (mid == 0) {
                        Toast.makeText(CompileActivity.this, "请先选择省份", Toast.LENGTH_SHORT).show();
                        mTabLayout.getTabAt(0).select();
                        return;
                    }
                    if (cityid == 0) {
                        Toast.makeText(CompileActivity.this, "请先选择城市", Toast.LENGTH_SHORT).show();
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
                LoginBean logUser = getLogUser(CompileActivity.this);
                if (name.equals("") || phone.equals("") || xiangqing.equals("") || countryId == 0) {
                    Toast.makeText(CompileActivity.this, "请完善信息", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
                    if (phone_text.getText().toString().matches(REGEX_MOBILE_EXACT)) {
                        insertAddressPresenter.request(logUser.getId(), name, phone, xiangqing, isFirst, proId, cityId, countryId);
                    } else if (!phone_text.getText().toString().matches(REGEX_MOBILE_EXACT)) {
                        Toast.makeText(CompileActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
            }
        });
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_compile;
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
        return width / 2;
    }

    /**
     * 添加收货地址
     */
    private class AddressIntface implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            if (request.isSuccess()) {
                finish();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
