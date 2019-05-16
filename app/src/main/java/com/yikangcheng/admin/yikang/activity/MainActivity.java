package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Fen;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Gou;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Miao;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Shou;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Wo;
import com.yikangcheng.admin.yikang.activity.seek.SeekActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.jessyan.autosize.internal.CustomAdapt;

public class MainActivity extends BaseActivtiy implements CustomAdapt {
    private RadioGroup radio;
    private FragmentTransaction transaction;
    private RadioButton shou, fen, miao, gou, wo;
    private LinearLayout shou_linear, fen_linear, miao_linear, gou_linear, wo_linear;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNv;
    //    private ImageView mImg_ceHua;
    private RelativeLayout mRelativeLayout;
    private TextView tv_toolBar_title, tv_toolBar_right;
    private ImageView iv_toolBar_right;
    private ImageView mImg_activity_main_soushuo;


    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        radio = findViewById(R.id.radio_group);
        shou_linear = findViewById(R.id.shou_linear);
        fen_linear = findViewById(R.id.fen_linear);
        miao_linear = findViewById(R.id.miao_linear);
        gou_linear = findViewById(R.id.gou_linear);
        wo_linear = findViewById(R.id.wo_linear);
        View toobar = findViewById(R.id.toobar);
        tv_toolBar_title = toobar.findViewById(R.id.tv_toolBar_title);
        tv_toolBar_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SearchListActivity.class));
            }
        });
        iv_toolBar_right = toobar.findViewById(R.id.iv_toolBar_right);
        iv_toolBar_right = toobar.findViewById(R.id.iv_toolBar_right);
        tv_toolBar_right = toobar.findViewById(R.id.tv_toolBar_right);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mNv = findViewById(R.id.nv);
        mRelativeLayout = findViewById(R.id.relativeLayout);
        /**
         * 抽屉菜单图标不显示，设置这行代码
         */
        mNv.setItemIconTintList(null);
        mNv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                /**
                 * 设置menu选中，这样才会有背景
                 */
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.wan:
                        /**
                         * 关闭抽屉
                         */
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                }
                return false;
            }
        });
        //抽屉滑出时,主界面被挤到右边
        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //抽屉滑动监听
                mRelativeLayout.setX(mNv.getWidth() * slideOffset);
            }
        });


        //点击搜索图标跳转搜索页面
        mImg_activity_main_soushuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SeekActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initEventData() {
        transaction = getSupportFragmentManager().beginTransaction();
        final Fragment_Shou fragment_shou = new Fragment_Shou();
        final Fragment_Fen fragment_fen = new Fragment_Fen();
        final Fragment_Miao fragment_miao = new Fragment_Miao();
        final Fragment_Gou fragment_gou = new Fragment_Gou();
        final Fragment_Wo fragment_wo = new Fragment_Wo();
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
        tv_toolBar_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "编辑", Toast.LENGTH_SHORT).show();
            }
        });
                /**
                 * 切换页面
                 */
        shou = findViewById(R.id.shou);
        fen = findViewById(R.id.fen);
        miao = findViewById(R.id.miao);
        gou = findViewById(R.id.gou);
        wo = findViewById(R.id.wo);
        shou_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                tv_toolBar_right.setVisibility(View.GONE);
                iv_toolBar_right.setVisibility(View.VISIBLE);
                tv_toolBar_title.setText("优选商城");
            }
        });
        fen_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                tv_toolBar_right.setVisibility(View.GONE);
                iv_toolBar_right.setVisibility(View.VISIBLE);
                tv_toolBar_title.setText("");

            }
        });
        miao_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                tv_toolBar_right.setVisibility(View.GONE);
                iv_toolBar_right.setVisibility(View.VISIBLE);
                tv_toolBar_title.setText("秒杀专区");

            }
        });
        gou_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                iv_toolBar_right.setVisibility(View.GONE);
                tv_toolBar_right.setVisibility(View.VISIBLE);
                tv_toolBar_title.setText("我的购物车");

            }
        });
        wo_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                tv_toolBar_right.setVisibility(View.GONE);
                iv_toolBar_right.setVisibility(View.VISIBLE);
                tv_toolBar_title.setText("我的");
            }
        });
        shou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                tv_toolBar_right.setVisibility(View.GONE);
                iv_toolBar_right.setVisibility(View.VISIBLE);
                tv_toolBar_title.setText("优选商城");
            }
        });
        fen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                tv_toolBar_right.setVisibility(View.GONE);
                iv_toolBar_right.setVisibility(View.VISIBLE);
                tv_toolBar_title.setText("");
            }
        });
        miao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                tv_toolBar_right.setVisibility(View.GONE);
                iv_toolBar_right.setVisibility(View.VISIBLE);
                tv_toolBar_title.setText("秒杀专区");
            }
        });
        gou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                iv_toolBar_right.setVisibility(View.GONE);
                tv_toolBar_right.setVisibility(View.VISIBLE);
                tv_toolBar_title.setText("我的购物车");
            }
        });
        wo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                tv_toolBar_right.setVisibility(View.GONE);
                iv_toolBar_right.setVisibility(View.VISIBLE);
                tv_toolBar_title.setText("我的");
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
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 750;
    }
}
