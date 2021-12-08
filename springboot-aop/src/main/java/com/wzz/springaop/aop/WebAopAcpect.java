package com.wzz.springaop.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Aspect//这是一个切面
@Component//告诉Spring需要将其加入到IOC容器
@Slf4j
public class WebAopAcpect {
//
//    //ThreadLocal保证不受其他线程影响，用于记录接口响应时间
//    private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();
//
//    /**
//     * 定义切入点  就是扫描的包
//     */
//    @Pointcut("execution(public * com.wzz.springaop.controller..*.*(..))")
//    public void aopAcpect() {
//    }
//
////    /**
////     * 环绕增强
////     */
////    @Around("aopAcpect()")
////    public void doAround(JoinPoint joinPoint) {
////        System.out.println("环绕增强");
////        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
////        HttpServletRequest request = attributes.getRequest();
////
////        request.setAttribute("info", "info:wzz");
////
////        //请求的内容，记录url、method、ip
////        System.out.println("URL==>" + request.getRequestURL().toString() + "\n" +//获取请求连接
////                "METHOD==>" + request.getMethod() + "\n" +//获取请求方式
////                "IP==>" + request.getRemoteAddr() + "\n" +//获取ip地址
////                "getContextPath==>" + request.getContextPath() + "\n");//获取请求上下文路径
////
////        //获取请求参数，将value数组转成字符串
////        Map<String, String[]> params = request.getParameterMap();
////
////        Map<String, String> args = new HashMap<>();
////        for (Map.Entry<String, String[]> temp : params.entrySet()) {
////            args.put(temp.getKey(), Arrays.toString(temp.getValue()));
////        }
////
////        //连接点的签名可以跟踪到程序具体类、具体方法，记录一下方法和参数
////        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();//获取包路径
////        String name = joinPoint.getSignature().getName();//获取方法名
////        System.out.println("CLASS_METHOD==>" + declaringTypeName + "." + name + "," +
////                "ARGS==>" + args);
////    }
//
//    @Before("aopAcpect()")
//    public void doBefore(JoinPoint joinPoint) {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//        request.setAttribute("info", "info:wzz");
//
//        //请求的内容，记录url、method、ip
//        System.out.println("URL==>" + request.getRequestURL().toString() + "\n" +//获取请求连接
//                "METHOD==>" + request.getMethod() + "\n" +//获取请求方式
//                "IP==>" + request.getRemoteAddr() + "\n" +//获取ip地址
//                "getContextPath==>" + request.getContextPath() + "\n");//获取请求上下文路径
//
//        //获取请求参数，将value数组转成字符串
//        Map<String, String[]> params = request.getParameterMap();
//
//        Map<String, String> args = new HashMap<>();
//        for (Map.Entry<String, String[]> temp : params.entrySet()) {
//            args.put(temp.getKey(), Arrays.toString(temp.getValue()));
//        }
//
//        //连接点的签名可以跟踪到程序具体类、具体方法，记录一下方法和参数
//        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();//获取包路径
//        String name = joinPoint.getSignature().getName();//获取方法名
//        System.out.println("CLASS_METHOD==>" + declaringTypeName + "." + name + "," +
//                "ARGS==>" + args);
//        //记录请求接口开始时间
//        threadLocal.set(System.currentTimeMillis());
//    }
//
//    /***
//     * after returning advice 后置返回增强处理
//     * @param joinPoint  连接点
//     * @param returnMsg return返回的信息(就是response)
//     */
//    @AfterReturning(pointcut = "aopAcpect()", returning = "returnMsg")
//    public void doAfterReturn(JoinPoint joinPoint, Object returnMsg) {
//        //与前置增强一致记录下方法名
//        System.out.println("CLASS_METHOD==>" + joinPoint.getSignature().getDeclaringTypeName() + "." +
//                joinPoint.getSignature().getName());
//        //记录一下接口响应时间
//        Long reponseTime = System.currentTimeMillis() - threadLocal.get();
//        System.out.println("接口响应时间(毫秒)==>" + reponseTime);
//        //删除threadLocal变量副本
//        threadLocal.remove();
//    }
//
//    /***
//     * 后置异常增强处理
//     * @param joinPoint 连接点
//     * @param exception 目标方法抛出的异常与增强处理的异常一致才执行，否则不执行，
//     * 如果是throwing对应的是Throwable类型将匹配任何类型异常
//     */
//    @AfterThrowing(pointcut = "aopAcpect()", throwing = "exception")
//    public void doAfterThrow(JoinPoint joinPoint, Throwable exception) {
//        //记录空指针异常
//        if (exception instanceof NullPointerException) {
//            System.out.println("报NullPointerException了！请处理一下！");
//        }
//    }
//
//    /**
//     * 返回通知
//     * @param joinpoint
//     * @param rvt
//     */
//    @AfterReturning(pointcut = "aopAcpect()", returning = "rvt")
//    public void afterReturningMethod(JoinPoint joinpoint, Object rvt) {
//
//    }
}
