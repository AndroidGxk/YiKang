package com.yikangcheng.admin.yikang.presenter;

import com.yikangcheng.admin.yikang.base.BasePresenter;
import com.yikangcheng.admin.yikang.base.BasePresenterImage;
import com.yikangcheng.admin.yikang.model.http.ApiService;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.model.http.NotWorkUtilsImage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.MultipartBody.Part;
import okhttp3.RequestBody;

/**
 * Created by lenovo on 2019/5/23.
 * 上传图片
 */
public class GoswfPresenter extends BasePresenterImage {
    public GoswfPresenter(ICoreInfe dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        ApiService apiService = NotWorkUtilsImage.getInstance().create(ApiService.class);
        File fileupload = (File) args[0];
        List<MultipartBody.Part> lists = new ArrayList<>();
        lists.add(MultipartBody.Part.createFormData("token", fileupload.getPath()));//上传 字段和值
        File file = new File(fileupload.getPath());//需要上传的文件的地址
        if (!file.exists()) {//文件不存在
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("fileupload", fileupload.getName(), requestBody);
        lists.add(part);
        return apiService.goswf(lists);
    }
}
