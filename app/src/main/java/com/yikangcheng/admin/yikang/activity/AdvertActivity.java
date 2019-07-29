package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.sqlite.dao.DaoMaster;
import com.example.sqlite.dao.DaoSession;
import com.example.sqlite.dao.LoginBeanDao;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.AdvertBean;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.AdvertPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * 启动页广告显示
 */
public class AdvertActivity extends BaseActivtiy implements ICoreInfe {
    private AdvertPresenter advertPresenter;

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tiaoguo)
    TextView tiaoguo;
    private int count = 5;
    private LoginBean loginBean;
    Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                count--;
                if (count > 0) {
                    handler.sendEmptyMessageDelayed(1, 1000);
                } else {
                    DaoSession daoSession = DaoMaster.newDevSession(AdvertActivity.this, LoginBeanDao.TABLENAME);
                    LoginBeanDao loginBeanDao = daoSession.getLoginBeanDao();
                    List<LoginBean> list = loginBeanDao.queryBuilder().where(LoginBeanDao.Properties.Status.eq("1"))
                            .build().list();
                    if (list.size() > 0) {
                        loginBean = list.get(0);
                    }
                    if (loginBean == null) {
                        startActivity(new Intent(AdvertActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(AdvertActivity.this, ApplySeleActivity.class));
                        finish();
                    }
                }
            }
        }
    };
    private String path;

    @Override
    protected void initView() {
        handler.sendEmptyMessage(1);
    }

    @Override
    protected void initEventData() {
        advertPresenter = new AdvertPresenter(this);
        advertPresenter.request();
        tiaoguo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeMessages(1);
                DaoSession daoSession = DaoMaster.newDevSession(AdvertActivity.this, LoginBeanDao.TABLENAME);
                LoginBeanDao loginBeanDao = daoSession.getLoginBeanDao();
                List<LoginBean> list = loginBeanDao.queryBuilder().where(LoginBeanDao.Properties.Status.eq("1"))
                        .build().list();
                if (list.size() > 0) {
                    loginBean = list.get(0);
                }
                if (loginBean == null) {
                    startActivity(new Intent(AdvertActivity.this, LoginActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(AdvertActivity.this, ApplySeleActivity.class));
                    finish();
                }
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_advert;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void success(Object data) {
        Request beans = (Request) data;
        List<AdvertBean> advertBeanList = (List<AdvertBean>) beans.getEntity();
        if (advertBeanList != null) {
            path = advertBeanList.get(0).getImagesUrl();
            if (path == null && path.equals("")) {
            } else {
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.placeholder(R.drawable.guanggaoimg);
                requestOptions.fallback(R.drawable.guanggaoimg);
                Glide.with(AdvertActivity.this).load(Constants.BASETUPIANSHANGCHUANURL + path)
                        .apply(requestOptions)
                        .into(img);
            }
        } else {
        }
    }


    @Override
    public void fail(ApiException e) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.guanggaoimg);
        requestOptions.fallback(R.drawable.guanggaoimg);
        Glide.with(AdvertActivity.this).load(Constants.BASETUPIANSHANGCHUANURL + path)
                .apply(requestOptions)
                .into(img);
    }

}
