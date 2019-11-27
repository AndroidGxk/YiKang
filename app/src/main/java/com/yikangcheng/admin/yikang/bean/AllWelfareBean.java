package com.yikangcheng.admin.yikang.bean;

import java.util.List;

/**
 * 作者：古祥坤 on 2019/9/5 18:26
 * 邮箱：1724959985@qq.com
 */
public class AllWelfareBean {

    /**
     * id : 44
     * orderNumber : NO1567678272593yikang
     * addTime : 2019-09-05 18:11:13
     * content : 福利类型 ：节日礼2人,2件商品,0张福利券
     * personNum : 2
     * enterId : 1
     * type : 0
     * getperson : 0
     * received : 0
     * welfareStatus : 0
     * welfareDetailsList : [{"id":90,"userId":92,"welfareState":1,"welfarefetchTime":"2019-09-05","welfareId":"NO1567678272593yikang","age":0,"type":0,"cursename":" Adidas阿迪达斯 女子训练运动七分裤","curselogo":"http://img.seatent.com/statics/99cea281725792cfefc2737e7f5ecb3d/attachment/upload/image/20190626/45a7852b361446f6af2d12a6f2385b04_big.jpg"},{"id":90,"userId":92,"welfareState":1,"welfarefetchTime":"2019-09-05","welfareId":"NO1567678272593yikang","age":0,"type":0,"couponname":"测试福利券"},{"id":90,"userId":92,"welfareState":1,"welfarefetchTime":"2019-09-05","welfareId":"NO1567678272593yikang","age":0,"type":0,"cursename":" Airpods无线蓝牙耳机手机平板电脑运动入耳式","curselogo":"https://img.seatent.com/statics/99cea281725792cfefc2737e7f5ecb3d/attachment/upload/image/20190626/45a7852b361446f6af2d12a6f2385b04_big.jpg"}]
     * totalPrice : 2416
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
    private String welfarefetchTime;
    private List<WelfareDetailsListBean> welfareDetailsList;

    public String getWelfarefetchTime() {
        return welfarefetchTime;
    }

    public void setWelfarefetchTime(String welfarefetchTime) {
        this.welfarefetchTime = welfarefetchTime;
    }

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
         * id : 90
         * userId : 92
         * welfareState : 1
         * welfarefetchTime : 2019-09-05
         * welfareId : NO1567678272593yikang
         * age : 0
         * type : 0
         * cursename :  Adidas阿迪达斯 女子训练运动七分裤
         * curselogo : http://img.seatent.com/statics/99cea281725792cfefc2737e7f5ecb3d/attachment/upload/image/20190626/45a7852b361446f6af2d12a6f2385b04_big.jpg
         * couponname : 测试福利券
         */

        private int id;
        private int userId;
        private int welfareState;
        private String welfarefetchTime;
        private String welfareId;
        private int age;
        private int type;
        private String cursename;
        private String curselogo;
        private String couponname;

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

        public String getWelfarefetchTime() {
            return welfarefetchTime;
        }

        public void setWelfarefetchTime(String welfarefetchTime) {
            this.welfarefetchTime = welfarefetchTime;
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

        public String getCouponname() {
            return couponname;
        }

        public void setCouponname(String couponname) {
            this.couponname = couponname;
        }
    }
}
