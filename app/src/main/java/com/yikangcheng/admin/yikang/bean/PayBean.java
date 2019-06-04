package com.yikangcheng.admin.yikang.bean;

/**
 * Created by lenovo on 2019/6/3.
 * WF
 */
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

    /**
     * amount : 2610
     * balance : 0.00
     * orderNo : NO155954777354092
     * bankAmount : 2610
     * orderinfo : alipay_sdk=alipay-sdk-java-3.7.26.ALL&app_id=2019030863469663&biz_content=%7B%22body%22%3A%22APP%E8%B4%AD%E7%89%A9%E6%B6%88%E8%B4%B9%22%2C%22out_trade_no%22%3A%22NO155954777354092%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22NO155954777354092%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%222610.0%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay¬ify_url=https%3A%2F%2Fwww.yikch.com%2Fapi%2Forder%2FpaySuccess&sign=Sxk4fIe1B2cB9krL5fYrdMxtEYopD2WXpOXsiUQB8LRoHk9D7rC0tPZ4pGvlH3s7srVUwL9WVIAx%2BRAk8SxE5K7ag6JSvzkUOkF53owWVxfFkpsT5DGnjf4wFzfQkZMlFpkWGd2eD7sV6pwdzCGdixNkpm%2FdoA9zmiA%2FfeaEEevl144vKh0Gv97CUob1Vo28smiQ9AzGfAEYm6OSTfGEGRaaFGtKz6p5uaFvzjtU6nZiAUezHCwZtLzdBZDGiBew4Oa1i77qS77WBphEL%2FVMqpnLl70SXSzbDWLCfNPXHu3s0a591OtqaCIGK34CKCHF1nVlTxQ55UcGr8AiVL9GRA%3D%3D&sign_type=RSA2×tamp=2019-06-03+15%3A48%3A07&version=1.0
     * orderId : 733
     */


    private double amount;
    private String balance;
    private String orderNo;
    private double bankAmount;
    private String orderinfo;
    private int orderId;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public double getBankAmount() {
        return bankAmount;
    }

    public void setBankAmount(double bankAmount) {
        this.bankAmount = bankAmount;
    }

    public String getOrderinfo() {
        return orderinfo;
    }

    public void setOrderinfo(String orderinfo) {
        this.orderinfo = orderinfo;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
