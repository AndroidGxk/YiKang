package com.yikangcheng.admin.yikang.bean;

/**
 * 作者：古祥坤 on 2019/7/1 10:57
 * 邮箱：1724959985@qq.com
 * 用户订单可使用优惠券
 */
public class CouponusableBean {
    /**
     * title : 无门槛优惠券
     * startTime : 2009-05-01 00:00:00
     * endTime : 2019-08-08 00:00:00
     * limitAmount : 0
     * amount : 5
     * useType : 2
     * type : 2
     * optuserName : "adminsys"
     * range : 4
     * id : 55
     * info : 无门槛优惠券
     * couponId : 1
     * status : 1
     * requestId :
     * trxorderId : 0
     * userId : 151
     * couponCode : 2019062UI71707S5J774
     * createTime : 2019-06-27 16:44:49
     * remindStatus : INIT
     */

    private String title;
    private String startTime;
    private String endTime;
    private int limitAmount;
    private int amount;
    private int useType;
    private int type;
    private String optuserName;
    private int range;
    private int id;
    private String info;
    private int couponId;
    private int status;
    private String requestId;
    private int trxorderId;
    private int userId;
    private String couponCode;
    private String createTime;
    private String remindStatus;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(int limitAmount) {
        this.limitAmount = limitAmount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getUseType() {
        return useType;
    }

    public void setUseType(int useType) {
        this.useType = useType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOptuserName() {
        return optuserName;
    }

    public void setOptuserName(String optuserName) {
        this.optuserName = optuserName;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getTrxorderId() {
        return trxorderId;
    }

    public void setTrxorderId(int trxorderId) {
        this.trxorderId = trxorderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRemindStatus() {
        return remindStatus;
    }

    public void setRemindStatus(String remindStatus) {
        this.remindStatus = remindStatus;
    }
}
