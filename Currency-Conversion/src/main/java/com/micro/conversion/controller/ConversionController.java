package com.micro.conversion.controller;

import static com.micro.conversion.AppConstants.CURRENCY_EXCHANGE;
import com.micro.conversion.beans.ConversionInfo;
import com.micro.conversion.proxy.CurrencyFeignProxy;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConversionController {
    
    @Autowired
    private RestTemplate rest;
    
    @Autowired
    private CurrencyFeignProxy feignProxy;
    
    @Autowired
    private DiscoveryClient client;
    
    private final String path = "/currency-conversion/from/{from}/to/{to}/quantity/{quantity}";
    private final String pathFeign = "/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}";
    private final Logger logger = LoggerFactory.getLogger(ConversionController.class);
    
    @GetMapping(pathFeign)
    public ConversionInfo getExchangeValueFeign(@PathVariable String from, 
                                          @PathVariable String to,
                                          @PathVariable int quantity){
        
        logger.info("GET " + pathFeign);
        ConversionInfo exchangeValueFeign = feignProxy.getExchangeValueFeign(from, to);
        
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
    
    @GetMapping(path)
    public ConversionInfo getExchangeValue(@PathVariable String from, 
                                          @PathVariable String to,
                                          @PathVariable int quantity){
        
        String url = "/currency-exchange/from/{from}/to/{to}";
        String host = "http://localhost:8100";
        logger.info("GET " + url + "/quantity/{quantity}");
        
        Map<String, String> restVars = new HashMap<>();
        restVars.put("from", from);
        restVars.put("to", to);
        
        ConversionInfo info = rest.getForEntity(host + url, ConversionInfo.class, restVars).getBody();
        logger.info(info.toString());
        return new ConversionInfo(info.getId(), info.getPort(), quantity, 
                                    info.getConversionFactor(), info.getTotalAmount(), from, to);
    }
}
