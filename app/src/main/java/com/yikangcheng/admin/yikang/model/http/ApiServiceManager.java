package com.yikangcheng.admin.yikang.model.http;

import android.os.Environment;

import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.util.SDCardUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


public class ApiServiceManager {
    private static ApiServiceManager mManager;
    private Retrofit mRetrofit;
    //20M的缓存
    private static final long MAX_CACHE_SIZE = 1024 * 1024 * 20;

    private ApiServiceManager() {
        initRetrofit();
    }

    private void initRetrofit() {
        File cache_file = null;
        if (SDCardUtil.isSCardAvailable(MAX_CACHE_SIZE)) {
            String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            cache_file = new File(rootPath, "cache");
            // /mnt/sdcard/cache  SDCARD目录(所有程序都可以访问该目录)
        } else {
            cache_file = BaseApp.getAppInstance().getCacheDir();
            //  /data/data/包名/cache  内存目录(只有当前程序可以访问)
        }
        Cache cache = new Cache(cache_file, MAX_CACHE_SIZE);

        OkHttpClient client = new OkHttpClient.Builder().
                connectTimeout(10, TimeUnit.SECONDS).
                readTimeout(10, TimeUnit.SECONDS).
                cache(cache).
                build();

        mRetrofit = new Retrofit.Builder().
                baseUrl(Constants.BASEURL).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                client(client).build();
    }


    public static ApiServiceManager getManagerInstance() {
        if (mManager == null) {
            synchronized (ApiServiceManager.class) {

                if (mManager == null) {

                    mManager = new ApiServiceManager();
                }
            }
        }
        return mManager;
    }


    public ApiService getApiService() {
        return mRetrofit.create(ApiService.class);
    }

}
