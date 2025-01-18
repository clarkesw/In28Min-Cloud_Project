package com.micro.conversion.controller;

import com.micro.conversion.beans.ConversionInfo;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class ConversionController {
    
    @Autowired
    private RestTemplate rest;
    
    Logger logger = LoggerFactory.getLogger(ConversionController.class);
    
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
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
