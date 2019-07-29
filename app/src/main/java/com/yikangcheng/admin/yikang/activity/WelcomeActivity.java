package com.yikangcheng.admin.yikang.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.sqlite.dao.DaoMaster;
import com.example.sqlite.dao.DaoSession;
import com.example.sqlite.dao.LoginBeanDao;
import com.hjq.toast.ToastUtils;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.fragment.introduction.Introduction_one;
import com.yikangcheng.admin.yikang.activity.fragment.introduction.Introduction_three;
import com.yikangcheng.admin.yikang.activity.fragment.introduction.Introduction_two;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.AdvertBean;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.AdvertPresenter;
import com.yikangcheng.admin.yikang.util.PermissionsActivity;
import com.yikangcheng.admin.yikang.util.PermissionsChecker;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;
import com.yikangcheng.admin.yikang.util.file.ToImg3;
import com.yikangcheng.admin.yikang.util.file.ToImg4;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import me.leefeng.promptlibrary.PromptDialog;

import static com.facebook.common.internal.ByteStreams.copy;

public class WelcomeActivity extends BaseActivtiy implements ICoreInfe {

    private static final int REQUEST_CODE = 0; // 请求码
    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CAMERA
    };
    private PermissionsChecker mPermissionsChecker; // 权限检测器

    private ViewPager viewpage;
    List<Fragment> list_frag = new ArrayList<>();
    private int count = 3;
    private static int record;
    private LoginBean loginBean;
    private int countper = 0;
    Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                count--;
                if (count > 0) {
                    handler.sendEmptyMessageDelayed(1, 1000);
                } else {
                    startActivity(new Intent(WelcomeActivity.this, AdvertActivity.class));
                    finish();
                }

            } else if (msg.what == 0) {
                ToastUtils.show(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/test.jpg").toString());
            }
        }
    };

    private ImageView image;
    private PromptDialog promptDialog;
    private AdvertPresenter advertPresenter;

    @Override
    protected void initView() {
        closeSwipeBack();
        mPermissionsChecker = new PermissionsChecker(this);
        //创建对象
        promptDialog = new PromptDialog(this);
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);
        viewpage = (ViewPager) findViewById(R.id.viewpage);
        image = (ImageView) findViewById(R.id.image);
        Introduction_one introduction_one = new Introduction_one();
        Introduction_two introduction_two = new Introduction_two();
        Introduction_three introduction_three = new Introduction_three();
        list_frag.add(introduction_one);
        list_frag.add(introduction_two);
        list_frag.add(introduction_three);
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
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.transparent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            startPermissionsActivity();
        } else {
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
        }
    }

    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        }
    }


    @Override
    protected void initEventData() {
        advertPresenter = new AdvertPresenter(this);
        advertPresenter.request();
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void createPresenter() {

    }

    public static void setRecord() {
        record = 1;
    }

    @Override
    public void success(Object data) {
        Request beans = (Request) data;
        List<AdvertBean> advertBeanList = (List<AdvertBean>) beans.getEntity();
        if (advertBeanList != null) {
            final String path = advertBeanList.get(0).getImagesUrl();
            if (path == null && path.equals("")) {
            } else {

            }
        } else {
        }
    }


    @Override
    public void fail(ApiException e) {
    }


}
