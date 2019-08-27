package com.yikangcheng.admin.yikang.activity.myaccount;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import butterknife.BindView;

public class IntroActivity extends BaseActivtiy {
    private ImageView back_img;
    private RelativeLayout mToolbarActivityIntro;
    private EditText mEtIntroActivityIntro;
    private TextView baocun_text;
    private int height;
    private int width;
    @BindView(R.id.title)
    TextView title;

    @Override
    protected void initView() {
        //设置状态栏颜色
        if (!getLogUser(this).getThemeColors().equals("")) {
            StatusBarUtil.setStatusBarMode(this, true, Color.parseColor(getLogUser(this).getThemeColors()));
        } else {
            StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        }
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        Intent intent = getIntent();
        String jianjie = intent.getStringExtra("jianjie");
        back_img = (ImageView) findViewById(R.id.back_img);
        mToolbarActivityIntro = (RelativeLayout) findViewById(R.id.toolbar_activity_intro);
        mToolbarActivityIntro.setBackgroundColor(Color.parseColor(getLogUser(this).getThemeColors()));
        //标题字体颜色
//        title.setTextColor(Color.parseColor());
        mEtIntroActivityIntro = (EditText) findViewById(R.id.et_intro_activity_intro);
        baocun_text = (TextView) findViewById(R.id.baocun_text);
        GradientDrawable gradient = (GradientDrawable) baocun_text.getBackground();
        gradient.setColor(Color.parseColor(getLogUser(IntroActivity.this).getThemeColors()));
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mEtIntroActivityIntro.setText(jianjie);
    }


    @Override
    protected void initEventData() {
        /**
         * 保存按钮
         */
        baocun_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String intro = mEtIntroActivityIntro.getText().toString();
                if (intro.length() < 2) {
                    ToastUtils.show("简介太少啦");
                    return;
                }
                SharedPreferences sp = getSharedPreferences("intro", MODE_PRIVATE);
                sp.edit().putString("intro", intro).commit();
                finish();
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_intro;
    }

    @Override
    protected void createPresenter() {

    }

}
