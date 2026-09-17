package com.slabi.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI slabiOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Slabi API")
                        .version("v1")
                        .description("API de gestão para pequenas e médias construtoras.")
                        .license(new License().name("Uso interno")));
    }
}
