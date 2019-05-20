package com.yikangcheng.admin.yikang.activity.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.ApoutUsActivity;
import com.yikangcheng.admin.yikang.activity.MessageActivity;
import com.yikangcheng.admin.yikang.activity.OrderFormActivity;
import com.yikangcheng.admin.yikang.activity.aftersale.AfterSaleActivity;
import com.yikangcheng.admin.yikang.activity.myaccount.MyaccountActivity;
import com.yikangcheng.admin.yikang.base.BaseFragment;

public class Fragment_Wo extends BaseFragment {


    private TextView mTvFragmentWoDingdan;
    private LinearLayout mImgFragmentWoMingxi;
    private LinearLayout mImgFragmentWoguanyu;
    private LinearLayout mImgFragmentWoYizhifu;
    private LinearLayout mImgFragmentWoYituikuan;
    private TextView mTvFragmentWoXiaoxiA;
    private RecyclerView mRlvFragmentWo;
    private TextView mTvFragmentWoXiaoxiB;
    private ImageView mImgFragmentWoTouxiang;
    private ImageView mImgFragmentWoHongyuanquanB;
    private ImageView mImgFragmentWoHongyuanquanA;
    private ImageView mImgFragmentWoLingdang;
    private LinearLayout mImgFragmentWoDaifukuan;
    private LinearLayout mImgFragmentWoYiquxiao;
    private LinearLayout mImgFragmentWoDizi;
    private LinearLayout mImgFragmentWoTuichudenglu;
    private TextView mTvFragmentWoName;

    @Override
    protected void initView(View view) {
        //查看全部订单
        mTvFragmentWoDingdan = view.findViewById(R.id.tv_fragment_wo_dingdan);
        //账号明细
        mImgFragmentWoMingxi = view.findViewById(R.id.img_fragment_wo_mingxi);
        //关于
        mImgFragmentWoguanyu = view.findViewById(R.id.img__fragment_wo_guanyu);
        //已支付APP
        mImgFragmentWoYizhifu = view.findViewById(R.id.img_fragment_wo_yizhifu);
        //已退款
        mImgFragmentWoYituikuan = view.findViewById(R.id.img_fragment_wo_yituikuan);
        //这是铃铛上面的提示消息
        mTvFragmentWoXiaoxiA = view.findViewById(R.id.tv_fragment_wo_xiaoxi_a);
        //这是为你推荐下面的列表
        mRlvFragmentWo = view.findViewById(R.id.rlv__fragment_wo);
        //这是待付款上面的提示消息
        mTvFragmentWoXiaoxiB = view.findViewById(R.id.tv_fragment_wo_xiaoxi_b);
        //这是头像
        mImgFragmentWoTouxiang = view.findViewById(R.id.img_fragment_wo_touxiang);
        //这是待付款上面的红点点
        mImgFragmentWoHongyuanquanB = view.findViewById(R.id.img_fragment_wo_hongyuanquan_b);
        //这是铃铛上面的红点点
        mImgFragmentWoHongyuanquanA = view.findViewById(R.id.img__fragment_wo_hongyuanquan_a);
        //这是铃铛
        mImgFragmentWoLingdang = view.findViewById(R.id.img_fragment_wo_lingdang);
        //待付款
        mImgFragmentWoDaifukuan = view.findViewById(R.id.img_fragment_wo_daifukuan);
        //已取消
        mImgFragmentWoYiquxiao = view.findViewById(R.id.img__fragment_wo_yiquxiao);
        //收货地址
        mImgFragmentWoDizi = view.findViewById(R.id.img_fragment_wo_dizi);
        //退出登录
        mImgFragmentWoTuichudenglu = view.findViewById(R.id.img__fragment_wo_tuichudenglu);
        //用户名
        mTvFragmentWoName = view.findViewById(R.id.tv__fragment_wo_name);


        /**
         * 点击头像跳转页面
         */
        mImgFragmentWoTouxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyaccountActivity.class);
                startActivity(intent);
            }
        });
        /**
         * 点击售后/退款 进行页面跳转
         */
        mImgFragmentWoYituikuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AfterSaleActivity.class);
                startActivity(intent);
            }
        });
        /**
         * 消息页面
         */
        mImgFragmentWoLingdang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MessageActivity.class));
            }
        });

        /**
         * 点击关于APP跳转页面
         */
        mImgFragmentWoguanyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ApoutUsActivity.class);
                startActivity(intent);
            }
        });

        /**
         * 点击全部订单 跳转页面
         */
        mTvFragmentWoDingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderFormActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_wo;
    }
}
