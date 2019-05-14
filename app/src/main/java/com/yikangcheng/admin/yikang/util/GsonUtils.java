package com.yikangcheng.admin.yikang.util;


import com.google.gson.Gson;

/**
 * 爱生活，爱代码
 * 创建于：2019/2/20 18:18
 * 作 者：T
 * 微信：704003376
 */
public class GsonUtils {

    public static <T> T gsonStrToBean(String jsonStr, Class<T> tClass) {
        Gson gson = new Gson();
        T t = gson.fromJson(jsonStr, tClass);
        return t;
    }
}
