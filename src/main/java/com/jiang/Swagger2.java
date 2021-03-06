package com.jiang;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: purchase-sale-stock
 * @description: Swagger2配置类
 * @author: lvjx
 * @create: 2020-04-20 10:10
 **/
@Configuration
@EnableSwagger2
@Profile({"dev","test"})
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        /*ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("Authorization")
                .description("令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build();
        pars.add(tokenPar.build());*/

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .host("127.0.0.1:80")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jiang"))
                .paths(PathSelectors.any())
                .build();
                //.globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("进销存 APIs")
                .description("进销存 APIs")
                .version("1.0")
                .build();
    }
}
