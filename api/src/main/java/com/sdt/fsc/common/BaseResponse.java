package com.sdt.fsc.common;


import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang.math.NumberUtils;

import java.io.Serializable;

/**
 * @description:
 * @author: hedonglei
 * @date: 2021-6-15 9:09
 */
@Data
@Accessors(chain = true)
public class BaseResponse<T> implements Serializable {

    private int code;
    private String message;
    private T data;

    public BaseResponse() {
        super();
    }

    public BaseResponse(T data){
        this.data=data;
    }

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(String code, String message, T data) {
        if (NumberUtils.isNumber(code)) {
            this.code = Integer.parseInt(code);
        } else {
            this.code = 0;
        }
        this.message = message;
        this.data = data;
    }

    public BaseResponse(T data, CustomErrorType customErrorType){
        this.code= customErrorType.getCode();
        this.message= customErrorType.getMessage();
        this.data=data;
    }

    public BaseResponse(CustomErrorType customErrorType, T data){
        this.code = customErrorType.getCode();
        this.message = customErrorType.getMessage();
        this.data = data;
    }

    public BaseResponse(CustomErrorType customErrorType){
        this.code = customErrorType.getCode();
        this.message = customErrorType.getMessage();
        this.data = null;
    }

    public static BaseResponse<?> badRequest() {
        return new BaseResponse<>(404, "无法找到请求的资源");
    }
}
