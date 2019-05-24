package com.yikangcheng.admin.yikang.bean;

/**
 * Created by lenovo on 2019/5/22.
 * WF
 */

/**
 * 首页-----秒杀
 */
public class SeckillBean {
    @Override
    public String toString() {
        return "SeckillBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", pcImage='" + pcImage + '\'' +
                ", appImage='" + appImage + '\'' +
                ", description='" + description + '\'' +
                ", sort=" + sort +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status=" + status +
                '}';
    }

    /**
     * id : 6
     * name : 秒杀测试1
     * link :
     * pcImage : /upload/mavendemo/shop/20190505/1557038890767473416.jpg
     * appImage : /upload/mavendemo/shop/20190505/1557038936101099299.jpeg
     * description : 来秒杀啦
     * sort : 99
     * beginTime : 2019-05-07 00:00:00
     * endTime : 2019-05-28 00:00:00
     * status : 3
     */

    private int id;
    private String name;
    private String link;
    private String pcImage;
    private String appImage;
    private String description;
    private int sort;
    private String beginTime;
    private String endTime;
    private int status;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPcImage() {
        return pcImage;
    }

    public void setPcImage(String pcImage) {
        this.pcImage = pcImage;
    }

    public String getAppImage() {
        return appImage;
    }

    public void setAppImage(String appImage) {
        this.appImage = appImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
