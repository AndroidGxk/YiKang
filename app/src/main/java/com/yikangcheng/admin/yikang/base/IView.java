package com.yikangcheng.admin.yikang.base;

public interface IView<T> {
    void showError(String msg);

    void showSucess(T t);
}
