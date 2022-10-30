package com.example.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(r -> r.path("/api/v1/event/**")
                .filters(f -> f.addRequestHeader("first-request", "first-request-header")
                    .addResponseHeader("first-response", "first-header"))
                .uri("http://localhost:8081"))
            .route(r -> r.path("/api/v1/**")
                .filters(f -> f.addRequestHeader("parabole-request", "parabole-request-header")
                    .addResponseHeader("parabole-response", "parabole-header"))
                .uri("http://localhost:8080"))
            .build();
    }
}
