package com.yikangcheng.admin.yikang.activity.siteactivity;

import android.app.Dialog;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

/**
 * 这是编辑地址页面
 */
public class CompileActivity extends BaseActivtiy {


    private ImageView mImgActivityCompileFanhui;
    private Toolbar mToolbarActivityCompile;
    private EditText mEtActivityCompileName;
    private EditText mEtActivityCompileShoujihao;
    private EditText mEtActivityCompileDizi;
    private RelativeLayout mRelativeLayoutActivityCompile;
    private EditText mEtActivityCompileXiangxidizi;
    private RelativeLayout mRelativeLayoutActivityAite;
    private Dialog mDialog;
    private int width;
    private int height;
    private View mInflate;

    @Override
    protected void initView() {
        /**
         *  设置状态栏颜色
         */
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        mInflate = LayoutInflater.from(CompileActivity.this).inflate(R.layout.site_compile, null);
        mImgActivityCompileFanhui = findViewById(R.id.img_activity_compile_fanhui);
        mToolbarActivityCompile = findViewById(R.id.toolbar_activity_compile);
        mEtActivityCompileName = findViewById(R.id.et_activity_compile_name);
        mEtActivityCompileShoujihao = findViewById(R.id.et_activity_compile_shoujihao);
        mEtActivityCompileDizi = findViewById(R.id.et_activity_compile_dizi);
        mRelativeLayoutActivityCompile = findViewById(R.id.relativeLayout_activity_compile);
        mEtActivityCompileXiangxidizi = findViewById(R.id.et_activity_compile_xiangxidizi);
        mRelativeLayoutActivityAite = findViewById(R.id.relativeLayout_activity_aite);
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        mDialog = new Dialog(this, R.style.BottomDialog);
        /**
         * ToolBar
         */
        mToolbarActivityCompile.setTitle("");
        setSupportActionBar(mToolbarActivityCompile);
        /**
         * 点击返回按钮关闭当前页面
         */
        mImgActivityCompileFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mEtActivityCompileDizi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置dialog的宽高为屏幕的宽高
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, height - 145 * 2);
                mDialog.setContentView(mInflate, layoutParams);
                mDialog.getWindow().setGravity(Gravity.BOTTOM);
                mDialog.setCanceledOnTouchOutside(true);
                mDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
                mDialog.show();
            }
        });
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_compile;
    }

    @Override
    protected void createPresenter() {

    }
}
