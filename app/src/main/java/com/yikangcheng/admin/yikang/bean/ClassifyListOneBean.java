package com.yikangcheng.admin.yikang.bean;

import java.util.List;

/**
 * 作者：古祥坤 on 2019/5/18 14:05
 * 邮箱：1724959985@qq.com
 */
public class ClassifyListOneBean {
    /**
     * subjectId : 449
     * subjectName : 手机数码
     * status : 0
     * createTime : 2019-04-18 17:42:03
     * updateTime : 2019-04-18 17:42:03
     * parentId : 0
     * level : 1
     * image :
     * sort : 15
     * childSubjectList : [{"subjectId":450,"subjectName":"手机","status":0,"createTime":"2019-04-18 17:42:26","updateTime":"2019-04-18 17:42:26","parentId":449,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":456,"subjectName":"手机","status":0,"createTime":"2019-04-18 17:44:36","updateTime":"2019-04-29 11:08:03","parentId":450,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556507281432560448.jpg","sort":0,"childSubjectList":[]},{"subjectId":457,"subjectName":"手机配件","status":0,"createTime":"2019-04-18 17:44:55","updateTime":"2019-04-29 11:08:17","parentId":450,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556507296387922382.jpg","sort":0,"childSubjectList":[]}]},{"subjectId":452,"subjectName":"数码产品","status":0,"createTime":"2019-04-18 17:42:42","updateTime":"2019-04-18 17:45:34","parentId":449,"level":2,"image":"","sort":0,"childSubjectList":[{"subjectId":458,"subjectName":"耳机","status":0,"createTime":"2019-04-18 17:45:06","updateTime":"2019-04-30 09:23:34","parentId":452,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587411373285427.jpg","sort":0,"childSubjectList":[]},{"subjectId":459,"subjectName":"音响","status":0,"createTime":"2019-04-18 17:45:45","updateTime":"2019-04-30 09:23:47","parentId":452,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587424317382120.jpg","sort":0,"childSubjectList":[]},{"subjectId":460,"subjectName":"无人机","status":0,"createTime":"2019-04-18 17:45:54","updateTime":"2019-04-30 09:24:03","parentId":452,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587440077306294.jpg","sort":0,"childSubjectList":[]},{"subjectId":461,"subjectName":"摄影自拍","status":0,"createTime":"2019-04-18 17:46:09","updateTime":"2019-04-30 09:24:19","parentId":452,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587457687791315.jpg","sort":0,"childSubjectList":[]},{"subjectId":462,"subjectName":"智能手表","status":0,"createTime":"2019-04-18 18:00:00","updateTime":"2019-04-30 09:24:35","parentId":452,"level":3,"image":"","icon":"/upload/mavendemo/course/20190430/1556587473747989433.jpg","sort":0,"childSubjectList":[]}]}]
     * icon :
     */

    private int subjectId;
    private String subjectName;
    private int status;
    private String createTime;
    private String updateTime;
    private int parentId;
    private int level;
    private String image;
    private int sort;
    private String icon;
    private List<ChildSubjectListBeanX> childSubjectList;

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<ChildSubjectListBeanX> getChildSubjectList() {
        return childSubjectList;
    }

    public void setChildSubjectList(List<ChildSubjectListBeanX> childSubjectList) {
        this.childSubjectList = childSubjectList;
    }

    public static class ChildSubjectListBeanX {
        /**
         * subjectId : 450
         * subjectName : 手机
         * status : 0
         * createTime : 2019-04-18 17:42:26
         * updateTime : 2019-04-18 17:42:26
         * parentId : 449
         * level : 2
         * image :
         * sort : 0
         * childSubjectList : [{"subjectId":456,"subjectName":"手机","status":0,"createTime":"2019-04-18 17:44:36","updateTime":"2019-04-29 11:08:03","parentId":450,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556507281432560448.jpg","sort":0,"childSubjectList":[]},{"subjectId":457,"subjectName":"手机配件","status":0,"createTime":"2019-04-18 17:44:55","updateTime":"2019-04-29 11:08:17","parentId":450,"level":3,"image":"","icon":"/upload/mavendemo/course/20190429/1556507296387922382.jpg","sort":0,"childSubjectList":[]}]
         */

        private int subjectId;
        private String subjectName;
        private int status;
        private String createTime;
        private String updateTime;
        private int parentId;
        private int level;
        private String image;
        private int sort;
        private List<ChildSubjectListBean> childSubjectList;

        public int getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(int subjectId) {
            this.subjectId = subjectId;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public List<ChildSubjectListBean> getChildSubjectList() {
            return childSubjectList;
        }

        public void setChildSubjectList(List<ChildSubjectListBean> childSubjectList) {
            this.childSubjectList = childSubjectList;
        }

        public static class ChildSubjectListBean {
            /**
             * subjectId : 456
             * subjectName : 手机
             * status : 0
             * createTime : 2019-04-18 17:44:36
             * updateTime : 2019-04-29 11:08:03
             * parentId : 450
             * level : 3
             * image :
             * icon : /upload/mavendemo/course/20190429/1556507281432560448.jpg
             * sort : 0
             * childSubjectList : []
             */

            private int subjectId;
            private String subjectName;
            private int status;
            private String createTime;
            private String updateTime;
            private int parentId;
            private int level;
            private String image;
            private String icon;
            private int sort;
            private List<?> childSubjectList;

            public int getSubjectId() {
                return subjectId;
            }

            public void setSubjectId(int subjectId) {
                this.subjectId = subjectId;
            }

            public String getSubjectName() {
                return subjectName;
            }

            public void setSubjectName(String subjectName) {
                this.subjectName = subjectName;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public List<?> getChildSubjectList() {
                return childSubjectList;
            }

            public void setChildSubjectList(List<?> childSubjectList) {
                this.childSubjectList = childSubjectList;
            }
        }
    }
}
