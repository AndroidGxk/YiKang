package com.yikangcheng.admin.yikang.bean;

/**
 * Created by lenovo on 2019/5/20.
 * WF
 */
public class All_A_Bean {
    private String id;
    private String bianhao;
    private String data;

    public All_A_Bean(String id, String bianhao, String data) {
        this.id = id;
        this.bianhao = bianhao;
        this.data = data;
    }

    public All_A_Bean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBianhao() {
        return bianhao;
    }

    public void setBianhao(String bianhao) {
        this.bianhao = bianhao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "All_A_Bean{" +
                "id=" + id +
                ", bianhao='" + bianhao + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
