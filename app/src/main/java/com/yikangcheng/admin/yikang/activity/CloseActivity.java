package com.yikangcheng.admin.yikang.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
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

import com.yikangcheng.admin.yikang.R;
import com.yikangcheng.admin.yikang.activity.adapter.CloseRecyclerAdapter;
import com.yikangcheng.admin.yikang.base.BaseActivtiy;
import com.yikangcheng.admin.yikang.util.StatusBarUtil;

import me.jessyan.autosize.internal.CustomAdapt;

/**
 * 结算页面
 */
public class CloseActivity extends BaseActivtiy implements View.OnClickListener, CustomAdapt {
    boolean n_invoice = false, d_invoice = false, z_invoice = false, g_invoice = false, w_invoice = false, t_good = false, d_good = false;
    private RecyclerView recycler;
    private RelativeLayout rela4, rela3;
    private int width;
    private Dialog bottomDialog;
    private int height;
    private View sele_pay_digo, invoice_item;
    private ImageView buy_img;
    private RelativeLayout wechat_pay, zhi_rela;
    //选中未选中
    private boolean wechat = true, zhi = false;
    private FragmentTransaction fragmentTransaction;
    private RelativeLayout no_yes;
    private TextView zhi_invoice;
    private TextView no_invoice;
    private TextView dian_invoice;
    private EditText mesg_mail, mesg_na, work_name;
    private TextView work;
    private TextView personage;
    private TextView good_detail;
    private TextView good_type;

    @Override
    protected void initView() {
        StatusBarUtil.setStatusBarMode(this, true, R.color.clolrBAai);
        recycler = findViewById(R.id.recycler);
        buy_img = findViewById(R.id.buy_img);
        rela4 = findViewById(R.id.rela4);
        rela3 = findViewById(R.id.rela3);
        Display display = this.getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        height = display.getHeight();
        sele_pay_digo = LayoutInflater.from(this).inflate(R.layout.sele_pay_digo, null);
        invoice_item = LayoutInflater.from(this).inflate(R.layout.invoice_item, null);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(new CloseRecyclerAdapter(this));
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
        invoice();
    }

    /**
     * 发票信息逻辑事件
     */
    public void invoice() {
        dian_invoice = invoice_item.findViewById(R.id.dian_invoice);
        no_invoice = invoice_item.findViewById(R.id.no_invoice);
        zhi_invoice = invoice_item.findViewById(R.id.zhi_invoice);
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
        /***
         * 立即购买
         */
        buy_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CloseActivity.this, ConfirmActivity.class));
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
}
