package com.yikangcheng.admin.yikang.activity.fragment.wodezhanghu;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.LoginActivity;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.UserDetailBean;
import com.yikangcheng.admin.yikang.bean.UserInfoBean;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.GetMobileKeyPresenter;
import com.yikangcheng.admin.yikang.presenter.RetrievePwdPresenter;
import com.yikangcheng.admin.yikang.presenter.SendMobilePresenter;
import com.yikangcheng.admin.yikang.presenter.UserInfoPresenter;
import com.yikangcheng.admin.yikang.util.UIUtils;

/**
 * Created by lenovo on 2019/5/17.
 * WF
 */
public class AmendFragment extends BaseFragment implements ICoreInfe {

    private TextView moblie_text, phone_text;
    private int mCount = 60;
    private RelativeLayout reLayout_fragment_basic_save;
    private EditText moblie, newpwd_text, newpwd_texts;
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
    private LoginBean logUser;

    @Override
    protected void initView(View view) {
        moblie_text = view.findViewById(R.id.moblie_text);
        phone_text = view.findViewById(R.id.EditTixt_activity_seek_sousuo);
        moblie = view.findViewById(R.id.set_moblie);
        newpwd_text = view.findViewById(R.id.newpwd);
        newpwd_texts = view.findViewById(R.id.newpwds);
        reLayout_fragment_basic_save = view.findViewById(R.id.reLayout_fragment_basic_save);
        //修改密码
        retrievePwdPresenter = new RetrievePwdPresenter(this);
        //获取短信验证前的key
        getMobileKeyPresenter = new GetMobileKeyPresenter(new GetMobileKey());
        //获取短信验证码
        sendMobilePresenter = new SendMobilePresenter(new SendMobile());
        UserInfoPresenter userInfoPresenter = new UserInfoPresenter(new UserInfo());
        logUser = getLogUser(getContext());
        if (logUser != null) {
            userInfoPresenter.request(logUser.getId());
        }

    }

    @Override
    protected void initData() {
        /**
         * 点击发送验证码
         */
        moblie_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phone_text.getText().toString();
                boolean mobile = UIUtils.isMobile(phone);
                if (mobile) {
                    getMobileKeyPresenter.request(phone, "Android");
                    handler.sendEmptyMessage(1);
                } else {
                    Toast.makeText(getContext(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }

            }
        });
        /**
         * 修改
         */
        reLayout_fragment_basic_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = phone_text.getText().toString();
                String moblie_code = moblie.getText().toString();
                String newpwd = newpwd_text.getText().toString();
                String newpwds = newpwd_texts.getText().toString();
                if(moblie_code.equals("")){
                    Toast.makeText(getContext(), "请输入验证码", Toast.LENGTH_SHORT).show();
                }
                if (newpwd.equals("") || newpwds.equals("")) {
                    Toast.makeText(getContext(), "请输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                retrievePwdPresenter.request(phone, "mobile", moblie_code, newpwd, newpwds);
            }
        });
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_amend;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1);
    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        Toast.makeText(getContext(), "" + request.getMessage(), Toast.LENGTH_SHORT).show();
        if (request.isSuccess()) {
            getDelete(getContext());
            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void fail(ApiException e) {

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
            Toast.makeText(getContext(), "" + request.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    /**
     * 用户资料
     */
    private class UserInfo implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            UserInfoBean entity = (UserInfoBean) request.getEntity();
            UserDetailBean userCenter = (UserDetailBean) entity.getUserCenter();
            phone_text.setText(userCenter.getMobile() + "");
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
