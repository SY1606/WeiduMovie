package com.bw.movie.bean;

/**
 * @ProjectName: WeiduMovie
 * @ClassName: RegistBean
 * @Description: java类作用描述
 * @Author: 刘继超
 * @CreateDate: 2019/5/11 14:44:19
 */
public class RegistBean {

    /**
     * message : 注册成功
     * status : 0000
     */

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
