package com.yikangcheng.admin.yikang.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.Toast;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.GetMobileKeyPresenter;
import com.yikangcheng.admin.yikang.presenter.RegisterPresenter;
import com.yikangcheng.admin.yikang.presenter.SendMobilePresenter;
import com.yikangcheng.admin.yikang.util.UIUtils;

import java.util.List;

import me.jessyan.autosize.internal.CustomAdapt;
import me.leefeng.promptlibrary.PromptDialog;

public class RegisterActivity extends BaseActivtiy implements ICoreInfe, CustomAdapt, View.OnClickListener {

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
                    ver_btn.setText("点击发送验证码");
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

    @Override
    protected void initView() {
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
    }

    @Override
    protected void initEventData() {
        //点击事件
        log_image.setOnClickListener(this);
        ver_btn.setOnClickListener(this);
        register_btn.setOnClickListener(this);
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


    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return width / 2;
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
                boolean mobile = UIUtils.isMobile(phone);
                if (mobile) {
                    getMobileKeyPresenter.request(phone, "Android");
                } else {
                    Toast.makeText(RegisterActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }
                break;
//                注册
            case R.id.register_btn:
                String code = ver_pwd_edit.getText().toString();
                String reg_pwd = reg_pwd_edit.getText().toString();
                String reg_two_pwd = reg_two_pwd_edit.getText().toString();
                phone = reg_phone_edit.getText().toString();
                String code_two = RegisterActivity.this.code_two_pwd_edit.getText().toString();
                if (phone.equals("") || reg_pwd.equals("") || reg_two_pwd.equals("")) {
                    Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (code.equals("") || code_two.equals("")) {
                    Toast.makeText(this, "验证码或邀请码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                registerPresenter.request(phone, reg_pwd, reg_two_pwd, code, code_two);
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
            Toast.makeText(this, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(RegisterActivity.this, "" + request.getMessage(), Toast.LENGTH_SHORT).show();
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
                promptDialog.showSuccess(request.getMessage());
                finish();
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
}
