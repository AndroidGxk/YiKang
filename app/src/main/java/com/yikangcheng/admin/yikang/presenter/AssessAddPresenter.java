package com.yikangcheng.admin.yikang.presenter;

import com.yikangcheng.admin.yikang.base.BasePresenter;
import com.yikangcheng.admin.yikang.model.http.ApiService;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.model.http.NotWorkUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 作者：古祥坤 on 2019/7/28 21:15
 * 邮箱：1724959985@qq.com
 * 添加评论
 */
public class AssessAddPresenter extends BasePresenter {
    public AssessAddPresenter(ICoreInfe dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        ApiService apiService = NotWorkUtils.getInstance().create(ApiService.class);

        return apiService.assessadd((int) args[0], (int) args[1], (int) args[2], (String) args[3], (String) args[4], (String) args[5], (String) args[6]);
    }
}
