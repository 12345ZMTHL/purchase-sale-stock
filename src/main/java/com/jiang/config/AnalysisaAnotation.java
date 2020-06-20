package com.jiang.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 12345芝麻糖葫芦
 * @ClassName: AnalysisaAnotation
 * @Description: 数据处理注解
 * @date 2020/6/20 15:21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnalysisaAnotation {
    boolean flagAnalysis() default false;
    int eventId();
}
