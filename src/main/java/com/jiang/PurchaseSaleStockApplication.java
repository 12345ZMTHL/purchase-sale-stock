package com.jiang;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;

@SpringBootApplication(scanBasePackages = "com.jiang")
@EnableJpaRepositories(basePackages = "com.jiang.repository")
@EntityScan(basePackages = "com.jiang.entity")
public class PurchaseSaleStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurchaseSaleStockApplication.class, args);
    }
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager){
        return new JPAQueryFactory(entityManager);
    }
}
