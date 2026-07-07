package com.kayque.streamhubapi.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI streamHubOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("StreamHub API")
                        .description("Streaming Platform REST API")
                        .version("1.0.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub Repository"));
    }
}