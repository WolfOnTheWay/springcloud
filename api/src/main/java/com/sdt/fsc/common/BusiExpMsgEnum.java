package com.sdt.fsc.common;

/**
 * @author wangtiexiang
 * @Description
 * @Datetime 2020/8/18 14:20
 */
public enum BusiExpMsgEnum {
    USER_PWD_ERROR(20001,"密码错误"),
    USER_NOT_FOUND(20002,"用户未找到"),
    UNAUTH_ERROR(20003,"用户未登录");

    private String message;
    private Integer code;

    BusiExpMsgEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
