package com.example.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    private static final String ALLOWED_HEADERS = "authorization, Content-Type, Content-Length, Authorization, credential, X-XSRF-TOKEN";
    private static final String ALLOWED_METHODS = "*";
    private static final String ALLOWED_ORIGIN = "*";
    private static final String EXPOSE_HEADERS = "*, Authorization";
    private static final String MAX_AGE = "7200"; //2 hours (2 * 60 * 60)

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(r -> r.path("/api/v1/event/**")
                .filters(f -> f.addRequestHeader("event-request", "event-request-header")
                    .addResponseHeader("event-response", "event-header"))
                .uri("http://localhost:8081"))
            .route(r -> r.path("/api/v1/**")
                .filters(f -> f.addRequestHeader("market-request", "market-request-header")
                    .addResponseHeader("market-response", "market-header"))
                .uri("http://localhost:8080"))
            .build();
    }
}
