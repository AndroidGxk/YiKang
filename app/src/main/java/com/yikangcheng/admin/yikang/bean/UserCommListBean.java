package com.yikangcheng.admin.yikang.bean;

/**
 * 作者：古祥坤 on 2019/8/19 15:56
 * 邮箱：1724959985@qq.com
 */

import java.util.List;

/**
 * 用户评论列表数据
 */
public class UserCommListBean {

    /**
     * courseAssessList : [{"id":7,"userId":92,"commodityId":5345,"orderId":2304,"orderNo":"NO156389398651092","orderDetailsId":2855,"detailedId":18239,"content":"很好","status":0,"starClass":5,"image1":"https://static.yikch.com/upload/yizhilu/common/20190820/1566291070651595650.jpg","image2":"https://static.yikch.com/upload/yizhilu/common/20190820/1566291080750040416.jpg","image3":"https://static.yikch.com/upload/yizhilu/common/20190820/1566291087456708090.jpg","commodityName":"美妆测试008","logo":"/upload/mavendemo/course/20190415/1555299397176935540.JPG"},{"id":2,"userId":92,"commodityId":7683,"orderId":2409,"orderNo":"NO156402405317892","orderDetailsId":2972,"detailedId":23924,"content":"今天刚收到的东西，就迫不及待的打开戴上了，整体感觉很好，样子非常美观，款式非常新颖，还可随时检测心率，很好。","status":0,"starClass":5,"commodityName":"恩谷蓝牙运动手环 智能来电 久坐提醒 LED显示屏","logo":"/upload/mavendemo/course/20190530/1559194710872150720.jpg"}]
     * page : {"totalResultSize":2,"totalPageSize":1,"pageSize":10,"currentPage":1,"startRow":0,"first":false,"last":false}
     */

    private PageBean page;
    private List<CourseAssessListBean> courseAssessList;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<CourseAssessListBean> getCourseAssessList() {
        return courseAssessList;
    }

    public void setCourseAssessList(List<CourseAssessListBean> courseAssessList) {
        this.courseAssessList = courseAssessList;
    }

    public static class PageBean {
        /**
         * totalResultSize : 2
         * totalPageSize : 1
         * pageSize : 10
         * currentPage : 1
         * startRow : 0
         * first : false
         * last : false
         */

        private int totalResultSize;
        private int totalPageSize;
        private int pageSize;
        private int currentPage;
        private int startRow;
        private boolean first;
        private boolean last;

        public int getTotalResultSize() {
            return totalResultSize;
        }

        public void setTotalResultSize(int totalResultSize) {
            this.totalResultSize = totalResultSize;
        }

        public int getTotalPageSize() {
            return totalPageSize;
        }

        public void setTotalPageSize(int totalPageSize) {
            this.totalPageSize = totalPageSize;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }
    }

    public static class CourseAssessListBean {
        /**
         * id : 7
         * userId : 92
         * commodityId : 5345
         * orderId : 2304
         * orderNo : NO156389398651092
         * orderDetailsId : 2855
         * detailedId : 18239
         * content : 很好
         * status : 0
         * starClass : 5
         * image1 : https://static.yikch.com/upload/yizhilu/common/20190820/1566291070651595650.jpg
         * image2 : https://static.yikch.com/upload/yizhilu/common/20190820/1566291080750040416.jpg
         * image3 : https://static.yikch.com/upload/yizhilu/common/20190820/1566291087456708090.jpg
         * commodityName : 美妆测试008
         * logo : /upload/mavendemo/course/20190415/1555299397176935540.JPG
         */

        private int id;
        private int userId;
        private int commodityId;
        private int orderId;
        private String orderNo;
        private int orderDetailsId;
        private int detailedId;
        private String content;
        private int status;
        private int starClass;
        private String image1;
        private String image2;
        private String image3;
        private String commodityName;
        private String logo;

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

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getOrderDetailsId() {
            return orderDetailsId;
        }

        public void setOrderDetailsId(int orderDetailsId) {
            this.orderDetailsId = orderDetailsId;
        }

        public int getDetailedId() {
            return detailedId;
        }

        public void setDetailedId(int detailedId) {
            this.detailedId = detailedId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getStarClass() {
            return starClass;
        }

        public void setStarClass(int starClass) {
            this.starClass = starClass;
        }

        public String getImage1() {
            return image1;
        }

        public void setImage1(String image1) {
            this.image1 = image1;
        }

        public String getImage2() {
            return image2;
        }

        public void setImage2(String image2) {
            this.image2 = image2;
        }

        public String getImage3() {
            return image3;
        }

        public void setImage3(String image3) {
            this.image3 = image3;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }
    }
}
