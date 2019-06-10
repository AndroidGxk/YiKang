package com.yikangcheng.admin.yikang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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

import me.jessyan.autosize.internal.CustomAdapt;

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
    private int height;
    private GetMobileKeyPresenter getMobileKeyPresenter;
    private SendMobilePresenter sendMobilePresenter;
    private RegisterPresenter registerPresenter;
    private int width;

    @Override
    protected void initView() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        width = wm.getDefaultDisplay().getWidth();
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
                    handler.sendEmptyMessage(1);
                } else {
                    Toast.makeText(RegisterActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }
                break;
//                注册
            case R.id.register_btn:
                String code = ver_pwd_edit.getText().toString();
                String reg_pwd = reg_pwd_edit.getText().toString();
                String reg_two_pwd = reg_two_pwd_edit.getText().toString();
                String code_two = RegisterActivity.this.code_two_pwd_edit.getText().toString();
                registerPresenter.request(phone, reg_pwd, reg_two_pwd, code, code_two);
                break;
        }
    }

    /**
     * 发送短信
     */
    public class SendMobile implements ICoreInfe {

        @Override
        public void success(Object data) {
            Request request = (Request) data;
            Toast.makeText(RegisterActivity.this, "" + request.getMessage(), Toast.LENGTH_SHORT).show();
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
                finish();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1);
    }
}
