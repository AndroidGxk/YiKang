package com.yikangcheng.admin.yikang.bean;

/**
 * 作者：古祥坤 on 2019/7/28 21:15
 * 邮箱：1724959985@qq.com
 * 启动页广告
 */
public class AdvertBean {
    /**
     * id : 15
     * imagesUrl : /upload/mavendemo/bannerImages/20190728/1564315019574290787.png
     * mobileImagesUrl :
     * linkAddress : /
     * title : 广告图
     * keyWord : startUpBanner
     * seriesNumber : 0
     * color : #FFFFFF
     * previewUrl :
     */

    private int id;
    private String imagesUrl;
    private String mobileImagesUrl;
    private String linkAddress;
    private String title;
    private String keyWord;
    private double seriesNumber;
    private String color;
    private String previewUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public String getMobileImagesUrl() {
        return mobileImagesUrl;
    }

    public void setMobileImagesUrl(String mobileImagesUrl) {
        this.mobileImagesUrl = mobileImagesUrl;
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public double getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(double seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }
}
