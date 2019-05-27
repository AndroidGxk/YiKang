package com.yikangcheng.admin.yikang.bean;

import java.util.List;

/**
 * 作者：古祥坤 on 2019/5/25 17:25
 * 邮箱：1724959985@qq.com
 */
public class AllAddressBean {
    private List<ListUserAddressBean> listUserAddress;

    public List<ListUserAddressBean> getListUserAddress() {
        return listUserAddress;
    }

    public void setListUserAddress(List<ListUserAddressBean> listUserAddress) {
        this.listUserAddress = listUserAddress;
    }

    public static class ListUserAddressBean {
        @Override
        public String toString() {
            return "ListUserAddressBean{" +
                    "id=" + id +
                    ", userId=" + userId +
                    ", receiver='" + receiver + '\'' +
                    ", address='" + address + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", isFirst=" + isFirst +
                    ", sendTime=" + sendTime +
                    ", createTime='" + createTime + '\'' +
                    ", provinceId=" + provinceId +
                    ", cityId=" + cityId +
                    ", townId=" + townId +
                    ", provinceStr='" + provinceStr + '\'' +
                    ", cityStr='" + cityStr + '\'' +
                    ", townStr='" + townStr + '\'' +
                    '}';
        }

        /**
         * id : 58
         * userId : 92
         * receiver : 古彤
         * address : 1
         * mobile : 13366247385
         * isFirst : 2
         * sendTime : 0
         * createTime : 2019-05-25 15:54:44
         * provinceId : 1
         * cityId : 1
         * townId : 1
         * provinceStr : 中国
         * cityStr : 中国
         * townStr : 中国
         */

        private int id;
        private int userId;
        private String receiver;
        private String address;
        private String mobile;
        private int isFirst;
        private int sendTime;
        private String createTime;
        private int provinceId;
        private int cityId;
        private int townId;
        private String provinceStr;
        private String cityStr;
        private String townStr;

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

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getIsFirst() {
            return isFirst;
        }

        public void setIsFirst(int isFirst) {
            this.isFirst = isFirst;
        }

        public int getSendTime() {
            return sendTime;
        }

        public void setSendTime(int sendTime) {
            this.sendTime = sendTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public int getTownId() {
            return townId;
        }

        public void setTownId(int townId) {
            this.townId = townId;
        }

        public String getProvinceStr() {
            return provinceStr;
        }

        public void setProvinceStr(String provinceStr) {
            this.provinceStr = provinceStr;
        }

        public String getCityStr() {
            return cityStr;
        }

        public void setCityStr(String cityStr) {
            this.cityStr = cityStr;
        }

        public String getTownStr() {
            return townStr;
        }

        public void setTownStr(String townStr) {
            this.townStr = townStr;
        }
    }
}
