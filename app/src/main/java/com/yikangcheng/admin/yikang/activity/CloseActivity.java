package com.yikangcheng.admin.yikang.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.CloseRecyclerAdapter;
import com.yikangcheng.admin.yikang.activity.siteactivity.AiteActivity;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.bean.AllAddressBean;
import com.yikangcheng.admin.yikang.bean.CreatOrderBean;
import com.yikangcheng.admin.yikang.bean.PariticShopBean;
import com.yikangcheng.admin.yikang.bean.Request;
import com.yikangcheng.admin.yikang.bean.ShopCarBean;
import com.yikangcheng.admin.yikang.model.http.ApiException;
import com.yikangcheng.admin.yikang.model.http.ICoreInfe;
import com.yikangcheng.admin.yikang.presenter.AllAddressPresenter;
import com.yikangcheng.admin.yikang.presenter.OrderBuyPresenter;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import me.jessyan.autosize.internal.CustomAdapt;

/**
 * 结算页面
 */
public class CloseActivity extends BaseActivtiy implements View.OnClickListener, CustomAdapt {
    boolean n_invoice = false, d_invoice = false, z_invoice = false, g_invoice = false, w_invoice = false, t_good = false, d_good = false;
    private RecyclerView recycler;
    private RelativeLayout rela4, rela3, rela2;
    private int width;
    private Dialog bottomDialog;
    private int height;
    private View sele_pay_digo, invoice_item;
    private ImageView buy_img;
    private RelativeLayout wechat_pay, zhi_rela, youhuiquan;
    //选中未选中
    private boolean wechat = true, zhi = false;
    private FragmentTransaction fragmentTransaction;
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

    @Override
    protected void initView() {
        //网络请求
        network();
        StatusBarUtil.setStatusBarMode(this, true, R.color.clolrBAai);
        recycler = findViewById(R.id.recycler);
        buy_img = findViewById(R.id.buy_img);
        rela4 = findViewById(R.id.rela4);
        rela2 = findViewById(R.id.rela2);
        back_img = findViewById(R.id.back_img);
        rela3 = findViewById(R.id.rela3);
        user_name = findViewById(R.id.user_name);
        user_phone = findViewById(R.id.user_phone);
        user_address = findViewById(R.id.user_address);
        youhuiquan = findViewById(R.id.youhuiquan);
        heji_text = findViewById(R.id.heji_text);
        good_num = findViewById(R.id.good_num);
        total_money = findViewById(R.id.total_money);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        shopCarBeans = (List<ShopCarBean>) bundle.get("shopList");
        String goodinfo = intent.getStringExtra("goodinfo");
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        sele_pay_digo = LayoutInflater.from(this).inflate(R.layout.sele_pay_digo, null);
        invoice_item = LayoutInflater.from(this).inflate(R.layout.invoice_item, null);
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
                mTotal += shopCarBeans.get(i).getShopSpecDetailed().getRetailPrice() * mBuyNum;
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
        zhi_rela = sele_pay_digo.findViewById(R.id.zhi_rela);
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
                }
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
                }
            }
        });
        /**
         * 收货地址
         */
        rela2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CloseActivity.this, AiteActivity.class));
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
            PariticShopBean pariticShopBean = new PariticShopBean();
            pariticShopBean.setId(split[0]);
            pariticShopBean.setDataType(split[1]);
            pariticShopBean.setBuyNum(split[2]);
            pariticShopBean.setPrice(split[3]);
            pariticShopBean.setCommodityName(split[4]);
            pariticShopBean.setLogo(split[5]);
            pariticShopBean.setDataType(split[6]);
            Log.e("gy", pariticShopBean.toString());
            closeRecyclerAdapter.addAlls(pariticShopBean);
            double num = Double.parseDouble(split[2]);
            double price = Double.parseDouble(split[3]);
            java.text.DecimalFormat myformat1 = new java.text.DecimalFormat("0.00");
            String strs = myformat1.format(price * num);
            good_num.setText("x" + split[2]);
            total_money.setText("¥" + strs);
            heji_text.setText("合计：¥" + strs);
        }
        invoice();
    }

    /**
     * 网络请求
     */
    private void network() {
        allAddressPresenter = new AllAddressPresenter(new AllAddress());
        allAddressPresenter.request(getLogUser(CloseActivity.this).getId());
        //创建订单
        orderBuyPresenter = new OrderBuyPresenter(new OrderBuy());
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
            }
        });
        /**
         * 优惠券
         */
        youhuiquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //优惠券
                startActivity(new Intent(CloseActivity.this, SelectCouponActivity.class));
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.no_invoice:
                //不开发票
                if (!n_invoice) {
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
                bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
                bottomDialog.show();
            }
        });
        TextView wan = sele_pay_digo.findViewById(R.id.wan);
        wan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialog.dismiss();
            }
        });
        /***
         * 立即购买
         */
        buy_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shopCarBeans != null) {
                    for (int i = 0; i < shopCarBeans.size(); i++) {
                        int dataId = shopCarBeans.get(i).getDataId();
                        orderBuyPresenter.request(getLogUser(CloseActivity.this).getId(), dataId,
                                listUserAddressBean.getId(), 1, 1, 2, "易康成", "132", "2", "1724959985@qq.com", "ALIPAY", "Android");
                    }
                } else {
                    orderBuyPresenter.request(getLogUser(CloseActivity.this).getId(), 18576,
                            listUserAddressBean.getId(), 1, 1, 2, "易康成", "132", "2", "1724959985@qq.com", "ALIPAY", "Android");
                }
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
                bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
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


    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return height / 2;
    }

    /**
     * 查询用户所有地址
     */
    private class AllAddress implements ICoreInfe {


        @Override
        public void success(Object data) {
            Request request = (Request) data;
            AllAddressBean entity = (AllAddressBean) request.getEntity();
            List<AllAddressBean.ListUserAddressBean> listUserAddress = entity.getListUserAddress();
            for (int i = 0; i < listUserAddress.size(); i++) {
                listUserAddressBean = listUserAddress.get(i);
                if (listUserAddressBean.getIsFirst() == 1) {
                    user_name.setText(listUserAddressBean.getReceiver());
                    user_phone.setText(listUserAddressBean.getMobile());
                    user_address.setText(listUserAddressBean.getAddress());
                }
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
            Toast.makeText(CloseActivity.this, "" + request.getMessage() + "-------" + request.isSuccess(), Toast.LENGTH_SHORT).show();
            if (request.isSuccess()) {
                CreatOrderBean entity = (CreatOrderBean) request.getEntity();
                Intent intent = new Intent(CloseActivity.this, ConfirmActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("creatorder", entity);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }
}
