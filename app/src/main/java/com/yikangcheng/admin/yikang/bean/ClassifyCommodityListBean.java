package com.yikangcheng.admin.yikang.bean;

import java.util.List;

/**
 * 作者：古祥坤 on 2019/5/24 16:10
 * 邮箱：1724959985@qq.com
 */
public class ClassifyCommodityListBean {
    /**
     * page : {"totalResultSize":7,"totalPageSize":1,"pageSize":10,"currentPage":1,"startRow":0,"first":false,"last":false}
     * commodityList : [{"buycount":0,"viewcount":0,"commentcount":0,"playcount":0,"remainDays":0,"endMin":0,"liveCount":0,"alreadyBuy":0,"id":6513,"name":"REMAX金属音乐通话耳机 RM-512","isavaliable":0,"addtime":"2019-05-11 11:36:21","isPay":0,"sourceprice":28,"currentprice":26,"title":"REMAX金属音乐通话3.5mm耳机 \r\nRM-512","logo":"/upload/mavendemo/course/20190511/1557545392868362797.jpg","losetype":0,"subjectName":"耳机","sort":0,"manufactor":"上海程备实业有限公司","brand":"REMAX","goodsNo":"RM-512","freight":0},{"buycount":0,"viewcount":0,"commentcount":0,"playcount":0,"remainDays":0,"endMin":0,"liveCount":0,"alreadyBuy":0,"id":6512,"name":"REMAX有线通话音乐耳机 RM-711","isavaliable":0,"addtime":"2019-05-11 11:23:08","isPay":0,"sourceprice":39,"currentprice":29,"title":"REMAX有线通话音乐耳机 \r\nRM-711","logo":"/upload/mavendemo/course/20190511/1557544558045455348.jpg","losetype":0,"subjectName":"耳机","sort":0,"manufactor":"上海程备实业有限公司","brand":"REMAX","goodsNo":"RM-711","freight":0},{"buycount":0,"viewcount":0,"commentcount":0,"playcount":0,"remainDays":0,"endMin":0,"liveCount":0,"alreadyBuy":0,"id":6510,"name":"REMAX运动有线通话耳机 RM-S15","isavaliable":0,"addtime":"2019-05-11 11:09:36","isPay":0,"sourceprice":89,"currentprice":66,"title":"REMAX运动有线高保真通话耳机\r\nRM-S15","logo":"/upload/mavendemo/course/20190511/1557543859726704484.jpg","losetype":0,"subjectName":"耳机","sort":0,"manufactor":"上海程备实业有限公司","brand":"REMAX","goodsNo":"RM-S15","freight":0},{"buycount":0,"viewcount":0,"commentcount":0,"playcount":0,"remainDays":0,"endMin":0,"liveCount":0,"alreadyBuy":0,"id":6506,"name":"REMAX 蓝牙颈戴运动休闲耳机 RB-S17","isavaliable":0,"addtime":"2019-05-11 10:30:33","isPay":0,"sourceprice":318,"currentprice":166,"title":"REMAX 蓝牙颈戴运动防水防汗设计休闲耳机 \r\nRB-S17","logo":"/upload/mavendemo/course/20190511/1557541558462590903.jpg","losetype":0,"subjectName":"耳机","sort":0,"manufactor":"上海程备实业有限公司","brand":"REMAX","goodsNo":"RB-S17","freight":0},{"buycount":0,"viewcount":0,"commentcount":0,"playcount":0,"remainDays":0,"endMin":0,"liveCount":0,"alreadyBuy":0,"id":6505,"name":"REMAX 运动蓝牙耳机 RB-S18","isavaliable":0,"addtime":"2019-05-11 10:23:40","isPay":0,"sourceprice":79,"currentprice":66,"title":"REMAX 运动蓝牙内置CVC音乐耳机 \r\nRB-S18","logo":"/upload/mavendemo/course/20190511/1557541021434901754.jpg","losetype":0,"subjectName":"耳机","sort":0,"manufactor":"上海程备实业有限公司","brand":"REMAX","goodsNo":"RB-S18","freight":0},{"buycount":0,"viewcount":0,"commentcount":0,"playcount":0,"remainDays":0,"endMin":0,"liveCount":0,"alreadyBuy":0,"id":6493,"name":"REMAX蓝牙V4.1三款撞色设计头戴式蓝牙耳机","isavaliable":0,"addtime":"2019-05-10 20:17:08","isPay":0,"sourceprice":699,"currentprice":288,"title":"REMAX蓝牙V4.1三款撞色设计头戴式蓝牙耳机\r\nRB-200HB","logo":"/upload/mavendemo/course/20190510/1557490529046318721.jpg","losetype":0,"subjectName":"耳机","sort":0,"manufactor":"上海程备实业有限公司","brand":"REMAX","goodsNo":"RB-200HB","freight":0},{"buycount":0,"viewcount":0,"commentcount":0,"playcount":0,"remainDays":0,"endMin":0,"liveCount":0,"alreadyBuy":0,"id":4488,"name":"JBL T190A 立体声入耳式耳机 ","isavaliable":0,"addtime":"2019-04-01 16:12:53","isPay":0,"sourceprice":199,"currentprice":190,"title":"8mm驱动单元2，立体音效，人工工学，防缠绕线缆，一键线控\r\n\r\n\r\n\r\n","logo":"/upload/mavendemo/course/20190401/1554106148961410902.jpg","losetype":0,"subjectName":"耳机","sort":1,"manufactor":"北京华清世创商贸有限公司 ","brand":"JBL","goodsNo":"","freight":0}]
     */

    private PageBean page;
    private List<CommodityListBean> commodityList;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<CommodityListBean> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<CommodityListBean> commodityList) {
        this.commodityList = commodityList;
    }

    public static class PageBean {
        /**
         * totalResultSize : 7
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

    public static class CommodityListBean {
        /**
         * buycount : 0
         * viewcount : 0
         * commentcount : 0
         * playcount : 0
         * remainDays : 0
         * endMin : 0
         * liveCount : 0
         * alreadyBuy : 0
         * id : 6513
         * name : REMAX金属音乐通话耳机 RM-512
         * isavaliable : 0
         * addtime : 2019-05-11 11:36:21
         * isPay : 0
         * sourceprice : 28
         * currentprice : 26
         * title : REMAX金属音乐通话3.5mm耳机
         * RM-512
         * logo : /upload/mavendemo/course/20190511/1557545392868362797.jpg
         * losetype : 0
         * subjectName : 耳机
         * sort : 0
         * manufactor : 上海程备实业有限公司
         * brand : REMAX
         * goodsNo : RM-512
         * freight : 0
         */

        private int buycount;
        private int viewcount;
        private int commentcount;
        private int playcount;
        private int remainDays;
        private int endMin;
        private int liveCount;
        private int alreadyBuy;
        private int id;
        private String name;
        private int isavaliable;
        private String addtime;
        private int isPay;
        private int sourceprice;
        private int currentprice;
        private String title;
        private String logo;
        private int losetype;
        private String subjectName;
        private int sort;
        private String manufactor;
        private String brand;
        private String goodsNo;
        private int freight;

        public int getBuycount() {
            return buycount;
        }

        public void setBuycount(int buycount) {
            this.buycount = buycount;
        }

        public int getViewcount() {
            return viewcount;
        }

        public void setViewcount(int viewcount) {
            this.viewcount = viewcount;
        }

        public int getCommentcount() {
            return commentcount;
        }

        public void setCommentcount(int commentcount) {
            this.commentcount = commentcount;
        }

        public int getPlaycount() {
            return playcount;
        }

        public void setPlaycount(int playcount) {
            this.playcount = playcount;
        }

        public int getRemainDays() {
            return remainDays;
        }

        public void setRemainDays(int remainDays) {
            this.remainDays = remainDays;
        }

        public int getEndMin() {
            return endMin;
        }

        public void setEndMin(int endMin) {
            this.endMin = endMin;
        }

        public int getLiveCount() {
            return liveCount;
        }

        public void setLiveCount(int liveCount) {
            this.liveCount = liveCount;
        }

        public int getAlreadyBuy() {
            return alreadyBuy;
        }

        public void setAlreadyBuy(int alreadyBuy) {
            this.alreadyBuy = alreadyBuy;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIsavaliable() {
            return isavaliable;
        }

        public void setIsavaliable(int isavaliable) {
            this.isavaliable = isavaliable;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public int getIsPay() {
            return isPay;
        }

        public void setIsPay(int isPay) {
            this.isPay = isPay;
        }

        public int getSourceprice() {
            return sourceprice;
        }

        public void setSourceprice(int sourceprice) {
            this.sourceprice = sourceprice;
        }

        public int getCurrentprice() {
            return currentprice;
        }

        public void setCurrentprice(int currentprice) {
            this.currentprice = currentprice;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getLosetype() {
            return losetype;
        }

        public void setLosetype(int losetype) {
            this.losetype = losetype;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getManufactor() {
            return manufactor;
        }

        public void setManufactor(String manufactor) {
            this.manufactor = manufactor;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getGoodsNo() {
            return goodsNo;
        }

        public void setGoodsNo(String goodsNo) {
            this.goodsNo = goodsNo;
        }

        public int getFreight() {
            return freight;
        }

        public void setFreight(int freight) {
            this.freight = freight;
        }
    }
}
