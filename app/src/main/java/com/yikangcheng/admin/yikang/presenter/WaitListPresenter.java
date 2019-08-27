package com.yikangcheng.admin.yikang.presenter;

import com.yikangcheng.admin.yikang.base.BasePresenter;
import com.yikangcheng.admin.yikang.model.http.ApiService;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.model.http.NotWorkUtils;

import io.reactivex.Observable;

/**
 * 作者：古祥坤 on 2019/7/28 21:15
 * 邮箱：1724959985@qq.com
 * 评论列表
 */
public class WaitListPresenter extends BasePresenter {
    public WaitListPresenter(ICoreInfe dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        ApiService apiService = NotWorkUtils.getInstance().create(ApiService.class);
        return apiService.waitAssesslist((int) args[0], (int) args[1]);
    }
}
