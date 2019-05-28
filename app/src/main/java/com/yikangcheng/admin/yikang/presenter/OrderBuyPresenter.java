package com.yikangcheng.admin.yikang.presenter;

import com.yikangcheng.admin.yikang.base.BasePresenter;
import com.yikangcheng.admin.yikang.model.http.ApiService;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.model.http.NotWorkUtils;

import io.reactivex.Observable;

/**
 * Created by lenovo on 2019/5/23.
 * 创建订单
 */
public class OrderBuyPresenter extends BasePresenter {
    public OrderBuyPresenter(ICoreInfe dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        ApiService apiService = NotWorkUtils.getInstance().create(ApiService.class);
        return apiService.orderbuy((int) args[0], (int) args[1], (int) args[2], (int) args[3], (int) args[4],
                (int) args[5], (String) args[6], (String) args[7], (String) args[8], (String) args[9], (String) args[10],
                (String) args[11]);
    }
}