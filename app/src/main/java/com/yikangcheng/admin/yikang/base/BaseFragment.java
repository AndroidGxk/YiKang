package com.yikangcheng.admin.yikang.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sqlite.dao.DaoMaster;
import com.example.sqlite.dao.DaoSession;
import com.example.sqlite.dao.LoginBeanDao;
import com.example.sqlite.dao.UserDetailBeanDao;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.UserDetailBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {
    private Unbinder mButterKnife;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayoutId(), container, false);
        initView(view);
        initData();
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mButterKnife = ButterKnife.bind(this, view);
        initData();
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract void initView(View view);

    protected abstract void initData();

    protected abstract int getFragmentLayoutId();


    @Override
    public void onDestroy() {
        if (mButterKnife != null)
            mButterKnife.unbind();
        super.onDestroy();
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
}
