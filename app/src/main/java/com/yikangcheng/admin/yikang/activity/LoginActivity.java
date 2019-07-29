package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.LoginPresenter;

import me.leefeng.promptlibrary.PromptDialog;

public class LoginActivity extends BaseActivtiy implements ICoreInfe, View.OnClickListener {

    private LinearLayout reg_image;
    private TextView forget_pwd;
    private RelativeLayout log_btn;
    private EditText phone_edit, pwd_edit;
    private LoginPresenter loginPresenter;
    public SharedPreferences userInfo;
    private PromptDialog promptDialog;

    @Override
    protected void initView() {
        closeSwipeBack();
        //创建对象
        promptDialog = new PromptDialog(LoginActivity.this);
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(1).loadingDuration(1000);
        reg_image = (LinearLayout) findViewById(R.id.reg_image);
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
        //点击事件
        reg_image.setOnClickListener(this);
        log_btn.setOnClickListener(this);
        forget_pwd.setOnClickListener(this);
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void createPresenter() {

    }


    @Override
    public void success(Object data) {
        Request request = (Request) data;
        if (request.isSuccess()) {
            SharedPreferences.Editor edit = userInfo.edit();
            LoginBean entity = (LoginBean) request.getEntity();
            edit.putString("pwd", pwd_edit.getText().toString());
            edit.commit();
            entity.setStatus(1);
            setLogUser(LoginActivity.this, entity);
            promptDialog.showSuccess("登录成功");
            SharedPreferences sp = getSharedPreferences("activity", MODE_PRIVATE);
            SharedPreferences.Editor activit = sp.edit();
            activit.putString("acti", "login");
            activit.commit();
            startActivity(new Intent(LoginActivity.this, ApplySeleActivity.class));
            finish();
        } else {
            promptDialog.showError(request.getMessage());
        }
    }

    @Override
    public void fail(ApiException e) {
        Log.d("GTT", e.toString());
        promptDialog.showError("登录失败");
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //注册
            case R.id.reg_image:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            //登录
            case R.id.log_btn:
                String phone = phone_edit.getText().toString();
                String pwd = pwd_edit.getText().toString();
                if (phone.equals("") || pwd.equals("")) {
                    ToastUtils.show("账号或密码不能为空" );
                    return;
                }
                loginPresenter.request(phone, pwd);
                promptDialog.showLoading("正在登录");
                break;
            //忘记密码
            case R.id.forget_pwd:
                startActivity(new Intent(LoginActivity.this, FindPwdActivity.class));
                break;
        }
    }
}
