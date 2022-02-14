package com.cy.pi.common.aspect;

import com.cy.pi.common.anno.RequestLog;
import com.cy.pi.common.utils.IPUtils;
import com.cy.pi.sys.dao.SysLogDao;
import com.cy.pi.sys.entity.SysLog;
import com.cy.pi.sys.entity.SysUser;
import com.cy.pi.sys.service.SysLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.logging.Logger;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2021/12/19 19:38
 */
@Aspect
@Component
public class LogAspect {
    private Logger log = Logger.getLogger(String.valueOf(LogAspect.class));
    @Autowired
    private SysLogService sysLogService;
    @Pointcut("@annotation(com.cy.pi.common.anno.RequestLog)")
    public void logPointCut(){}
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long totalTime = endTime -startTime;
        saveSysLog(joinPoint,totalTime);
        return result;
    }
    public void saveSysLog(ProceedingJoinPoint point,long totalTime) throws Exception{
        Thread t = Thread.currentThread();
        String name = t.getName();
        System.out.println("name=" + name);
        //1.获取日志信息
        MethodSignature ms=
                (MethodSignature)point.getSignature();
        Class<?> targetClass=
                point.getTarget().getClass();
        String className=targetClass.getName();
        //获取接口声明的方法
        String methodName=ms.getMethod().getName();
        Class<?>[] parameterTypes=ms.getMethod().getParameterTypes();
        //获取目标对象方法
        Method targetMethod=targetClass.getDeclaredMethod(
                methodName,parameterTypes);
        //判定目标方法上是否有RequestLog注解
        boolean flag=
                targetMethod.isAnnotationPresent(RequestLog.class);
        SysUser user = (SysUser)
                SecurityUtils.getSubject().getPrincipal();
        String username= user.getUsername();
                //获取方法参数
        Object[] paramsObj=point.getArgs();
        System.out.println("paramsObj="+paramsObj);
        //将参数转换为字符串
        String params=new ObjectMapper()
                .writeValueAsString(paramsObj);
        //2.封装日志信息
        SysLog log=new SysLog();
        log.setUsername(username);//登陆的用户
        //假如目标方法对象上有注解,我们获取注解定义的操作值
        if(flag){
            RequestLog requestLog=
                    targetMethod.getDeclaredAnnotation(RequestLog.class);
            log.setOperation(requestLog.value());
        }
        log.setMethod(className+"."+methodName);//className.methodName()
        log.setParams(params);//method params
        log.setIp(IPUtils.getIpAddr());//ip 地址
        log.setTime(totalTime);//
        log.setCreatedTime(new Date());
        //3.保存日志信息
        sysLogService.insertObject(log);
    }
}
