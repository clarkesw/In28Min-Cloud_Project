/*
      How to query the Eureka server for the list of names?
 */
package com.micro.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    private final String conversionUri = "lb://" + "CURRENCY-CONVERSION";

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("currency-conversion-service", r -> r.path("/test/**")
                .uri(conversionUri)) // Load balancing via Eureka service discovery
                .build();
    }
}

@ConfigurationProperties
class UriConfiguration {

    private String httpbin = "http://localhost:8000";

    public String getHttpbin() {
        return httpbin;
    }

    public void setHttpbin(String httpbin) {
        this.httpbin = httpbin;
    }
}
