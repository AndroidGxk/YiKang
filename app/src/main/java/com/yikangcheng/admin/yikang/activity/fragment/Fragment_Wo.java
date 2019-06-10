package com.yikangcheng.admin.yikang.activity.fragment;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
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
import com.yikangcheng.admin.yikang.activity.seek.SeekActivity;
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

public class Fragment_Wo extends BaseFragment implements ICoreInfe, XRecyclerView.LoadingListener, View.OnClickListener {
    private TextView mTvFragmentWoDingdan;
    private LinearLayout mImgFragmentWoMingxi;
    private LinearLayout mImgFragmentWoguanyu;
    private LinearLayout mImgFragmentWoYizhifu;
    private LinearLayout mImgFragmentWoYituikuan;
    private TextView mTvFragmentWoXiaoxiA;
    private XRecyclerView mRlvFragmentWo;
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
    private RelativeLayout diviline;
    private RelativeLayout baseline;
    private RelativeLayout base_btn;
    private Recommend_Fragment_wo_Adapter mRecommend_fragment_wo_adapter;
    private ImageView mGuanggao, iv_toolBar_left, iv_toolBar_right;
    private UserInfoPresenter userInfoPresenter;
    private LoginBean logUser;
    private RelativeLayout mMingxi;
    private RecommendPresenter recommendPresenter;
    int mPage = 1;
    private NestedScrollView neste;
    private LinearLayout mImg_fragment_wo_daishouhuo;

    @Override
    protected void initView(View view) {
        //查询数据库中是否有用户
        logUser = getLogUser(getContext());
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
//        //已支付APP
        mImgFragmentWoYizhifu = view.findViewById(R.id.img_fragment_wo_yizhifu);
        //待收货
//        mImg_fragment_wo_daishouhuo = view.findViewById(R.id.img_fragment_wo_daishouhuo);
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
        //侧滑
        iv_toolBar_left = view.findViewById(R.id.iv_toolBar_left);
        //搜索
        iv_toolBar_right = view.findViewById(R.id.iv_toolBar_right);
        //滑动
        neste = view.findViewById(R.id.Neste);
        //底线
        diviline = view.findViewById(R.id.diviline);
        baseline = view.findViewById(R.id.baseline);
        base_btn = view.findViewById(R.id.base_btn);

        /**
         * 点击监听
         */
        mImgFragmentWoYiquxiao.setOnClickListener(this);
//        mImg_fragment_wo_daishouhuo.setOnClickListener(this);
        mMingxi.setOnClickListener(this);
        mImgFragmentWoDaifukuan.setOnClickListener(this);
        mTvFragmentWoName.setOnClickListener(this);
        mImgFragmentWoTouxiang.setOnClickListener(this);
        mTvFragmentWoDingdan.setOnClickListener(this);
        mImgFragmentWoyouhuiquan.setOnClickListener(this);
        mImgFragmentWoDizi.setOnClickListener(this);
        mImgFragmentWoYituikuan.setOnClickListener(this);
        mImgFragmentWoguanyu.setOnClickListener(this);
        mImgFragmentWoLingdang.setOnClickListener(this);
        mImgFragmentWoYizhifu.setOnClickListener(this);

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

        //接口回调
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
        int spanCount_tuijian = 1; // 3 columns
        int spacing_tuijian = 20; // 50px
        boolean includeEdge_tuijian = true;
        mRlvFragmentWo.addItemDecoration(new SpacesItemDecoration(spanCount_tuijian, spacing_tuijian, includeEdge_tuijian));


        if (logUser != null)
            recommendPresenter.request(logUser.getId(), 1, mPage);
        mRlvFragmentWo.setLoadingMoreEnabled(true);
        neste.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //判断是否滑到的底部
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    mRlvFragmentWo.setLoadingMoreEnabled(true);//调用刷新控件对应的加载更多方法
                    onLoadMore();
                }
            }
        });
        iv_toolBar_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SeekActivity.class));
            }
        });
        iv_toolBar_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onclick();
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        mRecommend_fragment_wo_adapter.removeAll();
    }

    @Override
    public void onLoadMore() {
        mPage++;
        recommendPresenter.request(logUser.getId(), 1, mPage);
    }

    /**
     * 点击事件
     *
     * @param
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //已取消
            case R.id.img__fragment_wo_yiquxiao:
                startActivity(new Intent(getActivity(), CanceledActivity.class));
                break;
            //待收货
//            case R.id.img_fragment_wo_daishouhuo:
//                startActivity(new Intent(getActivity(), WaitForReceivingActivity.class));
//                break;
            //账户明细
            case R.id.relativeLayout_mingxi_fragment_wo:
                startActivity(new Intent(getActivity(), DetailActivity.class));
                break;
            //待付款
            case R.id.img_fragment_wo_daifukuan:
                if (logUser != null) {
                    startActivity(new Intent(getActivity(), ObligationActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            //登录
            case R.id.tv__fragment_wo_name:
                if (logUser == null) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            //头像
            case R.id.img_fragment_wo_touxiang:
                if (logUser != null) {
                    startActivity(new Intent(getActivity(), MyaccountActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            //全部订单
            case R.id.tv_fragment_wo_dingdan:
                if (logUser != null) {
                    startActivity(new Intent(getActivity(), OrderFormActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            //优惠劵
            case R.id.img__fragment_wo_gouwuche:
                if (logUser != null) {
//                    startActivity(new Intent(getActivity(), CouponActivity.class));
                } else {
//                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            //收货地址
            case R.id.img_fragment_wo_dizi:
                if (logUser != null) {
                    startActivity(new Intent(getActivity(), AiteActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            //售后/退款
            case R.id.img_fragment_wo_yituikuan:
                if (logUser != null) {
//                    startActivity(new Intent(getActivity(), AfterSaleActivity.class));
                } else {
//                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            //关于App
            case R.id.img__fragment_wo_guanyu:
                if (logUser != null) {
                    startActivity(new Intent(getActivity(), ApoutUsActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            //铃铛——消息页面
            case R.id.img_fragment_wo_lingdang:
                if (logUser != null) {
//                    startActivity(new Intent(getActivity(), MessageActivity.class));
                } else {
//                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            //已支付
            case R.id.img_fragment_wo_yizhifu:
                startActivity(new Intent(getActivity(), PaidActivity.class));
                break;
        }
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
        mRecommend_fragment_wo_adapter.addData(entity);
        if (entity.size() == 0) {
            baseline.setVisibility(View.VISIBLE);
            diviline.setVisibility(View.VISIBLE);
            base_btn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void fail(ApiException e) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (logUser != null) {
            int userId = logUser.getId();
            tuijian.setVisibility(View.VISIBLE);
            userInfoPresenter.request(userId);
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
            if (userCenter.getNickName().equals("")) {
                mTvFragmentWoName.setText(userCenter.getMobile() + "");
            } else {
                mTvFragmentWoName.setText(userCenter.getNickName());
            }
            if (userCenter.getAvatar().equals("")) {
                mImgFragmentWoTouxiang.setBackgroundResource(R.drawable.touxiang_2);
            } else {
                //设置图片圆角角度
                Glide.with(getContext()).load("https://static.yikch.com" + userCenter.getAvatar())
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into(mImgFragmentWoTouxiang);
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    /**
     * 侧滑
     */
    onClickListener onClickListener;

    public void setOnClickListener(Fragment_Wo.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface onClickListener {
        void onclick();
    }
}
