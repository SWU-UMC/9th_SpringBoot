package com.example.umc.global.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI swagger() {

        Info info = new Info()
                .title("Project")
                .description("Project Swagger")
                .version("0.0.1");

        String schemeName = "JWT TOKEN";

        // Security Requirement
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(schemeName);

        // Security Scheme (Bearer Token)
        SecurityScheme securityScheme = new SecurityScheme()
                .name(schemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("Bearer")
                .bearerFormat("JWT");

        Components components = new Components()
                .addSecuritySchemes(schemeName, securityScheme);

        return new OpenAPI()
                .info(info)
                .addServersItem(new Server().url("/"))
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}
