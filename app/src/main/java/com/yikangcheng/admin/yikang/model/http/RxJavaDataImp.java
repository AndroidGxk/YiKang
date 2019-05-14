package com.yikangcheng.admin.yikang.model.http;

import android.text.TextUtils;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


//关联Retrofit和RxJava的
public class RxJavaDataImp {

    public void getData(String url, Observer<ResponseBody> observer) {
        if (!TextUtils.isEmpty(url)) {
            ApiServiceManager.getManagerInstance().getApiService().getData(url).
                    subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).
                    subscribe(observer);
        } else
            throw new IllegalArgumentException("url请求地址不能为空");

    }

    public void getData(String url, Map<String, Object> paramsMap, Observer<ResponseBody> observer) {
        if (!TextUtils.isEmpty(url)) {
            if (paramsMap != null && paramsMap.size() > 0) {
                ApiServiceManager.getManagerInstance().getApiService().
                        getData(url, paramsMap).
                        subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe(observer);
            } else if (paramsMap.size() == 0 || paramsMap == null) {
                getData(url, observer);
            }
        } else
            throw new IllegalArgumentException("url请求地址不能为空");

    }

    //有请求头和请求体的post请求
    public void postData(String url, Map<String, Object> headersMap, Map<String, Object> paramsMap, Observer<ResponseBody> observer) {
        if (!TextUtils.isEmpty(url)) {
            if (headersMap != null && headersMap.size() > 0) {
                ApiServiceManager.getManagerInstance().getApiService().
                        postData(headersMap, url, paramsMap).
                        subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe(observer);
            } else {
                postData(url, paramsMap, observer);
            }
        } else {
            throw new IllegalArgumentException("url请求地址不能为空");
        }

    }


    //没有请求头但有请求体的post请求
    public void postData(String url, Map<String, Object> paramsMap, Observer<ResponseBody> observer) {
        if (!TextUtils.isEmpty(url)) {
            if (paramsMap != null && paramsMap.size() > 0) {
                ApiServiceManager.getManagerInstance().getApiService().
                        postData(url, paramsMap).
                        subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe(observer);
            } else {
                postData(url, observer);
            }
        } else {
            throw new IllegalArgumentException("url请求地址不能为空");
        }

    }


    //没有请求头没有请求体的post请求
    public void postData(String url, Observer<ResponseBody> observer) {
        if (!TextUtils.isEmpty(url)) {
            ApiServiceManager.getManagerInstance().getApiService().
                    postData(url).
                    subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).
                    subscribe(observer);
        } else {
            throw new IllegalArgumentException("url请求地址不能为空");
        }
    }


    public void postData(String url, RequestBody body, Observer<ResponseBody> observer) {
        if (!TextUtils.isEmpty(url)) {

            ApiServiceManager.getManagerInstance().getApiService().
                    postData(url, body).subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).
                    subscribe(observer);
        } else
            throw new IllegalArgumentException("url请求地址不能为空");

    }

    public void postData(String url, Map<String, Object> headersMap, RequestBody body, Observer<ResponseBody> observer) {
        if (!TextUtils.isEmpty(url)) {
            if (headersMap != null && headersMap.size() > 0) {
                ApiServiceManager.getManagerInstance().getApiService().
                        postData(headersMap, url, body).
                        subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe(observer);
            } else {
                postData(url, observer);
            }
        } else
            throw new IllegalArgumentException("url请求地址不能为空");

    }


    //文件下载
    public void downloadFile(String url, Observer<ResponseBody> observer) {
        if (!TextUtils.isEmpty(url)) {
            ApiServiceManager.getManagerInstance().
                    getApiService().
                    downloadFile(url).
                    subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).
                    subscribe(observer);
        } else
            throw new IllegalArgumentException("url请求地址不能为空");
    }


    //文件上传
    public void uploadFile(String uploadUrl, MultipartBody.Part part, Observer<ResponseBody> observer) {
        if (!TextUtils.isEmpty(uploadUrl)) {
            ApiServiceManager.getManagerInstance().
                    getApiService().
                    uploadFile(uploadUrl, part).
                    subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).
                    subscribe(observer);
        } else
            throw new IllegalArgumentException("url请求地址不能为空");


    }


}
