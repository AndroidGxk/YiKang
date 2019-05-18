package com.yikangcheng.admin.yikang.app;

import android.app.Activity;
import android.app.Application;

import com.sobot.chat.SobotApi;

import java.util.HashSet;
import java.util.Set;


public class BaseApp extends Application {
    public static BaseApp mAppInstance;
    private Set<Activity> mSet;


    public static synchronized BaseApp getAppInstance() {
        return mAppInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppInstance = this;
        SobotApi.initSobotSDK(this,"7560599b63bf43378d05d018ded42cdd","");
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
