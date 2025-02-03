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

//    private final AuthenticationFilter filter;
//
//    public GatewayConfig(AuthenticationFilter filter){
//        this.filter = filter;
//    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
//        return builder.routes()
//                .route("user-service", r -> r.path("/users/**")
//                        .filters(f -> f.filter(filter)
//                                .circuitBreaker(config -> config.setName("user-service-circuit-breaker")
//                                        .setFallbackUri("forward:/fallback/users")))
//                        .uri("lb://user-service"))
//                .route("auth-service", r -> r.path("/auth/**")
//                        .filters(f -> f.filter(filter))
//                        .uri("lb://auth-service"))
//                .build();
        return builder.routes()
                .route(r -> r.path("/commerce/v3/api-docs")
                        .and().method(HttpMethod.GET).uri("lb://COMMERCE-SERVICE"))
                .build();
    }
}