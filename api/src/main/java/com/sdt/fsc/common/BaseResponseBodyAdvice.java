package com.sdt.fsc.common;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import springfox.documentation.spring.web.plugins.Docket;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: hedonglei
 * @date: 2021-6-15 9:12
 */
@RestControllerAdvice(value = {"com.sdt.fsc"})
public class BaseResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 这个方法表示对于哪些请求要执行beforeBodyWrite，返回true执行，返回false不执行
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return !methodParameter.getDeclaringClass().equals(Docket.class);
    }

    /**
     * 对于返回的对象如果不是最终对象ResponseResult，则选包装一下
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        /**
         * 验证方面的特殊处理
         */
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(httpServletRequest.getAttribute(CustomErrorType.class.getSimpleName()) != null){
            System.out.println("SimpleName:"+CustomErrorType.class.getSimpleName());
            CustomErrorType customErrorType = (CustomErrorType)httpServletRequest.getAttribute(CustomErrorType.class.getSimpleName());
            return JSONObject.toJSON(new BaseResponse<>(customErrorType));
        }

        //如果是字符类型,输出json字符串
        if(mediaType.includes(MediaType.TEXT_HTML)||mediaType.includes(MediaType.TEXT_PLAIN)){
            return JSONObject.toJSON(new BaseResponse<>(body, CustomErrorType.SUCCESS));
        }

        //如果已经被异常捕获,返回的就是BaseResponse对象,不用再次封装了
        if (body instanceof BaseResponse<?>) {
            return body;
        }
        return new BaseResponse<>(body, CustomErrorType.SUCCESS);
    }
}