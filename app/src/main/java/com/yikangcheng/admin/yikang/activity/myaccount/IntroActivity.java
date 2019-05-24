package com.yikangcheng.admin.yikang.activity.myaccount;


import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

public class IntroActivity extends BaseActivtiy {
    private ImageView mImgActivityIntroFanhui;
    private Toolbar mToolbarActivityIntro;
    private EditText mEtIntroActivityIntro;
    private RelativeLayout mRelativeLayoutActivityIntro;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        mImgActivityIntroFanhui = findViewById(R.id.img_activity_intro_fanhui);
        mToolbarActivityIntro = findViewById(R.id.toolbar_activity_intro);
        mEtIntroActivityIntro = findViewById(R.id.et_intro_activity_intro);
        mRelativeLayoutActivityIntro = findViewById(R.id.relativeLayout_activity_intro);

        mToolbarActivityIntro.setTitle("");
        setSupportActionBar(mToolbarActivityIntro);

        mImgActivityIntroFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_intro;
    }

    @Override
    protected void createPresenter() {

    }
}
