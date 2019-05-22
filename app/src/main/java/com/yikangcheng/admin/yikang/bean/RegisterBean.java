package com.yikangcheng.admin.yikang.bean;

/**
 * 作者：古祥坤 on 2019/5/22 16:10
 * 邮箱：1724959985@qq.com
 */
public class RegisterBean {
    /**
     * id : 85
     * nickname :
     * emailIsavalible : 0
     * mobile : 18888888888
     * mobileIsavalible : 0
     * isavalible : 0
     * createdate : 2019-05-21 19:22:55
     * userip : 192.168.0.134
     * userType : 1
     * registerFrom : appFrom
     * expenses : 0
     * bounsPoints : 0
     * isCreate : 0
     */

    private int id;
    private String nickname;
    private int emailIsavalible;
    private String mobile;
    private int mobileIsavalible;
    private int isavalible;
    private String createdate;
    private String userip;
    private int userType;
    private String registerFrom;
    private int expenses;
    private int bounsPoints;
    private int isCreate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getEmailIsavalible() {
        return emailIsavalible;
    }

    public void setEmailIsavalible(int emailIsavalible) {
        this.emailIsavalible = emailIsavalible;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getMobileIsavalible() {
        return mobileIsavalible;
    }

    public void setMobileIsavalible(int mobileIsavalible) {
        this.mobileIsavalible = mobileIsavalible;
    }

    public int getIsavalible() {
        return isavalible;
    }

    public void setIsavalible(int isavalible) {
        this.isavalible = isavalible;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getUserip() {
        return userip;
    }

    public void setUserip(String userip) {
        this.userip = userip;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getRegisterFrom() {
        return registerFrom;
    }

    public void setRegisterFrom(String registerFrom) {
        this.registerFrom = registerFrom;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    public int getBounsPoints() {
        return bounsPoints;
    }

    public void setBounsPoints(int bounsPoints) {
        this.bounsPoints = bounsPoints;
    }

    public int getIsCreate() {
        return isCreate;
    }

    public void setIsCreate(int isCreate) {
        this.isCreate = isCreate;
    }
}
