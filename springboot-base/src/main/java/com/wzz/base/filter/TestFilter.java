package com.wzz.base.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * init：过滤器初始化时回调，可以在这里做过滤器的初始化操作，例如设置白名单路径列表。
 * doFilter：过滤器初始化后并在请求到达后端且进入到注册过滤器设置的匹配路径时回调。
 * destroy：过滤器销毁时回调。
 */
@Order(999) // 序号越低，优先级越高
// 加上WebFilter即可成为过滤器
@WebFilter(filterName="myFilter", urlPatterns="/api/*")
public class TestFilter implements Filter {

    public static List<String> path = new ArrayList<>();

    static {
        path.add("/springboot-aop/stu/hello");
        path.add("/springboot-aop/stu/hello2");
        path.add("/springboot-aop/stu/hello33");
        path.add("/springboot-aop/stu/login");
//        path.add("/springboot-aop/static/");
    }

    //init方法，初始化过滤器，可以在init()方法中完成与构造方法类似的初始化功能，
    //如果初始化代码中要使用到FillerConfig对象，那么这些初始化代码就只能在Filler的init()方法中编写而不能在构造方法中编写
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("第一个过滤器成功初始化。。。。。。。。。。。。。");
    }

    /**
     * doFilter()方法有多个参数，其中
     * 参数request和response为Web服务器或Filter链中的上一个Filter传递过来的请求和响应对象；
     * 参数chain代表当前Filter链的对象，
     * 只有在当前Filter对象中的doFilter()方法内部需要调用FilterChain对象的doFilter()法才能把请求交付给Filter链中的下一个Filter或者目标程序处理
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        //白名单在前
        //对包含我们要求的四个请求予以放行，将其它请求拦截重定向至/online，
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);
        String requestUri = req.getRequestURI();
        System.out.println(requestUri);
        if (path.contains(requestUri)) {
            chain.doFilter(request, response);
        } else {
            wrapper.sendRedirect("/springboot-aop/stu/login");
        }


        //这里为了使用getHeader方法获取token，转型成HttpServletRequest
        System.out.println("token:" + req.getHeader("token"));
        String token = req.getHeader("token");
        //再判断token是否正确
        if (null == token) {
            throw new RuntimeException("token为空");
        }


        //调用doFilter方法，正常返回servletResponse
        chain.doFilter(request, response);
    }

    //destroy()方法在Web服务器卸载Filter对象之前被调用，该方法用于释放被Filter对象打开的资源，例如关闭数据库和I/O流
    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("过滤器被销毁");
    }
}
