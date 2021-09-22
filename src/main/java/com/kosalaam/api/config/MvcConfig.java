package com.kosalaam.api.config;

import com.kosalaam.api.common.RequiredAuthInterceptor;
import com.kosalaam.api.common.UnRequiredAuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final RequiredAuthInterceptor requiredAuthInterceptor;

    private final UnRequiredAuthInterceptor unrequiredAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 인증이 필수인 자원
        registry.addInterceptor(requiredAuthInterceptor)
                .addPathPatterns("/api/auth/**")
                .addPathPatterns("/api/**/like")
                .addPathPatterns("/api/**/review");

        // 인증이 선택인 자원
        registry.addInterceptor(unrequiredAuthInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/auth/**");

    }

}
