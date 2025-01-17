package com.micro.conversion;

import java.time.Duration;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

            return builder
                    .setConnectTimeout(Duration.ofMillis(3000))
                    .setReadTimeout(Duration.ofMillis(3000))
                    .build();
    }
}
