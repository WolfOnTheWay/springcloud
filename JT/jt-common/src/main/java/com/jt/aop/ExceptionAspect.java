package com.jt.aop;

import com.jt.vo.SysResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/1/7 15:41
 */
@RestControllerAdvice //切面的定义，定义Controller的切面
@Slf4j
public class ExceptionAspect {
    //当出现某类异常 该方法执行
    @ExceptionHandler(RuntimeException.class)
    public SysResult result(Exception exception) {
//        exception.printStackTrace(); //为程序员调错方便准备.

        log.error("异常信息:", exception);
        return SysResult.fail();
    }
}
