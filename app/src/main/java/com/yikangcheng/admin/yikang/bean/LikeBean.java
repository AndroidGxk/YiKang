package com.yikangcheng.admin.yikang.bean;

import java.util.List;

/**
 * Created by lenovo on 2019/5/18.
 * WF
 */
public class LikeBean {
    @Override
    public String toString() {
        return "LikeBean{" +
                "message='" + message + '\'' +
                ", success=" + success +
                ", entity=" + entity +
                '}';
    }

    /**
     * message : 查询成功
     * success : true
     * entity : [{"id":3643,"name":"全国出发-美国塞班岛旅游自由行多酒店可选","isPay":0,"sourceprice":7999,"currentprice":5599,"logo":"/upload/mavendemo/course/20190321/1553135022846242539.jpg","losetype":0,"freight":0},{"id":6055,"name":"Prada 普拉达 男士蓝色背包 2VZ023-2BTE-F0XVT-V-OOO【跨境直邮】","isPay":0,"sourceprice":8700,"currentprice":5639,"logo":"/upload/mavendemo/course/20190507/1557211546448968607.jpg","losetype":0,"freight":0},{"id":6167,"name":"Fendi 芬迪芬迪女士黑色羊毛小怪兽针织帽子 FAC502-A0DC-F0V6W 【跨境直邮】","isPay":0,"sourceprice":3240,"currentprice":1784,"logo":"/upload/mavendemo/course/20190508/1557302590982085870.jpg","losetype":0,"freight":0},{"id":5737,"name":"怀柔九景区联票","isPay":0,"sourceprice":99,"currentprice":58,"logo":"/upload/mavendemo/course/20190429/1556506280639996314.jpg","losetype":0,"freight":0},{"id":3679,"name":"赏樱网红地赠和服浴衣体验日本旅游东京大阪7天6晚跟团游半自由行","isPay":0,"sourceprice":9799,"currentprice":8099,"logo":"/upload/mavendemo/course/20190321/1553152301660343041.jpg","losetype":0,"freight":0},{"id":3292,"name":"尚格诺  仿皮屏显移动电源","isPay":0,"sourceprice":170,"currentprice":168,"logo":"/upload/mavendemo/course/20190319/1552958923409080985.jpg","losetype":0,"freight":0}]
     */

    private String message;
    private boolean success;
    private List<EntityBean> entity;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<EntityBean> getEntity() {
        return entity;
    }

    public void setEntity(List<EntityBean> entity) {
        this.entity = entity;
    }

    public static class EntityBean {
        @Override
        public String toString() {
            return "EntityBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", isPay=" + isPay +
                    ", sourceprice=" + sourceprice +
                    ", currentprice=" + currentprice +
                    ", logo='" + logo + '\'' +
                    ", losetype=" + losetype +
                    ", freight=" + freight +
                    '}';
        }

        /**
         * id : 3643
         * name : 全国出发-美国塞班岛旅游自由行多酒店可选
         * isPay : 0
         * sourceprice : 7999
         * currentprice : 5599
         * logo : /upload/mavendemo/course/20190321/1553135022846242539.jpg
         * losetype : 0
         * freight : 0
         */

        private int id;
        private String name;
        private int isPay;
        private int sourceprice;
        private int currentprice;
        private String logo;
        private int losetype;
        private int freight;

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

        public int getFreight() {
            return freight;
        }

        public void setFreight(int freight) {
            this.freight = freight;
        }
    }
}
