package com.yikangcheng.admin.yikang.activity.fragment.wodezhanghu;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.myaccount.IntroActivity;
import com.yikangcheng.admin.yikang.activity.myaccount.NameActivity;
import com.yikangcheng.admin.yikang.activity.myaccount.NicknameActivity;
import com.yikangcheng.admin.yikang.activity.myaccount.PhoneActivity;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.UserDetailBean;

public class BasicFragment extends BaseFragment {

    private RelativeLayout mReLayoutFragmentBasicName;
    private RelativeLayout mReLayoutFragmentBasicSave;
    private RelativeLayout mReLayoutFragmentBasicTouxiang;
    private RelativeLayout mReLayoutFragmentBasicPhone;
    private RelativeLayout mReLayoutFragmentBasic;
    private RelativeLayout mReLayoutFragmentBasicSex;
    private RelativeLayout mReLayoutFragmentBasicSynopsis;
    private int width;
    private int height;
    private Dialog mDialog;
    private View mInflate;
    private TextView mTvPhotograph;
    private TextView mTvPhotoAlbu;
    private ImageView mImgFinsh, user_header;
    private TextView name_text, rela_name_text;
    private RadioGroup sex_group;
    private TextView phone_text, jianjie;

    @Override
    protected void initView(View view) {
        //用户姓名
        mReLayoutFragmentBasicName = view.findViewById(R.id.reLayout_fragment_basic_name);
        //保存
        mReLayoutFragmentBasicSave = view.findViewById(R.id.reLayout_fragment_basic_save);
        //头像
        mReLayoutFragmentBasicTouxiang = view.findViewById(R.id.reLayout_fragment_basic_touxiang);
        //手机号
        mReLayoutFragmentBasicPhone = view.findViewById(R.id.reLayout_fragment_basic_phone);
        //用户昵称
        mReLayoutFragmentBasic = view.findViewById(R.id.reLayout_fragment_basic_niceng);
        //性别
        mReLayoutFragmentBasicSex = view.findViewById(R.id.reLayout_fragment_basic_sex);
        //简介
        mReLayoutFragmentBasicSynopsis = view.findViewById(R.id.reLayout_fragment_basic_synopsis);

        /**
         * 点击个人简介跳转页面
         */
        mReLayoutFragmentBasicSynopsis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IntroActivity.class);
                startActivity(intent);
            }
        });

        /**
         * 点击手机号跳转页面
         */
        mReLayoutFragmentBasicPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PhoneActivity.class);
                startActivity(intent);
            }
        });
        /**
         * 点击姓名跳转页面
         */
        mReLayoutFragmentBasicName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NameActivity.class);
                startActivity(intent);
            }
        });


        /**
         * 点击用户昵称跳转页面
         */
        mReLayoutFragmentBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NicknameActivity.class);
                startActivity(intent);
            }
        });

        //用户名称
        name_text = view.findViewById(R.id.name_text);
        //姓名名称
        rela_name_text = view.findViewById(R.id.rela_name_text);
        //性别单选
        sex_group = view.findViewById(R.id.sex);
        //手机号
        phone_text = view.findViewById(R.id.phone_text);
        //简介
        jianjie = view.findViewById(R.id.jianjie);
        //用户头像
        user_header = view.findViewById(R.id.user_header);
        mInflate = LayoutInflater.from(getContext()).inflate(R.layout.head_fragment_basic, null, false);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        mDialog = new Dialog(getContext(), R.style.BottomDialog);

        mReLayoutFragmentBasicTouxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置dialog的宽高为屏幕的宽高
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, height - 390 * 2);
                mDialog.setContentView(mInflate, layoutParams);
                mDialog.getWindow().setGravity(Gravity.BOTTOM);
                mDialog.setCanceledOnTouchOutside(true);
                mDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
                mDialog.show();
            }
        });
        /**
         * 拍照id
         */
        mTvPhotograph = mInflate.findViewById(R.id.tv_Photograph);
        /**
         * 相册id
         */
        mTvPhotoAlbu = mInflate.findViewById(R.id.tv_PhotoAlbu);
        mImgFinsh = mInflate.findViewById(R.id.img_finsh);

        mImgFinsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_basic;
    }

    @Override
    public void onResume() {
        super.onResume();
        UserDetailBean userInfo = getUserInfo(getContext());
        name_text.setText(userInfo.getNickName());
        rela_name_text.setText(userInfo.getRealName());
        phone_text.setText(userInfo.getMobile() + "");
        int gender = userInfo.getGender();
        if (gender == 0) {
            sex_group.check(sex_group.getChildAt(0).getId());
        } else {
            sex_group.check(sex_group.getChildAt(1).getId());
        }
        Glide.with(getContext()).load("https://static.yikch.com" + userInfo.getAvatar()).into(user_header);
        SharedPreferences intro = getContext().getSharedPreferences("intro", Context.MODE_PRIVATE);
        String introString = intro.getString("intro", "");
        if (!introString.equals("")) {
            jianjie.setText(introString);
        }
    }
}