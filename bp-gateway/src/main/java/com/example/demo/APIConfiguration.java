package com.example.demo;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIConfiguration {
    @Bean
    public RouteLocator applicationLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("create application", p->p.path("/application/**")
//						.filters(filter -> filter.stripPrefix(1))
                        .uri("http://localhost:8081/"))
//				.route("notification", p->p.path("/notification/**")
//						.filters(filter -> filter.stripPrefix(1))
//						.uri("microservice_path//notification"))
//				.route("auth", p->p.path("/auth/**")
//						.filters(filter -> filter.stripPrefix(1))
//						.uri("microservice_path//auth"))
                .build();
    }
}
