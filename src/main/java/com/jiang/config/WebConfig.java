package com.jiang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 12345芝麻糖葫芦
 * @ClassName: WebConfig
 * @Description: TODO
 * @date 2020/6/20 16:01
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public AnalysisAnotationInterceptor AnalysisAnotationInterceptor(){
        return new AnalysisAnotationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(AnalysisAnotationInterceptor()).addPathPatterns("/**");
    }
}
