package com.yikangcheng.admin.yikang.activity;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.GetMobileKeyPresenter;
import com.yikangcheng.admin.yikang.presenter.RetrievePwdPresenter;
import com.yikangcheng.admin.yikang.presenter.SendMobilePresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;
import com.yikangcheng.admin.yikang.util.UIUtils;

import me.jessyan.autosize.internal.CustomAdapt;

public class FindPwdActivity extends BaseActivtiy implements CustomAdapt, ICoreInfe, View.OnClickListener {
    private TextView moblie_text;
    private RelativeLayout updatebtn;
    private int mCount = 60;
    private EditText phone_text, moblie, newpwd_text, newpwd_texts;
    /**
     * 倒计时
     */
    Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (mCount <= 0) {
                    moblie_text.setClickable(true);
                    moblie_text.setText("获取短信验证码");
                    mCount = 60;
                    return;
                }
                moblie_text.setText(mCount + "秒后重试");
                moblie_text.setClickable(false);
                mCount--;
                handler.sendEmptyMessageDelayed(1, 1000);
            }
        }
    };
    private RetrievePwdPresenter retrievePwdPresenter;
    private GetMobileKeyPresenter getMobileKeyPresenter;
    private SendMobilePresenter sendMobilePresenter;
    private int width;
    private ImageView mFanhui;

    @Override
    protected void initView() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        width = wm.getDefaultDisplay().getWidth();
        updatebtn = (RelativeLayout) findViewById(R.id.updatebtn);
        moblie_text = (TextView) findViewById(R.id.moblie_text);
        phone_text = (EditText) findViewById(R.id.EditTixt_activity_seek_sousuo);
        moblie = (EditText) findViewById(R.id.set_moblie);
        newpwd_text = (EditText) findViewById(R.id.newpwd);
        mFanhui = (ImageView) findViewById(R.id.img_fanhui);
        newpwd_texts = (EditText) findViewById(R.id.newpwds);
        //修改密码
        retrievePwdPresenter = new RetrievePwdPresenter(this);
        //获取短信验证前的key
        getMobileKeyPresenter = new GetMobileKeyPresenter(new GetMobileKey());
        //获取短信验证码
        sendMobilePresenter = new SendMobilePresenter(new SendMobile());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1);
    }

    @Override
    protected void initEventData() {
        //点击事件
        mFanhui.setOnClickListener(this);
        moblie_text.setOnClickListener(this);
        updatebtn.setOnClickListener(this);
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_find_pwd;
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
        Toast.makeText(FindPwdActivity.this, "" + request.getMessage(), Toast.LENGTH_SHORT).show();
        if (request.isSuccess()) {
            finish();
        }
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
//          关闭当前页面
            case R.id.img_fanhui:
                finish();
                break;
//          点击发送验证码
            case R.id.moblie_text:
                String phone = phone_text.getText().toString();
                boolean mobile = UIUtils.isMobile(phone);
                if (mobile) {
                    getMobileKeyPresenter.request(phone, "Android");
                    handler.sendEmptyMessage(1);
                } else {
                    Toast.makeText(FindPwdActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }
                break;
//          修改
            case R.id.updatebtn:
                String phone_up = phone_text.getText().toString();
                String moblie_code = moblie.getText().toString();
                String newpwd = newpwd_text.getText().toString();
                String newpwds = newpwd_texts.getText().toString();
                if(moblie_code.equals("")){
                    Toast.makeText(FindPwdActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                }
                if (newpwd.equals("") || newpwds.equals("")) {
                    Toast.makeText(FindPwdActivity.this, "请输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                retrievePwdPresenter.request(phone_up, "mobile", moblie_code, newpwd, newpwds);
                break;
        }
    }

    /**
     * 获取短信验证key
     */
    private class GetMobileKey implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            if (request.isSuccess()) {
                String entity = (String) request.getEntity();
                String phone = phone_text.getText().toString();
                sendMobilePresenter.request(phone, "retrieve", entity, "Android");
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    /**
     * 获取验证码
     */
    private class SendMobile implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            Toast.makeText(FindPwdActivity.this, "" + request.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
