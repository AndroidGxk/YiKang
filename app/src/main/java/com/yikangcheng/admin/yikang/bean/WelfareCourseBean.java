package com.yikangcheng.admin.yikang.bean;

import java.util.List;

/**
 * 作者：古祥坤 on 2019/9/5 15:28
 * 邮箱：1724959985@qq.com
 * 用户礼品弹框
 */
public class WelfareCourseBean {
    /**
     * id : 1
     * orderNumber : 单号
     * addTime : 2019-08-23 00:00:00
     * content : 一人一城
     * personNum : 2
     * enterId : 5
     * type : 0
     * getperson : 0
     * received : 1
     * welfareStatus : 0
     * welfareDetailsList : [{"id":4,"userId":92,"welfareState":1,"welfareId":"单号","age":0,"type":0,"cursename":"111","curselogo":"/upload/mavendemo/course/20190304/1551667592715856712.jpg"},{"id":4,"userId":92,"welfareState":1,"welfareId":"单号","age":0,"type":0,"cursename":"恒大冰泉低钠 长白山饮用水500ml*24","curselogo":"/upload/mavendemo/course/20190418/1555572036206227085.jpg"}]
     * totalPrice : 100
     */

    private int id;
    private String orderNumber;
    private String addTime;
    private String content;
    private int personNum;
    private int enterId;
    private int type;
    private int getperson;
    private int received;
    private int welfareStatus;
    private int totalPrice;
    private List<WelfareDetailsListBean> welfareDetailsList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPersonNum() {
        return personNum;
    }

    public void setPersonNum(int personNum) {
        this.personNum = personNum;
    }

    public int getEnterId() {
        return enterId;
    }

    public void setEnterId(int enterId) {
        this.enterId = enterId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGetperson() {
        return getperson;
    }

    public void setGetperson(int getperson) {
        this.getperson = getperson;
    }

    public int getReceived() {
        return received;
    }

    public void setReceived(int received) {
        this.received = received;
    }

    public int getWelfareStatus() {
        return welfareStatus;
    }

    public void setWelfareStatus(int welfareStatus) {
        this.welfareStatus = welfareStatus;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<WelfareDetailsListBean> getWelfareDetailsList() {
        return welfareDetailsList;
    }

    public void setWelfareDetailsList(List<WelfareDetailsListBean> welfareDetailsList) {
        this.welfareDetailsList = welfareDetailsList;
    }

    public static class WelfareDetailsListBean {
        /**
         * id : 4
         * userId : 92
         * welfareState : 1
         * welfareId : 单号
         * age : 0
         * type : 0
         * cursename : 111
         * curselogo : /upload/mavendemo/course/20190304/1551667592715856712.jpg
         */

        private int id;
        private int userId;
        private int welfareState;
        private String welfareId;
        private int age;
        private int type;
        private String cursename;
        private String curselogo;
        private String couponname;

        public String getCouponname() {
            return couponname;
        }

        public void setCouponname(String couponname) {
            this.couponname = couponname;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWelfareState() {
            return welfareState;
        }

        public void setWelfareState(int welfareState) {
            this.welfareState = welfareState;
        }

        public String getWelfareId() {
            return welfareId;
        }

        public void setWelfareId(String welfareId) {
            this.welfareId = welfareId;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCursename() {
            return cursename;
        }

        public void setCursename(String cursename) {
            this.cursename = cursename;
        }

        public String getCurselogo() {
            return curselogo;
        }

        public void setCurselogo(String curselogo) {
            this.curselogo = curselogo;
        }
    }
}
