package com.yikangcheng.admin.yikang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
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

import me.jessyan.autosize.internal.CustomAdapt;

public class RegisterActivity extends BaseActivtiy implements ICoreInfe, CustomAdapt {

    private String phone;
    private ImageView log_image;
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

    @Override
    protected void initView() {
        log_image = findViewById(R.id.log_image);
        reg_phone_edit = findViewById(R.id.reg_phone_edit);
        ver_btn = findViewById(R.id.ver_btn);
        ver_pwd_edit = findViewById(R.id.ver_pwd_edit);
        reg_pwd_edit = findViewById(R.id.reg_pwd_edit);
        reg_two_pwd_edit = findViewById(R.id.reg_two_pwd_edit);
        code_two_pwd_edit = findViewById(R.id.code_two_pwd_edit);
        register_btn = findViewById(R.id.register_btn);
        getMobileKeyPresenter = new GetMobileKeyPresenter(this);
        sendMobilePresenter = new SendMobilePresenter(new SendMobile());
        registerPresenter = new RegisterPresenter(new Register());
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        height = wm.getDefaultDisplay().getHeight();
    }

    @Override
    protected void initEventData() {
        log_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        /**
         * 点击发送验证码
         */
        ver_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = reg_phone_edit.getText().toString();
                getMobileKeyPresenter.request(phone, "Android");
                handler.sendEmptyMessage(1);
            }
        });
        /**
         * 注册
         */
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ver_pwd_edit,reg_pwd_edit,reg_two_pwd_edit,code_two_pwd_edit
                String code = ver_pwd_edit.getText().toString();
                String reg_pwd = reg_pwd_edit.getText().toString();
                String reg_two_pwd = reg_two_pwd_edit.getText().toString();
                String code_two = RegisterActivity.this.code_two_pwd_edit.getText().toString();
                registerPresenter.request(phone, reg_pwd, reg_two_pwd, code, code_two);
            }
        });
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
        return height / 2;
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
