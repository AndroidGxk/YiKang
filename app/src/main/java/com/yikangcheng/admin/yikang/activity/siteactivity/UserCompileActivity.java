package com.yikangcheng.admin.yikang.activity.siteactivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.AddressRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.edittext_delete.ETextWithDelete;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.AddressBean;
import com.yikangcheng.admin.yikang.bean.AllAddressBean;
import com.yikangcheng.admin.yikang.bean.ProvinceBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.AddressPresenter;
import com.yikangcheng.admin.yikang.presenter.UpdateAddressPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 这是编辑地址页面
 */
public class UserCompileActivity extends BaseActivtiy implements ICoreInfe {
    private CheckBox check_btn;
    private ImageView back_img;
    private ETextWithDelete name_edit;
    private ETextWithDelete phone_text, relay_address;
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
    private RelativeLayout rela;
    @BindView(R.id.title)
    TextView title;
    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        listUserAddressBean = (AllAddressBean.ListUserAddressBean) bundle.get("listUserAddressBean");
        name_edit = (ETextWithDelete) findViewById(R.id.name_edit);
        phone_text = (ETextWithDelete) findViewById(R.id.phone_text);
        relay_address = (ETextWithDelete) findViewById(R.id.relay_address);
        address_text = (TextView) findViewById(R.id.address_text);
        back_img = (ImageView) findViewById(R.id.back_img);
        add_address = (TextView) findViewById(R.id.add_address);
        GradientDrawable myGrad = (GradientDrawable) add_address.getBackground();
        myGrad.setColor(Color.parseColor(getLogUser(this).getThemeColors()));

        check_btn = (CheckBox) findViewById(R.id.check_btn);
        rela = (RelativeLayout) findViewById(R.id.rela);
        rela.setBackgroundColor(Color.parseColor(getLogUser(this).getThemeColors()));
        //标题字体颜色
//        title.setTextColor(Color.parseColor());
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
        //设置状态栏颜色
        if (!getLogUser(this).getThemeColors().equals("")) {
            StatusBarUtil.setStatusBarMode(this, true, Color.parseColor(getLogUser(this).getThemeColors()));
        } else {
            StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        }
        mInflate = LayoutInflater.from(UserCompileActivity.this).inflate(R.layout.site_compile, null);
        mTabLayout = mInflate.findViewById(R.id.tablayout);
        address_recycler = mInflate.findViewById(R.id.add_recycler);
        mTabLayout.addTab(mTabLayout.newTab().setText(defaultProvince));
        mTabLayout.addTab(mTabLayout.newTab().setText(defaultCity));
        mTabLayout.addTab(mTabLayout.newTab().setText(defaultDistrict));
        changeTextColor(mTabLayout);
        address_recycler.setLayoutManager(new LinearLayoutManager(this));
        addressRecyclerAdapter = new AddressRecyclerAdapter(this, getLogUser(this).getThemeColors());
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
    private void changeTextColor(TabLayout tabLayout) {
        try {
            //拿到tabLayout的mTabStrip属性
            Field field = TabLayout.class.getDeclaredField("mTabStrip");
            field.setAccessible(true);
            //拿mTabStrip属性里面的值
            Object mTabStrip = field.get(tabLayout);
            //通过mTabStrip对象来获取class类，不能用field来获取class类，参数不能写Integer.class
            Method method = mTabStrip.getClass().getDeclaredMethod("setSelectedIndicatorColor", int.class);
            method.setAccessible(true);
            method.invoke(mTabStrip, Color.parseColor(getLogUser(this).getThemeColors()));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        //点击选择地址
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
                mDialog.getWindow().setWindowAnimations(R.style.BottomDialogs);
                WindowManager.LayoutParams params = mDialog.getWindow().getAttributes();
                params.x = 0;
                params.gravity = Gravity.BOTTOM;
                mDialog.show();
            }
        });
        //Tablayout
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
                        ToastUtils.show("请先选择省份");
                        mTabLayout.getTabAt(0).select();
                    }

                } else if (tab.getText().equals("区县")) {
                    if (mid == 0) {
                        ToastUtils.show("请先选择省份");
                        mTabLayout.getTabAt(0).select();
                        return;
                    }
                    if (cityid == 0) {
                        ToastUtils.show("请先选择城市");
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


//        //为EditText设置监听，注意监听类型为TextWatcher
//        phone_text.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String phone = phone_text.getText().toString();
//                String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
//                if (phone_text.getText().toString().matches(REGEX_MOBILE_EXACT)){
//
//                }else if (!phone_text.getText().toString().matches(REGEX_MOBILE_EXACT)){
//
//                    Toast.makeText(UserCompileActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });

        //点击事件
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
                if (name.equals("") || phone.equals("") || xiangqing.equals("") || countryId == 0) {
                    ToastUtils.show("请完善信息");
                    return;
                } else {
                    String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
                    if (phone_text.getText().toString().matches(REGEX_MOBILE_EXACT)) {
                        updateAddressPresenter.request(listUserAddressBean.getId(), getLogUser(UserCompileActivity.this).getId(),
                                name, phone, xiangqing, isFirst,
                                proId, cityId, countryId);
                    } else if (!phone_text.getText().toString().matches(REGEX_MOBILE_EXACT)) {
                        ToastUtils.show("请输入正确的手机号");
                    }
                    return;
                }

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


    /**
     * 修改用户地址
     */
    private class UpdateAddress implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            if (request.isSuccess()) {
                finish();
            } else {
                ToastUtils.show("" + request.getMessage());
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
