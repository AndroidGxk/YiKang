package com.yikangcheng.admin.yikang.bean;

/**
 * 作者：古祥坤 on 2019/6/21 17:21
 * 邮箱：1724959985@qq.com
 */
public class AppUpdateBean {

    /**
     * id : 1
     * kType : android
     * downloadUrl : https://www.yikch.com/android/yikang.apk
     * versionNo : 1.0.0
     * depict : 最新改版，修复各种bug
     */

    private int id;
    private String kType;
    private String downloadUrl;
    private String versionNo;
    private String depict;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKType() {
        return kType;
    }

    public void setKType(String kType) {
        this.kType = kType;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }
}
