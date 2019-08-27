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

import com.example.sqlite.dao.CreatOrderBeanDao;
import com.example.sqlite.dao.DaoMaster;
import com.example.sqlite.dao.DaoSession;
import com.example.sqlite.dao.LoginBeanDao;
import com.example.sqlite.dao.PayBeanDao;
import com.example.sqlite.dao.UserDetailBeanDao;
import com.yikangcheng.admin.yikang.bean.CreatOrderBean;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.PayBean;
import com.yikangcheng.admin.yikang.bean.UserDetailBean;

import java.util.List;

import butterknife.ButterKnife;


public abstract class BaseFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayoutId(), container, false);
        ButterKnife.bind(this, view);//绑定布局
        initView(view);
        initData();
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initData();
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract void initView(View view);

    protected abstract void initData();

    protected abstract int getFragmentLayoutId();


    @Override
    public void onDestroy() {
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


    /**
     * 删除用户登录数据库
     */
    public void getDelete(Context context) {
        DaoSession daoSession = DaoMaster.newDevSession(context, LoginBeanDao.TABLENAME);
        LoginBeanDao loginBeanDao = daoSession.getLoginBeanDao();
        loginBeanDao.deleteAll();
    }


    /**
     * 订单信息
     */
    public CreatOrderBean getOrderInfo(Context context) {
        DaoSession daoSession = DaoMaster.newDevSession(context, CreatOrderBeanDao.TABLENAME);
        CreatOrderBeanDao creatOrderBeanDao = daoSession.getCreatOrderBeanDao();
        List<CreatOrderBean> beanList = creatOrderBeanDao.loadAll();
        if (beanList.size() > 0) {
            CreatOrderBean creatOrderBean = beanList.get(0);
            return creatOrderBean;
        }
        return null;
    }

    /**
     * 添加订单数据库
     *
     * @param context
     * @param creatOrderBean
     */
    public void setOrderInfo(Context context, CreatOrderBean creatOrderBean) {
        DaoSession daoSession = DaoMaster.newDevSession(context, CreatOrderBeanDao.TABLENAME);
        CreatOrderBeanDao creatOrderBeanDao = daoSession.getCreatOrderBeanDao();
        creatOrderBeanDao.deleteAll();
        creatOrderBeanDao.insertOrReplace(creatOrderBean);
    }

    /**
     * 重新支付
     */
    public PayBean getNewPayInfo(Context context) {
        DaoSession daoSession = DaoMaster.newDevSession(context, PayBeanDao.TABLENAME);
        PayBeanDao payBeanDao = daoSession.getPayBeanDao();
        List<PayBean> beanList = payBeanDao.loadAll();
        if (beanList.size() > 0) {
            PayBean payBean = beanList.get(0);
            return payBean;
        }
        return null;
    }

    /**
     * 添加重新支付数据库
     *
     * @param context
     * @param PayBeanDao
     */
    public void setNewPayInfo(Context context, PayBean payBean) {
        DaoSession daoSession = DaoMaster.newDevSession(context, PayBeanDao.TABLENAME);
        PayBeanDao payBeanDao = daoSession.getPayBeanDao();
        payBeanDao.deleteAll();
        payBeanDao.insertOrReplace(payBean);
    }
}
