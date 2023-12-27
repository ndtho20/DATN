package com.example.demo;


import com.example.demo.Interceptor.GloballInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    GloballInterceptor globallInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globallInterceptor)
                .addPathPatterns("/**") //lấy
                .excludePathPatterns("/rest/**","/admin/**","/asets/**"); //loại trừ
    }
}
