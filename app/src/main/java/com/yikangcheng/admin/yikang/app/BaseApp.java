package com.yikangcheng.admin.yikang.app;

import android.app.Activity;
import android.app.Application;

import java.util.HashSet;
import java.util.Set;

import me.jessyan.autosize.internal.CustomAdapt;


public class BaseApp extends Application  {
    private static BaseApp mAppInstance;
    private Set<Activity> mSet;


    public static synchronized BaseApp getAppInstance() {
        return mAppInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppInstance = this;
    }

    public void addActivtiy(Activity activity) {
        if (mSet == null) {
            mSet = new HashSet<Activity>();
        }
        mSet.add(activity);
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


}
