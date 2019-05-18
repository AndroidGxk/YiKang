package com.yikangcheng.admin.yikang.model.http;


/**
 * 作者：古祥坤 on 2019/2/18 15:22
 * 邮箱：1724959985@qq.com
 */
public interface ICoreInfe<T> {
    void success(T data);
    void fail(ApiException e);
}
