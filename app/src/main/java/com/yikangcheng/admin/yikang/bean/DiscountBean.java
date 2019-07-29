package com.yikangcheng.admin.yikang.bean;

import java.util.List;

/**
 * 作者：古祥坤 on 2019/5/26 10:58
 * 邮箱：1724959985@qq.com
 * 优惠券
 */
public class DiscountBean {
    /**
     * couponList : [{"title":"满600元可使用","startTime":"2019-06-04 00:00:00","endTime":"2019-08-08 00:00:00","limitAmount":600,"amount":100,"useType":2,"type":2,"optuserName":"\"adminsys\"","range":2,"id":61,"info":"满600元可使用","couponId":5,"status":1,"requestId":"","trxorderId":0,"userId":93,"couponCode":"Q20M1K9062813280J655","createTime":"2019-06-28 13:28:06","remindStatus":"INIT"},{"title":"无门槛优惠券","startTime":"2009-05-01 00:00:00","endTime":"2019-08-08 00:00:00","limitAmount":0,"amount":5,"useType":2,"type":2,"optuserName":"\"adminsys\"","range":4,"id":60,"info":"无门槛优惠券","couponId":1,"status":1,"requestId":"","trxorderId":0,"userId":93,"couponCode":"B20R1906281H327477A7","createTime":"2019-06-28 13:27:47","remindStatus":"INIT"}]
     * page : {"totalResultSize":5,"totalPageSize":1,"pageSize":6,"currentPage":1,"startRow":0,"first":false,"last":false}
     */

    private PageBean page;
    private List<CouponListBean> couponList;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<CouponListBean> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<CouponListBean> couponList) {
        this.couponList = couponList;
    }

    public static class PageBean {
        /**
         * totalResultSize : 5
         * totalPageSize : 1
         * pageSize : 6
         * currentPage : 1
         * startRow : 0
         * first : false
         * last : false
         */

        private int totalResultSize;
        private int totalPageSize;
        private int pageSize;
        private int currentPage;
        private int startRow;
        private boolean first;
        private boolean last;

        public int getTotalResultSize() {
            return totalResultSize;
        }

        public void setTotalResultSize(int totalResultSize) {
            this.totalResultSize = totalResultSize;
        }

        public int getTotalPageSize() {
            return totalPageSize;
        }

        public void setTotalPageSize(int totalPageSize) {
            this.totalPageSize = totalPageSize;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }
    }

    public static class CouponListBean {
        /**
         * title : 满600元可使用
         * startTime : 2019-06-04 00:00:00
         * endTime : 2019-08-08 00:00:00
         * limitAmount : 600
         * amount : 100
         * useType : 2
         * type : 2
         * optuserName : "adminsys"
         * range : 2
         * id : 61
         * info : 满600元可使用
         * couponId : 5
         * status : 1
         * requestId :
         * trxorderId : 0
         * userId : 93
         * couponCode : Q20M1K9062813280J655
         * createTime : 2019-06-28 13:28:06
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
}
