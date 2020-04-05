package com.example.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket apiMain() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("MAIN").select()
				.apis(RequestHandlerSelectors.basePackage("com.example.controller")).paths(regex("/.*"))
				.build().apiInfo(metaInfo()).globalOperationParameters(
						Arrays.asList(new ParameterBuilder().name("Authorization").description("for jwt token")
								.modelRef(new ModelRef("string")).parameterType("header").required(true).build()));
	}
	
	@Bean
	public Docket apiAuth() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("AUTH").select()
				.apis(RequestHandlerSelectors.basePackage("com.example.security.controller")).paths(regex("/.*"))
				.build().apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {


		return new ApiInfo("APIs", "Resful API Documentaion", "1.0", "Terms of Service",
				new Contact("Mark Louie Perez", "https://www.linkedin.com/in/mark-louie-perez-8741b5b8/",
						StringUtils.EMPTY),
				"Apache License Version 2.0", "https://www.apache.org/licesen.html");
	}

}
