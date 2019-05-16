package com.yikangcheng.admin.yikang.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lenovo on 2019/5/16.
 * WF
 */
@Entity
public class SoSuoDb {
    @Id(autoincrement = true)
    private Long id;
    private String seekTitle;
    @Generated(hash = 582010189)
    public SoSuoDb(Long id, String seekTitle) {
        this.id = id;
        this.seekTitle = seekTitle;
    }
    @Generated(hash = 1916975951)
    public SoSuoDb() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSeekTitle() {
        return this.seekTitle;
    }
    public void setSeekTitle(String seekTitle) {
        this.seekTitle = seekTitle;
    }
}
