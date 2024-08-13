package com.githrd.demo_interceptor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.githrd.demo_interceptor.interceptor.LoggerInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor())
                .addPathPatterns("/admin/**", "/adult/**")      // 이 경로로 진입하는 경우에만 통과 시키겠다.
                .excludePathPatterns("/css/**", "/images/**", "/js/**");    // 이건 통과 안 시키겠다(이미지 굳이 interceptor 걸 필요X)
    }

}
