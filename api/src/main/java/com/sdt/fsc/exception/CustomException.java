package com.sdt.fsc.exception;

import com.sdt.fsc.common.CustomErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @description: 用户自定义异常
 * @author: hedonglei
 * @date: 2021-6-15 10:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException {

    private int code;
    private String message;

    public CustomException(CustomErrorType customErrorType, String message) {
        this.code = customErrorType.getCode();
        this.message = message;
    }

    public CustomException(CustomErrorType customErrorType) {
        this.code = customErrorType.getCode();
        this.message = customErrorType.getMessage();
    }
/*
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    public static class ParamInvalidException extends RuntimeException{
        private Map<String,Object> params;
    }

    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    public static class NotFoundException extends RuntimeException{
        private Map<String,Object> params;
    }

    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    public static class AlreadyExistsException extends RuntimeException{
        private Map<String,Object> params;
    }

    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    public static class ParamNullException extends RuntimeException{
        private Map<String,Object> params;
    }

    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    public static class ImageUploadException extends RuntimeException{
        private Map<String,Object> params;
    }

    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    public static class ImageNullException extends RuntimeException{
        private Map<String,Object> params;
    }*/

}
