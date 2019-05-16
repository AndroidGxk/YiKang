package com.yikangcheng.admin.yikang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.util.PriceUpDownView;

public class SearchListActivity extends AppCompatActivity implements PriceUpDownView.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);
        PriceUpDownView upDownView = (PriceUpDownView) findViewById(R.id.price_up_down_id);
        upDownView.setCallback(this);

    }

    @Override
    public void getStatus(boolean isUp) {
        Toast.makeText(this, isUp?"上":"下", Toast.LENGTH_SHORT).show();
    }
}
