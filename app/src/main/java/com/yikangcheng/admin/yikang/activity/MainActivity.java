package com.yikangcheng.admin.yikang.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.tianma.netdetector.lib.NetworkType;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Fen;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Gou;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Miao;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_New_Wo;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Shou;
import com.yikangcheng.admin.yikang.activity.myaccount.MyaccountActivity;
import com.yikangcheng.admin.yikang.activity.obligation.DetailActivity;
import com.yikangcheng.admin.yikang.activity.siteactivity.AiteActivity;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.UserDetailBean;
import com.yikangcheng.admin.yikang.bean.UserInfoBean;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.UserInfoPresenter;

import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

public class MainActivity extends BaseActivtiy {
    private RadioGroup radio;
    private FragmentTransaction transaction;
    private LinearLayout shou_linear, fen_linear, miao_linear,
            gou_linear, wo_linear, line1, line4, line3, line2, line5, line6;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mNv;
    private RelativeLayout mRelativeLayout;
    private TextView log_text;
    private TextView shou_text, fen_text, miao_text, gou_text, wo_text, my_name;
    private ImageView header;
    private TextView text_count;
    /**
     * 记录上次点击的页面
     */
    private int record_ac;
    private Fragment_Shou fragment_shou;
    private Fragment_Fen fragment_fen;
    private Fragment_Gou fragment_gou;
    private Fragment_Miao fragment_miao;
    private Fragment_New_Wo fragment_new_wo = new Fragment_New_Wo();
    private PromptDialog promptDialog;
    private UserInfoPresenter userInfoPresenter;
    private int width;
    private SharedPreferences sharedPreferences;
    private ImageView shou_img;
    private ImageView fen_img;
    private ImageView gou_img;
    private ImageView wo_img;

    @Override
    protected void initView() {
        closeSwipeBack();
        //创建对象
        promptDialog = new PromptDialog(this);
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);
        userInfoPresenter = new UserInfoPresenter(new UserInfo());
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        width = wm.getDefaultDisplay().getWidth();
        radio = (RadioGroup) findViewById(R.id.radio_group);
        radio.check(R.id.shou);
        shou_linear = (LinearLayout) findViewById(R.id.shou_linear);
        fen_linear = (LinearLayout) findViewById(R.id.fen_linear);
        shou_text = (TextView) findViewById(R.id.shou_text);
        shou_text.setTextColor(Color.parseColor(getLogUser(this).getThemeColors()));
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
//        GradientDrawable gradientDrawable = (GradientDrawable) text_count.getBackground();
//        gradientDrawable.setColor(Color.parseColor(getLogUser(this).getThemeColors()));
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
        sharedPreferences = getSharedPreferences("wo", MODE_PRIVATE);

    }

    //按钮的定义，创建一个按钮的对象
    final PromptButton confirm = new PromptButton("确定", new PromptButtonListener() {
        @Override
        public void onClick(PromptButton button) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            //进行查看账号之前是否存在如果存在就不添加只修改状态
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            LoginBean loginBean = getLogUser(BaseApp.getApp());
            loginBean.setStatus(2);
            getDelete(MainActivity.this);
            setLogUser(BaseApp.getApp(), loginBean);
            finish();
        }
    });

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
    }

    @Override
    protected void initEventData() {
        transaction = getSupportFragmentManager().beginTransaction();
        fragment_shou = new Fragment_Shou();
        fragment_fen = new Fragment_Fen();
        fragment_miao = new Fragment_Miao();
        fragment_gou = new Fragment_Gou();
        transaction.add(R.id.frag, fragment_shou);
        transaction.add(R.id.frag, fragment_fen);
        transaction.add(R.id.frag, fragment_miao);
        transaction.add(R.id.frag, fragment_gou);
        transaction.add(R.id.frag, fragment_new_wo);
        transaction.hide(fragment_fen);
        transaction.hide(fragment_miao);
        transaction.hide(fragment_gou);
        transaction.hide(fragment_new_wo);
        transaction.show(fragment_shou);
        transaction.commit();

        /**
         * 切换页面
         */
        shou_img = (ImageView) findViewById(R.id.shou_img);
        shou_img.setColorFilter(Color.parseColor(getLogUser(MainActivity.this).getThemeColors()));
        fen_img = (ImageView) findViewById(R.id.fen_img);
        gou_img = (ImageView) findViewById(R.id.gou_img);
        wo_img = (ImageView) findViewById(R.id.wo_img);
        shou_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_Gou.getSignY();
                record_ac = 1;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.hide(fragment_fen);
                fragmentTransaction.hide(fragment_miao);
                fragmentTransaction.hide(fragment_gou);
                fragmentTransaction.hide(fragment_new_wo);
                fragmentTransaction.show(fragment_shou);
                fragmentTransaction.commit();
                shou_img.setColorFilter(Color.parseColor(getLogUser(MainActivity.this).getThemeColors()));
                fen_img.setColorFilter(Color.parseColor("#666666"));
                gou_img.setColorFilter(Color.parseColor("#666666"));
                wo_img.setColorFilter(Color.parseColor("#666666"));
                shou_text.setTextColor(Color.parseColor(getLogUser(MainActivity.this).getThemeColors()));
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
                fragmentTransaction.hide(fragment_new_wo);
                fragmentTransaction.show(fragment_fen);
                fragmentTransaction.commit();
                shou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                fen_img.setColorFilter(Color.parseColor(getLogUser(MainActivity.this).getThemeColors()));
                shou_img.setColorFilter(Color.parseColor("#666666"));
                gou_img.setColorFilter(Color.parseColor("#666666"));
                wo_img.setColorFilter(Color.parseColor("#666666"));
                fen_text.setTextColor(Color.parseColor(getLogUser(MainActivity.this).getThemeColors()));
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
                fragmentTransaction.hide(fragment_new_wo);
                fragmentTransaction.show(fragment_miao);
                fragmentTransaction.commit();
                shou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                fen_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                miao_text.setTextColor(Color.parseColor(getLogUser(MainActivity.this).getThemeColors()));
                fen_img.setColorFilter(Color.parseColor("#666666"));
                shou_img.setColorFilter(Color.parseColor("#666666"));
                gou_img.setColorFilter(Color.parseColor("#666666"));
                wo_img.setColorFilter(Color.parseColor("#666666"));
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
                fragmentTransaction.hide(fragment_new_wo);
                fragmentTransaction.show(fragment_gou);
                fragmentTransaction.commit();
                shou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                fen_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                miao_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                gou_img.setColorFilter(Color.parseColor(getLogUser(MainActivity.this).getThemeColors()));
                fen_img.setColorFilter(Color.parseColor("#666666"));
                shou_img.setColorFilter(Color.parseColor("#666666"));
                wo_img.setColorFilter(Color.parseColor("#666666"));
                gou_text.setTextColor(Color.parseColor(getLogUser(MainActivity.this).getThemeColors()));
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
                fragmentTransaction.show(fragment_new_wo);
                fragmentTransaction.commit();
                shou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                fen_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                miao_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                gou_text.setTextColor(MainActivity.this.getResources().getColor(R.color.colorText));
                wo_img.setColorFilter(Color.parseColor(getLogUser(MainActivity.this).getThemeColors()));
                gou_img.setColorFilter(Color.parseColor("#666666"));
                fen_img.setColorFilter(Color.parseColor("#666666"));
                shou_img.setColorFilter(Color.parseColor("#666666"));
                wo_text.setTextColor(Color.parseColor(getLogUser(MainActivity.this).getThemeColors()));
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("wo", "no");
                editor.commit();
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
//                ToastUtils.show("功能暂未开放，敬请期待！");
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
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void createPresenter() {

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
            if (userCenter.getAvatar() != null) {
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
            }
            //名称
            my_name.setText(userCenter.getNickName());
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
