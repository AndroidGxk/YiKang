package com.yikangcheng.admin.yikang.bean;

/**
 * Created by lenovo on 2019/5/22.
 * WF
 */

/**
 * 这是我的页面 广告贴实体类
 */
public class AdvertisingBean {
    @Override
    public String toString() {
        return "AdvertisingBean{" +
                "id=" + id +
                ", imagesUrl='" + imagesUrl + '\'' +
                ", title='" + title + '\'' +
                ", keyWord='" + keyWord + '\'' +
                ", seriesNumber=" + seriesNumber +
                ", color='" + color + '\'' +
                ", previewUrl='" + previewUrl + '\'' +
                '}';
    }

    /**
     * id : 14
     * imagesUrl : http://pic40.nipic.com/20140412/18428321_144447597175_2.jpg
     * title : APP个人中心广告图
     * keyWord : img
     * seriesNumber : 1
     * color :
     * previewUrl :
     */

    private int id;
    private String imagesUrl;
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
