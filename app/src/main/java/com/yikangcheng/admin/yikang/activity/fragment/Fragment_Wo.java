package com.yikangcheng.admin.yikang.activity.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.ApoutUsActivity;
import com.yikangcheng.admin.yikang.activity.LoginActivity;
import com.yikangcheng.admin.yikang.activity.MessageActivity;
import com.yikangcheng.admin.yikang.activity.OrderFormActivity;
import com.yikangcheng.admin.yikang.activity.adapter.Recommend_Fragment_wo_Adapter;
import com.yikangcheng.admin.yikang.activity.aftersale.AfterSaleActivity;
import com.yikangcheng.admin.yikang.activity.coupon.CouponActivity;
import com.yikangcheng.admin.yikang.activity.myaccount.MyaccountActivity;
import com.yikangcheng.admin.yikang.activity.obligation.CanceledActivity;
import com.yikangcheng.admin.yikang.activity.obligation.DetailActivity;
import com.yikangcheng.admin.yikang.activity.obligation.ObligationActivity;
import com.yikangcheng.admin.yikang.activity.obligation.PaidActivity;
import com.yikangcheng.admin.yikang.activity.particulars.ParticularsActivity;
import com.yikangcheng.admin.yikang.activity.siteactivity.AiteActivity;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.AdvertisingBean;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.RecommendBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.UserDetailBean;
import com.yikangcheng.admin.yikang.bean.UserInfoBean;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.AdvertisingPresenter;
import com.yikangcheng.admin.yikang.presenter.RecommendPresenter;
import com.yikangcheng.admin.yikang.presenter.UserInfoPresenter;
import com.yikangcheng.admin.yikang.util.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Wo extends BaseFragment implements ICoreInfe {


    private TextView mTvFragmentWoDingdan;
    private LinearLayout mImgFragmentWoMingxi;
    private LinearLayout mImgFragmentWoguanyu;
    private LinearLayout mImgFragmentWoYizhifu;
    private LinearLayout mImgFragmentWoYituikuan;
    private TextView mTvFragmentWoXiaoxiA;
    private RecyclerView mRlvFragmentWo;
    private TextView mTvFragmentWoXiaoxiB;
    private ImageView mImgFragmentWoTouxiang, tuijian;
    private ImageView mImgFragmentWoHongyuanquanB;
    private ImageView mImgFragmentWoHongyuanquanA;
    private ImageView mImgFragmentWoLingdang;
    private LinearLayout mImgFragmentWoDaifukuan;
    private LinearLayout mImgFragmentWoYiquxiao;
    private LinearLayout mImgFragmentWoDizi;
    private LinearLayout mImgFragmentWoyouhuiquan;
    private TextView mTvFragmentWoName;
    private Recommend_Fragment_wo_Adapter mRecommend_fragment_wo_adapter;
    private ImageView mGuanggao;
    private UserInfoPresenter userInfoPresenter;
    private LoginBean logUser;
    private RelativeLayout mMingxi;
    private RecommendPresenter recommendPresenter;

    @Override
    protected void initView(View view) {
        //广告图
        mGuanggao = view.findViewById(R.id.img_fragment_wo_guanggao);
        //为你推荐分割线
        tuijian = view.findViewById(R.id.tuijian);
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
        //优惠券
        mImgFragmentWoyouhuiquan = view.findViewById(R.id.img__fragment_wo_gouwuche);
        //用户名
        mTvFragmentWoName = view.findViewById(R.id.tv__fragment_wo_name);
        //账户明细
        mMingxi = view.findViewById(R.id.relativeLayout_mingxi_fragment_wo);



        /**
         * 点击已取消 跳转页面
         */
        mImgFragmentWoYiquxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CanceledActivity.class);
                startActivity(intent);
            }
        });

        /**
         * 点击已支付跳转页面
         */
        mImgFragmentWoYizhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PaidActivity.class);
                startActivity(intent);
            }
        });


        /**
         * 点击账户明细 跳转页面
         */
        mMingxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);
            }
        });


        //接口请求
        userInfoPresenter = new UserInfoPresenter(new UserInfo());
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

        mRecommend_fragment_wo_adapter.setOnClickListener(new Recommend_Fragment_wo_Adapter.OnClickListener() {
            @Override
            public void OnClickListener(View v, int id) {
                Intent intent = new Intent(getActivity(), ParticularsActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        /**
         *     解决滑动不流畅
         */
        mRlvFragmentWo.setFocusable(false);
        mRlvFragmentWo.setHasFixedSize(true);
        mRlvFragmentWo.setNestedScrollingEnabled(false);

        /**
         * 为你推荐P层
         */
        recommendPresenter = new RecommendPresenter(this);

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
                if (logUser != null) {
                    Intent intent = new Intent(getActivity(), ObligationActivity.class);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }
        });

        /**
         * 登录
         */

        mTvFragmentWoName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (logUser == null) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }
        });

        /**
         * 点击收货地址跳转页面
         */
        mImgFragmentWoDizi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (logUser != null) {
                    Intent intent = new Intent(getActivity(), AiteActivity.class);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }
        });

        /**
         * 点击头像跳转页面
         */
        mImgFragmentWoTouxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (logUser != null) {
                    Intent intent = new Intent(getActivity(), MyaccountActivity.class);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }
        });

        /**
         * 点击售后/退款 进行页面跳转
         */
        mImgFragmentWoYituikuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (logUser != null) {
                    Intent intent = new Intent(getActivity(), AfterSaleActivity.class);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }
        });

        /**
         * 消息页面·
         */
        mImgFragmentWoLingdang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (logUser != null) {
                    startActivity(new Intent(getActivity(), MessageActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }
        });

        /**
         * 点击关于APP跳转页面
         */
        mImgFragmentWoguanyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (logUser != null) {
                    Intent intent = new Intent(getActivity(), ApoutUsActivity.class);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }
        });

        /**
         * 点击全部订单 跳转页面
         */
        mTvFragmentWoDingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (logUser != null) {
                    Intent intent = new Intent(getActivity(), OrderFormActivity.class);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }
        });
        /**
         * 优惠券
         */
        mImgFragmentWoyouhuiquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (logUser != null) {
                    Intent intent = new Intent(getActivity(), CouponActivity.class);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }
        });
    }

    public class AdvertisingICoreInfe implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            AdvertisingBean entity = (AdvertisingBean) request.getEntity();
            Glide.with(getContext()).load("https://www.yikch.com" + entity.getPreviewUrl()).into(mGuanggao);
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
        mRecommend_fragment_wo_adapter.removeAll();
        mRecommend_fragment_wo_adapter.addData(entity);
    }

    @Override
    public void fail(ApiException e) {

    }

    @Override
    public void onResume() {
        super.onResume();
        //查询数据库中是否有用户
        logUser = getLogUser(getContext());
        if (logUser != null) {
            int userId = logUser.getId();
            tuijian.setVisibility(View.VISIBLE);
            userInfoPresenter.request(userId);
            recommendPresenter.request(userId);
        } else {
            tuijian.setVisibility(View.GONE);
        }
    }

    /**
     * 用户资料
     */
    private class UserInfo implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            UserInfoBean entity = (UserInfoBean) request.getEntity();
            UserDetailBean userCenter = (UserDetailBean) entity.getUserCenter();
            if (request.isSuccess()) {
                setUserInfo(getContext(), userCenter);
            }
            mTvFragmentWoName.setText(userCenter.getNickName());
            //设置图片圆角角度
            Glide.with(getContext()).load("https://static.yikch.com" + userCenter.getAvatar())
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(mImgFragmentWoTouxiang);
        }

        @Override
        public void fail(ApiException e) {

        }
    }

}
