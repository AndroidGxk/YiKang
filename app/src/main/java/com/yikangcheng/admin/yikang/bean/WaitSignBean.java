package com.yikangcheng.admin.yikang.bean;

import java.util.List;

/**
 * 作者：古祥坤 on 2019/7/22 18:35
 * 邮箱：1724959985@qq.com
 */
public class WaitSignBean {

    /**
     * order : [{"email":"1724959985@qq.com","mobile":"13716675831","mailStatus":0,"signStatus":0,"user":{"nickname":"Lil-7","email":"1724959985@qq.com","emailIsavalible":0,"mobile":"13716675831","mobileIsavalible":0,"isavalible":0,"userip":"","userType":0,"province":"","city":"","district":"","longitude":0,"dimensionality":0,"expenses":0,"bounsPoints":0,"isCreate":0,"man":"0","woman":"0","unknown":"0","manRatio":"0%","womanRatio":"0%","unkonwnRatio":"0%","amount":0},"orderId":2284,"orderNo":"NO1563847415100151","userId":151,"sumPrice":42.9,"yhPrice":0,"realPrice":42.9,"freightPrice":0,"createTime":"2019-07-23 10:03:35","payTime":"2019-07-23 10:04:52","payType":"WEIXIN","orderState":"SUCCESS","orderForm":"Android","couponcodeId":0,"externalOrderNo":"4200000398201907234902543270","orderType":"PUBLIC_TYPE","afterSaleId":0,"sellTypeList":"#COMMODITY#","toPay":1,"payForm":"MOBILE","orderDetailsList":[{"id":2828,"orderId":2284,"orderNo":"NO1563847415100151","dataId":25480,"userId":151,"dataType":"COMMODITY","buyNum":1,"createTime":"2019-07-23 10:03:35","payTime":"2019-07-23 10:04:52","payState":"SUCCESS","price":42.9,"freight":2,"openTime":0,"mailName":"申通快递","mailKey":"shentong","mailCode":"3718130807674","mailStatus":2,"mailTime":"2019-07-23 10:30:31","signStatus":1,"afterSaleStatus":0,"shopName":"奔跑吧蛋蛋香辣咸蛋黄风味拌面 杯装 92g*4","specNames":"92g*4杯","commodityId":8429,"shopImg":"/upload/mavendemo/course/20190621/1561083034974377768.jpg","title":"Running Egg 隶属于澳洲品牌，专注于食品业务，品质做到独特，创新，生态，健康，可循环。把消费者健康放到首位。永远为消费者做善事，不做破坏环境平衡的事情。\r\nRunning Egg 带来的不仅仅是安全，健康，美味的体验，它更是在传递一种关注健康的生活态度和对你，家人，朋友分享爱的生活哲学打破传统拌面，口味更加香浓润滑，回味无穷。"}],"userName":"Lil-7"}]
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

    public static class OrderBean {
        /**
         * email : 1724959985@qq.com
         * mobile : 13716675831
         * mailStatus : 0
         * signStatus : 0
         * user : {"nickname":"Lil-7","email":"1724959985@qq.com","emailIsavalible":0,"mobile":"13716675831","mobileIsavalible":0,"isavalible":0,"userip":"","userType":0,"province":"","city":"","district":"","longitude":0,"dimensionality":0,"expenses":0,"bounsPoints":0,"isCreate":0,"man":"0","woman":"0","unknown":"0","manRatio":"0%","womanRatio":"0%","unkonwnRatio":"0%","amount":0}
         * orderId : 2284
         * orderNo : NO1563847415100151
         * userId : 151
         * sumPrice : 42.9
         * yhPrice : 0
         * realPrice : 42.9
         * freightPrice : 0
         * createTime : 2019-07-23 10:03:35
         * payTime : 2019-07-23 10:04:52
         * payType : WEIXIN
         * orderState : SUCCESS
         * orderForm : Android
         * couponcodeId : 0
         * externalOrderNo : 4200000398201907234902543270
         * orderType : PUBLIC_TYPE
         * afterSaleId : 0
         * sellTypeList : #COMMODITY#
         * toPay : 1
         * payForm : MOBILE
         * orderDetailsList : [{"id":2828,"orderId":2284,"orderNo":"NO1563847415100151","dataId":25480,"userId":151,"dataType":"COMMODITY","buyNum":1,"createTime":"2019-07-23 10:03:35","payTime":"2019-07-23 10:04:52","payState":"SUCCESS","price":42.9,"freight":2,"openTime":0,"mailName":"申通快递","mailKey":"shentong","mailCode":"3718130807674","mailStatus":2,"mailTime":"2019-07-23 10:30:31","signStatus":1,"afterSaleStatus":0,"shopName":"奔跑吧蛋蛋香辣咸蛋黄风味拌面 杯装 92g*4","specNames":"92g*4杯","commodityId":8429,"shopImg":"/upload/mavendemo/course/20190621/1561083034974377768.jpg","title":"Running Egg 隶属于澳洲品牌，专注于食品业务，品质做到独特，创新，生态，健康，可循环。把消费者健康放到首位。永远为消费者做善事，不做破坏环境平衡的事情。\r\nRunning Egg 带来的不仅仅是安全，健康，美味的体验，它更是在传递一种关注健康的生活态度和对你，家人，朋友分享爱的生活哲学打破传统拌面，口味更加香浓润滑，回味无穷。"}]
         * userName : Lil-7
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
        private int yhPrice;
        private double realPrice;
        private int freightPrice;
        private String createTime;
        private String payTime;
        private String payType;
        private String orderState;
        private String orderForm;
        private int couponcodeId;
        private String externalOrderNo;
        private String orderType;
        private int afterSaleId;
        private String sellTypeList;
        private int toPay;
        private String payForm;
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

        public int getYhPrice() {
            return yhPrice;
        }

        public void setYhPrice(int yhPrice) {
            this.yhPrice = yhPrice;
        }

        public double getRealPrice() {
            return realPrice;
        }

        public void setRealPrice(double realPrice) {
            this.realPrice = realPrice;
        }

        public int getFreightPrice() {
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

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
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

        public String getExternalOrderNo() {
            return externalOrderNo;
        }

        public void setExternalOrderNo(String externalOrderNo) {
            this.externalOrderNo = externalOrderNo;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public int getAfterSaleId() {
            return afterSaleId;
        }

        public void setAfterSaleId(int afterSaleId) {
            this.afterSaleId = afterSaleId;
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

        public String getPayForm() {
            return payForm;
        }

        public void setPayForm(String payForm) {
            this.payForm = payForm;
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
             * nickname : Lil-7
             * email : 1724959985@qq.com
             * emailIsavalible : 0
             * mobile : 13716675831
             * mobileIsavalible : 0
             * isavalible : 0
             * userip :
             * userType : 0
             * province :
             * city :
             * district :
             * longitude : 0
             * dimensionality : 0
             * expenses : 0
             * bounsPoints : 0
             * isCreate : 0
             * man : 0
             * woman : 0
             * unknown : 0
             * manRatio : 0%
             * womanRatio : 0%
             * unkonwnRatio : 0%
             * amount : 0
             */

            private String nickname;
            private String email;
            private int emailIsavalible;
            private String mobile;
            private int mobileIsavalible;
            private int isavalible;
            private String userip;
            private int userType;
            private String province;
            private String city;
            private String district;
            private int longitude;
            private int dimensionality;
            private int expenses;
            private int bounsPoints;
            private int isCreate;
            private String man;
            private String woman;
            private String unknown;
            private String manRatio;
            private String womanRatio;
            private String unkonwnRatio;
            private int amount;

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

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public int getLongitude() {
                return longitude;
            }

            public void setLongitude(int longitude) {
                this.longitude = longitude;
            }

            public int getDimensionality() {
                return dimensionality;
            }

            public void setDimensionality(int dimensionality) {
                this.dimensionality = dimensionality;
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

            public String getMan() {
                return man;
            }

            public void setMan(String man) {
                this.man = man;
            }

            public String getWoman() {
                return woman;
            }

            public void setWoman(String woman) {
                this.woman = woman;
            }

            public String getUnknown() {
                return unknown;
            }

            public void setUnknown(String unknown) {
                this.unknown = unknown;
            }

            public String getManRatio() {
                return manRatio;
            }

            public void setManRatio(String manRatio) {
                this.manRatio = manRatio;
            }

            public String getWomanRatio() {
                return womanRatio;
            }

            public void setWomanRatio(String womanRatio) {
                this.womanRatio = womanRatio;
            }

            public String getUnkonwnRatio() {
                return unkonwnRatio;
            }

            public void setUnkonwnRatio(String unkonwnRatio) {
                this.unkonwnRatio = unkonwnRatio;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }
        }

        public static class OrderDetailsListBean {
            /**
             * id : 2828
             * orderId : 2284
             * orderNo : NO1563847415100151
             * dataId : 25480
             * userId : 151
             * dataType : COMMODITY
             * buyNum : 1
             * createTime : 2019-07-23 10:03:35
             * payTime : 2019-07-23 10:04:52
             * payState : SUCCESS
             * price : 42.9
             * freight : 2
             * openTime : 0
             * mailName : 申通快递
             * mailKey : shentong
             * mailCode : 3718130807674
             * mailStatus : 2
             * mailTime : 2019-07-23 10:30:31
             * signStatus : 1
             * afterSaleStatus : 0
             * shopName : 奔跑吧蛋蛋香辣咸蛋黄风味拌面 杯装 92g*4
             * specNames : 92g*4杯
             * commodityId : 8429
             * shopImg : /upload/mavendemo/course/20190621/1561083034974377768.jpg
             * title : Running Egg 隶属于澳洲品牌，专注于食品业务，品质做到独特，创新，生态，健康，可循环。把消费者健康放到首位。永远为消费者做善事，不做破坏环境平衡的事情。
             * Running Egg 带来的不仅仅是安全，健康，美味的体验，它更是在传递一种关注健康的生活态度和对你，家人，朋友分享爱的生活哲学打破传统拌面，口味更加香浓润滑，回味无穷。
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
