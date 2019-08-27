package com.yikangcheng.admin.yikang.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者：古祥坤 on 2019/5/28 13:51
 * 邮箱：1724959985@qq.com
 */
@Entity
public class CreatOrderBean  {
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
    @Id
    private long uid;
    private String payType;
    private String balance;
    private String orderNo;
    private String bankAmount;
    private String priceKey;
    private double _sumPrice;
    private String orderStatus;
    private boolean success;
    private int orderId;
    private double sumPrice;
    private String orderinfo;
    private String timeStamp;
    private String nonceStr;
    private String sign;
    private String prepayId;
    private int isZeroPurchase;
    @Generated(hash = 462468002)
    public CreatOrderBean(long uid, String payType, String balance, String orderNo,
            String bankAmount, String priceKey, double _sumPrice,
            String orderStatus, boolean success, int orderId, double sumPrice,
            String orderinfo, String timeStamp, String nonceStr, String sign,
            String prepayId, int isZeroPurchase) {
        this.uid = uid;
        this.payType = payType;
        this.balance = balance;
        this.orderNo = orderNo;
        this.bankAmount = bankAmount;
        this.priceKey = priceKey;
        this._sumPrice = _sumPrice;
        this.orderStatus = orderStatus;
        this.success = success;
        this.orderId = orderId;
        this.sumPrice = sumPrice;
        this.orderinfo = orderinfo;
        this.timeStamp = timeStamp;
        this.nonceStr = nonceStr;
        this.sign = sign;
        this.prepayId = prepayId;
        this.isZeroPurchase = isZeroPurchase;
    }
    @Generated(hash = 1398930119)
    public CreatOrderBean() {
    }
    public long getUid() {
        return this.uid;
    }
    public void setUid(long uid) {
        this.uid = uid;
    }
    public String getPayType() {
        return this.payType;
    }
    public void setPayType(String payType) {
        this.payType = payType;
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
    public String getBankAmount() {
        return this.bankAmount;
    }
    public void setBankAmount(String bankAmount) {
        this.bankAmount = bankAmount;
    }
    public String getPriceKey() {
        return this.priceKey;
    }
    public void setPriceKey(String priceKey) {
        this.priceKey = priceKey;
    }
    public double get_sumPrice() {
        return this._sumPrice;
    }
    public void set_sumPrice(double _sumPrice) {
        this._sumPrice = _sumPrice;
    }
    public String getOrderStatus() {
        return this.orderStatus;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    public boolean getSuccess() {
        return this.success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public int getOrderId() {
        return this.orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public double getSumPrice() {
        return this.sumPrice;
    }
    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }
    public String getOrderinfo() {
        return this.orderinfo;
    }
    public void setOrderinfo(String orderinfo) {
        this.orderinfo = orderinfo;
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


}
