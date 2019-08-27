package com.yikangcheng.admin.yikang.bean;

import java.util.List;

/**
 * 作者：古祥坤 on 2019/5/26 16:14
 * 邮箱：1724959985@qq.com
 */
public class GoodComBean {
    /**
     * courseAssessList : [{"id":6,"userId":5,"commodityId":29709,"orderId":2411,"orderNo":"NO15640242111655","orderDetailsId":2974,"detailedId":80503,"content":"很好，很满意","status":0,"starClass":5,"image1":"","commodityName":"资生堂 珊珂 绵润白泥泡沫洁面乳 120g","logo":"/upload/mavendemo/course/20190723/1563864564823961012.jpg","nickName":"001","avatar":"/upload/yizhilu/common/20190730/1564448993044708878.jpg","mobile":"135****2856"}]
     * page : {"totalResultSize":1,"totalPageSize":1,"pageSize":10,"currentPage":1,"startRow":0,"first":false,"last":false}
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
         * totalResultSize : 1
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
         * id : 6
         * userId : 5
         * commodityId : 29709
         * orderId : 2411
         * orderNo : NO15640242111655
         * orderDetailsId : 2974
         * detailedId : 80503
         * content : 很好，很满意
         * status : 0
         * starClass : 5
         * image1 :
         * commodityName : 资生堂 珊珂 绵润白泥泡沫洁面乳 120g
         * logo : /upload/mavendemo/course/20190723/1563864564823961012.jpg
         * nickName : 001
         * avatar : /upload/yizhilu/common/20190730/1564448993044708878.jpg
         * mobile : 135****2856
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
        private String nickName;
        private String avatar;
        private String mobile;
        private String createTime;
        private String specNames;

        public String getSpecNames() {
            return specNames;
        }

        public void setSpecNames(String specNames) {
            this.specNames = specNames;
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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
