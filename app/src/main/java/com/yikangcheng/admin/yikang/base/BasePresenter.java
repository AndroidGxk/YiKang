package com.yikangcheng.admin.yikang.base;


import com.yikangcheng.admin.yikang.base.contract.Contract;

import java.util.Map;

public class BasePresenter implements Contract.Presenter {
    private IView mView;

    @Override
    public void start(Map<String, String> scuessMap) {

    }

    @Override
    public void start(Object json) {

    }


    //对外界提供View对象
    public IView getmView() {
        return mView;
    }

    //检查V层是否被挂载(当前View是否和Activtiy或者Fragment的生命周期关联起来)
    public void checkViewAttached() {
        //如果没有被挂载  就抛出异常
        if (!isViewAttached()) try {
            throw new MvpViewNotAttachedException();
        } catch (MvpViewNotAttachedException e) {
            e.printStackTrace();
        }
    }

    private boolean isViewAttached() {
        return mView != null;
    }

    @Override
    public void start() {

    }

    public void atteachView(IView iView) {
        this.mView = iView;
    }

    public void detachView() {
        mView = null;
    }


    //自定义异常
    public class MvpViewNotAttachedException extends Throwable {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
