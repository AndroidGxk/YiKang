package com.yikangcheng.admin.yikang.presenter;

import com.yikangcheng.admin.yikang.base.BasePresenter;
import com.yikangcheng.admin.yikang.model.http.ApiService;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.model.http.NotWorkUtils;

import io.reactivex.Observable;
import retrofit2.http.Query;

/**
 * Created by lenovo on 2019/5/23.
 * WF
 */
public class UpdateAddressPresenter extends BasePresenter {
    public UpdateAddressPresenter(ICoreInfe dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        ApiService apiService = NotWorkUtils.getInstance().create(ApiService.class);
        return apiService.updateAddress((int) args[0], (int) args[1], (String) args[2], (String) args[3], (String) args[4], (int) args[5], (int) args[6], (int) args[7], (int) args[8]);
    }
}
