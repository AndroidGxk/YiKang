package com.yikangcheng.admin.yikang.activity.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.ApoutUsActivity;
import com.yikangcheng.admin.yikang.activity.LoginActivity;
import com.yikangcheng.admin.yikang.activity.MessageActivity;
import com.yikangcheng.admin.yikang.activity.OrderFormActivity;
import com.yikangcheng.admin.yikang.activity.adapter.Recommend_Fragment_wo_Adapter;
import com.yikangcheng.admin.yikang.activity.aftersale.AfterSaleActivity;
import com.yikangcheng.admin.yikang.activity.myaccount.MyaccountActivity;
import com.yikangcheng.admin.yikang.activity.obligation.ObligationActivity;
import com.yikangcheng.admin.yikang.activity.orderstatus.WaitForpaymentActivity;
import com.yikangcheng.admin.yikang.activity.siteactivity.AiteActivity;
import com.yikangcheng.admin.yikang.app.Constants;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.AdvertisingBean;
import com.yikangcheng.admin.yikang.bean.RecommendBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.AdvertisingPresenter;
import com.yikangcheng.admin.yikang.presenter.RecommendPresenter;
import com.yikangcheng.admin.yikang.util.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import me.jessyan.autosize.utils.LogUtils;

public class Fragment_Wo extends BaseFragment implements ICoreInfe {


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
    private Recommend_Fragment_wo_Adapter mRecommend_fragment_wo_adapter;
    private ImageView mGuanggao;
    private RelativeLayout mMingxi;

    @Override
    protected void initView(View view) {
        //广告图
        mGuanggao = view.findViewById(R.id.img_fragment_wo_guanggao);
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
        //账户明细
        mMingxi = view.findViewById(R.id.relativeLayout_mingxi_fragment_wo);

        mMingxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WaitForpaymentActivity.class);
                startActivity(intent);
            }
        });


        /**
         * 广告图P层
         */
        AdvertisingPresenter advertisingPresenter = new AdvertisingPresenter(new AdvertisingICoreInfe());
        advertisingPresenter.request();

        /**
         * 为你推荐
         */
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mRlvFragmentWo.setLayoutManager(gridLayoutManager);
        ArrayList<RecommendBean> recommendBeans = new ArrayList<>();
        //创建适配器
        mRecommend_fragment_wo_adapter = new Recommend_Fragment_wo_Adapter(recommendBeans, getContext());
        //绑定适配器
        mRlvFragmentWo.setAdapter(mRecommend_fragment_wo_adapter);

        /**
         *     解决滑动不流畅
         */
        mRlvFragmentWo.setFocusable(false);
        mRlvFragmentWo.setHasFixedSize(true);
        mRlvFragmentWo.setNestedScrollingEnabled(false);

        /**
         * 为你推荐P层
         */
        RecommendPresenter recommendPresenter = new RecommendPresenter(this);
        recommendPresenter.request(1);

        /**
         * item间距
         */
        int spanCount_tuijian = 2; // 3 columns
        int spacing_tuijian = 20; // 50px
        boolean includeEdge_tuijian = false;
        mRlvFragmentWo.addItemDecoration(new SpacesItemDecoration(spanCount_tuijian, spacing_tuijian, includeEdge_tuijian));


        /**
         * 点击 待付款 跳转页面
         */
        mImgFragmentWoDaifukuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ObligationActivity.class);
                startActivity(intent);
            }
        });

        /**
         * 登录
         */

        mTvFragmentWoName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        /**
         * 点击收货地址跳转页面
         */
        mImgFragmentWoDizi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AiteActivity.class);
                startActivity(intent);
            }
        });

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
         * 消息页面·
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

    public class AdvertisingICoreInfe implements ICoreInfe {

        @Override
        public void success(Object data) {
            Request request = (Request) data;
            AdvertisingBean entity = (AdvertisingBean) request.getEntity();
            Glide.with(getContext()).load("https://static.yikch.com"+entity.getImagesUrl()).into(mGuanggao);
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_wo;
    }

    @Override
    public void success(Object data) {
        Request request = (Request) data;
        List<RecommendBean> entity = (List<RecommendBean>) request.getEntity();
        mRecommend_fragment_wo_adapter.addData(entity);
    }

    @Override
    public void fail(ApiException e) {

    }
}
