package com.phph.l_demo_x.response;

/**
 * Created by v on 2019/10/30.
 */
public class FFFResponse extends Object {


    /**
     * code : 4021
     * message : 图片验证码错误
     * data : false
     */

    private int code;
    private String message;
    private boolean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }
}
