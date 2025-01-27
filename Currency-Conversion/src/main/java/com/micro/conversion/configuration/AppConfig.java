package com.micro.conversion.configuration;

import com.micro.conversion.ExchangeInstanceSupplier;
import java.time.Duration;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        return builder
                .readTimeout(Duration.ofMillis(3000))
                .build();
    }

//    @Bean
////    @Primary
//    public ServiceInstanceListSupplier serviceInstanceList() {
//        return new ExchangeInstanceSupplier();
//    }
}
