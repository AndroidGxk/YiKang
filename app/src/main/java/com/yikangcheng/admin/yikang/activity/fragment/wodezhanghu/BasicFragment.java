package com.yikangcheng.admin.yikang.activity.fragment.wodezhanghu;

import android.app.Dialog;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.base.BaseFragment;

public class BasicFragment extends BaseFragment {

    private RelativeLayout mReLayoutFragmentBasicName;
    private RelativeLayout mReLayoutFragmentBasicSave;
    private RelativeLayout mReLayoutFragmentBasicTouxiang;
    private RelativeLayout mReLayoutFragmentBasicPhone;
    private RelativeLayout mReLayoutFragmentBasic昵称;
    private RelativeLayout mReLayoutFragmentBasicSex;
    private RelativeLayout mReLayoutFragmentBasicSynopsis;
    private int width;
    private int height;
    private Dialog mDialog;
    private View mInflate;
    private TextView mTvPhotograph;
    private TextView mTvPhotoAlbu;
    private ImageView mImgFinsh;

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
        mReLayoutFragmentBasic昵称 = view.findViewById(R.id.reLayout_fragment_basic_niceng);
        //性别
        mReLayoutFragmentBasicSex = view.findViewById(R.id.reLayout_fragment_basic_sex);
        //简介
        mReLayoutFragmentBasicSynopsis = view.findViewById(R.id.reLayout_fragment_basic_synopsis);
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
        mImgFinsh =  mInflate.findViewById(R.id.img_finsh);

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
}