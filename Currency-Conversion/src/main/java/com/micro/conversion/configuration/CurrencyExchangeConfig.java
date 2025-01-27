/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.micro.conversion.configuration;

import com.micro.conversion.ExchangeInstanceSupplier;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CurrencyExchangeConfig {
    
    @Bean
    @Primary
    public ServiceInstanceListSupplier serviceInstanceList() {
        return new ExchangeInstanceSupplier();
    }
}
