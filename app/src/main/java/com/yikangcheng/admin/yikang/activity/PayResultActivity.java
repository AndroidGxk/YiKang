package com.yikangcheng.admin.yikang.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.jessyan.autosize.internal.CustomAdapt;

public class PayResultActivity extends BaseActivtiy   {

    private ImageView img;
    private TextView text;
    private int pay;
    private int width;

    @Override
    protected void initView() {
        //设置标题栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorTab);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        width = wm.getDefaultDisplay().getWidth();
        Intent intent = getIntent();
        pay = intent.getIntExtra("pay", 0);
        img = (ImageView) findViewById(R.id.img);
        text = (TextView) findViewById(R.id.text);
    }

    @Override
    protected void initEventData() {
        if (pay == 1) {
            text.setText("支付成功");
            img.setBackgroundResource(R.drawable.pay_succeed);
        } else {
            text.setText("支付失败");
            img.setBackgroundResource(R.drawable.pay_error);
        }
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_pay_result;
    }

    @Override
    protected void createPresenter() {

    }

    public void backImg(View view) {
        finish();
    }

}
