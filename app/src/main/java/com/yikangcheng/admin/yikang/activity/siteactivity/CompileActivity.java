package com.yikangcheng.admin.yikang.activity.siteactivity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.AddressRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.MyAccountAdapter;
import com.yikangcheng.admin.yikang.activity.fragment.wodezhanghu.AmendFragment;
import com.yikangcheng.admin.yikang.activity.fragment.wodezhanghu.BasicFragment;
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

/**
 * 这是编辑地址页面
 */
public class CompileActivity extends BaseActivtiy implements ICoreInfe {

    private CheckBox check_btn;
    private ImageView mImgActivityCompileFanhui;
    private Toolbar mToolbarActivityCompile;
    private EditText mEtActivityCompileName;
    private EditText mEtActivityCompileShoujihao;
    private TextView mEtActivityCompileDizi;
    private RelativeLayout mRelativeLayoutActivityCompile;
    private EditText mEtActivityCompileXiangxidizi;
    private RelativeLayout mRelativeLayoutActivityAite;
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
    private int countryId;

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
        mImgActivityCompileFanhui = findViewById(R.id.img_activity_compile_fanhui);
        mToolbarActivityCompile = findViewById(R.id.toolbar_activity_compile);
        mEtActivityCompileName = findViewById(R.id.et_activity_compile_name);
        check_btn = findViewById(R.id.check_btn);
        mEtActivityCompileShoujihao = findViewById(R.id.et_activity_compile_shoujihao);
        mEtActivityCompileDizi = findViewById(R.id.et_activity_compile_dizi);
        mRelativeLayoutActivityCompile = findViewById(R.id.relativeLayout_activity_compile);
        mEtActivityCompileXiangxidizi = findViewById(R.id.et_activity_compile_xiangxidizi);
        mRelativeLayoutActivityAite = findViewById(R.id.relativeLayout_activity_aite);
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
         * ToolBar
         */
        mToolbarActivityCompile.setTitle("");
        setSupportActionBar(mToolbarActivityCompile);
        /**
         * 点击返回按钮关闭当前页面
         */
        mImgActivityCompileFanhui.setOnClickListener(new View.OnClickListener() {
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
                    mEtActivityCompileDizi.setText(city + country);
                    addressBeans.clear();
                    mDialog.dismiss();
                }
            }
        });
        mEtActivityCompileDizi.setOnClickListener(new View.OnClickListener() {
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
        mRelativeLayoutActivityAite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int isFirst;
                String name = mEtActivityCompileName.getText().toString();
                String phone = mEtActivityCompileShoujihao.getText().toString();
                String address = mEtActivityCompileDizi.getText().toString();
                String xiangqing = mEtActivityCompileXiangxidizi.getText().toString();
                boolean checked = check_btn.isChecked();
                if (checked) {
                    isFirst = 1;
                } else {
                    isFirst = 0;
                }
                LoginBean logUser = getLogUser(CompileActivity.this);
                insertAddressPresenter.request(logUser.getId(), name, phone, xiangqing, isFirst, proId, cityId, countryId);
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

    /**
     * 添加收货地址
     */
    private class AddressIntface implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            Toast.makeText(CompileActivity.this, "" + request.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
