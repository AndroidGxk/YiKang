package com.yikangcheng.admin.yikang.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lenovo on 2019/6/3.
 * WF
 */
@Entity
public class PayBean {
    @Override
    public String toString() {
        return "PayBean{" +
                "amount=" + amount +
                ", balance='" + balance + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", bankAmount=" + bankAmount +
                ", orderinfo='" + orderinfo + '\'' +
                ", orderId=" + orderId +
                '}';
    }
    public long getUid() {
        return this.uid;
    }
    public void setUid(long uid) {
        this.uid = uid;
    }
    public double getAmount() {
        return this.amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getBalance() {
        return this.balance;
    }
    public void setBalance(String balance) {
        this.balance = balance;
    }
    public String getOrderNo() {
        return this.orderNo;
    }
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public double getBankAmount() {
        return this.bankAmount;
    }
    public void setBankAmount(double bankAmount) {
        this.bankAmount = bankAmount;
    }
    public String getOrderinfo() {
        return this.orderinfo;
    }
    public void setOrderinfo(String orderinfo) {
        this.orderinfo = orderinfo;
    }
    public int getOrderId() {
        return this.orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public String getTimeStamp() {
        return this.timeStamp;
    }
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    public String getNonceStr() {
        return this.nonceStr;
    }
    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }
    public String getSign() {
        return this.sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getPrepayId() {
        return this.prepayId;
    }
    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }
    public int getIsZeroPurchase() {
        return this.isZeroPurchase;
    }
    public void setIsZeroPurchase(int isZeroPurchase) {
        this.isZeroPurchase = isZeroPurchase;
    }

    /**
     * amount : 2610
     * balance : 0.00
     * orderNo : NO155954777354092
     * bankAmount : 2610
     * orderinfo : alipay_sdk=alipay-sdk-java-3.7.26.ALL&app_id=2019030863469663&biz_content=%7B%22body%22%3A%22APP%E8%B4%AD%E7%89%A9%E6%B6%88%E8%B4%B9%22%2C%22out_trade_no%22%3A%22NO155954777354092%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22NO155954777354092%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%222610.0%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay¬ify_url=https%3A%2F%2Fwww.yikch.com%2Fapi%2Forder%2FpaySuccess&sign=Sxk4fIe1B2cB9krL5fYrdMxtEYopD2WXpOXsiUQB8LRoHk9D7rC0tPZ4pGvlH3s7srVUwL9WVIAx%2BRAk8SxE5K7ag6JSvzkUOkF53owWVxfFkpsT5DGnjf4wFzfQkZMlFpkWGd2eD7sV6pwdzCGdixNkpm%2FdoA9zmiA%2FfeaEEevl144vKh0Gv97CUob1Vo28smiQ9AzGfAEYm6OSTfGEGRaaFGtKz6p5uaFvzjtU6nZiAUezHCwZtLzdBZDGiBew4Oa1i77qS77WBphEL%2FVMqpnLl70SXSzbDWLCfNPXHu3s0a591OtqaCIGK34CKCHF1nVlTxQ55UcGr8AiVL9GRA%3D%3D&sign_type=RSA2×tamp=2019-06-03+15%3A48%3A07&version=1.0
     * orderId : 733
     */

    @Id
    private long uid;
    private double amount;
    private String balance;
    private String orderNo;
    private double bankAmount;
    private String orderinfo;
    private int orderId;
    private String timeStamp;
    private String nonceStr;
    private String sign;
    private String prepayId;
    private int isZeroPurchase;
    @Generated(hash = 352954693)
    public PayBean(long uid, double amount, String balance, String orderNo, double bankAmount, String orderinfo, int orderId, String timeStamp, String nonceStr, String sign, String prepayId, int isZeroPurchase) {
        this.uid = uid;
        this.amount = amount;
        this.balance = balance;
        this.orderNo = orderNo;
        this.bankAmount = bankAmount;
        this.orderinfo = orderinfo;
        this.orderId = orderId;
        this.timeStamp = timeStamp;
        this.nonceStr = nonceStr;
        this.sign = sign;
        this.prepayId = prepayId;
        this.isZeroPurchase = isZeroPurchase;
    }
    @Generated(hash = 1567866339)
    public PayBean() {
    }

  
}
