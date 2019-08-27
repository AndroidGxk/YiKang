package com.yikangcheng.admin.yikang.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者：古祥坤 on 2019/5/23 09:42
 * 邮箱：1724959985@qq.com
 */
@Entity
public class UserDetailBean {
    /**
     * userId : 92
     * realName : 古
     * gender : 0
     * userInfo :
     * avatar : /upload/yizhilu/common/20190409/1554783430861256942.JPG
     * nickName :
     * email : 774293409@qq.com
     * mobile : 13716675831
     */
    @Id
    private long id;
    private int userId;
    private int status;
    private String realName;
    private int gender;
    private String userInfo;
    private String avatar;
    private String nickName;
    private String email;
    private String mobile;
    @Generated(hash = 658792027)
    public UserDetailBean(long id, int userId, int status, String realName,
            int gender, String userInfo, String avatar, String nickName,
            String email, String mobile) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.realName = realName;
        this.gender = gender;
        this.userInfo = userInfo;
        this.avatar = avatar;
        this.nickName = nickName;
        this.email = email;
        this.mobile = mobile;
    }
    @Generated(hash = 1825006551)
    public UserDetailBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getRealName() {
        return this.realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public int getGender() {
        return this.gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public String getUserInfo() {
        return this.userInfo;
    }
    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
