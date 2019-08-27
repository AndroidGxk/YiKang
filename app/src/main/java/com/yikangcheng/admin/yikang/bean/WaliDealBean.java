package com.yikangcheng.admin.yikang.bean;

import java.util.List;


public class WaliDealBean {
    /**
     * order : {"orderId":1856,"orderNo":"NO156110641455992","userId":92,"sumPrice":0.06,"yhPrice":0,"realPrice":0.06,"freightPrice":0,"createTime":"2019-06-21 16:40:15","payTime":"2019-06-21 16:40:22","payType":"ALIPAY","orderState":"SUCCESS","orderForm":"Android","couponcodeId":0,"externalOrderNo":"2019062122001467210570021968","orderType":"PUBLIC_TYPE","afterSaleId":0,"sellTypeList":"#COMMODITY#","toPay":1}
     * haveCommodity : true
     * detailsList : [{"id":2184,"orderId":1856,"orderNo":"NO156110641455992","dataId":18239,"userId":92,"dataType":"COMMODITY","buyNum":1,"createTime":"2019-06-21 16:40:15","payTime":"2019-06-21 16:40:22","payState":"SUCCESS","price":0.01,"freight":2,"openTime":0,"mailName":"EMS","mailKey":"ems","mailCode":"333333","mailStatus":2,"mailTime":"2019-06-21 16:52:20","signStatus":2,"signTime":"2019-07-09 15:46:33","afterSaleStatus":0,"shopSpecDetailed":{"id":18239,"commodityId":5345,"serialNo":"","specIds":"11646,11648","specNames":"白色、s","retailPrice":0.01,"marketPrice":10,"stock":1941,"status":1,"commodityName":"美妆测试008","manufactor":"深圳美盛远实业有限公司","logo":"/upload/mavendemo/course/20190415/1555299397176935540.JPG","count":0,"buyNum":0}},{"id":2185,"orderId":1856,"orderNo":"NO156110641455992","dataId":18240,"userId":92,"dataType":"COMMODITY","buyNum":1,"createTime":"2019-06-21 16:40:15","payTime":"2019-06-21 16:40:22","payState":"SUCCESS","price":0.02,"freight":2,"openTime":0,"mailName":"中通快递","mailKey":"zhongtong","mailCode":"222222","mailStatus":2,"mailTime":"2019-06-21 16:52:28","signStatus":2,"signTime":"2019-06-21 16:58:16","afterSaleStatus":0,"shopSpecDetailed":{"id":18240,"commodityId":5345,"serialNo":"","specIds":"11647,11648","specNames":"红色、s","retailPrice":0.02,"marketPrice":10,"stock":2592,"status":1,"commodityName":"美妆测试008","manufactor":"深圳美盛远实业有限公司","logo":"/upload/mavendemo/course/20190415/1555299397176935540.JPG","count":0,"buyNum":0}},{"id":2186,"orderId":1856,"orderNo":"NO156110641455992","dataId":18241,"userId":92,"dataType":"COMMODITY","buyNum":1,"createTime":"2019-06-21 16:40:15","payTime":"2019-06-21 16:40:22","payState":"SUCCESS","price":0.03,"freight":2,"openTime":0,"mailName":"百世快递","mailKey":"huitongkuaidi","mailCode":"4444444","mailStatus":2,"mailTime":"2019-06-21 16:52:34","signStatus":2,"signTime":"2019-06-21 17:01:03","afterSaleStatus":0,"shopSpecDetailed":{"id":18241,"commodityId":5345,"serialNo":"","specIds":"11646,11649","specNames":"白色、m","retailPrice":0.03,"marketPrice":10,"stock":1598,"status":1,"commodityName":"美妆测试008","manufactor":"深圳美盛远实业有限公司","logo":"/upload/mavendemo/course/20190415/1555299397176935540.JPG","count":0,"buyNum":0}}]
     * orderBook : {"id":1835,"orderId":1856,"orderNo":"NO156110641455992","userId":92,"addressId":173,"shippingType":1,"telConfirm":1,"receiver":"古祥坤","mobile":"13366247835","address":"北京市 北京辖区 东城区 霍营","invoiceType":3}
     */

    private OrderBean order;
    private boolean haveCommodity;
    private OrderBookBean orderBook;
    private List<DetailsListBean> detailsList;

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public boolean isHaveCommodity() {
        return haveCommodity;
    }

    public void setHaveCommodity(boolean haveCommodity) {
        this.haveCommodity = haveCommodity;
    }

    public OrderBookBean getOrderBook() {
        return orderBook;
    }

    public void setOrderBook(OrderBookBean orderBook) {
        this.orderBook = orderBook;
    }

    public List<DetailsListBean> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<DetailsListBean> detailsList) {
        this.detailsList = detailsList;
    }

    public static class OrderBean {
        /**
         * orderId : 1856
         * orderNo : NO156110641455992
         * userId : 92
         * sumPrice : 0.06
         * yhPrice : 0
         * realPrice : 0.06
         * freightPrice : 0
         * createTime : 2019-06-21 16:40:15
         * payTime : 2019-06-21 16:40:22
         * payType : ALIPAY
         * orderState : SUCCESS
         * orderForm : Android
         * couponcodeId : 0
         * externalOrderNo : 2019062122001467210570021968
         * orderType : PUBLIC_TYPE
         * afterSaleId : 0
         * sellTypeList : #COMMODITY#
         * toPay : 1
         */

        private int orderId;
        private String orderNo;
        private int userId;
        private double sumPrice;
        private double yhPrice;
        private double realPrice;
        private double freightPrice;
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

        public void setFreightPrice(double freightPrice) {
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
    }

    public static class OrderBookBean {
        /**
         * id : 1835
         * orderId : 1856
         * orderNo : NO156110641455992
         * userId : 92
         * addressId : 173
         * shippingType : 1
         * telConfirm : 1
         * receiver : 古祥坤
         * mobile : 13366247835
         * address : 北京市 北京辖区 东城区 霍营
         * invoiceType : 3
         */

        private int id;
        private int orderId;
        private String orderNo;
        private int userId;
        private int addressId;
        private int shippingType;
        private int telConfirm;
        private String receiver;
        private String mobile;
        private String address;
        private int invoiceType;

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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getAddressId() {
            return addressId;
        }

        public void setAddressId(int addressId) {
            this.addressId = addressId;
        }

        public int getShippingType() {
            return shippingType;
        }

        public void setShippingType(int shippingType) {
            this.shippingType = shippingType;
        }

        public int getTelConfirm() {
            return telConfirm;
        }

        public void setTelConfirm(int telConfirm) {
            this.telConfirm = telConfirm;
        }

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(int invoiceType) {
            this.invoiceType = invoiceType;
        }
    }

    public static class DetailsListBean {
        /**
         * id : 2184
         * orderId : 1856
         * orderNo : NO156110641455992
         * dataId : 18239
         * userId : 92
         * dataType : COMMODITY
         * buyNum : 1
         * createTime : 2019-06-21 16:40:15
         * payTime : 2019-06-21 16:40:22
         * payState : SUCCESS
         * price : 0.01
         * freight : 2
         * openTime : 0
         * mailName : EMS
         * mailKey : ems
         * mailCode : 333333
         * mailStatus : 2
         * mailTime : 2019-06-21 16:52:20
         * signStatus : 2
         * signTime : 2019-07-09 15:46:33
         * afterSaleStatus : 0
         * shopSpecDetailed : {"id":18239,"commodityId":5345,"serialNo":"","specIds":"11646,11648","specNames":"白色、s","retailPrice":0.01,"marketPrice":10,"stock":1941,"status":1,"commodityName":"美妆测试008","manufactor":"深圳美盛远实业有限公司","logo":"/upload/mavendemo/course/20190415/1555299397176935540.JPG","count":0,"buyNum":0}
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
        private ShopSpecDetailedBean shopSpecDetailed;
        private int assessStatus;

        public int getAssessStatus() {
            return assessStatus;
        }

        public void setAssessStatus(int assessStatus) {
            this.assessStatus = assessStatus;
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

        public ShopSpecDetailedBean getShopSpecDetailed() {
            return shopSpecDetailed;
        }

        public void setShopSpecDetailed(ShopSpecDetailedBean shopSpecDetailed) {
            this.shopSpecDetailed = shopSpecDetailed;
        }

        public static class ShopSpecDetailedBean {
            /**
             * id : 18239
             * commodityId : 5345
             * serialNo :
             * specIds : 11646,11648
             * specNames : 白色、s
             * retailPrice : 0.01
             * marketPrice : 10
             * stock : 1941
             * status : 1
             * commodityName : 美妆测试008
             * manufactor : 深圳美盛远实业有限公司
             * logo : /upload/mavendemo/course/20190415/1555299397176935540.JPG
             * count : 0
             * buyNum : 0
             */

            private int id;
            private int commodityId;
            private String serialNo;
            private String specIds;
            private String specNames;
            private double retailPrice;
            private double marketPrice;
            private int stock;
            private int status;
            private String commodityName;
            private String manufactor;
            private String logo;
            private int count;
            private int buyNum;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public String getSerialNo() {
                return serialNo;
            }

            public void setSerialNo(String serialNo) {
                this.serialNo = serialNo;
            }

            public String getSpecIds() {
                return specIds;
            }

            public void setSpecIds(String specIds) {
                this.specIds = specIds;
            }

            public String getSpecNames() {
                return specNames;
            }

            public void setSpecNames(String specNames) {
                this.specNames = specNames;
            }

            public double getRetailPrice() {
                return retailPrice;
            }

            public void setRetailPrice(double retailPrice) {
                this.retailPrice = retailPrice;
            }

            public double getMarketPrice() {
                return marketPrice;
            }

            public void setMarketPrice(double marketPrice) {
                this.marketPrice = marketPrice;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public String getManufactor() {
                return manufactor;
            }

            public void setManufactor(String manufactor) {
                this.manufactor = manufactor;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getBuyNum() {
                return buyNum;
            }

            public void setBuyNum(int buyNum) {
                this.buyNum = buyNum;
            }
        }
    }
}


