package com.naijaswift.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI naijaSwiftOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("NaijaSwift API")
                .description("Secure P2P Wallet API for seamless transactions.")
                .version("1.0"));
    }
}

