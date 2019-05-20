package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;

/**
 * 搜索列表页面
 */
public class SeekListActivity extends BaseActivtiy {

    private EditText edit_seek_sousuo;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        String count = intent.getStringExtra("count");
        //搜素框
        edit_seek_sousuo = findViewById(R.id.EditTixt_activity_seek_sousuo);
        edit_seek_sousuo.setText(count);
    }


    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_seek_list;
    }

    @Override
    protected void createPresenter() {

    }
}
