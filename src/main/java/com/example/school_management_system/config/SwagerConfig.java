package com.example.school_management_system.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwagerConfig {
@Bean
    public OpenAPI openAPIconfig(){
    return new OpenAPI().info(new Info().title("School Management System").version("v1").description("APis For School Management System"));
}
}
