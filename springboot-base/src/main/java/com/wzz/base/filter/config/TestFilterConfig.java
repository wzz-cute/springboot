package com.wzz.base.filter.config;

import com.wzz.base.filter.TestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestFilterConfig {
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean(){
//        //创建一个注册过滤器对象
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        //设置自定义过滤器
//        registrationBean.setFilter(new TestFilter());
//        //设置过滤拦截匹配规则,/*是匹配所有
////        registrationBean.addUrlPatterns("/*");
//        //只拦截testController下面的接口
//        registrationBean.addUrlPatterns("/stu/*");
//        //存在多个过滤器时，设置执行顺序，值越大，执行顺序越靠后
//        registrationBean.setOrder(2);
//        //返回这个注册过滤器对象
//        return registrationBean;
//    }

//    @Bean
//    public FilterRegistrationBean filterRegistrationBean2(){
//        //创建一个注册过滤器对象
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        //设置自定义过滤器
//        registrationBean.setFilter(new TestFilter2());
//        //设置过滤拦截匹配规则,/*是匹配所有
////        registrationBean.addUrlPatterns("/*");
//        //只拦截testController下面的接口
//        registrationBean.addUrlPatterns("/stu/*");
//        //存在多个过滤器时，设置执行顺序，值越大，执行顺序越靠后
//        registrationBean.setOrder(1);
//        //返回这个注册过滤器对象
//        return registrationBean;
//    }
}
