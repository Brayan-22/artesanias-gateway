package com.artesanias.gateway.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API Gateway",version = "1.0", description = "API Gateway"))
public class GatewayConfig {


    private final AuthenticationFilter authenticationFilter;

    public GatewayConfig(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/commerce/v3/api-docs")
                        .and().method(HttpMethod.GET).uri("lb://COMMERCE-SERVICE"))
                .route(r -> r.path("inventory/v3/api-docs")
                        .and().method(HttpMethod.GET).uri("lb://INVENTORY-SERVICE"))
                .route(r -> r.path("user/v3/api-docs")
                        .and().method(HttpMethod.GET).uri("lb://USER-SERVICE"))
                .build();
    }
}