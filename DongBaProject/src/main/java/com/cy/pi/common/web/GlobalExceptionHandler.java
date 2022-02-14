package com.cy.pi.common.web;


import com.cy.pi.common.exception.ServiceException;
import com.cy.pi.common.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *@ControllerAdvice使用此注解描述的类为一个全局异常处理的类
 *@Author:LZJ
 *@Date:2021/11/9
 *@Param:
*/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //出现异常时会在日志中将异常定位到当前类中
//    private Logger log= LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     *@Description:@ExceptionHandler描述的方法为全局异常处理类中的异常处理方法
     * 传入的参数为其能够处理的异常的类型
     *@Author:LZJ
     *@Date:2021/11/9
     *@Param:
    */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public JsonResult doHandleRuntimeException(RuntimeException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return new JsonResult(e);
    }
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public JsonResult doHandleServiceException(RuntimeException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return new JsonResult(e);
    }

    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public JsonResult doHandleShiroException(ShiroException e){

        return new JsonResult("没用操作权限");
    }
}
