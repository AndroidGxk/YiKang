package com.yikangcheng.admin.yikang.bean;

import java.util.List;

/**
 * Created by lenovo on 2019/5/27.
 * WF
 */
public class CloseTheDealBean {
    @Override
    public String toString() {
        return "CloseTheDealBean{" +
                "userAddress=" + userAddress +
                ", order=" + order +
                ", haveCommodity=" + haveCommodity +
                ", orderBook=" + orderBook +
                ", detailsList=" + detailsList +
                '}';
    }

    /**
     * userAddress : {"id":2,"userId":2,"receiver":"王华杰","address":"门头沟区季月圆","postCode":"10000","mobile":"18600599022","isFirst":1,"sendTime":1,"createTime":"2019-03-24 17:20:37","provinceId":2,"cityId":33,"townId":378,"provinceStr":"北京市","cityStr":"北京辖区","townStr":"东城区"}
     * order : {"orderId":4,"orderNo":"NO15534192415602","userId":2,"sumPrice":0.02,"yhPrice":0,"realPrice":0.02,"freightPrice":0,"createTime":"2019-03-24 17:20:42","payTime":"2019-03-24 17:21:08","payType":"WEIXIN","orderState":"SUCCESS","orderForm":"WEB","couponcodeId":0,"externalOrderNo":"4200000277201903241287652645","orderType":"PUBLIC_TYPE","sellTypeList":"#COMMODITY#","toPay":1,"payForm":"WEB","balancePay":0,"externalPay":0.02}
     * haveCommodity : true
     * detailsList : [{"id":4,"orderId":4,"orderNo":"NO15534192415602","dataId":12446,"userId":2,"dataType":"COMMODITY","buyNum":2,"createTime":"2019-03-24 17:20:42","payTime":"2019-03-24 17:21:08","payState":"SUCCESS","price":0.01,"freight":2,"openTime":0,"mailStatus":1,"signStatus":1,"afterSaleStatus":0,"shopSpecDetailed":{"id":12446,"commodityId":3828,"specIds":"6826,6829","specNames":"规格1、规格3","retailPrice":0.01,"marketPrice":100,"stock":102,"status":1,"commodityName":"测试商品","manufactor":"易康成","logo":"/upload/mavendemo/course/20190324/1553418373913514689.jpg","count":0,"buyNum":0}}]
     * orderBook : {"id":2,"orderId":4,"orderNo":"NO15534192415602","userId":2,"addressId":2,"shippingType":1,"telConfirm":1,"invoiceType":1,"invoiceFrom":1,"invoiceName":"--","invoiceNo":"--","invoiceContent":1,"invoiceEmail":"--"}
     */

    private UserAddressBean userAddress;
    private OrderBean order;
    private boolean haveCommodity;
    private OrderBookBean orderBook;
    private List<DetailsListBean> detailsList;

    public UserAddressBean getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddressBean userAddress) {
        this.userAddress = userAddress;
    }

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

    public static class UserAddressBean {
        @Override
        public String toString() {
            return "UserAddressBean{" +
                    "id=" + id +
                    ", userId=" + userId +
                    ", receiver='" + receiver + '\'' +
                    ", address='" + address + '\'' +
                    ", postCode='" + postCode + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", isFirst=" + isFirst +
                    ", sendTime=" + sendTime +
                    ", createTime='" + createTime + '\'' +
                    ", provinceId=" + provinceId +
                    ", cityId=" + cityId +
                    ", townId=" + townId +
                    ", provinceStr='" + provinceStr + '\'' +
                    ", cityStr='" + cityStr + '\'' +
                    ", townStr='" + townStr + '\'' +
                    '}';
        }

        /**
         * id : 2
         * userId : 2
         * receiver : 王华杰
         * address : 门头沟区季月圆
         * postCode : 10000
         * mobile : 18600599022
         * isFirst : 1
         * sendTime : 1
         * createTime : 2019-03-24 17:20:37
         * provinceId : 2
         * cityId : 33
         * townId : 378
         * provinceStr : 北京市
         * cityStr : 北京辖区
         * townStr : 东城区
         */

        private int id;
        private int userId;
        private String receiver;
        private String address;
        private String postCode;
        private String mobile;
        private int isFirst;
        private int sendTime;
        private String createTime;
        private int provinceId;
        private int cityId;
        private int townId;
        private String provinceStr;
        private String cityStr;
        private String townStr;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPostCode() {
            return postCode;
        }

        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getIsFirst() {
            return isFirst;
        }

        public void setIsFirst(int isFirst) {
            this.isFirst = isFirst;
        }

        public int getSendTime() {
            return sendTime;
        }

        public void setSendTime(int sendTime) {
            this.sendTime = sendTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public int getTownId() {
            return townId;
        }

        public void setTownId(int townId) {
            this.townId = townId;
        }

        public String getProvinceStr() {
            return provinceStr;
        }

        public void setProvinceStr(String provinceStr) {
            this.provinceStr = provinceStr;
        }

        public String getCityStr() {
            return cityStr;
        }

        public void setCityStr(String cityStr) {
            this.cityStr = cityStr;
        }

        public String getTownStr() {
            return townStr;
        }

        public void setTownStr(String townStr) {
            this.townStr = townStr;
        }
    }

    public static class OrderBean {
        @Override
        public String toString() {
            return "OrderBean{" +
                    "orderId=" + orderId +
                    ", orderNo='" + orderNo + '\'' +
                    ", userId=" + userId +
                    ", sumPrice=" + sumPrice +
                    ", yhPrice=" + yhPrice +
                    ", realPrice=" + realPrice +
                    ", freightPrice=" + freightPrice +
                    ", createTime='" + createTime + '\'' +
                    ", payTime='" + payTime + '\'' +
                    ", payType='" + payType + '\'' +
                    ", orderState='" + orderState + '\'' +
                    ", orderForm='" + orderForm + '\'' +
                    ", couponcodeId=" + couponcodeId +
                    ", externalOrderNo='" + externalOrderNo + '\'' +
                    ", orderType='" + orderType + '\'' +
                    ", sellTypeList='" + sellTypeList + '\'' +
                    ", toPay=" + toPay +
                    ", payForm='" + payForm + '\'' +
                    ", balancePay=" + balancePay +
                    ", externalPay=" + externalPay +
                    '}';
        }

        /**
         * orderId : 4
         * orderNo : NO15534192415602
         * userId : 2
         * sumPrice : 0.02
         * yhPrice : 0
         * realPrice : 0.02
         * freightPrice : 0
         * createTime : 2019-03-24 17:20:42
         * payTime : 2019-03-24 17:21:08
         * payType : WEIXIN
         * orderState : SUCCESS
         * orderForm : WEB
         * couponcodeId : 0
         * externalOrderNo : 4200000277201903241287652645
         * orderType : PUBLIC_TYPE
         * sellTypeList : #COMMODITY#
         * toPay : 1
         * payForm : WEB
         * balancePay : 0
         * externalPay : 0.02
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
        private String sellTypeList;
        private int toPay;
        private String payForm;
        private int balancePay;
        private double externalPay;

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

        public int getBalancePay() {
            return balancePay;
        }

        public void setBalancePay(int balancePay) {
            this.balancePay = balancePay;
        }

        public double getExternalPay() {
            return externalPay;
        }

        public void setExternalPay(double externalPay) {
            this.externalPay = externalPay;
        }
    }

    public static class OrderBookBean {
        @Override
        public String toString() {
            return "OrderBookBean{" +
                    "id=" + id +
                    ", orderId=" + orderId +
                    ", orderNo='" + orderNo + '\'' +
                    ", userId=" + userId +
                    ", addressId=" + addressId +
                    ", shippingType=" + shippingType +
                    ", telConfirm=" + telConfirm +
                    ", invoiceType=" + invoiceType +
                    ", invoiceFrom=" + invoiceFrom +
                    ", invoiceName='" + invoiceName + '\'' +
                    ", invoiceNo='" + invoiceNo + '\'' +
                    ", invoiceContent=" + invoiceContent +
                    ", invoiceEmail='" + invoiceEmail + '\'' +
                    '}';
        }

        /**
         * id : 2
         * orderId : 4
         * orderNo : NO15534192415602
         * userId : 2
         * addressId : 2
         * shippingType : 1
         * telConfirm : 1
         * invoiceType : 1
         * invoiceFrom : 1
         * invoiceName : --
         * invoiceNo : --
         * invoiceContent : 1
         * invoiceEmail : --
         */

        private int id;
        private int orderId;
        private String orderNo;
        private int userId;
        private int addressId;
        private int shippingType;
        private int telConfirm;
        private int invoiceType;
        private int invoiceFrom;
        private String invoiceName;
        private String invoiceNo;
        private int invoiceContent;
        private String invoiceEmail;

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

        public int getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(int invoiceType) {
            this.invoiceType = invoiceType;
        }

        public int getInvoiceFrom() {
            return invoiceFrom;
        }

        public void setInvoiceFrom(int invoiceFrom) {
            this.invoiceFrom = invoiceFrom;
        }

        public String getInvoiceName() {
            return invoiceName;
        }

        public void setInvoiceName(String invoiceName) {
            this.invoiceName = invoiceName;
        }

        public String getInvoiceNo() {
            return invoiceNo;
        }

        public void setInvoiceNo(String invoiceNo) {
            this.invoiceNo = invoiceNo;
        }

        public int getInvoiceContent() {
            return invoiceContent;
        }

        public void setInvoiceContent(int invoiceContent) {
            this.invoiceContent = invoiceContent;
        }

        public String getInvoiceEmail() {
            return invoiceEmail;
        }

        public void setInvoiceEmail(String invoiceEmail) {
            this.invoiceEmail = invoiceEmail;
        }
    }

    public static class DetailsListBean {
        @Override
        public String toString() {
            return "DetailsListBean{" +
                    "id=" + id +
                    ", orderId=" + orderId +
                    ", orderNo='" + orderNo + '\'' +
                    ", dataId=" + dataId +
                    ", userId=" + userId +
                    ", dataType='" + dataType + '\'' +
                    ", buyNum=" + buyNum +
                    ", createTime='" + createTime + '\'' +
                    ", payTime='" + payTime + '\'' +
                    ", payState='" + payState + '\'' +
                    ", price=" + price +
                    ", freight=" + freight +
                    ", openTime=" + openTime +
                    ", mailStatus=" + mailStatus +
                    ", signStatus=" + signStatus +
                    ", afterSaleStatus=" + afterSaleStatus +
                    ", shopSpecDetailed=" + shopSpecDetailed +
                    '}';
        }

        /**
         * id : 4
         * orderId : 4
         * orderNo : NO15534192415602
         * dataId : 12446
         * userId : 2
         * dataType : COMMODITY
         * buyNum : 2
         * createTime : 2019-03-24 17:20:42
         * payTime : 2019-03-24 17:21:08
         * payState : SUCCESS
         * price : 0.01
         * freight : 2
         * openTime : 0
         * mailStatus : 1
         * signStatus : 1
         * afterSaleStatus : 0
         * shopSpecDetailed : {"id":12446,"commodityId":3828,"specIds":"6826,6829","specNames":"规格1、规格3","retailPrice":0.01,"marketPrice":100,"stock":102,"status":1,"commodityName":"测试商品","manufactor":"易康成","logo":"/upload/mavendemo/course/20190324/1553418373913514689.jpg","count":0,"buyNum":0}
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
        private int mailStatus;
        private int signStatus;
        private int afterSaleStatus;
        private ShopSpecDetailedBean shopSpecDetailed;

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

        public ShopSpecDetailedBean getShopSpecDetailed() {
            return shopSpecDetailed;
        }

        public void setShopSpecDetailed(ShopSpecDetailedBean shopSpecDetailed) {
            this.shopSpecDetailed = shopSpecDetailed;
        }

        public static class ShopSpecDetailedBean {
            @Override
            public String toString() {
                return "ShopSpecDetailedBean{" +
                        "id=" + id +
                        ", commodityId=" + commodityId +
                        ", specIds='" + specIds + '\'' +
                        ", specNames='" + specNames + '\'' +
                        ", retailPrice=" + retailPrice +
                        ", marketPrice=" + marketPrice +
                        ", stock=" + stock +
                        ", status=" + status +
                        ", commodityName='" + commodityName + '\'' +
                        ", manufactor='" + manufactor + '\'' +
                        ", logo='" + logo + '\'' +
                        ", count=" + count +
                        ", buyNum=" + buyNum +
                        '}';
            }

            /**
             * id : 12446
             * commodityId : 3828
             * specIds : 6826,6829
             * specNames : 规格1、规格3
             * retailPrice : 0.01
             * marketPrice : 100
             * stock : 102
             * status : 1
             * commodityName : 测试商品
             * manufactor : 易康成
             * logo : /upload/mavendemo/course/20190324/1553418373913514689.jpg
             * count : 0
             * buyNum : 0
             */

            private int id;
            private int commodityId;
            private String specIds;
            private String specNames;
            private double retailPrice;
            private int marketPrice;
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

            public int getMarketPrice() {
                return marketPrice;
            }

            public void setMarketPrice(int marketPrice) {
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

