package com.yikangcheng.admin.yikang.bean;

/**
 * 作者：古祥坤 on 2019/7/28 22:28
 * 邮箱：1724959985@qq.com
 */
public class ShouBannerBean {
    /**
     * id : 16
     * imagesUrl : /upload/mavendemo/bannerImages/20190728/1564308336875091796.png
     * mobileImagesUrl :
     * linkAddress : https://www.yikch.com/mobile/appShow/yousheng?type=ios
     * title : banner图
     * keyWord : appIndexBanner
     * seriesNumber : 0
     * color :
     * previewUrl :
     */

    private int id;
    private String imagesUrl;
    private String mobileImagesUrl;
    private String linkAddress;
    private String title;
    private String keyWord;
    private int seriesNumber;
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

    public int getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(int seriesNumber) {
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
