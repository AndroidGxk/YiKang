package com.yikangcheng.admin.yikang.bean;

/**
 * Created by lenovo on 2019/5/27.
 * WF
 */
public class SeekHotBean {
    private String id;
    private int img;

    public SeekHotBean() {
    }

    public SeekHotBean(String id, int img) {
        this.id = id;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "SeekHotBean{" +
                "id='" + id + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
