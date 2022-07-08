package com.wzz.base.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("执行了logininterceptor的preHandle方法");
        System.out.println(handler);
        System.out.println("token:" + request.getHeader("token"));
        String token = request.getHeader("token");


        String requestUri = request.getRequestURI();
        System.out.println(requestUri);

        //再判断token是否正确
        if (null == token) {
            throw new RuntimeException("token为空");
        }
        return true;
    }
}
