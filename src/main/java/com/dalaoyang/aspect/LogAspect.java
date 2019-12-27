package com.dalaoyang.aspect;

import com.dalaoyang.annotation.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


/**
* Description AOP切面类
* @Author junwei
* @Date 16:39 2019/12/27
**/
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(public * com.dalaoyang.controller.*.*(..))")
    public void LogAspect(){}


    @Around(value = "LogAspect() && @annotation(log) &&args(object,..) ")
    public Object around(ProceedingJoinPoint joinPoint, Log log, Object object) throws Throwable {

        try {
            //获取request信息
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            //输出IP地址
            System.out.println(getIpAddress(request));

            //输出LOG日志参数
            System.out.println(log.param1());
            //输出POST请求接收到的参数
            System.out.println(object);
            return joinPoint.proceed();
        } catch (Exception e) {
            e.printStackTrace();
            return joinPoint.proceed();
        }
    }


    /**
     * Description 获取IP地址
     *
     * @param request
     * @Author junwei
     * @Date 10:51 2019/10/28
     **/
    public static String getIpAddress(ServletRequest request) {
        String ip = ((HttpServletRequest) request).getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = ((HttpServletRequest) request).getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = ((HttpServletRequest) request).getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = ((HttpServletRequest) request).getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = ((HttpServletRequest) request).getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

//    @Before("LogAspect()")
//    public void doBefore(JoinPoint joinPoint){
//        System.out.println("doBefore");
//    }
//
//    @After("LogAspect()")
//    public void doAfter(JoinPoint joinPoint){
//        System.out.println("doAfter");
//    }
//
//    @AfterReturning("LogAspect()")
//    public void doAfterReturning(JoinPoint joinPoint){
//        System.out.println("doAfterReturning");
//    }
//
//    @AfterThrowing("LogAspect()")
//    public void deAfterThrowing(JoinPoint joinPoint){
//        System.out.println("deAfterThrowing");
//    }
//
//    @Around("LogAspect()")
//    public Object deAround(ProceedingJoinPoint joinPoint) throws Throwable{
//        System.out.println("deAround");
//        return joinPoint.proceed();
//    }

}
