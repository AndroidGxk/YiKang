package com.yikangcheng.admin.yikang.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.sqlite.dao.DaoMaster;
import com.example.sqlite.dao.DaoSession;
import com.example.sqlite.dao.UserDetailBeanDao;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.fragment.introduction.Introduction_four;
import com.yikangcheng.admin.yikang.activity.fragment.introduction.Introduction_one;
import com.yikangcheng.admin.yikang.activity.fragment.introduction.Introduction_three;
import com.yikangcheng.admin.yikang.activity.fragment.introduction.Introduction_two;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.UserDetailBean;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

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
                    DaoSession daoSession = DaoMaster.newDevSession(WelcomeActivity.this, UserDetailBeanDao.TABLENAME);
                    UserDetailBeanDao userDetailBeanDao = daoSession.getUserDetailBeanDao();
//        List<UserDetailBean> list = userDetailBeanDao.queryBuilder().where(UserDetailBeanDao.Properties.Statu.eq("1"))
//                .build().list();
                    List<UserDetailBean> list = userDetailBeanDao.loadAll();
                    if (list.size() > 0) {
                        userDetailBean = list.get(0);
                    }
                    if (userDetailBean == null) {
                        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                    } else {
                        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    }
                    finish();
                }

            }
        }
    };
    private UserDetailBean userDetailBean;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);//恢复原有的样式
        setContentView(R.layout.activity_welcome);
        if (ContextCompat.checkSelfPermission(WelcomeActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(WelcomeActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(WelcomeActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            ActivityCompat.requestPermissions(WelcomeActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
        } else {

        }
        viewpage = (ViewPager) findViewById(R.id.viewpage);
        image = (ImageView) findViewById(R.id.image);
        SharedPreferences startapp = getSharedPreferences("stratapp", MODE_PRIVATE);
        String start = startapp.getString("stratapp", "fasle");
        if (start.equals("true")) {
            if (record != 1) {
                viewpage.setVisibility(View.GONE);
                image.setVisibility(View.VISIBLE);
                setRecord();
            }
            handler.sendEmptyMessage(1);
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
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //这里已经获取到了摄像头的权限，想干嘛干嘛了可以
                } else {
                    //这里是拒绝给APP摄像头权限，给个提示什么的说明一下都可以
                    // 。
                    if (ContextCompat.checkSelfPermission(WelcomeActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                            ContextCompat.checkSelfPermission(WelcomeActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    ) {
                        // 申请权限
                        ActivityCompat.requestPermissions(WelcomeActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    }
                }
                break;
            default:
                break;
        }

    }
}
