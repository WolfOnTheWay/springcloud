package com.cy.pi.common.vo;

import java.io.Serializable;

/*借助此对象封装响应数据
* 状态码、具体信息、数据*/
public class JsonResult implements Serializable {

    private static final long serialVersionUID = -8416173372983024819L;
    private int state = 1;
    private String message="ok";
    private Object data;

    public JsonResult(String message) {
        this.message = message;
    }

    public JsonResult(Object data) {
        this.data = data;
    }
    public JsonResult(Throwable e){
        this.state = 0;
        this.message =e.getMessage();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
