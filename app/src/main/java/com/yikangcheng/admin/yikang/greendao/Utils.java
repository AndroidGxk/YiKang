package com.yikangcheng.admin.yikang.greendao;

import com.example.sqlite.dao.DaoMaster;
import com.example.sqlite.dao.DaoSession;
import com.example.sqlite.dao.SoSuoDbDao;
import com.yikangcheng.admin.yikang.app.BaseApp;

import java.util.List;

/**
 * Created by lenovo on 2019/5/16.
 * WF
 */
public class Utils {
    private static Utils mUtil;
    private final SoSuoDbDao mUtilBeanDao;

    private Utils() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(BaseApp.mAppInstance, "Demo.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getReadableDb());
        DaoSession daoSession = daoMaster.newSession();
        mUtilBeanDao = daoSession.getSoSuoDbDao();
    }

    public static Utils getInstance() {
        if (mUtil == null) {
            synchronized (Utils.class) {
                if (mUtil == null) {
                    mUtil = new Utils();
                }
            }
        }
        return mUtil;
    }

    //添加
    public void insent(List<SoSuoDb> list) {
        mUtilBeanDao.insertInTx(list);
    }

    //删除所有
    public void deleAAll() {
        mUtilBeanDao.deleteAll();
    }

    //删除数据
    public void delete(List<SoSuoDb> list, int position) {
        mUtilBeanDao.delete(list.get(position));
    }

    //修改
    private void update(SoSuoDb bean) {
        mUtilBeanDao.update(bean);
    }

    //查询
    public List<SoSuoDb> select() {
        return mUtilBeanDao.queryBuilder().list();
    }
}
