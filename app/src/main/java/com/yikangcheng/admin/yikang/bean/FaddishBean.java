package com.yikangcheng.admin.yikang.bean;

import java.util.List;

/**
 * Created by lenovo on 2019/5/22.
 * WF
 */
public class FaddishBean {
    /**
     * module : {"module2":[[],[{"id":6237,"name":"Burberry 巴宝莉 男士黑色拼黑色图案格纹对折长款钱夹钱包 8006072  【跨境直邮】","logo":"/upload/mavendemo/course/20190508/1557314248585780947.jpg"},{"id":6235,"name":"Burberry 巴宝莉 女士粉色小牛皮背包 4080197 【跨境直邮】","logo":"/upload/mavendemo/course/20190508/1557314004987053305.jpg"}],[],[{"id":5578,"name":"华为 HUAWEI Mate 20 麒麟980AI智能芯片全面屏超微距影像超大广角徕卡三摄全网通版双4G手机","logo":"/upload/mavendemo/course/20190425/1556172773502247071.jpg"},{"id":5355,"name":"小米（MI） 小米8 游戏手机 全网通 ","logo":"/upload/mavendemo/course/20190415/1555313259120051996.jpg"},{"id":5354,"name":" 荣耀v20全面屏AI全网通 双卡双待 手机","logo":"/upload/mavendemo/course/20190429/1556526499898259230.jpg"},{"id":4960,"name":"Apple iPhone XS Max 移动联通电信4G手机 双卡双待","logo":"/upload/mavendemo/course/20190408/1554691361820237874.jpg"}]],"module3":[[],[{"id":5466,"name":"兰蔻（LANCOME）小黑瓶肌底液100ml 补水保湿","logo":"/upload/mavendemo/course/20190423/1556009631179190823.jpg"},{"id":5529,"name":"兰芝隔离霜妆前乳 女 打底保湿 防晒遮瑕 雪纱丝柔修颜","logo":"/upload/mavendemo/course/20190424/1556089668792871940.jpg"},{"id":5616,"name":"CPB肌肤之钥光透白隔离乳30ml（化妆品 隔离霜 防晒 遮瑕 ）","logo":"/upload/mavendemo/course/20190426/1556256323777874464.jpg"}]],"module1":[[],[{"id":6407,"name":"缪缪（MIU MIU）莹铃女士香氛 香水 100ml","logo":"/upload/mavendemo/course/20190510/1557453342565345424.jpg"}]],"module4":[]}
     */

    private ModuleBean module;

    public ModuleBean getModule() {
        return module;
    }

    public void setModule(ModuleBean module) {
        this.module = module;
    }

    public static class ModuleBean {
        private List<List<?>> module2;
        private List<List<?>> module3;
        private List<List<?>> module1;
        private List<?> module4;

        public List<List<?>> getModule2() {
            return module2;
        }

        public void setModule2(List<List<?>> module2) {
            this.module2 = module2;
        }

        public List<List<?>> getModule3() {
            return module3;
        }

        public void setModule3(List<List<?>> module3) {
            this.module3 = module3;
        }

        public List<List<?>> getModule1() {
            return module1;
        }

        public void setModule1(List<List<?>> module1) {
            this.module1 = module1;
        }

        public List<?> getModule4() {
            return module4;
        }

        public void setModule4(List<?> module4) {
            this.module4 = module4;
        }
    }

}
