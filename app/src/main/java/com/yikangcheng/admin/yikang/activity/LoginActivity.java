package com.yikangcheng.admin.yikang.activity;

import android.content.Context;
import android.content.Intent;
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
import com.yikangcheng.admin.yikang.presenter.LoginPresenter;

import me.jessyan.autosize.internal.CustomAdapt;

public class LoginActivity extends BaseActivtiy implements CustomAdapt, ICoreInfe {

    private ImageView reg_image;
    private int height;
    private TextView log_btn;
    private EditText phone_edit, pwd_edit;
    private LoginPresenter loginPresenter;

    @Override
    protected void initView() {
        reg_image = findViewById(R.id.reg_image);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        height = wm.getDefaultDisplay().getHeight();
        phone_edit = findViewById(R.id.phone_edit);
        pwd_edit = findViewById(R.id.pwd_edit);
        log_btn = findViewById(R.id.log_btn);
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    protected void initEventData() {
        /**
         * 注册
         */
        reg_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        /**
         *
         */
        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phone_edit.getText().toString();
                String pwd = pwd_edit.getText().toString();
                loginPresenter.request(phone, pwd);
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return height / 2;
    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        if (request.isSuccess()) {
            finish();
        }else{
            Toast.makeText(this, ""+request.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void fail(ApiException e) {

    }
}
