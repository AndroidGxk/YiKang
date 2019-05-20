package com.yikangcheng.admin.yikang.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.CloseRecyclerAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

/**
 * 结算页面
 */
public class CloseActivity extends BaseActivtiy {

    private RecyclerView recycler;
    private RelativeLayout rela4;
    private int width;
    private Dialog bottomDialog;
    private int height;
    private View sele_pay_digo;
    private ImageView buy_img;
    private RelativeLayout wechat_pay, zhi_rela;
    //选中未选中
    private boolean wechat = true, zhi = false;

    @Override
    protected void initView() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.clolrBAai);
        recycler = findViewById(R.id.recycler);
        buy_img = findViewById(R.id.buy_img);
        rela4 = findViewById(R.id.rela4);
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        sele_pay_digo = LayoutInflater.from(this).inflate(R.layout.sele_pay_digo, null);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(new CloseRecyclerAdapter(this));
        //解决滑动不流畅
        recycler.setHasFixedSize(true);
        recycler.setNestedScrollingEnabled(false);
        bottomDialog = new Dialog(this, R.style.BottomDialog);
        wechat_pay = sele_pay_digo.findViewById(R.id.wechat_pay);
        zhi_rela = sele_pay_digo.findViewById(R.id.zhi_rela);
        wechat_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wechat) {
                    return;
                } else {
                    wechat = true;
                    zhi = false;
                    wechat_pay.setBackgroundResource(R.drawable.yellow_pay_shape);
                    zhi_rela.setBackgroundResource(R.drawable.gray_pay_shape);
                }
            }
        });
        zhi_rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (zhi) {
                    return;
                } else {
                    zhi_rela.setBackgroundResource(R.drawable.yellow_pay_shape);
                    wechat_pay.setBackgroundResource(R.drawable.gray_pay_shape);
                    zhi = true;
                    wechat = false;
                }
            }
        });
    }

    @Override
    protected void initEventData() {
        /**
         * 选择支付方式
         */
        rela4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置dialog的宽高为屏幕的宽高
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, height - 340 * 2);
                bottomDialog.setContentView(sele_pay_digo, layoutParams);
                bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
                bottomDialog.setCanceledOnTouchOutside(true);
                bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
                bottomDialog.show();
            }
        });
        /***
         * 立即购买
         */
        buy_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CloseActivity.this, ConfirmActivity.class));
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_close;
    }

    @Override
    protected void createPresenter() {

    }
}
