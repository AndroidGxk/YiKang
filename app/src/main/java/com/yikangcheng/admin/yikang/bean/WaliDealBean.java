package com.yikangcheng.admin.yikang.bean;

import java.util.List;

/**
 * Created by lenovo on 2019/5/27.
 * WF
 */
public class WaliDealBean {
    /**
     * userAddress : {"id":42,"userId":11,"receiver":"金烁","address":"中铁创业大厦A座604","mobile":"15210957177","isFirst":1,"sendTime":1,"createTime":"2019-04-19 10:44:53","provinceId":2,"cityId":33,"townId":384,"provinceStr":"北京市","cityStr":"北京辖区","townStr":"石景山区"}
     * dataList : [{"time":"2019-06-21 14:22:22","ftime":"2019-06-21 14:22:22","context":"【北京市】 北京石景山区金顶街公司 派件员 赵晓旭(17310784486)正在为您派送"},{"time":"2019-06-21 09:56:12","ftime":"2019-06-21 09:56:12","context":"【北京市】 已离开 北京分拨中心 发往 北京石景山区金顶街公司"},{"time":"2019-06-21 09:43:57","ftime":"2019-06-21 09:43:57","context":"【北京市】 已到达 北京分拨中心"},{"time":"2019-06-20 20:56:42","ftime":"2019-06-20 20:56:42","context":"【大连市】 已离开 辽宁大连分拨中心 发往 北京分拨中心"},{"time":"2019-06-20 20:52:41","ftime":"2019-06-20 20:52:41","context":"【大连市】 已离开 辽宁大连分拨中心 发往 北京网点包"},{"time":"2019-06-20 20:44:07","ftime":"2019-06-20 20:44:07","context":"【大连市】 已到达 辽宁大连分拨中心"},{"time":"2019-06-20 16:00:45","ftime":"2019-06-20 16:00:45","context":"【大连市】 辽宁大连甘井子区辛寨子公司 已揽收"}]
     * order : {"orderId":1727,"orderNo":"NO156099422285711","userId":11,"sumPrice":66.15,"yhPrice":0,"realPrice":66.15,"freightPrice":0,"createTime":"2019-06-20 09:30:23","payTime":"2019-06-20 09:30:38","payType":"ALIPAY","orderState":"SUCCESS","orderForm":"IOS","couponcodeId":0,"externalOrderNo":"2019062022001472660568858007","orderType":"PUBLIC_TYPE","afterSaleId":0,"sellTypeList":"#COMMODITY#","toPay":1}
     * haveCommodity : true
     * detailsList : [{"id":2023,"orderId":1727,"orderNo":"NO156099422285711","dataId":1240,"userId":11,"dataType":"COMMODITY","buyNum":1,"createTime":"2019-06-20 09:30:23","payTime":"2019-06-20 09:30:38","payState":"SUCCESS","price":12.6,"freight":2,"openTime":0,"mailName":"中通快递","mailKey":"zhongtong","mailCode":"75156910550427","dataList":[{"time":"2019-06-21 15:36:25","ftime":"2019-06-21 15:36:25","context":"【临沂市】 【临沂沂水】（0539-2222519、0539-2229818） 的 张明龙（18254965484） 已揽收"}],"mailStatus":2,"mailTime":"2019-06-20 14:01:42","signStatus":1,"afterSaleStatus":0,"shopSpecDetailed":{"id":1240,"commodityId":1912,"serialNo":"","specIds":"1416","specNames":"500克","retailPrice":12.6,"marketPrice":23,"stock":100,"status":1,"commodityName":"好大哥 奶酪味蒸蛋糕","manufactor":"沂水明松电子商务有限公司","logo":"/upload/mavendemo/course/20190316/1552707600871936637.jpg","count":0,"buyNum":0}},{"id":2024,"orderId":1727,"orderNo":"NO156099422285711","dataId":1631,"userId":11,"dataType":"COMMODITY","buyNum":1,"createTime":"2019-06-20 09:30:23","payTime":"2019-06-20 09:30:38","payState":"SUCCESS","price":22.05,"freight":2,"openTime":0,"mailName":"韵达快递","mailKey":"yunda","mailCode":"3968760568976","dataList":[{"time":"2019-06-21 14:22:22","ftime":"2019-06-21 14:22:22","context":"【北京市】 北京石景山区金顶街公司 派件员 赵晓旭(17310784486)正在为您派送"},{"time":"2019-06-21 09:56:12","ftime":"2019-06-21 09:56:12","context":"【北京市】 已离开 北京分拨中心 发往 北京石景山区金顶街公司"},{"time":"2019-06-21 09:43:57","ftime":"2019-06-21 09:43:57","context":"【北京市】 已到达 北京分拨中心"},{"time":"2019-06-20 20:56:42","ftime":"2019-06-20 20:56:42","context":"【大连市】 已离开 辽宁大连分拨中心 发往 北京分拨中心"},{"time":"2019-06-20 20:52:41","ftime":"2019-06-20 20:52:41","context":"【大连市】 已离开 辽宁大连分拨中心 发往 北京网点包"},{"time":"2019-06-20 20:44:07","ftime":"2019-06-20 20:44:07","context":"【大连市】 已到达 辽宁大连分拨中心"},{"time":"2019-06-20 16:00:45","ftime":"2019-06-20 16:00:45","context":"【大连市】 辽宁大连甘井子区辛寨子公司 已揽收"}],"mailStatus":2,"mailTime":"2019-06-20 14:35:15","signStatus":1,"afterSaleStatus":0,"shopSpecDetailed":{"id":1631,"commodityId":2112,"serialNo":"","specIds":"1791","specNames":"芝麻","retailPrice":22.05,"marketPrice":37.9,"stock":100,"status":1,"commodityName":"海狸先生夹心海苔*2","manufactor":"大连海朴生物科技有限公司","logo":"/upload/mavendemo/course/20190316/1552716387610928248.png","count":0,"buyNum":0}},{"id":2025,"orderId":1727,"orderNo":"NO156099422285711","dataId":18075,"userId":11,"dataType":"COMMODITY","buyNum":1,"createTime":"2019-06-20 09:30:23","payTime":"2019-06-20 09:30:38","payState":"SUCCESS","price":31.5,"freight":2,"openTime":0,"mailName":"韵达快递","mailKey":"yunda","mailCode":"3968760568976","dataList":[{"time":"2019-06-21 14:22:22","ftime":"2019-06-21 14:22:22","context":"【北京市】 北京石景山区金顶街公司 派件员 赵晓旭(17310784486)正在为您派送"},{"time":"2019-06-21 09:56:12","ftime":"2019-06-21 09:56:12","context":"【北京市】 已离开 北京分拨中心 发往 北京石景山区金顶街公司"},{"time":"2019-06-21 09:43:57","ftime":"2019-06-21 09:43:57","context":"【北京市】 已到达 北京分拨中心"},{"time":"2019-06-20 20:56:42","ftime":"2019-06-20 20:56:42","context":"【大连市】 已离开 辽宁大连分拨中心 发往 北京分拨中心"},{"time":"2019-06-20 20:52:41","ftime":"2019-06-20 20:52:41","context":"【大连市】 已离开 辽宁大连分拨中心 发往 北京网点包"},{"time":"2019-06-20 20:44:07","ftime":"2019-06-20 20:44:07","context":"【大连市】 已到达 辽宁大连分拨中心"},{"time":"2019-06-20 16:00:45","ftime":"2019-06-20 16:00:45","context":"【大连市】 辽宁大连甘井子区辛寨子公司 已揽收"}],"mailStatus":2,"mailTime":"2019-06-20 14:35:19","signStatus":2,"signTime":"2019-06-21 16:06:17","afterSaleStatus":0,"shopSpecDetailed":{"id":18075,"commodityId":2164,"serialNo":"","specIds":"11579","specNames":"香辣鳗鱼丝","retailPrice":31.5,"marketPrice":47.8,"stock":100,"status":1,"commodityName":"海狸先生鳗鱼丝*2","manufactor":"大连海朴生物科技有限公司","logo":"/upload/mavendemo/course/20190316/1552718165433141280.jpg","count":0,"buyNum":0}}]
     * orderBook : {"id":1706,"orderId":1727,"orderNo":"NO156099422285711","userId":11,"addressId":42,"shippingType":1,"telConfirm":1,"receiver":"金烁","mobile":"15210957177","address":"北京市 北京辖区 石景山区 中铁创业大厦A座604","invoiceType":1,"invoiceFrom":0,"invoiceName":"0","invoiceNo":"","invoiceContent":1,"invoiceEmail":"0"}
     */

    private UserAddressBean userAddress;
    private OrderBean order;
    private boolean haveCommodity;
    private OrderBookBean orderBook;
    private List<DataListBean> dataList;
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

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public List<DetailsListBean> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<DetailsListBean> detailsList) {
        this.detailsList = detailsList;
    }

    public static class UserAddressBean {
        /**
         * id : 42
         * userId : 11
         * receiver : 金烁
         * address : 中铁创业大厦A座604
         * mobile : 15210957177
         * isFirst : 1
         * sendTime : 1
         * createTime : 2019-04-19 10:44:53
         * provinceId : 2
         * cityId : 33
         * townId : 384
         * provinceStr : 北京市
         * cityStr : 北京辖区
         * townStr : 石景山区
         */

        private int id;
        private int userId;
        private String receiver;
        private String address;
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
        /**
         * orderId : 1727
         * orderNo : NO156099422285711
         * userId : 11
         * sumPrice : 66.15
         * yhPrice : 0
         * realPrice : 66.15
         * freightPrice : 0
         * createTime : 2019-06-20 09:30:23
         * payTime : 2019-06-20 09:30:38
         * payType : ALIPAY
         * orderState : SUCCESS
         * orderForm : IOS
         * couponcodeId : 0
         * externalOrderNo : 2019062022001472660568858007
         * orderType : PUBLIC_TYPE
         * afterSaleId : 0
         * sellTypeList : #COMMODITY#
         * toPay : 1
         */

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
    }

    public static class OrderBookBean {
        /**
         * id : 1706
         * orderId : 1727
         * orderNo : NO156099422285711
         * userId : 11
         * addressId : 42
         * shippingType : 1
         * telConfirm : 1
         * receiver : 金烁
         * mobile : 15210957177
         * address : 北京市 北京辖区 石景山区 中铁创业大厦A座604
         * invoiceType : 1
         * invoiceFrom : 0
         * invoiceName : 0
         * invoiceNo :
         * invoiceContent : 1
         * invoiceEmail : 0
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

    public static class DataListBean {
        /**
         * time : 2019-06-21 14:22:22
         * ftime : 2019-06-21 14:22:22
         * context : 【北京市】 北京石景山区金顶街公司 派件员 赵晓旭(17310784486)正在为您派送
         */

        private String time;
        private String ftime;
        private String context;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getFtime() {
            return ftime;
        }

        public void setFtime(String ftime) {
            this.ftime = ftime;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }
    }

    public static class DetailsListBean {
        /**
         * id : 2023
         * orderId : 1727
         * orderNo : NO156099422285711
         * dataId : 1240
         * userId : 11
         * dataType : COMMODITY
         * buyNum : 1
         * createTime : 2019-06-20 09:30:23
         * payTime : 2019-06-20 09:30:38
         * payState : SUCCESS
         * price : 12.6
         * freight : 2
         * openTime : 0
         * mailName : 中通快递
         * mailKey : zhongtong
         * mailCode : 75156910550427
         * dataList : [{"time":"2019-06-21 15:36:25","ftime":"2019-06-21 15:36:25","context":"【临沂市】 【临沂沂水】（0539-2222519、0539-2229818） 的 张明龙（18254965484） 已揽收"}]
         * mailStatus : 2
         * mailTime : 2019-06-20 14:01:42
         * signStatus : 1
         * afterSaleStatus : 0
         * shopSpecDetailed : {"id":1240,"commodityId":1912,"serialNo":"","specIds":"1416","specNames":"500克","retailPrice":12.6,"marketPrice":23,"stock":100,"status":1,"commodityName":"好大哥 奶酪味蒸蛋糕","manufactor":"沂水明松电子商务有限公司","logo":"/upload/mavendemo/course/20190316/1552707600871936637.jpg","count":0,"buyNum":0}
         * signTime : 2019-06-21 16:06:17
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
        private ShopSpecDetailedBean shopSpecDetailed;
        private String signTime;
        private List<DataListBeanX> dataList;

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

        public ShopSpecDetailedBean getShopSpecDetailed() {
            return shopSpecDetailed;
        }

        public void setShopSpecDetailed(ShopSpecDetailedBean shopSpecDetailed) {
            this.shopSpecDetailed = shopSpecDetailed;
        }

        public String getSignTime() {
            return signTime;
        }

        public void setSignTime(String signTime) {
            this.signTime = signTime;
        }

        public List<DataListBeanX> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBeanX> dataList) {
            this.dataList = dataList;
        }

        public static class ShopSpecDetailedBean {
            /**
             * id : 1240
             * commodityId : 1912
             * serialNo :
             * specIds : 1416
             * specNames : 500克
             * retailPrice : 12.6
             * marketPrice : 23
             * stock : 100
             * status : 1
             * commodityName : 好大哥 奶酪味蒸蛋糕
             * manufactor : 沂水明松电子商务有限公司
             * logo : /upload/mavendemo/course/20190316/1552707600871936637.jpg
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

        public static class DataListBeanX {
            /**
             * time : 2019-06-21 15:36:25
             * ftime : 2019-06-21 15:36:25
             * context : 【临沂市】 【临沂沂水】（0539-2222519、0539-2229818） 的 张明龙（18254965484） 已揽收
             */

            private String time;
            private String ftime;
            private String context;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getFtime() {
                return ftime;
            }

            public void setFtime(String ftime) {
                this.ftime = ftime;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }
        }
    }
}
//    /**
//     * order : {"orderId":1605,"orderNo":"NO156040437138492","userId":92,"sumPrice":283,"yhPrice":0,"realPrice":283,"freightPrice":0,"createTime":"2019-06-13 13:39:31","payType":"ALIPAY","orderState":"INIT","orderForm":"Android","couponcodeId":0,"orderType":"PUBLIC_TYPE","afterSaleId":0,"sellTypeList":"#COMMODITY#","toPay":1}
//     * haveCommodity : true
//     * detailsList : [{"id":1778,"orderId":1605,"orderNo":"NO156040437138492","dataId":22660,"userId":92,"dataType":"COMMODITY","buyNum":1,"createTime":"2019-06-13 13:39:31","payState":"INIT","price":283,"freight":2,"openTime":0,"mailStatus":1,"signStatus":1,"afterSaleStatus":0,"shopSpecDetailed":{"id":22660,"commodityId":6970,"serialNo":"VS04K1-FW","specIds":"16291","specNames":"VS04K1-FW","retailPrice":283,"marketPrice":439,"stock":49,"status":1,"commodityName":"美的（Midea）吸尘器家用小型迷你强力吸尘无耗材静音除尘手持式吸尘器","manufactor":"杭州紫萱贸易有限公司","logo":"/upload/mavendemo/course/20190520/1558348771964897918.jpg","count":0,"buyNum":0}}]
//     * orderBook : {"id":1586,"orderId":1605,"orderNo":"NO156040437138492","userId":92,"addressId":0,"shippingType":0,"telConfirm":0,"receiver":"古祥坤","mobile":"13366247385","address":"北京市 北京辖区 昌平区 霍营 龙跃苑三区","invoiceType":0,"invoiceFrom":0,"invoiceContent":0}
//     */
//
//    private OrderBean order;
//    private boolean haveCommodity;
//    private OrderBookBean orderBook;
//    private List<DetailsListBean> detailsList;
//
//    public OrderBean getOrder() {
//        return order;
//    }
//
//    public void setOrder(OrderBean order) {
//        this.order = order;
//    }
//
//    public boolean isHaveCommodity() {
//        return haveCommodity;
//    }
//
//    public void setHaveCommodity(boolean haveCommodity) {
//        this.haveCommodity = haveCommodity;
//    }
//
//    public OrderBookBean getOrderBook() {
//        return orderBook;
//    }
//
//    public void setOrderBook(OrderBookBean orderBook) {
//        this.orderBook = orderBook;
//    }
//
//    public List<DetailsListBean> getDetailsList() {
//        return detailsList;
//    }
//
//    public void setDetailsList(List<DetailsListBean> detailsList) {
//        this.detailsList = detailsList;
//    }
//
//    public static class OrderBean {
//        /**
//         * orderId : 1605
//         * orderNo : NO156040437138492
//         * userId : 92
//         * sumPrice : 283
//         * yhPrice : 0
//         * realPrice : 283
//         * freightPrice : 0
//         * createTime : 2019-06-13 13:39:31
//         * payType : ALIPAY
//         * orderState : INIT
//         * orderForm : Android
//         * couponcodeId : 0
//         * orderType : PUBLIC_TYPE
//         * afterSaleId : 0
//         * sellTypeList : #COMMODITY#
//         * toPay : 1
//         */
//
//        private int orderId;
//        private String orderNo;
//        private int userId;
//        private double sumPrice;
//        private double yhPrice;
//        private double realPrice;
//        private double freightPrice;
//        private String createTime;
//        private String payType;
//        private String orderState;
//        private String orderForm;
//        private int couponcodeId;
//        private String orderType;
//        private int afterSaleId;
//        private String sellTypeList;
//        private int toPay;
//
//        public int getOrderId() {
//            return orderId;
//        }
//
//        public void setOrderId(int orderId) {
//            this.orderId = orderId;
//        }
//
//        public String getOrderNo() {
//            return orderNo;
//        }
//
//        public void setOrderNo(String orderNo) {
//            this.orderNo = orderNo;
//        }
//
//        public int getUserId() {
//            return userId;
//        }
//
//        public void setUserId(int userId) {
//            this.userId = userId;
//        }
//
//        public double getSumPrice() {
//            return sumPrice;
//        }
//
//        public void setSumPrice(double sumPrice) {
//            this.sumPrice = sumPrice;
//        }
//
//        public double getYhPrice() {
//            return yhPrice;
//        }
//
//        public void setYhPrice(double yhPrice) {
//            this.yhPrice = yhPrice;
//        }
//
//        public double getRealPrice() {
//            return realPrice;
//        }
//
//        public void setRealPrice(double realPrice) {
//            this.realPrice = realPrice;
//        }
//
//        public double getFreightPrice() {
//            return freightPrice;
//        }
//
//        public void setFreightPrice(double freightPrice) {
//            this.freightPrice = freightPrice;
//        }
//
//        public String getCreateTime() {
//            return createTime;
//        }
//
//        public void setCreateTime(String createTime) {
//            this.createTime = createTime;
//        }
//
//        public String getPayType() {
//            return payType;
//        }
//
//        public void setPayType(String payType) {
//            this.payType = payType;
//        }
//
//        public String getOrderState() {
//            return orderState;
//        }
//
//        public void setOrderState(String orderState) {
//            this.orderState = orderState;
//        }
//
//        public String getOrderForm() {
//            return orderForm;
//        }
//
//        public void setOrderForm(String orderForm) {
//            this.orderForm = orderForm;
//        }
//
//        public int getCouponcodeId() {
//            return couponcodeId;
//        }
//
//        public void setCouponcodeId(int couponcodeId) {
//            this.couponcodeId = couponcodeId;
//        }
//
//        public String getOrderType() {
//            return orderType;
//        }
//
//        public void setOrderType(String orderType) {
//            this.orderType = orderType;
//        }
//
//        public int getAfterSaleId() {
//            return afterSaleId;
//        }
//
//        public void setAfterSaleId(int afterSaleId) {
//            this.afterSaleId = afterSaleId;
//        }
//
//        public String getSellTypeList() {
//            return sellTypeList;
//        }
//
//        public void setSellTypeList(String sellTypeList) {
//            this.sellTypeList = sellTypeList;
//        }
//
//        public int getToPay() {
//            return toPay;
//        }
//
//        public void setToPay(int toPay) {
//            this.toPay = toPay;
//        }
//    }
//
//    public static class OrderBookBean {
//        /**
//         * id : 1586
//         * orderId : 1605
//         * orderNo : NO156040437138492
//         * userId : 92
//         * addressId : 0
//         * shippingType : 0
//         * telConfirm : 0
//         * receiver : 古祥坤
//         * mobile : 13366247385
//         * address : 北京市 北京辖区 昌平区 霍营 龙跃苑三区
//         * invoiceType : 0
//         * invoiceFrom : 0
//         * invoiceContent : 0
//         */
//
//        private int id;
//        private int orderId;
//        private String orderNo;
//        private int userId;
//        private int addressId;
//        private int shippingType;
//        private int telConfirm;
//        private String receiver;
//        private String mobile;
//        private String address;
//        private int invoiceType;
//        private int invoiceFrom;
//        private int invoiceContent;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public int getOrderId() {
//            return orderId;
//        }
//
//        public void setOrderId(int orderId) {
//            this.orderId = orderId;
//        }
//
//        public String getOrderNo() {
//            return orderNo;
//        }
//
//        public void setOrderNo(String orderNo) {
//            this.orderNo = orderNo;
//        }
//
//        public int getUserId() {
//            return userId;
//        }
//
//        public void setUserId(int userId) {
//            this.userId = userId;
//        }
//
//        public int getAddressId() {
//            return addressId;
//        }
//
//        public void setAddressId(int addressId) {
//            this.addressId = addressId;
//        }
//
//        public int getShippingType() {
//            return shippingType;
//        }
//
//        public void setShippingType(int shippingType) {
//            this.shippingType = shippingType;
//        }
//
//        public int getTelConfirm() {
//            return telConfirm;
//        }
//
//        public void setTelConfirm(int telConfirm) {
//            this.telConfirm = telConfirm;
//        }
//
//        public String getReceiver() {
//            return receiver;
//        }
//
//        public void setReceiver(String receiver) {
//            this.receiver = receiver;
//        }
//
//        public String getMobile() {
//            return mobile;
//        }
//
//        public void setMobile(String mobile) {
//            this.mobile = mobile;
//        }
//
//        public String getAddress() {
//            return address;
//        }
//
//        public void setAddress(String address) {
//            this.address = address;
//        }
//
//        public int getInvoiceType() {
//            return invoiceType;
//        }
//
//        public void setInvoiceType(int invoiceType) {
//            this.invoiceType = invoiceType;
//        }
//
//        public int getInvoiceFrom() {
//            return invoiceFrom;
//        }
//
//        public void setInvoiceFrom(int invoiceFrom) {
//            this.invoiceFrom = invoiceFrom;
//        }
//
//        public int getInvoiceContent() {
//            return invoiceContent;
//        }
//
//        public void setInvoiceContent(int invoiceContent) {
//            this.invoiceContent = invoiceContent;
//        }
//    }
//
//    public static class DetailsListBean {
//        /**
//         * id : 1778
//         * orderId : 1605
//         * orderNo : NO156040437138492
//         * dataId : 22660
//         * userId : 92
//         * dataType : COMMODITY
//         * buyNum : 1
//         * createTime : 2019-06-13 13:39:31
//         * payState : INIT
//         * price : 283
//         * freight : 2
//         * openTime : 0
//         * mailStatus : 1
//         * signStatus : 1
//         * afterSaleStatus : 0
//         * shopSpecDetailed : {"id":22660,"commodityId":6970,"serialNo":"VS04K1-FW","specIds":"16291","specNames":"VS04K1-FW","retailPrice":283,"marketPrice":439,"stock":49,"status":1,"commodityName":"美的（Midea）吸尘器家用小型迷你强力吸尘无耗材静音除尘手持式吸尘器","manufactor":"杭州紫萱贸易有限公司","logo":"/upload/mavendemo/course/20190520/1558348771964897918.jpg","count":0,"buyNum":0}
//         */
//
//        private int id;
//        private int orderId;
//        private String orderNo;
//        private int dataId;
//        private int userId;
//        private String dataType;
//        private int buyNum;
//        private String createTime;
//        private String payState;
//        private double price;
//        private int freight;
//        private int openTime;
//        private int mailStatus;
//        private int signStatus;
//        private int afterSaleStatus;
//        private ShopSpecDetailedBean shopSpecDetailed;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public int getOrderId() {
//            return orderId;
//        }
//
//        public void setOrderId(int orderId) {
//            this.orderId = orderId;
//        }
//
//        public String getOrderNo() {
//            return orderNo;
//        }
//
//        public void setOrderNo(String orderNo) {
//            this.orderNo = orderNo;
//        }
//
//        public int getDataId() {
//            return dataId;
//        }
//
//        public void setDataId(int dataId) {
//            this.dataId = dataId;
//        }
//
//        public int getUserId() {
//            return userId;
//        }
//
//        public void setUserId(int userId) {
//            this.userId = userId;
//        }
//
//        public String getDataType() {
//            return dataType;
//        }
//
//        public void setDataType(String dataType) {
//            this.dataType = dataType;
//        }
//
//        public int getBuyNum() {
//            return buyNum;
//        }
//
//        public void setBuyNum(int buyNum) {
//            this.buyNum = buyNum;
//        }
//
//        public String getCreateTime() {
//            return createTime;
//        }
//
//        public void setCreateTime(String createTime) {
//            this.createTime = createTime;
//        }
//
//        public String getPayState() {
//            return payState;
//        }
//
//        public void setPayState(String payState) {
//            this.payState = payState;
//        }
//
//        public double getPrice() {
//            return price;
//        }
//
//        public void setPrice(double price) {
//            this.price = price;
//        }
//
//        public int getFreight() {
//            return freight;
//        }
//
//        public void setFreight(int freight) {
//            this.freight = freight;
//        }
//
//        public int getOpenTime() {
//            return openTime;
//        }
//
//        public void setOpenTime(int openTime) {
//            this.openTime = openTime;
//        }
//
//        public int getMailStatus() {
//            return mailStatus;
//        }
//
//        public void setMailStatus(int mailStatus) {
//            this.mailStatus = mailStatus;
//        }
//
//        public int getSignStatus() {
//            return signStatus;
//        }
//
//        public void setSignStatus(int signStatus) {
//            this.signStatus = signStatus;
//        }
//
//        public int getAfterSaleStatus() {
//            return afterSaleStatus;
//        }
//
//        public void setAfterSaleStatus(int afterSaleStatus) {
//            this.afterSaleStatus = afterSaleStatus;
//        }
//
//        public ShopSpecDetailedBean getShopSpecDetailed() {
//            return shopSpecDetailed;
//        }
//
//        public void setShopSpecDetailed(ShopSpecDetailedBean shopSpecDetailed) {
//            this.shopSpecDetailed = shopSpecDetailed;
//        }
//
//        public static class ShopSpecDetailedBean {
//            /**
//             * id : 22660
//             * commodityId : 6970
//             * serialNo : VS04K1-FW
//             * specIds : 16291
//             * specNames : VS04K1-FW
//             * retailPrice : 283
//             * marketPrice : 439
//             * stock : 49
//             * status : 1
//             * commodityName : 美的（Midea）吸尘器家用小型迷你强力吸尘无耗材静音除尘手持式吸尘器
//             * manufactor : 杭州紫萱贸易有限公司
//             * logo : /upload/mavendemo/course/20190520/1558348771964897918.jpg
//             * count : 0
//             * buyNum : 0
//             */
//
//            private int id;
//            private int commodityId;
//            private String serialNo;
//            private String specIds;
//            private String specNames;
//            private double retailPrice;
//            private double marketPrice;
//            private int stock;
//            private int status;
//            private String commodityName;
//            private String manufactor;
//            private String logo;
//            private int count;
//            private int buyNum;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public int getCommodityId() {
//                return commodityId;
//            }
//
//            public void setCommodityId(int commodityId) {
//                this.commodityId = commodityId;
//            }
//
//            public String getSerialNo() {
//                return serialNo;
//            }
//
//            public void setSerialNo(String serialNo) {
//                this.serialNo = serialNo;
//            }
//
//            public String getSpecIds() {
//                return specIds;
//            }
//
//            public void setSpecIds(String specIds) {
//                this.specIds = specIds;
//            }
//
//            public String getSpecNames() {
//                return specNames;
//            }
//
//            public void setSpecNames(String specNames) {
//                this.specNames = specNames;
//            }
//
//            public double getRetailPrice() {
//                return retailPrice;
//            }
//
//            public void setRetailPrice(double retailPrice) {
//                this.retailPrice = retailPrice;
//            }
//
//            public double getMarketPrice() {
//                return marketPrice;
//            }
//
//            public void setMarketPrice(double marketPrice) {
//                this.marketPrice = marketPrice;
//            }
//
//            public int getStock() {
//                return stock;
//            }
//
//            public void setStock(int stock) {
//                this.stock = stock;
//            }
//
//            public int getStatus() {
//                return status;
//            }
//
//            public void setStatus(int status) {
//                this.status = status;
//            }
//
//            public String getCommodityName() {
//                return commodityName;
//            }
//
//            public void setCommodityName(String commodityName) {
//                this.commodityName = commodityName;
//            }
//
//            public String getManufactor() {
//                return manufactor;
//            }
//
//            public void setManufactor(String manufactor) {
//                this.manufactor = manufactor;
//            }
//
//            public String getLogo() {
//                return logo;
//            }
//
//            public void setLogo(String logo) {
//                this.logo = logo;
//            }
//
//            public int getCount() {
//                return count;
//            }
//
//            public void setCount(int count) {
//                this.count = count;
//            }
//
//            public int getBuyNum() {
//                return buyNum;
//            }
//
//            public void setBuyNum(int buyNum) {
//                this.buyNum = buyNum;
//            }
//        }
//    }


