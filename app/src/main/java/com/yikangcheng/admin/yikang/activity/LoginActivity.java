package com.yikangcheng.admin.yikang.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.LoginPresenter;

import me.jessyan.autosize.internal.CustomAdapt;
import me.leefeng.promptlibrary.PromptDialog;

public class LoginActivity extends BaseActivtiy implements CustomAdapt, ICoreInfe {

    private LinearLayout reg_image;
    private int height;
    private TextView forget_pwd;
    private RelativeLayout log_btn;
    private EditText phone_edit, pwd_edit;
    private LoginPresenter loginPresenter;
    public SharedPreferences userInfo;
    private PromptDialog promptDialog;
    private int width;

    @Override
    protected void initView() {
        closeSwipeBack();
        //创建对象
        promptDialog = new PromptDialog(LoginActivity.this);
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(1).loadingDuration(1000);
        reg_image = (LinearLayout) findViewById(R.id.reg_image);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        height = wm.getDefaultDisplay().getHeight();
        width = wm.getDefaultDisplay().getWidth();
        phone_edit = (EditText) findViewById(R.id.phone_edit);
        pwd_edit = (EditText) findViewById(R.id.pwd_edit);
        forget_pwd = (TextView) findViewById(R.id.forget_pwd);
        log_btn = (RelativeLayout) findViewById(R.id.log_btn);
        loginPresenter = new LoginPresenter(this);
        //用户ID
        userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);
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
         *登录
         */
        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phone_edit.getText().toString();
                String pwd = pwd_edit.getText().toString();
                loginPresenter.request(phone, pwd);
                promptDialog.showLoading("正在登录");
            }
        });
        /**
         * 忘记密码
         */
        forget_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, FindPwdActivity.class));
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
        return width / 2;
    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        if (request.isSuccess()) {
            SharedPreferences.Editor edit = userInfo.edit();
            LoginBean entity = (LoginBean) request.getEntity();
            edit.putString("userId", String.valueOf(entity.getId()));
            edit.commit();
            entity.setStatus(1);
            setLogUser(LoginActivity.this, entity);
            promptDialog.showSuccess("登录成功");
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        } else {
            promptDialog.showError("登录失败");
        }
    }

    @Override
    public void fail(ApiException e) {
        promptDialog.showError("登录失败");
    }
}
