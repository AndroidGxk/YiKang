package com.yikangcheng.admin.yikang.activity.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.hjq.toast.ToastUtils;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.ApoutUsActivity;
import com.yikangcheng.admin.yikang.activity.BankActivity;
import com.yikangcheng.admin.yikang.activity.GoodCommCentreActivity;
import com.yikangcheng.admin.yikang.activity.H5SecActivity;
import com.yikangcheng.admin.yikang.activity.LoginActivity;
import com.yikangcheng.admin.yikang.activity.MainActivity;
import com.yikangcheng.admin.yikang.activity.OrderFormActivity;
import com.yikangcheng.admin.yikang.activity.PhotoActivity;
import com.yikangcheng.admin.yikang.activity.PhotoBigActivity;
import com.yikangcheng.admin.yikang.activity.ProtocolActivity;
import com.yikangcheng.admin.yikang.activity.adapter.Orderform_ViewPagerAdapter;
import com.yikangcheng.admin.yikang.activity.coupon.CouponActivity;
import com.yikangcheng.admin.yikang.activity.fragment.goodsrecommtion.Good_Recommiton_Chu;
import com.yikangcheng.admin.yikang.activity.fragment.goodsrecommtion.Good_Recommiton_Day;
import com.yikangcheng.admin.yikang.activity.fragment.goodsrecommtion.Good_Recommiton_Home;
import com.yikangcheng.admin.yikang.activity.fragment.goodsrecommtion.Good_Recommiton_fash;
import com.yikangcheng.admin.yikang.activity.fragment.goodsrecommtion.Good_Recommiton_snack;
import com.yikangcheng.admin.yikang.activity.h5activity.H5MessActivity;
import com.yikangcheng.admin.yikang.activity.myaccount.MyaccountActivity;
import com.yikangcheng.admin.yikang.activity.obligation.DetailActivity;
import com.yikangcheng.admin.yikang.activity.siteactivity.AiteActivity;
import com.yikangcheng.admin.yikang.app.BaseApp;
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
import com.yikangcheng.admin.yikang.util.FloatTouchListener;
import com.yikangcheng.admin.yikang.util.MyViewPager;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

public class Fragment_Wo extends BaseFragment implements View.OnClickListener {
    private TextView mTvFragmentWoDingdan;
    private LinearLayout mImgFragmentWoMingxi;
    private LinearLayout mImgFragmentWoguanyu;
    private LinearLayout mImgFragmentWoYizhifu;
    private LinearLayout mImgFragmentWoYituikuan;
    private LinearLayout mess_img;
    private TextView mTvFragmentWoXiaoxiB;
    private ImageView mImgFragmentWoTouxiang;
    private ImageView mImgFragmentWoHongyuanquanB;
    private ImageView mImgFragmentWoLingdang;
    private LinearLayout mImgFragmentWoDaifukuan;
    private LinearLayout mImgFragmentWoYiquxiao;
    private LinearLayout mImgFragmentWoDizi;
    private LinearLayout mImgFragmentWoyouhuiquan;
    private TextView mTvFragmentWoName;
    private ImageView mGuanggao, iv_toolBar_left;
    private UserInfoPresenter userInfoPresenter;
    private LoginBean logUser;
    private RelativeLayout mMingxi, tou_rela, rela;
    private LinearLayout tou_lin, tou_lin2;
    private LinearLayout shop_btn, gobuy_btn, seckill_btn, booking_btn, ge_line;
    private ImageView tou_lin3, bo_phone, beijing, daifukan_img, daishouhuo_img, yiwancheng_img, quxiao_img;
    private TextView text_fuli, tou_text, tv_toolBar_title;
    private InitOrderCountPresenter initOrderCountPresenter;
    private ImageView shop_car, shop_buy, shop_booking, shop_seckill, si_geren_qun;
    private ImageView you_img, guanyu, address, zhang;
    private TabLayout mTabActivityOrderform, tab1;
    private MyViewPager mViewPagerOrderform;
    private NestedScrollView neste;
    //控件高度
    private double mViewHeight;
    private RecommendPresenter recommendPresenter;
    private PromptDialog promptDialog;
    private FloatTouchListener mFloatTouchListener;
    private int margin;
    @BindView(R.id.fl_child)
    FrameLayout flChild;
    @BindView(R.id.flParent)
    FrameLayout flParent;
    @BindView(R.id.comm_img)
    ImageView comm_img;
    @BindView(R.id.shaer_btn)
    TextView shaer_btn;
    @BindView(R.id.xiaoxi)
    ImageView xiaoxi;
    private View contentMeiView;
    private Dialog bottomDialog;
    private int width;
    private int height;
    private LinearLayout wx_pengyouquan, wx_haoyou;
    @BindView(R.id.title)
    TextView title;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initView(View view) {
        String wo = BaseApp.getApp().getSharedPreferences("wo", Context.MODE_PRIVATE).getString("wo", "");
        bottomDialog = new Dialog(getContext(), R.style.BottomDialog);
        rela = (RelativeLayout) view.findViewById(R.id.rela);
        rela.setBackgroundColor(Color.parseColor(getLogUser(getContext()).getThemeColors()));
        //标题颜色
//        title.setTextColor(Color.parseColor());
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
        mess_img = view.findViewById(R.id.mess_img);
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
        //这是待付款上面的提示消息
        mTvFragmentWoXiaoxiB = view.findViewById(R.id.tv_fragment_wo_xiaoxi_b);
        //这是头像
        mImgFragmentWoTouxiang = view.findViewById(R.id.img_fragment_wo_touxiang);
        //这是待付款上面的红点点
        mImgFragmentWoHongyuanquanB = view.findViewById(R.id.img_fragment_wo_hongyuanquan_b);
//        mImgFragmentWoHongyuanquanB.setColorFilter(Color.parseColor(getLogUser(getContext()).getThemeColors()));
        //这是铃铛
        mImgFragmentWoLingdang = view.findViewById(R.id.img_fragment_wo_lingdang);
        //title
        tv_toolBar_title = view.findViewById(R.id.title);
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
        mViewPagerOrderform = view.findViewById(R.id.viewPager_orderforms);
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
        tou_rela = view.findViewById(R.id.tou_rela);
        tou_lin = view.findViewById(R.id.tou_lin);
        tou_lin2 = view.findViewById(R.id.tou_lin2);
        bo_phone = view.findViewById(R.id.bo_phone);
        daifukan_img = view.findViewById(R.id.daifukan_img);
        daishouhuo_img = view.findViewById(R.id.daishouhuo_img);
        yiwancheng_img = view.findViewById(R.id.yiwancheng_img);
        quxiao_img = view.findViewById(R.id.quxiao_img);
        beijing = view.findViewById(R.id.beijing);
        tou_lin3 = view.findViewById(R.id.tou_lin3);
        text_fuli = view.findViewById(R.id.text_fuli);
        tou_text = view.findViewById(R.id.tou_text);
        //图片
        shop_car = view.findViewById(R.id.shop_car);
        you_img = view.findViewById(R.id.you_img);
        guanyu = view.findViewById(R.id.guanyu);
        address = view.findViewById(R.id.address);
        zhang = view.findViewById(R.id.zhang);
        shop_buy = view.findViewById(R.id.shop_buy);
        shop_seckill = view.findViewById(R.id.shop_seckill);
        shop_booking = view.findViewById(R.id.shop_booking);
        si_geren_qun = view.findViewById(R.id.si_geren_qun);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.si_grenzhongxin);
        requestOptions.fallback(R.drawable.si_grenzhongxin);
        RequestOptions requestOptions1 = new RequestOptions();
        requestOptions1.placeholder(R.drawable.comm_fanxiangou);
        requestOptions1.fallback(R.drawable.comm_fanxiangou);
        Glide.with(getContext()).load(R.drawable.comm_fanxiangou).apply(requestOptions1)
                .into(comm_img);
        if (wo.equals("yes")) {
            Glide.with(getContext()).load(R.drawable.tuichuuser)
                    .into(xiaoxi);
            mess_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    promptDialog.showWarnAlert("确定要退出登录吗？", new PromptButton("取消", new PromptButtonListener() {
                        @Override
                        public void onClick(PromptButton button) {
                        }
                    }), confirm);
                }
            });
            tou_lin2.setBackgroundResource(R.drawable.three_bg);
            rela.setBackgroundResource(R.color.clolrBAai);
            iv_toolBar_left.setVisibility(View.GONE);
            contentMeiView = View.inflate(getContext(), R.layout.wxshaer_item,
                    null);
        } else {
            Glide.with(getContext()).load(R.drawable.wodebeijing)
                    .into(beijing);
            Glide.with(getContext()).load(R.drawable.xiaoxi_seek)
                    .into(xiaoxi);
            Glide.with(getContext()).load(R.drawable.gerenzhongxin_fukuan)
                    .into(daifukan_img);
            Glide.with(getContext()).load(R.drawable.gerenzhongxin_shouhuo)
                    .into(daishouhuo_img);
            Glide.with(getContext()).load(R.drawable.gerenzhongxin_wancheng)
                    .into(yiwancheng_img);
            Glide.with(getContext()).load(R.drawable.gerenzhongxin_pinglun)
                    .into(quxiao_img);
            Glide.with(getContext()).load(R.drawable.wo_youhuiquan)
                    .into(you_img);
            Glide.with(getContext()).load(R.drawable.guanyuruanjian)
                    .into(guanyu);
            Glide.with(getContext()).load(R.drawable.yonghudizhi)
                    .into(address);
            Glide.with(getContext()).load(R.drawable.zhanghuminggxi)
                    .into(zhang);
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
            tou_lin2.setBackgroundResource(R.drawable.pink_bg);
            rela.setBackgroundColor(Color.parseColor(getLogUser(getContext()).getThemeColors()));
            iv_toolBar_left.setVisibility(View.VISIBLE);
            contentMeiView = View.inflate(getContext(), R.layout.wxshaer_item,
                    null);
            mess_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getContext(), H5MessActivity.class));
                }
            });
        }
        //tabLayout
        final List<String> strings = new ArrayList<>();
        strings.add("家电");
        strings.add("日用");
        strings.add("厨卫");
        strings.add("护肤");
        strings.add("零食");
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Good_Recommiton_Home());
        fragments.add(new Good_Recommiton_Day());
        fragments.add(new Good_Recommiton_Chu());
        fragments.add(new Good_Recommiton_fash());
        fragments.add(new Good_Recommiton_snack());
        Orderform_ViewPagerAdapter orderform_viewPagerAdapter = new Orderform_ViewPagerAdapter(getChildFragmentManager(), strings, fragments);
        mViewPagerOrderform.setAdapter(orderform_viewPagerAdapter);
        mViewPagerOrderform.setOffscreenPageLimit(5);
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
        changeTextColor(tab1);
        changeTextColor(mTabActivityOrderform);
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
        mViewPagerOrderform.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mViewPagerOrderform.resetHeight(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        Display display = getActivity().getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        /**
         *  控件高度
         */
        getHeight();
        initBarColor();
        setTouchListener();
        /**
         *  点击监听
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
        tou_lin3.setOnClickListener(this);
        //查询待付款个数
        initOrderCountPresenter = new InitOrderCountPresenter(new InitOrder());
        //接口请求
        userInfoPresenter = new UserInfoPresenter(new UserInfo());
        //查询
        recommendPresenter = new RecommendPresenter(new RecommendGood());

        tab1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //这是选中状态
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            //未选中状态
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                neste.scrollTo(0, mTabActivityOrderform.getTop());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        /**
         *  广告图P层
         */
        AdvertisingPresenter advertisingPresenter = new AdvertisingPresenter(new AdvertisingICoreInfe());
        advertisingPresenter.request();
        iv_toolBar_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onclick();
                }
            }
        });
        shaer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置dialog的宽高为屏幕的宽高
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
                bottomDialog.setContentView(contentMeiView, layoutParams);
                bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
                bottomDialog.setCanceledOnTouchOutside(true);
                bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialogs);
                bottomDialog.show();
                wx_pengyouquan = contentMeiView.findViewById(R.id.wx_pengyou);
                wx_haoyou = contentMeiView.findViewById(R.id.wx_haoyou);
                wx_pengyouquan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        wechatShare(1);
                        bottomDialog.dismiss();
                    }
                });
                wx_haoyou.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        wechatShare(0);
                        bottomDialog.dismiss();
                    }
                });
            }
        });
    }

    //按钮的定义，创建一个按钮的对象
    final PromptButton confirm = new PromptButton("确定", new PromptButtonListener() {
        @Override
        public void onClick(PromptButton button) {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            //进行查看账号之前是否存在如果存在就不添加只修改状态
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            LoginBean loginBean = getLogUser(BaseApp.getApp());
            loginBean.setStatus(2);
            getDelete(getContext());
            setLogUser(BaseApp.getApp(), loginBean);
            getActivity().finish();
        }
    });
    private void changeTextColor(TabLayout tabLayout) {
        try {
            //拿到tabLayout的mTabStrip属性
            Field field = TabLayout.class.getDeclaredField("mTabStrip");
            field.setAccessible(true);
            //拿mTabStrip属性里面的值
            Object mTabStrip = field.get(tabLayout);
            //通过mTabStrip对象来获取class类，不能用field来获取class类，参数不能写Integer.class
            Method method = mTabStrip.getClass().getDeclaredMethod("setSelectedIndicatorColor", int.class);
            method.setAccessible(true);
            method.invoke(mTabStrip, Color.parseColor(getLogUser(getContext()).getThemeColors()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getHeight() {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        tou_rela.measure(w, h);
        mTabActivityOrderform.measure(w, h);
        int height = tou_rela.getMeasuredHeight();
        mViewHeight += height;
    }

    /**
     * 提供子fragment调用方法，解决listview高度不自适应出现多余空白
     */
    public void setChildObjectForPosition(View view, int poistion) {
        mViewPagerOrderform.setObjectForPosition(view, poistion);
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_wo;
    }

    private void setTouchListener() {
        margin = (int) (10 * getResources().getDisplayMetrics().density + 0.5f);
        mFloatTouchListener = new FloatTouchListener(BaseApp.getApp(), flParent, flChild, margin);
        flChild.setOnTouchListener(mFloatTouchListener);
        flChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 评论返现购
                 */
                Intent intent = new Intent(getContext(), H5SecActivity.class);
                intent.putExtra("http", "https://www.yikch.com/mobile/appShow/praisePurchase?type=android&userId=" + getLogUser(getContext()).getId() + "");
                intent.putExtra("title", "评论返现购");
                startActivity(intent);
            }
        });
    }

    private void initBarColor() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getActivity().getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getActivity().getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 点击事件
     *
     * @param
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //待评价
            case R.id.img__fragment_wo_yiquxiao:
                startActivity(new Intent(getContext(), GoodCommCentreActivity.class));
                break;
            //账户明细
            case R.id.relativeLayout_mingxi_fragment_wo:
//                ToastUtils.show("功能暂未开放，敬请期待！");
                startActivity(new Intent(getActivity(), DetailActivity.class));

                break;
            //待付款
            case R.id.img_fragment_wo_daifukuan:
                if (logUser != null) {
                    Intent img_fragment_wo_daifukuan = new Intent(getActivity(), OrderFormActivity.class);
                    img_fragment_wo_daifukuan.putExtra("tab", "daifukuan");
                    startActivity(img_fragment_wo_daifukuan);
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
                Intent img_fragment_wo_yizhifu = new Intent(getActivity(), OrderFormActivity.class);
                img_fragment_wo_yizhifu.putExtra("tab", "daishouhuo");
                startActivity(img_fragment_wo_yizhifu);
                break;
            //已完成
            case R.id.img_fragment_wo_yituikuan:
                Intent img_fragment_wo_yituikuan = new Intent(getActivity(), OrderFormActivity.class);
                img_fragment_wo_yituikuan.putExtra("tab", "yiwancheng");
                startActivity(img_fragment_wo_yituikuan);
                break;
            //挚友超市
            case R.id.shop_btn:
                Intent intent = new Intent(getContext(), H5SecActivity.class);
                intent.putExtra("none", "no");
                intent.putExtra("http", "https://www.yikch.com/mobile/appShow/market?type=android&userId=" + getLogUser(BaseApp.getApp()).getId());
                startActivity(intent);
                break;
            //超惠购
            case R.id.gobuy_btn:
                Intent gobuy_btn = new Intent(getContext(), H5SecActivity.class);
                gobuy_btn.putExtra("http", "https://www.yikch.com/mobile/appShow/superToday?type=android&userId=" + getLogUser(BaseApp.getApp()).getId());
                gobuy_btn.putExtra("title", "超惠购");
                startActivity(gobuy_btn);
                break;
            //周秒杀
            case R.id.seckill_btn:
                Intent seckill_btn = new Intent(getContext(), H5SecActivity.class);
                seckill_btn.putExtra("http", "https://www.yikch.com/mobile/appShow/activity?type=android&userId=" + getLogUser(BaseApp.getApp()).getId());
                seckill_btn.putExtra("title", "周秒杀");
                startActivity(seckill_btn);
                break;
            //分享
            case R.id.booking_btn:
                Intent booking_btn = new Intent(getContext(), H5SecActivity.class);
                booking_btn.putExtra("http", "https://www.yikch.com/mobile/appShow/share?type=android&userId=" + getLogUser(getContext()).getId());
                booking_btn.putExtra("title", "分享");
                startActivity(booking_btn);
                break;
            /**
             * 银行
             */
            case R.id.si_geren_qun:
                startActivity(new Intent(getContext(), BankActivity.class));
                break;
            /**
             *  拨打营养师号码
             */
            case R.id.bo_phone:
                Intent bo_phone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "010-57690370"));
                startActivity(bo_phone);
                break;
            /**
             *  会员福利社
             */
            case R.id.tou_lin3:
                Intent tou_lin3 = new Intent(getContext(), H5SecActivity.class);
                tou_lin3.putExtra("title", "会员福利社");
                tou_lin3.putExtra("http", "https://www.yikch.com/mobile/appShow/memberBenefits?type=android&userId=" + getLogUser(BaseApp.getApp()).getId());
                startActivity(tou_lin3);
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
        if (logUser != null)
            recommendPresenter.request(10, 136);
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
            if (userCenter.getAvatar() != null) {
                if (userCenter.getAvatar().equals("")) {
                    mImgFragmentWoTouxiang.setBackgroundResource(R.drawable.touxiang_2);
                } else {
                    //设置图片圆角角度
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.placeholder(R.drawable.touxiang_2);
                    requestOptions.fallback(R.drawable.touxiang_2);
                    if (userCenter.getAvatar().contains("http://") || userCenter.getAvatar().contains("https://")) {
                        Glide.with(getContext()).load(userCenter.getAvatar())
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .apply(requestOptions)
                                .into(mImgFragmentWoTouxiang);
                    } else {
                        Glide.with(getContext()).load("https://static.yikch.com" + userCenter.getAvatar())
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .apply(requestOptions)
                                .into(mImgFragmentWoTouxiang);
                    }
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
     *  设置指示器长度
     */
    /**
     * 通过反射机制  修改TableLayout  的下划线长度
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
            RecommendBean recommendBeans = (RecommendBean) request.getEntity();
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

    /**
     * 微信分享 （这里仅提供一个分享网页的示例，其它请参看官网示例代码）
     *
     * @param flag(0:分享到微信好友，1：分享到微信朋友圈)
     */
    private void wechatShare(int flag) {
        IWXAPI wxApi = WXAPIFactory.createWXAPI(getContext(), "wx38a0aef5df24fe50", false);
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = "https://www.yikch.com";
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "企挚友平台";
        msg.description = "用心打造企业福利";
        //这里替换一张自己工程里的图片资源
        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.drawable.guanggaoimg);
        msg.setThumbImage(thumb);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = flag == 0 ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
        wxApi.sendReq(req);
    }
}
