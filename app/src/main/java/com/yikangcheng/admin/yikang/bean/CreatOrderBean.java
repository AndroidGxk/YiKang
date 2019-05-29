package com.yikangcheng.admin.yikang.bean;

import java.io.Serializable;

/**
 * 作者：古祥坤 on 2019/5/28 13:51
 * 邮箱：1724959985@qq.com
 */
public class CreatOrderBean implements Serializable {
    /**
     * balance : 0.00
     * orderNo : NO155902240021692
     * bankAmount : 983.00
     * priceKey : SELL
     * _sumPrice : 983
     * orderStatus : INIT
     * success : true
     * orderId : 212
     * sumPrice : 983
     */

    private String balance;
    private String orderNo;
    private String bankAmount;
    private String priceKey;
    private double _sumPrice;
    private String orderStatus;
    private boolean success;
    private int orderId;
    private int sumPrice;
    private String orderinfo;

    public String getOrderinfo() {
        return orderinfo;
    }

    public void setOrderinfo(String orderinfo) {
        this.orderinfo = orderinfo;
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

    public String getBankAmount() {
        return bankAmount;
    }

    public void setBankAmount(String bankAmount) {
        this.bankAmount = bankAmount;
    }

    public String getPriceKey() {
        return priceKey;
    }

    public void setPriceKey(String priceKey) {
        this.priceKey = priceKey;
    }

    public double get_sumPrice() {
        return _sumPrice;
    }

    public void set_sumPrice(double _sumPrice) {
        this._sumPrice = _sumPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(int sumPrice) {
        this.sumPrice = sumPrice;
    }
}
