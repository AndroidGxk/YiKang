package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.SelectCouponRecyclerAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.CouponusableBean;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.CouponPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.List;

public class SelectCouponActivity extends BaseActivtiy implements ICoreInfe {

    private SelectCouponRecyclerAdapter selectCouponRecyclerAdapter;
    private ImageView youhuiquan_null, back_img;
    private CouponPresenter couponPresenter;
    private LoginBean loginBean;
    private RecyclerView recycler;
    private double moneyDouble;

    @Override
    protected void initView() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorTab);
        Intent intent = getIntent();
        String money = intent.getStringExtra("money");
        String[] split = money.split("¥");
        moneyDouble = Double.parseDouble(split[1]);
        back_img = (ImageView) findViewById(R.id.back_img);
        youhuiquan_null = (ImageView) findViewById(R.id.youhuiquan_null);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        couponPresenter = new CouponPresenter(this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        selectCouponRecyclerAdapter = new SelectCouponRecyclerAdapter(this);
        recycler.setAdapter(selectCouponRecyclerAdapter);
        loginBean = getLogUser(this);
    }

    @Override
    protected void initEventData() {
        /**
         * 退出
         */
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (loginBean != null) {
            couponPresenter.request(loginBean.getId(), moneyDouble);
        }
        selectCouponRecyclerAdapter.setOnClickListener(new SelectCouponRecyclerAdapter.onClickListener() {
            @Override
            public void onClick(int id, int amount) {
                Intent intent = new Intent();
                intent.putExtra("id", id);
                intent.putExtra("amount", amount);
                /*
                 * 调用setResult方法表示我将Intent对象返回给之前的那个Activity，这样就可以在onActivityResult方法中得到Intent对象，
                 */
                setResult(2001, intent);
                //    结束当前这个Activity对象的生命
                finish();
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_select_coupon;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        List<CouponusableBean> couponusableBeans = (List<CouponusableBean>) request.getEntity();
        if (couponusableBeans != null) {
            for (int i = 0; i < 3; i++) {
                selectCouponRecyclerAdapter.addAll(couponusableBeans);
            }
            youhuiquan_null.setVisibility(View.GONE);
        }
    }

    @Override
    public void fail(ApiException e) {
        youhuiquan_null.setVisibility(View.GONE);
    }
}
