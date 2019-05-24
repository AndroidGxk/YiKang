package com.yikangcheng.admin.yikang.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.aftersale.AfterSaleActivity;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Fen;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Gou;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Miao;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Shou;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Wo;
import com.yikangcheng.admin.yikang.activity.seek.SeekActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.jessyan.autosize.internal.CustomAdapt;

public class MainActivity extends BaseActivtiy implements CustomAdapt {
    private RadioGroup radio;
    private FragmentTransaction transaction;
    private RadioButton shou, fen, miao, gou, wo;
    private LinearLayout shou_linear, fen_linear, miao_linear, gou_linear, wo_linear, line1;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mNv;
    //    private ImageView mImg_ceHua;
    private RelativeLayout mRelativeLayout;
    private TextView tv_toolBar_title, tv_toolBar_right;
    private TextView shou_text, fen_text, miao_text, gou_text, wo_text;
    private ImageView iv_toolBar_right, iv_toolBar_left;
    private ImageView mImg_activity_main_soushuo;
    private View toobar;
    private boolean isclick;
    private Fragment_Miao fragment_miao;

    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarUtil.setStatusBarMode(this, true, R.color.colorToolbar);
        radio = findViewById(R.id.radio_group);
        radio.check(R.id.shou);
        mImg_activity_main_soushuo = findViewById(R.id.iv_toolBar_right);
        shou_linear = findViewById(R.id.shou_linear);
        fen_linear = findViewById(R.id.fen_linear);
        shou_text = findViewById(R.id.shou_text);
        fen_text = findViewById(R.id.fen_text);
        line1 = findViewById(R.id.line1);
        miao_text = findViewById(R.id.miao_text);
        gou_text = findViewById(R.id.gou_text);
        wo_text = findViewById(R.id.wo_text);
        miao_linear = findViewById(R.id.miao_linear);
        gou_linear = findViewById(R.id.gou_linear);
        wo_linear = findViewById(R.id.wo_linear);
        toobar = findViewById(R.id.toobar);
        tv_toolBar_title = toobar.findViewById(R.id.tv_toolBar_title);
        tv_toolBar_right = toobar.findViewById(R.id.tv_toolBar_right);
        iv_toolBar_left = toobar.findViewById(R.id.iv_toolBar_left);
        iv_toolBar_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        tv_toolBar_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //编辑
        tv_toolBar_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isclick) {
                    tv_toolBar_right.setText("完成");
                    isclick = true;
                } else {
                    tv_toolBar_right.setText("编辑");
                    isclick = false;
                }
                onClickListener.onclick();
            }
        });
        iv_toolBar_right = toobar.findViewById(R.id.iv_toolBar_right);
        iv_toolBar_right = toobar.findViewById(R.id.iv_toolBar_right);
        tv_toolBar_right = toobar.findViewById(R.id.tv_toolBar_right);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mNv = findViewById(R.id.nv);
        mRelativeLayout = findViewById(R.id.relativeLayout);
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
        fragment_miao = new Fragment_Miao();
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
                toobar.setVisibility(View.VISIBLE);
                tv_toolBar_right.setVisibility(View.GONE);
                iv_toolBar_right.setVisibility(View.VISIBLE);
                tv_toolBar_title.setText("优选商城");
//                private TextView shou_text,fen_text,miao_text,gou_text,wo_text;
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
                toobar.setVisibility(View.VISIBLE);
                tv_toolBar_right.setVisibility(View.GONE);
                iv_toolBar_right.setVisibility(View.VISIBLE);
                tv_toolBar_title.setText("分类");
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
                toobar.setVisibility(View.VISIBLE);
                tv_toolBar_right.setVisibility(View.GONE);
                iv_toolBar_right.setVisibility(View.VISIBLE);
                tv_toolBar_title.setText("秒杀专区");
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
                LoginBean logUser = getLogUser(MainActivity.this);
                if (logUser == null) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
                toobar.setVisibility(View.VISIBLE);
                tv_toolBar_right.setVisibility(View.VISIBLE);
                iv_toolBar_right.setVisibility(View.GONE);
                tv_toolBar_title.setText("我的购物车");
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
                toobar.setVisibility(View.VISIBLE);
                tv_toolBar_right.setVisibility(View.GONE);
                iv_toolBar_right.setVisibility(View.VISIBLE);
                tv_toolBar_title.setText("我的");
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
                toobar.setVisibility(View.VISIBLE);
                tv_toolBar_right.setVisibility(View.GONE);
                iv_toolBar_right.setVisibility(View.VISIBLE);
                tv_toolBar_title.setText("优选商城");
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
                toobar.setVisibility(View.VISIBLE);
                tv_toolBar_right.setVisibility(View.GONE);
                iv_toolBar_right.setVisibility(View.VISIBLE);
                tv_toolBar_title.setText("分类");
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
                toobar.setVisibility(View.VISIBLE);
                tv_toolBar_right.setVisibility(View.GONE);
                iv_toolBar_right.setVisibility(View.VISIBLE);
                tv_toolBar_title.setText("秒杀专区");
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
                LoginBean logUser = getLogUser(MainActivity.this);
                if (logUser == null) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
                toobar.setVisibility(View.VISIBLE);
                tv_toolBar_right.setVisibility(View.VISIBLE);
                iv_toolBar_right.setVisibility(View.GONE);
                tv_toolBar_title.setText("我的购物车");
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
                toobar.setVisibility(View.VISIBLE);
                tv_toolBar_right.setVisibility(View.GONE);
                iv_toolBar_right.setVisibility(View.VISIBLE);
                tv_toolBar_title.setText("我的");
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
        return 720;
    }

    onClickListener onClickListener;

    public void setOnClickListener(MainActivity.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface onClickListener {
        void onclick();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //非默认值
        if (newConfig.fontScale != 1){
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
}
