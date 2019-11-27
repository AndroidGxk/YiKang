package com.yikangcheng.admin.yikang.activity.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.BankActivity;
import com.yikangcheng.admin.yikang.activity.GoodCommCentreActivity;
import com.yikangcheng.admin.yikang.activity.H5SecActivity;
import com.yikangcheng.admin.yikang.activity.OrderFormActivity;
import com.yikangcheng.admin.yikang.activity.adapter.LipinAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.Orderform_ViewPagerAdapter;
import com.yikangcheng.admin.yikang.activity.coupon.CouponActivity;
import com.yikangcheng.admin.yikang.activity.fragment.goodsrecommtion.new_framgment.Good_Recommiton_Chu;
import com.yikangcheng.admin.yikang.activity.fragment.goodsrecommtion.new_framgment.Good_Recommiton_Day;
import com.yikangcheng.admin.yikang.activity.fragment.goodsrecommtion.new_framgment.Good_Recommiton_Home;
import com.yikangcheng.admin.yikang.activity.fragment.goodsrecommtion.new_framgment.Good_Recommiton_fash;
import com.yikangcheng.admin.yikang.activity.fragment.goodsrecommtion.new_framgment.Good_Recommiton_snack;
import com.yikangcheng.admin.yikang.activity.giftactivity.MyGiftActivity;
import com.yikangcheng.admin.yikang.activity.h5activity.H5MessActivity;
import com.yikangcheng.admin.yikang.activity.myaccount.MyaccountActivity;
import com.yikangcheng.admin.yikang.activity.obligation.DetailActivity;
import com.yikangcheng.admin.yikang.app.BaseApp;
import com.yikangcheng.admin.yikang.base.BaseFragment;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.UserDetailBean;
import com.yikangcheng.admin.yikang.bean.UserInfoBean;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.UserInfoPresenter;
import com.yikangcheng.admin.yikang.util.MyViewPager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：古祥坤 on 2019/9/4 09:17
 * 邮箱：1724959985@qq.com
 */
public class Fragment_New_Wo extends BaseFragment {
    @BindView(R.id.tab_activity_orderform)
    TabLayout mTabBottom;
    @BindView(R.id.tab1)
    TabLayout mTabTop;
    @BindView(R.id.neste)
    NestedScrollView mNeste;
    @BindView(R.id.sum_height)
    RelativeLayout mSumHeight;
    @BindView(R.id.viewPager_orderforms)
    MyViewPager mViewPagerBottom;
    @BindView(R.id.bo_phone)
    ImageView mPhone;
    @BindView(R.id.si_geren_qun)
    ImageView mGeren;
    @BindView(R.id.adver_img)
    ImageView mAdver;
    @BindView(R.id.mess_btn)
    ImageView mMess;
    /**
     * 全部订单
     */
    @BindView(R.id.user_order)
    LinearLayout mAllorder;
    /**
     * 超市
     */
    @BindView(R.id.shop_btn)
    LinearLayout mShop;
    /**
     * 超惠购
     */
    @BindView(R.id.buy_btn)
    LinearLayout mBuy;
    /**
     * 秒杀
     */
    @BindView(R.id.kill_btn)
    LinearLayout mKill;
    /**
     * 邀请
     */
    @BindView(R.id.invite_btn)
    LinearLayout mInvite;
    /**
     * 礼品中心
     */
    @BindView(R.id.gift_btn)
    LinearLayout mGift;
    @BindView(R.id.user_header)
    ImageView mHeader;
    @BindView(R.id.user_name)
    TextView mName;
    /**
     * 优惠券入口
     */
    @BindView(R.id.discount)
    LinearLayout mDiscount;
    /**
     * 账户余额入口
     */
    @BindView(R.id.account)
    LinearLayout mAccount;
    @BindView(R.id.recy)
    RecyclerView mOrderTypeRecy;
    @BindView(R.id.lipin_recy)
    RecyclerView mLipinRecy;
    private int mViewHeight;
    private LoginBean logUser;
    private UserInfoPresenter userInfoPresenter;
    private LinearLayoutManager mOrderTypeLieader;
    private LinearLayoutManager mLipinRecyLinear;
    @BindView(R.id.tabview)
    View tabview;

    @Override
    protected void initView(View view) {
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
        mViewPagerBottom.setAdapter(orderform_viewPagerAdapter);
        mViewPagerBottom.setOffscreenPageLimit(5);
        mTabBottom.setupWithViewPager(mViewPagerBottom);
        mTabTop.setupWithViewPager(mViewPagerBottom);
        //设置下划线长度
        mTabBottom.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(mTabBottom, 10, 10);
            }
        });
        mTabTop.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(mTabTop, 10, 10);
            }
        });
        changeTextColor(mTabBottom);
        changeTextColor(mTabTop);
        getHeight();
        onClickListener();
        /**
         * 用户信息
         */
        //接口请求
        userInfoPresenter = new UserInfoPresenter(new UserInfo());
        mOrderTypeLieader = new LinearLayoutManager(getContext());
        mOrderTypeLieader.setOrientation(LinearLayoutManager.HORIZONTAL);
        mOrderTypeRecy.setLayoutManager(mOrderTypeLieader);
        mOrderTypeRecy.setAdapter(new MyOrderTypeAdapter());
        mLipinRecyLinear = new LinearLayoutManager(getContext());
        mLipinRecyLinear.setOrientation(LinearLayoutManager.HORIZONTAL);
        mLipinRecy.setLayoutManager(mLipinRecyLinear);
        mLipinRecy.setAdapter(new LipinAdapter());

    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initData() {
        //查询数据库中是否有用户
        logUser = getLogUser(getContext());
        mNeste.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                int i4 = i1 - (int) mViewHeight;
                if (i1 > mTabBottom.getTop() - getStatusBarHeight(getContext())) {
                    tabview.setVisibility(View.VISIBLE);
                    RelativeLayout.LayoutParams linearParams =(RelativeLayout.LayoutParams) tabview.getLayoutParams(); //取控件textView当前的布局参数
                    linearParams.height =getStatusBarHeight(getContext());// 控件的高强制设成20
                    tabview.setLayoutParams(linearParams);
                    tabview.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    mTabTop.setVisibility(View.VISIBLE);
                    mTabBottom.setVisibility(View.INVISIBLE);
//                    tv_toolBar_title.setText("精品推荐");
                } else if (0 >= i4) {
                    tabview.setVisibility(View.GONE);
                    mTabTop.setVisibility(View.GONE);
                    mTabBottom.setVisibility(View.VISIBLE);
//                    tv_toolBar_title.setText("个人中心");
                }
                if (i1 - (int) mViewHeight == 0) {
//                    tv_toolBar_title.setText("精品推荐");
                }
            }
        });


        mTabTop.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //这是选中状态
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            //未选中状态
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                mNeste.scrollTo(0, mViewPagerBottom.getTop());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * 设置Tab下划线颜色
     *
     * @param tabLayout
     */
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
     * 提供子fragment调用方法，解决listview高度不自适应出现多余空白
     */
    public void setChildObjectForPosition(View view, int poistion) {
        mViewPagerBottom.setObjectForPosition(view, poistion);
    }

    /**
     * 控件高度
     *
     * @return
     */
    private void getHeight() {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        mSumHeight.measure(w, h);
        mViewPagerBottom.measure(w, h);
        int height = mSumHeight.getMeasuredHeight();
        int measuredHeight = mViewPagerBottom.getMeasuredHeight();
        mViewHeight += height;
    }

    /**
     * 点击事件
     *
     * @return
     */
    public void onClickListener() {

        /**
         * 退换货
         */

        /**
         * 全部订单
         */
        mAllorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (logUser != null) {
                    startActivity(new Intent(getActivity(), OrderFormActivity.class));
                }
            }
        });

        /**
         * 超市
         */
        mShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), H5SecActivity.class);
                intent.putExtra("none", "no");
                intent.putExtra("http", "https://www.yikch.com/mobile/appShow/market?type=android&userId=" + getLogUser(BaseApp.getApp()).getId());
                startActivity(intent);
            }
        });

        /**
         * 超惠购
         */
        mBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gobuy_btn = new Intent(getContext(), H5SecActivity.class);
                gobuy_btn.putExtra("http", "https://www.yikch.com/mobile/appShow/superToday?type=android&userId=" + getLogUser(BaseApp.getApp()).getId());
                gobuy_btn.putExtra("title", "超惠购");
                startActivity(gobuy_btn);
            }
        });

        /**
         * 秒杀
         */
        mKill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seckill_btn = new Intent(getContext(), H5SecActivity.class);
                seckill_btn.putExtra("http", "https://www.yikch.com/mobile/appShow/activity?type=android&userId=" + getLogUser(BaseApp.getApp()).getId());
                seckill_btn.putExtra("title", "周秒杀");
                startActivity(seckill_btn);
            }
        });
        /**
         * 分享邀请
         */
        mInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent booking_btn = new Intent(getContext(), H5SecActivity.class);
                booking_btn.putExtra("http", "https://www.yikch.com/mobile/appShow/share?type=android&userId=" + getLogUser(getContext()).getId());
                booking_btn.putExtra("title", "分享");
                startActivity(booking_btn);
            }
        });
        /**
         * 个人资料中心
         */
        mHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (logUser != null) {
                    startActivity(new Intent(getActivity(), MyaccountActivity.class));
                }
            }
        });
        /**
         * 优惠券
         */
        mDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CouponActivity.class));
            }
        });
        /**
         * 账户
         */
        mAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DetailActivity.class));
            }
        });
        /**
         * 拨打电话
         */
        mPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bo_phone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "010-57690370"));
                startActivity(bo_phone);
            }
        });
        /**
         * 银行办卡
         */
        mGeren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), BankActivity.class));
            }
        });
        /**
         * 消息页面
         */
        mMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), H5MessActivity.class));
            }
        });
        /**
         * 福利内购
         */
        mAdver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), H5SecActivity.class);
                intent.putExtra("http", "https://www.yikch.com/mobile/appShow/yousheng?type=ios&userId=" + getLogUser(getContext()).getId());
                intent.putExtra("title", "福利内购");
                startActivity(intent);
            }
        });
        /**
         * 礼品中心
         */
        mGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MyGiftActivity.class));
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        /**
         * 用户资料·
         */
        if (logUser != null) {
            int userId = logUser.getId();
            userInfoPresenter.request(userId);
//            initOrderCountPresenter.request(logUser.getId());
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
                mName.setText(userCenter.getMobile() + "");
            } else {
                mName.setText(userCenter.getNickName());
            }
            if (userCenter.getAvatar() != null) {
                if (userCenter.getAvatar().equals("")) {
                    mHeader.setBackgroundResource(R.drawable.touxiang_2);
                } else {
                    //设置图片圆角角度
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.placeholder(R.drawable.touxiang_2);
                    requestOptions.fallback(R.drawable.touxiang_2);
                    if (userCenter.getAvatar().contains("http://") || userCenter.getAvatar().contains("https://")) {
                        Glide.with(getContext()).load(userCenter.getAvatar())
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .apply(requestOptions)
                                .into(mHeader);
                    } else {
                        Glide.with(getContext()).load("https://static.yikch.com" + userCenter.getAvatar())
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .apply(requestOptions)
                                .into(mHeader);
                    }
                }
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_new_wo;
    }

    private class MyOrderTypeAdapter extends RecyclerView.Adapter<MyOrderTypeAdapter.Vh> {


        @NonNull
        @Override
        public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myordertype_item, parent, false);
            return new Vh(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Vh vh, int position) {
            switch (position) {
                case 0:
                    Glide.with(getContext()).load(R.drawable.new_wo_daifukuan)
                            .into(vh.img);
                    vh.count.setText("待付款");
                    vh.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent img_fragment_wo_daifukuan = new Intent(getActivity(), OrderFormActivity.class);
                            img_fragment_wo_daifukuan.putExtra("tab", "daifukuan");
                            startActivity(img_fragment_wo_daifukuan);
                        }
                    });
                    break;
                case 1:
                    Glide.with(getContext()).load(R.drawable.new_wo_daishouhuo)
                            .into(vh.img);
                    vh.count.setText("待收货");
                    vh.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent img_fragment_wo_yizhifu = new Intent(getActivity(), OrderFormActivity.class);
                            img_fragment_wo_yizhifu.putExtra("tab", "daishouhuo");
                            startActivity(img_fragment_wo_yizhifu);
                        }
                    });
                    break;
                case 2:
                    Glide.with(getContext()).load(R.drawable.new_wo_daipingjia)
                            .into(vh.img);
                    vh.count.setText("待评价");
                    vh.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getContext(), GoodCommCentreActivity.class));
                        }
                    });
                    break;
                case 3:
                    Glide.with(getContext()).load(R.drawable.new_wo_tuihuan)
                            .into(vh.img);
                    vh.count.setText("退换/售后");
                    break;

            }
        }

        @Override
        public int getItemCount() {
            return 4;
        }


        class Vh extends RecyclerView.ViewHolder {
            ImageView img;
            TextView count;
            public Vh(View itemView) {
                super(itemView);
                img = itemView.findViewById(R.id.img);
                count = itemView.findViewById(R.id.count);
            }
        }
    }
}
