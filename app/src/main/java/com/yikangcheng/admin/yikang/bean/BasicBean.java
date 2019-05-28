package com.yikangcheng.admin.yikang.bean;

/**
 * Created by lenovo on 2019/5/27.
 * WF
 */
public class BasicBean {
    @Override
    public String toString() {
        return "BasicBean{" +
                "message='" + message + '\'' +
                ", success=" + success +
                ", entity='" + entity + '\'' +
                '}';
    }

    /**
     * message : 修改成功
     * success : true
     * entity :
     */

    private String message;
    private boolean success;
    private String entity;

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

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }
}
