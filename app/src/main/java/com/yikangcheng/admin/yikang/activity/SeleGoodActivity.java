package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.aftersale.AfterSaleActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;

public class SeleGoodActivity extends BaseActivtiy {

    private RelativeLayout rela, rela1;
    private int id;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 00);
        //换货
        rela = (RelativeLayout) findViewById(R.id.rela);
        //退货
        rela1 = (RelativeLayout) findViewById(R.id.rela1);
    }

    @Override
    protected void initEventData() {
        //换货
        rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeleGoodActivity.this, AfterSaleActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        //退货
        rela1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeleGoodActivity.this, BarterActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_sele_good;
    }

    @Override
    protected void createPresenter() {

    }
}
