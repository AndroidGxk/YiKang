package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.Toast;

import com.yikangcheng.admin.yikang.R;

public class SearchListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 000);
    }
}
