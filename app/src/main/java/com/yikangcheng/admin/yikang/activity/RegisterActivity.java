package com.yikangcheng.admin.yikang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.ClassifyListOneBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.TestPersenter;

import java.util.List;

import me.jessyan.autosize.internal.CustomAdapt;

public class RegisterActivity extends BaseActivtiy implements ICoreInfe, CustomAdapt {


    private ImageView log_image;
    private TextView ver_btn;
    private TestPersenter testPersenter;
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

    @Override
    protected void initView() {
        log_image = findViewById(R.id.log_image);
        ver_btn = findViewById(R.id.ver_btn);
        testPersenter = new TestPersenter(this);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
        Toast.makeText(this, "" + height + "----" + width, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initEventData() {
        log_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                testPersenter.request();
            }
        });
        /**
         * 点击发送验证码
         */
        ver_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.sendEmptyMessage(1);
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
        List<ClassifyListOneBean> entity = (List<ClassifyListOneBean>) request.getEntity();
        Toast.makeText(this, "" + entity.get(0).getChildSubjectList().get(0).getSubjectName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fail(ApiException e) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1);
    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return height / 2;
    }
}
