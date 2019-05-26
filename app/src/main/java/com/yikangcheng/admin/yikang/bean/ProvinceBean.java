package com.yikangcheng.admin.yikang.bean;

import java.util.List;

/**
 * 作者：古祥坤 on 2019/5/25 14:02
 * 邮箱：1724959985@qq.com
 */
public class ProvinceBean {
    /**
     * id : 2
     * parentId : 1
     * areaName : 北京市
     * areaType : 1
     * childAreaList : []
     */

    private int id;
    private int parentId;
    private String areaName;
    private int areaType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getAreaType() {
        return areaType;
    }

    public void setAreaType(int areaType) {
        this.areaType = areaType;
    }
}
