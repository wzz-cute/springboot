package com.wzz.base.Interceptor.config;

import com.wzz.base.Interceptor.BaseInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MyAppCofnig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        BaseInterceptor loginInterceptor = new BaseInterceptor();
        String[] path = {"/base/api/**"};
        String[] excludePath = {"/pic/**"};
        registry.addInterceptor(loginInterceptor).addPathPatterns(path).excludePathPatterns(excludePath);
    }
}
