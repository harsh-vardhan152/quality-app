package com.harshvardhan.quality_app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerOverrideConfigImpl {

    @Bean
    public OpenAPI swaggerConfig(){
        return new OpenAPI().info(new Info()
                .title("Quality Tracker API")
                .description("API documentation for Task and Role Management").version("1.0"));
    }
}
