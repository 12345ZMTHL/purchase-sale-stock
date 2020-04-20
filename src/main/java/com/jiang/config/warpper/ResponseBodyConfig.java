package com.jiang.config.warpper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@ConditionalOnExpression("${spring.web.result.wapper.enable:false}")
@Configuration
public class ResponseBodyConfig {
    private static final Logger log = LoggerFactory.getLogger(ResponseBodyConfig.class);

    public ResponseBodyConfig() {
    }

    @PostConstruct
    public void init() {
        log.debug("启用spring结果集封装");
    }

    @Bean
    public ResponseBodyWrapFactoryBean getResponseBodyWrap() {
        return new ResponseBodyWrapFactoryBean();
    }
}
