package com.yikangcheng.admin.yikang.bean;

/**
 * 作者：古祥坤 on 2019/5/29 20:39
 * 邮箱：1724959985@qq.com
 */
public class NewOrderBean {
    /**
     * amount : 4307
     * balance : 0.01
     * bankAmount : 4306.99
     * requestId : NO155913268943292
     * orderId : 518
     */

    private int amount;
    private String balance;
    private double bankAmount;
    private String requestId;
    private int orderId;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public double getBankAmount() {
        return bankAmount;
    }

    public void setBankAmount(double bankAmount) {
        this.bankAmount = bankAmount;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
