package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.fragment.introduction.Introduction_four;
import com.yikangcheng.admin.yikang.activity.fragment.introduction.Introduction_one;
import com.yikangcheng.admin.yikang.activity.fragment.introduction.Introduction_three;
import com.yikangcheng.admin.yikang.activity.fragment.introduction.Introduction_two;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends BaseActivtiy {

    private ViewPager viewpage;
    List<Fragment> list_frag = new ArrayList<>();
    private int count = 3;
    private static int record;
    Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                count--;
                if (count > 0) {
                    handler.sendEmptyMessageDelayed(1, 1000);
                } else {
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    finish();
                }

            }
        }
    };

    @Override
    protected void initView() {
        viewpage = findViewById(R.id.viewpage);
        SharedPreferences startapp = getSharedPreferences("stratapp", MODE_PRIVATE);
        String start = startapp.getString("stratapp", "fasle");
        if (start.equals("true")) {
            if (record != 1) {
                viewpage.setVisibility(View.GONE);
                handler.sendEmptyMessage(1);
                setRecord();
            }
            return;
        }
        Introduction_one introduction_one = new Introduction_one();
        Introduction_two introduction_two = new Introduction_two();
        Introduction_three introduction_three = new Introduction_three();
        Introduction_four introduction_four = new Introduction_four();
        list_frag.add(introduction_one);
        list_frag.add(introduction_two);
        list_frag.add(introduction_three);
        list_frag.add(introduction_four);
        viewpage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list_frag.get(i);
            }

            @Override
            public int getCount() {
                return list_frag.size();
            }

        });
    }

    public static void setRecord() {
        record = 1;
    }

    @Override
    protected void initEventData() {

    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void createPresenter() {

    }
}
