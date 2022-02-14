package com.sdt.fsc.exception;

import com.sdt.fsc.common.BaseResponse;
import com.sdt.fsc.common.CustomErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: hedonglei
 * @date: 2021-7-13 12:49
 */
@RestControllerAdvice(value = {"com.sdt.fsc"})
@ResponseBody
@Slf4j
public class BaseException {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BaseResponse<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException e){
        return new BaseResponse<>(CustomErrorType.PARAMS_NULL.getCode(),e.getLocalizedMessage()==null?String.valueOf(e):e.getLocalizedMessage(),null);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public BaseResponse<Object> handleIllegalArgumentException(IllegalArgumentException e){
        return new BaseResponse<>(CustomErrorType.PARAM_INVALID.getCode(),e.getLocalizedMessage()==null?String.valueOf(e):e.getLocalizedMessage(),null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        final List<String> errList = new ArrayList<>();
        e.getBindingResult().getAllErrors().stream().forEach(x-> {
            errList.add(x.getDefaultMessage());
        });
        return new BaseResponse<>(CustomErrorType.PARAM_INVALID.getCode(),errList.toString(),null);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public BaseResponse<?> handleNoHandlerFoundException(NoHandlerFoundException e){
        return new BaseResponse<>(404, e.getMessage()==null?"请求资源不存在":e.getMessage(),null);
    }

    @ExceptionHandler(CustomException.class)
    public BaseResponse<?> handleCustomException(CustomException e){
        return new BaseResponse<>(e.getCode(), e.getMessage(), null);
    }

    @ExceptionHandler(GeneralFailException.class)
    public BaseResponse<?> handleGeneralFailException(GeneralFailException e){
        return new BaseResponse<>(e.getCode(), e.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse<String> handleException(Exception e){
        return new BaseResponse<>(CustomErrorType.ERROR.getCode(),e.getMessage()==null?String.valueOf(e): CustomErrorType.ERROR.getMessage(), null);
    }
}
