package com.yikangcheng.admin.yikang.presenter;

import com.yikangcheng.admin.yikang.base.BasePresenter;
import com.yikangcheng.admin.yikang.model.http.ApiService;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.model.http.NotWorkUtils;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2019/5/17.
 * WF
 */

public class InsertAddressPresenter extends BasePresenter {

    public InsertAddressPresenter(ICoreInfe dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        ApiService apiService = NotWorkUtils.getInstance().create(ApiService.class);
        return apiService.insertAddress((int) args[0], (String) args[1], (String) args[2], (String) args[3], (int) args[4], (int) args[5], (int) args[6], (int) args[7]);
    }
}
