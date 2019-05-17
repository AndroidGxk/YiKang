package com.yikangcheng.admin.yikang.presenter;

import com.google.gson.Gson;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Fen;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.base.contract.Contract;
import com.yikangcheng.admin.yikang.classify.ClassifyBean;
import com.yikangcheng.admin.yikang.model.http.RxJavaDataImp;

import java.io.IOException;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by lenovo on 2019/5/17.
 * WF
 */

public class ClassifyPresenter implements Contract.Presenter{
    private Fragment_Fen mFragment_fen;
    private RxJavaDataImp mRxJavaDataImp;

    public ClassifyPresenter(Fragment_Fen fragment_fen) {
        mFragment_fen = fragment_fen;
        mRxJavaDataImp = new RxJavaDataImp();
    }

    @Override
    public void start() {
        mRxJavaDataImp.postData(Constants.BASEURL + "/api/classification/all", new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    ClassifyBean classifyBean = new Gson().fromJson(string, ClassifyBean.class);
                    mFragment_fen.showSucess(classifyBean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                mFragment_fen.showError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void start(Map<String, String> scuessMap) {

    }

    @Override
    public void start(Object json) {

    }

}
