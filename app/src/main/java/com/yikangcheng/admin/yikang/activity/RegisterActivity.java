package com.yikangcheng.admin.yikang.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.GetMobileKeyPresenter;
import com.yikangcheng.admin.yikang.presenter.LoginPresenter;
import com.yikangcheng.admin.yikang.presenter.RegisterPresenter;
import com.yikangcheng.admin.yikang.presenter.SendMobilePresenter;
import com.yikangcheng.admin.yikang.util.PermissionsActivity;
import com.yikangcheng.admin.yikang.util.PermissionsChecker;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import me.jessyan.autosize.internal.CustomAdapt;
import me.leefeng.promptlibrary.PromptDialog;

public class RegisterActivity extends BaseActivtiy implements ICoreInfe, View.OnClickListener {

    private static final String TAG = "GTT";
    private String phone;
    private LinearLayout log_image;
    private TextView ver_btn, register_btn;
    private EditText reg_phone_edit, ver_pwd_edit, reg_pwd_edit, reg_two_pwd_edit, code_two_pwd_edit;
    private int mCount = 60;
    /**
     * 倒计时
     */
    Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (mCount <= 0) {
                    ver_btn.setClickable(true);
                    ver_btn.setText("获取验证码");
                    mCount = 60;
                    return;
                }
                ver_btn.setText(mCount + "秒");
                ver_btn.setClickable(false);
                mCount--;
                handler.sendEmptyMessageDelayed(1, 1000);
            }
        }
    };
    private GetMobileKeyPresenter getMobileKeyPresenter;
    private SendMobilePresenter sendMobilePresenter;
    private RegisterPresenter registerPresenter;
    private int width;
    private LocationManager locationManager;
    private String locationProvider;
    private String address;
    private PromptDialog promptDialog;
    private LoginPresenter loginPresenter;
    private String reg_two_pwd;
    public SharedPreferences userInfo;
    private double dimensionality;
    private double longitude;
    @BindView(R.id.phone_line)
    LinearLayout phone_line;
    @BindView(R.id.yanzheng_line)
    LinearLayout yanzheng_line;
    @BindView(R.id.password_line)
    LinearLayout password_line;
    @BindView(R.id.password_line2)
    LinearLayout password_line2;
    @BindView(R.id.yaoqingma)
    LinearLayout yaoqingma;
    private static final int REQUEST_CODE = 0; // 请求码
    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CAMERA
    };
    private PermissionsChecker mPermissionsChecker; // 权限检测器

    @Override
    protected void initView() {
        mPermissionsChecker = new PermissionsChecker(this);
        getLocation(getApplicationContext());
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        //创建对象
        promptDialog = new PromptDialog(RegisterActivity.this);
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(1).loadingDuration(1000);
        log_image = (LinearLayout) findViewById(R.id.log_image);
        reg_phone_edit = (EditText) findViewById(R.id.reg_phone_edit);
        ver_btn = (TextView) findViewById(R.id.ver_btn);
        ver_pwd_edit = (EditText) findViewById(R.id.ver_pwd_edit);
        reg_pwd_edit = (EditText) findViewById(R.id.reg_pwd_edit);
        reg_two_pwd_edit = (EditText) findViewById(R.id.reg_two_pwd_edit);
        code_two_pwd_edit = (EditText) findViewById(R.id.code_two_pwd_edit);
        register_btn = (TextView) findViewById(R.id.register_btn);
        getMobileKeyPresenter = new GetMobileKeyPresenter(this);
        sendMobilePresenter = new SendMobilePresenter(new SendMobile());
        registerPresenter = new RegisterPresenter(new Register());
        loginPresenter = new LoginPresenter(new Login());
        //用户ID
        userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
        phone_line.getBackground().mutate().setAlpha(100);
        password_line.getBackground().mutate().setAlpha(100);
        yanzheng_line.getBackground().mutate().setAlpha(100);
        password_line2.getBackground().mutate().setAlpha(100);
        yaoqingma.getBackground().mutate().setAlpha(100);
    }

    @Override
    protected void initEventData() {
        //点击事件
        log_image.setOnClickListener(this);
        ver_btn.setOnClickListener(this);
        register_btn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            startPermissionsActivity();
        } else {
        }
    }

    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        }
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        String key = (String) request.getEntity();
        sendMobilePresenter.request(phone, "register", key, "Android");
    }

    @Override
    public void fail(ApiException e) {

    }


    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            点击去登录
            case R.id.log_image:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
//            点击发送验证码
            case R.id.ver_btn:
                phone = reg_phone_edit.getText().toString();
                if (phone.equals("")) {
                    ToastUtils.show("请输入正确的手机号");
                    return;
                } else {
                    getMobileKeyPresenter.request(phone, "Android");
                }
                break;
//                注册
            case R.id.register_btn:
                String code = ver_pwd_edit.getText().toString();
                String reg_pwd = reg_pwd_edit.getText().toString();
                reg_two_pwd = reg_two_pwd_edit.getText().toString();
                phone = reg_phone_edit.getText().toString();
                String code_two = RegisterActivity.this.code_two_pwd_edit.getText().toString();
                if (phone.equals("") || reg_pwd.equals("") || reg_two_pwd.equals("")) {
                    ToastUtils.show("账号或密码不能为空");
                    return;
                } else if (code.equals("") || code_two.equals("")) {
                    ToastUtils.show("验证码或邀请码不能为空");
                    return;
                } else if (reg_pwd.length() < 6 || reg_pwd.length() > 20) {
                    ToastUtils.show("密码长度需在6-20位字符之间");
                    return;
                }
                registerPresenter.request(phone, reg_pwd, reg_two_pwd, code, code_two, String.valueOf(longitude), String.valueOf(dimensionality));
                promptDialog.showLoading("加载中...");
                break;
        }
    }

    private void getLocation(Context context) {
        //1.获取位置管理器
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //2.获取位置提供器，GPS或是NetWork
        List<String> providers = locationManager.getProviders(true);
        if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是网络定位
            locationProvider = LocationManager.NETWORK_PROVIDER;
            Log.e("main", "网络");
        } else if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //如果是GPS定位
            locationProvider = LocationManager.GPS_PROVIDER;
            Log.e("main", "gps");
        } else {
            Log.e("main", "没有可用的位置提供器");
            return;
        }

        //3.获取上次的位置，一般第一次运行，此值为null
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return;
        }
        Location location = locationManager.getLastKnownLocation(locationProvider);
        if (location != null) {
            showLocation(location);
        } else {
            // 监视地理位置变化，第二个和第三个参数分别为更新的最短时间minTime和最短距离minDistace
            locationManager.requestLocationUpdates(locationProvider, 0, 0, mListener);
        }
    }

    private void showLocation(Location location) {
        longitude = location.getLongitude();
        dimensionality = location.getLongitude();

        address = "纬度：" + location.getLatitude() + "经度：" + location.getLongitude();
    }

    LocationListener mListener = new LocationListener() {
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        // 如果位置发生变化，重新显示
        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }
    };



    /**
     * 发送短信
     */
    public class SendMobile implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            ToastUtils.show("" + request.getMessage());
            if (request.isSuccess()) {
                handler.sendEmptyMessage(1);
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    /**
     * 注册
     */
    public class Register implements ICoreInfe {

        @Override
        public void success(Object data) {
            Request request = (Request) data;
            if (request.isSuccess()) {
//                promptDialog.showSuccess(request.getMessage());
                loginPresenter.request(phone, reg_two_pwd);
                promptDialog.showLoading("正在登录");
            } else {
                promptDialog.showError(request.getMessage());
            }
        }

        @Override
        public void fail(ApiException e) {
            promptDialog.showError("注册失败");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1);
    }

    /**
     * 登录
     */
    private class Login implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            if (request.isSuccess()) {
                SharedPreferences.Editor edit = userInfo.edit();
                LoginBean entity = (LoginBean) request.getEntity();
                edit.putString("pwd", reg_two_pwd);
                edit.commit();
                entity.setStatus(1);
                setLogUser(RegisterActivity.this, entity);
                promptDialog.showSuccess("登录成功");
                SharedPreferences sp = getSharedPreferences("activity", MODE_PRIVATE);
                SharedPreferences.Editor activit = sp.edit();
                activit.putString("acti", "register");
                activit.commit();
                Intent intent = new Intent(RegisterActivity.this, ApplySeleActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
                promptDialog.showError(request.getMessage());
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
