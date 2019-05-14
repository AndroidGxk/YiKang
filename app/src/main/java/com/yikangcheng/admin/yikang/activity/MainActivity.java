package com.yikangcheng.admin.yikang.activity;

import android.support.v4.app.FragmentTransaction;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Fen;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Gou;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Miao;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Shou;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Wo;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;

public class MainActivity extends BaseActivtiy {
    private RadioGroup radio;
    private FragmentTransaction transaction;
    private RadioButton shou, fen, miao, gou, wo;


    @Override
    protected void initView() {
        radio = findViewById(R.id.radio_group);
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
        /**
         * 切换页面
         */
        shou = findViewById(R.id.shou);
        fen = findViewById(R.id.fen);
        miao = findViewById(R.id.miao);
        gou = findViewById(R.id.gou);
        wo = findViewById(R.id.wo);
        shou.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.hide(fragment_fen);
                    fragmentTransaction.hide(fragment_miao);
                    fragmentTransaction.hide(fragment_gou);
                    fragmentTransaction.hide(fragment_wo);
                    fragmentTransaction.show(fragment_shou);
                    fragmentTransaction.commit();
                    fen.setChecked(false);
                    gou.setChecked(false);
                    wo.setChecked(false);
                    miao.setChecked(false);
                }
            }
        });
        fen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.hide(fragment_shou);
                    fragmentTransaction.hide(fragment_miao);
                    fragmentTransaction.hide(fragment_gou);
                    fragmentTransaction.hide(fragment_wo);
                    fragmentTransaction.show(fragment_fen);
                    fragmentTransaction.commit();
                    shou.setChecked(false);
                    gou.setChecked(false);
                    wo.setChecked(false);
                    miao.setChecked(false);
                }
            }
        });
        miao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.hide(fragment_shou);
                    fragmentTransaction.hide(fragment_fen);
                    fragmentTransaction.hide(fragment_gou);
                    fragmentTransaction.hide(fragment_wo);
                    fragmentTransaction.show(fragment_miao);
                    fragmentTransaction.commit();
                    gou.setChecked(false);
                    wo.setChecked(false);
                    shou.setChecked(false);
                    fen.setChecked(false);
                }
            }
        });
        gou.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.hide(fragment_shou);
                    fragmentTransaction.hide(fragment_fen);
                    fragmentTransaction.hide(fragment_miao);
                    fragmentTransaction.hide(fragment_wo);
                    fragmentTransaction.show(fragment_gou);
                    fragmentTransaction.commit();
                    wo.setChecked(false);
                    shou.setChecked(false);
                    miao.setChecked(false);
                    fen.setChecked(false);
                }
            }
        });
        wo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.hide(fragment_shou);
                    fragmentTransaction.hide(fragment_fen);
                    fragmentTransaction.hide(fragment_miao);
                    fragmentTransaction.hide(fragment_gou);
                    fragmentTransaction.show(fragment_wo);
                    fragmentTransaction.commit();
                    shou.setChecked(false);
                    gou.setChecked(false);
                    miao.setChecked(false);
                    fen.setChecked(false);
                }
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
}
