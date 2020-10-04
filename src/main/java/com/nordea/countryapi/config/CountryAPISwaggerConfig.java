package com.nordea.countryapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.nordea.countryapi.common.APICommonConstants.API_CONTEXT;
import static com.nordea.countryapi.common.APICommonConstants.COUNTRY_CONTEXT_STR;

@Configuration
@EnableSwagger2
public class CountryAPISwaggerConfig {

    private static final String SWAGGER_PATH = API_CONTEXT + COUNTRY_CONTEXT_STR + ".*";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex(SWAGGER_PATH))
                .build();
    }
}


