package com.yikangcheng.admin.yikang.activity;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.callback.ConfigButton;
import com.mylhyl.circledialog.callback.ConfigDialog;
import com.mylhyl.circledialog.callback.ConfigText;
import com.mylhyl.circledialog.params.ButtonParams;
import com.mylhyl.circledialog.params.DialogParams;
import com.mylhyl.circledialog.params.TextParams;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.CloseAdapter_A;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.AppUpdateBean;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.CheckupdatePresenter;
import com.yikangcheng.admin.yikang.presenter.LoginPresenter;
import com.yikangcheng.admin.yikang.updater.Updater;
import com.yikangcheng.admin.yikang.updater.UpdaterConfig;

import me.jessyan.autosize.internal.CustomAdapt;
import me.leefeng.promptlibrary.PromptDialog;

public class LoginActivity extends BaseActivtiy implements ICoreInfe, View.OnClickListener {

    private TextView reg_image;
    private TextView forget_pwd;
    private TextView log_btn;
    private EditText phone_edit, pwd_edit;
    private LoginPresenter loginPresenter;
    public SharedPreferences userInfo;
    private PromptDialog promptDialog;
    private LinearLayout password_line;
    private LinearLayout phone_line;
    private CheckupdatePresenter checkupdatePresenter;

    @Override
    protected void initView() {
        closeSwipeBack();
        //创建对象
        promptDialog = new PromptDialog(LoginActivity.this);
        checkupdatePresenter = new CheckupdatePresenter(new UpdateApp());
        checkupdatePresenter.request("android");
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(1).loadingDuration(1000);
        reg_image = (TextView) findViewById(R.id.reg_image);
        password_line = (LinearLayout) findViewById(R.id.password_line);
        phone_line = (LinearLayout) findViewById(R.id.phone_line);
        phone_edit = (EditText) findViewById(R.id.phone_edit);
        pwd_edit = (EditText) findViewById(R.id.pwd_edit);
        forget_pwd = (TextView) findViewById(R.id.forget_pwd);
        log_btn = (TextView) findViewById(R.id.log_btn);
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
        password_line.getBackground().mutate().setAlpha(100);
        phone_line.getBackground().mutate().setAlpha(100);

    }

    //查询当前App版本号
    private String getVersionName() throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = LoginActivity.this.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(LoginActivity.this.getPackageName(), 0);
        String version = packInfo.versionName;
        return version;
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
        promptDialog.showError("登录失败");
    }

    /**
     * 更新APP
     */
    private class UpdateApp implements ICoreInfe {
        private String versionName;

        @Override
        public void success(Object data) {
            Request request = (Request) data;
            final AppUpdateBean entity = (AppUpdateBean) request.getEntity();
            try {
                versionName = getVersionName();
                if (versionName != null && entity.getVersionNo() != null) {
                    if (!versionName.equals(entity.getVersionNo())) {
                        new CircleDialog.Builder()
                                .setMaxHeight(0.8f)
                                .setCanceledOnTouchOutside(false)
                                .setCancelable(false)
                                .configDialog(new ConfigDialog() {
                                    @Override
                                    public void onConfig(DialogParams params) {
                                    }
                                })
                                .setTitle("发现新版本")
                                .setText(entity.getDepict())
                                .configText(new ConfigText() {
                                    @Override
                                    public void onConfig(TextParams params) {
                                        params.gravity = Gravity.LEFT | Gravity.TOP;
                                    }
                                })
                                .setNegative("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String versionNo = entity.getVersionNo();
                                        String versionNostr = versionNo.substring(0, 1);
                                        String versionNamestr = versionName.substring(0, 1);
                                        if (versionNamestr.equals(versionNamestr)) {
                                            String versionNostr2 = versionNo.substring(2, 3);
                                            String versionNamestr2 = versionName.substring(2, 3);
                                            if (versionNostr2.equals(versionNamestr2)) {
                                                String versionNostr3 = versionNo.substring(versionNo.length() - 1, versionNo.length());
                                                String versionNamestr3 = versionName.substring(versionName.length() - 1, versionName.length());
                                                if (versionNamestr3.equals(versionNostr3)) {
                                                    return;
                                                } else {
                                                    return;
                                                }
                                            } else {
                                                finish();
                                            }
                                            return;
                                        } else {
                                            finish();
                                        }
                                    }
                                })
                                .setPositive("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        ToastUtils.show("正在更新");
                                        UpdaterConfig config = new UpdaterConfig.Builder(LoginActivity.this)
                                                .setTitle(getResources().getString(R.string.app_name))
                                                .setDescription(entity.getDepict())
                                                .setFileUrl(entity.getDownloadUrl())
                                                .setCanMediaScanner(true)
                                                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE
                                                        | DownloadManager.Request.NETWORK_WIFI)
                                                .build();
                                        Updater.get().showLog(true).download(config);
                                        finish();
                                    }
                                })
                                .configPositive(new ConfigButton() {
                                    @Override
                                    public void onConfig(ButtonParams params) {
                                        params.backgroundColorPress = Color.RED;
                                    }
                                })
                                .show(getSupportFragmentManager());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
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
                    ToastUtils.show("账号或密码不能为空");
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
