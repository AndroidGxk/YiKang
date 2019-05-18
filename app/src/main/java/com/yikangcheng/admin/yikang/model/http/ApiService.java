package com.yikangcheng.admin.yikang.model.http;

import com.yikangcheng.admin.yikang.bean.ClassifyListOneBean;
import com.yikangcheng.admin.yikang.bean.Request;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;

public interface ApiService {
    @POST("classification/all")
    Observable<Request<List<ClassifyListOneBean>>> classify();
}
