package com.yikangcheng.admin.yikang.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者：古祥坤 on 2019/5/22 16:34
 * 邮箱：1724959985@qq.com
 */
@Entity
public class LoginBean {
    /**
     * lastLoginTime : 2019-05-21 19:39:04
     * nickname : 18888888866
     * memTime : 1558438744442
     * id : 85
     */
    @Id
    private long uid;
    private int status;
    private String lastLoginTime;
    private String nickname;
    private String memTime;
    private String themeColors;
    private int id;
    private int enterId;
    private String mobile;
    private int isZeroPurchase;
    @Generated(hash = 2108933976)
    public LoginBean(long uid, int status, String lastLoginTime, String nickname,
            String memTime, String themeColors, int id, int enterId, String mobile,
            int isZeroPurchase) {
        this.uid = uid;
        this.status = status;
        this.lastLoginTime = lastLoginTime;
        this.nickname = nickname;
        this.memTime = memTime;
        this.themeColors = themeColors;
        this.id = id;
        this.enterId = enterId;
        this.mobile = mobile;
        this.isZeroPurchase = isZeroPurchase;
    }
    @Generated(hash = 1112702939)
    public LoginBean() {
    }
    public long getUid() {
        return this.uid;
    }
    public void setUid(long uid) {
        this.uid = uid;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getLastLoginTime() {
        return this.lastLoginTime;
    }
    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getMemTime() {
        return this.memTime;
    }
    public void setMemTime(String memTime) {
        this.memTime = memTime;
    }
    public String getThemeColors() {
        return this.themeColors;
    }
    public void setThemeColors(String themeColors) {
        this.themeColors = themeColors;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getEnterId() {
        return this.enterId;
    }
    public void setEnterId(int enterId) {
        this.enterId = enterId;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public int getIsZeroPurchase() {
        return this.isZeroPurchase;
    }
    public void setIsZeroPurchase(int isZeroPurchase) {
        this.isZeroPurchase = isZeroPurchase;
    }


}
