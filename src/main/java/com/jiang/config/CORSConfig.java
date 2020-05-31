package com.jiang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 12345芝麻糖葫芦
 * @ClassName: CORSConfig
 * @Description: 解决跨域问题CROS
 * @date 2020/5/5 18:54
 */
@Configuration
public class CORSConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")   //对哪种格式的路径进行跨域处理
                .allowedHeaders("*")    //允许的请求头
                .allowedMethods("*")    //允许的请求方法
                .allowedMethods("GET", "POST","DELETE")
                .allowCredentials(false)
                .maxAge(3600);
    }
}
