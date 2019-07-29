package com.yikangcheng.admin.yikang.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.CloseRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.adapter.SelectCouponRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.fragment.Fragment_Gou;
import com.yikangcheng.admin.yikang.activity.siteactivity.AiteActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.AllAddressBean;
import com.yikangcheng.admin.yikang.bean.CouponusableBean;
import com.yikangcheng.admin.yikang.bean.CreatOrderBean;
import com.yikangcheng.admin.yikang.bean.LoginBean;
import com.yikangcheng.admin.yikang.bean.PariticShopBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.ShopCarBean;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.AllAddressPresenter;
import com.yikangcheng.admin.yikang.presenter.CouponPresenter;
import com.yikangcheng.admin.yikang.presenter.OrderBuyArrayPresenter;
import com.yikangcheng.admin.yikang.presenter.OrderBuyPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;
import com.yikangcheng.admin.yikang.util.TwoBallRotationProgressBar;
import com.yikangcheng.admin.yikang.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

import me.leefeng.promptlibrary.PromptDialog;

/**
 * 结算页面
 */
public class CloseActivity extends BaseActivtiy implements View.OnClickListener {
    boolean n_invoice = false, d_invoice = false, z_invoice = false, g_invoice = false, w_invoice = false, t_good = false, d_good = false;
    private RecyclerView recycler;
    private RelativeLayout rela4, rela3, rela2;
    private int width;
    private Dialog bottomDialog;
    private int height;
    private View sele_pay_digo, invoice_item, select_coupon;
    private ImageView buy_img;
    private RelativeLayout wechat_pay, zhi_rela, youhuiquan;
    //选中未选中
    private boolean wechat = false, zhi = true;
    private RelativeLayout no_yes;
    private TextView zhi_invoice, wan;
    private TextView no_invoice;
    private TextView dian_invoice;
    private EditText mesg_mail, mesg_na, work_name;
    private TextView work;
    private TextView personage;
    private TextView good_detail;
    private TextView good_type;
    private TextView good_num;
    private TextView total_money;
    private TextView heji_text;
    private List<ShopCarBean> shopCarBeans = new ArrayList<>();
    private CloseRecyclerAdapter closeRecyclerAdapter;
    private AllAddressPresenter allAddressPresenter;
    //地址
    private TextView user_name;
    private TextView user_phone;
    private TextView user_address;
    private int mBuyNum = 0;
    private double mTotal;
    private ImageView back_img;
    private OrderBuyPresenter orderBuyPresenter;
    private AllAddressBean.ListUserAddressBean listUserAddressBean;
    private PariticShopBean pariticShopBean;
    private PromptDialog promptDialog;
    private OrderBuyArrayPresenter orderBuyArrayPresenter;
    private AllAddressBean.ListUserAddressBean userAddressBean;
    //默认地址
    private int id;
    //发票类型
    String invoicetype;

    //发票类型Int
    int invoicetypeInt = 3;
    //发票抬头
    int invoicetypetitle;
    //发票内容
    int invoicecount;
    private TextView invoice_text;
    private TextView wan1;
    private String ipAddressString;
    private String num;
    private TextView pay_zhifu;
    private ImageView pay_img;
    private EditText mesg_phone;
    private String mWorkuniit = "";
    private String mNumBer = "";
    private String mPhone = "";
    private String mMail = "";
    private int s_invoiceInt = 1;
    private String mGood_type = "1";
    private TextView text;
    private TwoBallRotationProgressBar progress;
    private String strs;
    private int youhuiquanid;
    private int amount;
    private TextView youhuiquan_text;

    /**
     * 优惠券
     */
    private SelectCouponRecyclerAdapter selectCouponRecyclerAdapter;
    private ImageView youhuiquan_null;
    private TextView shiyong;
    private CouponPresenter couponPresenter;
    private LoginBean loginBean;
    private RecyclerView yourecycler;
    private double moneyDouble = 0.0;
    private int y_id;
    private int y_money;

    /**
     * 回传值
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == 1001) {
            Bundle bundle = data.getExtras();
            userAddressBean = (AllAddressBean.ListUserAddressBean) bundle.getSerializable("result");
            if (userAddressBean != null) {
                user_name.setVisibility(View.VISIBLE);
                user_address.setVisibility(View.VISIBLE);
                user_phone.setVisibility(View.VISIBLE);
                text.setVisibility(View.GONE);
                user_name.setText(userAddressBean.getReceiver());
                String mobile = userAddressBean.getMobile();
                if (mobile.length() >= 11) {
                    String frontMobile = mobile.substring(0, 3);
                    String frontStr = frontMobile + "****";
                    String AllMobile = frontStr + mobile.substring(7, mobile.length());
                    user_phone.setText(AllMobile);
                }
                user_address.setText(userAddressBean.getProvinceStr() + userAddressBean.getCityStr() + userAddressBean.getTownStr() + userAddressBean.getAddress());
            }
        } else if (requestCode == 2000 && resultCode == 2001) {
            youhuiquanid = data.getIntExtra("id", 0);
            amount = data.getIntExtra("amount", 0);
            youhuiquan_text.setTextColor(Color.parseColor("#FF0000"));
            youhuiquan_text.setText("-" + amount);
            String money = total_money.getText().toString();
            String[] split = money.split("¥");
            double moneyDouble = Double.parseDouble(split[1]);
            double zmoney = moneyDouble - amount;
            java.text.DecimalFormat myformat1 = new java.text.DecimalFormat("0.00");
            String moneyStr = myformat1.format(zmoney);
            heji_text.setText("合计：¥" + moneyStr);
        }
    }

    @Override
    protected void initView() {
        //获取Ip地址
        ipAddressString = UIUtils.getIpAddressString();
        SharedPreferences close = getSharedPreferences("close", MODE_PRIVATE);
        SharedPreferences.Editor edit = close.edit();
        edit.putBoolean("close", true);
        edit.commit();
        Fragment_Gou.getSign();
        //创建对象
        promptDialog = new PromptDialog(this);
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(100);
        //网络请求
        network();
        StatusBarUtil.setStatusBarMode(this, true, R.color.clolrBAai);
        sele_pay_digo = LayoutInflater.from(this).inflate(R.layout.sele_pay_digo, null);
        invoice_item = LayoutInflater.from(this).inflate(R.layout.invoice_item, null);

        invoice_text = (TextView) findViewById(R.id.invoice_text);
        pay_zhifu = (TextView) findViewById(R.id.pay_zhifu);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        youhuiquan_text = (TextView) findViewById(R.id.youhuiquan_text);
        buy_img = (ImageView) findViewById(R.id.buy_img);
        pay_img = (ImageView) findViewById(R.id.pay_img);
        rela4 = (RelativeLayout) findViewById(R.id.rela4);
        rela2 = (RelativeLayout) findViewById(R.id.rela2);
        back_img = (ImageView) findViewById(R.id.back_img);
        rela3 = (RelativeLayout) findViewById(R.id.rela3);
        //进度条
        progress = (TwoBallRotationProgressBar) findViewById(R.id.progress);
        user_name = (TextView) findViewById(R.id.user_name);
        user_phone = (TextView) findViewById(R.id.user_phone);
        text = (TextView) findViewById(R.id.text);
        user_address = (TextView) findViewById(R.id.user_address);
        youhuiquan = (RelativeLayout) findViewById(R.id.youhuiquan);
        heji_text = (TextView) findViewById(R.id.heji_text);
        good_num = (TextView) findViewById(R.id.good_num);
        total_money = (TextView) findViewById(R.id.total_money);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        shopCarBeans = (List<ShopCarBean>) bundle.get("shopList");
        String goodinfo = intent.getStringExtra("goodinfo");
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        recycler.setLayoutManager(new LinearLayoutManager(this));
        closeRecyclerAdapter = new CloseRecyclerAdapter(this);
        recycler.setAdapter(closeRecyclerAdapter);
        if (shopCarBeans != null) {
            closeRecyclerAdapter.addAll(shopCarBeans);
        }
        if (shopCarBeans != null) {
            for (int i = 0; i < shopCarBeans.size(); i++) {
                int buyNum = shopCarBeans.get(i).getBuyNum();
                mBuyNum += buyNum;
                mTotal += shopCarBeans.get(i).getShopSpecDetailed().getRetailPrice() * buyNum;
            }
        }
        good_num.setText("x" + mBuyNum);
        java.text.DecimalFormat myformat = new java.text.DecimalFormat("0.00");
        String str = myformat.format(mTotal);
        total_money.setText("¥" + str);
        heji_text.setText("合计：¥" + str);
        //解决滑动不流畅
        recycler.setHasFixedSize(true);
        recycler.setNestedScrollingEnabled(false);
        bottomDialog = new Dialog(this, R.style.BottomDialog);
        wechat_pay = sele_pay_digo.findViewById(R.id.wechat_pay);
        wan1 = sele_pay_digo.findViewById(R.id.wan);
        zhi_rela = sele_pay_digo.findViewById(R.id.zhi_rela);
        //优惠券
        select_coupon = LayoutInflater.from(this).inflate(R.layout.activity_select_coupon, null);
        String money = total_money.getText().toString();
        String[] splits = money.split("¥");
        moneyDouble = Double.parseDouble(splits[1]);
        back_img = select_coupon.findViewById(R.id.back_img);
        shiyong = select_coupon.findViewById(R.id.shiyong);
        youhuiquan_null = select_coupon.findViewById(R.id.youhuiquan_null);
        yourecycler = select_coupon.findViewById(R.id.recycler);
        couponPresenter = new CouponPresenter(new Coupon());
        yourecycler.setLayoutManager(new LinearLayoutManager(this));
        selectCouponRecyclerAdapter = new SelectCouponRecyclerAdapter(this);
        yourecycler.setAdapter(selectCouponRecyclerAdapter);
        loginBean = getLogUser(this);
        selectCouponRecyclerAdapter.setOnClickListener(new SelectCouponRecyclerAdapter.onClickListener() {
            @Override
            public void onClick(int id, int amount) {
                y_id = id;
                y_money = amount;
            }
        });

        shiyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (y_money == 0) {
                    youhuiquan_text.setTextColor(Color.parseColor("#FF999999"));
                    youhuiquan_text.setText("请选择优惠券");
                } else {
                    youhuiquan_text.setTextColor(Color.parseColor("#FF0000"));
                    youhuiquan_text.setText("优惠¥" + y_money + ".00");
                }
                String money = total_money.getText().toString();
                String[] split = money.split("¥");
                double moneyDouble = Double.parseDouble(split[1]);
                double zmoney = moneyDouble - y_money;
                java.text.DecimalFormat myformat1 = new java.text.DecimalFormat("0.00");
                String moneyStr = myformat1.format(zmoney);
                heji_text.setText("合计：¥" + moneyStr);
                bottomDialog.dismiss();
            }
        });
        /**/
        wechat_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wechat) {
                    return;
                } else {
                    wechat = true;
                    zhi = false;
                    wechat_pay.setBackgroundResource(R.drawable.yellow_pay_shape);
                    zhi_rela.setBackgroundResource(R.drawable.gray_pay_shape);
                    pay_zhifu.setText("微信");
                    pay_img.setBackgroundResource(R.drawable.weixinzhifu_sele);
                }
            }
        });
        wan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialog.dismiss();
            }
        });
        zhi_rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (zhi) {
                    return;
                } else {
                    zhi_rela.setBackgroundResource(R.drawable.yellow_pay_shape);
                    wechat_pay.setBackgroundResource(R.drawable.gray_pay_shape);
                    zhi = true;
                    wechat = false;
                    pay_zhifu.setText("支付宝");
                    pay_img.setBackgroundResource(R.drawable.zhifubaozhifu_sele);
                }
            }
        });
        /**
         * 收货地址
         */
        rela2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(CloseActivity.this, AiteActivity.class);
                addIntent.putExtra("address", "true");
                startActivityForResult(addIntent, 1000);
            }
        });

        /**
         * 退出
         */
        back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        /**
         * 详情页面数据
         */
        if (goodinfo != null) {
            String[] split = goodinfo.split(",");
            if (split.length == 9) {
                num = split[3];
                pariticShopBean = new PariticShopBean();
                pariticShopBean.setId(split[1]);
                pariticShopBean.setDataType(split[2]);
                pariticShopBean.setBuyNum(split[3]);
                pariticShopBean.setPrice(split[4]);
                pariticShopBean.setCommodityName(split[5]);
                pariticShopBean.setLogo(split[6]);
                pariticShopBean.setDataType(split[7]);
                closeRecyclerAdapter.addAlls(pariticShopBean);
                double price = Double.parseDouble(split[4]);
                java.text.DecimalFormat myformat1 = new java.text.DecimalFormat("0.00");
                strs = myformat1.format(price * Integer.parseInt(split[3]));
                good_num.setText("x" + split[3]);
                total_money.setText("¥" + strs);
                heji_text.setText("合计：¥" + strs);
                goodinfo = "";
            } else {
                num = split[2];
                pariticShopBean = new PariticShopBean();
                pariticShopBean.setId(split[0]);
                pariticShopBean.setDataType(split[1]);
                pariticShopBean.setBuyNum(split[2]);
                pariticShopBean.setPrice(split[3]);
                pariticShopBean.setCommodityName(split[4]);
                pariticShopBean.setLogo(split[5]);
                pariticShopBean.setDataType(split[6]);
                closeRecyclerAdapter.addAlls(pariticShopBean);
                double price = Double.parseDouble(split[3]);
                java.text.DecimalFormat myformat1 = new java.text.DecimalFormat("0.00");
                String strs = myformat1.format(price * Integer.parseInt(split[2]));
                good_num.setText("x" + split[2]);
                total_money.setText("¥" + strs);
                heji_text.setText("合计：¥" + strs);
                goodinfo = "";
            }
        }
        invoice();
    }

    /**
     * 网络请求
     */
    private void network() {
        allAddressPresenter = new AllAddressPresenter(new AllAddress());
        //创建订单
        orderBuyPresenter = new OrderBuyPresenter(new OrderBuy());
        orderBuyArrayPresenter = new OrderBuyArrayPresenter(new OrderBuy());
    }

    /**
     * 发票信息逻辑事件
     */
    public void invoice() {
        dian_invoice = invoice_item.findViewById(R.id.dian_invoice);
        no_invoice = invoice_item.findViewById(R.id.no_invoice);
        zhi_invoice = invoice_item.findViewById(R.id.zhi_invoice);
        wan = invoice_item.findViewById(R.id.wan);
        no_yes = invoice_item.findViewById(R.id.no_yes);
        work = invoice_item.findViewById(R.id.work);
        personage = invoice_item.findViewById(R.id.personage);
        mesg_phone = invoice_item.findViewById(R.id.mesg_phone);
        mesg_mail = invoice_item.findViewById(R.id.mesg_mail);
        work_name = invoice_item.findViewById(R.id.work_name);
        mesg_na = invoice_item.findViewById(R.id.mesg_na);
        good_type = invoice_item.findViewById(R.id.good_type);
        good_detail = invoice_item.findViewById(R.id.good_detail);
        no_invoice.setOnClickListener(this);
        dian_invoice.setOnClickListener(this);
        zhi_invoice.setOnClickListener(this);
        work.setOnClickListener(this);
        personage.setOnClickListener(this);
        good_type.setOnClickListener(this);
        good_detail.setOnClickListener(this);
        wan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialog.dismiss();
                invoice_text.setText(invoicetype);
                //单位名称
                mWorkuniit = work_name.getText().toString();
                //纳税人识别号
                mNumBer = mesg_na.getText().toString();
                //收票人手机号
                mPhone = mesg_phone.getText().toString();
                //收票人邮箱
                mMail = mesg_mail.getText().toString();
            }
        });
        /**
         * 优惠券
         */
        youhuiquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectCouponRecyclerAdapter.removeAll();
                //优惠券
                /*Intent intent = new Intent(CloseActivity.this, SelectCouponActivity.class);
                intent.putExtra("money", total_money.getText().toString());
                startActivityForResult(intent, 2000);*/
                if (loginBean != null) {
                    String money = total_money.getText().toString();
                    String[] splits = money.split("¥");
                    moneyDouble = Double.parseDouble(splits[1]);
                    couponPresenter.request(loginBean.getId(), moneyDouble);
                }
                //设置dialog的宽高为屏幕的宽高
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, height - (int) (height * 0.2));
                bottomDialog.setContentView(select_coupon, layoutParams);
                bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
                bottomDialog.setCanceledOnTouchOutside(true);
                bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialogs);
                bottomDialog.show();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.no_invoice:
                //不开发票
                if (!n_invoice) {
                    invoicetype = "不开发票";
                    invoicetypeInt = 3;
                    n_invoice = true;
                    d_invoice = false;
                    z_invoice = false;
                    setLayout(View.GONE, R.color.clolrBAai, R.color.colorText, R.color.colorText, R.drawable.shop_btn_shape, R.drawable.yellow_pay_shape, R.drawable.yellow_pay_shape);
                } else {
                    return;
                }
                break;
            case R.id.dian_invoice:
                //电子发票
                if (!d_invoice) {
                    invoicetype = "电子发票";
                    invoicetypeInt = 1;
                    d_invoice = true;
                    n_invoice = false;
                    z_invoice = false;
                    setLayout(View.VISIBLE, R.color.colorText, R.color.clolrBAai, R.color.colorText, R.drawable.yellow_pay_shape, R.drawable.shop_btn_shape, R.drawable.yellow_pay_shape);
                    mesg_mail.setVisibility(View.VISIBLE);
                } else {
                    return;
                }
                break;
            case R.id.zhi_invoice:
                //纸质发票
                if (!z_invoice) {
                    invoicetype = "纸质发票";
                    invoicetypeInt = 2;
                    z_invoice = true;
                    d_invoice = false;
                    n_invoice = false;
                    setLayout(View.VISIBLE, R.color.colorText, R.color.colorText, R.color.clolrBAai, R.drawable.yellow_pay_shape, R.drawable.yellow_pay_shape, R.drawable.shop_btn_shape);
                    mesg_mail.setVisibility(View.GONE);
                } else {
                    return;
                }
                break;
            case R.id.personage:
                //个人
                if (!g_invoice) {
                    s_invoiceInt = 1;
                    g_invoice = true;
                    w_invoice = false;
                    mesg_na.setVisibility(View.GONE);
                    work_name.setVisibility(View.GONE);
                    personage.setBackgroundResource(R.drawable.shop_btn_shape);
                    work.setBackgroundResource(R.drawable.yellow_pay_shape);
                    personage.setTextColor(CloseActivity.this.getResources().getColor(R.color.clolrBAai));
                    work.setTextColor(CloseActivity.this.getResources().getColor(R.color.colorText));
                } else {
                    return;
                }
                break;
            case R.id.work:
                //单位
                if (!w_invoice) {
                    s_invoiceInt = 2;
                    g_invoice = false;
                    w_invoice = true;
                    mesg_na.setVisibility(View.VISIBLE);
                    work_name.setVisibility(View.VISIBLE);
                    work.setBackgroundResource(R.drawable.shop_btn_shape);
                    personage.setBackgroundResource(R.drawable.yellow_pay_shape);
                    work.setTextColor(CloseActivity.this.getResources().getColor(R.color.clolrBAai));
                    personage.setTextColor(CloseActivity.this.getResources().getColor(R.color.colorText));
                } else {
                    return;
                }
                break;
            case R.id.good_detail:
                //商品明细
                if (!d_good) {
                    mGood_type = "1";
                    t_good = false;
                    d_good = true;
                    good_detail.setBackgroundResource(R.drawable.shop_btn_shape);
                    good_type.setBackgroundResource(R.drawable.yellow_pay_shape);
                    good_detail.setTextColor(CloseActivity.this.getResources().getColor(R.color.clolrBAai));
                    good_type.setTextColor(CloseActivity.this.getResources().getColor(R.color.colorText));
                } else {
                    return;
                }
                break;
            case R.id.good_type:
                //商品类型
                if (!t_good) {
                    mGood_type = "2";
                    d_good = false;
                    t_good = true;
                    good_type.setBackgroundResource(R.drawable.shop_btn_shape);
                    good_detail.setBackgroundResource(R.drawable.yellow_pay_shape);
                    good_type.setTextColor(CloseActivity.this.getResources().getColor(R.color.clolrBAai));
                    good_detail.setTextColor(CloseActivity.this.getResources().getColor(R.color.colorText));
                } else {
                    return;
                }
                break;
        }
    }

    public void setLayout(int View, int ColorN, int ColorD, int ColorZ, int BackgroundN, int BackgroundD, int BackgroundZ) {
        no_yes.setVisibility(View);
        no_invoice.setTextColor(CloseActivity.this.getResources().getColor(ColorN));
        dian_invoice.setTextColor(CloseActivity.this.getResources().getColor(ColorD));
        zhi_invoice.setTextColor(CloseActivity.this.getResources().getColor(ColorZ));
        no_invoice.setBackgroundResource(BackgroundN);
        dian_invoice.setBackgroundResource(BackgroundD);
        zhi_invoice.setBackgroundResource(BackgroundZ);
    }

    @Override
    protected void initEventData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.GONE);
                progress.stopAnimator();
            }
        }, 1500);
        /**
         * 选择支付方式
         */
        rela4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置dialog的宽高为屏幕的宽高
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, height - (int) (height * 0.5));
                bottomDialog.setContentView(sele_pay_digo, layoutParams);
                bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
                bottomDialog.setCanceledOnTouchOutside(true);
                bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialogs);
                bottomDialog.show();
            }
        });
        /***
         * 立即购买
         */
        buy_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promptDialog.showLoading("");
                if (shopCarBeans != null) {
                    String str = "";
                    for (int i = 0; i < shopCarBeans.size(); i++) {
                        str += shopCarBeans.get(i).getId() + ",";
                        if (i == shopCarBeans.size() - 1) {
                            String substring = str.substring(0, str.length() - 1);
                            if (userAddressBean == null) {
                                orderBuyArrayPresenter.request(getLogUser(CloseActivity.this).getId(), substring,
                                        id, invoicetypeInt, s_invoiceInt, mWorkuniit, mNumBer, mGood_type, mMail, zhi ? "ALIPAY" : "WEIXIN", "Android", "COMMODITY", zhi ? "" : ipAddressString, String.valueOf(y_id));
                            } else {
                                orderBuyArrayPresenter.request(getLogUser(CloseActivity.this).getId(), substring,
                                        userAddressBean.getId(), invoicetypeInt, s_invoiceInt, mWorkuniit, mNumBer, mGood_type, mMail, zhi ? "ALIPAY" : "WEIXIN", "Android", "COMMODITY", zhi ? "" : ipAddressString, String.valueOf(y_id));
                            }
                        }
                    }
                } else {
                    String ids = pariticShopBean.getId();
                    if (userAddressBean == null) {
                        orderBuyPresenter.request(getLogUser(CloseActivity.this).getId(), Integer.parseInt(ids),
                                id, Integer.parseInt(num), invoicetypeInt, s_invoiceInt, mWorkuniit, mNumBer, mGood_type, mMail, zhi ? "ALIPAY" : "WEIXIN", "Android", zhi ? "" : ipAddressString, String.valueOf(y_id));
                    } else {
                        orderBuyPresenter.request(getLogUser(CloseActivity.this).getId(), Integer.parseInt(ids),
                                userAddressBean.getId(), Integer.parseInt(num), invoicetypeInt, s_invoiceInt, mWorkuniit, mNumBer, mGood_type, mMail, zhi ? "ALIPAY" : "WEIXIN", "Android", zhi ? "" : ipAddressString, String.valueOf(y_id));
                    }
                }
            }
        });

        /**
         * 进度条
         */
        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
        /**
         * 开发票
         */
        rela3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置dialog的宽高为屏幕的宽高
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, height - (int) (height * 0.2));
                bottomDialog.setContentView(invoice_item, layoutParams);
                bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
                bottomDialog.setCanceledOnTouchOutside(true);
                bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialogs);
                bottomDialog.show();
            }
        });
    }

    @Override
    protected int getActivtiyLayoutId() {
        return R.layout.activity_close;
    }

    @Override
    protected void createPresenter() {

    }


    /**
     * 查询用户所有地址
     */
    private class AllAddress implements ICoreInfe {


        @Override
        public void success(Object data) {
            Request request = (Request) data;
            int type = 0;
            AllAddressBean entity = (AllAddressBean) request.getEntity();
            List<AllAddressBean.ListUserAddressBean> listUserAddress = entity.getListUserAddress();
            for (int i = 0; i < listUserAddress.size(); i++) {
                listUserAddressBean = listUserAddress.get(i);
                if (listUserAddressBean.getIsFirst() == 1) {
                    id = listUserAddressBean.getId();
                    user_name.setText(listUserAddressBean.getReceiver());
                    String mobile = listUserAddressBean.getMobile();
                    if (mobile.length() >= 11) {
                        String frontMobile = mobile.substring(0, 3);
                        String frontStr = frontMobile + "****";
                        String AllMobile = frontStr + mobile.substring(7, mobile.length());
                        user_phone.setText(AllMobile);
                    }
                    user_address.setText(listUserAddressBean.getProvinceStr() + listUserAddressBean.getCityStr() + listUserAddressBean.getTownStr() + listUserAddressBean.getAddress());
                    type = 1;
                    text.setVisibility(View.GONE);
                    user_address.setVisibility(View.VISIBLE);
                    user_phone.setVisibility(View.VISIBLE);
                    user_name.setVisibility(View.VISIBLE);
                }
            }
            if (type == 0) {
                text.setVisibility(View.VISIBLE);
                user_address.setVisibility(View.GONE);
                user_phone.setVisibility(View.GONE);
                user_name.setVisibility(View.GONE);
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    /**
     * 创建订单
     */
    private class OrderBuy implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            if (request.isSuccess()) {
                CreatOrderBean entity = (CreatOrderBean) request.getEntity();
                entity.setPayType(zhi ? "ALIPAY" : "WEIXIN");
                Intent intent = new Intent(CloseActivity.this, ConfirmActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("creatorder", entity);
                intent.putExtras(bundle);
                startActivity(intent);
                promptDialog.dismiss();
                SharedPreferences close = getSharedPreferences("close", MODE_PRIVATE);
                SharedPreferences.Editor edit = close.edit();
                edit.putBoolean("close", true);
                edit.putBoolean("closes", true);
                edit.commit();
                finish();
                Fragment_Gou.getRequest();
                if (PartiCarActivity.class != null) {
                    PartiCarActivity.getRequest();
                }
            } else {
                promptDialog.dismiss();
                ToastUtils.show("" + request.getMessage());
                SharedPreferences close = getSharedPreferences("close", MODE_PRIVATE);
                SharedPreferences.Editor edit = close.edit();
                edit.putBoolean("close", true);
                edit.putBoolean("closes", true);
                edit.commit();
                Fragment_Gou.getRequest();
                if (PartiCarActivity.class != null) {
                    PartiCarActivity.getRequest();
                }
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (userAddressBean == null) {
            allAddressPresenter.request(getLogUser(CloseActivity.this).getId());
        }
    }

    /**
     * 优惠券
     */
    private class Coupon implements ICoreInfe {
        @Override
        public void success(Object data) {
            Request request = (Request) data;
            List<CouponusableBean> couponusableBeans = (List<CouponusableBean>) request.getEntity();
            if (couponusableBeans != null && couponusableBeans.size() != 0) {
                selectCouponRecyclerAdapter.addAll(couponusableBeans);
                youhuiquan_null.setVisibility(View.GONE);
            } else {
                youhuiquan_null.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
