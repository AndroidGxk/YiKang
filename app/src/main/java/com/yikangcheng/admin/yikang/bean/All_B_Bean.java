package com.yikangcheng.admin.yikang.bean;

/**
 * Created by lenovo on 2019/5/20.
 * WF
 */
public class All_B_Bean {
    private String id;
    private String name;
    private String title;
    private String price;

    public All_B_Bean() {
    }

    public All_B_Bean(String id, String name, String title, String price) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "All_B_Bean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
