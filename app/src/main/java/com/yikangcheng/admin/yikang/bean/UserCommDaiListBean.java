package com.yikangcheng.admin.yikang.bean;

/**
 * 作者：古祥坤 on 2019/8/19 15:56
 * 邮箱：1724959985@qq.com
 */

import java.util.List;

/**
 * 用户评论列表数据
 */
public class UserCommDaiListBean {

    /**
     * orderDetailsList : [{"id":2838,"orderId":2294,"orderNo":"NO156386531243092","dataId":18239,"userId":92,"dataType":"COMMODITY","buyNum":1,"createTime":"2019-07-23 15:01:52","payTime":"2019-07-23 15:02:02","payState":"SUCCESS","price":0.01,"freight":2,"openTime":0,"mailName":"德邦","mailKey":"debangwuliu","mailCode":"234234234241","mailStatus":2,"mailTime":"2019-07-24 13:31:17","signStatus":2,"signTime":"2019-07-24 14:14:58","afterSaleStatus":0,"assessStatus":1,"commodityName":"美妆测试008","commodityLogo":"/upload/mavendemo/course/20190415/1555299397176935540.JPG"},{"id":2722,"orderId":2207,"orderNo":"NO156283789112492","dataId":18239,"userId":92,"dataType":"COMMODITY","buyNum":1,"createTime":"2019-07-11 17:38:11","payTime":"2019-07-11 17:38:18","payState":"SUCCESS","price":0.01,"freight":2,"openTime":0,"mailName":"顺丰速运","mailKey":"shunfeng","mailCode":"22222","mailStatus":2,"mailTime":"2019-07-11 17:38:52","signStatus":2,"signTime":"2019-07-14 12:26:25","afterSaleStatus":0,"assessStatus":1,"commodityName":"美妆测试008","commodityLogo":"/upload/mavendemo/course/20190415/1555299397176935540.JPG"},{"id":2723,"orderId":2207,"orderNo":"NO156283789112492","dataId":18241,"userId":92,"dataType":"COMMODITY","buyNum":1,"createTime":"2019-07-11 17:38:11","payTime":"2019-07-11 17:38:18","payState":"SUCCESS","price":0.03,"freight":2,"openTime":0,"mailName":"顺丰速运","mailKey":"shunfeng","mailCode":"22222","mailStatus":2,"mailTime":"2019-07-11 17:38:59","signStatus":2,"signTime":"2019-07-11 18:21:07","afterSaleStatus":0,"assessStatus":1,"commodityName":"美妆测试008","commodityLogo":"/upload/mavendemo/course/20190415/1555299397176935540.JPG"},{"id":2724,"orderId":2207,"orderNo":"NO156283789112492","dataId":18240,"userId":92,"dataType":"COMMODITY","buyNum":1,"createTime":"2019-07-11 17:38:11","payTime":"2019-07-11 17:38:18","payState":"SUCCESS","price":0.02,"freight":2,"openTime":0,"mailName":"顺丰速运","mailKey":"shunfeng","mailCode":"22222","mailStatus":2,"mailTime":"2019-07-11 17:39:05","signStatus":2,"signTime":"2019-07-22 19:37:12","afterSaleStatus":0,"assessStatus":1,"commodityName":"美妆测试008","commodityLogo":"/upload/mavendemo/course/20190415/1555299397176935540.JPG"},{"id":2719,"orderId":2206,"orderNo":"NO156283014033492","dataId":18239,"userId":92,"dataType":"COMMODITY","buyNum":1,"createTime":"2019-07-11 15:29:00","payTime":"2019-07-11 15:29:07","payState":"SUCCESS","price":0.01,"freight":2,"openTime":0,"mailName":"EMS","mailKey":"ems","mailCode":"111111","mailStatus":2,"mailTime":"2019-07-11 15:52:13","signStatus":2,"signTime":"2019-07-14 12:27:23","afterSaleStatus":0,"assessStatus":1,"commodityName":"美妆测试008","commodityLogo":"/upload/mavendemo/course/20190415/1555299397176935540.JPG"},{"id":2720,"orderId":2206,"orderNo":"NO156283014033492","dataId":18241,"userId":92,"dataType":"COMMODITY","buyNum":1,"createTime":"2019-07-11 15:29:00","payTime":"2019-07-11 15:29:07","payState":"SUCCESS","price":0.03,"freight":2,"openTime":0,"mailName":"EMS","mailKey":"ems","mailCode":"111111","mailStatus":2,"mailTime":"2019-07-11 15:51:54","signStatus":2,"signTime":"2019-07-11 18:28:40","afterSaleStatus":0,"assessStatus":1,"commodityName":"美妆测试008","commodityLogo":"/upload/mavendemo/course/20190415/1555299397176935540.JPG"},{"id":2721,"orderId":2206,"orderNo":"NO156283014033492","dataId":18240,"userId":92,"dataType":"COMMODITY","buyNum":1,"createTime":"2019-07-11 15:29:00","payTime":"2019-07-11 15:29:07","payState":"SUCCESS","price":0.02,"freight":2,"openTime":0,"mailName":"韵达快递","mailKey":"yunda","mailCode":"4444","mailStatus":2,"mailTime":"2019-07-11 17:39:45","signStatus":2,"signTime":"2019-07-22 19:33:13","afterSaleStatus":0,"assessStatus":1,"commodityName":"美妆测试008","commodityLogo":"/upload/mavendemo/course/20190415/1555299397176935540.JPG"},{"id":2215,"orderId":1884,"orderNo":"NO156134398005992","dataId":18075,"userId":92,"dataType":"COMMODITY","buyNum":1,"createTime":"2019-06-24 10:39:40","payTime":"2019-06-24 10:42:29","payState":"SUCCESS","price":31.5,"freight":2,"openTime":0,"mailName":"韵达快递","mailKey":"yunda","mailCode":"3968760584207","mailStatus":2,"mailTime":"2019-06-25 12:07:23","signStatus":2,"signTime":"2019-07-09 15:46:39","afterSaleStatus":0,"assessStatus":1,"commodityName":"海狸先生鳗鱼丝*2","commodityLogo":"/upload/mavendemo/course/20190316/1552718165433141280.jpg"},{"id":2189,"orderId":1859,"orderNo":"NO156110796594592","dataId":18239,"userId":92,"dataType":"COMMODITY","buyNum":1,"createTime":"2019-06-21 17:06:06","payTime":"2019-06-21 17:06:12","payState":"SUCCESS","price":0.01,"freight":2,"openTime":0,"mailName":"圆通速递","mailKey":"yuantong","mailCode":"44444","mailStatus":2,"mailTime":"2019-06-21 18:16:08","signStatus":2,"signTime":"2019-07-08 11:27:46","afterSaleStatus":0,"assessStatus":1,"commodityName":"美妆测试008","commodityLogo":"/upload/mavendemo/course/20190415/1555299397176935540.JPG"},{"id":2184,"orderId":1856,"orderNo":"NO156110641455992","dataId":18239,"userId":92,"dataType":"COMMODITY","buyNum":1,"createTime":"2019-06-21 16:40:15","payTime":"2019-06-21 16:40:22","payState":"SUCCESS","price":0.01,"freight":2,"openTime":0,"mailName":"EMS","mailKey":"ems","mailCode":"333333","mailStatus":2,"mailTime":"2019-06-21 16:52:20","signStatus":2,"signTime":"2019-07-09 15:46:33","afterSaleStatus":0,"assessStatus":1,"commodityName":"美妆测试008","commodityLogo":"/upload/mavendemo/course/20190415/1555299397176935540.JPG"}]
     * page : {"totalResultSize":19,"totalPageSize":2,"pageSize":10,"currentPage":1,"startRow":0,"first":false,"last":false}
     */

    private PageBean page;
    private List<OrderDetailsListBean> orderDetailsList;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<OrderDetailsListBean> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetailsListBean> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    public static class PageBean {
        /**
         * totalResultSize : 19
         * totalPageSize : 2
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

    public static class OrderDetailsListBean {
        /**
         * id : 2838
         * orderId : 2294
         * orderNo : NO156386531243092
         * dataId : 18239
         * userId : 92
         * dataType : COMMODITY
         * buyNum : 1
         * createTime : 2019-07-23 15:01:52
         * payTime : 2019-07-23 15:02:02
         * payState : SUCCESS
         * price : 0.01
         * freight : 2
         * openTime : 0
         * mailName : 德邦
         * mailKey : debangwuliu
         * mailCode : 234234234241
         * mailStatus : 2
         * mailTime : 2019-07-24 13:31:17
         * signStatus : 2
         * signTime : 2019-07-24 14:14:58
         * afterSaleStatus : 0
         * assessStatus : 1
         * commodityName : 美妆测试008
         * commodityLogo : /upload/mavendemo/course/20190415/1555299397176935540.JPG
         */

        private int id;
        private int orderId;
        private String orderNo;
        private int dataId;
        private int userId;
        private String dataType;
        private int buyNum;
        private String createTime;
        private String payTime;
        private String payState;
        private double price;
        private int freight;
        private int openTime;
        private String mailName;
        private String mailKey;
        private String mailCode;
        private int mailStatus;
        private String mailTime;
        private int signStatus;
        private String signTime;
        private int afterSaleStatus;
        private int assessStatus;
        private String commodityName;
        private String commodityLogo;
        private int commodityId;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getDataId() {
            return dataId;
        }

        public void setDataId(int dataId) {
            this.dataId = dataId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public int getBuyNum() {
            return buyNum;
        }

        public void setBuyNum(int buyNum) {
            this.buyNum = buyNum;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getPayState() {
            return payState;
        }

        public void setPayState(String payState) {
            this.payState = payState;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getFreight() {
            return freight;
        }

        public void setFreight(int freight) {
            this.freight = freight;
        }

        public int getOpenTime() {
            return openTime;
        }

        public void setOpenTime(int openTime) {
            this.openTime = openTime;
        }

        public String getMailName() {
            return mailName;
        }

        public void setMailName(String mailName) {
            this.mailName = mailName;
        }

        public String getMailKey() {
            return mailKey;
        }

        public void setMailKey(String mailKey) {
            this.mailKey = mailKey;
        }

        public String getMailCode() {
            return mailCode;
        }

        public void setMailCode(String mailCode) {
            this.mailCode = mailCode;
        }

        public int getMailStatus() {
            return mailStatus;
        }

        public void setMailStatus(int mailStatus) {
            this.mailStatus = mailStatus;
        }

        public String getMailTime() {
            return mailTime;
        }

        public void setMailTime(String mailTime) {
            this.mailTime = mailTime;
        }

        public int getSignStatus() {
            return signStatus;
        }

        public void setSignStatus(int signStatus) {
            this.signStatus = signStatus;
        }

        public String getSignTime() {
            return signTime;
        }

        public void setSignTime(String signTime) {
            this.signTime = signTime;
        }

        public int getAfterSaleStatus() {
            return afterSaleStatus;
        }

        public void setAfterSaleStatus(int afterSaleStatus) {
            this.afterSaleStatus = afterSaleStatus;
        }

        public int getAssessStatus() {
            return assessStatus;
        }

        public void setAssessStatus(int assessStatus) {
            this.assessStatus = assessStatus;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getCommodityLogo() {
            return commodityLogo;
        }

        public void setCommodityLogo(String commodityLogo) {
            this.commodityLogo = commodityLogo;
        }
    }
}
