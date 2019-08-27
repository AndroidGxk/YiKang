package com.yikangcheng.admin.yikang.bean;

/**
 * 作者：古祥坤 on 2019/8/19 15:56
 * 邮箱：1724959985@qq.com
 */

/**
 * 千企千面聚合首页板块数据
 */
public class SectionImageBean {
    /**
     * id : 18
     * imagesUrl : /upload/mavendemo/bannerImages/20190822/1566453189223160750.png
     * ioslinkAddress : https://www.yikch.com/mobile/appShow/praisePurchase?type=ios
     * linkAddress : https://www.yikch.com/mobile/appShow/praisePurchase?type=android
     * imagesTitle : 评论返现购
     * images_Sequence : 8
     * images_Start : 1
     * merChantId : 0
     */

    private int id;
    private String imagesUrl;
    private String ioslinkAddress;
    private String linkAddress;
    private String imagesTitle;
    private int images_Sequence;
    private int images_Start;
    private String merChantId;

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

    public String getIoslinkAddress() {
        return ioslinkAddress;
    }

    public void setIoslinkAddress(String ioslinkAddress) {
        this.ioslinkAddress = ioslinkAddress;
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

    public String getImagesTitle() {
        return imagesTitle;
    }

    public void setImagesTitle(String imagesTitle) {
        this.imagesTitle = imagesTitle;
    }

    public int getImages_Sequence() {
        return images_Sequence;
    }

    public void setImages_Sequence(int images_Sequence) {
        this.images_Sequence = images_Sequence;
    }

    public int getImages_Start() {
        return images_Start;
    }

    public void setImages_Start(int images_Start) {
        this.images_Start = images_Start;
    }

    public String getMerChantId() {
        return merChantId;
    }

    public void setMerChantId(String merChantId) {
        this.merChantId = merChantId;
    }
}
