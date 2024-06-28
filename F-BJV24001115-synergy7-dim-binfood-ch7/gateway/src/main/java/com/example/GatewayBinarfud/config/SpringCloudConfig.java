package com.example.GatewayBinarfud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("Binarfud", r->r.path("/binarfud/**")
                        .uri("http://localhost:8081")
                )
                .route("Notifier", r->r.path("/notifier/**")
                        .uri("http://localhost:8082")
                )
                .build();

    }
}
