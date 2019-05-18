package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.ClassifyListOneBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.TestPersenter;

import java.util.List;

public class RegisterActivity extends BaseActivtiy implements ICoreInfe {


    private ImageView log_image;
    private TestPersenter testPersenter;

    @Override
    protected void initView() {
        log_image = findViewById(R.id.log_image);
        testPersenter = new TestPersenter(this);
    }

    @Override
    protected void initEventData() {
        log_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                testPersenter.request();
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        List<ClassifyListOneBean> entity = (List<ClassifyListOneBean>) request.getEntity();
        Toast.makeText(this, ""+entity.get(0).getChildSubjectList().get(0).getSubjectName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fail(ApiException e) {

    }
}
