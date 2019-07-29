package com.yikangcheng.admin.yikang.base;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.example.sqlite.dao.DaoMaster;
import com.example.sqlite.dao.DaoSession;
import com.example.sqlite.dao.LoginBeanDao;
import com.example.sqlite.dao.UserDetailBeanDao;
import com.tianma.netdetector.lib.NetStateChangeObserver;
import com.tianma.netdetector.lib.NetStateChangeReceiver;
import com.tianma.netdetector.lib.NetworkType;
import com.umeng.message.PushAgent;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.UserDetailBean;

import java.util.List;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;


public abstract class BaseActivtiy extends SwipeBackActivity implements NetStateChangeObserver {
    public static Context mContext;
    private FragmentManager mManager;
    /**
     * 右滑退出
     */
    private SwipeBackLayout mSwipeBackLayout;

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
        PushAgent.getInstance(this).onAppStart();
        mManager = getSupportFragmentManager();
        mContext = this;
        ButterKnife.bind(this);//绑定布局
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


    /**
     * 数据库
     */
    public UserDetailBean getUserInfo(Context context) {
        DaoSession daoSession = DaoMaster.newDevSession(context, UserDetailBeanDao.TABLENAME);
        UserDetailBeanDao userDetailBeanDao = daoSession.getUserDetailBeanDao();
//        List<UserDetailBean> list = userDetailBeanDao.queryBuilder().where(UserDetailBeanDao.Properties.Statu.eq("1"))
//                .build().list();
        List<UserDetailBean> list = userDetailBeanDao.loadAll();
        if (list.size() > 0) {
            UserDetailBean userDetailBean = list.get(0);
            return userDetailBean;
        }
        return null;
    }

    /**
     * 添加数据库
     */
    public void setUserInfo(Context context, UserDetailBean data) {
        DaoSession daoSession = DaoMaster.newDevSession(context, UserDetailBeanDao.TABLENAME);
        UserDetailBeanDao userDetailBeanDao = daoSession.getUserDetailBeanDao();
        userDetailBeanDao.deleteAll();
        long l = userDetailBeanDao.insertOrReplace(data);
        Log.e("GT", "数据库--------------------" + l);
    }

    /**
     * 添加用户登录数据库
     */
    public void setLogUser(Context context, LoginBean data) {
        DaoSession daoSession = DaoMaster.newDevSession(context, LoginBeanDao.TABLENAME);
        LoginBeanDao loginBeanDao = daoSession.getLoginBeanDao();
        loginBeanDao.deleteAll();
        long l = loginBeanDao.insertOrReplace(data);
        Log.e("GT", "数据库--------------------" + l);
    }

    /**
     * 查询用户登录数据库
     */
    public LoginBean getLogUser(Context context) {
        DaoSession daoSession = DaoMaster.newDevSession(context, LoginBeanDao.TABLENAME);
        LoginBeanDao loginBeanDao = daoSession.getLoginBeanDao();
        List<LoginBean> list = loginBeanDao.queryBuilder().where(LoginBeanDao.Properties.Status.eq("1"))
                .build().list();
        if (list.size() > 0) {
            LoginBean loginBean = list.get(0);
            return loginBean;
        }
        return null;
    }

    /**
     * 删除用户登录数据库
     */
    public void getDelete(Context context) {
        DaoSession daoSession = DaoMaster.newDevSession(context, LoginBeanDao.TABLENAME);
        LoginBeanDao loginBeanDao = daoSession.getLoginBeanDao();
        loginBeanDao.deleteAll();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 解决修改系统字体大小APP字体跟着变大的问题
     *
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

    /**
     * 初始化右滑退出
     */
    private void initSwipeBack() {
        // 可以调用该方法，设置是否允许滑动退出
        setSwipeBackEnable(true);
        mSwipeBackLayout = getSwipeBackLayout();
        // 设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        // 滑动退出的效果只能从边界滑动才有效果，如果要扩大touch的范围，可以调用这个方法
        // mSwipeBackLayout.setEdgeSize(200);
    }

    /**
     * 关闭右滑退出
     */
    protected void closeSwipeBack() {
        setSwipeBackEnable(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (needRegisterNetworkChangeObserver()) {
            NetStateChangeReceiver.registerObserver(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (needRegisterNetworkChangeObserver()) {
            NetStateChangeReceiver.unregisterObserver(this);
        }
    }

    /**
     * 是否需要注册网络变化的Observer,如果不需要监听网络变化,则返回false;否则返回true.默认返回false
     */
    protected boolean needRegisterNetworkChangeObserver() {
        return false;
    }

    @Override
    public void onNetDisconnected() {
    }

    @Override
    public void onNetConnected(NetworkType networkType) {
    }
}
