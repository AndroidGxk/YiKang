package com.yikangcheng.admin.yikang.bean;

/**
 * 作者：古祥坤 on 2019/5/22 20:40
 * 邮箱：1724959985@qq.com
 */
public class UserInfoBean<T> {
    /**
     * userImage : {"id":14,"imagesUrl":"/upload/mavendemo/bannerImages/20190522/1558519323466881744.png","title":"APP个人中心广告图","keyWord":"img","seriesNumber":1,"color":"#FFFFFF","previewUrl":""}
     * userCenter : {"userId":92,"realName":"古","gender":0,"userInfo":"","avatar":"/upload/yizhilu/common/20190409/1554783430861256942.JPG","nickName":"","email":"774293409@qq.com","mobile":13716675831}
     */

    private UserImageBean userImage;
    private T userCenter;

    public UserImageBean getUserImage() {
        return userImage;
    }

    public void setUserImage(UserImageBean userImage) {
        this.userImage = userImage;
    }

    public T getUserCenter() {
        return userCenter;
    }

    public void setUserCenter(T userCenter) {
        this.userCenter = userCenter;
    }

    public static class UserImageBean {
        /**
         * id : 14
         * imagesUrl : /upload/mavendemo/bannerImages/20190522/1558519323466881744.png
         * title : APP个人中心广告图
         * keyWord : img
         * seriesNumber : 1
         * color : #FFFFFF
         * previewUrl :
         */

        private int id;
        private String imagesUrl;
        private String title;
        private String keyWord;
        private int seriesNumber;
        private String color;
        private String previewUrl;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getKeyWord() {
            return keyWord;
        }

        public void setKeyWord(String keyWord) {
            this.keyWord = keyWord;
        }

        public int getSeriesNumber() {
            return seriesNumber;
        }

        public void setSeriesNumber(int seriesNumber) {
            this.seriesNumber = seriesNumber;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getPreviewUrl() {
            return previewUrl;
        }

        public void setPreviewUrl(String previewUrl) {
            this.previewUrl = previewUrl;
        }
    }
}
