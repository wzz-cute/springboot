//package com.wzz.base.filter;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpServletResponseWrapper;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * init：过滤器初始化时回调，可以在这里做过滤器的初始化操作，例如设置白名单路径列表。
// * doFilter：过滤器初始化后并在请求到达后端且进入到注册过滤器设置的匹配路径时回调。
// * destroy：过滤器销毁时回调。
// */
//public class TestFilter2 implements Filter {
//
//    //init方法，初始化过滤器，可以在init()方法中完成与构造方法类似的初始化功能，
//    //如果初始化代码中要使用到FillerConfig对象，那么这些初始化代码就只能在Filler的init()方法中编写而不能在构造方法中编写
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//        System.out.println("第二个过滤器成功初始化。。。。。。。。。。。。。");
//    }
//
//    //doFilter()方法有多个参数，其中
//    //参数request和response为Web服务器或Filter链中的上一个Filter传递过来的请求和响应对象；
//    //参数chain代表当前Filter链的对象，
//    //只有在当前Filter对象中的doFilter()方法内部需要调用FilterChain对象的doFilter()法才能把请求交付给Filter链中的下一个Filter或者目标程序处理
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("这里是第一顺序的拦截器");//多次调用servletResponse会报错
////        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    //destroy()方法在Web服务器卸载Filter对象之前被调用，该方法用于释放被Filter对象打开的资源，例如关闭数据库和I/O流
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//        System.out.println("过滤器被销毁");
//    }
//}
