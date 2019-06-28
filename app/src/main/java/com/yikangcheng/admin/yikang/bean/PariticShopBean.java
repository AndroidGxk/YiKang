package com.yikangcheng.admin.yikang.bean;


/**
 * 作者：古祥坤 on 2019/5/18 15:17
 * 邮箱：1724959985@qq.com
 */
public class PariticShopBean  {
    private String id;
    private String dataType;
    private String buyNum;
    private String price;
    private String commodityName;
    private String logo;
    private String specNames;

    @Override
    public String toString() {
        return "PariticShopBean{" +
                "id='" + id + '\'' +
                ", dataType='" + dataType + '\'' +
                ", buyNum='" + buyNum + '\'' +
                ", price='" + price + '\'' +
                ", commodityName='" + commodityName + '\'' +
                ", logo='" + logo + '\'' +
                ", specNames='" + specNames + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(String buyNum) {
        this.buyNum = buyNum;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSpecNames() {
        return specNames;
    }

    public void setSpecNames(String specNames) {
        this.specNames = specNames;
    }
}
