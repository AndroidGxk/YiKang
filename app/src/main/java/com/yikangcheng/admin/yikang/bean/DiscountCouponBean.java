package com.yikangcheng.admin.yikang.bean;

import java.util.List;

/**
 * 作者：古祥坤 on 2019/5/26 16:14
 * 邮箱：1724959985@qq.com
 */
public class DiscountCouponBean {
    /**
     * couponCodeList : [{"id":1,"couponId":2,"status":1,"trxorderId":0,"requestId":0,"userId":11,"coupon":{"title":"优惠券","startTime":"2019-05-01 00:00:00","endTime":"2019-07-01 23:59:59","status":1}}]
     * page : {"totalResultSize":1,"totalPageSize":1,"pageSize":10,"currentPage":1,"startRow":0,"first":false,"last":false}
     */

    private PageBean page;
    private List<CouponCodeListBean> couponCodeList;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<CouponCodeListBean> getCouponCodeList() {
        return couponCodeList;
    }

    public void setCouponCodeList(List<CouponCodeListBean> couponCodeList) {
        this.couponCodeList = couponCodeList;
    }

    public static class PageBean {
        /**
         * totalResultSize : 1
         * totalPageSize : 1
         * pageSize : 10
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

    public static class CouponCodeListBean {
        /**
         * id : 1
         * couponId : 2
         * status : 1
         * trxorderId : 0
         * requestId : 0
         * userId : 11
         * coupon : {"title":"优惠券","startTime":"2019-05-01 00:00:00","endTime":"2019-07-01 23:59:59","status":1}
         */

        private int id;
        private int couponId;
        private int status;
        private int trxorderId;
        private int requestId;
        private int userId;
        private CouponBean coupon;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getTrxorderId() {
            return trxorderId;
        }

        public void setTrxorderId(int trxorderId) {
            this.trxorderId = trxorderId;
        }

        public int getRequestId() {
            return requestId;
        }

        public void setRequestId(int requestId) {
            this.requestId = requestId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public CouponBean getCoupon() {
            return coupon;
        }

        public void setCoupon(CouponBean coupon) {
            this.coupon = coupon;
        }

        public static class CouponBean {
            /**
             * title : 优惠券
             * startTime : 2019-05-01 00:00:00
             * endTime : 2019-07-01 23:59:59
             * status : 1
             */

            private String title;
            private String startTime;
            private String endTime;
            private int status;

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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
