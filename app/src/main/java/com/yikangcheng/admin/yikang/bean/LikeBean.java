package com.yikangcheng.admin.yikang.bean;

import java.util.List;

/**
 * Created by lenovo on 2019/5/18.
 * WF
 */
public class LikeBean {

        /**
         * id : 3643
         * name : 全国出发-美国塞班岛旅游自由行多酒店可选
         * isPay : 0
         * sourceprice : 7999
         * currentprice : 5599
         * logo : /upload/mavendemo/course/20190321/1553135022846242539.jpg
         * losetype : 0
         * freight : 0
         */

        private int id;
        private String name;
        private int isPay;
        private int sourceprice;
        private int currentprice;
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

        public int getSourceprice() {
            return sourceprice;
        }

        public void setSourceprice(int sourceprice) {
            this.sourceprice = sourceprice;
        }

        public int getCurrentprice() {
            return currentprice;
        }

        public void setCurrentprice(int currentprice) {
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

