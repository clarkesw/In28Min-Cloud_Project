package com.micro.conversion;

import java.util.Arrays;
import java.util.List;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;


public class ExchangeInstanceSupplier implements ServiceInstanceListSupplier {

    final String serviceId = "currency-exchange";

    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        List<ServiceInstance> instances = Arrays.asList(
                new DefaultServiceInstance(serviceId + "1", serviceId, "localhost", 8100, false),
                new DefaultServiceInstance(serviceId + "2", serviceId, "localhost", 8101, false),
                new DefaultServiceInstance(serviceId + "2", serviceId, "localhost", 8102, false)
        );
        return Flux.just(instances);
    }

}
