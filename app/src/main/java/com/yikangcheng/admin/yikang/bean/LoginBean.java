package com.yikangcheng.admin.yikang.bean;

/**
 * 作者：古祥坤 on 2019/5/22 16:34
 * 邮箱：1724959985@qq.com
 */
public class LoginBean {
    /**
     * lastLoginTime : 2019-05-21 19:39:04
     * nickname : 18888888866
     * memTime : 1558438744442
     * id : 85
     */

    private String lastLoginTime;
    private String nickname;
    private String memTime;
    private int id;

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMemTime() {
        return memTime;
    }

    public void setMemTime(String memTime) {
        this.memTime = memTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
