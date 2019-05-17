package com.yikangcheng.admin.yikang.base.contract;

import com.yikangcheng.admin.yikang.base.IPresenter;
import com.yikangcheng.admin.yikang.base.IView;

import java.util.Map;

//拓展V层和P层的方法
public interface Contract {

     interface View extends IView {
        void showProgressBar();

        void dissProgressBar();
    }

     interface Presenter extends IPresenter {
         void start(Map<String, String> scuessMap);

         void start(Object json);
    }

}
