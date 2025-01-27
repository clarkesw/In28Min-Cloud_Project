package com.micro.conversion.proxy;

import com.micro.conversion.beans.ConversionInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@LoadBalanced(name="currency-exchange")
@FeignClient(name="currency-exchange") //, url="http://localhost:8100")
public interface CurrencyFeignProxy {
       
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ConversionInfo getExchangeValueFeign(@PathVariable("from") String from, @PathVariable("to") String to);
    
    @GetMapping("/actuator/health")
    public String getFeignTest();
}
