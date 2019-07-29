package com.yikangcheng.admin.yikang.bean;

import java.util.List;

/**
 * 每日推荐页面实体类
 */
public class RecommendBean {

    private List<CommodityListBean> commodityList;

    public List<CommodityListBean> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<CommodityListBean> commodityList) {
        this.commodityList = commodityList;
    }

    public static class CommodityListBean {
        /**
         * id : 20663
         * name : 韩国原产SCANDINA成人儿童两用马桶盖坐便器盖子母马桶盖1269g
         * isPay : 0
         * sourceprice : 306
         * currentprice : 289
         * logo : http://images.houniao.hk/upload/goods/20190222173127899_big.jpeg
         * losetype : 0
         * freight : 0
         */

        private int id;
        private String name;
        private int isPay;
        private double sourceprice;
        private double currentprice;
        private String logo;
        private int losetype;
        private int freight;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIsPay() {
            return isPay;
        }

        public void setIsPay(int isPay) {
            this.isPay = isPay;
        }

        public double getSourceprice() {
            return sourceprice;
        }

        public void setSourceprice(double sourceprice) {
            this.sourceprice = sourceprice;
        }

        public double getCurrentprice() {
            return currentprice;
        }

        public void setCurrentprice(double currentprice) {
            this.currentprice = currentprice;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getLosetype() {
            return losetype;
        }

        public void setLosetype(int losetype) {
            this.losetype = losetype;
        }

        public int getFreight() {
            return freight;
        }

        public void setFreight(int freight) {
            this.freight = freight;
        }
    }
}

