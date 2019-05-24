package com.yikangcheng.admin.yikang.base;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;


import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivtiy extends AppCompatActivity {
    private Unbinder mUnbinder;
    public static Context mContext;
    protected BasePresenter mPresenter;
    private FragmentManager mManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivtiyLayoutId());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        mUnbinder = ButterKnife.bind(this);
        mManager = getSupportFragmentManager();
        mContext = this;
        BaseApp.getAppInstance().addActivtiy(this);
        initView();
        createPresenter();
        initEventData();

    }

    protected abstract void initView();


    protected void addFragment(int containerId, BaseFragment fragment) {
        mManager.beginTransaction().add(containerId, fragment,
                BaseFragment.class.getSimpleName()).
                commitAllowingStateLoss();
    }


    protected void replaceFragment(int containnerId, BaseFragment fragment) {
        mManager.beginTransaction().replace(containnerId, fragment,
                BaseFragment.class.getSimpleName()).
                commitAllowingStateLoss();

    }


    protected abstract void initEventData();

    protected abstract int getActivtiyLayoutId();

    protected abstract void createPresenter();



    protected void setToolBar(Toolbar toolBar, String title) {
        toolBar.setTitle(title);
        setSupportActionBar(toolBar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 解决修改系统字体大小APP字体跟着变大的问题
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }
}
