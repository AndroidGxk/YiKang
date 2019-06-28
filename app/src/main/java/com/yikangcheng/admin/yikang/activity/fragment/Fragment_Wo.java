package com.yikangcheng.admin.yikang.activity.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.ApoutUsActivity;
import com.yikangcheng.admin.yikang.activity.H5SecActivity;
import com.yikangcheng.admin.yikang.activity.LoginActivity;
import com.yikangcheng.admin.yikang.activity.OrderFormActivity;
import com.yikangcheng.admin.yikang.activity.adapter.Orderform_ViewPagerAdapter;
import com.yikangcheng.admin.yikang.activity.coupon.CouponActivity;
import com.yikangcheng.admin.yikang.activity.fragment.goodsrecommtion.Good_Recommiton_Day;
import com.yikangcheng.admin.yikang.activity.fragment.goodsrecommtion.Good_Recommiton_Home;
import com.yikangcheng.admin.yikang.activity.fragment.goodsrecommtion.Good_Recommiton_Three;
import com.yikangcheng.admin.yikang.activity.fragment.goodsrecommtion.Good_Recommiton_fash;
import com.yikangcheng.admin.yikang.activity.fragment.goodsrecommtion.Good_Recommiton_snack;
import com.yikangcheng.admin.yikang.activity.myaccount.MyaccountActivity;
import com.yikangcheng.admin.yikang.activity.obligation.CanceledActivity;
import com.yikangcheng.admin.yikang.activity.obligation.ObligationActivity;
import com.yikangcheng.admin.yikang.activity.obligation.PaidActivity;
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
import com.yikangcheng.admin.yikang.presenter.InitOrderCountPresenter;
import com.yikangcheng.admin.yikang.presenter.RecommendPresenter;
import com.yikangcheng.admin.yikang.presenter.UserInfoPresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

public class Fragment_Wo extends BaseFragment implements View.OnClickListener {
    private TextView mTvFragmentWoDingdan;
    private LinearLayout mImgFragmentWoMingxi;
    private LinearLayout mImgFragmentWoguanyu;
    private LinearLayout mImgFragmentWoYizhifu;
    private LinearLayout mImgFragmentWoYituikuan;
    private TextView mTvFragmentWoXiaoxiA;
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
    private ImageView mGuanggao, iv_toolBar_left, iv_toolBar_right;
    private UserInfoPresenter userInfoPresenter;
    private LoginBean logUser;
    private RelativeLayout mMingxi, tou_rela;
    private LinearLayout tou_lin, tou_lin2;
    private LinearLayout shop_btn, gobuy_btn, seckill_btn, booking_btn, ge_line;
    private ImageView tou_lin3, bo_phone;
    private TextView text_fuli, tou_text, tv_toolBar_title;
    private InitOrderCountPresenter initOrderCountPresenter;
    private ImageView shop_car, shop_buy, shop_booking, shop_seckill, si_geren_qun;
    private TabLayout mTabActivityOrderform, tab1;
    private ViewPager mViewPagerOrderform;
    private NestedScrollView neste;
    //控件高度
    private double mViewHeight;
    private double mViewHeight2;
    private RecommendPresenter recommendPresenter;
    private PromptDialog promptDialog;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initView(View view) {
        //创建对象
        promptDialog = new PromptDialog(getActivity());
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);
        //查询数据库中是否有用户
        logUser = getLogUser(getContext());
        //广告图
        mGuanggao = view.findViewById(R.id.img_fragment_wo_guanggao);
        //滑动
        neste = view.findViewById(R.id.Neste);
        //查看全部订单
        mTvFragmentWoDingdan = view.findViewById(R.id.tv_fragment_wo_dingdan);
        ge_line = view.findViewById(R.id.ge_line);
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
        //title
        tv_toolBar_title = view.findViewById(R.id.tv_toolBar_title);
        //待付款
        mImgFragmentWoDaifukuan = view.findViewById(R.id.img_fragment_wo_daifukuan);
        //已取消
        mImgFragmentWoYiquxiao = view.findViewById(R.id.img__fragment_wo_yiquxiao);
        //收货地址
        mImgFragmentWoDizi = view.findViewById(R.id.img_fragment_wo_dizi);
        //优惠券
        mImgFragmentWoyouhuiquan = view.findViewById(R.id.img__fragment_wo_gouwuche);
        //tablyout
        mTabActivityOrderform = view.findViewById(R.id.tab_activity_orderform);
        tab1 = view.findViewById(R.id.tab1);
        mViewPagerOrderform = view.findViewById(R.id.viewPager_orderform);
        //用户名
        mTvFragmentWoName = view.findViewById(R.id.tv__fragment_wo_name);
        //账户明细
        mMingxi = view.findViewById(R.id.relativeLayout_mingxi_fragment_wo);
        //挚友超市
        shop_btn = view.findViewById(R.id.shop_btn);
        gobuy_btn = view.findViewById(R.id.gobuy_btn);
        seckill_btn = view.findViewById(R.id.seckill_btn);
        booking_btn = view.findViewById(R.id.booking_btn);
        //侧滑
        iv_toolBar_left = view.findViewById(R.id.iv_toolBar_left);
        //搜索
        iv_toolBar_right = view.findViewById(R.id.iv_toolBar_right);
        tou_rela = view.findViewById(R.id.tou_rela);
        tou_lin = view.findViewById(R.id.tou_lin);
        tou_lin2 = view.findViewById(R.id.tou_lin2);
        bo_phone = view.findViewById(R.id.bo_phone);
        tou_lin3 = view.findViewById(R.id.tou_lin3);
        text_fuli = view.findViewById(R.id.text_fuli);
        tou_text = view.findViewById(R.id.tou_text);
        //图片
        shop_car = view.findViewById(R.id.shop_car);
        shop_buy = view.findViewById(R.id.shop_buy);
        shop_seckill = view.findViewById(R.id.shop_seckill);
        shop_booking = view.findViewById(R.id.shop_booking);
        si_geren_qun = view.findViewById(R.id.si_geren_qun);
        Glide.with(getContext()).load(R.drawable.shop_car_gif)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(shop_car);
        Glide.with(getContext()).load(R.drawable.buy_gif)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(shop_buy);
        Glide.with(getContext()).load(R.drawable.sekill_gif)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(shop_seckill);
        Glide.with(getContext()).load(R.drawable.pintuan_gif)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(shop_booking);
        Glide.with(getContext()).load(R.drawable.si_grenzhongxin)
                .into(si_geren_qun);
        //tabLayout
        final List<String> strings = new ArrayList<>();
        strings.add("家电");
        strings.add("日用");
        strings.add("3C");
        strings.add("时尚");
        strings.add("零食");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Good_Recommiton_Home());
        fragments.add(new Good_Recommiton_Day());
        fragments.add(new Good_Recommiton_Three());
        fragments.add(new Good_Recommiton_fash());
        fragments.add(new Good_Recommiton_snack());
        Orderform_ViewPagerAdapter orderform_viewPagerAdapter = new Orderform_ViewPagerAdapter(getChildFragmentManager(), strings, fragments);
        mViewPagerOrderform.setAdapter(orderform_viewPagerAdapter);
        mTabActivityOrderform.setupWithViewPager(mViewPagerOrderform);
        tab1.setupWithViewPager(mViewPagerOrderform);
        //设置下划线长度
        mTabActivityOrderform.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(mTabActivityOrderform, 10, 10);
            }
        });
        //设置下划线长度
        tab1.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tab1, 10, 10);
            }
        });
        /**
         * 点击tab字体变大
         */
        mTabActivityOrderform.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //这是选中状态
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                    //寻找item布局
                    tab.setCustomView(R.layout.custom_tab_layout_text);
                }
                //找控件
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                //设置文字加粗
                textView.setTextColor(mTabActivityOrderform.getTabTextColors());
                //设置文字字体大小
                textView.setTextSize(15);
                //这是设置字体
//                textView.setTypeface(Typeface.DEFAULT_BOLD);
            }

            //未选中状态
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                    //寻找item布局
                    tab.setCustomView(R.layout.custom_tab_layout_text);
                }
                //找控件
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                //设置当未选中状态字体大小
                textView.setTextSize(12);
                //设置字体
//                textView.setTypeface(Typeface.DEFAULT);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tab1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //这是选中状态
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                    //寻找item布局
                    tab.setCustomView(R.layout.custom_tab_layout_text);
                }
                //找控件
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                //设置文字加粗
                textView.setTextColor(tab1.getTabTextColors());
                //设置文字字体大小
                textView.setTextSize(15);
                //这是设置字体
//                textView.setTypeface(Typeface.DEFAULT_BOLD);

            }

            //未选中状态
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                    //寻找item布局
                    tab.setCustomView(R.layout.custom_tab_layout_text);
                }
                //找控件
                TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
                //设置当未选中状态字体大小
                textView.setTextSize(12);
                //设置字体
//                textView.setTypeface(Typeface.DEFAULT);
                neste.scrollTo(0, (int) mViewHeight);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        neste.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                int i4 = i1 - (int) mViewHeight;
                if (i1 > mViewHeight) {
                    tab1.setVisibility(View.VISIBLE);
                    mTabActivityOrderform.setVisibility(View.INVISIBLE);
                    tv_toolBar_title.setText("精品推荐");
                } else if (0 >= i4) {
                    tab1.setVisibility(View.GONE);
                    mTabActivityOrderform.setVisibility(View.VISIBLE);
                    tv_toolBar_title.setText("个人中心");
                }
                if (i1 - (int) mViewHeight == 0) {
                    tv_toolBar_title.setText("精品推荐");
                }
            }
        });
        /**
         * 控件高度
         */
        getHeight();
        /**
         * 点击监听
         */
        mImgFragmentWoYiquxiao.setOnClickListener(this);
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
        booking_btn.setOnClickListener(this);
        gobuy_btn.setOnClickListener(this);
        seckill_btn.setOnClickListener(this);
        si_geren_qun.setOnClickListener(this);
        shop_btn.setOnClickListener(this);
        bo_phone.setOnClickListener(this);
        //查询待付款个数
        initOrderCountPresenter = new InitOrderCountPresenter(new InitOrder());
        //接口请求
        userInfoPresenter = new UserInfoPresenter(new UserInfo());
        //查询
        recommendPresenter = new RecommendPresenter(new RecommendGood());

        /**
         * 广告图P层
         */
        AdvertisingPresenter advertisingPresenter = new AdvertisingPresenter(new AdvertisingICoreInfe());
        advertisingPresenter.request();
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

    private void getHeight() {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        tou_rela.measure(w, h);
        mTabActivityOrderform.measure(w, h);
        int height = tou_rela.getMeasuredHeight();
        int measuredHeight = mTabActivityOrderform.getMeasuredHeight();
        mViewHeight += height;
        mViewHeight2 += height;
        mViewHeight2 += measuredHeight;
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
            //账户明细
            case R.id.relativeLayout_mingxi_fragment_wo:
//                startActivity(new Intent(getActivity(), DetailActivity.class));
                Toast.makeText(getContext(), "敬请期待", Toast.LENGTH_SHORT).show();
                break;
            //待付款
            case R.id.img_fragment_wo_daifukuan:
                if (logUser != null) {
                    startActivity(new Intent(getActivity(), ObligationActivity.class));
                }
                break;
            //登录
            case R.id.tv__fragment_wo_name:
                break;
            //头像
            case R.id.img_fragment_wo_touxiang:
                if (logUser != null) {
                    startActivity(new Intent(getActivity(), MyaccountActivity.class));
                }
                break;
            //全部订单
            case R.id.tv_fragment_wo_dingdan:
                if (logUser != null) {
                    startActivity(new Intent(getActivity(), OrderFormActivity.class));
                }
                break;
            //优惠劵
            case R.id.img__fragment_wo_gouwuche:
                startActivity(new Intent(getActivity(), CouponActivity.class));
                break;
            //收货地址
            case R.id.img_fragment_wo_dizi:
                if (logUser != null) {
                    startActivity(new Intent(getActivity(), AiteActivity.class));
                }
                break;
            //全部订单
            case R.id.img_fragment_wo_yituikuan:
                if (logUser != null) {
//                    startActivity(new Intent(getActivity(), OrderFormActivity.class));
                    Toast.makeText(getContext(), "敬请期待", Toast.LENGTH_SHORT).show();
                }
                break;
            //关于App
            case R.id.img__fragment_wo_guanyu:
                if (logUser != null) {
                    startActivity(new Intent(getActivity(), ApoutUsActivity.class));
                }
                break;
            //铃铛——消息页面
            case R.id.img_fragment_wo_lingdang:
                break;
            //待收货
            case R.id.img_fragment_wo_yizhifu:
                startActivity(new Intent(getActivity(), PaidActivity.class));
                break;
            //挚友超市
            case R.id.shop_btn:
                Intent intent = new Intent(getContext(), H5SecActivity.class);
                intent.putExtra("none", "yes");
                intent.putExtra("http", "https://www.yikch.com/mobile/appShow/market?type=android");
                startActivity(intent);
                break;
            //超惠购
            case R.id.gobuy_btn:
                Intent gobuy_btn = new Intent(getContext(), H5SecActivity.class);
                gobuy_btn.putExtra("none", "yes");
                gobuy_btn.putExtra("http", "https://www.yikch.com/mobile/appShow/superToday?type=android");
                startActivity(gobuy_btn);
                break;
            //周秒杀
            case R.id.seckill_btn:
                Intent seckill_btn = new Intent(getContext(), H5SecActivity.class);
                seckill_btn.putExtra("http", "https://www.yikch.com/mobile/appShow/activity?type=android");
                startActivity(seckill_btn);
                break;
            //拼团
            case R.id.booking_btn:
                Intent booking_btn = new Intent(getContext(), H5SecActivity.class);
                booking_btn.putExtra("http", "https://www.yikch.com/mobile/appShow/activity1?type=android");
                startActivity(booking_btn);
                break;
            /**
             * 会员福利社
             */
            case R.id.si_geren_qun:
                Intent tou_lin3 = new Intent(getContext(), H5SecActivity.class);
                tou_lin3.putExtra("title", "会员福利社");
                tou_lin3.putExtra("http", "https://www.yikch.com/mobile/appShow/memberBenefits?type=android");
                startActivity(tou_lin3);
                break;
            /**
             * 拨打营养师号码
             */
            case R.id.bo_phone:
                Intent bo_phone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "010-57690370"));
                startActivity(bo_phone);
                break;
        }
    }

    //按钮的定义，创建一个按钮的对象
    final PromptButton confirm = new PromptButton("确定", new PromptButtonListener() {
        @Override
        public void onClick(PromptButton button) {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getDelete(getContext());
            getActivity().finish();
        }
    });

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
        if (logUser != null)
            recommendPresenter.request(logUser.getId(), 1, 1);
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_wo;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (logUser != null) {
            int userId = logUser.getId();
            userInfoPresenter.request(userId);
            initOrderCountPresenter.request(logUser.getId());
        } else {
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
                if (userCenter.getAvatar().contains("http://") || userCenter.getAvatar().contains("https://")) {
                    Glide.with(getContext()).load(userCenter.getAvatar())
                            .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                            .into(mImgFragmentWoTouxiang);
                } else {
                    Glide.with(getContext()).load("https://static.yikch.com" + userCenter.getAvatar())
                            .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                            .into(mImgFragmentWoTouxiang);
                }
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

    /**
     * 待付款个数
     */
    private class InitOrder implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            double num = (double) request.getEntity();
            int i = (int) num;
            if (i == 0) {
                mImgFragmentWoHongyuanquanB.setVisibility(View.GONE);
                mTvFragmentWoXiaoxiB.setVisibility(View.GONE);
            } else {
                mImgFragmentWoHongyuanquanB.setVisibility(View.VISIBLE);
                mTvFragmentWoXiaoxiB.setVisibility(View.VISIBLE);
                if (i > 99) {
                    mTvFragmentWoXiaoxiB.setText("99+");
                } else {
                    if (i < 10) {
                        mTvFragmentWoXiaoxiB.setText("0" + i);
                    } else {
                        mTvFragmentWoXiaoxiB.setText("" + i);
                    }
                }
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    /**
     * 设置指示器长度
     */
    /**
     * 通过反射机制 修改TableLayout 的下划线长度
     */

    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        //通过反射获取到
        Class tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //设置模式
        tabStrip.setAccessible(true);
        //获得tabview
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //设置tabView的padding为0，并且设置了margin
        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());
        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    /**
     * 推荐商品
     */
    private class RecommendGood implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            List<RecommendBean> recommendBeans = (List<RecommendBean>) request.getEntity();
            if (recommendBeans == null) {
                mTabActivityOrderform.setVisibility(View.GONE);
                mViewPagerOrderform.setVisibility(View.GONE);
                tou_text.setVisibility(View.GONE);
            } else {
                mTabActivityOrderform.setVisibility(View.VISIBLE);
                mViewPagerOrderform.setVisibility(View.VISIBLE);
                tou_text.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
