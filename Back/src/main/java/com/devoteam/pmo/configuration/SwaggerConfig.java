package com.devoteam.pmo.configuration;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.devoteam.pmo.controller")) 
            .paths(PathSelectors.any())
            .build();
    }
    @SuppressWarnings("unused")
	private ApiInfo apiInfo() {
        @SuppressWarnings("deprecation")
		ApiInfo apiInfo = new ApiInfo(
                "Spring Boot Swagger Tutorial",
                "Tutorial for how to use Swagger in spring boot",
                "1.0",
                "Terms of service XXX", "okok", "lklk", "lklkl");
        return apiInfo;
    }

}
