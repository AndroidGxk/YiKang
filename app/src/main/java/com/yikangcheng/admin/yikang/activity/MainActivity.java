package com.yikangcheng.admin.yikang.activity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.callback.ConfigButton;
import com.mylhyl.circledialog.callback.ConfigDialog;
import com.mylhyl.circledialog.callback.ConfigText;
import com.mylhyl.circledialog.params.ButtonParams;
import com.mylhyl.circledialog.params.DialogParams;
import com.mylhyl.circledialog.params.TextParams;
import com.tianma.netdetector.lib.NetworkType;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Fen;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Gou;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Miao;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Shou;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Wo;
import com.yikangcheng.admin.yikang.activity.myaccount.MyaccountActivity;
import com.yikangcheng.admin.yikang.activity.obligation.DetailActivity;
import com.yikangcheng.admin.yikang.activity.siteactivity.AiteActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.AppUpdateBean;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.UserDetailBean;
import com.yikangcheng.admin.yikang.bean.UserInfoBean;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.CheckupdatePresenter;
import com.yikangcheng.admin.yikang.presenter.UserInfoPresenter;
import com.yikangcheng.admin.yikang.updater.Updater;
import com.yikangcheng.admin.yikang.updater.UpdaterConfig;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.leefeng.promptlibrary.OnAdClickListener;
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

public class MainActivity extends BaseActivtiy {
    private RadioGroup radio;
    private FragmentTransaction transaction;
    private RadioButton shou, fen, miao, gou, wo;
    private LinearLayout shou_linear, fen_linear, miao_linear,
            gou_linear, wo_linear, line1, line4, line3, line2, line5, line6;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mNv;
    private RelativeLayout mRelativeLayout;
    private TextView log_text;
    private TextView shou_text, fen_text, miao_text, gou_text, wo_text, my_name;
    private ImageView header;
    private Fragment_Miao fragment_miao;
    private TextView text_count;
    /**
     * 记录上次点击的页面
     */
    private int record_ac;
    private Fragment_Shou fragment_shou;
    private Fragment_Fen fragment_fen;
    private Fragment_Gou fragment_gou;
    private Fragment_Wo fragment_wo;
    private PromptDialog promptDialog;
    private UserInfoPresenter userInfoPresenter;
    private int width;
    private CheckupdatePresenter checkupdatePresenter;

    @Override
    protected void initView() {
        closeSwipeBack();
        //创建对象
        promptDialog = new PromptDialog(this);
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        userInfoPresenter = new UserInfoPresenter(new UserInfo());
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        width = wm.getDefaultDisplay().getWidth();
        radio = (RadioGroup) findViewById(R.id.radio_group);
        radio.check(R.id.shou);
        shou_linear = (LinearLayout) findViewById(R.id.shou_linear);
        fen_linear = (LinearLayout) findViewById(R.id.fen_linear);
        shou_text = (TextView) findViewById(R.id.shou_text);
        fen_text = (TextView) findViewById(R.id.fen_text);
        log_text = (TextView) findViewById(R.id.log_text);
        line1 = (LinearLayout) findViewById(R.id.line1);
        line4 = (LinearLayout) findViewById(R.id.line4);
        line2 = (LinearLayout) findViewById(R.id.line2);
        line5 = (LinearLayout) findViewById(R.id.line5);
        line3 = (LinearLayout) findViewById(R.id.line3);
        line6 = (LinearLayout) findViewById(R.id.line6);
        header = (ImageView) findViewById(R.id.header);
        text_count = (TextView) findViewById(R.id.text_count);
        my_name = (TextView) findViewById(R.id.my_name);
        miao_text = (TextView) findViewById(R.id.miao_text);
        gou_text = (TextView) findViewById(R.id.gou_text);
        wo_text = (TextView) findViewById(R.id.wo_text);
        miao_linear = (LinearLayout) findViewById(R.id.miao_linear);
        gou_linear = (LinearLayout) findViewById(R.id.gou_linear);
        wo_linear = (LinearLayout) findViewById(R.id.wo_linear);
        //我的账号
        line2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MyaccountActivity.class));
            }
        });
        //帮助中心
        line5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ApoutUsActivity.class));
            }
        });

        checkupdatePresenter = new CheckupdatePresenter(new UpdateApp());
        checkupdatePresenter.request("android");
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNv = (RelativeLayout) findViewById(R.id.nv);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        //抽屉滑出时,主界面被挤到右边
        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //抽屉滑动监听
                mRelativeLayout.setX(mNv.getWidth() * slideOffset);
            }
        });
        record_ac = 1;
        line6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promptDialog.showWarnAlert("确定要退出登录吗？", new PromptButton("取消", new PromptButtonListener() {
                    @Override
                    public void onClick(PromptButton button) {
                    }
                }), confirm);
            }
        });


    }

    //查询当前App版本号
    private String getVersionName() throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        String version = packInfo.versionName;
        return version;
    }

    //按钮的定义，创建一个按钮的对象
    final PromptButton confirm = new PromptButton("确定", new PromptButtonListener() {
        @Override
        public void onClick(PromptButton button) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            getDelete(MainActivity.this);
            finish();
        }
    });
//    //更新按钮
//    final PromptButton newConfirm = new PromptButton("更新", new PromptButtonListener() {
//        @Override
//        public void onClick(PromptButton button) {
//            Toast.makeText(MainActivity.this, "正在更新", Toast.LENGTH_SHORT).show();
//            UpdaterConfig config = new UpdaterConfig.Builder(MainActivity.this)
//                    .setTitle(getResources().getString(R.string.app_name))
//                    .setDescription("更新")
//                    .setFileUrl(APK_URL)
//                    .setCanMediaScanner(true)
//                    .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE
//                            | DownloadManager.Request.NETWORK_WIFI)
//                    .build();
//            Updater.get().showLog(true).download(config);
//        }
//    });

    /**
     * 购物车的商品数量
     */
    public void setCount(int count) {
        if (count == 0) {
            text_count.setVisibility(View.GONE);
        } else {
            text_count.setVisibility(View.VISIBLE);
            if (count >= 99) {
                text_count.setText("99+");
            } else {
                text_count.setText("" + count);
            }
        }
    }

    @Override
    protected boolean needRegisterNetworkChangeObserver() {
        return true;
    }

    @Override
    public void onNetConnected(NetworkType networkType) {
    }

    @Override
    public void onNetDisconnected() {
        Toast.makeText(this, "当前网络不可用", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initEventData() {
        transaction = getSupportFragmentManager().beginTransaction();
        fragment_shou = new Fragment_Shou();
        fragment_fen = new Fragment_Fen();
        fragment_miao = new Fragment_Miao();
        fragment_gou = new Fragment_Gou();
        fragment_wo = new Fragment_Wo();
        transaction.add(R.id.frag, fragment_shou);
        transaction.add(R.id.frag, fragment_fen);
        transaction.add(R.id.frag, fragment_miao);
        transaction.add(R.id.frag, fragment_gou);
        transaction.add(R.id.frag, fragment_wo);
        transaction.hide(fragment_fen);
        transaction.hide(fragment_miao);
        transaction.hide(fragment_gou);
        transaction.hide(fragment_wo);
        transaction.show(fragment_shou);
        transaction.commit();

        /**
         * 切换页面
         */
        shou = (RadioButton) findViewById(R.id.shou);
        fen = (RadioButton) findViewById(R.id.fen);
        miao = (RadioButton) findViewById(R.id.miao);
        gou = (RadioButton) findViewById(R.id.gou);
        wo = (RadioButton) findViewById(R.id.wo);
        shou_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_Gou.getSignY();
                record_ac = 1;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.hide(fragment_fen);
                fragmentTransaction.hide(fragment_miao);
                fragmentTransaction.hide(fragment_gou);
                fragmentTransaction.hide(fragment_wo);
                fragmentTransaction.show(fragment_shou);
                fragmentTransaction.commit();
                shou.setChecked(true);
                fen.setChecked(false);
                gou.setChecked(false);
                wo.setChecked(false);
                miao.setChecked(false);
                shou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorTab));
                fen_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                miao_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                gou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                wo_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
            }
        });
        fen_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_Gou.getSignY();
                record_ac = 2;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.hide(fragment_shou);
                fragmentTransaction.hide(fragment_miao);
                fragmentTransaction.hide(fragment_gou);
                fragmentTransaction.hide(fragment_wo);
                fragmentTransaction.show(fragment_fen);
                fragmentTransaction.commit();
                fen.setChecked(true);
                shou.setChecked(false);
                gou.setChecked(false);
                wo.setChecked(false);
                miao.setChecked(false);
                shou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                fen_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorTab));
                miao_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                gou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                wo_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));

            }
        });
        miao_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_Gou.getSignY();
                record_ac = 3;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.hide(fragment_shou);
                fragmentTransaction.hide(fragment_fen);
                fragmentTransaction.hide(fragment_gou);
                fragmentTransaction.hide(fragment_wo);
                fragmentTransaction.show(fragment_miao);
                fragmentTransaction.commit();
                miao.setChecked(true);
                gou.setChecked(false);
                wo.setChecked(false);
                shou.setChecked(false);
                fen.setChecked(false);
                shou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                fen_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                miao_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorTab));
                gou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                wo_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));

            }
        });
        gou_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences close = getSharedPreferences("close", MODE_PRIVATE);
                SharedPreferences.Editor edit = close.edit();
                edit.putBoolean("close", false);
                edit.putBoolean("closes", false);
                edit.commit();
                Fragment_Gou.getSignY();
                LoginBean logUser = getLogUser(MainActivity.this);
                if (logUser == null) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.hide(fragment_shou);
                fragmentTransaction.hide(fragment_fen);
                fragmentTransaction.hide(fragment_miao);
                fragmentTransaction.hide(fragment_wo);
                fragmentTransaction.show(fragment_gou);
                fragmentTransaction.commit();
                gou.setChecked(true);
                wo.setChecked(false);
                shou.setChecked(false);
                miao.setChecked(false);
                fen.setChecked(false);
                shou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                fen_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                miao_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                gou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorTab));
                wo_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));

            }
        });
        wo_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_Gou.getSignY();
                record_ac = 5;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.hide(fragment_shou);
                fragmentTransaction.hide(fragment_fen);
                fragmentTransaction.hide(fragment_miao);
                fragmentTransaction.hide(fragment_gou);
                fragmentTransaction.show(fragment_wo);
                fragmentTransaction.commit();
                wo.setChecked(true);
                shou.setChecked(false);
                gou.setChecked(false);
                miao.setChecked(false);
                fen.setChecked(false);
                shou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                fen_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                miao_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                gou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                wo_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorTab));
            }
        });
        shou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_Gou.getSignY();
                record_ac = 1;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.hide(fragment_fen);
                fragmentTransaction.hide(fragment_miao);
                fragmentTransaction.hide(fragment_gou);
                fragmentTransaction.hide(fragment_wo);
                fragmentTransaction.show(fragment_shou);
                fragmentTransaction.commit();
                shou.setChecked(true);
                fen.setChecked(false);
                gou.setChecked(false);
                wo.setChecked(false);
                miao.setChecked(false);
                shou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorTab));
                fen_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                miao_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                gou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                wo_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
            }
        });
        fen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_Gou.getSignY();
                record_ac = 2;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.hide(fragment_shou);
                fragmentTransaction.hide(fragment_miao);
                fragmentTransaction.hide(fragment_gou);
                fragmentTransaction.hide(fragment_wo);
                fragmentTransaction.show(fragment_fen);
                fragmentTransaction.commit();
                fen.setChecked(true);
                shou.setChecked(false);
                gou.setChecked(false);
                wo.setChecked(false);
                miao.setChecked(false);
                shou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                fen_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorTab));
                miao_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                gou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                wo_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
            }
        });
        miao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_Gou.getSignY();
                record_ac = 3;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.hide(fragment_shou);
                fragmentTransaction.hide(fragment_fen);
                fragmentTransaction.hide(fragment_gou);
                fragmentTransaction.hide(fragment_wo);
                fragmentTransaction.show(fragment_miao);
                fragmentTransaction.commit();
                miao.setChecked(true);
                gou.setChecked(false);
                wo.setChecked(false);
                shou.setChecked(false);
                fen.setChecked(false);
                shou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                fen_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                miao_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorTab));
                gou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                wo_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
            }
        });
        gou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_Gou.getSignY();
                SharedPreferences close = getSharedPreferences("close", MODE_PRIVATE);
                SharedPreferences.Editor edit = close.edit();
                edit.putBoolean("close", false);
                edit.putBoolean("closes", false);
                edit.commit();
                LoginBean logUser = getLogUser(MainActivity.this);
                if (logUser == null) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.hide(fragment_shou);
                fragmentTransaction.hide(fragment_fen);
                fragmentTransaction.hide(fragment_miao);
                fragmentTransaction.hide(fragment_wo);
                fragmentTransaction.show(fragment_gou);
                fragmentTransaction.commit();
                gou.setChecked(true);
                wo.setChecked(false);
                shou.setChecked(false);
                miao.setChecked(false);
                fen.setChecked(false);
                shou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                fen_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                miao_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                gou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorTab));
                wo_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
            }
        });
        wo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_Gou.getSignY();
                record_ac = 5;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.hide(fragment_shou);
                fragmentTransaction.hide(fragment_fen);
                fragmentTransaction.hide(fragment_miao);
                fragmentTransaction.hide(fragment_gou);
                fragmentTransaction.show(fragment_wo);
                fragmentTransaction.commit();
                wo.setChecked(true);
                shou.setChecked(false);
                gou.setChecked(false);
                miao.setChecked(false);
                fen.setChecked(false);
                shou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                fen_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                miao_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                gou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                wo_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorTab));
            }
        });
        /**
         * 跳转到activity
         */
        line1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OrderFormActivity.class));
            }
        });
        /**
         * 头像跳转
         */
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MyaccountActivity.class));
            }
        });

        /**
         * 收货地址
         */
        line4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AiteActivity.class));
            }
        });
        /**
         * 账户明细
         */
        line3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DetailActivity.class));
            }
        });
        /**
         * 点击登录
         */
        log_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        fragment_wo.setOnClickListener(new Fragment_Wo.onClickListener() {
            @Override
            public void onclick() {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void createPresenter() {

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //非默认值
        if (newConfig.fontScale != 1) {
            getResources();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {//还原字体大小
        Resources res = super.getResources();
        //非默认值
        if (res.getConfiguration().fontScale != 1) {
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }

    /**
     * @authorszx 双击返回键退出应用
     */

    int longprePressed = 0;//第一次点击

    int longlastPressed = 0;//第二次点击
    long lastPressed;
    long prePressed;

    @Override

    public void onBackPressed() {

//            获得系统第二次点击的时间

        lastPressed = System.currentTimeMillis();

        if (lastPressed - prePressed > 2000) {

//把第一次点击获得的时间赋值给第二次

            prePressed = lastPressed;

//弹出吐司

            Toast.makeText(this, "再点一次退出应用", Toast.LENGTH_SHORT).show();

        } else {

//结束页面（销毁页面）

            finish();

            System.exit(0);

//            Log.e("exit", "应用退出");

        }

    }


    @Override
    protected void onResume() {
        super.onResume();

        LoginBean logUser = getLogUser(this);
        if (logUser != null) {
            line1.setVisibility(View.VISIBLE);
            line2.setVisibility(View.VISIBLE);
            line3.setVisibility(View.VISIBLE);
            line4.setVisibility(View.VISIBLE);
            line5.setVisibility(View.VISIBLE);
            line6.setVisibility(View.VISIBLE);
            header.setVisibility(View.VISIBLE);
            my_name.setVisibility(View.VISIBLE);
            log_text.setVisibility(View.GONE);
            if (logUser != null) {
                userInfoPresenter.request(logUser.getId());
            }
        } else {
            line1.setVisibility(View.GONE);
            line2.setVisibility(View.GONE);
            line3.setVisibility(View.GONE);
            line4.setVisibility(View.GONE);
            line5.setVisibility(View.GONE);
            line6.setVisibility(View.GONE);
            header.setVisibility(View.GONE);
            my_name.setVisibility(View.GONE);
            log_text.setVisibility(View.VISIBLE);
            /**
             * 页面
             */
            switch (record_ac) {
                case 1:
                    shou.setChecked(true);
                    fen.setChecked(false);
                    gou.setChecked(false);
                    wo.setChecked(false);
                    miao.setChecked(false);
                    shou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorTab));
                    fen_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                    miao_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                    gou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                    wo_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.hide(fragment_wo);
                    fragmentTransaction1.hide(fragment_fen);
                    fragmentTransaction1.hide(fragment_miao);
                    fragmentTransaction1.hide(fragment_gou);
                    fragmentTransaction1.show(fragment_shou);
                    fragmentTransaction1.commit();
                    break;
                case 2:
                    shou.setChecked(false);
                    fen.setChecked(true);
                    gou.setChecked(false);
                    wo.setChecked(false);
                    miao.setChecked(false);
                    shou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                    fen_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorTab));
                    miao_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                    gou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                    wo_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.hide(fragment_shou);
                    fragmentTransaction2.hide(fragment_wo);
                    fragmentTransaction2.hide(fragment_miao);
                    fragmentTransaction2.hide(fragment_gou);
                    fragmentTransaction2.show(fragment_fen);
                    fragmentTransaction2.commit();
                    break;
                case 3:
                    shou.setChecked(false);
                    fen.setChecked(false);
                    gou.setChecked(false);
                    wo.setChecked(false);
                    miao.setChecked(true);
                    shou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                    fen_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                    miao_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorTab));
                    gou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                    wo_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                    FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction3.hide(fragment_shou);
                    fragmentTransaction3.hide(fragment_fen);
                    fragmentTransaction3.hide(fragment_wo);
                    fragmentTransaction3.hide(fragment_gou);
                    fragmentTransaction3.show(fragment_miao);
                    fragmentTransaction3.commit();
                    break;
                case 5:
                    shou.setChecked(false);
                    fen.setChecked(false);
                    gou.setChecked(false);
                    wo.setChecked(true);
                    miao.setChecked(false);
                    shou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                    fen_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                    miao_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                    gou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                    wo_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorTab));
                    FragmentTransaction fragmentTransaction5 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction5.hide(fragment_shou);
                    fragmentTransaction5.hide(fragment_fen);
                    fragmentTransaction5.hide(fragment_miao);
                    fragmentTransaction5.hide(fragment_gou);
                    fragmentTransaction5.show(fragment_wo);
                    fragmentTransaction5.commit();
                    break;
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    public void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        //super.onSaveInstanceState(outState);   //将这一行注释掉，阻止activity保存fragment的状态
    }

    /**
     * 个人资料
     */
    private class UserInfo implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            UserInfoBean entity = (UserInfoBean) request.getEntity();
            UserDetailBean userCenter = (UserDetailBean) entity.getUserCenter();
            if (userCenter.getAvatar().contains("https://") || userCenter.getAvatar().contains("http://")) {
                //设置图片圆角角度
                Glide.with(MainActivity.this).load(userCenter.getAvatar())
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into(header);
            } else {
                //设置图片圆角角度
                Glide.with(MainActivity.this).load("https://static.yikch.com" + userCenter.getAvatar())
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into(header);
            }
            //名称
            my_name.setText(userCenter.getNickName());
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    /**
     * 更新APP
     */
    private class UpdateApp implements ICoreInfe {
        private String versionName;

        @Override
        public void success(Object data) {
            Request request = (Request) data;
            final AppUpdateBean entity = (AppUpdateBean) request.getEntity();
            try {
                versionName = getVersionName();
                if (versionName != null && entity.getVersionNo() != null) {
                    if (!versionName.equals(entity.getVersionNo())) {
                        new CircleDialog.Builder()
                                .setMaxHeight(0.8f)
                                .setCanceledOnTouchOutside(false)
                                .setCancelable(false)
                                .configDialog(new ConfigDialog() {
                                    @Override
                                    public void onConfig(DialogParams params) {
//                            params.backgroundColor = Color.DKGRAY;
//                            params.backgroundColorPress = Color.BLUE;

                                    }
                                })
                                .setTitle("发现新版本")
                                .setText(entity.getDepict())
                                .configText(new ConfigText() {
                                    @Override
                                    public void onConfig(TextParams params) {
                                        params.gravity = Gravity.LEFT | Gravity.TOP;
                                    }
                                })
                                .setNegative("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String versionNo = entity.getVersionNo();
                                        String versionNostr = versionNo.substring(0, 1);
                                        String versionNamestr = versionName.substring(0, 1);
                                        if (versionNamestr.equals(versionNamestr)) {
                                            String versionNostr2 = versionNo.substring(2, 3);
                                            String versionNamestr2 = versionName.substring(2, 3);
                                            if (versionNostr2.equals(versionNamestr2)) {
                                                String versionNostr3 = versionNo.substring(versionNo.length() - 1, versionNo.length());
                                                String versionNamestr3 = versionName.substring(versionName.length() - 1, versionName.length());
                                                if (versionNamestr3.equals(versionNostr3)) {
                                                    return;
                                                } else {
                                                    return;
                                                }
                                            } else {
                                                finish();
                                            }
                                            return;
                                        } else {
                                            finish();
                                        }
                                    }
                                })
                                .setPositive("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Toast.makeText(MainActivity.this, "正在更新", Toast.LENGTH_SHORT).show();
                                        UpdaterConfig config = new UpdaterConfig.Builder(MainActivity.this)
                                                .setTitle(getResources().getString(R.string.app_name))
                                                .setDescription(entity.getDepict())
                                                .setFileUrl(entity.getDownloadUrl())
                                                .setCanMediaScanner(true)
                                                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE
                                                        | DownloadManager.Request.NETWORK_WIFI)
                                                .build();
                                        Updater.get().showLog(true).download(config);
                                    }
                                })
                                .configPositive(new ConfigButton() {
                                    @Override
                                    public void onConfig(ButtonParams params) {
                                        params.backgroundColorPress = Color.RED;
                                    }
                                })
                                .show(getSupportFragmentManager());
//                promptDialog.showWarnAlert("发现新版本是否要更新", new PromptButton("取消", new PromptButtonListener() {
//                    @Override
//                    public void onClick(PromptButton button) {
//                        finish();
//                    }
//                }), newConfirm);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
