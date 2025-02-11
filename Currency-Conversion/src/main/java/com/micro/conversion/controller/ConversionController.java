package com.micro.conversion.controller;

import static com.micro.conversion.AppConstants.CURRENCY_EXCHANGE;
import com.micro.conversion.beans.ConversionInfo;
import com.micro.conversion.proxy.CurrencyFeignProxy;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConversionController {
    
    @Autowired
    private CurrencyFeignProxy feignProxy;
    
    @Autowired
    private DiscoveryClient client;
    
    private final String pathFeign = "/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}";
    private final Logger logger = LoggerFactory.getLogger(ConversionController.class);
    private final Counter conversionRequests;

    @Autowired
    public ConversionController(MeterRegistry meterRegistry) {
        this.conversionRequests = Counter.builder("currency_conversion_requests")
                                         .description("Number of currency conversion requests")
                                         .register(meterRegistry);
    }
    
    @GetMapping(pathFeign)
    public ConversionInfo getExchangeValueFeign(@PathVariable String from, 
                                          @PathVariable String to,
                                          @PathVariable int quantity){ 
        logger.info("GET " + pathFeign);
        ConversionInfo exchangeValueFeign = feignProxy.getExchangeValueFeign(from, to);
        conversionRequests.increment();
        
        client.getInstances(CURRENCY_EXCHANGE).forEach(
                   instance -> logger.info("Instance ID: " + instance.getInstanceId() + "Service ID: " + 
                                            instance.getServiceId() + " Port: " + instance.getPort()));

        return new ConversionInfo(exchangeValueFeign.getId(), exchangeValueFeign.getPort(), quantity, 
                                  exchangeValueFeign.getConversionFactor(), 
                                  exchangeValueFeign.getTotalAmount(), from, to);
    }
    
    @GetMapping("/test")
    public String getFeignTest(){
        logger.info("GET " + "/actuator/health");
        
        return feignProxy.getFeignTest();
    }
}
