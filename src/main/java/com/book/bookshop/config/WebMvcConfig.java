package com.book.bookshop.config;

import com.book.bookshop.interceptor.PremissionInterecptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author qianjin
 * @create 2022-02-14 18:36
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("*public/**").addResourceLocations("file:///H:image/book/");
    }

    //注册定义拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new PremissionInterecptor()).addPathPatterns("/order/**","/cart/**");

    }
}
