package com.yikangcheng.admin.yikang.base.contract;

import com.yikangcheng.admin.yikang.base.IPresenter;
import com.yikangcheng.admin.yikang.base.IView;

/**
 * 爱生活，爱代码
 * 创建于：2019/3/4 14:52
 * 作 者：T
 * 微信：704003376
 */
//拓展V层和P层的方法
public class Contract {

    public interface View extends IView {
        void showProgressBar();

        void dissProgressBar();
    }

    public interface Presenter extends IPresenter {
        void start();
    }

}
