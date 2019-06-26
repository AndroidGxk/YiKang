package com.yikangcheng.admin.yikang.bean;

/**
 * Created by lenovo on 2019/5/22.
 * WF
 */

import java.util.List;

/**
 * 这是为你推荐页面实体类
 */
public class RecommendBean {


    @Override
    public String toString() {
        return "EntityBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isPay=" + isPay +
                ", sourceprice=" + sourceprice +
                ", currentprice=" + currentprice +
                ", title='" + title + '\'' +
                ", logo='" + logo + '\'' +
                ", losetype=" + losetype +
                ", freight=" + freight +
                '}';
    }

    /**
     * id : 3643
     * name : 全国出发-美国塞班岛旅游自由行多酒店可选
     * isPay : 0
     * sourceprice : 7999
     * currentprice : 5599
     * title :
     * logo : /upload/mavendemo/course/20190321/1553135022846242539.jpg
     * losetype : 0
     * freight : 0
     */

    private int id;
    private String name;
    private int isPay;
    private double sourceprice;
    private double currentprice;
    private String title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

