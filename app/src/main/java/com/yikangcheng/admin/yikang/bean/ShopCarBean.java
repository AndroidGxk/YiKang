package com.yikangcheng.admin.yikang.bean;

import java.util.List;

/**
 * 作者：古祥坤 on 2019/5/18 15:17
 * 邮箱：1724959985@qq.com
 */
public class ShopCarBean {

    /**
     * message : 查询成功
     * success : true
     * entity : [{"id":251,"dataId":15521,"commodityId":4955,"dataType":"COMMODITY","userId":11,"buyNum":4,"createTime":"2019-05-14 15:04:33","price":0,"shopSpecDetailed":{"id":15521,"commodityId":4955,"serialNo":"","specIds":"9550,9559","specNames":"白、256GB","retailPrice":6603,"marketPrice":7099,"stock":4,"status":1,"commodityName":"Apple iPhone XR 移动联通电信4G手机 双卡双待","manufactor":"北京亿兆云通科技有限公司","logo":"/upload/mavendemo/course/20190404/1554367071482551816.jpg","count":0,"buyNum":0}},{"id":252,"dataId":15581,"commodityId":4959,"dataType":"COMMODITY","userId":11,"buyNum":1,"createTime":"2019-05-14 14:01:52","price":0,"shopSpecDetailed":{"id":15581,"commodityId":4959,"serialNo":"","specIds":"9591,9595","specNames":"金、64GB","retailPrice":7495,"marketPrice":7599,"stock":4,"status":1,"commodityName":"Apple iPhone XS 移动联通电信4G手机","manufactor":"北京亿兆云通科技有限公司","logo":"/upload/mavendemo/course/20190408/1554690580463886012.jpg","count":0,"buyNum":0}},{"id":253,"dataId":17895,"commodityId":5354,"dataType":"COMMODITY","userId":11,"buyNum":1,"createTime":"2019-05-14 14:01:51","price":0,"shopSpecDetailed":{"id":17895,"commodityId":5354,"serialNo":"","specIds":"11474,11477","specNames":"8+128G、幻夜黑 ","retailPrice":3099,"marketPrice":3299,"stock":5,"status":1,"commodityName":" 荣耀v20全面屏AI全网通 双卡双待 手机","manufactor":"北京亿兆云通科技有限公司","logo":"/upload/mavendemo/course/20190429/1556526499898259230.jpg","count":0,"buyNum":0}},{"id":260,"dataId":12778,"commodityId":4128,"dataType":"COMMODITY","userId":11,"buyNum":1,"createTime":"2019-05-14 15:06:02","price":879,"shopSpecDetailed":{"id":12778,"commodityId":4128,"serialNo":"","specIds":"7179","specNames":"单机","retailPrice":879,"marketPrice":899,"stock":10,"status":1,"commodityName":"大疆 OSMO-M2 手持云台 灰色","manufactor":"北京华清世创商贸有限公司","logo":"/upload/mavendemo/course/20190327/1553672866830541869.jpg","count":0,"buyNum":0}},{"id":261,"dataId":13006,"commodityId":4355,"dataType":"COMMODITY","userId":11,"buyNum":2,"createTime":"2019-05-14 15:06:35","price":0,"shopSpecDetailed":{"id":13006,"commodityId":4355,"serialNo":"","specIds":"7450","specNames":"HERO7 White","retailPrice":1450,"marketPrice":1498,"stock":10,"status":1,"commodityName":"GoPro HERO7 White运动相机防水摄像机 vlog户外水下潜水1080P60视频摄像机 语音控制+坚固耐用","manufactor":"北京华清世创商贸有限公司 ","logo":"/upload/mavendemo/course/20190328/1553754987354628234.jpg","count":0,"buyNum":0}},{"id":262,"dataId":13010,"commodityId":4359,"dataType":"COMMODITY","userId":11,"buyNum":2,"createTime":"2019-05-14 15:07:30","price":0,"shopSpecDetailed":{"id":13010,"commodityId":4359,"serialNo":"","specIds":"7461","specNames":"HERO7 Black","retailPrice":3100,"marketPrice":3398,"stock":10,"status":1,"commodityName":"GoPro HERO7 Black黑色 运动相机摄像机vlog 4K户外水下潜水视频直播 摄像机 HyperSmooth坚固耐用+防水","manufactor":"北京华清世创商贸有限公司 ","logo":"/upload/mavendemo/course/20190328/1553756415963741642.jpg","count":0,"buyNum":0}}]
     */

    /**
     * id : 251
     * dataId : 15521
     * commodityId : 4955
     * dataType : COMMODITY
     * userId : 11
     * buyNum : 4
     * createTime : 2019-05-14 15:04:33
     * price : 0
     * shopSpecDetailed : {"id":15521,"commodityId":4955,"serialNo":"","specIds":"9550,9559","specNames":"白、256GB","retailPrice":6603,"marketPrice":7099,"stock":4,"status":1,"commodityName":"Apple iPhone XR 移动联通电信4G手机 双卡双待","manufactor":"北京亿兆云通科技有限公司","logo":"/upload/mavendemo/course/20190404/1554367071482551816.jpg","count":0,"buyNum":0}
     */

    private int id;
    private int dataId;
    private int commodityId;
    private String dataType;
    private int userId;
    private int buyNum;
    private String createTime;
    private int price;
    private ShopSpecDetailedBean shopSpecDetailed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ShopSpecDetailedBean getShopSpecDetailed() {
        return shopSpecDetailed;
    }

    public void setShopSpecDetailed(ShopSpecDetailedBean shopSpecDetailed) {
        this.shopSpecDetailed = shopSpecDetailed;
    }

    public static class ShopSpecDetailedBean {
        /**
         * id : 15521
         * commodityId : 4955
         * serialNo :
         * specIds : 9550,9559
         * specNames : 白、256GB
         * retailPrice : 6603
         * marketPrice : 7099
         * stock : 4
         * status : 1
         * commodityName : Apple iPhone XR 移动联通电信4G手机 双卡双待
         * manufactor : 北京亿兆云通科技有限公司
         * logo : /upload/mavendemo/course/20190404/1554367071482551816.jpg
         * count : 0
         * buyNum : 0
         */
        private int check;
        private int id;
        private int commodityId;
        private String serialNo;
        private String specIds;
        private String specNames;
        private int retailPrice;
        private int marketPrice;
        private int stock;
        private int status;
        private String commodityName;
        private String manufactor;
        private String logo;
        private int count;
        private int buyNum;

        public int getCheck() {
            return check;
        }

        public void setCheck(int check) {
            this.check = check;
        }

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

        public int getRetailPrice() {
            return retailPrice;
        }

        public void setRetailPrice(int retailPrice) {
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
