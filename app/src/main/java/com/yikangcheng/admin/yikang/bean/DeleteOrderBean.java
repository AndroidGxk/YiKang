package com.yikangcheng.admin.yikang.bean;

/**
 * Created by lenovo on 2019/5/28.
 * WF
 */
public class DeleteOrderBean {
    @Override
    public String toString() {
        return "DeleteOrderBean{" +
                "message='" + message + '\'' +
                ", success=" + success +
                '}';
    }

    /**
     * message : 删除成功
     * success : true
     */

    private String message;
    private boolean success;

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
}
