package com.yikangcheng.admin.yikang.bean;

import java.util.List;

/**
 * Created by lenovo on 2019/5/24.
 * WF
 */
public class ObligationBean {

    /**
     * order : [{"email":"","mobile":"15210957177","mailStatus":0,"signStatus":0,"user":{"nickname":"15210957177","email":"","emailIsavalible":0,"mobile":"15210957177","mobileIsavalible":0,"isavalible":0,"userip":"","userType":0,"expenses":0,"bounsPoints":0,"isCreate":0},"orderId":178,"orderNo":"NO155869129612511","userId":11,"sumPrice":269,"yhPrice":0,"realPrice":269,"freightPrice":0,"createTime":"2019-05-24 17:48:16","payType":"WEIXIN","orderState":"INIT","orderForm":"MOBILE","couponcodeId":0,"orderType":"PUBLIC_TYPE","sellTypeList":"#COMMODITY#","toPay":1,"orderDetailsList":[{"id":191,"orderId":178,"orderNo":"NO155869129612511","dataId":20713,"userId":11,"dataType":"COMMODITY","buyNum":1,"createTime":"2019-05-24 17:48:16","payState":"INIT","price":269,"freight":2,"openTime":0,"mailStatus":1,"signStatus":1,"afterSaleStatus":0,"shopName":"宝格丽（BVLGARI）紫晶女士淡香水25ml（紫晶纯香 花舞轻盈 紫水晶）","specNames":"25ml","commodityId":6402,"shopImg":"/upload/mavendemo/course/20190510/1557452192783946692.jpg","title":"宝格丽（BVLGARI）紫晶女士淡香水25ml（紫晶纯香 花舞轻盈 紫水晶）\r\n"}],"userName":"15210957177"}]
     * page : {"totalResultSize":1,"totalPageSize":1,"pageSize":10,"currentPage":1,"startRow":0,"first":false,"last":false}
     */

    private PageBean page;
    private List<OrderBean> order;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<OrderBean> getOrder() {
        return order;
    }

    public void setOrder(List<OrderBean> order) {
        this.order = order;
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
        private double pageSize;
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

        public double getPageSize() {
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

    public static class OrderBean {
        /**
         * email :
         * mobile : 15210957177
         * mailStatus : 0
         * signStatus : 0
         * user : {"nickname":"15210957177","email":"","emailIsavalible":0,"mobile":"15210957177","mobileIsavalible":0,"isavalible":0,"userip":"","userType":0,"expenses":0,"bounsPoints":0,"isCreate":0}
         * orderId : 178
         * orderNo : NO155869129612511
         * userId : 11
         * sumPrice : 269
         * yhPrice : 0
         * realPrice : 269
         * freightPrice : 0
         * createTime : 2019-05-24 17:48:16
         * payType : WEIXIN
         * orderState : INIT
         * orderForm : MOBILE
         * couponcodeId : 0
         * orderType : PUBLIC_TYPE
         * sellTypeList : #COMMODITY#
         * toPay : 1
         * orderDetailsList : [{"id":191,"orderId":178,"orderNo":"NO155869129612511","dataId":20713,"userId":11,"dataType":"COMMODITY","buyNum":1,"createTime":"2019-05-24 17:48:16","payState":"INIT","price":269,"freight":2,"openTime":0,"mailStatus":1,"signStatus":1,"afterSaleStatus":0,"shopName":"宝格丽（BVLGARI）紫晶女士淡香水25ml（紫晶纯香 花舞轻盈 紫水晶）","specNames":"25ml","commodityId":6402,"shopImg":"/upload/mavendemo/course/20190510/1557452192783946692.jpg","title":"宝格丽（BVLGARI）紫晶女士淡香水25ml（紫晶纯香 花舞轻盈 紫水晶）\r\n"}]
         * userName : 15210957177
         */

        private String email;
        private String mobile;
        private int mailStatus;
        private int signStatus;
        private UserBean user;
        private int orderId;
        private String orderNo;
        private int userId;
        private double sumPrice;
        private double yhPrice;
        private double realPrice;
        private double freightPrice;
        private String createTime;
        private String payType;
        private String orderState;
        private String orderForm;
        private int couponcodeId;
        private String orderType;
        private String sellTypeList;
        private int toPay;
        private String userName;
        private List<OrderDetailsListBean> orderDetailsList;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getMailStatus() {
            return mailStatus;
        }

        public void setMailStatus(int mailStatus) {
            this.mailStatus = mailStatus;
        }

        public int getSignStatus() {
            return signStatus;
        }

        public void setSignStatus(int signStatus) {
            this.signStatus = signStatus;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public double getSumPrice() {
            return sumPrice;
        }

        public void setSumPrice(double sumPrice) {
            this.sumPrice = sumPrice;
        }

        public double getYhPrice() {
            return yhPrice;
        }

        public void setYhPrice(double yhPrice) {
            this.yhPrice = yhPrice;
        }

        public double getRealPrice() {
            return realPrice;
        }

        public void setRealPrice(double realPrice) {
            this.realPrice = realPrice;
        }

        public double getFreightPrice() {
            return freightPrice;
        }

        public void setFreightPrice(int freightPrice) {
            this.freightPrice = freightPrice;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getOrderState() {
            return orderState;
        }

        public void setOrderState(String orderState) {
            this.orderState = orderState;
        }

        public String getOrderForm() {
            return orderForm;
        }

        public void setOrderForm(String orderForm) {
            this.orderForm = orderForm;
        }

        public int getCouponcodeId() {
            return couponcodeId;
        }

        public void setCouponcodeId(int couponcodeId) {
            this.couponcodeId = couponcodeId;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getSellTypeList() {
            return sellTypeList;
        }

        public void setSellTypeList(String sellTypeList) {
            this.sellTypeList = sellTypeList;
        }

        public int getToPay() {
            return toPay;
        }

        public void setToPay(int toPay) {
            this.toPay = toPay;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public List<OrderDetailsListBean> getOrderDetailsList() {
            return orderDetailsList;
        }

        public void setOrderDetailsList(List<OrderDetailsListBean> orderDetailsList) {
            this.orderDetailsList = orderDetailsList;
        }

        public static class UserBean {
            /**
             * nickname : 15210957177
             * email :
             * emailIsavalible : 0
             * mobile : 15210957177
             * mobileIsavalible : 0
             * isavalible : 0
             * userip :
             * userType : 0
             * expenses : 0
             * bounsPoints : 0
             * isCreate : 0
             */

            private String nickname;
            private String email;
            private int emailIsavalible;
            private String mobile;
            private int mobileIsavalible;
            private int isavalible;
            private String userip;
            private int userType;
            private int expenses;
            private int bounsPoints;
            private int isCreate;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getEmailIsavalible() {
                return emailIsavalible;
            }

            public void setEmailIsavalible(int emailIsavalible) {
                this.emailIsavalible = emailIsavalible;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public int getMobileIsavalible() {
                return mobileIsavalible;
            }

            public void setMobileIsavalible(int mobileIsavalible) {
                this.mobileIsavalible = mobileIsavalible;
            }

            public int getIsavalible() {
                return isavalible;
            }

            public void setIsavalible(int isavalible) {
                this.isavalible = isavalible;
            }

            public String getUserip() {
                return userip;
            }

            public void setUserip(String userip) {
                this.userip = userip;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public int getExpenses() {
                return expenses;
            }

            public void setExpenses(int expenses) {
                this.expenses = expenses;
            }

            public int getBounsPoints() {
                return bounsPoints;
            }

            public void setBounsPoints(int bounsPoints) {
                this.bounsPoints = bounsPoints;
            }

            public int getIsCreate() {
                return isCreate;
            }

            public void setIsCreate(int isCreate) {
                this.isCreate = isCreate;
            }
        }

        public static class OrderDetailsListBean {
            /**
             * id : 191
             * orderId : 178
             * orderNo : NO155869129612511
             * dataId : 20713
             * userId : 11
             * dataType : COMMODITY
             * buyNum : 1
             * createTime : 2019-05-24 17:48:16
             * payState : INIT
             * price : 269
             * freight : 2
             * openTime : 0
             * mailStatus : 1
             * signStatus : 1
             * afterSaleStatus : 0
             * shopName : 宝格丽（BVLGARI）紫晶女士淡香水25ml（紫晶纯香 花舞轻盈 紫水晶）
             * specNames : 25ml
             * commodityId : 6402
             * shopImg : /upload/mavendemo/course/20190510/1557452192783946692.jpg
             * title : 宝格丽（BVLGARI）紫晶女士淡香水25ml（紫晶纯香 花舞轻盈 紫水晶）
             */

            private int id;
            private int orderId;
            private String orderNo;
            private int dataId;
            private int userId;
            private String dataType;
            private int buyNum;
            private String createTime;
            private String payState;
            private double price;
            private int freight;
            private int openTime;
            private int mailStatus;
            private int signStatus;
            private int afterSaleStatus;
            private String shopName;
            private String specNames;
            private int commodityId;
            private String shopImg;
            private String title;

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

            public int getMailStatus() {
                return mailStatus;
            }

            public void setMailStatus(int mailStatus) {
                this.mailStatus = mailStatus;
            }

            public int getSignStatus() {
                return signStatus;
            }

            public void setSignStatus(int signStatus) {
                this.signStatus = signStatus;
            }

            public int getAfterSaleStatus() {
                return afterSaleStatus;
            }

            public void setAfterSaleStatus(int afterSaleStatus) {
                this.afterSaleStatus = afterSaleStatus;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public String getSpecNames() {
                return specNames;
            }

            public void setSpecNames(String specNames) {
                this.specNames = specNames;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public String getShopImg() {
                return shopImg;
            }

            public void setShopImg(String shopImg) {
                this.shopImg = shopImg;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

        }
    }
}
