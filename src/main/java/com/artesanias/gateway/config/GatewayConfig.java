package com.artesanias.gateway.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@OpenAPIDefinition(info = @Info(title = "API Gateway",version = "1.0", description = "API Gateway"))
public class GatewayConfig {

    private final AuthenticationFilter authenticationFilter;

    public GatewayConfig(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder, RouteLocator routes){
        return builder.routes()
                .route("auth-service", r -> r.path("/auth/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://auth-service"))
                .route("user-service", r -> r.path("/user/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://user-service"))
                .build();

    }

}