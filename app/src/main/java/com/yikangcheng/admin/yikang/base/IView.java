package com.yikangcheng.admin.yikang.base;

/**
 * 爱生活，爱代码
 * 创建于：2019/3/4 10:39
 * 作 者：T
 * 微信：704003376
 */
public interface IView {
    void showError(String msg);
    <T> void showSucess(T t);
}
