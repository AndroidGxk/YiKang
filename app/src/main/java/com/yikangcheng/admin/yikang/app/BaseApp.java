package com.yikangcheng.admin.yikang.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.content.Context;

import com.example.sqlite.dao.DaoMaster;
import com.example.sqlite.dao.DaoSession;
import com.example.sqlite.dao.UserDetailBeanDao;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.sobot.chat.SobotApi;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.bean.UserDetailBean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class BaseApp extends Application {
    public static BaseApp mAppInstance;
    private Set<Activity> mSet;
    /**
     * context 全局唯一的上下文
     */
    private static Context context;
    /**
     * 记录处于前台的Activity
     */
    private static BaseApp mForegroundActivity = null;
    /**
     * 主线程ID
     */
    private static int mMainThreadId = -1;
    /**
     * 主线程ID
     */
    private static Thread mMainThread;
    /**
     * 主线程Handler
     */
    private static Handler mMainThreadHandler;
    /**
     * 主线程Looper
     */
    private static Looper mMainLooper;

    public static synchronized BaseApp getAppInstance() {
        return mAppInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppInstance = this;

        SobotApi.initSobotSDK(this, "7560599b63bf43378d05d018ded42cdd", "");
    }

    public void addActivtiy(Activity activity) {
        if (mSet == null) {
            mSet = new HashSet<Activity>();
        }
        mSet.add(activity);
    }

    /**
     * 获取当前处于前台的activity
     */
    public static BaseApp getForegroundActivity() {
        return mForegroundActivity;
    }

    public void removeActivity(Activity activity) {
        if (mSet != null)
            mSet.remove(activity);
    }


    public void exit(Activity activity) {
        if (mSet != null) {
            synchronized (this) {
                for (Activity activity1 : mSet) {
                    activity1.finish();
                }

            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /**
     * @return 全局唯一的上下文
     * @author: 康海涛 QQ2541849981
     * @describe: 获取全局Application的上下文
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取主线程ID
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * smartRefershLayout智能刷新加载框架防泄漏
     * ==
     */
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    /**
     * 获取主线程
     */
    public static Thread getMainThread() {
        return mMainThread;
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * 获取主线程的looper
     */
    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }


}
